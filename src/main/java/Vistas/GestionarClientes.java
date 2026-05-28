package Vistas;

import Modelos.Cliente;
import Controladores.CtrlGestionarClientes;
import Modelos.ClienteDAO;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class GestionarClientes extends javax.swing.JInternalFrame {

    private CtrlGestionarClientes controladorGestionar;

    public GestionarClientes() {
        initComponents();
        txtIdCliente.setVisible(false); // Ocultar ID
        
        // Estilizar cabecera de tabla
        tblClientes.getTableHeader().setBackground(Color.decode("#181D2E"));
        tblClientes.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tblClientes.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        tblClientes.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050")));
        ((DefaultTableCellRenderer) tblClientes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Efectos Hover a Botones
        btnActualizar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnActualizar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnActualizar.setBackground(Color.decode("#6C63FF")); }
        });
        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnEliminar.setBackground(Color.decode("#FF5B7A")); btnEliminar.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e) { btnEliminar.setBackground(Color.decode("#1E0A10")); btnEliminar.setForeground(Color.decode("#FF5B7A")); }
        });
        btnRefrescar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnRefrescar.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e)  { btnRefrescar.setBackground(Color.decode("#181D2E")); }
        });

        // Inicializar Controlador Original
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        controladorGestionar = new CtrlGestionarClientes(cliente, clienteDAO, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();
        panelEdit = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDui = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        chkEstado = new javax.swing.JCheckBox();
        txtIdCliente = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnVerDetalle = new javax.swing.JButton();
        txtBuscarClientes = new javax.swing.JTextField();
        btnBuscarClientes = new javax.swing.JButton();
        cbFiltroClientes = new javax.swing.JComboBox<>();
        btnLimpiarFiltroClientes = new javax.swing.JButton();
        btnExportarClientes = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestionar Clientes");
        setPreferredSize(new java.awt.Dimension(1050, 680));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 242, 255));
        lblTitulo.setText("✦  Directorio y Gestión de Clientes");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(30, 20, 400, 30);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));

        tblClientes.setBackground(new java.awt.Color(14, 18, 25));
        tblClientes.setForeground(new java.awt.Color(240, 242, 255));
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido", "DUI", "Teléfono", "Dirección", "Estado"
            }
        ));
        tblClientes.setGridColor(new java.awt.Color(26, 31, 48));
        tblClientes.setRowHeight(30);
        tblClientes.setSelectionBackground(new java.awt.Color(108, 99, 255));
        tblClientes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 70, 750, 380);

        btnActualizar.setBackground(new java.awt.Color(108, 99, 255));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorderPainted(false);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        getContentPane().add(btnActualizar);
        btnActualizar.setBounds(940, 70, 180, 45);

        btnEliminar.setBackground(new java.awt.Color(30, 10, 16));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 91, 122));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(930, 130, 180, 45);

        btnRefrescar.setBackground(new java.awt.Color(24, 29, 46));
        btnRefrescar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRefrescar.setForeground(new java.awt.Color(240, 242, 255));
        btnRefrescar.setText("Refrescar");
        btnRefrescar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        btnRefrescar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRefrescar.setFocusPainted(false);
        btnRefrescar.addActionListener(this::btnRefrescarActionPerformed);
        getContentPane().add(btnRefrescar);
        btnRefrescar.setBounds(930, 200, 180, 45);

        panelEdit.setBackground(new java.awt.Color(17, 21, 32));
        panelEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelEdit.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 163, 196));
        jLabel2.setText("NOMBRE:");
        panelEdit.add(jLabel2);
        jLabel2.setBounds(20, 20, 220, 16);

        txtNombre.setBackground(new java.awt.Color(24, 29, 46));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(240, 242, 255));
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtNombre.addActionListener(this::txtNombreActionPerformed);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        panelEdit.add(txtNombre);
        txtNombre.setBounds(20, 40, 220, 38);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(155, 163, 196));
        jLabel3.setText("APELLIDO:");
        panelEdit.add(jLabel3);
        jLabel3.setBounds(260, 20, 220, 16);

        txtApellido.setBackground(new java.awt.Color(24, 29, 46));
        txtApellido.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(240, 242, 255));
        txtApellido.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        panelEdit.add(txtApellido);
        txtApellido.setBounds(260, 40, 220, 38);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(155, 163, 196));
        jLabel4.setText("DUI:");
        panelEdit.add(jLabel4);
        jLabel4.setBounds(500, 20, 200, 16);

        txtDui.setBackground(new java.awt.Color(24, 29, 46));
        txtDui.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDui.setForeground(new java.awt.Color(240, 242, 255));
        txtDui.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtDui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDuiKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDuiKeyTyped(evt);
            }
        });
        panelEdit.add(txtDui);
        txtDui.setBounds(500, 40, 200, 38);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(155, 163, 196));
        jLabel5.setText("TELÉFONO:");
        panelEdit.add(jLabel5);
        jLabel5.setBounds(720, 20, 220, 16);

        txtTelefono.setBackground(new java.awt.Color(24, 29, 46));
        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(240, 242, 255));
        txtTelefono.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        panelEdit.add(txtTelefono);
        txtTelefono.setBounds(720, 40, 190, 38);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(155, 163, 196));
        jLabel6.setText("DIRECCIÓN:");
        panelEdit.add(jLabel6);
        jLabel6.setBounds(20, 85, 460, 16);

        txtDireccion.setBackground(new java.awt.Color(24, 29, 46));
        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(240, 242, 255));
        txtDireccion.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelEdit.add(txtDireccion);
        txtDireccion.setBounds(20, 105, 460, 38);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(155, 163, 196));
        jLabel1.setText("ESTADO:");
        panelEdit.add(jLabel1);
        jLabel1.setBounds(500, 85, 200, 16);

        chkEstado.setText("Activo");
        panelEdit.add(chkEstado);
        chkEstado.setBounds(520, 110, 64, 22);

        getContentPane().add(panelEdit);
        panelEdit.setBounds(30, 470, 960, 150);

        txtIdCliente.setBackground(new java.awt.Color(14, 18, 25));
        getContentPane().add(txtIdCliente);
        txtIdCliente.setBounds(0, 0, 0, 0);

        btnLimpiar.setText("Limpiar");
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(970, 300, 120, 28);

        btnGuardar.setText("Guardar");
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(1000, 260, 87, 28);

        btnVerDetalle.setText("Ver Detalle");
        getContentPane().add(btnVerDetalle);
        btnVerDetalle.setBounds(980, 340, 120, 28);

        txtBuscarClientes.addActionListener(this::txtBuscarClientesActionPerformed);
        getContentPane().add(txtBuscarClientes);
        txtBuscarClientes.setBounds(530, 20, 180, 28);

        btnBuscarClientes.setText("Buscar Clientes");
        getContentPane().add(btnBuscarClientes);
        btnBuscarClientes.setBounds(740, 20, 160, 28);

        cbFiltroClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "ID", "Nombre", "Apellido", "DUI", "Teléfono", "Dirección", "Estado" }));
        getContentPane().add(cbFiltroClientes);
        cbFiltroClientes.setBounds(810, 120, 100, 28);

        btnLimpiarFiltroClientes.setText("Limpiar Clientes");
        getContentPane().add(btnLimpiarFiltroClientes);
        btnLimpiarFiltroClientes.setBounds(790, 390, 160, 28);

        btnExportarClientes.setText("Exportar Clientes");
        btnExportarClientes.addActionListener(this::btnExportarClientesActionPerformed);
        getContentPane().add(btnExportarClientes);
        btnExportarClientes.setBounds(800, 430, 170, 28);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO lógica del controlador original
    }//GEN-LAST:event_formInternalFrameActivated

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // TODO lógica del controlador original
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO lógica del controlador original
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO lógica del controlador original
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        // TODO lógica del controlador original
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtDuiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuiKeyReleased
        // TODO lógica del controlador original
    }//GEN-LAST:event_txtDuiKeyReleased

    private void txtDuiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuiKeyTyped
        // TODO lógica del controlador original
    }//GEN-LAST:event_txtDuiKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO lógica del controlador original
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO lógica del controlador original
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO lógica del controlador original
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        // TODO lógica del controlador original
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void txtBuscarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarClientesActionPerformed

    private void btnExportarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExportarClientesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnBuscarClientes;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnExportarClientes;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnLimpiarFiltroClientes;
    public javax.swing.JButton btnRefrescar;
    public javax.swing.JButton btnVerDetalle;
    public javax.swing.JComboBox<String> cbFiltroClientes;
    public javax.swing.JCheckBox chkEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelEdit;
    public javax.swing.JTable tblClientes;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtBuscarClientes;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtDui;
    public javax.swing.JTextField txtIdCliente;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}