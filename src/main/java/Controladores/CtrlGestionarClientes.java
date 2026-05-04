package Controladores;

import Modelos.Cliente;
import Modelos.ClienteDAO;
import Vistas.GestionarClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlGestionarClientes implements ActionListener {

    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private GestionarClientes form;

    public CtrlGestionarClientes(Cliente cliente, ClienteDAO clienteDAO, GestionarClientes form) {
        this.cliente = cliente;
        this.clienteDAO = clienteDAO;
        this.form = form;

        this.form.btnActualizar.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);
        this.form.btnRefrescar.addActionListener(this);

        cargarTabla();

        this.form.tblClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });
    }

    public void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override // Evita que las celdas sean editables directamente en la tabla
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("DUI");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Dirección");
        modelo.addColumn("Estado");

        ArrayList<Cliente> lista = clienteDAO.listar();
        for (Cliente c : lista) {
            modelo.addRow(new Object[]{
                c.getId_cliente(),
                c.getNombre(),
                c.getApellido(),
                c.getDui(),
                c.getTelefono(),
                c.getDireccion(),
                c.getEstado()
            });
        }
        form.tblClientes.setModel(modelo);
    }

    public void seleccionarFila() {
        int fila = form.tblClientes.getSelectedRow();
        if (fila >= 0) {
            form.txtIdCliente.setText(form.tblClientes.getValueAt(fila, 0).toString());
            form.txtNombre.setText(form.tblClientes.getValueAt(fila, 1).toString());
            form.txtApellido.setText(form.tblClientes.getValueAt(fila, 2).toString());
            form.txtDui.setText(form.tblClientes.getValueAt(fila, 3).toString());
            form.txtTelefono.setText(form.tblClientes.getValueAt(fila, 4).toString());
            form.txtDireccion.setText(form.tblClientes.getValueAt(fila, 5).toString());
            form.cbEstado.setSelectedItem(form.tblClientes.getValueAt(fila, 6).toString());
        }
    }

    public void limpiarCampos() {
        form.txtIdCliente.setText("");
        form.txtNombre.setText("");
        form.txtApellido.setText("");
        form.txtDui.setText("");
        form.txtTelefono.setText("");
        form.txtDireccion.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // -------- ACTUALIZAR --------
        if (e.getSource() == form.btnActualizar) {
            
            // Validar selección
            if (form.txtIdCliente.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente de la tabla");
                return;
            }

            // OBTENER DATOS
            String nombre = form.txtNombre.getText().trim();
            String apellido = form.txtApellido.getText().trim();
            String dui = form.txtDui.getText().trim();
            String telefono = form.txtTelefono.getText().trim();

            // VALIDACIÓN: Solo letras en Nombre y Apellido
            // [a-zA-ZáéíóúÁÉÍÓÚñÑ ] permite letras, tildes y espacios
            if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras.");
                return;
            }
            if (!apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                JOptionPane.showMessageDialog(null, "El apellido solo debe contener letras.");
                return;
            }

            // VALIDACIÓN: DUI (00000000-0)
            if (!dui.matches("\\d{8}-\\d")) {
                JOptionPane.showMessageDialog(null, "DUI inválido. Formato: 00000000-0");
                return;
            }

            // VALIDACIÓN: Teléfono (mínimo 8 dígitos numéricos)
            if (!telefono.matches("\\d{8,15}")) {
                JOptionPane.showMessageDialog(null, "Teléfono inválido. Ingrese solo números (mínimo 8).");
                return;
            }

            // LLENAR OBJETO
            cliente.setId_cliente(Integer.parseInt(form.txtIdCliente.getText()));
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDui(dui);
            cliente.setTelefono(telefono);
            cliente.setDireccion(form.txtDireccion.getText().trim());
            cliente.setEstado(form.cbEstado.getSelectedItem().toString());

            if (clienteDAO.actualizar(cliente)) {
                JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente");
                cargarTabla(); // <--- SE ACTUALIZA LA TABLA AUTOMÁTICAMENTE
                limpiarCampos();
            }
        }

        // -------- ELIMINAR --------
        if (e.getSource() == form.btnEliminar) {
            if (form.txtIdCliente.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente");
                return;
            }

            int confirmar = JOptionPane.showConfirmDialog(null, "¿Inactivar cliente?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(form.txtIdCliente.getText());
                if (clienteDAO.eliminarLogico(id)) {
                    JOptionPane.showMessageDialog(null, "Cliente Inactivado");
                    cargarTabla(); // <--- SE ACTUALIZA LA TABLA AUTOMÁTICAMENTE
                    limpiarCampos();
                }
            }
        }

        // -------- REFRESCAR --------
        if (e.getSource() == form.btnRefrescar) {
            cargarTabla();
            limpiarCampos();
        }
    }
}