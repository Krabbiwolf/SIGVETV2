package Vistas;

import java.awt.*;
import javax.swing.*;

public class FrmAnaliticas extends javax.swing.JInternalFrame {

    private static final Color BG_MAIN   = Color.decode("#0A0C10");
    private static final Color BG_CARD   = Color.decode("#111520");
    private static final Color BORDER    = Color.decode("#2A3050");
    private static final Color ACCENT    = Color.decode("#6C63FF");
    private static final Color TEXT_PRI  = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT  = Color.decode("#9BA3C4");

    private static final int PADX    = 24;

    public FrmAnaliticas() {
        initComponents();
        this.setSize(new Dimension(850, 600));
        this.setPreferredSize(new Dimension(850, 600));
        this.setTitle("Analíticas y Reportes");
        aplicarEstiloMinimalistaPremium();
    }

    private void aplicarEstiloMinimalistaPremium() {
        this.getContentPane().setBackground(BG_MAIN);

        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setText("✦  Analíticas de Rendimiento");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, 20, 400, 30));

        // --- PANEL DE GRÁFICO ---
        panelGrafico.setBackground(BG_CARD);
        panelGrafico.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        
        lblSubtitulo.setForeground(TEXT_MUT);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSubtitulo.setText("Ventas de los últimos 7 días");
        panelGrafico.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, 300, 20));

        // Gráfico de barras simulado
        int[] alturasBarras = {150, 200, 120, 280, 90, 250, 320};
        String[] dias = {"LUN", "MAR", "MIE", "JUE", "VIE", "SAB", "DOM"};
        
        int xBarra = 60;
        for (int i = 0; i < alturasBarras.length; i++) {
            JPanel barra = new JPanel();
            barra.setBackground(ACCENT);
            panelGrafico.add(barra, new org.netbeans.lib.awtextra.AbsoluteConstraints(xBarra, 400 - alturasBarras[i], 50, alturasBarras[i]));

            JLabel lblDia = new JLabel(dias[i], SwingConstants.CENTER);
            lblDia.setForeground(TEXT_MUT);
            lblDia.setFont(new Font("Segoe UI", Font.BOLD, 11));
            panelGrafico.add(lblDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(xBarra, 410, 50, 20));

            xBarra += 95; // Espaciado horizontal
        }

        getContentPane().add(panelGrafico, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, 70, 780, 450));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        lblTitulo = new javax.swing.JLabel();
        panelGrafico = new javax.swing.JPanel();
        lblSubtitulo = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelGrafico.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pack();
    }

    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelGrafico;
}