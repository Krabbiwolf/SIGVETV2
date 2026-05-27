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
        crearBotonExportarCSV();
        aplicarDisenoCorporativo();
        
        // Estilizar cabecera de tabla
        tblAjustes.getTableHeader().setBackground(Color.decode("#DCE6F2"));
        tblAjustes.getTableHeader().setForeground(Color.decode("#2D4A8A"));
        tblAjustes.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        tblAjustes.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#C5D8F5")));
        ((DefaultTableCellRenderer) tblAjustes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Efectos Hover (Garantizando legibilidad total)
        btnRegistrarAjuste.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnRegistrarAjuste.setBackground(Color.decode("#3A5AAA")); }
            public void mouseExited(MouseEvent e)  { btnRegistrarAjuste.setBackground(Color.decode("#2D4A8A")); }
        });

        btnLimpiar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnLimpiar.setBackground(Color.decode("#DCE6F2")); }
            public void mouseExited(MouseEvent e)  { btnLimpiar.setBackground(Color.decode("#FFFFFF")); }
        });

        btnExportarCSV.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnExportarCSV.setBackground(Color.decode("#C5D8F5")); }
            public void mouseExited(MouseEvent e)  { btnExportarCSV.setBackground(Color.decode("#DCE6F2")); }
        });
        
        btnRegistrarAjuste.setBackground(new Color(45, 74, 138));
btnRegistrarAjuste.setForeground(Color.WHITE);

btnRegistrarAjuste.setFocusPainted(false);
btnRegistrarAjuste.setBorderPainted(false);
btnRegistrarAjuste.setOpaque(true);

