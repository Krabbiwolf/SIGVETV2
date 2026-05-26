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
        btnLimpiar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnLimpiar.setBackground(Color.decode("#FF5B7A")); btnLimpiar.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e) { btnLimpiar.setBackground(Color.decode("#1E0A10")); btnLimpiar.setForeground(Color.decode("#FF5B7A")); }
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
        cmbCliente = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cmbProducto = new javax.swing.JComboBox<>();
        txtCantidad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnAgregarProducto = new javax.swing.JButton();
        btnQuitarProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleFactura = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbDescuento = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnImprimirFactura = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnAnularFactura = new javax.swing.JButton();
        lblTotal = new javax.swing.JLabel();
        lblSubTotal = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel8 = new javax.swing.JLabel();
        cmbCliente1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtFechaEmision1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmbProducto1 = new javax.swing.JComboBox<>();
        txtCantidad1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnAgregarProducto1 = new javax.swing.JButton();
        btnQuitarProducto1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalleFactura1 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cmbDescuento1 = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnImprimirFactura1 = new javax.swing.JButton();
        btnLimpiar1 = new javax.swing.JButton();
        btnFacturar1 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        btnAnularFactura2 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        lblIVA = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Punto de Venta");
        setPreferredSize(new java.awt.Dimension(1000, 680));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 242, 255));
        jLabel1.setText("✦  Generar Factura / Venta");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 300, -1));

        cmbCliente.setBackground(new java.awt.Color(24, 29, 46));
        cmbCliente.setForeground(new java.awt.Color(240, 242, 255));
        cmbCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        getContentPane().add(cmbCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 300, 38));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(155, 163, 196));
        jLabel4.setText("CLIENTE");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 300, 16));

        txtFechaEmision.setEditable(false);
        txtFechaEmision.setBackground(new java.awt.Color(14, 18, 25));
        txtFechaEmision.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtFechaEmision.setForeground(new java.awt.Color(240, 242, 255));
        txtFechaEmision.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtFechaEmision.addActionListener(this::txtFechaEmisionActionPerformed);
        getContentPane().add(txtFechaEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 160, 38));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(155, 163, 196));
        jLabel3.setText("FECHA EMISIÓN");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 160, 16));

        cmbProducto.setBackground(new java.awt.Color(24, 29, 46));
        cmbProducto.setForeground(new java.awt.Color(240, 242, 255));
        cmbProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        getContentPane().add(cmbProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 280, 38));

        txtCantidad.setBackground(new java.awt.Color(24, 29, 46));
        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(240, 242, 255));
        txtCantidad.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtCantidad.setCaretColor(new java.awt.Color(108, 99, 255));
        getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 80, 38));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(155, 163, 196));
        jLabel12.setText("CANTIDAD");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 80, 16));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(155, 163, 196));
        jLabel9.setText("PRODUCTO");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 70, 16));

        btnAgregarProducto.setBackground(new java.awt.Color(108, 99, 255));
        btnAgregarProducto.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAgregarProducto.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProducto.setText("+Agregar");
        btnAgregarProducto.setBorderPainted(false);
        btnAgregarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarProducto.setFocusPainted(false);
        getContentPane().add(btnAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 150, 40));

        btnQuitarProducto.setBackground(new java.awt.Color(30, 10, 16));
        btnQuitarProducto.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnQuitarProducto.setForeground(new java.awt.Color(255, 91, 122));
        btnQuitarProducto.setText("Quitar");
        btnQuitarProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnQuitarProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnQuitarProducto.setFocusPainted(false);
        getContentPane().add(btnQuitarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 150, 40));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 550, 340));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 242, 240));
        jLabel17.setText("Detalle de la factura");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 257, 34));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(155, 163, 196));
        jLabel15.setText("Subtotal General:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, 120, 25));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 163, 196));
        jLabel2.setText("Descuento:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 340, 120, 25));

        cmbDescuento.setBackground(new java.awt.Color(24, 29, 46));
        cmbDescuento.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cmbDescuento.setForeground(new java.awt.Color(240, 242, 255));
        cmbDescuento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        cmbDescuento.addActionListener(this::cmbDescuentoActionPerformed);
        getContentPane().add(cmbDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 140, 35));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(155, 163, 196));
        jLabel19.setText("IVA (13%)");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 310, 110, 25));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(155, 163, 196));
        jLabel16.setText("Total a pagar:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 370, 110, 25));

        btnImprimirFactura.setBackground(new java.awt.Color(31, 38, 64));
        btnImprimirFactura.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnImprimirFactura.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimirFactura.setText("Imprimir Factura");
        btnImprimirFactura.setBorderPainted(false);
        btnImprimirFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnImprimirFactura.setFocusPainted(false);
        getContentPane().add(btnImprimirFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 470, 140, 40));

        btnLimpiar.setBackground(new java.awt.Color(30, 10, 16));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 91, 122));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setFocusPainted(false);
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, 120, 40));

        btnFacturar.setBackground(new java.awt.Color(108, 99, 255));
        btnFacturar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnFacturar.setForeground(new java.awt.Color(255, 255, 255));
        btnFacturar.setText("Facturar / Guardar");
        btnFacturar.setBorderPainted(false);
        btnFacturar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnFacturar.setFocusPainted(false);
        getContentPane().add(btnFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 420, 280, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(155, 163, 196));
        jLabel5.setText("Descuento:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 120, 25));

        btnAnularFactura.setBackground(new java.awt.Color(30, 10, 16));
        btnAnularFactura.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAnularFactura.setForeground(new java.awt.Color(255, 91, 122));
        btnAnularFactura.setText("Anular");
        btnAnularFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnAnularFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAnularFactura.setFocusPainted(false);
        getContentPane().add(btnAnularFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 470, 120, 40));

        lblTotal.setText("jLabel6");
        getContentPane().add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 370, 110, -1));

        lblSubTotal.setText("jLabel6");
        getContentPane().add(lblSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 280, 110, -1));

        jInternalFrame1.setBackground(new java.awt.Color(10, 12, 16));
        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setTitle("Punto de Venta");
        jInternalFrame1.setPreferredSize(new java.awt.Dimension(1000, 680));
        jInternalFrame1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(240, 242, 255));
        jLabel8.setText("✦  Generar Factura / Venta");
        jInternalFrame1.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 300, -1));

        cmbCliente1.setBackground(new java.awt.Color(24, 29, 46));
        cmbCliente1.setForeground(new java.awt.Color(240, 242, 255));
        cmbCliente1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        jInternalFrame1.getContentPane().add(cmbCliente1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 140, 300, 38));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(155, 163, 196));
        jLabel10.setText("CLIENTE");
        jInternalFrame1.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, 300, 16));

        txtFechaEmision1.setEditable(false);
        txtFechaEmision1.setBackground(new java.awt.Color(14, 18, 25));
        txtFechaEmision1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtFechaEmision1.setForeground(new java.awt.Color(240, 242, 255));
        txtFechaEmision1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtFechaEmision1.addActionListener(this::txtFechaEmision1ActionPerformed);
        jInternalFrame1.getContentPane().add(txtFechaEmision1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 160, 38));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(155, 163, 196));
        jLabel11.setText("FECHA EMISIÓN");
        jInternalFrame1.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 160, 16));

        cmbProducto1.setBackground(new java.awt.Color(24, 29, 46));
        cmbProducto1.setForeground(new java.awt.Color(240, 242, 255));
        cmbProducto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        jInternalFrame1.getContentPane().add(cmbProducto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 280, 38));

        txtCantidad1.setBackground(new java.awt.Color(24, 29, 46));
        txtCantidad1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCantidad1.setForeground(new java.awt.Color(240, 242, 255));
        txtCantidad1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtCantidad1.setCaretColor(new java.awt.Color(108, 99, 255));
        jInternalFrame1.getContentPane().add(txtCantidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 80, 38));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(155, 163, 196));
        jLabel13.setText("CANTIDAD");
        jInternalFrame1.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 80, 16));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(155, 163, 196));
        jLabel14.setText("PRODUCTO");
        jInternalFrame1.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 70, 16));

        btnAgregarProducto1.setBackground(new java.awt.Color(108, 99, 255));
        btnAgregarProducto1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAgregarProducto1.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProducto1.setText("+Agregar");
        btnAgregarProducto1.setBorderPainted(false);
        btnAgregarProducto1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarProducto1.setFocusPainted(false);
        jInternalFrame1.getContentPane().add(btnAgregarProducto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 50, 150, 40));

        btnQuitarProducto1.setBackground(new java.awt.Color(30, 10, 16));
        btnQuitarProducto1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnQuitarProducto1.setForeground(new java.awt.Color(255, 91, 122));
        btnQuitarProducto1.setText("Quitar");
        btnQuitarProducto1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnQuitarProducto1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnQuitarProducto1.setFocusPainted(false);
        jInternalFrame1.getContentPane().add(btnQuitarProducto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 150, 40));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));

        tblDetalleFactura1.setBackground(new java.awt.Color(14, 18, 25));
        tblDetalleFactura1.setForeground(new java.awt.Color(240, 242, 255));
        tblDetalleFactura1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDetalleFactura1.setGridColor(new java.awt.Color(26, 31, 48));
        tblDetalleFactura1.setRowHeight(30);
        tblDetalleFactura1.setSelectionBackground(new java.awt.Color(108, 99, 255));
        tblDetalleFactura1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(tblDetalleFactura1);

        jInternalFrame1.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 550, 340));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 242, 240));
        jLabel18.setText("Detalle de la factura");
        jInternalFrame1.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 257, 34));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(155, 163, 196));
        jLabel20.setText("Subtotal General:");
        jInternalFrame1.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 120, 25));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(155, 163, 196));
        jLabel21.setText("Descuento:");
        jInternalFrame1.getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 340, 120, 25));

        cmbDescuento1.setBackground(new java.awt.Color(24, 29, 46));
        cmbDescuento1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cmbDescuento1.setForeground(new java.awt.Color(240, 242, 255));
        cmbDescuento1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        cmbDescuento1.addActionListener(this::cmbDescuento1ActionPerformed);
        jInternalFrame1.getContentPane().add(cmbDescuento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 210, 140, 35));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(155, 163, 196));
        jLabel22.setText("IVA (13%)");
        jInternalFrame1.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 310, 110, 25));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(155, 163, 196));
        jLabel23.setText("Total a pagar:");
        jInternalFrame1.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 370, 110, 25));

        btnImprimirFactura1.setBackground(new java.awt.Color(31, 38, 64));
        btnImprimirFactura1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnImprimirFactura1.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimirFactura1.setText("Imprimir Factura");
        btnImprimirFactura1.setBorderPainted(false);
        btnImprimirFactura1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnImprimirFactura1.setFocusPainted(false);
        jInternalFrame1.getContentPane().add(btnImprimirFactura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 470, 140, 40));

        btnLimpiar1.setBackground(new java.awt.Color(30, 10, 16));
        btnLimpiar1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar1.setForeground(new java.awt.Color(255, 91, 122));
        btnLimpiar1.setText("Limpiar");
        btnLimpiar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnLimpiar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar1.setFocusPainted(false);
        jInternalFrame1.getContentPane().add(btnLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, 120, 40));

        btnFacturar1.setBackground(new java.awt.Color(108, 99, 255));
        btnFacturar1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnFacturar1.setForeground(new java.awt.Color(255, 255, 255));
        btnFacturar1.setText("Facturar / Guardar");
        btnFacturar1.setBorderPainted(false);
        btnFacturar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnFacturar1.setFocusPainted(false);
        jInternalFrame1.getContentPane().add(btnFacturar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 420, 280, 40));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(155, 163, 196));
        jLabel24.setText("Descuento:");
        jInternalFrame1.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 180, 120, 25));

        btnAnularFactura2.setBackground(new java.awt.Color(30, 10, 16));
        btnAnularFactura2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAnularFactura2.setForeground(new java.awt.Color(255, 91, 122));
        btnAnularFactura2.setText("Anular");
        btnAnularFactura2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnAnularFactura2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAnularFactura2.setFocusPainted(false);
        jInternalFrame1.getContentPane().add(btnAnularFactura2, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 470, 120, 40));

        jLabel25.setText("jLabel6");
        jInternalFrame1.getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 310, 110, -1));

        jLabel26.setText("jLabel6");
        jInternalFrame1.getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 280, 110, -1));

        getContentPane().add(jInternalFrame1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        lblIVA.setText("jLabel6");
        getContentPane().add(lblIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 310, 110, -1));

        lblDescuento.setText("jLabel6");
        getContentPane().add(lblDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 340, 110, -1));

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
    public javax.swing.JButton btnAgregarProducto1;
    public javax.swing.JButton btnAnularFactura;
    public javax.swing.JButton btnAnularFactura2;
    public javax.swing.JButton btnFacturar;
    public javax.swing.JButton btnFacturar1;
    public javax.swing.JButton btnImprimirFactura;
    public javax.swing.JButton btnImprimirFactura1;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnLimpiar1;
    public javax.swing.JButton btnQuitarProducto;
    public javax.swing.JButton btnQuitarProducto1;
    public javax.swing.JComboBox<String> cmbCliente;
    public javax.swing.JComboBox<String> cmbCliente1;
    public javax.swing.JComboBox<String> cmbDescuento;
    public javax.swing.JComboBox<String> cmbDescuento1;
    public javax.swing.JComboBox<String> cmbProducto;
    public javax.swing.JComboBox<String> cmbProducto1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblIVA;
    private javax.swing.JLabel lblSubTotal;
    private javax.swing.JLabel lblTotal;
    public javax.swing.JTable tblDetalleFactura;
    public javax.swing.JTable tblDetalleFactura1;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextField txtCantidad1;
    public javax.swing.JTextField txtFechaEmision;
    public javax.swing.JTextField txtFechaEmision1;
    // End of variables declaration//GEN-END:variables
}