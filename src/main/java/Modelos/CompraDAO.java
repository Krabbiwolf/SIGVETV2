package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompraDAO {

    Conexion con = new Conexion();

    // ============================
    // CARGAR PROVEEDORES
    // ============================
    public ArrayList<String> listarProveedoresCombo() {

        ArrayList<String> lista = new ArrayList<>();

        String sql = """
            SELECT id_proveedor, nombre_empresa
            FROM PROVEEDORES
            WHERE estado = 'ACTIVO'
        """;

        try (
            Connection conexion = con.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(
                    rs.getInt("id_proveedor")
                    + " - "
                    + rs.getString("nombre_empresa")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error proveedores: " + e.getMessage());
        }

        return lista;
    }

    // ============================
    // CARGAR PRODUCTOS
    // ============================
    public ArrayList<String> listarProductosCombo() {

        ArrayList<String> lista = new ArrayList<>();

        String sql = """
            SELECT id_producto, nombre
            FROM PRODUCTOS
            WHERE estado = 'Activo'
        """;

        try (
            Connection conexion = con.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(
                    rs.getInt("id_producto")
                    + " - "
                    + rs.getString("nombre")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error productos: " + e.getMessage());
        }

        return lista;
    }

    // ============================
    // GENERAR NÚMERO COMPROBANTE
    // ============================
    private String generarNumeroComprobante(Connection conexion) throws SQLException {

        String sql = """
            SELECT MAX(id_compra) AS ultimo_id
            FROM COMPRAS
        """;

        try (
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next() && rs.getInt("ultimo_id") > 0) {
                return String.format("COMB-%03d", rs.getInt("ultimo_id") + 1);
            }
        }

        return "COMB-001";
    }

    // ============================
    // REGISTRAR COMPRA
    // El trigger LoteKardexTrigger se encarga
    // de crear el LOTE y el KARDEX automáticamente
    // ============================
    public boolean registrarCompra(
            Compra compra,
            int idProducto,
            int cantidad,
            double precioCompra
    ) {

        String sqlCompra = """
            INSERT INTO COMPRAS
            (
                numero_comprobante,
                estado,
                id_proveedor,
                id_usuario
            )
            VALUES (?, 'COMPLETADA', ?, ?)
        """;

        String sqlDetalle = """
            INSERT INTO DETALLE_COMPRAS
            (
                cantidad_comprada,
                precio_compra,
                porcentaje_iva,
                id_compra,
                id_producto
            )
            VALUES (?, ?, 0.13, ?, ?)
        """;

        Connection conexion = con.conectar();

        try {

            conexion.setAutoCommit(false);

            // ============================
            // INSERT COMPRAS
            // ============================
            PreparedStatement psCompra = conexion.prepareStatement(
                sqlCompra,
                PreparedStatement.RETURN_GENERATED_KEYS
            );

            psCompra.setString(1, generarNumeroComprobante(conexion));
            psCompra.setInt(2, compra.getIdProveedor());
            psCompra.setInt(3, compra.getIdUsuario());

            psCompra.executeUpdate();

            // OBTENER ID GENERADO
            ResultSet rsKeys = psCompra.getGeneratedKeys();
            int idCompraGenerada = 0;

            if (rsKeys.next()) {
                idCompraGenerada = rsKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de la compra.");
            }

            // ============================
            // INSERT DETALLE
            // (el trigger actúa aquí y crea
            // el lote + kardex automáticamente)
            // ============================
            PreparedStatement psDetalle = conexion.prepareStatement(sqlDetalle);

            psDetalle.setInt(1, cantidad);
            psDetalle.setDouble(2, precioCompra);
            psDetalle.setInt(3, idCompraGenerada);
            psDetalle.setInt(4, idProducto);

            psDetalle.executeUpdate();

            conexion.commit();
            return true;

        } catch (SQLException e) {

            System.out.println("Error al registrar compra: " + e.getMessage());

            try {
                conexion.rollback();
            } catch (SQLException ex) {
                System.out.println("Error rollback: " + ex.getMessage());
            }

            return false;

        } finally {

            try {
                conexion.setAutoCommit(true);
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error cerrando conexión: " + e.getMessage());
            }
        }
    }
}