package Vistas;

// import Controladores.CtrlFactura.CtrlConsultarFactura; // <- Descomenta esto para vincular el controlador
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmFacturaConsultar extends javax.swing.JInternalFrame {

    public FrmFacturaConsultar() {
        initComponents();
        
        // Estilo de tabla
        tblConsultaFacturas.getTableHeader().setBackground(Color.decode("#181D2E"));
        tblConsultaFacturas.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tblConsultaFacturas.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        tblConsultaFacturas.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050")));
        ((DefaultTableCellRenderer) tblConsultaFacturas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Hover btn Anular
        btnAnularFactura.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnAnularFactura.setBackground(Color.decode("#FF5B7A")); btnAnularFactura.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e) { btnAnularFactura.setBackground(Color.decode("#1E0A10")); btnAnularFactura.setForeground(Color.decode("#FF5B7A")); }
        });

        // CtrlConsultarFactura controlador = new CtrlConsultarFactura(this); // <- Descomenta para inicializar tu controlador
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultaFacturas = new javax.swing.JTable();
        btnAnularFactura = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consultar Facturas");
        setPreferredSize(new java.awt.Dimension(900, 560));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 242, 255));
        jLabel1.setText("✦  Consulta de Facturas Emitidas");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 20, 400, 30);

        panelDatos.setBackground(new java.awt.Color(17, 21, 32));
        panelDatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelDatos.setLayout(null);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));

        tblConsultaFacturas.setBackground(new java.awt.Color(14, 18, 25));
        tblConsultaFacturas.setForeground(new java.awt.Color(240, 242, 255));
        tblConsultaFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblConsultaFacturas.setGridColor(new java.awt.Color(26, 31, 48));
        tblConsultaFacturas.setRowHeight(30);
        tblConsultaFacturas.setSelectionBackground(new java.awt.Color(108, 99, 255));
        tblConsultaFacturas.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblConsultaFacturas);

        panelDatos.add(jScrollPane1);
        jScrollPane1.setBounds(20, 20, 620, 390);

        btnAnularFactura.setBackground(new java.awt.Color(30, 10, 16));
        btnAnularFactura.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAnularFactura.setForeground(new java.awt.Color(255, 91, 122));
        btnAnularFactura.setText("Anular Factura");
        btnAnularFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnAnularFactura.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAnularFactura.setFocusPainted(false);
        panelDatos.add(btnAnularFactura);
        btnAnularFactura.setBounds(660, 20, 140, 42);

        getContentPane().add(panelDatos);
        panelDatos.setBounds(30, 70, 820, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnularFactura;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JTable tblConsultaFacturas;
    // End of variables declaration//GEN-END:variables
}