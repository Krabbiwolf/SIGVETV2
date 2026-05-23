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

public class FrmNuevoUsuario extends javax.swing.JInternalFrame {

    private RolesDAO dao = new RolesDAO();
    private SwingWorker<ArrayList<Roles>, Void> currentRolesWorker;

    public FrmNuevoUsuario() {
        initComponents();
        txtRutaImagen.setVisible(false);
        
        cargarRolesAsync(); // 🔥 asíncrono

        // Hover btnGuardar
        btnGuardarUsuario.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnGuardarUsuario.setBackground(Color.decode("#00B38F")); }
            public void mouseExited(MouseEvent e)  { btnGuardarUsuario.setBackground(Color.decode("#00D4AA")); }
        });

        // Hover btnImagen
        btnAgregarImagen.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnAgregarImagen.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e)  { btnAgregarImagen.setBackground(Color.decode("#1F2640")); }
        });
    }

    // ================== Carga asíncrona de roles ==================
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
        txtPassword = new javax.swing.JTextField();
        lblRol = new javax.swing.JLabel();
        comboRoles = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        lblImagen = new javax.swing.JLabel();
        btnAgregarImagen = new javax.swing.JButton();
        lblImagenPreview = new javax.swing.JLabel();
        txtRutaImagen = new javax.swing.JTextField();
        btnGuardarUsuario = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setMaximizable(true);
        setTitle("Registrar Staff");
        setPreferredSize(new java.awt.Dimension(920, 640));
        getContentPane().setLayout(null);

        card.setBackground(new java.awt.Color(17, 21, 32));
        card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        card.setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 242, 255));
        lblTitulo.setText("✦  Alta de Nuevo Empleado");
        card.add(lblTitulo);
        lblTitulo.setBounds(30, 20, 400, 30);

        lblNombres.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNombres.setForeground(new java.awt.Color(155, 163, 196));
        lblNombres.setText("NOMBRES");
        card.add(lblNombres);
        lblNombres.setBounds(30, 70, 400, 18);

        txtNombres.setBackground(new java.awt.Color(24, 29, 46));
        txtNombres.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombres.setForeground(new java.awt.Color(240, 242, 255));
        txtNombres.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtNombres);
        txtNombres.setBounds(30, 88, 400, 40);

        lblApellidos.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblApellidos.setForeground(new java.awt.Color(155, 163, 196));
        lblApellidos.setText("APELLIDOS");
        card.add(lblApellidos);
        lblApellidos.setBounds(30, 140, 400, 18);

        txtApellidos.setBackground(new java.awt.Color(24, 29, 46));
        txtApellidos.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtApellidos.setForeground(new java.awt.Color(240, 242, 255));
        txtApellidos.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtApellidos);
        txtApellidos.setBounds(30, 158, 400, 40);

        lblDUI.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDUI.setForeground(new java.awt.Color(155, 163, 196));
        lblDUI.setText("DUI");
        card.add(lblDUI);
        lblDUI.setBounds(30, 210, 190, 18);

        txtDUI.setBackground(new java.awt.Color(24, 29, 46));
        txtDUI.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDUI.setForeground(new java.awt.Color(240, 242, 255));
        txtDUI.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtDUI);
        txtDUI.setBounds(30, 228, 190, 40);

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(155, 163, 196));
        lblTelefono.setText("TELÉFONO");
        card.add(lblTelefono);
        lblTelefono.setBounds(240, 210, 190, 18);

        txtTelefono.setBackground(new java.awt.Color(24, 29, 46));
        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(240, 242, 255));
        txtTelefono.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtTelefono);
        txtTelefono.setBounds(240, 228, 190, 40);

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(155, 163, 196));
        lblUsuario.setText("USERNAME");
        card.add(lblUsuario);
        lblUsuario.setBounds(30, 280, 190, 18);

        txtUsuario.setBackground(new java.awt.Color(24, 29, 46));
        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(240, 242, 255));
        txtUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtUsuario);
        txtUsuario.setBounds(30, 298, 190, 40);

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(155, 163, 196));
        lblPassword.setText("CONTRASEÑA");
        card.add(lblPassword);
        lblPassword.setBounds(240, 280, 190, 18);

        txtPassword.setBackground(new java.awt.Color(24, 29, 46));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(240, 242, 255));
        txtPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtPassword);
        txtPassword.setBounds(240, 298, 190, 40);

        lblRol.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblRol.setForeground(new java.awt.Color(155, 163, 196));
        lblRol.setText("ROL ASIGNADO");
        card.add(lblRol);
        lblRol.setBounds(30, 350, 190, 18);

        comboRoles.setBackground(new java.awt.Color(24, 29, 46));
        comboRoles.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        comboRoles.setForeground(new java.awt.Color(240, 242, 255));
        comboRoles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        card.add(comboRoles);
        comboRoles.setBounds(30, 368, 190, 40);

        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(155, 163, 196));
        lblEstado.setText("ESTADO");
        card.add(lblEstado);
        lblEstado.setBounds(240, 350, 190, 18);

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        comboEstado.setBackground(new java.awt.Color(24, 29, 46));
        comboEstado.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        comboEstado.setForeground(new java.awt.Color(240, 242, 255));
        comboEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        card.add(comboEstado);
        comboEstado.setBounds(240, 368, 190, 40);

        lblImagen.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblImagen.setForeground(new java.awt.Color(155, 163, 196));
        lblImagen.setText("FOTO DE PERFIL");
        card.add(lblImagen);
        lblImagen.setBounds(470, 70, 340, 18);

        btnAgregarImagen.setBackground(new java.awt.Color(31, 38, 64));
        btnAgregarImagen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregarImagen.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarImagen.setText("Examinar...");
        btnAgregarImagen.setBorderPainted(false);
        btnAgregarImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarImagen.addActionListener(this::btnAgregarImagenActionPerformed);
        card.add(btnAgregarImagen);
        btnAgregarImagen.setBounds(470, 88, 120, 40);

        lblImagenPreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImagenPreview.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        card.add(lblImagenPreview);
        lblImagenPreview.setBounds(470, 143, 300, 300);

        txtRutaImagen.setBackground(new java.awt.Color(24, 29, 46));
        card.add(txtRutaImagen);
        txtRutaImagen.setBounds(0, 0, 0, 0);

        btnGuardarUsuario.setBackground(new java.awt.Color(0, 212, 170));
        btnGuardarUsuario.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnGuardarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarUsuario.setText("Guardar Empleado");
        btnGuardarUsuario.setBorderPainted(false);
        btnGuardarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        card.add(btnGuardarUsuario);
        btnGuardarUsuario.setBounds(470, 490, 300, 45);

        getContentPane().add(card);
        card.setBounds(20, 20, 860, 560);

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
    private javax.swing.JButton btnAgregarImagen;
    public javax.swing.JButton btnGuardarUsuario;
    private javax.swing.JPanel card;
    public javax.swing.JComboBox<String> comboEstado;
    public javax.swing.JComboBox<Modelos.Roles> comboRoles;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblDUI;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblImagenPreview;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    public javax.swing.JTextField txtApellidos;
    public javax.swing.JTextField txtDUI;
    public javax.swing.JTextField txtNombres;
    public javax.swing.JTextField txtPassword;
    public javax.swing.JTextField txtRutaImagen;
    public javax.swing.JTextField txtTelefono;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}