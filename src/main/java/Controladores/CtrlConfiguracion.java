/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelos.ConfiguracionDAO;
import Vistas.FrmConfiguracion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class CtrlConfiguracion implements ActionListener {

    private final FrmConfiguracion form;
    private final ConfiguracionDAO configuracionDAO;

    public CtrlConfiguracion(FrmConfiguracion form, ConfiguracionDAO configuracionDAO) {
        this.form = form;
        this.configuracionDAO = configuracionDAO;

        this.form.btnGuardar.addActionListener(this);
        this.form.btnRestaurar.addActionListener(this);
        this.form.btnCerrar.addActionListener(this);

        cargarOpcionesCombos();
        cargarConfiguraciones();
    }

    private void cargarOpcionesCombos() {
        form.cbPorcentajeGanancia.removeAllItems();
        form.cbIvaPredeterminado.removeAllItems();
        form.cbDescuentoMaximo.removeAllItems();

        String[] ganancias = {"10", "15", "20", "25", "30", "35", "40", "45", "50", "60", "75", "100"};
        String[] ivas = {"0", "5", "10", "13", "15"};
        String[] descuentos = {"0", "5", "10", "15", "20", "25", "30", "40", "50"};

        for (String valor : ganancias) {
            form.cbPorcentajeGanancia.addItem(valor);
        }

        for (String valor : ivas) {
            form.cbIvaPredeterminado.addItem(valor);
        }

        for (String valor : descuentos) {
            form.cbDescuentoMaximo.addItem(valor);
        }
    }

    private void cargarConfiguraciones() {
        HashMap<String, Double> configuraciones = configuracionDAO.obtenerConfiguraciones();

        double porcentajeGanancia = configuraciones.getOrDefault("porcentaje_ganancia_venta", 50.00);
        double ivaPredeterminado = configuraciones.getOrDefault("iva_predeterminado", 13.00);
        double descuentoMaximo = configuraciones.getOrDefault("descuento_maximo", 25.00);

        seleccionarValorCombo(form.cbPorcentajeGanancia, porcentajeGanancia);
        seleccionarValorCombo(form.cbIvaPredeterminado, ivaPredeterminado);
        seleccionarValorCombo(form.cbDescuentoMaximo, descuentoMaximo);
    }

    private void seleccionarValorCombo(JComboBox<String> combo, double valor) {
        String valorTexto = String.valueOf((int) valor);

        for (int i = 0; i < combo.getItemCount(); i++) {
            if (combo.getItemAt(i).equals(valorTexto)) {
                combo.setSelectedIndex(i);
                return;
            }
        }

        combo.addItem(valorTexto);
        combo.setSelectedItem(valorTexto);
    }

    private void guardarConfiguraciones() {
        if (form.cbPorcentajeGanancia.getSelectedItem() == null
                || form.cbIvaPredeterminado.getSelectedItem() == null
                || form.cbDescuentoMaximo.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(form, "Seleccione todos los valores de configuración.");
            return;
        }

        double porcentajeGanancia = Double.parseDouble(form.cbPorcentajeGanancia.getSelectedItem().toString());
        double ivaPredeterminado = Double.parseDouble(form.cbIvaPredeterminado.getSelectedItem().toString());
        double descuentoMaximo = Double.parseDouble(form.cbDescuentoMaximo.getSelectedItem().toString());

        boolean actualizado = configuracionDAO.actualizarConfiguraciones(
                porcentajeGanancia,
                ivaPredeterminado,
                descuentoMaximo
        );

        if (actualizado) {
            JOptionPane.showMessageDialog(form, "Configuración guardada correctamente.");
        } else {
            JOptionPane.showMessageDialog(form, "Error al guardar la configuración.");
        }
    }

    private void restaurarValores() {
        int confirmar = JOptionPane.showConfirmDialog(
                form,
                "¿Desea restaurar los valores predeterminados?\n\n"
                + "Ganancia: 50%\n"
                + "IVA: 13%\n"
                + "Descuento máximo: 25%",
                "Restaurar configuración",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmar == JOptionPane.YES_OPTION) {
            form.cbPorcentajeGanancia.setSelectedItem("50");
            form.cbIvaPredeterminado.setSelectedItem("13");
            form.cbDescuentoMaximo.setSelectedItem("25");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == form.btnGuardar) {
            guardarConfiguraciones();
        } else if (source == form.btnRestaurar) {
            restaurarValores();
        } else if (source == form.btnCerrar) {
            form.dispose();
        }
    }
}
