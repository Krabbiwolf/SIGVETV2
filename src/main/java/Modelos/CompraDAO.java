package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompraDAO {

    Conexion con = new Conexion();
    private final ConfiguracionDAO configuracionDAO = new ConfiguracionDAO();

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
    // OBTENER IVA CONFIGURADO
    // ============================
    private double obtenerIvaDecimal() {
        double ivaPorcentaje = configuracionDAO.obtenerValor("iva_predeterminado", 13.00);
        return ivaPorcentaje / 100.0;
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
    // REGISTRAR COMPRA (CORREGIDO)
    // Inserta una sola COMPRA y múltiples DETALLES_COMPRA
    // ============================
    public boolean registrarCompra(Compra compra, java.util.List<Object[]> detalles) {

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
            VALUES (?, ?, ?, ?, ?)
        """;

        Connection conexion = con.conectar();

        try {
            conexion.setAutoCommit(false);

            // 1. Insertar la Cabecera (COMPRAS) UNA SOLA VEZ
            PreparedStatement psCompra = conexion.prepareStatement(
                sqlCompra,
                PreparedStatement.RETURN_GENERATED_KEYS
            );

            psCompra.setString(1, generarNumeroComprobante(conexion));
            psCompra.setInt(2, compra.getIdProveedor());
            psCompra.setInt(3, compra.getIdUsuario());

            psCompra.executeUpdate();

            ResultSet rsKeys = psCompra.getGeneratedKeys();
            int idCompraGenerada = 0;

            if (rsKeys.next()) {
                idCompraGenerada = rsKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de la compra.");
            }

            double ivaDecimal = obtenerIvaDecimal();

            // 2. Insertar los múltiples detalles
            PreparedStatement psDetalle = conexion.prepareStatement(sqlDetalle);

            for (Object[] detalle : detalles) {
                int idProducto = (int) detalle[0];
                int cantidad = (int) detalle[1];
                double precioCompra = (double) detalle[2];

                psDetalle.setInt(1, cantidad);
                psDetalle.setDouble(2, precioCompra);
                psDetalle.setDouble(3, ivaDecimal);
                psDetalle.setInt(4, idCompraGenerada);
                psDetalle.setInt(5, idProducto);
                
                // Agregar al batch para ejecutar todo junto
                psDetalle.addBatch();
            }

            // Ejecutar todos los detalles de golpe
            psDetalle.executeBatch();

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