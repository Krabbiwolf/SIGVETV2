package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class ConsultarFacturasDAO {

    Conexion con = new Conexion();

    // 1. Cargar las facturas con filtro de fechas
    public DefaultTableModel listarFacturas(java.util.Date inicio, java.util.Date fin) {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID Venta", "N° Comprobante", "Fecha", "Estado", "Cliente", "Empleado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String sql = "SELECT v.id_venta, v.numero_comprobante, v.fecha_hora, v.estado, "
                + "CONCAT(COALESCE(c.nombre, ''), ' ', COALESCE(c.apellido, '')) AS cliente, "
                + "u.nombre AS empleado "
                + "FROM VENTAS v "
                + "INNER JOIN CLIENTES c ON v.id_cliente = c.id_cliente "
                + "INNER JOIN USUARIOS u ON v.id_usuario = u.id_usuario ";

        boolean filtrarPorFechas = (inicio != null && fin != null);
        if (filtrarPorFechas) {
            sql += "WHERE DATE(v.fecha_hora) BETWEEN ? AND ? ";
        }
        sql += "ORDER BY v.id_venta DESC";

        try (Connection cn = con.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            if (filtrarPorFechas) {
                ps.setDate(1, new java.sql.Date(inicio.getTime()));
                ps.setDate(2, new java.sql.Date(fin.getTime()));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt("id_venta"),
                        rs.getString("numero_comprobante"),
                        rs.getTimestamp("fecha_hora"),
                        rs.getString("estado"),
                        rs.getString("cliente").trim(),
                        rs.getString("empleado")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar facturas: " + e.getMessage());
        }
        return modelo;
    }

    // 2. Anular una factura
    public boolean anularFactura(int idVenta) {
        String sql = "UPDATE VENTAS SET estado = 'ANULADA' WHERE id_venta = ?";
        try (Connection cn = con.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // 3. Obtener los datos generales de la venta para el PDF (Cabecera)
    public Map<String, String> obtenerCabeceraFactura(int idVenta) {
        Map<String, String> datos = new HashMap<>();
        String sql = "SELECT v.numero_comprobante, v.fecha_hora, "
                + "CONCAT(COALESCE(c.nombre, ''), ' ', COALESCE(c.apellido, '')) AS cliente, "
                + "c.dui, u.nombre AS cajero "
                + "FROM VENTAS v "
                + "JOIN CLIENTES c ON v.id_cliente = c.id_cliente "
                + "JOIN USUARIOS u ON v.id_usuario = u.id_usuario "
                + "WHERE v.id_venta = ?";

        try (Connection cn = con.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    datos.put("comprobante", rs.getString("numero_comprobante"));
                    datos.put("fecha", rs.getString("fecha_hora"));
                    datos.put("cliente", rs.getString("cliente"));
                    datos.put("dui", rs.getString("dui") != null ? rs.getString("dui") : "N/A");
                    datos.put("cajero", rs.getString("cajero"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error cabecera PDF: " + e.getMessage());
        }
        return datos;
    }

    // 4. Obtener los productos vendidos para el PDF (Detalle)
    public List<Object[]> obtenerDetallesFactura(int idVenta) {
        List<Object[]> detalles = new ArrayList<>();

        // Consulta limpia de 1 a 1: extrae cada fila independiente de DETALLE_VENTAS
        // y hace un JOIN con PRODUCTOS únicamente para traer el nombre.
        String sql = "SELECT dv.cantidad, p.nombre AS nombre_producto, dv.precio_unitario, dv.porcentaje_iva "
                + "FROM DETALLE_VENTAS dv "
                + "INNER JOIN PRODUCTOS p ON dv.id_producto = p.id_producto "
                + "WHERE dv.id_venta = ? "
                + "ORDER BY dv.id_detalle_venta ASC"; // Ordenados por el orden de inserción

        try (Connection cn = con.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    detalles.add(new Object[]{
                        rs.getInt("cantidad"),
                        rs.getString("nombre_producto"),
                        rs.getDouble("precio_unitario"),
                        rs.getDouble("porcentaje_iva")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error detalles PDF: " + e.getMessage());
        }
        return detalles;
    }
}
