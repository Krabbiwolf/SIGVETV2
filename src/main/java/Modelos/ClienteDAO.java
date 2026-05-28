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

    try (Connection cn = conexion.conectar();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getApellido());
        ps.setString(3, cliente.getDui());
        ps.setString(4, cliente.getTelefono());
        ps.setString(5, cliente.getDireccion());
        ps.setString(6, cliente.getEstado());

        return ps.executeUpdate() > 0;

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
    
    public boolean existeDui(String dui, int idClienteActual) {
    String sql = "SELECT COUNT(*) FROM CLIENTES WHERE dui = ? AND id_cliente <> ?";

    try {
        Connection cn = conexion.conectar();
        PreparedStatement ps = cn.prepareStatement(sql);

        ps.setString(1, dui);
        ps.setInt(2, idClienteActual);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0;
        }

        cn.close();

    } catch (SQLException e) {
        System.out.println("Error al validar DUI: " + e.getMessage());
    }

    return false;
}
    
     public boolean eliminarFisico(int idCliente) {
    String sql = "DELETE FROM CLIENTES WHERE id_cliente = ?";

    try (Connection cn = conexion.conectar();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, idCliente);
        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al eliminar físicamente cliente: " + e.getMessage());
        return false;
    }
}

public ArrayList<String> obtenerRelacionesCliente(int idCliente) {
    ArrayList<String> relaciones = new ArrayList<>();

    try (Connection cn = conexion.conectar()) {

        verificarRelacion(cn, relaciones, "COMPRAS", "id_cliente", idCliente, "compras");
        verificarRelacion(cn, relaciones, "COMPRA", "id_cliente", idCliente, "compras");

        verificarRelacion(cn, relaciones, "FACTURAS", "id_cliente", idCliente, "facturas");
        verificarRelacion(cn, relaciones, "FACTURA", "id_cliente", idCliente, "facturas");

        verificarRelacion(cn, relaciones, "VENTAS", "id_cliente", idCliente, "ventas");
        verificarRelacion(cn, relaciones, "VENTA", "id_cliente", idCliente, "ventas");

    } catch (SQLException e) {
        System.out.println("Error al verificar relaciones del cliente: " + e.getMessage());
    }

    return relaciones;
}

private void verificarRelacion(Connection cn, ArrayList<String> relaciones, String tabla, String columna, int idCliente, String nombreMostrar) {
    if (!existeTabla(cn, tabla) || !existeColumna(cn, tabla, columna)) {
        return;
    }

    String sql = "SELECT COUNT(*) FROM " + tabla + " WHERE " + columna + " = ?";

    try (PreparedStatement ps = cn.prepareStatement(sql)) {
        ps.setInt(1, idCliente);

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
            + "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ?";

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
            + "WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ? AND COLUMN_NAME = ?";

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
