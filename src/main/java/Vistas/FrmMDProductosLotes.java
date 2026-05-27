package Vistas;

import Controladores.CtrlMaestroDetalle.CtrlMaestroDetalle;
import Modelos.MaestroDetalleDAO;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmMDProductosLotes extends javax.swing.JInternalFrame implements MaestroDetalleVista {

    public FrmMDProductosLotes() {
        initComponents();
        configurarComponentes();
        new CtrlMaestroDetalle(this, new MaestroDetalleDAO());
    }

    @Override
    public String getTipo() {
        return MaestroDetalleVista.PRODUCTOS_LOTES;
    }

    @Override
    public void configurarTextos(String titulo, String maestro, String detalle, String placeholder) {
        setTitle(titulo);
        lblTitulo.setText("✦  " + titulo);
        lblMaestro.setText(maestro);
        lblDetalle.setText(detalle);
        txtBuscar.setToolTipText(placeholder);
    }

    private void configurarComponentes() {
        getContentPane().setBackground(Color.decode("#0A0C10"));
        setSize(1100, 680);

        configurarTabla(tblMaestro);
        configurarTabla(tblDetalle);

        scrollMaestro.getViewport().setBackground(Color.decode("#0E1219"));
        scrollDetalle.getViewport().setBackground(Color.decode("#0E1219"));
        scrollMaestro.getVerticalScrollBar().setUnitIncrement(16);
        scrollDetalle.getVerticalScrollBar().setUnitIncrement(16);
        scrollMaestro.getHorizontalScrollBar().setUnitIncrement(16);
        scrollDetalle.getHorizontalScrollBar().setUnitIncrement(16);
    }

    private void configurarTabla(javax.swing.JTable tabla) {
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setFillsViewportHeight(true);
        tabla.setOpaque(true);
        tabla.setShowGrid(true);
        tabla.getTableHeader().setBackground(Color.decode("#181D2E"));
        tabla.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tabla.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        tabla.getTableHeader().setReorderingAllowed(false);
        ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    @Override
    public JLabel getLblMaestro() {
        return lblMaestro;
    }

    @Override
    public JLabel getLblDetalle() {
        return lblDetalle;
    }

    @Override
    public JLabel getLblInfo() {
        return lblInfo;
    }

    @Override
    public javax.swing.JTextField getTxtBuscar() {
        return txtBuscar;
    }

    @Override
    public javax.swing.JButton getBtnBuscar() {
        return btnBuscar;
    }

    @Override
    public javax.swing.JButton getBtnActualizar() {
        return btnActualizar;
    }

    @Override
    public javax.swing.JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    @Override
    public javax.swing.JTable getTblMaestro() {
        return tblMaestro;
    }

    @Override
    public javax.swing.JTable getTblDetalle() {
        return tblDetalle;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        panelFiltros = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        panelMaestro = new javax.swing.JPanel();
        lblMaestro = new javax.swing.JLabel();
        scrollMaestro = new javax.swing.JScrollPane();
        tblMaestro = new javax.swing.JTable();
        panelDetalle = new javax.swing.JPanel();
        lblDetalle = new javax.swing.JLabel();
        scrollDetalle = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        lblInfo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Productos - Lotes");
        setPreferredSize(new java.awt.Dimension(1100, 680));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 242, 255));
        lblTitulo.setText("✦  Productos - Lotes");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(30, 20, 520, 32);

        panelFiltros.setBackground(new java.awt.Color(17, 21, 32));
        panelFiltros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelFiltros.setLayout(null);

        lblBuscar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(155, 163, 196));
        lblBuscar.setText("BUSCAR");
        panelFiltros.add(lblBuscar);
        lblBuscar.setBounds(20, 14, 210, 16);

        txtBuscar.setBackground(new java.awt.Color(24, 29, 46));
        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(240, 242, 255));
        txtBuscar.setToolTipText("Buscar por producto o código de barras");
        txtBuscar.setCaretColor(new java.awt.Color(108, 99, 255));
        txtBuscar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelFiltros.add(txtBuscar);
        txtBuscar.setBounds(20, 34, 520, 38);

        btnBuscar.setBackground(new java.awt.Color(108, 99, 255));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setFocusPainted(false);
        panelFiltros.add(btnBuscar);
        btnBuscar.setBounds(560, 34, 130, 38);

        btnActualizar.setBackground(new java.awt.Color(31, 38, 64));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(240, 242, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setFocusPainted(false);
        panelFiltros.add(btnActualizar);
        btnActualizar.setBounds(700, 34, 130, 38);

        btnLimpiar.setBackground(new java.awt.Color(30, 10, 16));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 91, 122));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 42, 58)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setFocusPainted(false);
        panelFiltros.add(btnLimpiar);
        btnLimpiar.setBounds(840, 34, 130, 38);

        getContentPane().add(panelFiltros);
        panelFiltros.setBounds(30, 65, 1020, 92);

        panelMaestro.setBackground(new java.awt.Color(17, 21, 32));
        panelMaestro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelMaestro.setLayout(null);

        lblMaestro.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblMaestro.setForeground(new java.awt.Color(240, 242, 255));
        lblMaestro.setText("Productos");
        panelMaestro.add(lblMaestro);
        lblMaestro.setBounds(18, 10, 400, 22);

        scrollMaestro.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblMaestro.setBackground(new java.awt.Color(14, 18, 25));
        tblMaestro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblMaestro.setForeground(new java.awt.Color(240, 242, 255));
        tblMaestro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblMaestro.setGridColor(new java.awt.Color(26, 31, 48));
        tblMaestro.setRowHeight(30);
        tblMaestro.setSelectionBackground(new java.awt.Color(108, 99, 255));
        tblMaestro.setSelectionForeground(new java.awt.Color(255, 255, 255));
        scrollMaestro.setViewportView(tblMaestro);

        panelMaestro.add(scrollMaestro);
        scrollMaestro.setBounds(18, 40, 984, 165);

        getContentPane().add(panelMaestro);
        panelMaestro.setBounds(30, 170, 1020, 220);

        panelDetalle.setBackground(new java.awt.Color(17, 21, 32));
        panelDetalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDetalle.setLayout(null);

        lblDetalle.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblDetalle.setForeground(new java.awt.Color(240, 242, 255));
        lblDetalle.setText("Lotes del producto seleccionado");
        panelDetalle.add(lblDetalle);
        lblDetalle.setBounds(18, 10, 620, 22);

        scrollDetalle.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblDetalle.setBackground(new java.awt.Color(14, 18, 25));
        tblDetalle.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblDetalle.setForeground(new java.awt.Color(240, 242, 255));
        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDetalle.setGridColor(new java.awt.Color(26, 31, 48));
        tblDetalle.setRowHeight(30);
        tblDetalle.setSelectionBackground(new java.awt.Color(108, 99, 255));
        tblDetalle.setSelectionForeground(new java.awt.Color(255, 255, 255));
        scrollDetalle.setViewportView(tblDetalle);

        panelDetalle.add(scrollDetalle);
        scrollDetalle.setBounds(18, 40, 984, 165);

        getContentPane().add(panelDetalle);
        panelDetalle.setBounds(30, 405, 1020, 220);

        lblInfo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(155, 163, 196));
        lblInfo.setText("Selecciona un registro maestro para ver su detalle.");
        getContentPane().add(lblInfo);
        lblInfo.setBounds(30, 632, 1020, 22);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel lblBuscar;
    public javax.swing.JLabel lblDetalle;
    public javax.swing.JLabel lblInfo;
    public javax.swing.JLabel lblMaestro;
    public javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelDetalle;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JPanel panelMaestro;
    private javax.swing.JScrollPane scrollDetalle;
    private javax.swing.JScrollPane scrollMaestro;
    public javax.swing.JTable tblDetalle;
    public javax.swing.JTable tblMaestro;
    public javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
