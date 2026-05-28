package Controladores;

import Modelos.Cliente;
import Modelos.ClienteDAO;
import Modelos.SesionUsuario;
import Vistas.FrmGestionarClientes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CtrlGestionarClientes implements ActionListener {

    private final ClienteDAO clienteDAO;
    private final FrmGestionarClientes form;
    private SwingWorker<ArrayList<Cliente>, Void> currentWorker;

    public CtrlGestionarClientes(Cliente cliente, ClienteDAO clienteDAO, FrmGestionarClientes form) {
        this.clienteDAO = clienteDAO;
        this.form = form;

        this.form.btnGuardar.addActionListener(this);
        this.form.btnActualizar.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);
        this.form.btnRefrescar.addActionListener(this);
        this.form.btnLimpiar.addActionListener(this);
        this.form.btnVerDetalle.addActionListener(this);

        this.form.btnBuscarClientes.addActionListener(this);
        this.form.btnLimpiarFiltroClientes.addActionListener(this);
        this.form.btnExportarClientes.addActionListener(this);

        this.form.tblClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });

        agregarValidacionesDeTeclado();
        estadoInicial();
        cargarTabla();
        
        // Bloquear edición si no tiene permiso
        if (!SesionUsuario.tienePermiso("EDICION_TERCEROS")) {
            form.btnGuardar.setVisible(false);
            form.btnActualizar.setVisible(false);
            form.btnEliminar.setVisible(false);
        }
        
        // Bloquear exportación
        if (!SesionUsuario.tienePermiso("EXPORTAR_TERCEROS")) {
            form.btnExportarClientes.setVisible(false);
        }
    }

    private void estadoInicial() {
        form.txtIdCliente.setText("");
        form.txtIdCliente.setEnabled(false);

        form.btnGuardar.setEnabled(true);
        form.btnActualizar.setEnabled(false);
        form.btnEliminar.setEnabled(false);
        form.btnVerDetalle.setEnabled(false);

        form.chkEstado.setSelected(true);

        form.cbFiltroClientes.removeAllItems();
        form.cbFiltroClientes.addItem("Todos");
        form.cbFiltroClientes.addItem("ID");
        form.cbFiltroClientes.addItem("Nombre");
        form.cbFiltroClientes.addItem("Apellido");
        form.cbFiltroClientes.addItem("DUI");
        form.cbFiltroClientes.addItem("Teléfono");
        form.cbFiltroClientes.addItem("Dirección");
        form.cbFiltroClientes.addItem("Estado");
        form.cbFiltroClientes.setSelectedItem("Todos");
    }

    private void cargarTabla() {
        if (currentWorker != null && !currentWorker.isDone()) {
            currentWorker.cancel(true);
        }

        DefaultTableModel modeloCargando = crearModeloTabla();
        modeloCargando.addRow(new Object[]{"", "Cargando...", "", "", "", "", ""});
        form.tblClientes.setModel(modeloCargando);
        form.tblClientes.setRowSorter(null);

        currentWorker = new SwingWorker<ArrayList<Cliente>, Void>() {
            @Override
            protected ArrayList<Cliente> doInBackground() {
                return clienteDAO.listar();
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Cliente> lista = get();
                    llenarTabla(lista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, "Error al cargar clientes: " + ex.getMessage());
                } finally {
                    currentWorker = null;
                }
            }
        };

        currentWorker.execute();
    }

    private DefaultTableModel crearModeloTabla() {
        return new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Apellido", "DUI", "Teléfono", "Dirección", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void llenarTabla(ArrayList<Cliente> lista) {
        DefaultTableModel modelo = crearModeloTabla();

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
        form.tblClientes.setRowSorter(null);

        if (form.tblClientes.getColumnModel().getColumnCount() > 0) {
            form.tblClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
            form.tblClientes.getColumnModel().getColumn(1).setPreferredWidth(120);
            form.tblClientes.getColumnModel().getColumn(2).setPreferredWidth(120);
            form.tblClientes.getColumnModel().getColumn(3).setPreferredWidth(110);
            form.tblClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
            form.tblClientes.getColumnModel().getColumn(5).setPreferredWidth(180);
            form.tblClientes.getColumnModel().getColumn(6).setPreferredWidth(90);
        }
    }

    private void seleccionarFila() {
        int fila = form.tblClientes.getSelectedRow();

        if (fila < 0) {
            return;
        }

        fila = form.tblClientes.convertRowIndexToModel(fila);

        Object id = form.tblClientes.getModel().getValueAt(fila, 0);

        if (id == null || id.toString().trim().isEmpty()) {
            return;
        }

        form.txtIdCliente.setText(id.toString());
        form.txtNombre.setText(form.tblClientes.getModel().getValueAt(fila, 1).toString());
        form.txtApellido.setText(form.tblClientes.getModel().getValueAt(fila, 2).toString());
        form.txtDui.setText(form.tblClientes.getModel().getValueAt(fila, 3).toString());
        form.txtTelefono.setText(form.tblClientes.getModel().getValueAt(fila, 4).toString());
        form.txtDireccion.setText(form.tblClientes.getModel().getValueAt(fila, 5).toString());

        String estado = form.tblClientes.getModel().getValueAt(fila, 6).toString();
        form.chkEstado.setSelected(estado.equalsIgnoreCase("Activo"));

        form.btnGuardar.setEnabled(false);
        form.btnActualizar.setEnabled(true);
        form.btnEliminar.setEnabled(true);
        form.btnVerDetalle.setEnabled(true);
    }

    private Cliente obtenerClienteDelFormulario() {
        Cliente c = new Cliente();

        if (!form.txtIdCliente.getText().trim().isEmpty()) {
            c.setId_cliente(Integer.parseInt(form.txtIdCliente.getText().trim()));
        }

        c.setNombre(form.txtNombre.getText().trim());
        c.setApellido(form.txtApellido.getText().trim());
        c.setDui(form.txtDui.getText().trim());
        c.setTelefono(form.txtTelefono.getText().trim());
        c.setDireccion(form.txtDireccion.getText().trim());
        c.setEstado(form.chkEstado.isSelected() ? "Activo" : "Inactivo");

        return c;
    }

    private boolean validarCampos(boolean actualizando) {
        String nombre = form.txtNombre.getText().trim();
        String apellido = form.txtApellido.getText().trim();
        String dui = form.txtDui.getText().trim();
        String telefono = form.txtTelefono.getText().trim();
        String direccion = form.txtDireccion.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || dui.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(form, "Complete todos los campos.");
            return false;
        }

        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            JOptionPane.showMessageDialog(form, "El nombre solo debe contener letras.");
            form.txtNombre.requestFocus();
            return false;
        }

        if (!apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
            JOptionPane.showMessageDialog(form, "El apellido solo debe contener letras.");
            form.txtApellido.requestFocus();
            return false;
        }

        if (!dui.matches("\\d{8}-\\d")) {
            JOptionPane.showMessageDialog(form, "DUI inválido. Formato correcto: 00000000-0");
            form.txtDui.requestFocus();
            return false;
        }

        if (!telefono.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(form, "Teléfono inválido. Debe contener 8 números.");
            form.txtTelefono.requestFocus();
            return false;
        }

        int idActual = 0;

        if (actualizando && !form.txtIdCliente.getText().trim().isEmpty()) {
            idActual = Integer.parseInt(form.txtIdCliente.getText().trim());
        }

        if (clienteDAO.existeDui(dui, idActual)) {
            JOptionPane.showMessageDialog(form, "Ya existe un cliente registrado con ese DUI.");
            form.txtDui.requestFocus();
            return false;
        }

        return true;
    }

    private void guardarCliente() {
        if (!validarCampos(false)) {
            return;
        }

        Cliente c = obtenerClienteDelFormulario();

        if (clienteDAO.guardar(c)) {
            JOptionPane.showMessageDialog(form, "Cliente guardado correctamente.");
            limpiarCampos();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(form, "Error al guardar cliente.");
        }
    }

    private void actualizarCliente() {
        if (form.txtIdCliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(form, "Seleccione un cliente de la tabla.");
            return;
        }

        if (!validarCampos(true)) {
            return;
        }

        Cliente c = obtenerClienteDelFormulario();

        if (clienteDAO.actualizar(c)) {
            JOptionPane.showMessageDialog(form, "Cliente actualizado correctamente.");
            limpiarCampos();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(form, "Error al actualizar cliente.");
        }
    }

    private void eliminarCliente() {
        if (form.txtIdCliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(form, "Seleccione un cliente de la tabla.");
            return;
        }

        int id = Integer.parseInt(form.txtIdCliente.getText().trim());
        ArrayList<String> relaciones = clienteDAO.obtenerRelacionesCliente(id);

        if (relaciones.isEmpty()) {
            int confirmar = JOptionPane.showConfirmDialog(
                    form,
                    "Este cliente no tiene relaciones con otras partes del sistema.\n"
                    + "¿Desea eliminarlo permanentemente?",
                    "Confirmar eliminación definitiva",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmar == JOptionPane.YES_OPTION) {
                if (clienteDAO.eliminarFisico(id)) {
                    JOptionPane.showMessageDialog(form, "Cliente eliminado permanentemente.");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(form, "Error al eliminar el cliente.");
                }
            }

        } else {
            String mensajeRelaciones = String.join(", ", relaciones);

            int confirmar = JOptionPane.showConfirmDialog(
                    form,
                    "Este cliente tiene relación con: " + mensajeRelaciones + ".\n\n"
                    + "Por seguridad no se puede eliminar permanentemente.\n"
                    + "Solo se inactivará el cliente.\n\n"
                    + "¿Desea continuar?",
                    "Cliente con relaciones",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmar == JOptionPane.YES_OPTION) {
                if (clienteDAO.eliminarLogico(id)) {
                    JOptionPane.showMessageDialog(
                            form,
                            "Cliente inactivado correctamente.\n"
                            + "Relaciones encontradas: " + mensajeRelaciones
                    );
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(form, "Error al inactivar el cliente.");
                }
            }
        }
    }

    private void verDetalle() {
        if (form.txtIdCliente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(form, "Seleccione un cliente de la tabla.");
            return;
        }

        String estado = form.chkEstado.isSelected() ? "Activo" : "Inactivo";

        String detalle = "DETALLE DEL CLIENTE\n\n"
                + "ID: " + form.txtIdCliente.getText().trim() + "\n"
                + "Nombre: " + form.txtNombre.getText().trim() + "\n"
                + "Apellido: " + form.txtApellido.getText().trim() + "\n"
                + "DUI: " + form.txtDui.getText().trim() + "\n"
                + "Teléfono: " + form.txtTelefono.getText().trim() + "\n"
                + "Dirección: " + form.txtDireccion.getText().trim() + "\n"
                + "Estado: " + estado;

        JOptionPane.showMessageDialog(form, detalle, "Detalle del Cliente", JOptionPane.INFORMATION_MESSAGE);
    }

    private void filtrarClientes() {
        DefaultTableModel modelo = (DefaultTableModel) form.tblClientes.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        form.tblClientes.setRowSorter(sorter);

        String texto = form.txtBuscarClientes.getText().trim();
        String filtro = form.cbFiltroClientes.getSelectedItem().toString();

        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
            return;
        }

        texto = java.util.regex.Pattern.quote(texto);

        int columna;

        switch (filtro) {
            case "ID":
                columna = 0;
                break;
            case "Nombre":
                columna = 1;
                break;
            case "Apellido":
                columna = 2;
                break;
            case "DUI":
                columna = 3;
                break;
            case "Teléfono":
                columna = 4;
                break;
            case "Dirección":
                columna = 5;
                break;
            case "Estado":
                columna = 6;
                break;
            case "Todos":
            default:
                columna = -1;
                break;
        }

        if (columna == -1) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
        }
    }

    private void limpiarFiltroClientes() {
        form.txtBuscarClientes.setText("");
        form.cbFiltroClientes.setSelectedItem("Todos");
        form.tblClientes.setRowSorter(null);
    }

    private void exportarClientesCSV() {
        exportarTablaCSV(form.tblClientes, "clientes");
    }

    private void exportarTablaCSV(JTable tabla, String nombreArchivoSugerido) {
        if (tabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(form, "No hay datos para exportar.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo CSV");
        fileChooser.setSelectedFile(new File(nombreArchivoSugerido + ".csv"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo CSV", "csv"));

        int seleccion = fileChooser.showSaveDialog(form);

        if (seleccion != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = fileChooser.getSelectedFile();

        if (!archivo.getName().toLowerCase().endsWith(".csv")) {
            archivo = new File(archivo.getAbsolutePath() + ".csv");
        }

        try (FileWriter fw = new FileWriter(archivo)) {

            for (int i = 0; i < tabla.getColumnCount(); i++) {
                fw.write(escaparCSV(tabla.getColumnName(i)));

                if (i < tabla.getColumnCount() - 1) {
                    fw.write(",");
                }
            }

            fw.write("\n");

            for (int filaVista = 0; filaVista < tabla.getRowCount(); filaVista++) {
                int filaModelo = tabla.convertRowIndexToModel(filaVista);

                for (int col = 0; col < tabla.getColumnCount(); col++) {
                    Object valor = tabla.getModel().getValueAt(filaModelo, col);
                    fw.write(escaparCSV(valor == null ? "" : valor.toString()));

                    if (col < tabla.getColumnCount() - 1) {
                        fw.write(",");
                    }
                }

                fw.write("\n");
            }

            JOptionPane.showMessageDialog(form, "Archivo CSV exportado correctamente.");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(form, "Error al exportar CSV: " + e.getMessage());
        }
    }

    private String escaparCSV(String texto) {
        if (texto == null) {
            return "";
        }

        if (texto.contains(",") || texto.contains("\"") || texto.contains("\n")) {
            texto = texto.replace("\"", "\"\"");
            return "\"" + texto + "\"";
        }

        return texto;
    }

    private void limpiarCampos() {
        form.txtIdCliente.setText("");
        form.txtNombre.setText("");
        form.txtApellido.setText("");
        form.txtDui.setText("");
        form.txtTelefono.setText("");
        form.txtDireccion.setText("");
        form.chkEstado.setSelected(true);

        form.btnGuardar.setEnabled(true);
        form.btnActualizar.setEnabled(false);
        form.btnEliminar.setEnabled(false);
        form.btnVerDetalle.setEnabled(false);

        form.tblClientes.clearSelection();
    }

    private void agregarValidacionesDeTeclado() {
        form.txtNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                permitirSoloLetras(e);
            }
        });

        form.txtApellido.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                permitirSoloLetras(e);
            }
        });

        form.txtTelefono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                permitirSoloNumerosConLimite(e, form.txtTelefono.getText(), 8);
            }
        });

        form.txtDui.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                permitirSoloNumerosConLimite(e, form.txtDui.getText().replace("-", ""), 9);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                formatearDui();
            }
        });
    }

    private void permitirSoloLetras(KeyEvent e) {
        char c = e.getKeyChar();

        if (!Character.isLetter(c) && c != ' ' && c != '\b') {
            e.consume();
        }
    }

    private void permitirSoloNumerosConLimite(KeyEvent e, String textoActual, int limite) {
        char c = e.getKeyChar();

        if (!Character.isDigit(c) && c != '\b') {
            e.consume();
            return;
        }

        if (textoActual.length() >= limite && c != '\b') {
            e.consume();
        }
    }

    private void formatearDui() {
        String limpio = form.txtDui.getText().replaceAll("[^0-9]", "");

        if (limpio.length() > 9) {
            limpio = limpio.substring(0, 9);
        }

        if (limpio.length() > 8) {
            limpio = limpio.substring(0, 8) + "-" + limpio.substring(8);
        }

        form.txtDui.setText(limpio);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == form.btnGuardar) {
            guardarCliente();
        } else if (source == form.btnActualizar) {
            actualizarCliente();
        } else if (source == form.btnEliminar) {
            eliminarCliente();
        } else if (source == form.btnRefrescar) {
            limpiarCampos();
            cargarTabla();
        } else if (source == form.btnLimpiar) {
            limpiarCampos();
        } else if (source == form.btnVerDetalle) {
            verDetalle();
        } else if (source == form.btnBuscarClientes) {
            filtrarClientes();
        } else if (source == form.btnLimpiarFiltroClientes) {
            limpiarFiltroClientes();
        } else if (source == form.btnExportarClientes) {
            exportarClientesCSV();
        }
    }
}