package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO exclusivo para Ajuste de Inventario.
 *
 * Esta versión trabaja directamente con LOTES.stock_actual.
 * No calcula stock desde KARDEX, porque ahora el stock oficial vive en LOTES.
 */
public class AjusteInventarioDAO {

    private final Conexion cn = new Conexion();
    private String ultimoError = "";

    public String getUltimoError() {
        return ultimoError;
    }

    private void setUltimoError(String mensaje) {
        ultimoError = mensaje == null ? "" : mensaje;
    }

    public ArrayList<LoteInventario> listarLotesActivos() {
        ArrayList<LoteInventario> lista = new ArrayList<>();
        setUltimoError("");

        try (Connection con = cn.conectar()) {
            if (con == null) {
                setUltimoError("No se pudo conectar a la base de datos.");
                return lista;
            }

            ColumnasLotes columnas = resolverColumnasLotes(con);

            String sql = "SELECT l.id_lote, p.nombre AS producto, l.numero_lote, "
                    + columnaStockInicialSql(columnas) + " AS stock_inicial, "
                    + "COALESCE(" + columnaSql("l", columnas.stockActual) + ", 0) AS stock_actual, "
                    + "COALESCE(l.precio_compra, 0) AS precio_compra, "
                    + "COALESCE(l.precio_venta, 0) AS precio_venta "
                    + "FROM LOTES l "
                    + "INNER JOIN PRODUCTOS p ON p.id_producto = l.id_producto "
                    + "WHERE UPPER(TRIM(l.estado)) = 'ACTIVO' AND UPPER(TRIM(p.estado)) = 'ACTIVO' "
                    + "ORDER BY p.nombre, l.numero_lote";

            System.out.println("Ajuste Inventario - columna stock_actual usada: " + columnas.stockActual);

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    lista.add(new LoteInventario(
                            rs.getInt("id_lote"),
                            rs.getString("producto"),
                            rs.getString("numero_lote"),
                            rs.getInt("stock_inicial"),
                            rs.getInt("stock_actual"),
                            rs.getDouble("precio_compra"),
                            rs.getDouble("precio_venta")
                    ));
                }
            }

