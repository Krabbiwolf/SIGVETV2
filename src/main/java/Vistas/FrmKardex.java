package Vistas;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Controladores.CtrlKardex.CtrlKardex;

public class FrmKardex extends javax.swing.JInternalFrame {

    public FrmKardex() {
        initComponents();
        
       
        
        // Inicializar Controlador
        new CtrlKardex(this);
    }

    // Métodos Originales Respetados
    public Date getFechaInicio() {
        return (Date) spFechaInicio.getValue();
    }

    public Date getFechaFin() {
        return (Date) spFechaFin.getValue();
    }

    public void cargarModelo(DefaultTableModel modelo) {
        tblKardex.setModel(modelo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        panelFiltros = new javax.swing.JPanel();
        lblInicio = new javax.swing.JLabel();
        spFechaInicio = new javax.swing.JSpinner();
        lblFin = new javax.swing.JLabel();
        spFechaFin = new javax.swing.JSpinner();
        lblTipo = new javax.swing.JLabel();
        cboTipoMovimiento = new javax.swing.JComboBox<>();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tblKardex = new javax.swing.JTable();

        setBackground(new java.awt.Color(240, 244, 248));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Kardex - Historial de Movimientos");
        setPreferredSize(new java.awt.Dimension(1100, 620));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(45, 74, 138));
        lblTitulo.setText("Kardex / Historial de Movimientos");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(30, 20, 400, 30);

        panelFiltros.setBackground(new java.awt.Color(240, 244, 248));
        panelFiltros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelFiltros.setLayout(null);

        lblInicio.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblInicio.setForeground(new java.awt.Color(45, 74, 138));
        lblInicio.setText("FECHA INICIO");
        panelFiltros.add(lblInicio);
        lblInicio.setBounds(20, 20, 200, 16);

        spFechaInicio.setModel(new javax.swing.SpinnerDateModel());
        spFechaInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelFiltros.add(spFechaInicio);
        spFechaInicio.setBounds(20, 40, 200, 38);

        lblFin.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblFin.setForeground(new java.awt.Color(45, 74, 138));
        lblFin.setText("FECHA FIN");
        panelFiltros.add(lblFin);
        lblFin.setBounds(240, 20, 200, 16);

        spFechaFin.setModel(new javax.swing.SpinnerDateModel());
        spFechaFin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelFiltros.add(spFechaFin);
        spFechaFin.setBounds(240, 40, 200, 38);

        lblTipo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTipo.setForeground(new java.awt.Color(45, 74, 138));
        lblTipo.setText("TIPO DE MOVIMIENTO");
        panelFiltros.add(lblTipo);
        lblTipo.setBounds(460, 20, 200, 16);

        cboTipoMovimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "ENTRADA", "SALIDA", "AJUSTE" }));
        cboTipoMovimiento.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cboTipoMovimiento.setForeground(new java.awt.Color(51, 51, 51));
        cboTipoMovimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelFiltros.add(cboTipoMovimiento);
        cboTipoMovimiento.setBounds(460, 40, 200, 38);

        lblBuscar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(45, 74, 138));
        lblBuscar.setText("BÚSQUEDA / CÓDIGO / NOMBRE");
        panelFiltros.add(lblBuscar);
        lblBuscar.setBounds(680, 20, 300, 16);

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscar.setToolTipText("Buscar por código de barras o nombre del producto");
        txtBuscar.setCaretColor(new java.awt.Color(45, 74, 138));
        txtBuscar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelFiltros.add(txtBuscar);
        txtBuscar.setBounds(680, 40, 310, 38);

        btnFiltrar.setBackground(new java.awt.Color(45, 74, 138));
        btnFiltrar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.setText("Filtrar / Buscar");
        btnFiltrar.setBorderPainted(false);
        btnFiltrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnFiltrar.setFocusPainted(false);
        panelFiltros.add(btnFiltrar);
        btnFiltrar.setBounds(20, 95, 150, 40);

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(45, 74, 138));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setFocusPainted(false);
        panelFiltros.add(btnLimpiar);
        btnLimpiar.setBounds(180, 95, 150, 40);

        btnExportar.setBackground(new java.awt.Color(40, 167, 69));
        btnExportar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnExportar.setText("📊 Exportar a Excel");
        btnExportar.setBorderPainted(false);
        btnExportar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExportar.setFocusPainted(false);
        panelFiltros.add(btnExportar);
        btnExportar.setBounds(340, 95, 160, 40);

        getContentPane().add(panelFiltros);
        panelFiltros.setBounds(30, 70, 1020, 150);

        scrollTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));

        tblKardex.setForeground(new java.awt.Color(51, 51, 51));
        tblKardex.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblKardex.setGridColor(new java.awt.Color(224, 224, 224));
        tblKardex.setRowHeight(28);
        tblKardex.setSelectionBackground(new java.awt.Color(197, 216, 245));
        tblKardex.setSelectionForeground(new java.awt.Color(13, 32, 96));
        scrollTabla.setViewportView(tblKardex);

        getContentPane().add(scrollTabla);
        scrollTabla.setBounds(30, 240, 1020, 330);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnExportar;
    public javax.swing.JButton btnFiltrar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JComboBox<String> cboTipoMovimiento;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblFin;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JScrollPane scrollTabla;
    public javax.swing.JSpinner spFechaFin;
    public javax.swing.JSpinner spFechaInicio;
    public javax.swing.JTable tblKardex;
    public javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}