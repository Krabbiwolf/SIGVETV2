/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author axele
 */
public class UsuarioDAO {
    Conexion con = new Conexion();
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO USUARIOS (nombre,apellido,dui,telefono,username,password,imagen_url,id_rol) VALUES(?,?,?,?,?,MD5(?),?,?);";

        Connection conexion = con.conectar();

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getDui());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getUsuario());
            ps.setString(6, usuario.getPassword());
            ps.setString(7, usuario.getImagenURL());
            ps.setInt(8, usuario.getIdRol());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Usuario> listarUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String sql="SELECT u.id_usuario, u.nombre, u.apellido, u.dui, u.telefono, u.username, u.password, u.imagen_url, u.estado, r.nombre_cargo FROM USUARIOS u INNER JOIN ROLES r ON u.id_rol = r.id_rol ORDER BY u.estado";
        Connection conexion = con.conectar();
        
        try{
            ps=conexion.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                Usuario usuario =new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                usuario.setDui(rs.getString("dui"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setUsuario(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setImagenURL(rs.getString("imagen_url"));
                usuario.setEstado(rs.getString("estado"));
                usuario.setRolCargo(rs.getString("nombre_cargo"));
                
                usuarios.add(usuario);
            }
        }catch(SQLException e){
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        
        return  usuarios;
    }
    
    public boolean eliminarUsuario(int id){
        String sql="DELETE FROM USUARIOS WHERE id_usuario = ?";
        
        Connection conexion = con.conectar();
        
        try{
            ps=conexion.prepareStatement(sql);
            ps.setInt(1, id);
            int filasAfectadas=ps.executeUpdate();
         
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
            
        }catch(SQLException e){
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
        return false;
    }
    
    public boolean actualizarUsuario(Usuario usuario){
        String sql="UPDATE USUARIOS SET nombre=?,apellido=?,dui=?,telefono=?,username=?,password=MD5(?),imagen_url=?,estado=?,id_rol=? WHERE id_usuario = ?";
        
        Connection conexion = con.conectar();
        
        try{
            ps=conexion.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getDui());
            ps.setString(4, usuario.getTelefono());
            ps.setString(5, usuario.getUsuario());
            ps.setString(6, usuario.getPassword());
            ps.setString(7, usuario.getImagenURL());
            ps.setString(8, usuario.getEstado());
            ps.setInt(9, usuario.getIdRol());
            ps.setInt(10, usuario.getIdUsuario());
            
            int filasAfectadas=ps.executeUpdate();
         
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
            
        }catch(SQLException e){
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
        return false;
    }

public Usuario login(String user, String pass) {
    String sql = "SELECT u.*, r.nombre_cargo FROM USUARIOS u "
               + "INNER JOIN ROLES r ON u.id_rol = r.id_rol "
               + "WHERE u.username = ? AND u.password = MD5(?) AND u.estado = 'ACTIVO'";
    Connection conexion = con.conectar();
    try {
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, user);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Usuario u = new Usuario();
            u.setIdUsuario(rs.getInt("id_usuario"));
            u.setIdRol(rs.getInt("id_rol"));
            u.setNombre(rs.getString("nombre"));
            u.setApellido(rs.getString("apellido"));
            u.setUsuario(rs.getString("username"));
            u.setRolCargo(rs.getString("nombre_cargo"));
            return u;
        }
    } catch (SQLException e) {
        System.out.println("Error en login: " + e.getMessage());
    }
    return null;
}

    public ArrayList<String> obtenerPermisosDeRol(int idRol) {
        ArrayList<String> permisos = new ArrayList<>();
        String sql = "SELECT p.nombre_permiso FROM PERMISOS p " +
                     "INNER JOIN ROL_PERMISO rp ON p.id_permiso = rp.id_permiso " +
                     "WHERE rp.id_rol = ?";
        Connection conexion = con.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idRol);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                permisos.add(rs.getString("nombre_permiso"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener permisos: " + e.getMessage());
        }
        return permisos;
    }

}

