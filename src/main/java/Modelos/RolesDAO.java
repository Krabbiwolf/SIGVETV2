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
public class RolesDAO {
    Conexion con = new Conexion();
    private PreparedStatement ps;
    private ResultSet rs;
    
    public boolean registrarRol(Roles rol) {
        String sql = "INSERT INTO ROLES (nombre_cargo,descripcion) VALUES(?,?);";

        Connection conexion = con.conectar();

        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, rol.getNombre_cargo());
            ps.setString(2, rol.getDescripcion());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar rol: " + e.getMessage());
            return false;
        }
    }
    
    public ArrayList<Roles> listarRoles(){
        ArrayList<Roles> roles = new ArrayList<>();
        String sql="SELECT * FROM ROLES";
        Connection conexion = con.conectar();
        
        try{
            ps=conexion.prepareStatement(sql);
            rs=ps.executeQuery();
            
            while(rs.next()){
                Roles rol = new Roles();
                rol.setIdRol(rs.getInt("id_rol"));
                rol.setNombre_cargo(rs.getString("nombre_cargo"));
                rol.setDescripcion(rs.getString("descripcion"));
                
                roles.add(rol);
            }
        }catch(SQLException e){
            System.out.println("Error al listar roles: " + e.getMessage());
        }
        
        return  roles;
    }
}