package Vistas;

import Modelos.AnaliticasDAO;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FrmDashboard extends javax.swing.JInternalFrame {

    private final AnaliticasDAO dao;

    public FrmDashboard() {
        initComponents();
        getContentPane().setBackground(Color.decode("#F0F4F8"));
        dao = new AnaliticasDAO();
        
        // Iconos cargados vía Unicode de manera limpia
        lblIcon1.setText("\uD83D\uDC65"); 
        lblIcon2.setText("\uD83D\uDCE6"); 
        lblIcon3.setText("\uD83D\uDCB5"); 
        lblIcon4.setText("\u26A0\uFE0F");  

        estilizarTabla();
        cargarDatosDashboard();
    }

    private void estilizarTabla() {
        tblCriticos.getTableHeader().setBackground(Color.decode("#DCE6F2"));
        tblCriticos.getTableHeader().setForeground(Color.decode("#2D4A8A"));
        tblCriticos.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        ((DefaultTableCellRenderer) tblCriticos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }

    private void cargarDatosDashboard() {
        new SwingWorker<Void, Object[]>() {
            int clientes, productos, alertas;
            double ventasHoy;
            ArrayList<Object[]> listaCriticos;

            @Override
            protected Void doInBackground() {
                clientes = dao.getTotalClientes();
                productos = dao.getTotalProductos();
                ventasHoy = dao.getVentasDelDia();
                alertas = dao.getAlertasStock();
                listaCriticos = dao.obtenerLotesCriticos();
                return null;
            }

            @Override
            protected void done() {
                // Formateamos las ventas con coma para miles (%,.2f)
                lblVal1.setText(String.format("%,d", clientes));
                lblVal2.setText(String.format("%,d", productos));
                lblVal3.setText(String.format("$ %,.2f", ventasHoy));
                lblVal4.setText(String.format("%,d", alertas));

                DefaultTableModel model = (DefaultTableModel) tblCriticos.getModel();
                model.setRowCount(0);
                for (Object[] fila : listaCriticos) {
                    model.addRow(fila);
                }
            }
        }.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        panelKpi1 = new javax.swing.JPanel();
        lblIcon1 = new javax.swing.JLabel();
        lblVal1 = new javax.swing.JLabel();
        lblTit1 = new javax.swing.JLabel();
        panelKpi2 = new javax.swing.JPanel();
        lblIcon2 = new javax.swing.JLabel();
        lblVal2 = new javax.swing.JLabel();
        lblTit2 = new javax.swing.JLabel();
        panelKpi3 = new javax.swing.JPanel();
        lblIcon3 = new javax.swing.JLabel();
        lblVal3 = new javax.swing.JLabel();
        lblTit3 = new javax.swing.JLabel();
        panelKpi4 = new javax.swing.JPanel();
        lblIcon4 = new javax.swing.JLabel();
        lblVal4 = new javax.swing.JLabel();
        lblTit4 = new javax.swing.JLabel();
        panelActividad = new javax.swing.JPanel();
        lblActividad = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCriticos = new javax.swing.JTable();

        setBackground(new java.awt.Color(240, 244, 248));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Dashboard General");
        setPreferredSize(new java.awt.Dimension(950, 600));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(45, 74, 138));
        lblTitulo.setText("Centro de Control SIGVET");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(25, 20, 400, 30);

        panelKpi1.setBackground(new java.awt.Color(255, 255, 255));
        panelKpi1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(45, 74, 138))));
        panelKpi1.setLayout(null);

        lblIcon1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 36)); // NOI18N
        panelKpi1.add(lblIcon1);
        lblIcon1.setBounds(15, 20, 50, 50);

        lblVal1.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblVal1.setForeground(new java.awt.Color(51, 51, 51));
        lblVal1.setText("0");
        panelKpi1.add(lblVal1);
        lblVal1.setBounds(65, 20, 130, 35);

        lblTit1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTit1.setForeground(new java.awt.Color(153, 153, 153));
        lblTit1.setText("Clientes Activos");
        panelKpi1.add(lblTit1);
        lblTit1.setBounds(15, 75, 170, 20);

        getContentPane().add(panelKpi1);
        panelKpi1.setBounds(25, 70, 200, 110);

        panelKpi2.setBackground(new java.awt.Color(255, 255, 255));
        panelKpi2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(40, 167, 69))));
        panelKpi2.setLayout(null);

        lblIcon2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 36)); // NOI18N
        panelKpi2.add(lblIcon2);
        lblIcon2.setBounds(15, 20, 50, 50);

        lblVal2.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblVal2.setForeground(new java.awt.Color(51, 51, 51));
        lblVal2.setText("0");
        panelKpi2.add(lblVal2);
        lblVal2.setBounds(65, 20, 130, 35);

        lblTit2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTit2.setForeground(new java.awt.Color(153, 153, 153));
        lblTit2.setText("Total Productos");
        panelKpi2.add(lblTit2);
        lblTit2.setBounds(15, 75, 170, 20);

        getContentPane().add(panelKpi2);
        panelKpi2.setBounds(245, 70, 200, 110);

        panelKpi3.setBackground(new java.awt.Color(255, 255, 255));
        panelKpi3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(255, 193, 7))));
        panelKpi3.setLayout(null);

        lblIcon3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 36)); // NOI18N
        panelKpi3.add(lblIcon3);
        lblIcon3.setBounds(15, 20, 50, 50);

        lblVal3.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblVal3.setForeground(new java.awt.Color(51, 51, 51));
        lblVal3.setText("$ 0.00");
        panelKpi3.add(lblVal3);
        lblVal3.setBounds(65, 20, 130, 35);

        lblTit3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTit3.setForeground(new java.awt.Color(153, 153, 153));
        lblTit3.setText("Ventas del Día");
        panelKpi3.add(lblTit3);
        lblTit3.setBounds(15, 75, 170, 20);

        getContentPane().add(panelKpi3);
        panelKpi3.setBounds(465, 70, 200, 110);

        panelKpi4.setBackground(new java.awt.Color(255, 255, 255));
        panelKpi4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(230, 57, 70))));
        panelKpi4.setLayout(null);

        lblIcon4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 36)); // NOI18N
        panelKpi4.add(lblIcon4);
        lblIcon4.setBounds(15, 20, 50, 50);

        lblVal4.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        lblVal4.setForeground(new java.awt.Color(51, 51, 51));
        lblVal4.setText("0");
        panelKpi4.add(lblVal4);
        lblVal4.setBounds(65, 20, 130, 35);

        lblTit4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTit4.setForeground(new java.awt.Color(153, 153, 153));
        lblTit4.setText("Alertas de Stock");
        panelKpi4.add(lblTit4);
        lblTit4.setBounds(15, 75, 170, 20);

        getContentPane().add(panelKpi4);
        panelKpi4.setBounds(685, 70, 200, 110);

        panelActividad.setBackground(new java.awt.Color(255, 255, 255));
        panelActividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelActividad.setLayout(null);

        lblActividad.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblActividad.setForeground(new java.awt.Color(45, 74, 138));
        lblActividad.setText("Alertas de Inventario Crítico (Lotes Próximos a Agotarse)");
        panelActividad.add(lblActividad);
        lblActividad.setBounds(20, 15, 500, 25);

        tblCriticos.setForeground(new java.awt.Color(51, 51, 51));
        tblCriticos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Número de Lote", "Stock Actual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCriticos.setGridColor(new java.awt.Color(240, 244, 248));
        tblCriticos.setRowHeight(30);
        tblCriticos.setSelectionBackground(new java.awt.Color(197, 216, 245));
        tblCriticos.setSelectionForeground(new java.awt.Color(13, 32, 96));
        jScrollPane1.setViewportView(tblCriticos);

        panelActividad.add(jScrollPane1);
        jScrollPane1.setBounds(20, 50, 820, 240);

        getContentPane().add(panelActividad);
        panelActividad.setBounds(25, 210, 860, 320);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblActividad;
    private javax.swing.JLabel lblIcon1;
    private javax.swing.JLabel lblIcon2;
    private javax.swing.JLabel lblIcon3;
    private javax.swing.JLabel lblIcon4;
    private javax.swing.JLabel lblTit1;
    private javax.swing.JLabel lblTit2;
    private javax.swing.JLabel lblTit3;
    private javax.swing.JLabel lblTit4;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblVal1;
    private javax.swing.JLabel lblVal2;
    private javax.swing.JLabel lblVal3;
    private javax.swing.JLabel lblVal4;
    private javax.swing.JPanel panelActividad;
    private javax.swing.JPanel panelKpi1;
    private javax.swing.JPanel panelKpi2;
    private javax.swing.JPanel panelKpi3;
    private javax.swing.JPanel panelKpi4;
    public javax.swing.JTable tblCriticos;
    // End of variables declaration//GEN-END:variables
}