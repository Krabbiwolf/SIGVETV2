package Vistas;

import Controladores.CtrlNuevoCliente;
import Modelos.Cliente;
import Modelos.ClienteDAO;
import java.awt.Color;

public class NuevoCliente extends javax.swing.JInternalFrame {

    public NuevoCliente() {
        initComponents();
        
        // Efecto Hover para el botón principal
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardar.setBackground(Color.decode("#5850DC"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardar.setBackground(Color.decode("#6C63FF"));
            }
        });
        
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        CtrlNuevoCliente controlador = new CtrlNuevoCliente(cliente, clienteDAO, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDui = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setTitle("Nuevo Cliente");
        setPreferredSize(new java.awt.Dimension(620, 480));
        getContentPane().setLayout(null);

        card.setBackground(new java.awt.Color(17, 21, 32));
        card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        card.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(240, 242, 255));
        jLabel6.setText("✦ Registro de Nuevo Cliente");
        card.add(jLabel6);
        jLabel6.setBounds(20, 15, 300, 30);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(155, 163, 196));
        jLabel1.setText("NOMBRES");
        card.add(jLabel1);
        jLabel1.setBounds(20, 65, 240, 16);

        txtNombre.setBackground(new java.awt.Color(24, 29, 46));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(240, 242, 255));
        txtNombre.setCaretColor(new java.awt.Color(108, 99, 255));
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        card.add(txtNombre);
        txtNombre.setBounds(20, 85, 240, 38);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 163, 196));
        jLabel2.setText("APELLIDOS");
        card.add(jLabel2);
        jLabel2.setBounds(280, 65, 240, 16);

        txtApellido.setBackground(new java.awt.Color(24, 29, 46));
        txtApellido.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(240, 242, 255));
        txtApellido.setCaretColor(new java.awt.Color(108, 99, 255));
        txtApellido.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        card.add(txtApellido);
        txtApellido.setBounds(280, 85, 240, 38);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(155, 163, 196));
        jLabel3.setText("DUI");
        card.add(jLabel3);
        jLabel3.setBounds(20, 140, 240, 16);

        txtDui.setBackground(new java.awt.Color(24, 29, 46));
        txtDui.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDui.setForeground(new java.awt.Color(240, 242, 255));
        txtDui.setCaretColor(new java.awt.Color(108, 99, 255));
        txtDui.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtDui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDuiKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDuiKeyTyped(evt);
            }
        });
        card.add(txtDui);
        txtDui.setBounds(20, 160, 240, 38);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(155, 163, 196));
        jLabel4.setText("TELÉFONO");
        card.add(jLabel4);
        jLabel4.setBounds(280, 140, 240, 16);

        txtTelefono.setBackground(new java.awt.Color(24, 29, 46));
        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(240, 242, 255));
        txtTelefono.setCaretColor(new java.awt.Color(108, 99, 255));
        txtTelefono.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        card.add(txtTelefono);
        txtTelefono.setBounds(280, 160, 240, 38);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(155, 163, 196));
        jLabel5.setText("DIRECCIÓN COMPLETA");
        card.add(jLabel5);
        jLabel5.setBounds(20, 215, 500, 16);

        txtDireccion.setBackground(new java.awt.Color(24, 29, 46));
        txtDireccion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(240, 242, 255));
        txtDireccion.setCaretColor(new java.awt.Color(108, 99, 255));
        txtDireccion.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        card.add(txtDireccion);
        txtDireccion.setBounds(20, 235, 500, 38);

        btnGuardar.setBackground(new java.awt.Color(108, 99, 255));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Cliente");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        card.add(btnGuardar);
        btnGuardar.setBounds(20, 310, 500, 45);

        getContentPane().add(card);
        card.setBounds(30, 20, 540, 390);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDuiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuiKeyReleased
        String texto = txtDui.getText();
        texto = texto.replaceAll("[^0-9]", "");
        if (texto.length() > 9) { texto = texto.substring(0, 9); }
        if (texto.length() > 8) { texto = texto.substring(0, 8) + "-" + texto.substring(8); }
        txtDui.setText(texto);
    }//GEN-LAST:event_txtDuiKeyReleased

    private void txtDuiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDuiKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '\b') { evt.consume(); return; }
        String texto = txtDui.getText();
        if (texto.length() == 8) { txtDui.setText(texto + "-"); }
        if (texto.length() >= 10) { evt.consume(); }
    }//GEN-LAST:event_txtDuiKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '-' && c != '\b') { evt.consume(); }
        if (txtTelefono.getText().length() >= 9) { evt.consume(); }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here
    }//GEN-LAST:event_btnGuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGuardar;
    private javax.swing.JPanel card;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JTextField txtApellido;
    public javax.swing.JTextField txtDireccion;
    public javax.swing.JTextField txtDui;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}