package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VentaDAO {

    Conexion con = new Conexion();
    private final ConfiguracionDAO configuracionDAO = new ConfiguracionDAO();

    // ============================
    // CARGAR CLIENTES
    // ============================
    public ArrayList<String> listarClientesCombo() {

        ArrayList<String> lista = new ArrayList<>();

        String sql = """
            SELECT id_cliente, nombre
            FROM CLIENTES
            WHERE estado = 'ACTIVO'
        """;

        try (
            Connection conexion = con.conectar();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                lista.add(
                    rs.getInt("id_cliente")
                    + " - "
                    + rs.getString("nombre")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error clientes: " + e.getMessage());
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
    // GENERAR COMPROBANTE
    // ============================
    private String generarNumeroComprobante(Connection conexion) throws SQLException {

        String sql = """
            SELECT MAX(id_venta) AS ultimo_id
            FROM VENTAS
        """;

        try (
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next() && rs.getInt("ultimo_id") > 0) {
                return String.format("VENT-%03d", rs.getInt("ultimo_id") + 1);
            }
        }

        return "VENT-001";
    }

    // ============================
    // OBTENER LOTES DISPONIBLES
    // ============================
    public ArrayList<LoteDisponible> obtenerLotesDisponibles(int idProducto) {

        ArrayList<LoteDisponible> lista = new ArrayList<>();

        String sql = """
            SELECT
                id_lote,
                stock_actual,
                precio_venta
            FROM LOTES
            WHERE id_producto = ?
            AND stock_actual > 0
            ORDER BY precio_venta DESC, fecha_ingreso ASC
        """;

        try (
            Connection cn = con.conectar();
            PreparedStatement ps = cn.prepareStatement(sql)
        ) {

            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LoteDisponible lote = new LoteDisponible();
                lote.setIdLote(rs.getInt("id_lote"));
                lote.setStock(rs.getInt("stock_actual"));
                lote.setPrecioVenta(rs.getDouble("precio_venta"));
                lista.add(lote);
            }

        } catch (Exception e) {
            System.out.println("Error lotes: " + e.getMessage());
        }

        return lista;
    }

    // Mantengo este método por compatibilidad con código viejo.
    public boolean guardarVenta(Venta venta) {
        return guardarVenta(venta, 0.0);
    }

    // ============================
    // GUARDAR VENTA
    // El trigger VentaKardexTrigger se encarga
    // de descontar stock e insertar KARDEX
    // ============================
    public boolean guardarVenta(Venta venta, double porcentajeDescuento) {

        Connection conexion = con.conectar();

        String sqlVenta = """
            INSERT INTO VENTAS
            (
                tipo_comprobante,
                numero_comprobante,
                estado,
                id_cliente,
                id_usuario
            )
            VALUES (?, ?, ?, ?, ?)
        """;

        String sqlDetalle = """
            INSERT INTO DETALLE_VENTAS
            (
                cantidad,
                precio_unitario,
                porcentaje_descuento,
                porcentaje_iva,
                id_venta,
                id_producto,
                id_lote
            )
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try {

            conexion.setAutoCommit(false);

            PreparedStatement psVenta = conexion.prepareStatement(
                sqlVenta,
                Statement.RETURN_GENERATED_KEYS
            );

            psVenta.setString(1, venta.getTipoComprobante());
            psVenta.setString(2, generarNumeroComprobante(conexion));
            psVenta.setString(3, venta.getEstado());
            psVenta.setInt(4, venta.getIdCliente());
            psVenta.setInt(5, venta.getIdUsuario());

            psVenta.executeUpdate();

            ResultSet rsKeys = psVenta.getGeneratedKeys();
            int idVentaGenerada = 0;

            if (rsKeys.next()) {
                idVentaGenerada = rsKeys.getInt(1);
            } else {
                throw new SQLException("No se pudo obtener el ID de la venta.");
            }

            double ivaDecimal = obtenerIvaDecimal();
            double descuentoDecimal = porcentajeDescuento / 100.0;

            PreparedStatement psDetalle =
                conexion.prepareStatement(sqlDetalle);

            for (DetalleVenta d : venta.getDetalles()) {

                psDetalle.setInt(1, d.getCantidad());
                psDetalle.setDouble(2, d.getPrecioUnitario());
                psDetalle.setDouble(3, descuentoDecimal);
                psDetalle.setDouble(4, ivaDecimal);
                psDetalle.setInt(5, idVentaGenerada);
                psDetalle.setInt(6, d.getIdProducto());
                psDetalle.setInt(7, d.getIdLote());

                psDetalle.executeUpdate();
            }

            conexion.commit();
            return true;

        } catch (SQLException e) {

            System.out.println("Error Venta: " + e.getMessage());

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
            } catch (SQLException ex) {
                System.out.println("Error cerrando conexión: " + ex.getMessage());
            }
        }
    }
}