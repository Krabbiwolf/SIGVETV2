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
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class CtrlGestionarClientes implements ActionListener {

    private Cliente cliente;
    private ClienteDAO clienteDAO;
    private GestionarClientes form;
    private SwingWorker<ArrayList<Cliente>, Void> currentWorker;

    public CtrlGestionarClientes(Cliente cliente, ClienteDAO clienteDAO, GestionarClientes form) {
        this.cliente = cliente;
        this.clienteDAO = clienteDAO;
        this.form = form;

        this.form.btnActualizar.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);
        this.form.btnRefrescar.addActionListener(this);

        cargarTabla(); // asíncrono

        this.form.tblClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });
    }

    // ================== CARGA ASÍNCRONA ==================
    public void cargarTabla() {
        if (currentWorker != null && !currentWorker.isDone()) {
            currentWorker.cancel(true);
        }
        currentWorker = new SwingWorker<ArrayList<Cliente>, Void>() {
            @Override
            protected ArrayList<Cliente> doInBackground() throws Exception {
                return clienteDAO.listar();
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Cliente> lista = get();
                    DefaultTableModel modelo = new DefaultTableModel() {
                        @Override
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
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, "Error al cargar clientes: " + ex.getMessage());
                } finally {
                    currentWorker = null;
                }
            }
        };
        currentWorker.execute();
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
        if (e.getSource() == form.btnActualizar) {
            if (form.txtIdCliente.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente de la tabla");
                return;
            }
            String nombre = form.txtNombre.getText().trim();
            String apellido = form.txtApellido.getText().trim();
            String dui = form.txtDui.getText().trim();
            String telefono = form.txtTelefono.getText().trim();
            if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                JOptionPane.showMessageDialog(null, "El nombre solo debe contener letras.");
                return;
            }
            if (!apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                JOptionPane.showMessageDialog(null, "El apellido solo debe contener letras.");
                return;
            }
            if (!dui.matches("\\d{8}-\\d")) {
                JOptionPane.showMessageDialog(null, "DUI inválido. Formato: 00000000-0");
                return;
            }
            if (!telefono.matches("\\d{8,15}")) {
                JOptionPane.showMessageDialog(null, "Teléfono inválido. Ingrese solo números (mínimo 8).");
                return;
            }
            cliente.setId_cliente(Integer.parseInt(form.txtIdCliente.getText()));
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setDui(dui);
            cliente.setTelefono(telefono);
            cliente.setDireccion(form.txtDireccion.getText().trim());
            cliente.setEstado(form.cbEstado.getSelectedItem().toString());
            if (clienteDAO.actualizar(cliente)) {
                JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente");
                cargarTabla();
                limpiarCampos();
            }
        }

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
                    cargarTabla();
                    limpiarCampos();
                }
            }
        }

        if (e.getSource() == form.btnRefrescar) {
            cargarTabla();
            limpiarCampos();
        }
    }
}