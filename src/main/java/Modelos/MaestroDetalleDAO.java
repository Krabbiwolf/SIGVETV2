package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 * DAO exclusivo para vistas Maestro-Detalle.
 *
 * IMPORTANTE:
 * - No modifica la base de datos.
 * - No inserta, no actualiza y no elimina.
 * - Solo hace SELECT para mostrar todos los maestros y luego filtrar el detalle
 *   según el registro seleccionado.
 */
public class MaestroDetalleDAO {

    private final Conexion conexion = new Conexion();
    private final Map<String, Boolean> cacheTablas = new HashMap<>();
    private final Map<String, Boolean> cacheColumnas = new HashMap<>();
    private String ultimoError = "";

    public String getUltimoError() {
        return ultimoError;
    }

    private void setUltimoError(String mensaje) {
        ultimoError = mensaje == null ? "" : mensaje;
    }

    // =========================
    // MAESTROS
    // =========================

    public DefaultTableModel listarClientes(String filtro) {
        setUltimoError("");

        String sql = "SELECT "
                + "id_cliente AS ID, "
                + "CONCAT(COALESCE(nombre, ''), ' ', COALESCE(apellido, '')) AS Cliente, "
                + "dui AS DUI, "
                + "telefono AS Telefono, "
                + "direccion AS Direccion, "
                + "estado AS Estado "
                + "FROM CLIENTES ";

        boolean usarFiltro = tieneTexto(filtro);
        if (usarFiltro) {
            sql += "WHERE CONCAT(COALESCE(nombre, ''), ' ', COALESCE(apellido, '')) LIKE ? "
                    + "OR dui LIKE ? "
                    + "OR telefono LIKE ? "
                    + "OR direccion LIKE ? "
                    + "OR estado LIKE ? ";
        }

        sql += "ORDER BY id_cliente DESC";

        return ejecutarConsulta(sql, usarFiltro ? parametrosFiltro(filtro, 5) : new Object[]{});
    }

    public DefaultTableModel listarProveedores(String filtro) {
        setUltimoError("");

        String sql = "SELECT "
                + "id_proveedor AS ID, "
                + "nombre_empresa AS Proveedor, "
                + "telefono AS Telefono, "
                + "estado AS Estado "
                + "FROM PROVEEDORES ";

        boolean usarFiltro = tieneTexto(filtro);
        if (usarFiltro) {
            sql += "WHERE nombre_empresa LIKE ? "
                    + "OR telefono LIKE ? "
                    + "OR estado LIKE ? ";
        }

        sql += "ORDER BY id_proveedor DESC";

        return ejecutarConsulta(sql, usarFiltro ? parametrosFiltro(filtro, 3) : new Object[]{});
    }

    public DefaultTableModel listarCategorias(String filtro) {
        setUltimoError("");

        String sql = "SELECT "
                + "id_categoria AS ID, "
                + "nombre AS Categoria, "
                + "descripcion AS Descripcion, "
                + "estado AS Estado "
                + "FROM CATEGORIAS ";

        boolean usarFiltro = tieneTexto(filtro);
        if (usarFiltro) {
            sql += "WHERE nombre LIKE ? "
                    + "OR descripcion LIKE ? "
                    + "OR estado LIKE ? ";
        }

        sql += "ORDER BY id_categoria DESC";

        return ejecutarConsulta(sql, usarFiltro ? parametrosFiltro(filtro, 3) : new Object[]{});
    }

    public DefaultTableModel listarProductos(String filtro) {
        setUltimoError("");

        try (Connection cn = conexion.conectar()) {
            if (cn == null) {
                return modeloVacio();
            }

            boolean puedeUnirCategoria = existeTabla(cn, "CATEGORIAS")
                    && existeColumna(cn, "PRODUCTOS", "id_categoria")
                    && existeColumna(cn, "CATEGORIAS", "id_categoria");

            String sql;
            if (puedeUnirCategoria) {
                sql = "SELECT "
                        + "p.id_producto AS ID, "
                        + "p.codigo_barras AS Codigo, "
                        + "p.nombre AS Producto, "
                        + "c.nombre AS Categoria, "
                        + "p.estado AS Estado "
                        + "FROM PRODUCTOS p "
                        + "LEFT JOIN CATEGORIAS c ON p.id_categoria = c.id_categoria ";
            } else {
                sql = "SELECT "
                        + "id_producto AS ID, "
                        + "codigo_barras AS Codigo, "
                        + "nombre AS Producto, "
                        + "estado AS Estado "
                        + "FROM PRODUCTOS ";
            }

            boolean usarFiltro = tieneTexto(filtro);
            Object[] params = new Object[]{};

            if (usarFiltro) {
                String prefijo = puedeUnirCategoria ? "p." : "";
                sql += "WHERE " + prefijo + "nombre LIKE ? "
                        + "OR " + prefijo + "codigo_barras LIKE ? "
                        + "OR " + prefijo + "estado LIKE ? ";
                params = parametrosFiltro(filtro, 3);
            }

            sql += puedeUnirCategoria ? "ORDER BY p.id_producto DESC" : "ORDER BY id_producto DESC";

            return consultar(cn, sql, params);

        } catch (SQLException e) {
            setUltimoError("Error al listar productos: " + e.getMessage());
            return modeloVacio();
        }
    }

