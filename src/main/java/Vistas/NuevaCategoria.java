package Vistas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NuevaCategoria extends javax.swing.JInternalFrame {

    private static final Color BG_MAIN   = Color.decode("#0A0C10");
    private static final Color BG_CARD   = Color.decode("#111520");
    private static final Color BG_INPUT  = Color.decode("#181D2E");
    private static final Color BORDER    = Color.decode("#2A3050");
    private static final Color ACCENT    = Color.decode("#6C63FF");
    private static final Color TEXT_PRI  = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT  = Color.decode("#9BA3C4");

    public NuevaCategoria() {
        initComponents();
        this.setSize(new Dimension(460, 360));
        this.setPreferredSize(new Dimension(460, 360));
        this.setTitle("Nueva Categoría");
        aplicarEstiloPremium();
    }

    private void aplicarEstiloPremium() {
        getContentPane().setBackground(BG_MAIN);
        getContentPane().setLayout(null);

        JPanel card = new JPanel(null);
        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        card.setBounds(30, 20, 380, 280);
        getContentPane().add(card);

        JLabel lblTitulo = new JLabel("✦  Nueva Categoría");
        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBounds(20, 15, 300, 30);
        card.add(lblTitulo);

        jLabel3.setText("NOMBRE DE LA CATEGORÍA");
        jLabel3.setForeground(TEXT_MUT);
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel3.setBounds(20, 65, 340, 16);
        card.add(jLabel3);

        estilizarCampo(txtnombrecategoria);
        txtnombrecategoria.setBounds(20, 85, 340, 38);
        card.add(txtnombrecategoria);

        jLabel1.setText("DESCRIPCIÓN");
        jLabel1.setForeground(TEXT_MUT);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel1.setBounds(20, 140, 340, 16);
        card.add(jLabel1);

        estilizarCampo(txtDescripcion);
        txtDescripcion.setBounds(20, 160, 340, 38);
        card.add(txtDescripcion);

        btnGuardar.setText("Guardar Categoría");
        btnGuardar.setBackground(ACCENT);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setBounds(20, 215, 340, 42);
        btnGuardar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnGuardar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnGuardar.setBackground(ACCENT); }
        });
        card.add(btnGuardar);
    }

    private void estilizarCampo(JTextField f) {
        f.setBackground(BG_INPUT);
        f.setForeground(TEXT_PRI);
        f.setCaretColor(ACCENT);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER, 1), new EmptyBorder(6, 10, 6, 10)));
        f.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) { f.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ACCENT, 1), new EmptyBorder(6, 10, 6, 10))); }
            public void focusLost(FocusEvent e) { f.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER, 1), new EmptyBorder(6, 10, 6, 10))); }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtnombrecategoria = new javax.swing.JTextField();

        setClosable(true);
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);
        pack();
    }

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO
    }

    public javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtnombrecategoria;
}