btnRegistrarAjuste.setContentAreaFilled(true);
        // Inicializar controlador
        AjusteInventarioController controlador = new AjusteInventarioController(this);
    }
    
    

    private void crearBotonExportarCSV() {
        btnExportarCSV = new javax.swing.JButton();
        btnExportarCSV.setText("Exportar CSV");
        prepararBotonCorporativo(btnExportarCSV, Color.decode("#DCE6F2"), Color.decode("#2D4A8A"));
        getContentPane().add(btnExportarCSV);
        // Alineado arriba a la derecha
        btnExportarCSV.setBounds(605, 15, 150, 32); 
    }

    private void aplicarDisenoCorporativo() {
        prepararBotonCorporativo(btnLimpiar, Color.decode("#FFFFFF"), Color.decode("#2D4A8A"));
        prepararBotonCorporativo(btnRegistrarAjuste, Color.decode("#2D4A8A"), Color.decode("#FFFFFF"));
        prepararComboCorporativo(cboLoteProducto);
        prepararComboCorporativo(cboTipoMovimiento);
    }

    private void prepararBotonCorporativo(JButton boton, Color fondo, Color texto) {
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setBorderPainted(true);
        boton.setFocusPainted(false);
        boton.setFocusable(false);
        boton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        boton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.decode("#C5D8F5")));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void prepararComboCorporativo(JComboBox<?> combo) {
        combo.setBackground(Color.decode("#FFFFFF"));
        combo.setForeground(Color.decode("#333333"));
        combo.setOpaque(true);
        combo.setFocusable(false);
        combo.setBorder(javax.swing.BorderFactory.createLineBorder(Color.decode("#C5D8F5")));

        combo.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    javax.swing.JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);

                label.setOpaque(true);
                label.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
                label.setText(value == null ? "" : value.toString());

                if (isSelected) {
                    label.setBackground(Color.decode("#2D4A8A"));
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.decode("#333333"));
                }

                label.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 8, 6, 8));
                return label;
            }
        });
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

        setBackground(new java.awt.Color(240, 244, 248));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ajuste de Inventario");
        setPreferredSize(new java.awt.Dimension(790, 590));
        getContentPane().setLayout(null);

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(45, 74, 138));
        jLabelTitulo.setText("Ajuste de Inventario");
        getContentPane().add(jLabelTitulo);
        jLabelTitulo.setBounds(25, 15, 400, 30);

        panelDatos.setBackground(new java.awt.Color(240, 244, 248));
        panelDatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelDatos.setLayout(null);

        jLabelLote.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelLote.setForeground(new java.awt.Color(45, 74, 138));
        jLabelLote.setText("PRODUCTO / LOTE");
        panelDatos.add(jLabelLote);
        jLabelLote.setBounds(20, 15, 160, 16);

        cboLoteProducto.setForeground(new java.awt.Color(51, 51, 51));
        cboLoteProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelDatos.add(cboLoteProducto);
        cboLoteProducto.setBounds(20, 35, 370, 38);

        jLabelStock.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelStock.setForeground(new java.awt.Color(45, 74, 138));
        jLabelStock.setText("STOCK ACTUAL");
        panelDatos.add(jLabelStock);
        jLabelStock.setBounds(410, 15, 120, 16);

        lblStockActual.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblStockActual.setForeground(new java.awt.Color(45, 74, 138));
        lblStockActual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStockActual.setText("0");
        lblStockActual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelDatos.add(lblStockActual);
        lblStockActual.setBounds(410, 35, 120, 38);

        jLabelTipo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelTipo.setForeground(new java.awt.Color(45, 74, 138));
        jLabelTipo.setText("TIPO DE AJUSTE");
        panelDatos.add(jLabelTipo);
        jLabelTipo.setBounds(20, 85, 180, 16);

        cboTipoMovimiento.setForeground(new java.awt.Color(51, 51, 51));
        cboTipoMovimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelDatos.add(cboTipoMovimiento);
        cboTipoMovimiento.setBounds(20, 105, 180, 38);

        jLabelCantidad.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelCantidad.setForeground(new java.awt.Color(45, 74, 138));
        jLabelCantidad.setText("CANTIDAD");
        panelDatos.add(jLabelCantidad);
        jLabelCantidad.setBounds(220, 85, 120, 16);

        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(51, 51, 51));
        txtCantidad.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtCantidad.setCaretColor(new java.awt.Color(45, 74, 138));
        panelDatos.add(txtCantidad);
        txtCantidad.setBounds(220, 105, 120, 38);

        jLabelMotivo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabelMotivo.setForeground(new java.awt.Color(45, 74, 138));
        jLabelMotivo.setText("MOTIVO DEL AJUSTE");
        panelDatos.add(jLabelMotivo);
        jLabelMotivo.setBounds(20, 155, 180, 16);

        scrollMotivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));

        txtMotivoAjuste.setColumns(20);
        txtMotivoAjuste.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMotivoAjuste.setForeground(new java.awt.Color(51, 51, 51));
        txtMotivoAjuste.setRows(4);
        txtMotivoAjuste.setCaretColor(new java.awt.Color(45, 74, 138));
        txtMotivoAjuste.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10));
        scrollMotivo.setViewportView(txtMotivoAjuste);

        panelDatos.add(scrollMotivo);
        scrollMotivo.setBounds(20, 175, 510, 50);

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(45, 74, 138));
        btnLimpiar.setText("Cancelar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setFocusPainted(false);
        panelDatos.add(btnLimpiar);
        btnLimpiar.setBounds(560, 165, 150, 42);

        btnRegistrarAjuste.setBackground(new java.awt.Color(45, 74, 138));
        btnRegistrarAjuste.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnRegistrarAjuste.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarAjuste.setText("Guardar Ajuste");
        btnRegistrarAjuste.setBorderPainted(false);
        btnRegistrarAjuste.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrarAjuste.setFocusPainted(false);
        panelDatos.add(btnRegistrarAjuste);
        btnRegistrarAjuste.setBounds(560, 105, 150, 42);

        getContentPane().add(panelDatos);
        panelDatos.setBounds(25, 60, 730, 245);

        scrollTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));

        tblAjustes.setForeground(new java.awt.Color(51, 51, 51));
        tblAjustes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAjustes.setGridColor(new java.awt.Color(224, 224, 224));
        tblAjustes.setRowHeight(32);
        tblAjustes.setSelectionBackground(new java.awt.Color(197, 216, 245));
        tblAjustes.setSelectionForeground(new java.awt.Color(13, 32, 96));
        scrollTabla.setViewportView(tblAjustes);

        getContentPane().add(scrollTabla);
        scrollTabla.setBounds(25, 320, 730, 210);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public javax.swing.JButton btnExportarCSV;

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