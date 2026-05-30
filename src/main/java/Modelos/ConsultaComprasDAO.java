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

public class ConsultaComprasDAO {

    Conexion con = new Conexion();

    // 1. Cargar las compras con filtro de fechas
    public DefaultTableModel listarCompras(java.util.Date inicio, java.util.Date fin) {
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID Compra", "N° Comprobante", "Fecha", "Estado", "Proveedor", "Empleado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        String sql = "SELECT c.id_compra, c.numero_comprobante, c.fecha_hora, c.estado, "
                   + "p.nombre_empresa AS proveedor, "
                   + "u.nombre AS empleado "
                   + "FROM COMPRAS c "
                   + "INNER JOIN PROVEEDORES p ON c.id_proveedor = p.id_proveedor "
                   + "INNER JOIN USUARIOS u ON c.id_usuario = u.id_usuario ";

        boolean filtrarPorFechas = (inicio != null && fin != null);
        if (filtrarPorFechas) {
            sql += "WHERE DATE(c.fecha_hora) BETWEEN ? AND ? ";
        }
        sql += "ORDER BY c.id_compra DESC";

        try (Connection cn = con.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            if (filtrarPorFechas) {
                ps.setDate(1, new java.sql.Date(inicio.getTime()));
                ps.setDate(2, new java.sql.Date(fin.getTime()));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt("id_compra"),
                        rs.getString("numero_comprobante"),
                        rs.getTimestamp("fecha_hora"),
                        rs.getString("estado"),
                        rs.getString("proveedor"),
                        rs.getString("empleado")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar compras: " + e.getMessage());
        }
        return modelo;
    }

    // 2. Anular una compra
    public boolean anularCompra(int idCompra) {
        String sql = "UPDATE COMPRAS SET estado = 'ANULADA' WHERE id_compra = ?";
        try (Connection cn = con.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // 3. Obtener los datos generales de la compra para el PDF (Cabecera)
    public Map<String, String> obtenerCabeceraCompra(int idCompra) {
        Map<String, String> datos = new HashMap<>();
        String sql = "SELECT c.numero_comprobante, c.fecha_hora, "
                   + "p.nombre_empresa AS proveedor, p.telefono, "
                   + "u.nombre AS empleado "
                   + "FROM COMPRAS c "
                   + "INNER JOIN PROVEEDORES p ON c.id_proveedor = p.id_proveedor "
                   + "INNER JOIN USUARIOS u ON c.id_usuario = u.id_usuario "
                   + "WHERE c.id_compra = ?";
        
        try (Connection cn = con.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    datos.put("comprobante", rs.getString("numero_comprobante"));
                    datos.put("fecha", rs.getString("fecha_hora"));
                    datos.put("proveedor", rs.getString("proveedor"));
                    datos.put("telefono", rs.getString("telefono") != null ? rs.getString("telefono") : "N/A");
                    datos.put("empleado", rs.getString("empleado"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error cabecera compra PDF: " + e.getMessage());
        }
        return datos;
    }

    // 4. Obtener los productos comprados para el PDF (Detalle)
    public List<Object[]> obtenerDetallesCompra(int idCompra) {
        List<Object[]> detalles = new ArrayList<>();
        
        // Consulta limpia de 1 a 1: extrae cada fila independiente de DETALLE_COMPRAS
        // Usamos LEFT JOIN por si algún producto fue eliminado, para que no desaparezca la fila
        String sql = "SELECT dc.cantidad_comprada AS cantidad, p.nombre AS nombre_producto, "
                   + "dc.precio_compra, dc.porcentaje_iva "
                   + "FROM DETALLE_COMPRAS dc "
                   + "LEFT JOIN PRODUCTOS p ON dc.id_producto = p.id_producto "
                   + "WHERE dc.id_compra = ? "
                   + "ORDER BY dc.id_detalle_compra ASC";
        
        try (Connection cn = con.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String nombreProd = rs.getString("nombre_producto");
                    detalles.add(new Object[]{
                        rs.getInt("cantidad"),
                        nombreProd != null ? nombreProd : "Producto Desconocido",
                        rs.getDouble("precio_compra"),
                        rs.getDouble("porcentaje_iva")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error detalles compra PDF: " + e.getMessage());
        }
        return detalles;
    }
}