package Vistas;

// import Controladores.CtrlFactura.CtrlConsultarFactura; // <- Descomenta esto para vincular el controlador
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmConsultarCompras extends javax.swing.JInternalFrame {

    public FrmConsultarCompras() {
        initComponents();
        
        

        // CtrlConsultarFactura controlador = new CtrlConsultarFactura(this); // <- Descomenta para inicializar tu controlador
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultaCompras = new javax.swing.JTable();
        btnAnularCompra = new javax.swing.JButton();
        spFechaInicio = new javax.swing.JSpinner();
        spFechaFin = new javax.swing.JSpinner();
        btnFiltrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        lblInicio = new javax.swing.JLabel();
        lblFin = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();

        setBackground(new java.awt.Color(244, 246, 249));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consultar Facturas");
        setPreferredSize(new java.awt.Dimension(900, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));

        tblConsultaCompras.setBackground(new java.awt.Color(255, 255, 255));
        tblConsultaCompras.setForeground(new java.awt.Color(51, 51, 51));
        tblConsultaCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Numero", "Fecha", "Estado", "Proveedor", "Empleado"
            }
        ));
        tblConsultaCompras.setGridColor(new java.awt.Color(224, 229, 236));
        tblConsultaCompras.setRowHeight(35);
        tblConsultaCompras.setSelectionBackground(new java.awt.Color(217, 226, 243));
        tblConsultaCompras.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(tblConsultaCompras);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 620, 390));

        btnAnularCompra.setBackground(new java.awt.Color(248, 249, 250));
        btnAnularCompra.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAnularCompra.setForeground(new java.awt.Color(255, 51, 51));
        btnAnularCompra.setText("Anular");
        btnAnularCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAnularCompra.setFocusPainted(false);
        getContentPane().add(btnAnularCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 280, 140, 42));

        spFechaInicio.setModel(new javax.swing.SpinnerDateModel());
        spFechaInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        getContentPane().add(spFechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 200, 38));

        spFechaFin.setModel(new javax.swing.SpinnerDateModel());
        spFechaFin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        getContentPane().add(spFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 200, 38));

        btnFiltrar.setBackground(new java.awt.Color(45, 74, 138));
        btnFiltrar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnFiltrar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltrar.setText("Filtrar / Buscar");
        btnFiltrar.setBorderPainted(false);
        btnFiltrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnFiltrar.setFocusPainted(false);
        getContentPane().add(btnFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 150, 40));

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(45, 74, 138));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setFocusPainted(false);
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 150, 40));

        btnExportar.setBackground(new java.awt.Color(40, 167, 69));
        btnExportar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnExportar.setForeground(new java.awt.Color(255, 255, 255));
        btnExportar.setText("Exportar a Excel");
        btnExportar.setBorderPainted(false);
        btnExportar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExportar.setFocusPainted(false);
        getContentPane().add(btnExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 160, 40));

        lblInicio.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblInicio.setForeground(new java.awt.Color(45, 74, 138));
        lblInicio.setText("FECHA INICIO");
        getContentPane().add(lblInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 200, 16));

        lblFin.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblFin.setForeground(new java.awt.Color(45, 74, 138));
        lblFin.setText("FECHA FIN");
        getContentPane().add(lblFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 200, 16));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(32, 56, 100));
        jLabel1.setText("Consulta de Compras Realizadas");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 400, 30));

        btnImprimir.setBackground(new java.awt.Color(248, 249, 250));
        btnImprimir.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnImprimir.setForeground(new java.awt.Color(43, 68, 122));
        btnImprimir.setText("Imprimir");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnImprimir.setFocusPainted(false);
        getContentPane().add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 140, 42));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAnularCompra;
    public javax.swing.JButton btnExportar;
    public javax.swing.JButton btnFiltrar;
    public javax.swing.JButton btnImprimir;
    public javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFin;
    private javax.swing.JLabel lblInicio;
    public javax.swing.JSpinner spFechaFin;
    public javax.swing.JSpinner spFechaInicio;
    public javax.swing.JTable tblConsultaCompras;
    // End of variables declaration//GEN-END:variables
}