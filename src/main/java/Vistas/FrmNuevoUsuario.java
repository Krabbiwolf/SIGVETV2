package Vistas;

import Modelos.Roles;
import Modelos.RolesDAO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrmNuevoUsuario extends javax.swing.JInternalFrame {
    private RolesDAO dao = new RolesDAO();
    
    public FrmNuevoUsuario() {
        initComponents();
        cargarRoles();
        aplicarEstiloMinimalistaPremium();
    }

    public void cargarRoles(){
        ArrayList<Roles> roles = dao.listarRoles();
        comboRoles.removeAllItems();
        for(Roles rol : roles){
            comboRoles.addItem(rol);
        }
    }

    private void aplicarEstiloMinimalistaPremium() {
        // === FONDO PRINCIPAL ===
        this.getContentPane().setBackground(Color.decode("#0A0C10"));

        // === TÍTULO ===
        jLabel1.setForeground(Color.decode("#F0F2FF"));
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel1.setText("✦  Registrar Usuario");

        // === LABELS ===
        javax.swing.JLabel[] labels = {jLabel2, jLabel3, jLabel4, jLabel5, jLabel7, jLabel8, jLabel9, jLabel10};
        String[] textos = {"APELLIDOS", "NOMBRES", "DUI", "USUARIO", "TELÉFONO", "CONTRASEÑA", "ROL", "ESTADO"};
        for (int i = 0; i < labels.length; i++) {
            labels[i].setForeground(Color.decode("#9BA3C4"));
            labels[i].setFont(new Font("Segoe UI", Font.BOLD, 10));
            labels[i].setText(textos[i]);
        }

        // === INPUTS ===
        javax.swing.JTextField[] campos = {txtNombres, txtApellidos, txtDUI, txtTelefono, txtUsuario, txtPassword, txtRutaImagen};
        for (javax.swing.JTextField c : campos) {
            c.setBackground(Color.decode("#181D2E"));
            c.setForeground(Color.decode("#F0F2FF"));
            c.setCaretColor(Color.decode("#6C63FF"));
            c.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#2A3050"), 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
            ));
            c.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent e) {
                    ((javax.swing.JTextField) e.getSource()).setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#6C63FF"), 1),
                        BorderFactory.createEmptyBorder(6, 10, 6, 10)
                    ));
                }
                public void focusLost(java.awt.event.FocusEvent e) {
                    ((javax.swing.JTextField) e.getSource()).setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#2A3050"), 1),
                        BorderFactory.createEmptyBorder(6, 10, 6, 10)
                    ));
                }
            });
        }

        // === COMBOBOXES ===
        javax.swing.JComboBox[] combos = {comboRoles, comboEstado};
        for (javax.swing.JComboBox c : combos) {
            c.setBackground(Color.decode("#181D2E"));
            c.setForeground(Color.decode("#F0F2FF"));
            c.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            c.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        }

        // === BOTÓN GUARDAR ===
        btnGuardarUsuario.setBackground(Color.decode("#00D4AA"));
        btnGuardarUsuario.setForeground(Color.decode("#0A0C10"));
        btnGuardarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardarUsuario.setFocusPainted(false);
        btnGuardarUsuario.setBorderPainted(false);
        btnGuardarUsuario.setOpaque(true);
        btnGuardarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardarUsuario.setText("Guardar Usuario");
        btnGuardarUsuario.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnGuardarUsuario.setBackground(Color.decode("#00B894")); }
            public void mouseExited(MouseEvent e)  { btnGuardarUsuario.setBackground(Color.decode("#00D4AA")); }
            public void mousePressed(MouseEvent e) { btnGuardarUsuario.setBackground(Color.decode("#009E80")); }
            public void mouseReleased(MouseEvent e){ btnGuardarUsuario.setBackground(Color.decode("#00D4AA")); }
        });

        // === BOTÓN AGREGAR IMAGEN ===
        btnAgregarImagen.setBackground(Color.decode("#1F2640"));
        btnAgregarImagen.setForeground(Color.decode("#F0F2FF"));
        btnAgregarImagen.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnAgregarImagen.setFocusPainted(false);
        btnAgregarImagen.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        btnAgregarImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregarImagen.setText("📁 Imagen");
        btnAgregarImagen.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnAgregarImagen.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e)  { btnAgregarImagen.setBackground(Color.decode("#1F2640")); }
        });

        // === PREVIEW IMAGEN ===
        txtImagen.setBorder(BorderFactory.createDashedBorder(Color.decode("#2A3050"), 3, 4));
        txtImagen.setBackground(Color.decode("#111520"));
        txtImagen.setOpaque(true);
        txtImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtImagen.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        txtImagen.setText("Sin foto");
        txtImagen.setForeground(Color.decode("#2A3050"));
        txtImagen.setFont(new Font("Segoe UI", Font.PLAIN, 11));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAgregarImagen = new javax.swing.JButton();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtDUI = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        btnGuardarUsuario = new javax.swing.JButton();
        txtRutaImagen = new javax.swing.JTextField();
        txtImagen = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboRoles = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();

        setClosable(true);
        setMaximizable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("Registrar Usuario");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Apellidos:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Nombres:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("DUI:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Usuario:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Telefono:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Contraseña:");

        btnAgregarImagen.setText("Imagen");
        btnAgregarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImagenActionPerformed(evt);
            }
        });

        btnGuardarUsuario.setText("Guardar");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Rol:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Estado:");

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUsuario)
                                .addComponent(txtTelefono)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                                .addComponent(txtPassword)
                                .addComponent(txtDUI)
                                    .addComponent(txtTelefono)
                                .addComponent(txtDUI))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregarImagen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRutaImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(181, 181, 181))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addComponent(jLabel3)
                    .addContainerGap(396, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDUI, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(comboRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRutaImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(80, 80, 80)
                    .addComponent(jLabel3)
                    .addContainerGap(593, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
        selector.setFileFilter(filtro);
        int resultado = selector.showOpenDialog(this);
        if(resultado == JFileChooser.APPROVE_OPTION){
            File archivo = selector.getSelectedFile();
            txtRutaImagen.setText(archivo.getAbsolutePath());
            ImageIcon icono = new ImageIcon(archivo.getAbsolutePath());
            Image imagenEscalada = icono.getImage().getScaledInstance(
                    txtImagen.getWidth(), txtImagen.getHeight(), Image.SCALE_SMOOTH);
            txtImagen.setIcon(new ImageIcon(imagenEscalada));
            txtImagen.setText("");
        }
    }//GEN-LAST:event_btnAgregarImagenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarImagen;
    public javax.swing.JButton btnGuardarUsuario;
    public javax.swing.JComboBox<String> comboEstado;
    public javax.swing.JComboBox<Roles> comboRoles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JTextField txtApellidos;
    public javax.swing.JTextField txtDUI;
    private javax.swing.JLabel txtImagen;
    public javax.swing.JTextField txtNombres;
    public javax.swing.JTextField txtPassword;
    public javax.swing.JTextField txtRutaImagen;
    public javax.swing.JTextField txtTelefono;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}