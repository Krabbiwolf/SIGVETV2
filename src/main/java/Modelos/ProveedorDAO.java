package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProveedorDAO {

    Conexion con = new Conexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrar(Proveedor proveedor) {
        // Se agregaron 'estado' y 'ruta_imagen' a la consulta de inserción
        String sql = "INSERT INTO PROVEEDORES (nombre_empresa, telefono, estado, ruta_imagen) VALUES(?,?,?,?);";

        Connection conexion = con.conectar();

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getEstado());
            ps.setString(4, proveedor.getRutaImagen()); // Nuevo campo para la imagen

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar proveedor: " + e.getMessage());
            return false;
        }
    }

    public boolean existeProveedor(String nombre) {
        String sql = "SELECT id_proveedor FROM PROVEEDORES WHERE nombre_empresa = ?";

        try (Connection conexion = con.conectar(); 
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception e) {
            System.out.println("Error al verificar existencia de proveedor: " + e.toString());
            return false;
        }
    }

    public ArrayList<Proveedor> listar() {
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM PROVEEDORES ORDER BY estado";
        Connection conexion = con.conectar();

        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setId(rs.getInt("id_proveedor"));
                proveedor.setNombre(rs.getString("nombre_empresa"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setEstado(rs.getString("estado"));
                
                // Se recupera la ruta de la imagen desde la base de datos
                proveedor.setRutaImagen(rs.getString("ruta_imagen")); 

                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar proveedores: " + e.getMessage());
        }

        return proveedores;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM PROVEEDORES WHERE id_proveedor=?";

        Connection conexion = con.conectar();

        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar proveedor: " + e.getMessage());
        }
        return false;
    }

    public boolean actualizar(Proveedor p) {
        // Se agregó 'ruta_imagen' a la consulta de actualización
        String sql = "UPDATE PROVEEDORES SET nombre_empresa=?, telefono=?, estado=?, ruta_imagen=? WHERE id_proveedor=?";

        Connection conexion = con.conectar();

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTelefono());
            ps.setString(3, p.getEstado());
            ps.setString(4, p.getRutaImagen()); // Nuevo campo para actualizar la imagen
            ps.setInt(5, p.getId());
            
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar proveedor: " + e.getMessage());
        }
        return false;
    }
    
    // Agrégalo dentro de tu ProveedorDAO.java
    public boolean inactivar(int id) {
        String sql = "UPDATE PROVEEDORES SET estado = 'INACTIVO' WHERE id_proveedor = ?";
        Connection conexion = con.conectar();
        try {
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al inactivar proveedor: " + e.getMessage());
            return false;
        }
    }
}