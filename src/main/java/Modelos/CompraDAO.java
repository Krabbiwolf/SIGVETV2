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

/**
 *
 * @author axele
 */
public class CompraDAO {
    Conexion con = new Conexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarCompra(Compra compra, int idProducto, int cantidad, double precio) {
        String sqlCompra = "INSERT INTO COMPRAS (numero_comprobante, id_proveedor, id_usuario) VALUES (?, ?, ?)";

        String sqlDetalle = "INSERT INTO DETALLE_COMPRAS (cantidad_comprada, precio_compra, porcentaje_iva, id_compra, id_producto) VALUES (?, ?, ?, ?, ?)";

        Connection conexion = con.conectar();

        try {
            conexion.setAutoCommit(false); // 🔥 IMPORTANTE

            // ===== INSERT COMPRAS =====
            ps = conexion.prepareStatement(sqlCompra, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, generarNumeroComprobante(conexion)); // lo generamos abajo
            ps.setInt(2, compra.getIdProveedor());
            ps.setInt(3, compra.getIdUsuario());

            ps.executeUpdate();

            // Obtener ID generado
            ResultSet rs = ps.getGeneratedKeys();
            int idCompra = 0;

            if (rs.next()) {
                idCompra = rs.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de la compra.");
            }

            // ===== INSERT DETALLE =====
            ps = conexion.prepareStatement(sqlDetalle);
            ps.setInt(1, cantidad);
            ps.setDouble(2, precio);
            ps.setDouble(3, 0.13); // IVA fijo
            ps.setInt(4, idCompra);
            ps.setInt(5, idProducto);

            ps.executeUpdate();

            conexion.commit(); // ✅ TODO OK
            return true;

        } catch (SQLException e) {
            try {
                conexion.rollback(); // ❌ ERROR → revertir todo
            } catch (SQLException ex) {
                System.out.println("Error en rollback: " + ex.getMessage());
            }

            System.out.println("Error al registrar compra: " + e.getMessage());
            return false;

        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private String generarNumeroComprobante(Connection conexion) throws SQLException {
        String sql = "SELECT COUNT(*) FROM COMPRAS";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        int numero = 1;

        if (rs.next()) {
            numero = rs.getInt(1) + 1;
        }

        return String.format("COMB-%03d", numero);
    }
}
