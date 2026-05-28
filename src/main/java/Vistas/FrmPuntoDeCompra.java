package Vistas;

// import Controladores.CtrlFactura.CtrlPuntoVenta; // <- Descomenta si usas el controlador
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmPuntoDeCompra extends javax.swing.JInternalFrame {

    public FrmPuntoDeCompra() {
        initComponents();
        


        // CtrlPuntoVenta controlador = new CtrlPuntoVenta(this); // <- Descomenta si usas el controlador aquí
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbProducto = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        btnAgregarProducto = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleCompra = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbDescuento = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        lblSubTotal = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblIVA = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnRegistrarCompra = new javax.swing.JButton();
        btnImprimirFactura = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();

        setBackground(new java.awt.Color(244, 246, 249));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Punto de Venta");
        setPreferredSize(new java.awt.Dimension(1000, 680));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(32, 56, 100));
        jLabel1.setText("Generar Factura / Venta");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 350, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("PRODUCTO");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 150, 16));

        cmbProducto.setBackground(new java.awt.Color(255, 255, 255));
        cmbProducto.setForeground(new java.awt.Color(51, 51, 51));
        cmbProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        getContentPane().add(cmbProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 280, 38));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("PRECIO");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 80, 16));

        btnAgregarProducto.setBackground(new java.awt.Color(248, 249, 250));
        btnAgregarProducto.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAgregarProducto.setForeground(new java.awt.Color(43, 68, 122));
        btnAgregarProducto.setText("+ Agregar");
        btnAgregarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarProducto.setFocusPainted(false);
        getContentPane().add(btnAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 150, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(32, 56, 100));
        jLabel17.setText("Detalle de la factura");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 257, 24));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));

        tblDetalleCompra.setBackground(new java.awt.Color(255, 255, 255));
        tblDetalleCompra.setForeground(new java.awt.Color(51, 51, 51));
        tblDetalleCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Cantidad", "Precio Unitario", "Subtotal", "IVA", "Total", "Acción"
            }
        ));
        tblDetalleCompra.setGridColor(new java.awt.Color(224, 229, 236));
        tblDetalleCompra.setRowHeight(35);
        tblDetalleCompra.setSelectionBackground(new java.awt.Color(217, 226, 243));
        tblDetalleCompra.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(tblDetalleCompra);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 550, 350));

        btnLimpiar.setBackground(new java.awt.Color(248, 249, 250));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(43, 68, 122));
        btnLimpiar.setText("Limpiar Todo");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setFocusPainted(false);
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 590, 120, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("FECHA EMISIÓN");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, 160, 16));

        txtFechaEmision.setEditable(false);
        txtFechaEmision.setBackground(new java.awt.Color(245, 245, 245));
        txtFechaEmision.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtFechaEmision.setForeground(new java.awt.Color(51, 51, 51));
        txtFechaEmision.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtFechaEmision.addActionListener(this::txtFechaEmisionActionPerformed);
        getContentPane().add(txtFechaEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 160, 38));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("PROVEEDOR");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 160, 280, 16));

        cmbProveedor.setBackground(new java.awt.Color(255, 255, 255));
        cmbProveedor.setForeground(new java.awt.Color(51, 51, 51));
        cmbProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        getContentPane().add(cmbProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 280, 38));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("DESCUENTO");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 240, 120, 25));

        cmbDescuento.setBackground(new java.awt.Color(255, 255, 255));
        cmbDescuento.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cmbDescuento.setForeground(new java.awt.Color(51, 51, 51));
        cmbDescuento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        cmbDescuento.addActionListener(this::cmbDescuentoActionPerformed);
        getContentPane().add(cmbDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 260, 140, 38));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Subtotal General:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 120, 25));

        lblSubTotal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSubTotal.setForeground(new java.awt.Color(51, 51, 51));
        lblSubTotal.setText("$ 0.00");
        getContentPane().add(lblSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 330, 110, 25));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("IVA (13%):");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 360, 110, 25));

        lblIVA.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblIVA.setForeground(new java.awt.Color(51, 51, 51));
        lblIVA.setText("$ 0.00");
        getContentPane().add(lblIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 360, 110, 25));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Descuento:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 390, 120, 25));

        lblDescuento.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblDescuento.setForeground(new java.awt.Color(220, 53, 69));
        lblDescuento.setText("- $ 0.00");
        getContentPane().add(lblDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 390, 110, 25));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(32, 56, 100));
        jLabel16.setText("Total a pagar:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 430, 120, 25));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(32, 56, 100));
        lblTotal.setText("$ 0.00");
        getContentPane().add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 430, 140, 25));

        btnRegistrarCompra.setBackground(new java.awt.Color(43, 68, 122));
        btnRegistrarCompra.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnRegistrarCompra.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarCompra.setText("Facturar / Guardar Compra");
        btnRegistrarCompra.setBorderPainted(false);
        btnRegistrarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrarCompra.setFocusPainted(false);
        getContentPane().add(btnRegistrarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 480, 280, 40));

        btnImprimirFactura.setBackground(new java.awt.Color(248, 249, 250));
        btnImprimirFactura.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnImprimirFactura.setForeground(new java.awt.Color(43, 68, 122));
        btnImprimirFactura.setText("Imprimir");
        btnImprimirFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnImprimirFactura.setFocusPainted(false);
        getContentPane().add(btnImprimirFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 530, 280, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("CANTIDAD");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 80, 16));

        txtPrecioCompra.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecioCompra.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtPrecioCompra.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecioCompra.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtPrecioCompra.setCaretColor(new java.awt.Color(43, 68, 122));
        getContentPane().add(txtPrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 80, 38));

        txtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(51, 51, 51));
        txtCantidad.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtCantidad.setCaretColor(new java.awt.Color(43, 68, 122));
        getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 80, 38));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaEmisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaEmisionActionPerformed

    private void cmbDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDescuentoActionPerformed

    private void txtFechaEmision1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaEmision1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaEmision1ActionPerformed

    private void cmbDescuento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDescuento1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDescuento1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarProducto;
    public javax.swing.JButton btnImprimirFactura;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnRegistrarCompra;
    public javax.swing.JComboBox<String> cmbDescuento;
    public javax.swing.JComboBox<String> cmbProducto;
    public javax.swing.JComboBox<String> cmbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblDescuento;
    public javax.swing.JLabel lblIVA;
    public javax.swing.JLabel lblSubTotal;
    public javax.swing.JLabel lblTotal;
    public javax.swing.JTable tblDetalleCompra;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtFechaEmision;
    public javax.swing.JTextField txtPrecioCompra;
    // End of variables declaration//GEN-END:variables
}