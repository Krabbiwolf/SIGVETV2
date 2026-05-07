package Vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

public class FrmNuevoProveedor extends javax.swing.JInternalFrame {

    public FrmNuevoProveedor() {
        initComponents();
        this.setSize(new Dimension(420, 230));
        this.setTitle("Nuevo Proveedor");
        aplicarEstiloMinimalistaPremium();
    }
    
    private void aplicarEstiloMinimalistaPremium() {
        // === FONDO PRINCIPAL ===
        this.getContentPane().setBackground(Color.decode("#0A0C10"));

        // === TÍTULO PRINCIPAL ===
        jLabel1.setForeground(Color.decode("#F0F2FF"));
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 15));
        jLabel1.setText("✦  Nuevo Proveedor");

        // === LABELS ===
        jLabel2.setForeground(Color.decode("#9BA3C4"));
        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 11));
        jLabel2.setText("NOMBRE");

        jLabel3.setForeground(Color.decode("#9BA3C4"));
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 11));
        jLabel3.setText("TELÉFONO");

        // === INPUTS con efecto focus ===
        javax.swing.JTextField[] campos = {txtNombreProveedor, txtTelefonoProveedor};
        for (javax.swing.JTextField campo : campos) {
            campo.setBackground(Color.decode("#181D2E"));
            campo.setForeground(Color.decode("#F0F2FF"));
            campo.setCaretColor(Color.decode("#6C63FF"));
            campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#2A3050"), 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
            ));
            campo.addFocusListener(new java.awt.event.FocusAdapter() {
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

        // === BOTÓN GUARDAR ===
        btnGuardarProveedor.setBackground(Color.decode("#6C63FF"));
        btnGuardarProveedor.setForeground(Color.WHITE);
        btnGuardarProveedor.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardarProveedor.setFocusPainted(false);
        btnGuardarProveedor.setBorderPainted(false);
        btnGuardarProveedor.setOpaque(true);
        btnGuardarProveedor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardarProveedor.setText("Guardar Proveedor");
        btnGuardarProveedor.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnGuardarProveedor.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnGuardarProveedor.setBackground(Color.decode("#6C63FF")); }
            public void mousePressed(MouseEvent e) { btnGuardarProveedor.setBackground(Color.decode("#4A42C8")); }
            public void mouseReleased(MouseEvent e){ btnGuardarProveedor.setBackground(Color.decode("#6C63FF")); }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        txtTelefonoProveedor = new javax.swing.JTextField();
        btnGuardarProveedor = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nuevo Proveedor");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        jLabel2.setText("Nombre: ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel3.setText("Telefono:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));
        getContentPane().add(txtNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 220, -1));
        getContentPane().add(txtTelefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 150, -1));

        btnGuardarProveedor.setText("Guardar");
        getContentPane().add(btnGuardarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 150, 34));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGuardarProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JTextField txtNombreProveedor;
    public javax.swing.JTextField txtTelefonoProveedor;
    // End of variables declaration//GEN-END:variables

    public void txtNombreProveedor(String string) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void txtTelefonoProveedor(String string) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}