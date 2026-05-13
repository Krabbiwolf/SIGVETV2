package Vistas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmGestionarProveedores extends javax.swing.JInternalFrame {

    private static final Color BG_MAIN   = Color.decode("#0A0C10");
    private static final Color BG_CARD   = Color.decode("#111520");
    private static final Color BG_TABLE  = Color.decode("#0E1219");
    private static final Color BG_INPUT  = Color.decode("#181D2E");
    private static final Color BORDER    = Color.decode("#2A3050");
    private static final Color ACCENT    = Color.decode("#6C63FF");
    private static final Color DANGER    = Color.decode("#FF5B7A");
    private static final Color TEXT_PRI  = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT  = Color.decode("#9BA3C4");
    private static final Color GRID      = Color.decode("#1A1F30");
    private static final Color SEL_BG    = Color.decode("#6C63FF");

    private static final int FIELD_H  = 36;
    private static final int LABEL_H  = 16;
    private static final int FIELD_W1 = 220; 
    private static final int FIELD_W2 = 160; 
    private static final int CMB_W    = 120; 
    private static final int BTN_W    = 130;
    private static final int BTN_H    = 36;

    public FrmGestionarProveedores() {
        initComponents();
        this.setSize(new Dimension(880, 480)); // --- CORRECCIÓN DE TAMAÑO ---
        this.setTitle("Gestionar Proveedores");
        aplicarEstilo();
        
        // --- CORRECCIÓN DE ALIAS (EVITA NULL POINTER) ---
        TableProveedores = tableProveedores;
    }

    private void aplicarEstilo() {
        getContentPane().setBackground(BG_MAIN);

        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        panelTabla.setBackground(BG_CARD);
        panelTabla.setBorder(BorderFactory.createLineBorder(BORDER, 1));

        tableProveedores.setBackground(BG_TABLE);
        tableProveedores.setForeground(TEXT_PRI);
        tableProveedores.setGridColor(GRID);
        tableProveedores.setRowHeight(30);
        tableProveedores.setSelectionBackground(SEL_BG);
        tableProveedores.setSelectionForeground(Color.WHITE);
        tableProveedores.setShowGrid(true);
        tableProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableProveedores.setIntercellSpacing(new Dimension(0, 0));
        tableProveedores.setFillsViewportHeight(true);
        tableProveedores.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        DefaultTableCellRenderer centerRnd = new DefaultTableCellRenderer();
        centerRnd.setHorizontalAlignment(JLabel.CENTER);
        centerRnd.setBackground(BG_TABLE);
        centerRnd.setForeground(TEXT_PRI);
        for (int i = 0; i < tableProveedores.getColumnModel().getColumnCount(); i++)
            tableProveedores.getColumnModel().getColumn(i).setCellRenderer(centerRnd);
        if (tableProveedores.getColumnModel().getColumnCount() > 0)
            tableProveedores.getColumnModel().getColumn(0).setMaxWidth(60);

        tableProveedores.getTableHeader().setBackground(Color.decode("#181D2E"));
        tableProveedores.getTableHeader().setForeground(TEXT_MUT);
        tableProveedores.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tableProveedores.getTableHeader().setReorderingAllowed(false);
        tableProveedores.getTableHeader().setResizingAllowed(false);
        tableProveedores.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));
        ((DefaultTableCellRenderer) tableProveedores.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        scrollTabla.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        scrollTabla.getViewport().setBackground(BG_TABLE);

        panelBotones.setBackground(BG_CARD);
        panelBotones.setBorder(BorderFactory.createLineBorder(BORDER, 1));

        btnActualizar.setBackground(ACCENT);
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);
        btnActualizar.setOpaque(true);
        btnActualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnActualizar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnActualizar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnActualizar.setBackground(ACCENT); }
        });

        btnEliminar.setBackground(Color.decode("#1E0A10"));
        btnEliminar.setForeground(DANGER);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorder(BorderFactory.createLineBorder(Color.decode("#3A1520"), 1));
        btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnEliminar.setBackground(DANGER); btnEliminar.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e) { btnEliminar.setBackground(Color.decode("#1E0A10")); btnEliminar.setForeground(DANGER); }
        });

        panelEdicion.setBackground(BG_MAIN);

        for (JLabel lbl : new JLabel[]{lblNombre, lblTelefono, lblEstado}) {
            lbl.setForeground(TEXT_MUT);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 10));
        }

        estilizarCampo(txtNombre);
        estilizarCampo(txtTelefono);

        cmbEstado.setBackground(BG_INPUT);
        cmbEstado.setForeground(TEXT_PRI);
        cmbEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cmbEstado.setBorder(BorderFactory.createLineBorder(BORDER, 1));
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
        lblTitulo     = new JLabel();
        panelTabla    = new JPanel();
        scrollTabla   = new JScrollPane();
        tableProveedores = new JTable();
        panelBotones  = new JPanel();
        btnActualizar = new JButton();
        btnEliminar   = new JButton();
        panelEdicion  = new JPanel();
        lblNombre     = new JLabel();
        lblTelefono   = new JLabel();
        lblEstado     = new JLabel();
        txtNombre     = new JTextField();
        txtTelefono   = new JTextField();
        cmbEstado     = new JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestionar Proveedores");
        setPreferredSize(new Dimension(880, 480));

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("✦  Gestionar Proveedores");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 14, 880, 28));

        panelTabla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        tableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Id", "Nombre", "Teléfono", "Estado"}
        ));
        scrollTabla.setViewportView(tableProveedores);
        panelTabla.add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 580, 210));
        getContentPane().add(panelTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 50, 600, 230));

        panelBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(evt -> btnActualizarActionPerformed(evt));
        panelBotones.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 40, BTN_W, BTN_H));
        btnEliminar.setText("Eliminar");
        panelBotones.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 94, BTN_W, BTN_H));
        getContentPane().add(panelBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(624, 50, 166, 230));

        panelEdicion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombre.setText("NOMBRE");
        panelEdicion.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, FIELD_W1, LABEL_H));
        panelEdicion.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 18, FIELD_W1, FIELD_H));

        lblTelefono.setText("TELÉFONO");
        panelEdicion.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 0, FIELD_W2, LABEL_H));
        panelEdicion.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 18, FIELD_W2, FIELD_H));

        lblEstado.setText("ESTADO");
        panelEdicion.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 0, CMB_W, LABEL_H));
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"ACTIVO", "INACTIVO"}));
        panelEdicion.add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(412, 18, CMB_W, FIELD_H));

        getContentPane().add(panelEdicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 300, 550, 70));

        pack();
    }

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO
    }

    public static javax.swing.JTable    TableProveedores; 
    public        javax.swing.JButton   btnActualizar;
    public        javax.swing.JButton   btnEliminar;
    public        javax.swing.JComboBox<String> cmbEstado;
    private       javax.swing.JLabel    lblEstado;
    private       javax.swing.JLabel    lblNombre;
    private       javax.swing.JLabel    lblTelefono;
    private       javax.swing.JLabel    lblTitulo;
    private       javax.swing.JPanel    panelBotones;
    private       javax.swing.JPanel    panelEdicion;
    private       javax.swing.JPanel    panelTabla;
    private       javax.swing.JScrollPane scrollTabla;
    public static javax.swing.JTable    tableProveedores;
    public        javax.swing.JTextField txtNombre;
    public        javax.swing.JTextField txtTelefono;
}