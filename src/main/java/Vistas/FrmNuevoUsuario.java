package Vistas;

import Modelos.Roles;
import Modelos.RolesDAO;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmNuevoUsuario extends javax.swing.JInternalFrame {

    private RolesDAO dao = new RolesDAO();
    private SwingWorker<ArrayList<Roles>, Void> currentRolesWorker;

    public FrmNuevoUsuario() {
        initComponents();
        
        btnEliminarUsuario.setBackground(new Color(230, 57, 70));
btnEliminarUsuario.setForeground(Color.WHITE);

btnEliminarUsuario.setOpaque(true);
btnEliminarUsuario.setContentAreaFilled(true);

btnEliminarUsuario.setFocusPainted(false);
btnEliminarUsuario.setBorderPainted(false);

        txtRutaImagen.setVisible(false);
        txtPassword.setText(""); // Limpiar campo de contraseña
        
        cargarRolesAsync(); 

        
    }

    private void cargarRolesAsync() {
        if (currentRolesWorker != null && !currentRolesWorker.isDone()) {
            currentRolesWorker.cancel(true);
        }
        currentRolesWorker = new SwingWorker<ArrayList<Roles>, Void>() {
            @Override
            protected ArrayList<Roles> doInBackground() throws Exception {
                return dao.listarRoles();
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Roles> roles = get();
                    comboRoles.removeAllItems();
                    for (Roles rol : roles) {
                        comboRoles.addItem(rol);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FrmNuevoUsuario.this, "Error al cargar roles: " + ex.getMessage());
                } finally {
                    currentRolesWorker = null;
                }
            }
        };
        currentRolesWorker.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        card = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        lblApellidos = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        lblDUI = new javax.swing.JLabel();
        txtDUI = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        lblRol = new javax.swing.JLabel();
        comboRoles = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        lblImagen = new javax.swing.JLabel();
        btnAgregarImagen = new javax.swing.JButton();
        lblImagenPreview = new javax.swing.JLabel();
        txtRutaImagen = new javax.swing.JTextField();
        btnGuardarUsuario = new javax.swing.JButton();
        lblTituloOLD = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        tableUsuarios = new javax.swing.JTable();
        lblRol1 = new javax.swing.JLabel();
        txtBuscador = new javax.swing.JTextField();
        btnVerInfo = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        btnActualizarUsuario = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        btnExportar = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 244, 248));
        setClosable(true);
        setMaximizable(true);
        setTitle("Registrar Staff");
        setAutoscrolls(true);
        setPreferredSize(new java.awt.Dimension(920, 640));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 1000));

        card.setBackground(new java.awt.Color(240, 244, 248));
        card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        card.setAutoscrolls(true);
        card.setPreferredSize(new java.awt.Dimension(800, 1100));
        card.setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(45, 74, 138));
        lblTitulo.setText("Nuevo Empleado");
        card.add(lblTitulo);
        lblTitulo.setBounds(30, 20, 400, 30);

        lblNombres.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNombres.setForeground(new java.awt.Color(45, 74, 138));
        lblNombres.setText("NOMBRES");
        card.add(lblNombres);
        lblNombres.setBounds(30, 70, 400, 18);

        txtNombres.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(51, 51, 51));
        txtNombres.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtNombres);
        txtNombres.setBounds(30, 90, 400, 38);

        lblApellidos.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblApellidos.setForeground(new java.awt.Color(45, 74, 138));
        lblApellidos.setText("APELLIDOS");
        card.add(lblApellidos);
        lblApellidos.setBounds(30, 140, 400, 18);

        txtApellidos.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtApellidos.setForeground(new java.awt.Color(51, 51, 51));
        txtApellidos.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtApellidos);
        txtApellidos.setBounds(30, 160, 400, 38);

        lblDUI.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDUI.setForeground(new java.awt.Color(45, 74, 138));
        lblDUI.setText("DUI");
        card.add(lblDUI);
        lblDUI.setBounds(30, 210, 190, 18);

        txtDUI.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDUI.setForeground(new java.awt.Color(51, 51, 51));
        txtDUI.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtDUI);
        txtDUI.setBounds(30, 230, 190, 38);

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(45, 74, 138));
        lblTelefono.setText("TELÉFONO");
        card.add(lblTelefono);
        lblTelefono.setBounds(240, 210, 190, 18);

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(51, 51, 51));
        txtTelefono.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtTelefono);
        txtTelefono.setBounds(240, 230, 190, 38);

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(45, 74, 138));
        lblUsuario.setText("USERNAME");
        card.add(lblUsuario);
        lblUsuario.setBounds(30, 280, 190, 18);

        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(51, 51, 51));
        txtUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtUsuario);
        txtUsuario.setBounds(30, 300, 190, 38);

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(45, 74, 138));
        lblPassword.setText("CONTRASEÑA");
        card.add(lblPassword);
        lblPassword.setBounds(240, 280, 190, 18);

        lblRol.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRol.setForeground(new java.awt.Color(45, 74, 138));
        lblRol.setText("BUSCAR");
        card.add(lblRol);
        lblRol.setBounds(30, 620, 100, 18);

        comboRoles.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        comboRoles.setForeground(new java.awt.Color(51, 51, 51));
        comboRoles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        card.add(comboRoles);
        comboRoles.setBounds(30, 370, 190, 38);

        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(45, 74, 138));
        lblEstado.setText("ESTADO");
        card.add(lblEstado);
        lblEstado.setBounds(240, 350, 190, 18);

        comboEstado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        comboEstado.setForeground(new java.awt.Color(51, 51, 51));
        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        comboEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        card.add(comboEstado);
        comboEstado.setBounds(240, 370, 190, 38);

        lblImagen.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblImagen.setForeground(new java.awt.Color(45, 74, 138));
        lblImagen.setText("FOTO DE PERFIL");
        card.add(lblImagen);
        lblImagen.setBounds(470, 70, 340, 18);

        btnAgregarImagen.setBackground(new java.awt.Color(220, 230, 242));
        btnAgregarImagen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregarImagen.setForeground(new java.awt.Color(45, 74, 138));
        btnAgregarImagen.setText("Examinar...");
        btnAgregarImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarImagen.setFocusPainted(false);
        btnAgregarImagen.addActionListener(this::btnAgregarImagenActionPerformed);
        card.add(btnAgregarImagen);
        btnAgregarImagen.setBounds(470, 90, 120, 38);

        lblImagenPreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagenPreview.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        card.add(lblImagenPreview);
        lblImagenPreview.setBounds(470, 140, 300, 300);
        card.add(txtRutaImagen);
        txtRutaImagen.setBounds(0, 0, 0, 0);

        btnGuardarUsuario.setBackground(new java.awt.Color(45, 74, 138));
        btnGuardarUsuario.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnGuardarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarUsuario.setText("Guardar Empleado");
        btnGuardarUsuario.setBorderPainted(false);
        btnGuardarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardarUsuario.setFocusPainted(false);
        card.add(btnGuardarUsuario);
        btnGuardarUsuario.setBounds(470, 480, 300, 42);

        lblTituloOLD.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTituloOLD.setForeground(new java.awt.Color(45, 74, 138));
        lblTituloOLD.setText("Gestión de Usuarios");
        card.add(lblTituloOLD);
        lblTituloOLD.setBounds(30, 580, 400, 30);

        scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));

        tableUsuarios.setForeground(new java.awt.Color(51, 51, 51));
        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido", "DUI", "Telefono", "Username", "Contraseña", "Imagen", "Estado", "Rol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUsuarios.setGridColor(new java.awt.Color(224, 224, 224));
        tableUsuarios.setRowHeight(32);
        tableUsuarios.setSelectionBackground(new java.awt.Color(197, 216, 245));
        tableUsuarios.setSelectionForeground(new java.awt.Color(13, 32, 96));
        scrollPane.setViewportView(tableUsuarios);

        card.add(scrollPane);
        scrollPane.setBounds(30, 690, 760, 270);

        lblRol1.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRol1.setForeground(new java.awt.Color(45, 74, 138));
        lblRol1.setText("ROL ASIGNADO");
        card.add(lblRol1);
        lblRol1.setBounds(30, 350, 190, 18);

        txtBuscador.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtBuscador.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscador.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtBuscador);
        txtBuscador.setBounds(30, 640, 760, 40);

        btnVerInfo.setBackground(new java.awt.Color(220, 230, 242));
        btnVerInfo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnVerInfo.setForeground(new java.awt.Color(45, 74, 138));
        btnVerInfo.setText("Ver información");
        btnVerInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVerInfo.setFocusPainted(false);
        card.add(btnVerInfo);
        btnVerInfo.setBounds(160, 975, 140, 40);

        btnEliminarUsuario.setBackground(new java.awt.Color(230, 57, 70));
        btnEliminarUsuario.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEliminarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarUsuario.setText("Eliminar");
        btnEliminarUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 57, 70)));
        btnEliminarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarUsuario.setFocusPainted(false);
        card.add(btnEliminarUsuario);
        btnEliminarUsuario.setBounds(30, 975, 120, 40);

        btnActualizarUsuario.setBackground(new java.awt.Color(45, 74, 138));
        btnActualizarUsuario.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarUsuario.setText("Actualizar");
        btnActualizarUsuario.setBorderPainted(false);
        btnActualizarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizarUsuario.setFocusPainted(false);
        card.add(btnActualizarUsuario);
        btnActualizarUsuario.setBounds(160, 430, 120, 40);

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(45, 74, 138));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setFocusPainted(false);
        card.add(btnLimpiar);
        btnLimpiar.setBounds(30, 430, 120, 40);

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(51, 51, 51));
        txtPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtPassword);
        txtPassword.setBounds(240, 300, 190, 38);

        btnExportar.setBackground(new java.awt.Color(40, 167, 69));
        btnExportar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnExportar.setText("Exportar");
        btnExportar.setBorderPainted(false);
        btnExportar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExportar.setFocusPainted(false);
        card.add(btnExportar);
        btnExportar.setBounds(310, 975, 120, 40);

        jScrollPane1.setViewportView(card);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
        selector.setFileFilter(filtro);
        if(selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File archivo = selector.getSelectedFile();
            txtRutaImagen.setText(archivo.getAbsolutePath());
            ImageIcon icono = new ImageIcon(archivo.getAbsolutePath());
            Image img = icono.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            lblImagenPreview.setIcon(new ImageIcon(img));
            lblImagenPreview.setText("");
        }
    }//GEN-LAST:event_btnAgregarImagenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizarUsuario;
    private javax.swing.JButton btnAgregarImagen;
    public javax.swing.JButton btnEliminarUsuario;
    public javax.swing.JButton btnExportar;
    public javax.swing.JButton btnGuardarUsuario;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnVerInfo;
    private javax.swing.JPanel card;
    public javax.swing.JComboBox<String> comboEstado;
    public javax.swing.JComboBox<Modelos.Roles> comboRoles;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblDUI;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagenPreview;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblRol1;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloOLD;
    private javax.swing.JLabel lblUsuario;
    public static javax.swing.JScrollPane scrollPane;
    public static javax.swing.JTable tableUsuarios;
    public javax.swing.JTextField txtApellidos;
    public javax.swing.JTextField txtBuscador;
    public javax.swing.JTextField txtDUI;
    public javax.swing.JTextField txtNombres;
    public javax.swing.JPasswordField txtPassword;
    public javax.swing.JTextField txtRutaImagen;
    public javax.swing.JTextField txtTelefono;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}