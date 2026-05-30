/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class KardexDAO {

    private final Conexion cn = new Conexion();

    public ArrayList<Kardex> listarKardex(Date fechaInicio, Date fechaFin, String tipoMovimiento, String busqueda) {
        ArrayList<Kardex> lista = new ArrayList<>();

        String sql = "SELECT "
                + "k.id_movimiento, "
                + "DATE_FORMAT(k.fecha_hora, '%Y-%m-%d %H:%i:%s') AS fecha_hora, "
                + "k.tipo_movimiento, "
                + "p.nombre AS producto, "
                + "p.codigo_barras, "
                + "l.numero_lote, "
                + "k.cantidad, "
                + "CONCAT(u.nombre, ' ', u.apellido) AS usuario, "
                + "v.numero_comprobante AS comprobante_venta, "
                + "c.numero_comprobante AS comprobante_compra, "
                + "k.id_venta, "
                + "k.id_compra, "
                + "k.motivo_ajuste "
                + "FROM KARDEX k "
                + "INNER JOIN LOTES l ON k.id_lote = l.id_lote "
                + "INNER JOIN PRODUCTOS p ON l.id_producto = p.id_producto "
                + "INNER JOIN USUARIOS u ON k.id_usuario = u.id_usuario "
                + "LEFT JOIN VENTAS v ON k.id_venta = v.id_venta "
                + "LEFT JOIN COMPRAS c ON k.id_compra = c.id_compra "
                + "WHERE 1 = 1 ";

        if (fechaInicio != null) {
            sql += "AND DATE(k.fecha_hora) >= ? ";
        }

        if (fechaFin != null) {
            sql += "AND DATE(k.fecha_hora) <= ? ";
        }

        if (tipoMovimiento != null && !tipoMovimiento.equals("Todos")) {
    if (tipoMovimiento.equals("AJUSTE")) {
        sql += "AND UPPER(k.tipo_movimiento) LIKE '%AJUSTE%' ";
    } else if (tipoMovimiento.equals("ENTRADA")) {
        sql += "AND UPPER(k.tipo_movimiento) LIKE '%ENTRADA%' ";
    } else if (tipoMovimiento.equals("SALIDA")) {
        sql += "AND UPPER(k.tipo_movimiento) LIKE '%SALIDA%' ";
    }
}

        if (busqueda != null && !busqueda.trim().isEmpty()) {
            sql += "AND (p.nombre LIKE ? OR p.codigo_barras LIKE ? OR l.numero_lote LIKE ?) ";
        }

        sql += "ORDER BY k.fecha_hora DESC";

        try {
            Connection con = cn.conectar();
            PreparedStatement ps = con.prepareStatement(sql);

            int index = 1;

            if (fechaInicio != null) {
                ps.setDate(index++, new java.sql.Date(fechaInicio.getTime()));
            }

            if (fechaFin != null) {
                ps.setDate(index++, new java.sql.Date(fechaFin.getTime()));
            }

           

            if (busqueda != null && !busqueda.trim().isEmpty()) {
                String filtro = "%" + busqueda.trim() + "%";
                ps.setString(index++, filtro);
                ps.setString(index++, filtro);
                ps.setString(index++, filtro);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Kardex kardex = new Kardex();

                kardex.setIdMovimiento(rs.getInt("id_movimiento"));
                kardex.setFechaHora(rs.getString("fecha_hora"));
                kardex.setTipoMovimiento(rs.getString("tipo_movimiento"));
                kardex.setProducto(rs.getString("producto"));
                kardex.setCodigoBarras(rs.getString("codigo_barras"));
                kardex.setLote(rs.getString("numero_lote"));
                kardex.setCantidad(rs.getInt("cantidad"));
                kardex.setUsuario(rs.getString("usuario"));

                Integer idVenta = (Integer) rs.getObject("id_venta");
                Integer idCompra = (Integer) rs.getObject("id_compra");

                String comprobanteVenta = rs.getString("comprobante_venta");
                String comprobanteCompra = rs.getString("comprobante_compra");
                String motivoAjuste = rs.getString("motivo_ajuste");

                // --- NUEVA LÓGICA DE PRIORIDAD ---
                // Si el registro tiene un motivo de ajuste (como la Anulación), le damos prioridad.
                if (motivoAjuste != null && !motivoAjuste.trim().isEmpty()) {
                    
                    // Si además de ser una anulación, tiene el comprobante, lo agregamos para más contexto
                    if (motivoAjuste.equals("Anulacion de Factura") && comprobanteVenta != null) {
                        kardex.setRespaldoMotivo(motivoAjuste + " #" + comprobanteVenta);
                    } else {
                        kardex.setRespaldoMotivo(motivoAjuste);
                    }

                } else if (idVenta != null) {
                    if (comprobanteVenta != null) {
                        kardex.setRespaldoMotivo("Venta #" + comprobanteVenta);
                    } else {
                        kardex.setRespaldoMotivo("Venta ID #" + idVenta);
                    }
                } else if (idCompra != null) {
                    if (comprobanteCompra != null) {
                        kardex.setRespaldoMotivo("Compra #" + comprobanteCompra);
                    } else {
                        kardex.setRespaldoMotivo("Compra ID #" + idCompra);
                    }
                } else {
                    kardex.setRespaldoMotivo("Sin respaldo registrado");
                }

                lista.add(kardex);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al listar Kardex: " + e.getMessage());
        }

        return lista;
    }
}
