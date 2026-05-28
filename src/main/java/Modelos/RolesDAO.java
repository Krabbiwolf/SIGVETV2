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
    
    public boolean registrarRol(Roles rol, ArrayList<String> permisos) 
    {
    Connection conexion = con.conectar();

    try {
            conexion.setAutoCommit(false);

            // =========================
            // 1. INSERTAR EL ROL
            // =========================
            String sqlRol = "INSERT INTO ROLES(nombre_cargo, descripcion) VALUES(?, ?)";
            ps = conexion.prepareStatement(sqlRol, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, rol.getNombre_cargo());
            ps.setString(2, rol.getDescripcion());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            int idRol = 0;
            if (rs.next()) {
                idRol = rs.getInt(1);
            }

            // =========================
            // 2. PROCESAR CADA PERMISO
            // =========================
            for (String nombrePermiso : permisos) {
                int idPermiso = 0;

                // A. Buscar si el permiso ya existe en el diccionario (Tabla PERMISOS)
                String sqlCheck = "SELECT id_permiso FROM PERMISOS WHERE nombre_permiso = ?";
                PreparedStatement psCheck = conexion.prepareStatement(sqlCheck);
                psCheck.setString(1, nombrePermiso);
                ResultSet rsCheck = psCheck.executeQuery();

                if (rsCheck.next()) {
                    // Si ya existe, simplemente tomamos su ID
                    idPermiso = rsCheck.getInt("id_permiso");
                } else {
                    // B. Si NO existe, lo insertamos por primera vez
                    String sqlPermiso = "INSERT INTO PERMISOS(nombre_permiso) VALUES(?)";
                    PreparedStatement psInsert = conexion.prepareStatement(sqlPermiso, PreparedStatement.RETURN_GENERATED_KEYS);
                    psInsert.setString(1, nombrePermiso);
                    psInsert.executeUpdate();
                    
                    ResultSet rsInsert = psInsert.getGeneratedKeys();
                    if (rsInsert.next()) {
                        idPermiso = rsInsert.getInt(1);
                    }
                }

                // =========================
                // 3. INSERTAR LA RELACIÓN ROL_PERMISO
                // =========================
                String sqlRelacion = "INSERT INTO ROL_PERMISO(id_rol, id_permiso) VALUES(?, ?)";
                PreparedStatement psRelacion = conexion.prepareStatement(sqlRelacion);
                psRelacion.setInt(1, idRol);
                psRelacion.setInt(2, idPermiso);
                psRelacion.executeUpdate();
            }

            conexion.commit(); // Confirmamos todos los cambios en la BD
            return true;

        } catch (SQLException e) {
            try {
                conexion.rollback(); // Si algo falla, revertimos todo para no dejar datos a medias
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
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