            if (lista.isEmpty()) {
                setUltimoError("La consulta funcionó, pero no hay lotes activos para mostrar.");
            }

        } catch (SQLException e) {
            setUltimoError("Error al listar lotes activos: " + e.getMessage());
            System.out.println(getUltimoError());
        }

        return lista;
    }

    public LoteInventario obtenerLotePorId(int idLote) {
        setUltimoError("");

        try (Connection con = cn.conectar()) {
            if (con == null) {
                setUltimoError("No se pudo conectar a la base de datos.");
                return null;
            }

            ColumnasLotes columnas = resolverColumnasLotes(con);
            return obtenerLotePorId(con, idLote, false, columnas);

        } catch (SQLException e) {
            setUltimoError("Error al obtener lote: " + e.getMessage());
            System.out.println(getUltimoError());
            return null;
        }
    }

    public ResultadoAjuste registrarAjusteInventario(int idLote, String tipoAjuste,
                                                     int cantidad, String motivoAjuste,
                                                     int idUsuario, String nombreUsuario) {
        Connection con = null;

        try {
            con = cn.conectar();

            if (con == null) {
                return new ResultadoAjuste(false, "No se pudo conectar a la base de datos.");
            }

            con.setAutoCommit(false);

            ColumnasLotes columnas = resolverColumnasLotes(con);
            LoteInventario lote = obtenerLotePorId(con, idLote, true, columnas);

            if (lote == null) {
                con.rollback();
                return new ResultadoAjuste(false, "No se encontró el lote seleccionado.");
            }

            boolean esEntrada = esTipoEntrada(tipoAjuste);
            boolean esSalida = esTipoSalida(tipoAjuste);

            if (!esEntrada && !esSalida) {
                con.rollback();
                return new ResultadoAjuste(false, "Tipo de ajuste no válido: " + tipoAjuste);
            }

            if (cantidad <= 0) {
                con.rollback();
                return new ResultadoAjuste(false, "La cantidad debe ser mayor que cero.");
            }

            int stockAnterior = lote.getStockActual();

            if (esSalida && cantidad > stockAnterior) {
                con.rollback();
                return new ResultadoAjuste(false, "No puede restar " + cantidad
                        + " unidades porque el lote solo tiene " + stockAnterior + ".");
            }

            int nuevoStock = esEntrada ? stockAnterior + cantidad : stockAnterior - cantidad;

            actualizarStockActualLote(con, idLote, nuevoStock, columnas.stockActual);

            String tipoMovimiento = esEntrada ? "AJUSTE ENTRADA" : "AJUSTE SALIDA";
            insertarKardex(con, tipoMovimiento, cantidad, motivoAjuste, idLote, idUsuario);

            String signo = esEntrada ? "+" : "-";
            String usuarioBitacora = nombreUsuario == null || nombreUsuario.trim().isEmpty()
                    ? "ID " + idUsuario
                    : nombreUsuario.trim();

            String accionBitacora = "El usuario " + usuarioBitacora
                    + " ajustó " + signo + cantidad
                    + " unidades del lote " + lote.getNumeroLote()
                    + " del producto " + lote.getNombreProducto()
                    + ". Stock anterior: " + stockAnterior
                    + ", stock nuevo: " + nuevoStock
                    + ". Motivo: " + motivoAjuste;

            insertarBitacora(con, idUsuario, accionBitacora);

            con.commit();

            return new ResultadoAjuste(true,
                    "Ajuste registrado correctamente. Stock anterior: " + stockAnterior
                    + ". Nuevo stock: " + nuevoStock + ".");

        } catch (SQLException e) {
            hacerRollback(con);
            System.out.println("Error al registrar ajuste de inventario: " + e.getMessage());
            return new ResultadoAjuste(false, "Error al registrar ajuste: " + e.getMessage());
        } finally {
            cerrarConexion(con);
        }
    }

    public ArrayList<Object[]> listarAjustesInventario() {
        ArrayList<Object[]> lista = new ArrayList<>();
        setUltimoError("");

        try (Connection con = cn.conectar()) {
            if (con == null) {
                setUltimoError("No se pudo conectar a la base de datos.");
                return lista;
            }

            ColumnasLotes columnas = resolverColumnasLotes(con);

            String sql = "SELECT k.id_movimiento, k.tipo_movimiento, k.cantidad, k.fecha_hora, "
                    + "k.precio_compra, k.precio_venta, k.motivo_ajuste, "
                    + "p.nombre AS producto, l.numero_lote, "
                    + columnaStockInicialSql(columnas) + " AS stock_inicial, "
                    + "COALESCE(" + columnaSql("l", columnas.stockActual) + ", 0) AS stock_actual, "
                    + "k.id_usuario "
                    + "FROM KARDEX k "
                    + "INNER JOIN LOTES l ON l.id_lote = k.id_lote "
                    + "INNER JOIN PRODUCTOS p ON p.id_producto = l.id_producto "
                    + "WHERE UPPER(REPLACE(k.tipo_movimiento, ' ', '_')) IN ('AJUSTE_ENTRADA', 'AJUSTE_SALIDA') "
                    + "ORDER BY k.id_movimiento DESC "
                    + "LIMIT 50";

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Object[] fila = new Object[]{
                        rs.getInt("id_movimiento"),
                        rs.getString("tipo_movimiento"),
                        rs.getInt("cantidad"),
                        rs.getString("fecha_hora"),
                        rs.getString("producto"),
                        rs.getString("numero_lote"),
                        rs.getInt("stock_inicial"),
                        rs.getInt("stock_actual"),
                        rs.getDouble("precio_compra"),
                        rs.getDouble("precio_venta"),
                        rs.getString("motivo_ajuste"),
                        rs.getInt("id_usuario")
                    };

                    lista.add(fila);
                }
            }

        } catch (SQLException e) {
            setUltimoError("Error al listar ajustes de inventario: " + e.getMessage());
            System.out.println(getUltimoError());
        }

        return lista;
    }

    private LoteInventario obtenerLotePorId(Connection con, int idLote,
                                            boolean bloquearFila,
                                            ColumnasLotes columnas) throws SQLException {
        String sql = "SELECT l.id_lote, p.nombre AS producto, l.numero_lote, "
                + columnaStockInicialSql(columnas) + " AS stock_inicial, "
                + "COALESCE(" + columnaSql("l", columnas.stockActual) + ", 0) AS stock_actual, "
                + "COALESCE(l.precio_compra, 0) AS precio_compra, "
                + "COALESCE(l.precio_venta, 0) AS precio_venta "
                + "FROM LOTES l "
                + "INNER JOIN PRODUCTOS p ON p.id_producto = l.id_producto "
                + "WHERE l.id_lote = ?";

        if (bloquearFila) {
            sql += " FOR UPDATE";
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idLote);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new LoteInventario(
                            rs.getInt("id_lote"),
                            rs.getString("producto"),
                            rs.getString("numero_lote"),
                            rs.getInt("stock_inicial"),
                            rs.getInt("stock_actual"),
                            rs.getDouble("precio_compra"),
                            rs.getDouble("precio_venta")
                    );
                }
            }
        }

        return null;
    }

    private void actualizarStockActualLote(Connection con, int idLote,
                                           int nuevoStock,
                                           String columnaStockActual) throws SQLException {
        String sql = "UPDATE LOTES SET " + columnaSql(null, columnaStockActual) + " = ? WHERE id_lote = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nuevoStock);
            ps.setInt(2, idLote);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo actualizar el stock actual del lote.");
            }
        }
    }

    private void insertarKardex(Connection con, String tipoMovimiento, int cantidad,
                                String motivoAjuste, int idLote, int idUsuario) throws SQLException {
        String sql = "INSERT INTO KARDEX "
                + "(tipo_movimiento, cantidad, fecha_hora, precio_compra, precio_venta, "
                + "motivo_ajuste, id_lote, id_usuario, id_venta, id_compra) "
                + "SELECT ?, ?, NOW(), precio_compra, precio_venta, ?, id_lote, ?, NULL, NULL "
                + "FROM LOTES "
                + "WHERE id_lote = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tipoMovimiento);
            ps.setInt(2, cantidad);
            ps.setString(3, motivoAjuste);
            ps.setInt(4, idUsuario);
            ps.setInt(5, idLote);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo registrar el movimiento en Kardex.");
            }
        }
    }

    private void insertarBitacora(Connection con, int idUsuario, String accionDetalle) throws SQLException {
        String sql = "INSERT INTO BITACORA_EVENTOS (accion, id_usuario) VALUES (?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, limitarTexto(accionDetalle, 255));
            ps.setInt(2, idUsuario);

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("No se pudo registrar la bitácora del ajuste.");
            }
        }
    }

    private ColumnasLotes resolverColumnasLotes(Connection con) throws SQLException {
        List<String> columnas = obtenerColumnasTabla(con, "LOTES");

        String stockActual = buscarColumnaFlexible(columnas,
                "stock_actual", "stockactual", "stock actual", "stockActual", "Stock_Actual");

        String stockInicial = buscarColumnaFlexible(columnas,
                "stock_inicial", "stockinicial", "stock inicial", "stockInicial", "Stock_Inicial");

        if (stockActual == null) {
            throw new SQLException("No se encontró la columna stock_actual en LOTES. "
                    + "Columnas encontradas: " + String.join(", ", columnas));
        }

        return new ColumnasLotes(stockInicial, stockActual);
    }

    private List<String> obtenerColumnasTabla(Connection con, String tabla) throws SQLException {
        List<String> columnas = new ArrayList<>();

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SHOW COLUMNS FROM " + tabla)) {

            while (rs.next()) {
                columnas.add(rs.getString("Field"));
            }
        }

        return columnas;
    }

    private String buscarColumnaFlexible(List<String> columnas, String... nombresPosibles) {
        for (String nombrePosible : nombresPosibles) {
            String normalizadoPosible = normalizarNombre(nombrePosible);

            for (String columna : columnas) {
                if (normalizarNombre(columna).equals(normalizadoPosible)) {
                    return columna;
                }
            }
        }

        return null;
    }

    private String normalizarNombre(String texto) {
        if (texto == null) {
            return "";
        }

        return texto.toLowerCase()
                .replace("_", "")
                .replace(" ", "")
                .replace("-", "");
    }

    private String columnaStockInicialSql(ColumnasLotes columnas) {
        if (columnas.stockInicial == null) {
            return "0";
        }

        return "COALESCE(" + columnaSql("l", columnas.stockInicial) + ", 0)";
    }

    private String columnaSql(String alias, String columna) {
        String nombreSeguro = columna.replace("`", "``");

        if (alias == null || alias.trim().isEmpty()) {
            return "`" + nombreSeguro + "`";
        }

        return alias + ".`" + nombreSeguro + "`";
    }

    private boolean esTipoEntrada(String tipoAjuste) {
        if (tipoAjuste == null) {
            return false;
        }

        String tipo = tipoAjuste.trim().toUpperCase();
        return tipo.contains("INGRESO") || tipo.contains("ENTRADA") || tipo.contains("SUMA");
    }

    private boolean esTipoSalida(String tipoAjuste) {
        if (tipoAjuste == null) {
            return false;
        }

        String tipo = tipoAjuste.trim().toUpperCase();
        return tipo.contains("SALIDA") || tipo.contains("RESTA");
    }

    private String limitarTexto(String texto, int maximo) {
        if (texto == null) {
            return "";
        }

        if (texto.length() <= maximo) {
            return texto;
        }

        return texto.substring(0, maximo);
    }

    private void hacerRollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                System.out.println("Error al hacer rollback: " + e.getMessage());
            }
        }
    }

    private void cerrarConexion(Connection con) {
        if (con != null) {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexión: " + e.getMessage());
            }
        }
    }

    private static class ColumnasLotes {
        private final String stockInicial;
        private final String stockActual;

        public ColumnasLotes(String stockInicial, String stockActual) {
            this.stockInicial = stockInicial;
            this.stockActual = stockActual;
        }
    }

    public static class ResultadoAjuste {
        private final boolean correcto;
        private final String mensaje;

        public ResultadoAjuste(boolean correcto, String mensaje) {
            this.correcto = correcto;
            this.mensaje = mensaje;
        }

        public boolean isCorrecto() {
            return correcto;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}
