package Vistas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FrmNuevoProducto extends javax.swing.JInternalFrame {

    private static final Color BG_MAIN  = Color.decode("#0A0C10");
    private static final Color BG_CARD  = Color.decode("#111520");
    private static final Color BG_INPUT = Color.decode("#181D2E");
    private static final Color BORDER   = Color.decode("#2A3050");
    private static final Color ACCENT   = Color.decode("#6C63FF");
    private static final Color TEXT_PRI = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT = Color.decode("#9BA3C4");

    // ── Medidas uniformes (AMPLIADAS PARA EVITAR RECORTES) ───────────────────
    private static final int CARD_W  = 520;
    private static final int CARD_H  = 560;
    private static final int PADX    = 30;
    private static final int FIELD_W = 460;  // CARD_W - 2*PADX
    private static final int FIELD_H = 40;
    private static final int LABEL_H = 20;
    private static final int GAP_Y   = 15;   // Espaciado vertical entre campos

    private static final int Y_TITLE = 20;
    private static final int Y_L1    = 70;
    private static final int Y_F1    = Y_L1 + LABEL_H + 5;
    private static final int Y_L2    = Y_F1 + FIELD_H + GAP_Y;
    private static final int Y_F2    = Y_L2 + LABEL_H + 5;
    
    // Fila dividida para IVA y Categoría
    private static final int Y_L3    = Y_F2 + FIELD_H + GAP_Y;
    private static final int Y_F3    = Y_L3 + LABEL_H + 5;
    private static final int HALF_W  = (FIELD_W - 20) / 2;
    
    private static final int Y_L4    = Y_F3 + FIELD_H + GAP_Y;
    private static final int Y_F4    = Y_L4 + LABEL_H + 5;
    
    private static final int Y_BTN   = Y_F4 + FIELD_H + 30;
    private static final int BTN_H   = 45;
    private static final int BTN_W2  = HALF_W; // Botones mitad y mitad

    public FrmNuevoProducto() {
        initComponents();
        this.setSize(new Dimension(580, 640)); // Ventana un poco más grande que el Card
        this.setPreferredSize(new Dimension(580, 640));
        this.setTitle("Nuevo Producto");
        aplicarEstiloMinimalistaPremium();
    }

    private void aplicarEstiloMinimalistaPremium() {
        this.getContentPane().setBackground(BG_MAIN);

        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        getContentPane().add(card, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, CARD_W, CARD_H));

        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setText("✦  Registro de Producto");
        card.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_TITLE, 300, 30));

        javax.swing.JLabel[] labels = {lblNombre, lblDescripcion, lblIva, lblCategoria, lblImagen};
        for (javax.swing.JLabel lbl : labels) {
            lbl.setForeground(TEXT_MUT);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        }

        // --- FILA 1 ---
        lblNombre.setText("NOMBRE DEL PRODUCTO");
        card.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_L1, FIELD_W, LABEL_H));
        card.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_F1, FIELD_W, FIELD_H));

        // --- FILA 2 ---
        lblDescripcion.setText("DESCRIPCIÓN TÉCNICA");
        card.add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_L2, FIELD_W, LABEL_H));
        card.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_F2, FIELD_W, FIELD_H));

        // --- FILA 3 (Dividida) ---
        lblIva.setText("IVA (%)");
        card.add(lblIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_L3, HALF_W, LABEL_H));
        card.add(cbIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_F3, HALF_W, FIELD_H));

        lblCategoria.setText("CATEGORÍA");
        card.add(lblCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX + HALF_W + 20, Y_L3, HALF_W, LABEL_H));
        card.add(cboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX + HALF_W + 20, Y_F3, HALF_W, FIELD_H));

        // --- FILA 4 ---
        lblImagen.setText("IMAGEN REFERENCIAL (OPCIONAL)");
        card.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_L4, FIELD_W, LABEL_H));
        
        jButton1.setText("📁 Seleccionar");
        card.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_F4, 140, FIELD_H));
        card.add(txtRutaImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX + 150, Y_F4, FIELD_W - 150, FIELD_H));

        // --- BOTONES ---
        btnGuardar.setText("Guardar Producto");
        card.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_BTN, BTN_W2, BTN_H));

        btnLimpiar.setText("Limpiar");
        card.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX + BTN_W2 + 20, Y_BTN, BTN_W2, BTN_H));

        // --- ESTILOS COMPONENTES ---
        javax.swing.JTextField[] campos = {txtNombre, txtDescripcion, txtRutaImagen};
        for (javax.swing.JTextField c : campos) {
            c.setBackground(BG_INPUT);
            c.setForeground(TEXT_PRI);
            c.setCaretColor(ACCENT);
            c.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(6, 12, 6, 12)
            ));
        }
        
        javax.swing.JComboBox[] combos = {cboCategoria, cbIva};
        for (javax.swing.JComboBox c : combos) {
            c.setBackground(BG_INPUT);
            c.setForeground(TEXT_PRI);
            c.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            c.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        }
        
        btnGuardar.setBackground(ACCENT);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnLimpiar.setBackground(BG_INPUT);
        btnLimpiar.setForeground(TEXT_PRI);
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLimpiar.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        jButton1.setBackground(Color.decode("#1F2640")); 
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new Font("Segoe UI", Font.BOLD, 12));
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        card = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblIva = new javax.swing.JLabel();
        cbIva = new javax.swing.JComboBox<>();
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>(new String[] { "Seleccione..." });
        lblImagen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtRutaImagen = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        card.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pack();
    }

    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JComboBox<String> cbIva;
    public javax.swing.JComboBox<String> cboCategoria;
    public javax.swing.JButton jButton1;
    private javax.swing.JPanel card;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtRutaImagen;
}