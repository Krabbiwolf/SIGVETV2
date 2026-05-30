package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class MaestroDetalleDAO {

    private final Conexion conexion = new Conexion();
    private String ultimoError = "";

    public String getUltimoError() {
        return ultimoError;
    }

    private void setUltimoError(String mensaje) {
        ultimoError = mensaje == null ? "" : mensaje;
    }

    // =========================================================
    // MAESTROS (Construidos Manualmente)
    // =========================================================

    public DefaultTableModel listarClientes(String filtro) {
        setUltimoError("");
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID", "Cliente", "DUI", "Teléfono", "Dirección", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        String sql = "SELECT id_cliente, CONCAT(COALESCE(nombre, ''), ' ', COALESCE(apellido, '')) AS cliente_nombre, "
                   + "dui, telefono, direccion, estado FROM CLIENTES ";

        boolean usarFiltro = filtro != null && !filtro.trim().isEmpty();
        if (usarFiltro) {
            sql += "WHERE CONCAT(COALESCE(nombre, ''), ' ', COALESCE(apellido, '')) LIKE ? "
                 + "OR dui LIKE ? OR telefono LIKE ? OR direccion LIKE ? OR estado LIKE ? ";
        }
        sql += "ORDER BY id_cliente DESC";

        try (Connection cn = conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            if (usarFiltro) {
                String busqueda = "%" + filtro.trim() + "%";
                for (int i = 1; i <= 5; i++) ps.setString(i, busqueda);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt("id_cliente"),
                        rs.getString("cliente_nombre"),
                        rs.getString("dui"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("estado")
                    });
                }
            }
        } catch (SQLException e) {
            setUltimoError("Error al listar clientes: " + e.getMessage());
        }
        return modelo;
    }

    public DefaultTableModel listarProveedores(String filtro) {
        setUltimoError("");
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID", "Proveedor", "Teléfono", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        String sql = "SELECT id_proveedor, nombre_empresa, telefono, estado FROM PROVEEDORES ";

        boolean usarFiltro = filtro != null && !filtro.trim().isEmpty();
        if (usarFiltro) {
            sql += "WHERE nombre_empresa LIKE ? OR telefono LIKE ? OR estado LIKE ? ";
        }
        sql += "ORDER BY id_proveedor DESC";

        try (Connection cn = conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            if (usarFiltro) {
                String busqueda = "%" + filtro.trim() + "%";
                for (int i = 1; i <= 3; i++) ps.setString(i, busqueda);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt("id_proveedor"),
                        rs.getString("nombre_empresa"),
                        rs.getString("telefono"),
                        rs.getString("estado")
                    });
                }
            }
        } catch (SQLException e) {
            setUltimoError("Error al listar proveedores: " + e.getMessage());
        }
        return modelo;
    }

    public DefaultTableModel listarCategorias(String filtro) {
        setUltimoError("");
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID", "Categoría", "Descripción", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        String sql = "SELECT id_categoria, nombre, descripcion, estado FROM CATEGORIAS ";

        boolean usarFiltro = filtro != null && !filtro.trim().isEmpty();
        if (usarFiltro) {
            sql += "WHERE nombre LIKE ? OR descripcion LIKE ? OR estado LIKE ? ";
        }
        sql += "ORDER BY id_categoria DESC";

        try (Connection cn = conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            if (usarFiltro) {
                String busqueda = "%" + filtro.trim() + "%";
                for (int i = 1; i <= 3; i++) ps.setString(i, busqueda);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt("id_categoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("estado")
                    });
                }
            }
        } catch (SQLException e) {
            setUltimoError("Error al listar categorías: " + e.getMessage());
        }
        return modelo;
    }

    public DefaultTableModel listarProductos(String filtro) {
        setUltimoError("");
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID", "Código", "Producto", "Categoría", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        // Hacemos JOIN con Categorias para mostrar el nombre en lugar del ID
        String sql = "SELECT p.id_producto, p.codigo_barras, p.nombre AS producto_nombre, "
                   + "c.nombre AS categoria_nombre, p.estado "
                   + "FROM PRODUCTOS p "
                   + "LEFT JOIN CATEGORIAS c ON p.id_categoria = c.id_categoria ";

        boolean usarFiltro = filtro != null && !filtro.trim().isEmpty();
        if (usarFiltro) {
            sql += "WHERE p.nombre LIKE ? OR p.codigo_barras LIKE ? OR p.estado LIKE ? ";
        }
        sql += "ORDER BY p.id_producto DESC";

        try (Connection cn = conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            if (usarFiltro) {
                String busqueda = "%" + filtro.trim() + "%";
                for (int i = 1; i <= 3; i++) ps.setString(i, busqueda);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt("id_producto"),
                        rs.getString("codigo_barras"),
                        rs.getString("producto_nombre"),
                        rs.getString("categoria_nombre"),
                        rs.getString("estado")
                    });
                }
            }
        } catch (SQLException e) {
            setUltimoError("Error al listar productos: " + e.getMessage());
        }
        return modelo;
    }

    // =========================================================
    // DETALLES (Construidos Manualmente)
    // =========================================================

    public DefaultTableModel listarFacturasVentasPorCliente(int idCliente) {
        setUltimoError("");
        
       
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID", "N° Comprobante", "Fecha Emisión", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        String sql = "SELECT id_venta, numero_comprobante, fecha_hora, estado "
                   + "FROM VENTAS WHERE id_cliente = ? ORDER BY id_venta DESC";

        try (Connection cn = conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt("id_venta"),
                        rs.getString("numero_comprobante"),
                        rs.getTimestamp("fecha_hora"),
                        rs.getString("estado")
                    });
                }
            }
        } catch (SQLException e) {
            setUltimoError("Error al listar ventas del cliente: " + e.getMessage());
        }
        return modelo;
    }

    public DefaultTableModel listarComprasPorProveedor(int idProveedor) {
        setUltimoError("");
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID Compra", "N° Comprobante", "Fecha de Compra", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        String sql = "SELECT id_compra, numero_comprobante, fecha_hora, estado "
                   + "FROM COMPRAS WHERE id_proveedor = ? ORDER BY id_compra DESC";

        try (Connection cn = conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt("id_compra"),
                        rs.getString("numero_comprobante"),
                        rs.getTimestamp("fecha_hora"),
                        rs.getString("estado")
                    });
                }
            }
        } catch (SQLException e) {
            setUltimoError("Error al listar compras del proveedor: " + e.getMessage());
        }
        return modelo;
    }

    public DefaultTableModel listarProductosPorCategoria(int idCategoria) {
        setUltimoError("");
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"ID", "Código Barras", "Producto", "Descripción", "IVA", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        String sql = "SELECT id_producto, codigo_barras, nombre, descripcion_tecnica, porcentaje_iva_default, estado "
                   + "FROM PRODUCTOS WHERE id_categoria = ? ORDER BY id_producto DESC";

        try (Connection cn = conexion.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modelo.addRow(new Object[]{
                        rs.getInt("id_producto"),
                        rs.getString("codigo_barras"),
                        rs.getString("nombre"),
                        rs.getString("descripcion_tecnica"),
                        rs.getDouble("porcentaje_iva_default") + "%",
                        rs.getString("estado")
                    });
                }
            }
        } catch (SQLException e) {
            setUltimoError("Error al listar productos por categoría: " + e.getMessage());
        }
        return modelo;
    }

    public DefaultTableModel listarLotesPorProducto(int idProducto) {
    setUltimoError("");
    DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{
                "ID Lote", "N° Lote", "Stock Inicial", "Stock Actual",
                "Fecha Ingreso", "Precio Compra", "Precio Venta", "Estado"
            }, 0) {
        @Override
        public boolean isCellEditable(int row, int column) { return false; }
    };

    String sql = """
        SELECT
            id_lote,
            numero_lote,
            stock_inicial,
            stock_actual,
            fecha_ingreso,
            precio_compra,
            precio_venta,
            estado
        FROM LOTES
        WHERE id_producto = ?
        ORDER BY id_lote DESC
    """;

    try (
        Connection cn = conexion.conectar();
        PreparedStatement ps = cn.prepareStatement(sql)
    ) {
        ps.setInt(1, idProducto);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id_lote"),
                    rs.getString("numero_lote"),
                    rs.getInt("stock_inicial"),
                    rs.getInt("stock_actual"),
                    rs.getDate("fecha_ingreso"),
                    "$" + String.format("%.2f", rs.getDouble("precio_compra")),
                    "$" + String.format("%.2f", rs.getDouble("precio_venta")),
                    rs.getString("estado")
                });
            }
        }

    } catch (SQLException e) {
        setUltimoError("Error al listar lotes: " + e.getMessage());
    }

    return modelo;
}
}