    // =========================
    // DETALLES
    // =========================

    public DefaultTableModel listarFacturasVentasPorCliente(int idCliente) {
        setUltimoError("");

        try (Connection cn = conexion.conectar()) {
            if (cn == null) {
                return modeloVacio();
            }

            String tabla = primeraTablaExistente(cn, "VENTAS", "FACTURAS", "FACTURA", "VENTA");
            if (tabla == null || !existeColumna(cn, tabla, "id_cliente")) {
                return modeloVacio();
            }

            String idTabla = primeraColumnaExistente(cn, tabla, "id_venta", "id_factura", "id");
            String fecha = primeraColumnaExistente(cn, tabla, "fecha_hora", "fecha_emision", "fecha", "created_at");

            String orden = "";
            if (idTabla != null) {
                orden = " ORDER BY " + columna(idTabla) + " DESC";
            } else if (fecha != null) {
                orden = " ORDER BY " + columna(fecha) + " DESC";
            }

            String sql = "SELECT * FROM " + tabla + " WHERE id_cliente = ?" + orden;
            return consultar(cn, sql, idCliente);

        } catch (SQLException e) {
            setUltimoError("Error al listar facturas/ventas del cliente: " + e.getMessage());
            return modeloVacio();
        }
    }

    public DefaultTableModel listarComprasPorProveedor(int idProveedor) {
        setUltimoError("");

        try (Connection cn = conexion.conectar()) {
            if (cn == null) {
                return modeloVacio();
            }

            if (!existeTabla(cn, "COMPRAS") || !existeColumna(cn, "COMPRAS", "id_proveedor")) {
                return modeloVacio();
            }

            boolean puedeUnirDetalle = existeTabla(cn, "DETALLE_COMPRAS")
                    && existeColumna(cn, "DETALLE_COMPRAS", "id_compra")
                    && existeColumna(cn, "DETALLE_COMPRAS", "id_producto")
                    && existeTabla(cn, "PRODUCTOS")
                    && existeColumna(cn, "PRODUCTOS", "id_producto");

            if (puedeUnirDetalle) {
                String sql = "SELECT "
                        + "c.id_compra AS ID, "
                        + "c.numero_comprobante AS Comprobante, "
                        + "c.fecha_hora AS Fecha, "
                        + "c.estado AS Estado, "
                        + "p.nombre AS Producto, "
                        + "dc.cantidad_comprada AS Cantidad, "
                        + "dc.precio_compra AS PrecioCompra, "
                        + "dc.porcentaje_iva AS IVA, "
                        + "ROUND(dc.cantidad_comprada * dc.precio_compra, 2) AS Subtotal "
                        + "FROM COMPRAS c "
                        + "LEFT JOIN DETALLE_COMPRAS dc ON c.id_compra = dc.id_compra "
                        + "LEFT JOIN PRODUCTOS p ON dc.id_producto = p.id_producto "
                        + "WHERE c.id_proveedor = ? "
                        + "ORDER BY c.id_compra DESC";
                return consultar(cn, sql, idProveedor);
            }

            String sql = "SELECT * FROM COMPRAS WHERE id_proveedor = ? ORDER BY id_compra DESC";
            return consultar(cn, sql, idProveedor);

        } catch (SQLException e) {
            setUltimoError("Error al listar compras del proveedor: " + e.getMessage());
            return modeloVacio();
        }
    }

    public DefaultTableModel listarProductosPorCategoria(int idCategoria) {
        setUltimoError("");

        String sql = "SELECT "
                + "id_producto AS ID, "
                + "codigo_barras AS Codigo, "
                + "nombre AS Producto, "
                + "descripcion_tecnica AS Descripcion, "
                + "porcentaje_iva_default AS IVA, "
                + "estado AS Estado "
                + "FROM PRODUCTOS "
                + "WHERE id_categoria = ? "
                + "ORDER BY id_producto DESC";

        return ejecutarConsulta(sql, idCategoria);
    }

