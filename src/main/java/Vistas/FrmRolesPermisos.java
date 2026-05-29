package Vistas;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmRolesPermisos extends javax.swing.JInternalFrame {

    public FrmRolesPermisos() {
        initComponents();
        
       
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDatos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtRol = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        checkGestionProductos = new javax.swing.JCheckBox();
        checkRegistroCompraVenta = new javax.swing.JCheckBox();
        checkGestionUsuarios = new javax.swing.JCheckBox();
        checkVerProductos = new javax.swing.JCheckBox();
        checkVerVentas = new javax.swing.JCheckBox();
        checkVerUsuarios = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        btnGuardarRol = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        checkExportarProductos = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        checkExportarUsuarios = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        checkExportarVentas = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        checkVerCompras = new javax.swing.JCheckBox();
        checkGestionCompra = new javax.swing.JCheckBox();
        checkExportarCompras = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        checkVerLotes = new javax.swing.JCheckBox();
        checkGestionLotes = new javax.swing.JCheckBox();
        checkExportarLotes = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        checkVerTerceros = new javax.swing.JCheckBox();
        checkGestionTerceros = new javax.swing.JCheckBox();
        checkExportarTerceros = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        checkVerReportes = new javax.swing.JCheckBox();
        checkGestionReportes = new javax.swing.JCheckBox();
        checkExportarReportes = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(240, 244, 248));
        setClosable(true);
        setMaximizable(true);
        setTitle("Gestión de Roles");
        setPreferredSize(new java.awt.Dimension(500, 600));
        getContentPane().setLayout(null);

        panelDatos.setBackground(new java.awt.Color(240, 244, 248));
        panelDatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelDatos.setPreferredSize(new java.awt.Dimension(490, 600));
        panelDatos.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(45, 74, 138));
        jLabel2.setText("NOMBRE DEL ROL");
        panelDatos.add(jLabel2);
        jLabel2.setBounds(20, 50, 390, 16);

        txtRol.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtRol.setForeground(new java.awt.Color(51, 51, 51));
        txtRol.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelDatos.add(txtRol);
        txtRol.setBounds(20, 70, 390, 38);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(45, 74, 138));
        jLabel3.setText("GESTIÓN REPORTES");
        panelDatos.add(jLabel3);
        jLabel3.setBounds(30, 330, 120, 20);

        checkGestionProductos.setForeground(new java.awt.Color(51, 51, 51));
        checkGestionProductos.setText("Edición");
        checkGestionProductos.setFocusPainted(false);
        checkGestionProductos.addActionListener(this::checkGestionProductosActionPerformed);
        panelDatos.add(checkGestionProductos);
        checkGestionProductos.setBounds(220, 180, 70, 20);

        checkRegistroCompraVenta.setForeground(new java.awt.Color(51, 51, 51));
        checkRegistroCompraVenta.setText("Edición");
        checkRegistroCompraVenta.setFocusPainted(false);
        checkRegistroCompraVenta.addActionListener(this::checkRegistroCompraVentaActionPerformed);
        panelDatos.add(checkRegistroCompraVenta);
        checkRegistroCompraVenta.setBounds(220, 210, 70, 20);

        checkGestionUsuarios.setForeground(new java.awt.Color(51, 51, 51));
        checkGestionUsuarios.setText("Edición");
        checkGestionUsuarios.setFocusPainted(false);
        checkGestionUsuarios.addActionListener(this::checkGestionUsuariosActionPerformed);
        panelDatos.add(checkGestionUsuarios);
        checkGestionUsuarios.setBounds(220, 150, 70, 20);

        checkVerProductos.setForeground(new java.awt.Color(51, 51, 51));
        checkVerProductos.setText("Lectura");
        checkVerProductos.setFocusPainted(false);
        panelDatos.add(checkVerProductos);
        checkVerProductos.setBounds(150, 180, 70, 20);

        checkVerVentas.setForeground(new java.awt.Color(51, 51, 51));
        checkVerVentas.setText("Lectura");
        checkVerVentas.setFocusPainted(false);
        panelDatos.add(checkVerVentas);
        checkVerVentas.setBounds(150, 210, 70, 20);

        checkVerUsuarios.setForeground(new java.awt.Color(51, 51, 51));
        checkVerUsuarios.setText("Lectura");
        checkVerUsuarios.setFocusPainted(false);
        panelDatos.add(checkVerUsuarios);
        checkVerUsuarios.setBounds(150, 150, 70, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(45, 74, 138));
        jLabel4.setText("DESCRIPCIÓN");
        panelDatos.add(jLabel4);
        jLabel4.setBounds(20, 370, 390, 16);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        txtDescripcion.setRows(5);
        txtDescripcion.setCaretColor(new java.awt.Color(45, 74, 138));
        txtDescripcion.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10));
        jScrollPane1.setViewportView(txtDescripcion);

        panelDatos.add(jScrollPane1);
        jScrollPane1.setBounds(20, 390, 390, 100);

        btnGuardarRol.setBackground(new java.awt.Color(45, 74, 138));
        btnGuardarRol.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarRol.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarRol.setText("Guardar Rol");
        btnGuardarRol.setBorderPainted(false);
        btnGuardarRol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarRol.setFocusPainted(false);
        panelDatos.add(btnGuardarRol);
        btnGuardarRol.setBounds(20, 510, 390, 45);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 74, 138));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agregar Rol");
        panelDatos.add(jLabel1);
        jLabel1.setBounds(-20, 10, 500, 30);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(45, 74, 138));
        jLabel5.setText("PERMISOS ASIGNADOS");
        panelDatos.add(jLabel5);
        jLabel5.setBounds(20, 120, 390, 16);

        checkExportarProductos.setForeground(new java.awt.Color(51, 51, 51));
        checkExportarProductos.setText("Exportar");
        checkExportarProductos.setFocusPainted(false);
        checkExportarProductos.addActionListener(this::checkExportarProductosActionPerformed);
        panelDatos.add(checkExportarProductos);
        checkExportarProductos.setBounds(290, 180, 70, 20);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(45, 74, 138));
        jLabel6.setText("GESTIÓN USUARIOS");
        panelDatos.add(jLabel6);
        jLabel6.setBounds(30, 150, 120, 20);

        checkExportarUsuarios.setForeground(new java.awt.Color(51, 51, 51));
        checkExportarUsuarios.setText("Exportar");
        checkExportarUsuarios.setFocusPainted(false);
        checkExportarUsuarios.addActionListener(this::checkExportarUsuariosActionPerformed);
        panelDatos.add(checkExportarUsuarios);
        checkExportarUsuarios.setBounds(290, 150, 70, 20);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(45, 74, 138));
        jLabel7.setText("GESTIÓN PRODUCTOS");
        panelDatos.add(jLabel7);
        jLabel7.setBounds(30, 180, 120, 20);

        checkExportarVentas.setForeground(new java.awt.Color(51, 51, 51));
        checkExportarVentas.setText("Exportar");
        checkExportarVentas.setFocusPainted(false);
        checkExportarVentas.addActionListener(this::checkExportarVentasActionPerformed);
        panelDatos.add(checkExportarVentas);
        checkExportarVentas.setBounds(290, 210, 70, 20);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(45, 74, 138));
        jLabel8.setText("GESTIÓN VENTAS");
        panelDatos.add(jLabel8);
        jLabel8.setBounds(30, 210, 120, 20);

        checkVerCompras.setForeground(new java.awt.Color(51, 51, 51));
        checkVerCompras.setText("Lectura");
        checkVerCompras.setFocusPainted(false);
        panelDatos.add(checkVerCompras);
        checkVerCompras.setBounds(150, 240, 70, 20);

        checkGestionCompra.setForeground(new java.awt.Color(51, 51, 51));
        checkGestionCompra.setText("Edición");
        checkGestionCompra.setFocusPainted(false);
        checkGestionCompra.addActionListener(this::checkGestionCompraActionPerformed);
        panelDatos.add(checkGestionCompra);
        checkGestionCompra.setBounds(220, 240, 70, 20);

        checkExportarCompras.setForeground(new java.awt.Color(51, 51, 51));
        checkExportarCompras.setText("Exportar");
        checkExportarCompras.setFocusPainted(false);
        checkExportarCompras.addActionListener(this::checkExportarComprasActionPerformed);
        panelDatos.add(checkExportarCompras);
        checkExportarCompras.setBounds(290, 240, 70, 20);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(45, 74, 138));
        jLabel9.setText("GESTIÓN COMPRAS");
        panelDatos.add(jLabel9);
        jLabel9.setBounds(30, 240, 120, 20);

        checkVerLotes.setForeground(new java.awt.Color(51, 51, 51));
        checkVerLotes.setText("Lectura");
        checkVerLotes.setFocusPainted(false);
        panelDatos.add(checkVerLotes);
        checkVerLotes.setBounds(150, 270, 70, 20);

        checkGestionLotes.setForeground(new java.awt.Color(51, 51, 51));
        checkGestionLotes.setText("Edición");
        checkGestionLotes.setFocusPainted(false);
        checkGestionLotes.addActionListener(this::checkGestionLotesActionPerformed);
        panelDatos.add(checkGestionLotes);
        checkGestionLotes.setBounds(220, 270, 70, 20);

        checkExportarLotes.setForeground(new java.awt.Color(51, 51, 51));
        checkExportarLotes.setText("Exportar");
        checkExportarLotes.setFocusPainted(false);
        checkExportarLotes.addActionListener(this::checkExportarLotesActionPerformed);
        panelDatos.add(checkExportarLotes);
        checkExportarLotes.setBounds(290, 270, 70, 20);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(45, 74, 138));
        jLabel10.setText("GESTIÓN LOTES");
        panelDatos.add(jLabel10);
        jLabel10.setBounds(30, 270, 120, 20);

        checkVerTerceros.setForeground(new java.awt.Color(51, 51, 51));
        checkVerTerceros.setText("Lectura");
        checkVerTerceros.setFocusPainted(false);
        panelDatos.add(checkVerTerceros);
        checkVerTerceros.setBounds(150, 300, 70, 20);

        checkGestionTerceros.setForeground(new java.awt.Color(51, 51, 51));
        checkGestionTerceros.setText("Edición");
        checkGestionTerceros.setFocusPainted(false);
        checkGestionTerceros.addActionListener(this::checkGestionTercerosActionPerformed);
        panelDatos.add(checkGestionTerceros);
        checkGestionTerceros.setBounds(220, 300, 70, 20);

        checkExportarTerceros.setForeground(new java.awt.Color(51, 51, 51));
        checkExportarTerceros.setText("Exportar");
        checkExportarTerceros.setFocusPainted(false);
        checkExportarTerceros.addActionListener(this::checkExportarTercerosActionPerformed);
        panelDatos.add(checkExportarTerceros);
        checkExportarTerceros.setBounds(290, 300, 70, 20);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(45, 74, 138));
        jLabel11.setText("GESTIÓN TERCEROS");
        panelDatos.add(jLabel11);
        jLabel11.setBounds(30, 300, 120, 20);

        checkVerReportes.setForeground(new java.awt.Color(51, 51, 51));
        checkVerReportes.setText("Lectura");
        checkVerReportes.setFocusPainted(false);
        panelDatos.add(checkVerReportes);
        checkVerReportes.setBounds(150, 330, 70, 20);

        checkGestionReportes.setForeground(new java.awt.Color(51, 51, 51));
        checkGestionReportes.setText("Edición");
        checkGestionReportes.setFocusPainted(false);
        checkGestionReportes.addActionListener(this::checkGestionReportesActionPerformed);
        panelDatos.add(checkGestionReportes);
        checkGestionReportes.setBounds(220, 330, 70, 20);

        checkExportarReportes.setForeground(new java.awt.Color(51, 51, 51));
        checkExportarReportes.setText("Exportar");
        checkExportarReportes.setFocusPainted(false);
        checkExportarReportes.addActionListener(this::checkExportarReportesActionPerformed);
        panelDatos.add(checkExportarReportes);
        checkExportarReportes.setBounds(290, 330, 70, 20);

        getContentPane().add(panelDatos);
        panelDatos.setBounds(0, 0, 490, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkGestionProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkGestionProductosActionPerformed
        if(checkGestionProductos.isSelected()){
            checkVerProductos.setSelected(true);
        }else if(!checkGestionProductos.isSelected()){
            checkVerProductos.setSelected(false);
        }
    }//GEN-LAST:event_checkGestionProductosActionPerformed

    private void checkRegistroCompraVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRegistroCompraVentaActionPerformed
        if(checkRegistroCompraVenta.isSelected()){
            checkVerVentas.setSelected(true);
        }else if(!checkRegistroCompraVenta.isSelected()){
            checkVerVentas.setSelected(false);
        }
    }//GEN-LAST:event_checkRegistroCompraVentaActionPerformed

    private void checkGestionUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkGestionUsuariosActionPerformed
        if(checkGestionUsuarios.isSelected()){
            checkVerUsuarios.setSelected(true);
        }else if(!checkGestionUsuarios.isSelected()){
            checkVerUsuarios.setSelected(false);
        }
    }//GEN-LAST:event_checkGestionUsuariosActionPerformed

    private void checkExportarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkExportarProductosActionPerformed
    }//GEN-LAST:event_checkExportarProductosActionPerformed

    private void checkExportarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkExportarUsuariosActionPerformed
    }//GEN-LAST:event_checkExportarUsuariosActionPerformed

    private void checkExportarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkExportarVentasActionPerformed
    }//GEN-LAST:event_checkExportarVentasActionPerformed

    private void checkGestionCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkGestionCompraActionPerformed
        if(checkGestionCompra.isSelected()){
            checkVerCompras.setSelected(true);
        }else if(!checkGestionCompra.isSelected()){
            checkVerCompras.setSelected(false);
        }
    }//GEN-LAST:event_checkGestionCompraActionPerformed

    private void checkExportarComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkExportarComprasActionPerformed
    }//GEN-LAST:event_checkExportarComprasActionPerformed

    private void checkGestionLotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkGestionLotesActionPerformed
        if(checkGestionLotes.isSelected()){
            checkVerLotes.setSelected(true);
        }else if(!checkGestionLotes.isSelected()){
            checkVerLotes.setSelected(false);
        }
    }//GEN-LAST:event_checkGestionLotesActionPerformed

    private void checkExportarLotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkExportarLotesActionPerformed
    }//GEN-LAST:event_checkExportarLotesActionPerformed

    private void checkGestionTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkGestionTercerosActionPerformed
        if(checkGestionTerceros.isSelected()){
            checkVerTerceros.setSelected(true);
        }else if(!checkGestionTerceros.isSelected()){
            checkVerTerceros.setSelected(false);
        }
    }//GEN-LAST:event_checkGestionTercerosActionPerformed

    private void checkExportarTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkExportarTercerosActionPerformed
    }//GEN-LAST:event_checkExportarTercerosActionPerformed

    private void checkGestionReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkGestionReportesActionPerformed
        if(checkGestionReportes.isSelected()){
            checkVerReportes.setSelected(true);
        }else if(!checkGestionReportes.isSelected()){
            checkVerReportes.setSelected(false);
        }
    }//GEN-LAST:event_checkGestionReportesActionPerformed

    private void checkExportarReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkExportarReportesActionPerformed
    }//GEN-LAST:event_checkExportarReportesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGuardarRol;
    public javax.swing.JCheckBox checkExportarCompras;
    public javax.swing.JCheckBox checkExportarLotes;
    public javax.swing.JCheckBox checkExportarProductos;
    public javax.swing.JCheckBox checkExportarReportes;
    public javax.swing.JCheckBox checkExportarTerceros;
    public javax.swing.JCheckBox checkExportarUsuarios;
    public javax.swing.JCheckBox checkExportarVentas;
    public javax.swing.JCheckBox checkGestionCompra;
    public javax.swing.JCheckBox checkGestionLotes;
    public javax.swing.JCheckBox checkGestionProductos;
    public javax.swing.JCheckBox checkGestionReportes;
    public javax.swing.JCheckBox checkGestionTerceros;
    public javax.swing.JCheckBox checkGestionUsuarios;
    public javax.swing.JCheckBox checkRegistroCompraVenta;
    public javax.swing.JCheckBox checkVerCompras;
    public javax.swing.JCheckBox checkVerLotes;
    public javax.swing.JCheckBox checkVerProductos;
    public javax.swing.JCheckBox checkVerReportes;
    public javax.swing.JCheckBox checkVerTerceros;
    public javax.swing.JCheckBox checkVerUsuarios;
    public javax.swing.JCheckBox checkVerVentas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelDatos;
    public javax.swing.JTextArea txtDescripcion;
    public javax.swing.JTextField txtRol;
    // End of variables declaration//GEN-END:variables
}