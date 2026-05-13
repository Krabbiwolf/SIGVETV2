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
        // INSERTAR ROL
        // =========================

        String sqlRol =
        "INSERT INTO ROLES(nombre_cargo, descripcion) VALUES(?, ?)";

        ps = conexion.prepareStatement(
            sqlRol,
            PreparedStatement.RETURN_GENERATED_KEYS
        );

        ps.setString(1, rol.getNombre_cargo());
        ps.setString(2, rol.getDescripcion());

        ps.executeUpdate();

        // =========================
        // OBTENER ID DEL ROL
        // =========================

        rs = ps.getGeneratedKeys();

        int idRol = 0;

        if (rs.next()) {
            idRol = rs.getInt(1);
        }

        // =========================
        // INSERTAR PERMISOS
        // =========================

        for (String nombrePermiso : permisos) {

            String sqlPermiso =
            "INSERT INTO PERMISOS(nombre_permiso) VALUES(?)";

            ps = conexion.prepareStatement(
                sqlPermiso,
                PreparedStatement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, nombrePermiso);

            ps.executeUpdate();

            // =========================
            // OBTENER ID DEL PERMISO
            // =========================

            rs = ps.getGeneratedKeys();

            int idPermiso = 0;

            if (rs.next()) {
                idPermiso = rs.getInt(1);
            }

            // =========================
            // INSERTAR RELACIÓN
            // =========================

            String sqlRelacion =
            "INSERT INTO ROL_PERMISO(id_rol, id_permiso) VALUES(?, ?)";

            ps = conexion.prepareStatement(sqlRelacion);

            ps.setInt(1, idRol);
            ps.setInt(2, idPermiso);

            ps.executeUpdate();
        }

        conexion.commit();

        return true;

    } catch (SQLException e) {

        try {
            conexion.rollback();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(
            "Error al registrar rol: " + e.getMessage()
        );

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