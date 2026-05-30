package Vistas;

import Controladores.ctrlProductos.AjusteInventarioController;
import Modelos.LoteInventario;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmAjusteInventario extends javax.swing.JInternalFrame {

    public FrmAjusteInventario() {
        initComponents();
        
       
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
        btnRegistrarAjuste = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tblAjustes = new javax.swing.JTable();
        btnExportarCSV = new javax.swing.JButton();

        setBackground(new java.awt.Color(244, 246, 249));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ajuste de Inventario");
        setPreferredSize(new java.awt.Dimension(790, 590));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(32, 56, 100));
        jLabelTitulo.setText("Ajuste de Inventario");
        getContentPane().add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 15, 400, 30));

        panelDatos.setBackground(new java.awt.Color(255, 255, 255));
        panelDatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        panelDatos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLote.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelLote.setForeground(new java.awt.Color(51, 51, 51));
        jLabelLote.setText("PRODUCTO / LOTE");
        panelDatos.add(jLabelLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, 160, 16));

        cboLoteProducto.setBackground(new java.awt.Color(255, 255, 255));
        cboLoteProducto.setForeground(new java.awt.Color(51, 51, 51));
        cboLoteProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        panelDatos.add(cboLoteProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 35, 370, 38));

        jLabelStock.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelStock.setForeground(new java.awt.Color(51, 51, 51));
        jLabelStock.setText("STOCK ACTUAL");
        panelDatos.add(jLabelStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 15, 120, 16));

        lblStockActual.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblStockActual.setForeground(new java.awt.Color(32, 56, 100));
        lblStockActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStockActual.setText("0");
        lblStockActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        panelDatos.add(lblStockActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 35, 120, 38));

        jLabelTipo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelTipo.setForeground(new java.awt.Color(51, 51, 51));
        jLabelTipo.setText("TIPO DE AJUSTE");
        panelDatos.add(jLabelTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 85, 180, 16));

        cboTipoMovimiento.setBackground(new java.awt.Color(255, 255, 255));
        cboTipoMovimiento.setForeground(new java.awt.Color(51, 51, 51));
        cboTipoMovimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        panelDatos.add(cboTipoMovimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 180, 38));

        jLabelCantidad.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelCantidad.setForeground(new java.awt.Color(51, 51, 51));
        jLabelCantidad.setText("CANTIDAD");
        panelDatos.add(jLabelCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 85, 120, 16));

        txtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(51, 51, 51));
        txtCantidad.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtCantidad.setCaretColor(new java.awt.Color(43, 68, 122));
        panelDatos.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 105, 120, 38));

        jLabelMotivo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelMotivo.setForeground(new java.awt.Color(51, 51, 51));
        jLabelMotivo.setText("MOTIVO DEL AJUSTE");
        panelDatos.add(jLabelMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 155, 180, 16));

        scrollMotivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));

        txtMotivoAjuste.setColumns(20);
        txtMotivoAjuste.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMotivoAjuste.setForeground(new java.awt.Color(51, 51, 51));
        txtMotivoAjuste.setRows(4);
        txtMotivoAjuste.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10));
        txtMotivoAjuste.setCaretColor(new java.awt.Color(43, 68, 122));
        scrollMotivo.setViewportView(txtMotivoAjuste);

        panelDatos.add(scrollMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 175, 510, 50));

        btnRegistrarAjuste.setBackground(new java.awt.Color(43, 68, 122));
        btnRegistrarAjuste.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnRegistrarAjuste.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarAjuste.setText("Guardar Ajuste");
        btnRegistrarAjuste.setBorderPainted(false);
        btnRegistrarAjuste.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrarAjuste.setFocusPainted(false);
        panelDatos.add(btnRegistrarAjuste, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 105, 150, 42));

        btnLimpiar.setBackground(new java.awt.Color(248, 249, 250));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(43, 68, 122));
        btnLimpiar.setText("Cancelar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setFocusPainted(false);
        panelDatos.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 165, 150, 42));

        getContentPane().add(panelDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 60, 730, 245));

        scrollTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));

        tblAjustes.setBackground(new java.awt.Color(255, 255, 255));
        tblAjustes.setForeground(new java.awt.Color(51, 51, 51));
        tblAjustes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Movimineto", "Tipo", "Cantidad", "Fecha", "Producto", "Lote", "Stock Inicial", "Stock Actual", "Precio Compra", "Precio Venta", "Motivo", "ID Usuario"
            }
        ));
        tblAjustes.setGridColor(new java.awt.Color(224, 229, 236));
        tblAjustes.setRowHeight(35);
        tblAjustes.setSelectionBackground(new java.awt.Color(217, 226, 243));
        tblAjustes.setSelectionForeground(new java.awt.Color(0, 0, 0));
        scrollTabla.setViewportView(tblAjustes);

        getContentPane().add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 320, 730, 210));

        btnExportarCSV.setBackground(new java.awt.Color(43, 68, 122));
        btnExportarCSV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnExportarCSV.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarCSV.setText("Exportar CSV");
        btnExportarCSV.setBorderPainted(false);
        btnExportarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExportarCSV.setFocusPainted(false);
        getContentPane().add(btnExportarCSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 150, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnExportarCSV;
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