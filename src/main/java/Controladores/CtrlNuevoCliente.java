/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelos.Cliente;
import Modelos.ClienteDAO;
import Vistas.NuevoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CtrlNuevoCliente implements ActionListener {

    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private NuevoCliente form;

    public CtrlNuevoCliente(Cliente cliente, ClienteDAO clienteDAO, NuevoCliente form) {
        this.cliente = cliente;
        this.clienteDAO = clienteDAO;
        this.form = form;

        this.form.btnGuardar.addActionListener(this);
    }

    public void limpiarCampos() {
        form.txtNombre.setText("");
        form.txtApellido.setText("");
        form.txtDui.setText("");
        form.txtTelefono.setText("");
        form.txtDireccion.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == form.btnGuardar) {

            if (form.txtNombre.getText().isEmpty()
                    || form.txtApellido.getText().isEmpty()
                    || form.txtDui.getText().isEmpty()
                    || form.txtTelefono.getText().isEmpty()
                    || form.txtDireccion.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Complete todos los campos");
                return;
            }

            cliente.setNombre(form.txtNombre.getText());
            cliente.setApellido(form.txtApellido.getText());
            cliente.setDui(form.txtDui.getText());
            cliente.setTelefono(form.txtTelefono.getText());
            cliente.setDireccion(form.txtDireccion.getText());
            cliente.setEstado("Activo");
            
            if (!form.txtDui.getText().matches("\\d{8}-\\d")) {
    JOptionPane.showMessageDialog(null, "DUI inválido. Formato correcto: 00000000-0");
    return;
}

            if (clienteDAO.guardar(cliente)) {
                JOptionPane.showMessageDialog(null, "Cliente guardado correctamente");
                limpiarCampos();
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar cliente");
            }
        }
    }
}
