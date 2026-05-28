package Vistas;

import java.awt.Color;
import java.awt.event.MouseEvent;

public class NuevaCategoria extends javax.swing.JInternalFrame {

    public NuevaCategoria() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtnombrecategoria = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setTitle("Nueva Categoría");
        setPreferredSize(new java.awt.Dimension(460, 360));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(17, 21, 32));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 242, 255));
        jLabel2.setText("✦ Nueva Categoría");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 15, 300, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(155, 163, 196));
        jLabel3.setText("NOMBRE DE LA CATEGORÍA");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 65, 340, 16);

        txtnombrecategoria.setBackground(new java.awt.Color(24, 29, 46));
        txtnombrecategoria.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtnombrecategoria.setForeground(new java.awt.Color(240, 242, 255));
        txtnombrecategoria.setCaretColor(new java.awt.Color(108, 99, 255));
        txtnombrecategoria.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        jPanel1.add(txtnombrecategoria);
        txtnombrecategoria.setBounds(20, 85, 340, 38);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(155, 163, 196));
        jLabel1.setText("DESCRIPCIÓN");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 140, 340, 16);

        txtDescripcion.setBackground(new java.awt.Color(24, 29, 46));
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(240, 242, 255));
        txtDescripcion.setCaretColor(new java.awt.Color(108, 99, 255));
        txtDescripcion.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        jPanel1.add(txtDescripcion);
        txtDescripcion.setBounds(20, 160, 340, 38);

        btnGuardar.setBackground(new java.awt.Color(108, 99, 255));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Categoría");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
        });
        jPanel1.add(btnGuardar);
        btnGuardar.setBounds(20, 215, 340, 42);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(30, 20, 380, 280);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO: Lógica del controlador aquí
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        btnGuardar.setBackground(Color.decode("#5850DC"));
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        btnGuardar.setBackground(Color.decode("#6C63FF"));
    }//GEN-LAST:event_btnGuardarMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtnombrecategoria;
    // End of variables declaration//GEN-END:variables
}