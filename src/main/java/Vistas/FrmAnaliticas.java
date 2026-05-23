package Vistas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FrmAnaliticas extends javax.swing.JInternalFrame {

    public FrmAnaliticas() {
        initComponents();
        // Mantenemos tu gráfico dinámico original, pero asegurado.
        generarGrafico();
    }

    private void generarGrafico() {
        int[] alturasBarras = {150, 200, 120, 280, 90, 250, 320};
        String[] dias = {"LUN", "MAR", "MIE", "JUE", "VIE", "SAB", "DOM"};
        
        int xBarra = 60;
        for (int i = 0; i < alturasBarras.length; i++) {
            JPanel barra = new JPanel();
            barra.setBackground(Color.decode("#6C63FF"));
            panelGrafico.add(barra, new org.netbeans.lib.awtextra.AbsoluteConstraints(xBarra, 400 - alturasBarras[i], 50, alturasBarras[i]));

            JLabel lblDia = new JLabel(dias[i], SwingConstants.CENTER);
            lblDia.setForeground(Color.decode("#9BA3C4"));
            lblDia.setFont(new Font("Segoe UI", Font.BOLD, 11));
            panelGrafico.add(lblDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(xBarra, 410, 50, 20));

            xBarra += 95;
        }
        
        // Refrescar el panel para que muestre el gráfico dibujado dinámicamente
        panelGrafico.revalidate();
        panelGrafico.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        panelGrafico = new javax.swing.JPanel();
        lblSubtitulo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Analíticas y Reportes");
        setPreferredSize(new java.awt.Dimension(850, 600));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 242, 255));
        lblTitulo.setText("✦  Analíticas de Rendimiento");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(24, 20, 400, 30);

        panelGrafico.setBackground(new java.awt.Color(17, 21, 32));
        panelGrafico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelGrafico.setLayout(null);

        lblSubtitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(155, 163, 196));
        lblSubtitulo.setText("Ventas de los últimos 7 días");
        panelGrafico.add(lblSubtitulo);
        lblSubtitulo.setBounds(20, 15, 300, 20);

        getContentPane().add(panelGrafico);
        panelGrafico.setBounds(24, 70, 780, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelGrafico;
    // End of variables declaration//GEN-END:variables
}