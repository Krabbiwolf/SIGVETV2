package Modelos;

import Conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;

public class CategoriaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // LISTAR
    public ArrayList<Categoria> listar() {
        ArrayList<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIAS";

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNombre(rs.getString("nombre")); // Agregado: leer nombre de la BD
                c.setDescripcion(rs.getString("descripcion"));
                c.setEstado(rs.getString("estado"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    // GUARDAR
    public boolean guardar(Categoria c) {
        // SQL tiene 3 parámetros: ?, ?, ?
        String sql = "INSERT INTO CATEGORIAS (nombre, descripcion, estado) VALUES (?, ?, ?)";

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            // Debes asignar los 3 parámetros en orden
            ps.setString(1, c.getNombre());      // Parámetro 1: nombre
            ps.setString(2, c.getDescripcion()); // Parámetro 2: descripcion
            ps.setString(3, c.getEstado());    // Parámetro 3: estado
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    // ACTUALIZAR
    public boolean actualizar(Categoria c) {
        // SQL tiene 4 parámetros: ?, ?, ?, ?
        String sql = "UPDATE CATEGORIAS SET nombre=?, descripcion=?, estado=? WHERE id_categoria=?";

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, c.getNombre());      // 1
            ps.setString(2, c.getDescripcion()); // 2
            ps.setString(3, c.getEstado());    // 3
            ps.setInt(4, c.getIdCategoria());    // 4
            
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    // ELIMINAR LOGICO
    public boolean eliminarLogico(int id) {
        String sql = "UPDATE CATEGORIAS SET estado='INACTIVO' WHERE id_categoria=?";

        try {
            con = cn.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            cerrarRecursos();
        }
    }
    
    // Método auxiliar para cerrar conexiones (buena práctica)
    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}