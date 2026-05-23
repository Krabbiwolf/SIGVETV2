package Vistas;

import java.awt.Color;

public class FrmNuevoProveedor extends javax.swing.JInternalFrame {

    public FrmNuevoProveedor() {
        initComponents();
        
        // Efecto Hover para el botón
        btnGuardarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarProveedor.setBackground(Color.decode("#5850DC"));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarProveedor.setBackground(Color.decode("#6C63FF"));
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefonoProveedor = new javax.swing.JTextField();
        btnGuardarProveedor = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Nuevo Proveedor");
        setPreferredSize(new java.awt.Dimension(480, 380));
        getContentPane().setLayout(null);

        card.setBackground(new java.awt.Color(17, 21, 32));
        card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        card.setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 242, 255));
        lblTitulo.setText("✦  Registro de Proveedor");
        card.add(lblTitulo);
        lblTitulo.setBounds(25, 20, 300, 30);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(155, 163, 196));
        lblNombre.setText("NOMBRE DEL PROVEEDOR");
        card.add(lblNombre);
        lblNombre.setBounds(25, 70, 370, 16);

        txtNombreProveedor.setBackground(new java.awt.Color(24, 29, 46));
        txtNombreProveedor.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombreProveedor.setForeground(new java.awt.Color(240, 242, 255));
        txtNombreProveedor.setCaretColor(new java.awt.Color(108, 99, 255));
        txtNombreProveedor.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtNombreProveedor);
        txtNombreProveedor.setBounds(25, 90, 370, 38);

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(155, 163, 196));
        lblTelefono.setText("TELÉFONO DE CONTACTO");
        card.add(lblTelefono);
        lblTelefono.setBounds(25, 143, 370, 16);

        txtTelefonoProveedor.setBackground(new java.awt.Color(24, 29, 46));
        txtTelefonoProveedor.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelefonoProveedor.setForeground(new java.awt.Color(240, 242, 255));
        txtTelefonoProveedor.setCaretColor(new java.awt.Color(108, 99, 255));
        txtTelefonoProveedor.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtTelefonoProveedor);
        txtTelefonoProveedor.setBounds(25, 163, 370, 38);

        btnGuardarProveedor.setBackground(new java.awt.Color(108, 99, 255));
        btnGuardarProveedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarProveedor.setText("Guardar Proveedor");
        btnGuardarProveedor.setBorderPainted(false);
        btnGuardarProveedor.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarProveedor.setFocusPainted(false);
        card.add(btnGuardarProveedor);
        btnGuardarProveedor.setBounds(25, 226, 370, 45);

        getContentPane().add(card);
        card.setBounds(24, 20, 420, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JPanel card;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    public javax.swing.JTextField txtNombreProveedor;
    public javax.swing.JTextField txtTelefonoProveedor;
    // End of variables declaration//GEN-END:variables
}