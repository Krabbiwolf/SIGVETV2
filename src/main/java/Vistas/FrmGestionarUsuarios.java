package Vistas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmGestionarUsuarios extends javax.swing.JInternalFrame {

    private static final Color BG_MAIN   = Color.decode("#0A0C10");
    private static final Color BG_PANEL  = Color.decode("#111520");
    private static final Color BG_TABLE  = Color.decode("#0E1219");
    private static final Color BG_INPUT  = Color.decode("#181D2E");
    private static final Color BORDER    = Color.decode("#2A3050");
    private static final Color ACCENT    = Color.decode("#6C63FF");
    private static final Color DANGER    = Color.decode("#FF5B7A");
    private static final Color TEXT_PRI  = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT  = Color.decode("#9BA3C4");
    private static final Color GRID      = Color.decode("#1A1F30");

    private static final int FORM_W  = 760;
    private static final int PADX    = 20;
    private static final int FIELD_H = 36;
    private static final int LABEL_H = 15;
    private static final int FW_PASS = 240;
    private static final int FW_TEL  = 180;
    private static final int FW_CMB  = 160;
    private static final int GAP     = 14;
    private static final int BTN_W   = 120;
    private static final int BTN_H   = 36;
    private static final int TABLE_H = 280;

    private static final int X_PASS  = PADX;
    private static final int X_TEL   = PADX + FW_PASS + GAP;
    private static final int X_CMB   = X_TEL + FW_TEL + GAP;
    private static final int X_BTN1  = PADX;
    private static final int X_BTN2  = PADX + BTN_W + 14;

    public FrmGestionarUsuarios() {
        initComponents();
        this.setSize(new Dimension(820, 560)); // --- CORRECCIÓN DE TAMAÑO ---
        aplicarEstilo();
    }

    private void aplicarEstilo() {
        getContentPane().setBackground(BG_MAIN);

        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        for (JLabel l : new JLabel[]{lblContraseña, lblTelefono, lblEstado}) {
            l.setForeground(TEXT_MUT);
            l.setFont(new Font("Segoe UI", Font.BOLD, 10));
        }

        estilizarCampo(txtNuevaPassword);
        estilizarCampo(txtNuevoTelefono);

        comboNuevoEstado.setBackground(BG_INPUT);
        comboNuevoEstado.setForeground(TEXT_PRI);
        comboNuevoEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboNuevoEstado.setBorder(BorderFactory.createLineBorder(BORDER, 1));

        tableUsuarios.setBackground(BG_TABLE);
        tableUsuarios.setForeground(TEXT_PRI);
        tableUsuarios.setGridColor(GRID);
        tableUsuarios.setRowHeight(30);
        tableUsuarios.setSelectionBackground(ACCENT);
        tableUsuarios.setSelectionForeground(Color.WHITE);
        tableUsuarios.setShowGrid(true);
        tableUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableUsuarios.setIntercellSpacing(new Dimension(0, 0));
        tableUsuarios.setFillsViewportHeight(true);
        tableUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableUsuarios.getTableHeader().setBackground(Color.decode("#181D2E"));
        tableUsuarios.getTableHeader().setForeground(TEXT_MUT);
        tableUsuarios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tableUsuarios.getTableHeader().setReorderingAllowed(false);
        tableUsuarios.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));

        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        scrollPane.getViewport().setBackground(BG_TABLE);

        btnActualizarUsuario.setBackground(ACCENT);
        btnActualizarUsuario.setForeground(Color.WHITE);
        btnActualizarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnActualizarUsuario.setFocusPainted(false);
        btnActualizarUsuario.setBorderPainted(false);
        btnActualizarUsuario.setOpaque(true);
        btnActualizarUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActualizarUsuario.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnActualizarUsuario.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnActualizarUsuario.setBackground(ACCENT); }
        });

        btnEliminarUsuario.setBackground(Color.decode("#1E0A10"));
        btnEliminarUsuario.setForeground(DANGER);
        btnEliminarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEliminarUsuario.setFocusPainted(false);
        btnEliminarUsuario.setBorder(BorderFactory.createLineBorder(Color.decode("#3A1520"), 1));
        btnEliminarUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEliminarUsuario.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnEliminarUsuario.setBackground(DANGER); btnEliminarUsuario.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e) { btnEliminarUsuario.setBackground(Color.decode("#1E0A10")); btnEliminarUsuario.setForeground(DANGER); }
        });
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
        lblTitulo           = new JLabel();
        scrollPane          = new JScrollPane();
        tableUsuarios       = new JTable();
        lblContraseña       = new JLabel();
        txtNuevaPassword    = new JTextField();
        lblTelefono         = new JLabel();
        txtNuevoTelefono    = new JTextField();
        lblEstado           = new JLabel();
        comboNuevoEstado    = new JComboBox<>();
        btnActualizarUsuario= new JButton();
        btnEliminarUsuario  = new JButton();

        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Gestión de Usuarios");
        setPreferredSize(new Dimension(FORM_W + 40, TABLE_H + 200));

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("✦  Gestión de Usuarios");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 14, FORM_W + 40, 30));

        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Id","Nombre","Apellido","DUI","Telefono","Username","Contraseña","Imagen","Estado","Rol"}
        ) {
            boolean[] canEdit = {false,false,false,false,false,false,false,false,false,true};
            public boolean isCellEditable(int row, int col) { return canEdit[col]; }
        });
        scrollPane.setViewportView(tableUsuarios);
        getContentPane().add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, 52, FORM_W, TABLE_H));

        int yEdit = 52 + TABLE_H + 14;
        JSeparator sep = new JSeparator();
        sep.setForeground(BORDER);
        getContentPane().add(sep, new org.netbeans.lib.awtextra.AbsoluteConstraints(PADX, yEdit - 6, FORM_W, 1));

        lblContraseña.setText("NUEVA CONTRASEÑA");
        getContentPane().add(lblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_PASS, yEdit, FW_PASS, LABEL_H));
        getContentPane().add(txtNuevaPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_PASS, yEdit + LABEL_H + 3, FW_PASS, FIELD_H));

        lblTelefono.setText("NUEVO TELÉFONO");
        getContentPane().add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_TEL, yEdit, FW_TEL, LABEL_H));
        getContentPane().add(txtNuevoTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_TEL, yEdit + LABEL_H + 3, FW_TEL, FIELD_H));

        lblEstado.setText("ESTADO");
        getContentPane().add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_CMB, yEdit, FW_CMB, LABEL_H));
        comboNuevoEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"ACTIVO", "INACTIVO"}));
        getContentPane().add(comboNuevoEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_CMB, yEdit + LABEL_H + 3, FW_CMB, FIELD_H));

        int yBtn = yEdit + LABEL_H + 3 + FIELD_H + 14;
        btnActualizarUsuario.setText("Actualizar");
        getContentPane().add(btnActualizarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_BTN1, yBtn, BTN_W, BTN_H));

        btnEliminarUsuario.setText("Eliminar");
        getContentPane().add(btnEliminarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(X_BTN2, yBtn, BTN_W, BTN_H));

        pack();
    }

    public  javax.swing.JButton   btnActualizarUsuario;
    public  javax.swing.JButton   btnEliminarUsuario;
    public  javax.swing.JComboBox<String> comboNuevoEstado;
    private javax.swing.JLabel    lblContraseña;
    private javax.swing.JLabel    lblEstado;
    private javax.swing.JLabel    lblTelefono;
    private javax.swing.JLabel    lblTitulo;
    private javax.swing.JScrollPane scrollPane;
    public  static javax.swing.JTable tableUsuarios;
    public  javax.swing.JTextField txtNuevaPassword;
    public  javax.swing.JTextField txtNuevoTelefono;
}