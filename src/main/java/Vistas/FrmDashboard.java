package Vistas;

public class FrmDashboard extends javax.swing.JInternalFrame {

    public FrmDashboard() {
        initComponents();
        // Las tarjetas ahora están integradas directamente en el archivo .form
        // para que sean nativas de NetBeans y puedas editarlas visualmente.
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
        lblInfo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Dashboard General");
        setPreferredSize(new java.awt.Dimension(920, 600));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 242, 255));
        lblTitulo.setText("✦  Dashboard y Resumen SIGVET");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(24, 20, 400, 30);

        panelKpi1.setBackground(new java.awt.Color(17, 21, 32));
        panelKpi1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(108, 99, 255))));
        panelKpi1.setLayout(null);

        lblIcon1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 28)); // NOI18N
        lblIcon1.setText("👥");
        panelKpi1.add(lblIcon1);
        lblIcon1.setBounds(15, 20, 40, 40);

        lblVal1.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblVal1.setForeground(new java.awt.Color(240, 242, 255));
        lblVal1.setText("12");
        panelKpi1.add(lblVal1);
        lblVal1.setBounds(65, 20, 130, 35);

        lblTit1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTit1.setForeground(new java.awt.Color(155, 163, 196));
        lblTit1.setText("Usuarios Activos");
        panelKpi1.add(lblTit1);
        lblTit1.setBounds(15, 75, 170, 20);

        getContentPane().add(panelKpi1);
        panelKpi1.setBounds(24, 70, 200, 110);

        panelKpi2.setBackground(new java.awt.Color(17, 21, 32));
        panelKpi2.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(0, 212, 170))));
        panelKpi2.setLayout(null);

        lblIcon2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 28)); // NOI18N
        lblIcon2.setText("📦");
        panelKpi2.add(lblIcon2);
        lblIcon2.setBounds(15, 20, 40, 40);

        lblVal2.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblVal2.setForeground(new java.awt.Color(240, 242, 255));
        lblVal2.setText("1,245");
        panelKpi2.add(lblVal2);
        lblVal2.setBounds(65, 20, 130, 35);

        lblTit2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTit2.setForeground(new java.awt.Color(155, 163, 196));
        lblTit2.setText("Total Productos");
        panelKpi2.add(lblTit2);
        lblTit2.setBounds(15, 75, 170, 20);

        getContentPane().add(panelKpi2);
        panelKpi2.setBounds(244, 70, 200, 110);

        panelKpi3.setBackground(new java.awt.Color(17, 21, 32));
        panelKpi3.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(255, 181, 71))));
        panelKpi3.setLayout(null);

        lblIcon3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 28)); // NOI18N
        lblIcon3.setText("💵");
        panelKpi3.add(lblIcon3);
        lblIcon3.setBounds(15, 20, 40, 40);

        lblVal3.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblVal3.setForeground(new java.awt.Color(240, 242, 255));
        lblVal3.setText("$ 845.50");
        panelKpi3.add(lblVal3);
        lblVal3.setBounds(65, 20, 130, 35);

        lblTit3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTit3.setForeground(new java.awt.Color(155, 163, 196));
        lblTit3.setText("Ventas del Día");
        panelKpi3.add(lblTit3);
        lblTit3.setBounds(15, 75, 170, 20);

        getContentPane().add(panelKpi3);
        panelKpi3.setBounds(464, 70, 200, 110);

        panelKpi4.setBackground(new java.awt.Color(17, 21, 32));
        panelKpi4.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(255, 107, 157))));
        panelKpi4.setLayout(null);

        lblIcon4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 28)); // NOI18N
        lblIcon4.setText("⚠️");
        panelKpi4.add(lblIcon4);
        lblIcon4.setBounds(15, 20, 40, 40);

        lblVal4.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblVal4.setForeground(new java.awt.Color(240, 242, 255));
        lblVal4.setText("5");
        panelKpi4.add(lblVal4);
        lblVal4.setBounds(65, 20, 130, 35);

        lblTit4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTit4.setForeground(new java.awt.Color(155, 163, 196));
        lblTit4.setText("Alertas Stock");
        panelKpi4.add(lblTit4);
        lblTit4.setBounds(15, 75, 170, 20);

        getContentPane().add(panelKpi4);
        panelKpi4.setBounds(684, 70, 200, 110);

        panelActividad.setBackground(new java.awt.Color(17, 21, 32));
        panelActividad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelActividad.setLayout(null);

        lblActividad.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblActividad.setForeground(new java.awt.Color(240, 242, 255));
        lblActividad.setText("Actividad Reciente");
        panelActividad.add(lblActividad);
        lblActividad.setBounds(20, 15, 200, 25);

        lblInfo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(155, 163, 196));
        lblInfo.setText("El área de gráficos y reportes de actividad se cargará en este espacio.");
        panelActividad.add(lblInfo);
        lblInfo.setBounds(20, 60, 600, 25);

        getContentPane().add(panelActividad);
        panelActividad.setBounds(24, 210, 860, 320);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblActividad;
    private javax.swing.JLabel lblIcon1;
    private javax.swing.JLabel lblIcon2;
    private javax.swing.JLabel lblIcon3;
    private javax.swing.JLabel lblIcon4;
    private javax.swing.JLabel lblInfo;
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
    // End of variables declaration//GEN-END:variables
}