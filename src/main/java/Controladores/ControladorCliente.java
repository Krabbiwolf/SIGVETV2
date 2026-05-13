/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;
import Conexion.Conexion;
import Modelos.Cliente;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author afane
 */
public class ControladorCliente {
     private Conexion conexion = new Conexion();

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
            JOptionPane.showMessageDialog(null, "Error al guardar cliente: " + e.getMessage());
            return false;
        }
    }

    public DefaultTableModel mostrar() {
        String[] columnas = {"ID", "Nombre", "Apellido", "DUI", "Teléfono", "Dirección", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String sql = "SELECT id_cliente, nombre, apellido, dui, telefono, direccion, estado FROM CLIENTES";

        try {
            Connection cn = conexion.conectar();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Object[] fila = new Object[7];

                fila[0] = rs.getInt("id_cliente");
                fila[1] = rs.getString("nombre");
                fila[2] = rs.getString("apellido");
                fila[3] = rs.getString("dui");
                fila[4] = rs.getString("telefono");
                fila[5] = rs.getString("direccion");
                fila[6] = rs.getString("estado");

                modelo.addRow(fila);
            }

            cn.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar clientes: " + e.getMessage());
        }

        return modelo;
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
            JOptionPane.showMessageDialog(null, "Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idCliente) {
        String sql = "UPDATE CLIENTES SET estado='Inactivo' WHERE id_cliente=?";

        try {
            Connection cn = conexion.conectar();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setInt(1, idCliente);
            ps.executeUpdate();

            cn.close();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}
