package Vistas;

// import Controladores.CtrlFactura.CtrlPuntoVenta; // <- Descomenta si usas el controlador
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmFacturaPuntoVenta extends javax.swing.JInternalFrame {

    public FrmFacturaPuntoVenta() {
        initComponents();
        
        // Estilizar cabecera de tabla
        tblDetalleFactura.getTableHeader().setBackground(Color.decode("#181D2E"));
        tblDetalleFactura.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tblDetalleFactura.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        tblDetalleFactura.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050")));
        ((DefaultTableCellRenderer) tblDetalleFactura.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Efectos Hover a Botones
        btnAgregarProducto.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnAgregarProducto.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnAgregarProducto.setBackground(Color.decode("#6C63FF")); }
        });
        btnQuitarProducto.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnQuitarProducto.setBackground(Color.decode("#FF5B7A")); btnQuitarProducto.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e) { btnQuitarProducto.setBackground(Color.decode("#1E0A10")); btnQuitarProducto.setForeground(Color.decode("#FF5B7A")); }
        });
        btnFacturar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnFacturar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnFacturar.setBackground(Color.decode("#6C63FF")); }
        });
        btnAnularFactura.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnAnularFactura.setBackground(Color.decode("#FF5B7A")); btnAnularFactura.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e) { btnAnularFactura.setBackground(Color.decode("#1E0A10")); btnAnularFactura.setForeground(Color.decode("#FF5B7A")); }
        });
        btnImprimirFactura.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnImprimirFactura.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e) { btnImprimirFactura.setBackground(Color.decode("#1F2640")); }
        });

        // CtrlPuntoVenta controlador = new CtrlPuntoVenta(this); // <- Descomenta si usas el controlador aquí
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbCliente = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbProducto = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnAgregarProducto = new javax.swing.JButton();
        btnQuitarProducto = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleFactura = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtSubtotalGeneral = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbDescuento = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTotalPagar = new javax.swing.JTextField();
        btnFacturar = new javax.swing.JButton();
        btnImprimirFactura = new javax.swing.JButton();
        btnAnularFactura = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Punto de Venta");
        setPreferredSize(new java.awt.Dimension(1000, 680));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 242, 255));
        jLabel1.setText("✦  Generar Factura / Venta");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 20, 300, 30);

        panelDatos.setBackground(new java.awt.Color(17, 21, 32));
        panelDatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(155, 163, 196));
        jLabel3.setText("FECHA EMISIÓN");
        panelDatos.add(jLabel3);
        jLabel3.setBounds(740, 20, 160, 16);

        txtFechaEmision.setEditable(false);
        txtFechaEmision.setBackground(new java.awt.Color(14, 18, 25));
        txtFechaEmision.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtFechaEmision.setForeground(new java.awt.Color(240, 242, 255));
        txtFechaEmision.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtFechaEmision.addActionListener(this::txtFechaEmisionActionPerformed);
        panelDatos.add(txtFechaEmision);
        txtFechaEmision.setBounds(740, 40, 160, 38);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(155, 163, 196));
        jLabel4.setText("CLIENTE");
        panelDatos.add(jLabel4);
        jLabel4.setBounds(20, 20, 300, 16);

        cbCliente.setBackground(new java.awt.Color(24, 29, 46));
        cbCliente.setForeground(new java.awt.Color(240, 242, 255));
        cbCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.add(cbCliente);
        cbCliente.setBounds(20, 40, 300, 38);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(155, 163, 196));
        jLabel9.setText("PRODUCTO");
        panelDatos.add(jLabel9);
        jLabel9.setBounds(340, 20, 280, 16);

        cbProducto.setBackground(new java.awt.Color(24, 29, 46));
        cbProducto.setForeground(new java.awt.Color(240, 242, 255));
        cbProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.add(cbProducto);
        cbProducto.setBounds(340, 40, 280, 38);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(155, 163, 196));
        jLabel12.setText("CANTIDAD");
        panelDatos.add(jLabel12);
        jLabel12.setBounds(640, 20, 80, 16);

        txtCantidad.setBackground(new java.awt.Color(24, 29, 46));
        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(240, 242, 255));
        txtCantidad.setCaretColor(new java.awt.Color(108, 99, 255));
        txtCantidad.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelDatos.add(txtCantidad);
        txtCantidad.setBounds(640, 40, 80, 38);

        btnAgregarProducto.setBackground(new java.awt.Color(108, 99, 255));
        btnAgregarProducto.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAgregarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProducto.setText("Agregar Producto");
        btnAgregarProducto.setBorderPainted(false);
        btnAgregarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarProducto.setFocusPainted(false);
        panelDatos.add(btnAgregarProducto);
        btnAgregarProducto.setBounds(20, 95, 160, 40);

        btnQuitarProducto.setBackground(new java.awt.Color(30, 10, 16));
        btnQuitarProducto.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnQuitarProducto.setForeground(new java.awt.Color(255, 91, 122));
        btnQuitarProducto.setText("Quitar Producto");
        btnQuitarProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnQuitarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuitarProducto.setFocusPainted(false);
        panelDatos.add(btnQuitarProducto);
        btnQuitarProducto.setBounds(190, 95, 160, 40);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 242, 240));
        jLabel17.setText("Detalle de la factura");
        panelDatos.add(jLabel17);
        jLabel17.setBounds(20, 150, 257, 34);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));

        tblDetalleFactura.setBackground(new java.awt.Color(14, 18, 25));
        tblDetalleFactura.setForeground(new java.awt.Color(240, 242, 255));
        tblDetalleFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDetalleFactura.setGridColor(new java.awt.Color(26, 31, 48));
        tblDetalleFactura.setRowHeight(30);
        tblDetalleFactura.setSelectionBackground(new java.awt.Color(108, 99, 255));
        tblDetalleFactura.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblDetalleFactura);

        panelDatos.add(jScrollPane1);
        jScrollPane1.setBounds(20, 190, 880, 220);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(155, 163, 196));
        jLabel15.setText("Subtotal General:");
        panelDatos.add(jLabel15);
        jLabel15.setBounds(20, 430, 120, 25);

        txtSubtotalGeneral.setBackground(new java.awt.Color(24, 29, 46));
        txtSubtotalGeneral.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSubtotalGeneral.setForeground(new java.awt.Color(240, 242, 255));
        txtSubtotalGeneral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.add(txtSubtotalGeneral);
        txtSubtotalGeneral.setBounds(140, 425, 140, 35);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 163, 196));
        jLabel2.setText("Descuento:");
        panelDatos.add(jLabel2);
        jLabel2.setBounds(20, 480, 120, 25);

        cbDescuento.setBackground(new java.awt.Color(24, 29, 46));
        cbDescuento.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cbDescuento.setForeground(new java.awt.Color(240, 242, 255));
        cbDescuento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.add(cbDescuento);
        cbDescuento.setBounds(140, 475, 140, 35);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(155, 163, 196));
        jLabel19.setText("IVA (%)");
        panelDatos.add(jLabel19);
        jLabel19.setBounds(320, 430, 110, 25);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(240, 242, 255));
        jLabel20.setText("13.0");
        panelDatos.add(jLabel20);
        jLabel20.setBounds(440, 430, 100, 25);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(155, 163, 196));
        jLabel16.setText("Total a pagar:");
        panelDatos.add(jLabel16);
        jLabel16.setBounds(320, 480, 110, 25);

        txtTotalPagar.setBackground(new java.awt.Color(14, 18, 25));
        txtTotalPagar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTotalPagar.setForeground(new java.awt.Color(35, 233, 108));
        txtTotalPagar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.add(txtTotalPagar);
        txtTotalPagar.setBounds(440, 470, 140, 45);

        btnFacturar.setBackground(new java.awt.Color(108, 99, 255));
        btnFacturar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnFacturar.setForeground(new java.awt.Color(255, 255, 255));
        btnFacturar.setText("Facturar / Guardar");
        btnFacturar.setBorderPainted(false);
        btnFacturar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFacturar.setFocusPainted(false);
        panelDatos.add(btnFacturar);
        btnFacturar.setBounds(640, 425, 260, 40);

        btnImprimirFactura.setBackground(new java.awt.Color(31, 38, 64));
        btnImprimirFactura.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnImprimirFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimirFactura.setText("Imprimir Factura");
        btnImprimirFactura.setBorderPainted(false);
        btnImprimirFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimirFactura.setFocusPainted(false);
        panelDatos.add(btnImprimirFactura);
        btnImprimirFactura.setBounds(630, 475, 140, 40);

        btnAnularFactura.setBackground(new java.awt.Color(30, 10, 16));
        btnAnularFactura.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAnularFactura.setForeground(new java.awt.Color(255, 91, 122));
        btnAnularFactura.setText("Anular");
        btnAnularFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnAnularFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnularFactura.setFocusPainted(false);
        panelDatos.add(btnAnularFactura);
        btnAnularFactura.setBounds(780, 475, 120, 40);

        getContentPane().add(panelDatos);
        panelDatos.setBounds(30, 70, 930, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaEmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaEmisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaEmisionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarProducto;
    public javax.swing.JButton btnAnularFactura;
    public javax.swing.JButton btnFacturar;
    public javax.swing.JButton btnImprimirFactura;
    public javax.swing.JButton btnQuitarProducto;
    public javax.swing.JComboBox<String> cbCliente;
    public javax.swing.JComboBox<String> cbDescuento;
    public javax.swing.JComboBox<String> cbProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelDatos;
    public javax.swing.JTable tblDetalleFactura;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtFechaEmision;
    public javax.swing.JTextField txtSubtotalGeneral;
    public javax.swing.JTextField txtTotalPagar;
    // End of variables declaration//GEN-END:variables
}