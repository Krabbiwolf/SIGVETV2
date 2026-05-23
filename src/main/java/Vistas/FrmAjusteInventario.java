package Vistas;

import Controladores.ctrlProductos.AjusteInventarioController;
import Modelos.LoteInventario;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmAjusteInventario extends javax.swing.JInternalFrame {

    public FrmAjusteInventario() {
        initComponents();
        
        // Estilizar cabecera de tabla
        tblAjustes.getTableHeader().setBackground(Color.decode("#181D2E"));
        tblAjustes.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tblAjustes.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        tblAjustes.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050")));
        ((DefaultTableCellRenderer) tblAjustes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Efectos Hover
        btnRegistrarAjuste.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnRegistrarAjuste.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnRegistrarAjuste.setBackground(Color.decode("#6C63FF")); }
        });

        btnLimpiar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnLimpiar.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e)  { btnLimpiar.setBackground(Color.decode("#181D2E")); }
        });
        
        // Inicializar controlador
        AjusteInventarioController controlador = new AjusteInventarioController(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        jLabelLote = new javax.swing.JLabel();
        cboLoteProducto = new javax.swing.JComboBox<>();
        jLabelStock = new javax.swing.JLabel();
        lblStockActual = new javax.swing.JLabel();
        jLabelTipo = new javax.swing.JLabel();
        cboTipoMovimiento = new javax.swing.JComboBox<>();
        jLabelCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabelMotivo = new javax.swing.JLabel();
        scrollMotivo = new javax.swing.JScrollPane();
        txtMotivoAjuste = new javax.swing.JTextArea();
        btnLimpiar = new javax.swing.JButton();
        btnRegistrarAjuste = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tblAjustes = new javax.swing.JTable();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ajuste de Inventario");
        setPreferredSize(new java.awt.Dimension(780, 580));
        getContentPane().setLayout(null);

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(240, 242, 255));
        jLabelTitulo.setText("✦  Ajuste de Inventario");
        getContentPane().add(jLabelTitulo);
        jLabelTitulo.setBounds(20, 15, 400, 30);

        panelDatos.setBackground(new java.awt.Color(17, 21, 32));
        panelDatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.setLayout(null);

        jLabelLote.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelLote.setForeground(new java.awt.Color(155, 163, 196));
        jLabelLote.setText("PRODUCTO / LOTE");
        panelDatos.add(jLabelLote);
        jLabelLote.setBounds(20, 20, 160, 16);

        cboLoteProducto.setBackground(new java.awt.Color(24, 29, 46));
        cboLoteProducto.setForeground(new java.awt.Color(240, 242, 255));
        cboLoteProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.add(cboLoteProducto);
        cboLoteProducto.setBounds(20, 40, 370, 38);

        jLabelStock.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelStock.setForeground(new java.awt.Color(155, 163, 196));
        jLabelStock.setText("STOCK ACTUAL");
        panelDatos.add(jLabelStock);
        jLabelStock.setBounds(410, 20, 120, 16);

        lblStockActual.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblStockActual.setForeground(new java.awt.Color(240, 242, 255));
        lblStockActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStockActual.setText("0");
        lblStockActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.add(lblStockActual);
        lblStockActual.setBounds(410, 40, 120, 38);

        jLabelTipo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelTipo.setForeground(new java.awt.Color(155, 163, 196));
        jLabelTipo.setText("TIPO DE AJUSTE");
        panelDatos.add(jLabelTipo);
        jLabelTipo.setBounds(20, 90, 180, 16);

        cboTipoMovimiento.setBackground(new java.awt.Color(24, 29, 46));
        cboTipoMovimiento.setForeground(new java.awt.Color(240, 242, 255));
        cboTipoMovimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.add(cboTipoMovimiento);
        cboTipoMovimiento.setBounds(20, 110, 180, 38);

        jLabelCantidad.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelCantidad.setForeground(new java.awt.Color(155, 163, 196));
        jLabelCantidad.setText("CANTIDAD");
        panelDatos.add(jLabelCantidad);
        jLabelCantidad.setBounds(220, 90, 120, 16);

        txtCantidad.setBackground(new java.awt.Color(24, 29, 46));
        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(240, 242, 255));
        txtCantidad.setCaretColor(new java.awt.Color(108, 99, 255));
        txtCantidad.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelDatos.add(txtCantidad);
        txtCantidad.setBounds(220, 110, 120, 38);

        jLabelMotivo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelMotivo.setForeground(new java.awt.Color(155, 163, 196));
        jLabelMotivo.setText("MOTIVO DEL AJUSTE");
        panelDatos.add(jLabelMotivo);
        jLabelMotivo.setBounds(20, 160, 180, 16);

        scrollMotivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));

        txtMotivoAjuste.setBackground(new java.awt.Color(24, 29, 46));
        txtMotivoAjuste.setColumns(20);
        txtMotivoAjuste.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMotivoAjuste.setForeground(new java.awt.Color(240, 242, 255));
        txtMotivoAjuste.setRows(4);
        txtMotivoAjuste.setCaretColor(new java.awt.Color(108, 99, 255));
        scrollMotivo.setViewportView(txtMotivoAjuste);

        panelDatos.add(scrollMotivo);
        scrollMotivo.setBounds(20, 180, 510, 35);

        btnLimpiar.setBackground(new java.awt.Color(24, 29, 46));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(240, 242, 255));
        btnLimpiar.setText("Cancelar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setFocusPainted(false);
        panelDatos.add(btnLimpiar);
        btnLimpiar.setBounds(560, 100, 140, 42);

        btnRegistrarAjuste.setBackground(new java.awt.Color(108, 99, 255));
        btnRegistrarAjuste.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnRegistrarAjuste.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarAjuste.setText("Guardar");
        btnRegistrarAjuste.setBorderPainted(false);
        btnRegistrarAjuste.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarAjuste.setFocusPainted(false);
        panelDatos.add(btnRegistrarAjuste);
        btnRegistrarAjuste.setBounds(560, 40, 140, 42);

        getContentPane().add(panelDatos);
        panelDatos.setBounds(20, 60, 730, 230);

        scrollTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));

        tblAjustes.setBackground(new java.awt.Color(14, 18, 25));
        tblAjustes.setForeground(new java.awt.Color(240, 242, 255));
        tblAjustes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAjustes.setGridColor(new java.awt.Color(26, 31, 48));
        tblAjustes.setRowHeight(30);
        tblAjustes.setSelectionBackground(new java.awt.Color(108, 99, 255));
        tblAjustes.setSelectionForeground(new java.awt.Color(255, 255, 255));
        scrollTabla.setViewportView(tblAjustes);

        getContentPane().add(scrollTabla);
        scrollTabla.setBounds(20, 310, 730, 220);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnRegistrarAjuste;
    public javax.swing.JComboBox<Modelos.LoteInventario> cboLoteProducto;
    public javax.swing.JComboBox<String> cboTipoMovimiento;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JLabel jLabelLote;
    private javax.swing.JLabel jLabelMotivo;
    private javax.swing.JLabel jLabelStock;
    private javax.swing.JLabel jLabelTipo;
    private javax.swing.JLabel jLabelTitulo;
    public javax.swing.JLabel lblStockActual;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JScrollPane scrollMotivo;
    private javax.swing.JScrollPane scrollTabla;
    public javax.swing.JTable tblAjustes;
    public javax.swing.JTextField txtCantidad;
    public javax.swing.JTextArea txtMotivoAjuste;
    // End of variables declaration//GEN-END:variables
}