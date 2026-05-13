package Vistas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FrmNuevoProveedor extends javax.swing.JInternalFrame {

    // ── Paleta ───────────────────────────────────────────────────────────────
    private static final Color BG_MAIN   = Color.decode("#0A0C10");
    private static final Color BG_CARD   = Color.decode("#111520");
    private static final Color BG_INPUT  = Color.decode("#181D2E");
    private static final Color BORDER    = Color.decode("#2A3050");
    private static final Color ACCENT    = Color.decode("#6C63FF");
    private static final Color TEXT_PRI  = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT  = Color.decode("#9BA3C4");

    // ── Medidas uniformes (Corregidas para evitar recortes) ──────────────────
    private static final int CARD_W  = 420;   
    private static final int CARD_H  = 300;   
    private static final int PADX    = 25;    
    private static final int FIELD_W = 370;   // CARD_W - 2*PADX
    private static final int FIELD_H = 38;
    private static final int LABEL_H = 16;
    private static final int GAP_Y   = 15;

    private static final int Y_TITLE = 20;
    private static final int Y_L1    = 70;
    private static final int Y_F1    = Y_L1 + LABEL_H + 4;
    private static final int Y_L2    = Y_F1 + FIELD_H + GAP_Y;
    private static final int Y_F2    = Y_L2 + LABEL_H + 4;
    private static final int Y_BTN   = Y_F2 + FIELD_H + 25;
    private static final int BTN_W   = FIELD_W;
    private static final int BTN_H   = 45;

    public FrmNuevoProveedor() {
        initComponents();
        this.setSize(new Dimension(480, 380));
        this.setPreferredSize(new Dimension(480, 380));
        this.setTitle("Nuevo Proveedor");
        aplicarEstiloMinimalistaPremium();
    }

    private void aplicarEstiloMinimalistaPremium() {
        this.getContentPane().setBackground(BG_MAIN);

        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        getContentPane().add(card, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 20, CARD_W, CARD_H));

        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setText("✦  Registro de Proveedor");
        card.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_TITLE, 300, 30));

        lblNombre.setForeground(TEXT_MUT);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblNombre.setText("NOMBRE DEL PROVEEDOR");
        card.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_L1, FIELD_W, LABEL_H));
        
        txtNombreProveedor.setBackground(BG_INPUT);
        txtNombreProveedor.setForeground(TEXT_PRI);
        txtNombreProveedor.setCaretColor(ACCENT);
        txtNombreProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNombreProveedor.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        card.add(txtNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_F1, FIELD_W, FIELD_H));

        lblTelefono.setForeground(TEXT_MUT);
        lblTelefono.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTelefono.setText("TELÉFONO DE CONTACTO");
        card.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_L2, FIELD_W, LABEL_H));
        
        txtTelefonoProveedor.setBackground(BG_INPUT);
        txtTelefonoProveedor.setForeground(TEXT_PRI);
        txtTelefonoProveedor.setCaretColor(ACCENT);
        txtTelefonoProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTelefonoProveedor.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(6, 12, 6, 12)
        ));
        card.add(txtTelefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_F2, FIELD_W, FIELD_H));

        btnGuardarProveedor.setText("Guardar Proveedor");
        btnGuardarProveedor.setBackground(ACCENT);
        btnGuardarProveedor.setForeground(Color.WHITE);
        btnGuardarProveedor.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardarProveedor.setBorderPainted(false);
        btnGuardarProveedor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.add(btnGuardarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, Y_BTN, BTN_W, BTN_H));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        card = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefonoProveedor = new javax.swing.JTextField();
        btnGuardarProveedor = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        card.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pack();
    }

    public  javax.swing.JButton    btnGuardarProveedor;
    private javax.swing.JPanel     card;
    private javax.swing.JLabel     lblNombre;
    private javax.swing.JLabel     lblTelefono;
    private javax.swing.JLabel     lblTitulo;
    public  javax.swing.JTextField txtNombreProveedor;
    public  javax.swing.JTextField txtTelefonoProveedor;
}