package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AjusteInventarioDAO {

    private final Conexion cn = new Conexion();
    private String ultimoError = "";

    public String getUltimoError() {
        return ultimoError;
    }

    private void setUltimoError(String mensaje) {
        ultimoError = mensaje == null ? "" : mensaje;
    }

    // Método optimizado: limitar a 200 lotes para evitar lentitud extrema
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
                    + "ORDER BY p.nombre, l.numero_lote "
                    + "LIMIT 200";  // ← Límite para evitar lentitud

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
                setUltimoError("No hay lotes activos para mostrar.");
            }

        } catch (SQLException e) {
            setUltimoError("Error al listar lotes activos: " + e.getMessage());
            System.err.println(getUltimoError());
            e.printStackTrace();
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
            boolean esEntrada = tipoAjuste.toUpperCase().contains("INGRESO") || tipoAjuste.toUpperCase().contains("ENTRADA");
            boolean esSalida = !esEntrada;
            if (cantidad <= 0) {
                con.rollback();
                return new ResultadoAjuste(false, "La cantidad debe ser mayor que cero.");
            }
            int stockAnterior = lote.getStockActual();
            if (esSalida && cantidad > stockAnterior) {
                con.rollback();
                return new ResultadoAjuste(false, "Stock insuficiente: " + stockAnterior);
            }
            int nuevoStock = esEntrada ? stockAnterior + cantidad : stockAnterior - cantidad;
            actualizarStockActualLote(con, idLote, nuevoStock, columnas.stockActual);
            String tipoMovimiento = esEntrada ? "AJUSTE ENTRADA" : "AJUSTE SALIDA";
            insertarKardex(con, tipoMovimiento, cantidad, motivoAjuste, idLote, idUsuario);
            String signo = esEntrada ? "+" : "-";
            String usuarioBitacora = (nombreUsuario == null || nombreUsuario.trim().isEmpty()) ? "ID " + idUsuario : nombreUsuario.trim();
            String accionBitacora = "Ajuste " + signo + cantidad + " unidades del lote " + lote.getNumeroLote()
                    + " de " + lote.getNombreProducto() + ". Stock anterior: " + stockAnterior + ", nuevo: " + nuevoStock
                    + ". Motivo: " + motivoAjuste;
            insertarBitacora(con, idUsuario, accionBitacora);
            con.commit();
            return new ResultadoAjuste(true, "Ajuste exitoso. Stock anterior: " + stockAnterior + ", nuevo: " + nuevoStock);
        } catch (SQLException e) {
            if (con != null) try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            System.err.println("Error al registrar ajuste: " + e.getMessage());
            return new ResultadoAjuste(false, "Error en BD: " + e.getMessage());
        } finally {
            if (con != null) try { con.setAutoCommit(true); con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public ArrayList<Object[]> listarAjustesInventario() {
        ArrayList<Object[]> lista = new ArrayList<>();
        try (Connection con = cn.conectar()) {
            if (con == null) return lista;
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
                    + "LIMIT 100";
            try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
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
            System.err.println("Error al listar ajustes: " + e.getMessage());
        }
        return lista;
    }

    // Métodos privados auxiliares (igual que antes)
    private LoteInventario obtenerLotePorId(Connection con, int idLote, boolean bloquearFila, ColumnasLotes columnas) throws SQLException {
        String sql = "SELECT l.id_lote, p.nombre AS producto, l.numero_lote, "
                + columnaStockInicialSql(columnas) + " AS stock_inicial, "
                + "COALESCE(" + columnaSql("l", columnas.stockActual) + ", 0) AS stock_actual, "
                + "COALESCE(l.precio_compra, 0) AS precio_compra, "
                + "COALESCE(l.precio_venta, 0) AS precio_venta "
                + "FROM LOTES l INNER JOIN PRODUCTOS p ON p.id_producto = l.id_producto WHERE l.id_lote = ?"
                + (bloquearFila ? " FOR UPDATE" : "");
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idLote);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new LoteInventario(rs.getInt("id_lote"), rs.getString("producto"), rs.getString("numero_lote"),
                            rs.getInt("stock_inicial"), rs.getInt("stock_actual"), rs.getDouble("precio_compra"), rs.getDouble("precio_venta"));
                }
            }
        }
        return null;
    }

    private void actualizarStockActualLote(Connection con, int idLote, int nuevoStock, String columnaStockActual) throws SQLException {
        String sql = "UPDATE LOTES SET " + columnaSql(null, columnaStockActual) + " = ? WHERE id_lote = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nuevoStock);
            ps.setInt(2, idLote);
            if (ps.executeUpdate() == 0) throw new SQLException("No se actualizó el stock.");
        }
    }

    private void insertarKardex(Connection con, String tipoMovimiento, int cantidad, String motivo, int idLote, int idUsuario) throws SQLException {
        String sql = "INSERT INTO KARDEX (tipo_movimiento, cantidad, fecha_hora, precio_compra, precio_venta, motivo_ajuste, id_lote, id_usuario) SELECT ?, ?, NOW(), precio_compra, precio_venta, ?, id_lote, ? FROM LOTES WHERE id_lote = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tipoMovimiento);
            ps.setInt(2, cantidad);
            ps.setString(3, motivo);
            ps.setInt(4, idUsuario);
            ps.setInt(5, idLote);
            if (ps.executeUpdate() == 0) throw new SQLException("No se insertó en Kardex.");
        }
    }

    private void insertarBitacora(Connection con, int idUsuario, String detalle) throws SQLException {
        String sql = "INSERT INTO BITACORA_EVENTOS (accion, id_usuario) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, detalle.length() > 255 ? detalle.substring(0, 255) : detalle);
            ps.setInt(2, idUsuario);
            ps.executeUpdate();
        }
    }

    private ColumnasLotes resolverColumnasLotes(Connection con) throws SQLException {
        List<String> columnas = new ArrayList<>();
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery("SHOW COLUMNS FROM LOTES")) {
            while (rs.next()) columnas.add(rs.getString("Field"));
        }
        String stockActual = buscarColumnaFlexible(columnas, "stock_actual", "stockactual", "stock actual", "stockActual");
        String stockInicial = buscarColumnaFlexible(columnas, "stock_inicial", "stockinicial", "stock inicial", "stockInicial");
        if (stockActual == null) throw new SQLException("No se encontró columna stock_actual en LOTES");
        return new ColumnasLotes(stockInicial, stockActual);
    }

    private String buscarColumnaFlexible(List<String> columnas, String... nombres) {
        for (String nombre : nombres) {
            for (String col : columnas) {
                if (col.equalsIgnoreCase(nombre)) return col;
            }
        }
        return null;
    }

    private String columnaStockInicialSql(ColumnasLotes columnas) {
        return columnas.stockInicial == null ? "0" : "COALESCE(" + columnaSql("l", columnas.stockInicial) + ", 0)";
    }

    private String columnaSql(String alias, String columna) {
        return (alias == null ? "" : alias + ".") + "`" + columna + "`";
    }

    private static class ColumnasLotes {
        final String stockInicial, stockActual;
        ColumnasLotes(String si, String sa) { stockInicial = si; stockActual = sa; }
    }

    public static class ResultadoAjuste {
        private final boolean correcto;
        private final String mensaje;
        public ResultadoAjuste(boolean correcto, String mensaje) { this.correcto = correcto; this.mensaje = mensaje; }
        public boolean isCorrecto() { return correcto; }
        public String getMensaje() { return mensaje; }
    }
}