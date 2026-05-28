package Vistas;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmRolesPermisos extends javax.swing.JInternalFrame {

    public FrmRolesPermisos() {
        initComponents();
        
        // Efecto Hover al botón Guardar
        btnGuardarRol.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnGuardarRol.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnGuardarRol.setBackground(Color.decode("#6C63FF")); }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtRol = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        checkGestionProductoProveedor = new javax.swing.JCheckBox();
        checkRegistroCompraVenta = new javax.swing.JCheckBox();
        checkGestionUsuarios = new javax.swing.JCheckBox();
        checkVerProductoProveedor = new javax.swing.JCheckBox();
        checkVerCompraVenta = new javax.swing.JCheckBox();
        checkVerUsuarios = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnGuardarRol = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setMaximizable(true);
        setTitle("Gestión de Roles");
        setPreferredSize(new java.awt.Dimension(500, 560));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 242, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("✦ Agregar Rol");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 20, 500, 30);

        panelDatos.setBackground(new java.awt.Color(17, 21, 32));
        panelDatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 163, 196));
        jLabel2.setText("NOMBRE DEL ROL");
        panelDatos.add(jLabel2);
        jLabel2.setBounds(20, 20, 390, 16);

        txtRol.setBackground(new java.awt.Color(24, 29, 46));
        txtRol.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtRol.setForeground(new java.awt.Color(240, 242, 255));
        txtRol.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelDatos.add(txtRol);
        txtRol.setBounds(20, 40, 390, 38);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(155, 163, 196));
        jLabel3.setText("PERMISOS ASIGNADOS");
        panelDatos.add(jLabel3);
        jLabel3.setBounds(20, 90, 390, 16);

        checkGestionProductoProveedor.setBackground(new java.awt.Color(17, 21, 32));
        checkGestionProductoProveedor.setForeground(new java.awt.Color(240, 242, 255));
        checkGestionProductoProveedor.setText("Gestión Productos y Proveedores");
        checkGestionProductoProveedor.setFocusPainted(false);
        checkGestionProductoProveedor.addActionListener(this::checkGestionProductoProveedorActionPerformed);
        panelDatos.add(checkGestionProductoProveedor);
        checkGestionProductoProveedor.setBounds(20, 110, 220, 20);

        checkRegistroCompraVenta.setBackground(new java.awt.Color(17, 21, 32));
        checkRegistroCompraVenta.setForeground(new java.awt.Color(240, 242, 255));
        checkRegistroCompraVenta.setText("Registro Compra y Ventas");
        checkRegistroCompraVenta.setFocusPainted(false);
        checkRegistroCompraVenta.addActionListener(this::checkRegistroCompraVentaActionPerformed);
        panelDatos.add(checkRegistroCompraVenta);
        checkRegistroCompraVenta.setBounds(20, 140, 220, 20);

        checkGestionUsuarios.setBackground(new java.awt.Color(17, 21, 32));
        checkGestionUsuarios.setForeground(new java.awt.Color(240, 242, 255));
        checkGestionUsuarios.setText("Gestión Usuarios");
        checkGestionUsuarios.setFocusPainted(false);
        checkGestionUsuarios.addActionListener(this::checkGestionUsuariosActionPerformed);
        panelDatos.add(checkGestionUsuarios);
        checkGestionUsuarios.setBounds(20, 170, 220, 20);

        checkVerProductoProveedor.setBackground(new java.awt.Color(17, 21, 32));
        checkVerProductoProveedor.setForeground(new java.awt.Color(155, 163, 196));
        checkVerProductoProveedor.setText("Ver Productos y Proveedores");
        checkVerProductoProveedor.setFocusPainted(false);
        panelDatos.add(checkVerProductoProveedor);
        checkVerProductoProveedor.setBounds(230, 110, 190, 20);

        checkVerCompraVenta.setBackground(new java.awt.Color(17, 21, 32));
        checkVerCompraVenta.setForeground(new java.awt.Color(155, 163, 196));
        checkVerCompraVenta.setText("Ver Compra y Ventas");
        checkVerCompraVenta.setFocusPainted(false);
        panelDatos.add(checkVerCompraVenta);
        checkVerCompraVenta.setBounds(230, 140, 190, 20);

        checkVerUsuarios.setBackground(new java.awt.Color(17, 21, 32));
        checkVerUsuarios.setForeground(new java.awt.Color(155, 163, 196));
        checkVerUsuarios.setText("Ver Usuarios");
        checkVerUsuarios.setFocusPainted(false);
        panelDatos.add(checkVerUsuarios);
        checkVerUsuarios.setBounds(230, 170, 190, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(155, 163, 196));
        jLabel4.setText("DESCRIPCIÓN");
        panelDatos.add(jLabel4);
        jLabel4.setBounds(20, 210, 390, 16);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));

        txtDescripcion.setBackground(new java.awt.Color(24, 29, 46));
        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(240, 242, 255));
        txtDescripcion.setRows(5);
        txtDescripcion.setCaretColor(new java.awt.Color(108, 99, 255));
        jScrollPane1.setViewportView(txtDescripcion);

        panelDatos.add(jScrollPane1);
        jScrollPane1.setBounds(20, 230, 390, 100);

        btnGuardarRol.setBackground(new java.awt.Color(108, 99, 255));
        btnGuardarRol.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarRol.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarRol.setText("Guardar Rol");
        btnGuardarRol.setBorderPainted(false);
        btnGuardarRol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarRol.setFocusPainted(false);
        panelDatos.add(btnGuardarRol);
        btnGuardarRol.setBounds(20, 350, 390, 45);

        getContentPane().add(panelDatos);
        panelDatos.setBounds(30, 70, 430, 420);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkGestionProductoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkGestionProductoProveedorActionPerformed
        if(checkGestionProductoProveedor.isSelected()){
            checkVerProductoProveedor.setSelected(true);
        }else if(!checkGestionProductoProveedor.isSelected()){
            checkVerProductoProveedor.setSelected(false);
        }
    }//GEN-LAST:event_checkGestionProductoProveedorActionPerformed

    private void checkRegistroCompraVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRegistroCompraVentaActionPerformed
        if(checkRegistroCompraVenta.isSelected()){
            checkVerCompraVenta.setSelected(true);
        }else if(!checkRegistroCompraVenta.isSelected()){
            checkVerCompraVenta.setSelected(false);
        }
    }//GEN-LAST:event_checkRegistroCompraVentaActionPerformed

    private void checkGestionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkGestionUsuariosActionPerformed
        if(checkGestionUsuarios.isSelected()){
            checkVerUsuarios.setSelected(true);
        }else if(!checkGestionUsuarios.isSelected()){
            checkVerUsuarios.setSelected(false);
        }
    }//GEN-LAST:event_checkGestionUsuariosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGuardarRol;
    public javax.swing.JCheckBox checkGestionProductoProveedor;
    public javax.swing.JCheckBox checkGestionUsuarios;
    public javax.swing.JCheckBox checkRegistroCompraVenta;
    public javax.swing.JCheckBox checkVerCompraVenta;
    public javax.swing.JCheckBox checkVerProductoProveedor;
    public javax.swing.JCheckBox checkVerUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelDatos;
    public javax.swing.JTextArea txtDescripcion;
    public javax.swing.JTextField txtRol;
    // End of variables declaration//GEN-END:variables
}