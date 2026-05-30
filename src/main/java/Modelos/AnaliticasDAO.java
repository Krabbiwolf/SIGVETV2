/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnaliticasDAO {

    private final Conexion conexion = new Conexion();

    private String getColumnaFechaVenta() {
        String col = "fecha"; 
        try (Connection con = conexion.conectar();
             ResultSet rs = con.getMetaData().getColumns(null, null, "VENTAS", "fecha%")) {
            if (rs.next()) col = rs.getString("COLUMN_NAME");
        } catch(Exception e){}
        return col;
    }

    // ====================================================
    // KPIs del Dashboard
    // ====================================================
    public int getTotalClientes() {
        int total = 0;
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM CLIENTES WHERE UPPER(estado) = 'ACTIVO'")) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getInt(1);
        } catch (Exception e) { System.out.println("Error total clientes: " + e.getMessage()); }
        return total;
    }

    public int getTotalProductos() {
        int total = 0;
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM PRODUCTOS WHERE UPPER(estado) = 'ACTIVO'")) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getInt(1);
        } catch (Exception e) { System.out.println("Error total productos: " + e.getMessage()); }
        return total;
    }

    public double getVentasDelDia() {
        double total = 0;
        String colFecha = getColumnaFechaVenta();
        String sql = "SELECT SUM(d.cantidad * d.precio_unitario) FROM DETALLE_VENTAS d " +
                     "INNER JOIN VENTAS v ON d.id_venta = v.id_venta " +
                     "WHERE DATE(v." + colFecha + ") = CURDATE() AND UPPER(v.estado) <> 'ANULADO'";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getDouble(1);
        } catch (Exception e) { System.out.println("Error ventas hoy: " + e.getMessage()); }
        return total;
    }

    public int getAlertasStock() {
        int total = 0;
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM LOTES WHERE stock_actual <= 5")) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) total = rs.getInt(1);
        } catch (Exception e) { System.out.println("Error alertas stock: " + e.getMessage()); }
        return total;
    }

    public ArrayList<Object[]> obtenerLotesCriticos() {
        ArrayList<Object[]> lista = new ArrayList<>();
        String sql = "SELECT p.nombre, l.numero_lote, l.stock_actual FROM LOTES l " +
                     "INNER JOIN PRODUCTOS p ON l.id_producto = p.id_producto " +
                     "WHERE l.stock_actual <= 5 ORDER BY l.stock_actual ASC LIMIT 5";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Object[]{rs.getString("nombre"), rs.getString("numero_lote"), rs.getInt("stock_actual")});
            }
        } catch (Exception e) { System.out.println("Error lotes criticos: " + e.getMessage()); }
        return lista;
    }

    // ====================================================
    // GRÁFICOS
    // ====================================================
    public Map<String, Double> getVentasUltimos7Dias() {
        Map<String, Double> datos = new LinkedHashMap<>();
        String colFecha = getColumnaFechaVenta();
        String sql = "SELECT DATE(v." + colFecha + ") as fecha, SUM(d.cantidad * d.precio_unitario) as total " +
                     "FROM VENTAS v INNER JOIN DETALLE_VENTAS d ON v.id_venta = d.id_venta " +
                     "WHERE v." + colFecha + " >= DATE_SUB(CURDATE(), INTERVAL 7 DAY) AND UPPER(v.estado) <> 'ANULADO' " +
                     "GROUP BY DATE(v." + colFecha + ") ORDER BY fecha ASC";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String key = rs.getString("fecha");
                if (key != null) datos.put(key, rs.getDouble("total"));
            }
        } catch (Exception e) { System.out.println("Error grafico ventas: " + e.getMessage()); }
        return datos;
    }

    // LECTURA REAL DE STOCK POR CATEGORÍA
    public Map<String, Integer> getProductosPorCategoria() {
        Map<String, Integer> datos = new LinkedHashMap<>();
        // LEFT JOIN obliga a mostrar todas las categorías, SUM(l.stock_actual) nos da el valor real físico
        String sql = "SELECT c.nombre, COALESCE(SUM(l.stock_actual), 0) as total_stock " +
                     "FROM CATEGORIAS c " +
                     "LEFT JOIN PRODUCTOS p ON c.id_categoria = p.id_categoria " +
                     "LEFT JOIN LOTES l ON p.id_producto = l.id_producto " +
                     "GROUP BY c.id_categoria, c.nombre";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String key = rs.getString("nombre");
                if (key == null || key.trim().isEmpty()) key = "Sin Categor\u00EDa";
                datos.put(key, rs.getInt("total_stock"));
            }
        } catch (Exception e) { System.out.println("Error grafico categorias: " + e.getMessage()); }
        return datos;
    }

    public Map<String, Double> getTopProductosMasVendidos() {
        Map<String, Double> datos = new LinkedHashMap<>();
        String sql = "SELECT p.nombre, SUM(d.cantidad) as total FROM DETALLE_VENTAS d " +
                     "INNER JOIN PRODUCTOS p ON d.id_producto = p.id_producto " +
                     "INNER JOIN VENTAS v ON d.id_venta = v.id_venta WHERE UPPER(v.estado) <> 'ANULADO' " +
                     "GROUP BY p.id_producto, p.nombre ORDER BY total DESC LIMIT 5";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String key = rs.getString("nombre");
                if (key != null) datos.put(key, rs.getDouble("total"));
            }
        } catch (Exception e) { System.out.println("Error top productos: " + e.getMessage()); }
        return datos;
    }

    public Map<String, Integer> getProporcionStock() {
        Map<String, Integer> datos = new LinkedHashMap<>();
        int criticos = 0, saludables = 0;
        String sql = "SELECT stock_actual FROM LOTES";
        try (Connection con = conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                if (rs.getInt("stock_actual") <= 5) criticos++; else saludables++;
            }
            datos.put("Stock Cr\u00EDtico (<=5)", criticos); 
            datos.put("Stock Saludable (>5)", saludables); 
        } catch (Exception e) { System.out.println("Error proporcion stock: " + e.getMessage()); }
        return datos;
    }
}