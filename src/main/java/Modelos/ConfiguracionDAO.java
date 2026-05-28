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
import java.util.HashMap;

public class ConfiguracionDAO {

    private final Conexion conexion = new Conexion();

    public HashMap<String, Double> obtenerConfiguraciones() {
        HashMap<String, Double> configuraciones = new HashMap<>();

        String sql = "SELECT clave, valor FROM CONFIGURACIONES";

        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                configuraciones.put(
                        rs.getString("clave"),
                        rs.getDouble("valor")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener configuraciones: " + e.getMessage());
        }

        return configuraciones;
    }

    public double obtenerValor(String clave, double valorPorDefecto) {
        String sql = "SELECT valor FROM CONFIGURACIONES WHERE clave = ?";

        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, clave);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("valor");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener valor de configuración: " + e.getMessage());
        }

        return valorPorDefecto;
    }

    public boolean actualizarValor(String clave, double valor) {
        String sql = "UPDATE CONFIGURACIONES SET valor = ? WHERE clave = ?";

        try (Connection cn = conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setDouble(1, valor);
            ps.setString(2, clave);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar configuración: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarConfiguraciones(double porcentajeGanancia, double ivaPredeterminado, double descuentoMaximo) {
        boolean gananciaOk = actualizarValor("porcentaje_ganancia_venta", porcentajeGanancia);
        boolean ivaOk = actualizarValor("iva_predeterminado", ivaPredeterminado);
        boolean descuentoOk = actualizarValor("descuento_maximo", descuentoMaximo);

        return gananciaOk && ivaOk && descuentoOk;
    }
}
