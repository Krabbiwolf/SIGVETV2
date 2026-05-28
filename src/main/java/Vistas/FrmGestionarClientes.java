package Vistas;

import Modelos.Cliente;
import Controladores.CtrlGestionarClientes;
import Modelos.ClienteDAO;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmGestionarClientes extends javax.swing.JInternalFrame {

    private CtrlGestionarClientes controladorGestionar;

    public FrmGestionarClientes() {
        initComponents();
        txtIdCliente.setVisible(false); // Ocultar ID

        // Inicializar Controlador Original
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        controladorGestionar = new CtrlGestionarClientes(cliente, clienteDAO, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        txtBuscarClientes = new javax.swing.JTextField();
        btnBuscarClientes = new javax.swing.JButton();
        cbFiltroClientes = new javax.swing.JComboBox<>();
        btnLimpiarFiltroClientes = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
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
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnVerDetalle = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();
        btnExportarClientes = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 244, 248));
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
        lblTitulo.setForeground(new java.awt.Color(45, 74, 138));
        lblTitulo.setText("Gestión de Clientes");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(30, 15, 400, 30);

        txtBuscarClientes.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscarClientes.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtBuscarClientes.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarClientes.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtBuscarClientes.setCaretColor(new java.awt.Color(45, 74, 138));
        txtBuscarClientes.addActionListener(this::txtBuscarClientesActionPerformed);
        getContentPane().add(txtBuscarClientes);
        txtBuscarClientes.setBounds(30, 60, 200, 35);

        btnBuscarClientes.setBackground(new java.awt.Color(45, 74, 138));
        btnBuscarClientes.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBuscarClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarClientes.setText("Buscar");
        btnBuscarClientes.setBorderPainted(false);
        btnBuscarClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscarClientes.setFocusPainted(false);
        getContentPane().add(btnBuscarClientes);
        btnBuscarClientes.setBounds(240, 60, 130, 35);

        cbFiltroClientes.setBackground(new java.awt.Color(255, 255, 255));
        cbFiltroClientes.setForeground(new java.awt.Color(51, 51, 51));
        cbFiltroClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "ID", "Nombre", "Apellido", "DUI", "Teléfono", "Dirección", "Estado" }));
        cbFiltroClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        getContentPane().add(cbFiltroClientes);
        cbFiltroClientes.setBounds(380, 60, 150, 35);

        btnLimpiarFiltroClientes.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiarFiltroClientes.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiarFiltroClientes.setForeground(new java.awt.Color(45, 74, 138));
        btnLimpiarFiltroClientes.setText("Limpiar Filtros");
        btnLimpiarFiltroClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiarFiltroClientes.setFocusPainted(false);
        getContentPane().add(btnLimpiarFiltroClientes);
        btnLimpiarFiltroClientes.setBounds(540, 60, 150, 35);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));

        tblClientes.setForeground(new java.awt.Color(51, 51, 51));
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido", "DUI", "Teléfono", "Dirección", "Estado"
            }
        ));
        tblClientes.setGridColor(new java.awt.Color(224, 224, 224));
        tblClientes.setRowHeight(32);
        tblClientes.setSelectionBackground(new java.awt.Color(197, 216, 245));
        tblClientes.setSelectionForeground(new java.awt.Color(13, 32, 96));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 110, 760, 340);

        panelEdit.setBackground(new java.awt.Color(240, 244, 248));
        panelEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelEdit.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(45, 74, 138));
        jLabel2.setText("NOMBRE:");
        panelEdit.add(jLabel2);
        jLabel2.setBounds(20, 15, 220, 16);

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtNombre.setCaretColor(new java.awt.Color(45, 74, 138));
        txtNombre.addActionListener(this::txtNombreActionPerformed);
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        panelEdit.add(txtNombre);
        txtNombre.setBounds(20, 35, 220, 38);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(45, 74, 138));
        jLabel3.setText("APELLIDO:");
        panelEdit.add(jLabel3);
        jLabel3.setBounds(260, 15, 220, 16);

        txtApellido.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(51, 51, 51));
        txtApellido.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtApellido.setCaretColor(new java.awt.Color(45, 74, 138));
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        panelEdit.add(txtApellido);
        txtApellido.setBounds(260, 35, 220, 38);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(45, 74, 138));
        jLabel4.setText("DUI:");
        panelEdit.add(jLabel4);
        jLabel4.setBounds(500, 15, 200, 16);

        txtDui.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDui.setForeground(new java.awt.Color(51, 51, 51));
        txtDui.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtDui.setCaretColor(new java.awt.Color(45, 74, 138));
        txtDui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDuiKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDuiKeyTyped(evt);
            }
        });
        panelEdit.add(txtDui);
        txtDui.setBounds(500, 35, 240, 38);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(45, 74, 138));
        jLabel5.setText("TELÉFONO:");
        panelEdit.add(jLabel5);
        jLabel5.setBounds(20, 80, 220, 16);

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(51, 51, 51));
        txtTelefono.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtTelefono.setCaretColor(new java.awt.Color(45, 74, 138));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        panelEdit.add(txtTelefono);
        txtTelefono.setBounds(20, 100, 220, 38);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(45, 74, 138));
        jLabel6.setText("DIRECCIÓN:");
        panelEdit.add(jLabel6);
        jLabel6.setBounds(260, 80, 350, 16);

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(51, 51, 51));
        txtDireccion.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtDireccion.setCaretColor(new java.awt.Color(45, 74, 138));
        panelEdit.add(txtDireccion);
        txtDireccion.setBounds(260, 100, 350, 38);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 74, 138));
        jLabel1.setText("ESTADO:");
        panelEdit.add(jLabel1);
        jLabel1.setBounds(630, 80, 100, 16);

        chkEstado.setText("Activo");
        chkEstado.setForeground(new java.awt.Color(51, 51, 51));
        panelEdit.add(chkEstado);
        chkEstado.setBounds(630, 100, 100, 38);

        getContentPane().add(panelEdit);
        panelEdit.setBounds(30, 470, 760, 150);
        getContentPane().add(txtIdCliente);
        txtIdCliente.setBounds(0, 0, 0, 0);

        btnGuardar.setBackground(new java.awt.Color(45, 74, 138));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar.setFocusPainted(false);
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(820, 110, 180, 40);

        btnActualizar.setBackground(new java.awt.Color(45, 74, 138));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorderPainted(false);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        getContentPane().add(btnActualizar);
        btnActualizar.setBounds(820, 160, 180, 40);

        btnVerDetalle.setBackground(new java.awt.Color(220, 230, 242));
        btnVerDetalle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVerDetalle.setForeground(new java.awt.Color(45, 74, 138));
        btnVerDetalle.setText("Ver Detalle");
        btnVerDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVerDetalle.setFocusPainted(false);
        getContentPane().add(btnVerDetalle);
        btnVerDetalle.setBounds(820, 260, 180, 40);

        btnRefrescar.setBackground(new java.awt.Color(220, 230, 242));
        btnRefrescar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRefrescar.setForeground(new java.awt.Color(45, 74, 138));
        btnRefrescar.setText("Refrescar");
        btnRefrescar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRefrescar.setFocusPainted(false);
        btnRefrescar.addActionListener(this::btnRefrescarActionPerformed);
        getContentPane().add(btnRefrescar);
        btnRefrescar.setBounds(820, 310, 180, 40);

        btnExportarClientes.setBackground(new java.awt.Color(40, 167, 69));
        btnExportarClientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnExportarClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarClientes.setText("Exportar a Excel");
        btnExportarClientes.setBorderPainted(false);
        btnExportarClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExportarClientes.setFocusPainted(false);
        btnExportarClientes.addActionListener(this::btnExportarClientesActionPerformed);
        getContentPane().add(btnExportarClientes);
        btnExportarClientes.setBounds(820, 410, 180, 40);

        btnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 51, 51));
        btnEliminar.setText("Eliminar");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.setFocusPainted(false);
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(820, 210, 180, 40);

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(45, 74, 138));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setFocusPainted(false);
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(820, 360, 180, 40);

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