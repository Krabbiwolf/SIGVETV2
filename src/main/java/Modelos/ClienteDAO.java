/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;
import Conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author afane
 */
public class ClienteDAO {
    Conexion conexion = new Conexion();

    public boolean guardar(Cliente cliente) {
        String sql = "INSERT INTO CLIENTES (nombre, apellido, dui, telefono, direccion, estado) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection cn = conexion.conectar();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDui());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, "Activo");

            ps.executeUpdate();
            cn.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al guardar cliente: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> lista = new ArrayList<>();

        String sql = "SELECT id_cliente, nombre, apellido, dui, telefono, direccion, estado FROM CLIENTES";

        try {
            Connection cn = conexion.conectar();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setDui(rs.getString("dui"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setEstado(rs.getString("estado"));

                lista.add(cliente);
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return lista;
    }

    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE CLIENTES SET nombre=?, apellido=?, dui=?, telefono=?, direccion=?, estado=? WHERE id_cliente=?";

        try {
            Connection cn = conexion.conectar();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDui());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getEstado());
            ps.setInt(7, cliente.getId_cliente());

            ps.executeUpdate();
            cn.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarLogico(int idCliente) {
        String sql = "UPDATE CLIENTES SET estado = 'Inactivo' WHERE id_cliente = ?";

        try {
            Connection cn = conexion.conectar();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, idCliente);
            ps.executeUpdate();
            cn.close();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}
