package Vistas;

import java.awt.*;
import javax.swing.*;

public class FrmDashboard extends javax.swing.JInternalFrame {

    private static final Color BG_MAIN   = Color.decode("#0A0C10");
    private static final Color BG_CARD   = Color.decode("#111520");
    private static final Color BORDER    = Color.decode("#2A3050");
    private static final Color TEXT_PRI  = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT  = Color.decode("#9BA3C4");
    
    // Acentos de colores para las tarjetas
    private static final Color[] COLORS = {
        Color.decode("#6C63FF"), Color.decode("#00D4AA"), 
        Color.decode("#FFB547"), Color.decode("#FF6B9D")
    };

    // ── Medidas uniformes ────────────────────────────────────────────────────
    private static final int PADX    = 24;
    private static final int CARD_W  = 200;
    private static final int CARD_H  = 110;
    private static final int GAP_X   = 20;

    public FrmDashboard() {
        initComponents();
        this.setSize(new Dimension(920, 600));
        this.setPreferredSize(new Dimension(920, 600));
        this.setTitle("Dashboard General");
        aplicarEstiloMinimalistaPremium();
    }

    private void aplicarEstiloMinimalistaPremium() {
        this.getContentPane().setBackground(BG_MAIN);

        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setText("✦  Dashboard y Resumen SIGVET");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, 20, 400, 30));

        // --- TARJETAS SUPERIORES (KPIs) ---
        String[] titulos = {"Usuarios Activos", "Total Productos", "Ventas del Día", "Alertas Stock"};
        String[] valores = {"12", "1,245", "$ 845.50", "5"};
        String[] iconos  = {"👥", "📦", "💵", "⚠️"};

        int xCurrent = PADX;
        for (int i = 0; i < 4; i++) {
            JPanel card = crearTarjetaKPI(titulos[i], valores[i], iconos[i], COLORS[i]);
            getContentPane().add(card, new org.netbeans.lib.awtextra.AbsoluteConstraints(xCurrent, 70, CARD_W, CARD_H));
            xCurrent += CARD_W + GAP_X;
        }

        // --- PANEL ACTIVIDAD INFERIOR ---
        panelActividad.setBackground(BG_CARD);
        panelActividad.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        
        lblActividad.setForeground(TEXT_PRI);
        lblActividad.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblActividad.setText("Actividad Reciente");
        panelActividad.add(lblActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, 200, 25));

        lblInfo.setForeground(TEXT_MUT);
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblInfo.setText("El área de gráficos y reportes de actividad se cargará en este espacio.");
        panelActividad.add(lblInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 600, 25));

        getContentPane().add(panelActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, 210, 860, 320));
    }

    private JPanel crearTarjetaKPI(String titulo, String valor, String icono, Color colorAcento) {
        JPanel card = new JPanel();
        card.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createMatteBorder(4, 0, 0, 0, colorAcento)
        ));

        JLabel lblIcono = new JLabel(icono);
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        card.add(lblIcono, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 40, 40));

        JLabel lblValor = new JLabel(valor);
        lblValor.setForeground(TEXT_PRI);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 26));
        card.add(lblValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 20, 130, 35));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(TEXT_MUT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        card.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 75, 170, 20));

        return card;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        lblTitulo = new javax.swing.JLabel();
        panelActividad = new javax.swing.JPanel();
        lblActividad = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelActividad.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pack();
    }

    private javax.swing.JLabel lblActividad;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelActividad;
}