package Vistas;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmGestionarProductos extends javax.swing.JInternalFrame {

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

    // ── Medidas del diseño de 3 columnas (Premium Dashboard Layout) ──
    private static final int FIELD_H  = 38;
    private static final int LABEL_H  = 16;
    private static final int GAP_Y    = 15;
    
    // Columnas X
    private static final int COL1_X   = 25;
    private static final int COL2_X   = 360;
    private static final int COL3_X   = 635;

    public FrmGestionarProductos() {
        initComponents();
        // Dimensiones exactas para que las 3 columnas calcen a la perfección
        this.setSize(new Dimension(1150, 640));
        this.setPreferredSize(new Dimension(1150, 640));
        this.setTitle("Gestionar Productos");
        aplicarEstiloMinimalistaPremium();
    }

    private void aplicarEstiloMinimalistaPremium() {
        this.getContentPane().setBackground(BG_MAIN);

        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setText("✦  Gestión y Stock de Productos");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X, 20, 400, 30));

        javax.swing.JLabel[] labels = {lblIdProducto, lblCodBarras, lblNombre, lblDescripcion, lblIva, lblImagen, lblCategoria, lblEstado, lblBuscar, lblPreview};
        for (javax.swing.JLabel lbl : labels) {
            lbl.setForeground(TEXT_MUT);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 11));
        }

        // ──────────────────────────────────────────────────────────────────────
        // COLUMNA 1: Formulario de Datos (X = 25)
        // ──────────────────────────────────────────────────────────────────────
        int y = 70;
        lblIdProducto.setText("ID");
        getContentPane().add(lblIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X, y, 90, LABEL_H));
        txtIdProducto.setBackground(Color.decode("#0E1219"));
        txtIdProducto.setEditable(false);
        getContentPane().add(txtIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X, y + LABEL_H + 4, 90, FIELD_H));

        lblCodBarras.setText("CÓDIGO DE BARRAS");
        getContentPane().add(lblCodBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X + 105, y, 205, LABEL_H));
        getContentPane().add(txtCodigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X + 105, y + LABEL_H + 4, 205, FIELD_H));

        y += LABEL_H + FIELD_H + GAP_Y;
        lblNombre.setText("NOMBRE DEL PRODUCTO");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X, y, 310, LABEL_H));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X, y + LABEL_H + 4, 310, FIELD_H));

        y += LABEL_H + FIELD_H + GAP_Y;
        lblDescripcion.setText("DESCRIPCIÓN TÉCNICA");
        getContentPane().add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X, y, 310, LABEL_H));
        getContentPane().add(txtDescripcionTecnica, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X, y + LABEL_H + 4, 310, FIELD_H));

        y += LABEL_H + FIELD_H + GAP_Y;
        lblIva.setText("IVA (%)");
        getContentPane().add(lblIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X, y, 145, LABEL_H));
        cbIva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"13", "0"}));
        getContentPane().add(cbIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X, y + LABEL_H + 4, 145, FIELD_H));

        lblCategoria.setText("CATEGORÍA");
        getContentPane().add(lblCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X + 165, y, 145, LABEL_H));
        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        getContentPane().add(cboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X + 165, y + LABEL_H + 4, 145, FIELD_H));

        y += LABEL_H + FIELD_H + GAP_Y;
        lblEstado.setText("ESTADO");
        getContentPane().add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X, y, 145, LABEL_H));
        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Activo", "Inactivo"}));
        getContentPane().add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X, y + LABEL_H + 4, 145, FIELD_H));

        lblImagen.setText("IMAGEN");
        getContentPane().add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X + 165, y, 145, LABEL_H));
        btnAgregarImagen.setText("📁 Cargar Foto");
        btnAgregarImagen.addActionListener(evt -> btnAgregarImagenActionPerformed(evt));
        getContentPane().add(btnAgregarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL1_X + 165, y + LABEL_H + 4, 145, FIELD_H));
        
        // El campo ruta se oculta porque la imagen ya se ve en la vista previa
        txtRuta.setVisible(false);
        getContentPane().add(txtRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        // ──────────────────────────────────────────────────────────────────────
        // COLUMNA 2: Botones, Búsqueda y Vista Previa (X = 360)
        // ──────────────────────────────────────────────────────────────────────
        int y2 = 70 + LABEL_H + 4; // Alineado con la fila 1
        
        btnActualizar.setText("Guardar Cambios");
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL2_X, y2, 250, 42));

        btnLimpiar.setText("Limpiar Campos");
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL2_X, y2 + 52, 250, 42));

        btnEliminar.setText("Desactivar Producto");
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL2_X, y2 + 104, 250, 42));

        int yBusc = y2 + 156 + 10;
        lblBuscar.setText("BÚSQUEDA RÁPIDA");
        getContentPane().add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL2_X, yBusc, 250, LABEL_H));
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL2_X, yBusc + LABEL_H + 4, 160, FIELD_H));
        btnBuscar.setText("Buscar");
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL2_X + 170, yBusc + LABEL_H + 4, 80, FIELD_H));

        int yPrev = yBusc + LABEL_H + FIELD_H + 20;
        lblPreview.setText("VISTA PREVIA DEL PRODUCTO");
        getContentPane().add(lblPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL2_X, yPrev, 250, LABEL_H));
        
        lblMostrarImagen.setText("Sin imagen");
        lblMostrarImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblMostrarImagen.setBorder(BorderFactory.createDashedBorder(BORDER, 2, 2));
        getContentPane().add(lblMostrarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL2_X, yPrev + LABEL_H + 4, 250, 200));

        // ──────────────────────────────────────────────────────────────────────
        // COLUMNA 3: Tabla Scrolleable Gigante (X = 635)
        // ──────────────────────────────────────────────────────────────────────
        tblProductos.setBackground(BG_TABLE);
        tblProductos.setForeground(TEXT_PRI);
        tblProductos.setGridColor(GRID);
        tblProductos.setRowHeight(32);
        tblProductos.setSelectionBackground(ACCENT);
        tblProductos.setSelectionForeground(Color.WHITE);
        tblProductos.setShowGrid(true);
        tblProductos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblProductos.setIntercellSpacing(new Dimension(0, 0));
        tblProductos.setFillsViewportHeight(true);

        tblProductos.getTableHeader().setBackground(Color.decode("#181D2E"));
        tblProductos.getTableHeader().setForeground(TEXT_MUT);
        tblProductos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));
        ((DefaultTableCellRenderer) tblProductos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        scrollTabla.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        scrollTabla.getViewport().setBackground(BG_TABLE);
        // Altura de tabla: Empieza en y=90, termina en la base del form
        getContentPane().add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(COL3_X, 90, 480, 485));

        // ──────────────────────────────────────────────────────────────────────
        // ESTILIZACIÓN GENERAL DE LOS COMPONENTES
        // ──────────────────────────────────────────────────────────────────────
        javax.swing.JTextField[] campos = {txtIdProducto, txtCodigoBarras, txtNombre, txtDescripcionTecnica, txtBuscar, txtRuta};
        for (javax.swing.JTextField c : campos) {
            c.setBackground(BG_INPUT);
            c.setForeground(TEXT_PRI);
            c.setCaretColor(ACCENT);
            c.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            c.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER, 1), new EmptyBorder(6, 10, 6, 10)));
            c.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) { c.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(ACCENT, 1), new EmptyBorder(6, 10, 6, 10))); }
                public void focusLost(FocusEvent e) { c.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER, 1), new EmptyBorder(6, 10, 6, 10))); }
            });
        }

        javax.swing.JComboBox[] combos = {cboCategoria, cboEstado, cbIva};
        for (javax.swing.JComboBox c : combos) {
            c.setBackground(BG_INPUT);
            c.setForeground(TEXT_PRI);
            c.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            c.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        }

        estilizarBtn(btnActualizar, ACCENT, Color.WHITE);
        estilizarBtn(btnLimpiar, BG_INPUT, TEXT_PRI);
        estilizarBtnDanger(btnEliminar);
        estilizarBtn(btnBuscar, Color.decode("#1F2640"), Color.WHITE);
        estilizarBtn(btnAgregarImagen, Color.decode("#1F2640"), Color.WHITE);
    }

    private void estilizarBtn(JButton b, Color bg, Color fg) {
        b.setBackground(bg);
        b.setForeground(fg);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorderPainted(bg.equals(BG_INPUT)); // Solo si es secundario lleva borde
        if(bg.equals(BG_INPUT)) b.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        b.setOpaque(true);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { if(bg.equals(ACCENT)) b.setBackground(Color.decode("#5850DC")); else b.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e)  { b.setBackground(bg); }
        });
    }

    private void estilizarBtnDanger(JButton b) {
        b.setBackground(Color.decode("#1E0A10"));
        b.setForeground(DANGER);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createLineBorder(Color.decode("#3A1520"), 1));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { b.setBackground(DANGER); b.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e)  { b.setBackground(Color.decode("#1E0A10")); b.setForeground(DANGER); }
        });
    }

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser selector = new JFileChooser();
        javax.swing.filechooser.FileNameExtensionFilter filtro = new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif");
        selector.setFileFilter(filtro);
        if(selector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            File archivo = selector.getSelectedFile();
            txtRuta.setText(archivo.getAbsolutePath());
            ImageIcon icono = new ImageIcon(archivo.getAbsolutePath());
            Image img = icono.getImage().getScaledInstance(lblMostrarImagen.getWidth(), lblMostrarImagen.getHeight(), Image.SCALE_SMOOTH);
            lblMostrarImagen.setIcon(new ImageIcon(img));
            lblMostrarImagen.setText("");
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        lblTitulo          = new JLabel();
        lblIdProducto      = new JLabel();
        txtIdProducto      = new JTextField();
        lblCodBarras       = new JLabel();
        txtCodigoBarras    = new JTextField();
        lblNombre          = new JLabel();
        txtNombre          = new JTextField();
        lblDescripcion     = new JLabel();
        txtDescripcionTecnica = new JTextField();
        lblIva             = new JLabel();
        cbIva              = new JComboBox<>();
        lblImagen          = new JLabel();
        btnAgregarImagen   = new JButton();
        txtRuta            = new JTextField();
        lblCategoria       = new JLabel();
        cboCategoria       = new JComboBox<>();
        lblEstado          = new JLabel();
        cboEstado          = new JComboBox<>();
        lblBuscar          = new JLabel();
        txtBuscar          = new JTextField();
        btnBuscar          = new JButton();
        scrollTabla        = new JScrollPane();
        tblProductos       = new JTable();
        btnActualizar      = new JButton();
        btnLimpiar         = new JButton();
        btnEliminar        = new JButton();
        lblPreview         = new JLabel();
        lblMostrarImagen   = new JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "ID", "Código", "Nombre", "Desc.", "IVA", "Cat", "Estado" }
        ));
        scrollTabla.setViewportView(tblProductos);

        pack();
    }

    public  javax.swing.JButton   btnActualizar;
    public  javax.swing.JButton   btnAgregarImagen;
    public  javax.swing.JButton   btnBuscar;
    public  javax.swing.JButton   btnEliminar;
    public  javax.swing.JButton   btnLimpiar;
    public  javax.swing.JComboBox<String> cbIva;
    public  javax.swing.JComboBox<String> cboCategoria;
    public  javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JLabel    lblBuscar;
    private javax.swing.JLabel    lblCategoria;
    private javax.swing.JLabel    lblCodBarras;
    private javax.swing.JLabel    lblDescripcion;
    private javax.swing.JLabel    lblEstado;
    private javax.swing.JLabel    lblIdProducto;
    private javax.swing.JLabel    lblImagen;
    private javax.swing.JLabel    lblIva;
    private javax.swing.JLabel    lblNombre;
    private javax.swing.JLabel    lblPreview;
    private javax.swing.JLabel    lblTitulo;
    public  javax.swing.JLabel    lblMostrarImagen;
    private javax.swing.JScrollPane scrollTabla;
    public  javax.swing.JTable    tblProductos;
    public  javax.swing.JTextField txtBuscar;
    public  javax.swing.JTextField txtCodigoBarras;
    public  javax.swing.JTextField txtDescripcionTecnica;
    public  javax.swing.JTextField txtIdProducto;
    public  javax.swing.JTextField txtNombre;
    public  javax.swing.JTextField txtRuta;
}