    public DefaultTableModel listarLotesPorProducto(int idProducto) {
        setUltimoError("");

        try (Connection cn = conexion.conectar()) {
            if (cn == null) {
                return modeloVacio();
            }

            if (!existeTabla(cn, "LOTES") || !existeColumna(cn, "LOTES", "id_producto")) {
                return modeloVacio();
            }

            String stockActual = primeraColumnaExistente(cn, "LOTES", "stock_actual", "stockActual", "stock", "cantidad_disponible", "cantidad");
            String stockInicial = primeraColumnaExistente(cn, "LOTES", "stock_inicial", "stockInicial");
            String fechaVencimiento = primeraColumnaExistente(cn, "LOTES", "fecha_vencimiento", "fechaVencimiento", "vencimiento");

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id_lote AS ID, numero_lote AS Lote");

            if (stockInicial != null) {
                sql.append(", COALESCE(").append(columna(stockInicial)).append(", 0) AS StockInicial");
            }

            if (stockActual != null) {
                sql.append(", COALESCE(").append(columna(stockActual)).append(", 0) AS StockActual");
            }

            if (fechaVencimiento != null) {
                sql.append(", ").append(columna(fechaVencimiento)).append(" AS Vencimiento");
            }

            if (existeColumna(cn, "LOTES", "precio_compra")) {
                sql.append(", precio_compra AS PrecioCompra");
            }

            if (existeColumna(cn, "LOTES", "precio_venta")) {
                sql.append(", precio_venta AS PrecioVenta");
            }

            if (existeColumna(cn, "LOTES", "estado")) {
                sql.append(", estado AS Estado");
            }

            sql.append(" FROM LOTES WHERE id_producto = ? ORDER BY id_lote DESC");

            return consultar(cn, sql.toString(), idProducto);

        } catch (SQLException e) {
            setUltimoError("Error al listar lotes del producto: " + e.getMessage());
            return modeloVacio();
        }
    }

    // =========================
    // CONSULTAS BASE
    // =========================

    private DefaultTableModel ejecutarConsulta(String sql, Object... params) {
        try (Connection cn = conexion.conectar()) {
            if (cn == null) {
                return modeloVacio();
            }
            return consultar(cn, sql, params);
        } catch (SQLException e) {
            setUltimoError("Error en consulta maestro-detalle: " + e.getMessage());
            return modeloVacio();
        }
    }

    private DefaultTableModel consultar(Connection cn, String sql, Object... params) throws SQLException {
        DefaultTableModel modelo = modeloVacio();

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setFetchSize(100);

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData meta = rs.getMetaData();
                int columnas = meta.getColumnCount();

                for (int i = 1; i <= columnas; i++) {
                    modelo.addColumn(meta.getColumnLabel(i));
                }

                while (rs.next()) {
                    Object[] fila = new Object[columnas];
                    for (int i = 1; i <= columnas; i++) {
                        fila[i - 1] = rs.getObject(i);
                    }
                    modelo.addRow(fila);
                }
            }
        }

        return modelo;
    }

    private DefaultTableModel modeloVacio() {
        return new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private boolean tieneTexto(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    private Object[] parametrosFiltro(String filtro, int cantidad) {
        Object[] params = new Object[cantidad];
        String valor = "%" + filtro.trim() + "%";
        for (int i = 0; i < cantidad; i++) {
            params[i] = valor;
        }
        return params;
    }

    private boolean existeTabla(Connection cn, String tabla) throws SQLException {
        String key = tabla.toUpperCase();
        if (cacheTablas.containsKey(key)) {
            return cacheTablas.get(key);
        }

        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES "
                + "WHERE TABLE_SCHEMA = DATABASE() AND UPPER(TABLE_NAME) = UPPER(?)";

        boolean existe = false;
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, tabla);
            try (ResultSet rs = ps.executeQuery()) {
                existe = rs.next() && rs.getInt(1) > 0;
            }
        }

        cacheTablas.put(key, existe);
        return existe;
    }

    private boolean existeColumna(Connection cn, String tabla, String columna) throws SQLException {
        String key = tabla.toUpperCase() + "." + columna.toUpperCase();
        if (cacheColumnas.containsKey(key)) {
            return cacheColumnas.get(key);
        }

        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS "
                + "WHERE TABLE_SCHEMA = DATABASE() AND UPPER(TABLE_NAME) = UPPER(?) "
                + "AND UPPER(COLUMN_NAME) = UPPER(?)";

        boolean existe = false;
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, tabla);
            ps.setString(2, columna);
            try (ResultSet rs = ps.executeQuery()) {
                existe = rs.next() && rs.getInt(1) > 0;
            }
        }

        cacheColumnas.put(key, existe);
        return existe;
    }

    private String primeraTablaExistente(Connection cn, String... tablas) throws SQLException {
        for (String tabla : tablas) {
            if (existeTabla(cn, tabla)) {
                return tabla;
            }
        }
        return null;
    }

    private String primeraColumnaExistente(Connection cn, String tabla, String... columnas) throws SQLException {
        for (String col : columnas) {
            if (existeColumna(cn, tabla, col)) {
                return col;
            }
        }
        return null;
    }

    private String columna(String nombreColumna) {
        return "`" + nombreColumna.replace("`", "") + "`";
    }
}
