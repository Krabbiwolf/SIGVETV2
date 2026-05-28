package Modelos;

import Conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;

public class CategoriaDAO {

    private final Conexion conexion = new Conexion();

    public boolean guardar(Categoria categoria) {
        String sql = "INSERT INTO CATEGORIAS (nombre, descripcion, estado) VALUES (?, ?, ?)";

        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setString(3, categoria.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar categoría: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Categoria> listar() {
        ArrayList<Categoria> lista = new ArrayList<>();

        String sql = "SELECT id_categoria, nombre, descripcion, estado "
                + "FROM CATEGORIAS "
                + "ORDER BY id_categoria DESC";

        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();

                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setEstado(rs.getString("estado"));

                lista.add(categoria);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Categoria categoria) {
        String sql = "UPDATE CATEGORIAS "
                + "SET nombre = ?, descripcion = ?, estado = ? "
                + "WHERE id_categoria = ?";

        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setString(3, categoria.getEstado());
            ps.setInt(4, categoria.getIdCategoria());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar categoría: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarLogico(int idCategoria) {
        String sql = "UPDATE CATEGORIAS SET estado = 'Inactivo' WHERE id_categoria = ?";

        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al inactivar categoría: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarFisico(int idCategoria) {
        String sql = "DELETE FROM CATEGORIAS WHERE id_categoria = ?";

        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idCategoria);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar categoría permanentemente: " + e.getMessage());
            return false;
        }
    }

    public boolean existeNombre(String nombre, int idCategoriaActual) {
        String sql = "SELECT COUNT(*) FROM CATEGORIAS "
                + "WHERE LOWER(nombre) = LOWER(?) "
                + "AND id_categoria <> ?";

        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setInt(2, idCategoriaActual);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al validar nombre de categoría: " + e.getMessage());
        }

        return false;
    }

    public ArrayList<String> obtenerRelacionesCategoria(int idCategoria) {
        ArrayList<String> relaciones = new ArrayList<>();

        try (Connection cn = conexion.conectar()) {

            verificarRelacion(cn, relaciones, "PRODUCTOS", "id_categoria", idCategoria, "productos");
            verificarRelacion(cn, relaciones, "PRODUCTOS", "idCategoria", idCategoria, "productos");
            verificarRelacion(cn, relaciones, "PRODUCTOS", "categoria_id", idCategoria, "productos");

            verificarRelacion(cn, relaciones, "productos", "id_categoria", idCategoria, "productos");
            verificarRelacion(cn, relaciones, "productos", "idCategoria", idCategoria, "productos");
            verificarRelacion(cn, relaciones, "productos", "categoria_id", idCategoria, "productos");

            verificarRelacion(cn, relaciones, "Producto", "id_categoria", idCategoria, "productos");
            verificarRelacion(cn, relaciones, "Producto", "idCategoria", idCategoria, "productos");
            verificarRelacion(cn, relaciones, "Producto", "categoria_id", idCategoria, "productos");

            verificarRelacion(cn, relaciones, "PRODUCTO", "id_categoria", idCategoria, "productos");
            verificarRelacion(cn, relaciones, "PRODUCTO", "idCategoria", idCategoria, "productos");
            verificarRelacion(cn, relaciones, "PRODUCTO", "categoria_id", idCategoria, "productos");

        } catch (SQLException e) {
            System.out.println("Error al verificar relaciones de categoría: " + e.getMessage());
        }

        return relaciones;
    }

    private void verificarRelacion(Connection cn, ArrayList<String> relaciones, String tabla, String columna, int idCategoria, String nombreMostrar) {
        if (!existeTabla(cn, tabla) || !existeColumna(cn, tabla, columna)) {
            return;
        }

        String sql = "SELECT COUNT(*) FROM " + tabla + " WHERE " + columna + " = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    if (!relaciones.contains(nombreMostrar)) {
                        relaciones.add(nombreMostrar);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error verificando relación en " + tabla + ": " + e.getMessage());
        }
    }

    private boolean existeTabla(Connection cn, String tabla) {
        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES "
                + "WHERE TABLE_SCHEMA = DATABASE() "
                + "AND TABLE_NAME = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, tabla);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error verificando tabla " + tabla + ": " + e.getMessage());
            return false;
        }
    }

    private boolean existeColumna(Connection cn, String tabla, String columna) {
        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS "
                + "WHERE TABLE_SCHEMA = DATABASE() "
                + "AND TABLE_NAME = ? "
                + "AND COLUMN_NAME = ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, tabla);
            ps.setString(2, columna);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error verificando columna " + columna + ": " + e.getMessage());
            return false;
        }
    }
}