package Vistas;

import Modelos.Roles;
import Modelos.RolesDAO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FrmNuevoUsuario extends javax.swing.JInternalFrame {

    private static final Color BG_MAIN   = Color.decode("#0A0C10");
    private static final Color BG_CARD   = Color.decode("#111520");
    private static final Color BG_INPUT  = Color.decode("#181D2E");
    private static final Color BORDER    = Color.decode("#2A3050");
    private static final Color ACCENT    = Color.decode("#6C63FF");
    private static final Color ACCENT3   = Color.decode("#00D4AA");
    private static final Color TEXT_PRI  = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT  = Color.decode("#9BA3C4");

    // ── Medidas uniformes ────────────────────────────────────────────────────
    private static final int CARD_W  = 860;
    private static final int CARD_H  = 560;
    private static final int PADX    = 30;
    
    // Columna 1 (Campos)
    private static final int X_COL1  = PADX;
    private static final int W_COL1  = 400;
    
    // Columna 2 (Avatar)
    private static final int X_COL2  = PADX + W_COL1 + 40;
    private static final int W_COL2  = 340;
    
    private static final int FIELD_H = 40;
    private static final int LABEL_H = 18;
    private static final int GAP_Y   = 12;
    private static final int IMG_SZ  = 300; // Avatar Size

    private RolesDAO dao = new RolesDAO();

    public FrmNuevoUsuario() {
        initComponents();
        this.setSize(new Dimension(920, 640)); // Aumentado
        this.setPreferredSize(new Dimension(920, 640));
        this.setTitle("Registrar Staff");
        cargarRoles();
        aplicarEstiloMinimalistaPremium();
    }

    public void cargarRoles() {
        ArrayList<Roles> roles = dao.listarRoles();
        comboRoles.removeAllItems();
        for (Roles rol : roles) {
            comboRoles.addItem(rol);
        }
    }

    private void aplicarEstiloMinimalistaPremium() {
        this.getContentPane().setBackground(BG_MAIN);

        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        getContentPane().add(card, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, CARD_W, CARD_H));

        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setText("✦  Alta de Nuevo Empleado");
        card.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, 20, 400, 30));

        javax.swing.JLabel[] labels = {lblNombres, lblApellidos, lblDUI, lblTelefono, lblUsuario, lblPassword, lblRol, lblEstado, lblImagen};
        for (javax.swing.JLabel lbl : labels) {
            lbl.setForeground(TEXT_MUT);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        }

        // --- COLUMNA 1 (Formularios) ---
        int yRow = 70;
        lblNombres.setText("NOMBRES");
        card.add(lblNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1, yRow, W_COL1, LABEL_H));
        card.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1, yRow + LABEL_H, W_COL1, FIELD_H));

        yRow += LABEL_H + FIELD_H + GAP_Y;
        lblApellidos.setText("APELLIDOS");
        card.add(lblApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1, yRow, W_COL1, LABEL_H));
        card.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1, yRow + LABEL_H, W_COL1, FIELD_H));

        yRow += LABEL_H + FIELD_H + GAP_Y;
        int halfW = (W_COL1 - 20) / 2;
        lblDUI.setText("DUI");
        card.add(lblDUI, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1, yRow, halfW, LABEL_H));
        card.add(txtDUI, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1, yRow + LABEL_H, halfW, FIELD_H));

        lblTelefono.setText("TELÉFONO");
        card.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1 + halfW + 20, yRow, halfW, LABEL_H));
        card.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1 + halfW + 20, yRow + LABEL_H, halfW, FIELD_H));

        yRow += LABEL_H + FIELD_H + GAP_Y;
        lblUsuario.setText("USERNAME");
        card.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1, yRow, halfW, LABEL_H));
        card.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1, yRow + LABEL_H, halfW, FIELD_H));

        lblPassword.setText("CONTRASEÑA");
        card.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1 + halfW + 20, yRow, halfW, LABEL_H));
        card.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1 + halfW + 20, yRow + LABEL_H, halfW, FIELD_H));

        yRow += LABEL_H + FIELD_H + GAP_Y;
        lblRol.setText("ROL ASIGNADO");
        card.add(lblRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1, yRow, halfW, LABEL_H));
        card.add(comboRoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1, yRow + LABEL_H, halfW, FIELD_H));

        lblEstado.setText("ESTADO");
        card.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1 + halfW + 20, yRow, halfW, LABEL_H));
        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        card.add(comboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL1 + halfW + 20, yRow + LABEL_H, halfW, FIELD_H));

        // --- COLUMNA 2 (Avatar y Botón) ---
        lblImagen.setText("FOTO DE PERFIL");
        card.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL2, 70, W_COL2, LABEL_H));
        
        btnAgregarImagen.setText("Examinar...");
        card.add(btnAgregarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL2, 70 + LABEL_H, 120, FIELD_H));
        txtRutaImagen.setVisible(false); // Oculto

        lblImagenPreview.setBorder(BorderFactory.createDashedBorder(BORDER, 2, 2));
        lblImagenPreview.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(lblImagenPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL2, 70 + LABEL_H + FIELD_H + 15, IMG_SZ, IMG_SZ));

        // Botón principal
        btnGuardarUsuario.setText("Guardar Empleado");
        card.add(btnGuardarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_COL2, CARD_H - 70, IMG_SZ, 45));

        // --- ESTILOS COMPONENTES ---
        javax.swing.JTextField[] campos = {txtNombres, txtApellidos, txtDUI, txtTelefono, txtUsuario, txtPassword, txtRutaImagen};
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

        javax.swing.JComboBox[] combos = {comboRoles, comboEstado};
        for (javax.swing.JComboBox c : combos) {
            c.setBackground(BG_INPUT);
            c.setForeground(TEXT_PRI);
            c.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            c.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        }

        btnGuardarUsuario.setBackground(ACCENT3); 
        btnGuardarUsuario.setForeground(Color.WHITE);
        btnGuardarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGuardarUsuario.setBorderPainted(false);
        btnGuardarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnAgregarImagen.setBackground(Color.decode("#1F2640"));
        btnAgregarImagen.setForeground(Color.WHITE);
        btnAgregarImagen.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnAgregarImagen.setBorderPainted(false);
        btnAgregarImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnAgregarImagen.addActionListener(evt -> btnAgregarImagenActionPerformed(evt));
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        card = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        lblApellidos = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        lblDUI = new javax.swing.JLabel();
        txtDUI = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        lblRol = new javax.swing.JLabel();
        comboRoles = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox<>();
        lblImagen = new javax.swing.JLabel();
        btnAgregarImagen = new javax.swing.JButton();
        txtRutaImagen = new javax.swing.JTextField();
        lblImagenPreview = new javax.swing.JLabel();
        btnGuardarUsuario = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        card.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pack();
    }

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
        selector.setFileFilter(filtro);
        if(selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File archivo = selector.getSelectedFile();
            txtRutaImagen.setText(archivo.getAbsolutePath());
            ImageIcon icono = new ImageIcon(archivo.getAbsolutePath());
            Image img = icono.getImage().getScaledInstance(IMG_SZ, IMG_SZ, Image.SCALE_SMOOTH);
            lblImagenPreview.setIcon(new ImageIcon(img));
            lblImagenPreview.setText("");
        }
    }

    private  javax.swing.JButton    btnAgregarImagen;
    public   javax.swing.JButton    btnGuardarUsuario;
    private  javax.swing.JPanel     card;
    public   javax.swing.JComboBox<String>  comboEstado;
    public   javax.swing.JComboBox<Roles>   comboRoles;
    private  javax.swing.JLabel     lblApellidos;
    private  javax.swing.JLabel     lblDUI;
    private  javax.swing.JLabel     lblEstado;
    private  javax.swing.JLabel     lblImagen;
    private  javax.swing.JLabel     lblImagenPreview;
    private  javax.swing.JLabel     lblNombres;
    private  javax.swing.JLabel     lblPassword;
    private  javax.swing.JLabel     lblRol;
    private  javax.swing.JLabel     lblTelefono;
    private  javax.swing.JLabel     lblTitulo;
    private  javax.swing.JLabel     lblUsuario;
    public   javax.swing.JTextField txtApellidos;
    public   javax.swing.JTextField txtDUI;
    public   javax.swing.JTextField txtNombres;
    public   javax.swing.JTextField txtPassword;
    public   javax.swing.JTextField txtRutaImagen;
    public   javax.swing.JTextField txtTelefono;
    public   javax.swing.JTextField txtUsuario;
}