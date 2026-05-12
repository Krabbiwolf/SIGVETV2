package Vistas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import com.formdev.flatlaf.FlatDarkLaf;

// Importaciones de tus Modelos y Controladores
import Modelos.*;
import Controladores.ctrlProductos.*;
import Controladores.CtrlProveedor.*;
import Controladores.CtrlUsuarios.*;
import Controladores.CtrlCompra.*;
import Controladores.CtrlFactura.*;
import Controladores.CtrlRoles.*;
import Controladores.CtrlNuevoCliente;
import Controladores.CtrlGestionarClientes;

/**
 * SIGVET — MDI Principal
 * Sidebar lateral fija + header + JDesktopPane con fondo personalizado
 */
public class MDI extends javax.swing.JFrame {

    // ── Paleta SIGVET ──────────────────────────────────────────────────────
    private static final Color BG_DEEP      = Color.decode("#0A0C10");
    private static final Color BG_SIDEBAR   = Color.decode("#111520");
    private static final Color BG_HEADER    = Color.decode("#111520");
    private static final Color BG_INPUT     = Color.decode("#181D2E");
    private static final Color BG_HOVER     = Color.decode("#1F2640");
    private static final Color BORDER_COLOR = Color.decode("#2A3050");
    private static final Color ACCENT       = Color.decode("#6C63FF");
    private static final Color ACCENT2      = Color.decode("#FF6B9D");
    private static final Color ACCENT3      = Color.decode("#00D4AA");
    private static final Color TEXT_PRIMARY = Color.decode("#F0F2FF");
    private static final Color TEXT_MUTED   = Color.decode("#9BA3C4");
    private static final Color TEXT_HINT    = Color.decode("#5A6280");
    private static final Color DANGER       = Color.decode("#FF5B7A");

    // ── Tipografía ───────────────────────────────────────────────────────────
    private static final Font FONT_BRAND   = new Font("Segoe UI", Font.BOLD,  15);
    private static final Font FONT_SECTION = new Font("Segoe UI", Font.BOLD,   9);
    private static final Font FONT_ITEM    = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONT_BADGE   = new Font("Segoe UI", Font.BOLD,   9);

    public static JDesktopPane jDesktopPane_MDI;
    private JPanel  sidebarPanel;
    private JLabel  lblPageTitle;
    private JLabel  lblPageSub;
    private JButton itemActivo;

    // Usuario en sesión
    private String nombreUsuario = "Admin";
    private String rolUsuario    = "Administrador";

    public MDI() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            configurarUIManager();
        } catch (Exception ex) {
            System.err.println("FlatLaf no disponible: " + ex.getMessage());
        }
        initComponents();
        jMenuBar1.setVisible(false);
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1100, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SIGVET ERP");
        construirInterfaz();
    }

    public MDI(String nombre, String rol) {
        this.nombreUsuario = nombre;
        this.rolUsuario    = rol;
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            configurarUIManager();
        } catch (Exception ex) {}
        initComponents();
        jMenuBar1.setVisible(false);
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(1100, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("SIGVET ERP");
        construirInterfaz();
    }

    private void construirInterfaz() {
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(BG_DEEP);

        sidebarPanel = crearSidebar();
        add(sidebarPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new BorderLayout(0, 0));
        rightPanel.setBackground(BG_DEEP);
        rightPanel.add(crearHeader(), BorderLayout.NORTH);

        jDesktopPane_MDI = new JDesktopPane() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(BG_DEEP);
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.setColor(Color.decode("#141824"));
                for (int x = 0; x < getWidth(); x += 24)
                    for (int y = 0; y < getHeight(); y += 24)
                        g2.fillRect(x, y, 1, 1);
                
                if (getAllFrames().length == 0) {
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(Color.decode("#1A1F32"));
                    g2.setFont(new Font("Segoe UI", Font.BOLD, 48));
                    FontMetrics fm = g2.getFontMetrics();
                    String txt = "SIGVET ERP";
                    g2.drawString(txt, (getWidth() - fm.stringWidth(txt)) / 2, getHeight() / 2 - 20);
                    g2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
                    fm = g2.getFontMetrics();
                    String sub = "Selecciona una opción del menú para comenzar";
                    g2.setColor(Color.decode("#1F2640"));
                    g2.drawString(sub, (getWidth() - fm.stringWidth(sub)) / 2, getHeight() / 2 + 20);
                }
            }
        };
        jDesktopPane_MDI.setBackground(BG_DEEP);
        jDesktopPane_MDI.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

        rightPanel.add(jDesktopPane_MDI, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.CENTER);
    }

    private JPanel crearSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(BG_SIDEBAR);
        sidebar.setPreferredSize(new Dimension(240, 0));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER_COLOR));

        JPanel brand = new JPanel(null);
        brand.setBackground(BG_SIDEBAR);
        brand.setMaximumSize(new Dimension(240, 60));
        brand.setPreferredSize(new Dimension(240, 60));
        brand.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR));

        JPanel logoMini = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, ACCENT, getWidth(), getHeight(), ACCENT2);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 6, 6);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 11));
                FontMetrics fm = g2.getFontMetrics();
                String txt = "S";
                g2.drawString(txt, (getWidth() - fm.stringWidth(txt)) / 2, (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
            }
        };
        logoMini.setBounds(14, 14, 24, 24);
        logoMini.setOpaque(false);
        brand.add(logoMini);

        JLabel brandLbl = new JLabel("SIGVET");
        brandLbl.setFont(FONT_BRAND);
        brandLbl.setForeground(TEXT_PRIMARY);
        brandLbl.setBounds(46, 12, 130, 20);
        brand.add(brandLbl);

        JLabel versionLbl = new JLabel("v2.0 ERP");
        versionLbl.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        versionLbl.setForeground(TEXT_HINT);
        versionLbl.setBounds(46, 33, 80, 12);
        brand.add(versionLbl);
        sidebar.add(brand);

        JPanel itemsWrap = new JPanel();
        itemsWrap.setLayout(new BoxLayout(itemsWrap, BoxLayout.Y_AXIS));
        itemsWrap.setBackground(BG_SIDEBAR);
        itemsWrap.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));

        itemsWrap.add(crearSectionLabel("PRINCIPAL"));
        itemsWrap.add(crearSidebarItem("📊", "Dashboard", null, () -> abrirVentana(new FrmDashboard(), "Dashboard", "Vista general del sistema")));
        itemsWrap.add(crearSidebarItem("📈", "Analíticas", null, () -> abrirVentana(new FrmAnaliticas(), "Analíticas", "Reportes y estadísticas")));

        itemsWrap.add(crearSectionLabel("CATEGORÍAS"));
        itemsWrap.add(crearSidebarItem("📑", "Nueva Categoría", null, () -> abrirVentana(new NuevaCategoria(), "Categorías", "Registro de nueva categoría")));
        itemsWrap.add(crearSidebarItem("📂", "Gestionar Categorías", null, () -> abrirVentana(new GestionarCategorias(), "Categorías", "Administración de categorías")));

        itemsWrap.add(crearSectionLabel("PRODUCTO"));
        itemsWrap.add(crearSidebarItem("📦", "Nuevo Producto", null, () -> {
            FrmNuevoProducto vista = new FrmNuevoProducto();
            new NuevoProductoController(vista);
            abrirVentana(vista, "Producto", "Registro de nuevo producto");
        }));
        itemsWrap.add(crearSidebarItem("📋", "Gestion Productos", null, () -> {
            FrmGestionarProductos vista = new FrmGestionarProductos();
            new GestionProductosController(vista);
            abrirVentana(vista, "Producto", "Gestión y stock");
        }));
        itemsWrap.add(crearSidebarItem("⚖️", "Ajuste Inventario", null, () -> {
            FrmAjusteInventario vista = new FrmAjusteInventario();
            new AjusteInventarioController(vista);
            abrirVentana(vista, "Producto", "Ajustes de inventario");
        }));

        itemsWrap.add(crearSectionLabel("PROVEEDORES"));
        itemsWrap.add(crearSidebarItem("🏭", "Nuevo Proveedor", null, () -> {
            FrmNuevoProveedor vista = new FrmNuevoProveedor();
            new CtrlNuevoProveedor(new Proveedor(), vista, new ProveedorDAO());
            abrirVentana(vista, "Proveedores", "Registrar proveedor");
        }));
        itemsWrap.add(crearSidebarItem("🏷️", "Gestionar Proveedores", null, () -> {
            FrmGestionarProveedores vista = new FrmGestionarProveedores();
            CtrlGestionarProveedores ctrl = new CtrlGestionarProveedores(new Proveedor(), vista, new ProveedorDAO());
            ctrl.cargarTabla();
            abrirVentana(vista, "Proveedores", "Directorio de proveedores");
        }));

        itemsWrap.add(crearSectionLabel("CLIENTES"));
        itemsWrap.add(crearSidebarItem("👥", "Nuevo Cliente", null, () -> abrirVentana(new NuevoCliente(), "Clientes", "Nuevo Cliente")));
        itemsWrap.add(crearSidebarItem("🤝", "Gestionar Clientes", null, () -> abrirVentana(new GestionarClientes(), "Clientes", "Gestión de Clientes")));

        itemsWrap.add(crearSectionLabel("USUARIOS"));
        itemsWrap.add(crearSidebarItem("👤", "Nuevo Usuario", null, () -> {
            FrmNuevoUsuario vista = new FrmNuevoUsuario();
            new CtrlNuevoUsuario(new Usuario(), vista, new UsuarioDAO());
            abrirVentana(vista, "Usuarios", "Registrar usuario");
        }));
        itemsWrap.add(crearSidebarItem("🔐", "Gestionar Usuarios", null, () -> {
            FrmGestionarUsuarios vista = new FrmGestionarUsuarios();
            CtrlGestionarUsuarios ctrl = new CtrlGestionarUsuarios(new Usuario(), vista, new UsuarioDAO());
            ctrl.cargarTabla();
            abrirVentana(vista, "Usuarios", "Control de accesos");
        }));
        itemsWrap.add(crearSidebarItem("🛡️", "Roles y Permisos", null, () ->{
            FrmRolesPermisos vista = new FrmRolesPermisos();
            FrmNuevoUsuario vistaUsuario = new FrmNuevoUsuario();           
            new CtrlGestionarRoles(new Roles(), vista,vistaUsuario, new RolesDAO());
            abrirVentana(vista, "Usuarios", "Roles y permisos");
        }));

        itemsWrap.add(crearSectionLabel("COMPRAS"));
        itemsWrap.add(crearSidebarItem("🛒", "Nueva Compra", null, () -> {
            FrmRegistrarNuevaCompra vista = new FrmRegistrarNuevaCompra();
            new CtrlNuevaCompra(new Compra(), vista, new CompraDAO());
            abrirVentana(vista, "Compras", "Registrar compra");
        }));

        itemsWrap.add(crearSectionLabel("FACTURA"));
        itemsWrap.add(crearSidebarItem("🧾", "Punto Venta", null, () -> {
            FrmFacturaPuntoVenta vista = new FrmFacturaPuntoVenta();
            new CtrlPuntoVenta();
            abrirVentana(vista, "Factura", "Punto de venta");
        }));
        itemsWrap.add(crearSidebarItem("📄", "Consultar Factura", null, () -> {
            FrmFacturaConsultar vista = new FrmFacturaConsultar();
            new CtrlConsultarFactura();
            abrirVentana(vista, "Factura", "Historial de ventas");
        }));

        JScrollPane scroll = new JScrollPane(itemsWrap);
        scroll.setBorder(null);
        scroll.setBackground(BG_SIDEBAR);
        scroll.getViewport().setBackground(BG_SIDEBAR);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(8);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        sidebar.add(scroll);

        sidebar.add(crearUserPanel());
        return sidebar;
    }

    private JComponent crearSectionLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(FONT_SECTION);
        lbl.setForeground(TEXT_HINT);
        lbl.setMaximumSize(new Dimension(240, 35));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        lbl.setBorder(BorderFactory.createEmptyBorder(15, 16, 5, 8));
        return lbl;
    }

    private JButton crearSidebarItem(String icono, String texto, String badge, Runnable accion) {
        JButton btn = new JButton() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isRollover()) {
                    g2.setColor(BG_HOVER);
                    g2.fillRoundRect(8, 0, getWidth() - 16, getHeight(), 8, 8);
                }
                super.paintComponent(g);
            }
        };
        btn.setLayout(new BorderLayout());
        btn.setMaximumSize(new Dimension(240, 38));
        btn.setPreferredSize(new Dimension(240, 38));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setBackground(BG_SIDEBAR);
        btn.setForeground(TEXT_MUTED);
        btn.setFont(FONT_ITEM);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 12));

        JLabel iconLbl = new JLabel(icono + "  " + texto);
        iconLbl.setFont(FONT_ITEM);
        iconLbl.setForeground(TEXT_MUTED);
        btn.add(iconLbl, BorderLayout.CENTER);

        btn.addActionListener(e -> {
            if (itemActivo != null) {
                itemActivo.setBackground(BG_SIDEBAR);
                ((JLabel) itemActivo.getComponent(0)).setForeground(TEXT_MUTED);
                itemActivo.setContentAreaFilled(false);
            }
            btn.setBackground(new Color(ACCENT.getRed(), ACCENT.getGreen(), ACCENT.getBlue(), 30));
            btn.setContentAreaFilled(true);
            ((JLabel) btn.getComponent(0)).setForeground(ACCENT);
            itemActivo = btn;
            accion.run();
        });
        return btn;
    }

    private JPanel crearUserPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(BG_SIDEBAR);
        panel.setMaximumSize(new Dimension(240, 65));
        panel.setMinimumSize(new Dimension(240, 65));
        panel.setPreferredSize(new Dimension(240, 65));
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR));

        JPanel avatar = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, ACCENT, getWidth(), getHeight(), ACCENT2);
                g2.setPaint(gp);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
                FontMetrics fm = g2.getFontMetrics();
                String txt = nombreUsuario.length() >= 2 ? nombreUsuario.substring(0, 2).toUpperCase() : "SV";
                g2.drawString(txt, (getWidth() - fm.stringWidth(txt)) / 2, (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
            }
        };
        avatar.setBounds(14, 16, 32, 32);
        avatar.setOpaque(false);
        panel.add(avatar);

        JLabel name = new JLabel(nombreUsuario);
        name.setFont(new Font("Segoe UI", Font.BOLD, 12));
        name.setForeground(TEXT_PRIMARY);
        name.setBounds(54, 14, 140, 16);
        panel.add(name);

        JLabel role = new JLabel(rolUsuario);
        role.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        role.setForeground(TEXT_HINT);
        role.setBounds(54, 32, 140, 14);
        panel.add(role);

        JButton logoutBtn = new JButton("⏻");
        logoutBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        logoutBtn.setForeground(DANGER);
        logoutBtn.setBackground(BG_SIDEBAR);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutBtn.setBounds(195, 20, 30, 24);
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Cerrar sesión y salir?", "SIGVET", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) { dispose(); new FrmLogin().setVisible(true); }
        });
        panel.add(logoutBtn);
        return panel;
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel(null);
        header.setBackground(BG_HEADER);
        header.setPreferredSize(new Dimension(0, 55));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR));

        lblPageTitle = new JLabel("Bienvenido");
        lblPageTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblPageTitle.setForeground(TEXT_PRIMARY);
        lblPageTitle.setBounds(20, 10, 300, 20);
        header.add(lblPageTitle);

        lblPageSub = new JLabel("Seleccione una opción en el menú lateral");
        lblPageSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblPageSub.setForeground(TEXT_MUTED);
        lblPageSub.setBounds(20, 32, 400, 14);
        header.add(lblPageSub);

        JLabel clockLbl = new JLabel();
        clockLbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        clockLbl.setForeground(TEXT_MUTED);
        header.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) { clockLbl.setBounds(header.getWidth() - 150, 20, 120, 16); }
        });
        Timer clockTimer = new Timer(1000, e -> clockLbl.setText(new java.text.SimpleDateFormat("dd MMM yyyy - HH:mm").format(new java.util.Date())));
        clockTimer.start();
        header.add(clockLbl);

        JButton tileBtn = makeHeaderButton("⊞");
        tileBtn.addActionListener(e -> organizarVentanas());
        header.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) { tileBtn.setBounds(header.getWidth() - 80, 13, 32, 28); }
        });
        header.add(tileBtn);

        JButton closeAllBtn = makeHeaderButton("✕");
        closeAllBtn.setForeground(DANGER);
        closeAllBtn.addActionListener(e -> {
            for (JInternalFrame f : jDesktopPane_MDI.getAllFrames()) try { f.setClosed(true); } catch (Exception ex) {}
        });
        header.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) { closeAllBtn.setBounds(header.getWidth() - 40, 13, 30, 28); }
        });
        header.add(closeAllBtn);

        return header;
    }

    private JButton makeHeaderButton(String icon) {
        JButton btn = new JButton(icon);
        btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        btn.setBackground(BG_INPUT);
        btn.setForeground(TEXT_MUTED);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void actualizarPageTitle(String titulo, String sub) {
        if (lblPageTitle != null) lblPageTitle.setText(titulo);
        if (lblPageSub   != null) lblPageSub.setText(sub);
    }

    private void abrirVentana(JInternalFrame frame, String seccion, String subtitulo) {
        actualizarPageTitle(seccion, subtitulo); 
        frame.getContentPane().setBackground(Color.decode("#0A0C10"));
        frame.putClientProperty("JInternalFrame.paletteMode", false);
        frame.putClientProperty("JInternalFrame.style", "dark");
        JInternalFrame.JDesktopIcon di = frame.getDesktopIcon();
        if (di != null) di.setBackground(Color.decode("#181D2E"));
        jDesktopPane_MDI.add(frame);
        frame.setVisible(true);
        try { frame.setSelected(true); } catch (Exception ex) {}
        centrarInternalFrame(frame);
    }

    private void centrarInternalFrame(JInternalFrame f) {
        Dimension desktop = jDesktopPane_MDI.getSize();
        Dimension frame   = f.getPreferredSize();
        if (frame.width > 0 && frame.height > 0) {
            f.setSize(frame.width, frame.height);
            f.setLocation(Math.max(0, (desktop.width - frame.width) / 2), Math.max(0, (desktop.height - frame.height) / 2));
        } else {
            f.setSize(800, 550);
            f.setLocation((desktop.width - 800) / 2, (desktop.height - 550) / 2);
        }
    }

    private void organizarVentanas() {
        JInternalFrame[] frames = jDesktopPane_MDI.getAllFrames();
        if (frames.length == 0) return;
        int cols = (int) Math.ceil(Math.sqrt(frames.length));
        int rows = (int) Math.ceil((double) frames.length / cols);
        int w = jDesktopPane_MDI.getWidth() / cols;
        int h = jDesktopPane_MDI.getHeight() / rows;
        for (int i = 0; i < frames.length; i++) {
            if (!frames[i].isIcon()) frames[i].setBounds((i % cols) * w, (i / cols) * h, w, h);
        }
    }

    private void configurarUIManager() {
        UIManager.put("InternalFrame.background",        Color.decode("#111520"));
        UIManager.put("InternalFrame.activeTitleBackground", Color.decode("#181D2E"));
        UIManager.put("InternalFrame.inactiveTitleBackground", Color.decode("#111520"));
        UIManager.put("InternalFrame.activeTitleForeground", Color.decode("#F0F2FF"));
        UIManager.put("InternalFrame.inactiveTitleForeground", Color.decode("#9BA3C4"));
        UIManager.put("InternalFrame.titleFont",         new Font("Segoe UI", Font.BOLD, 12));
        UIManager.put("InternalFrame.borderColor",       Color.decode("#2A3050"));
        UIManager.put("Table.background",                Color.decode("#111520"));
        UIManager.put("Table.foreground",                Color.decode("#F0F2FF"));
        UIManager.put("Table.selectionBackground",       Color.decode("#6C63FF"));
        UIManager.put("Table.gridColor",                 Color.decode("#1F2640"));
        UIManager.put("TableHeader.background",          Color.decode("#181D2E"));
        UIManager.put("TableHeader.foreground",          Color.decode("#9BA3C4"));
        UIManager.put("ComboBox.background",             Color.decode("#181D2E"));
        UIManager.put("ComboBox.foreground",             Color.decode("#F0F2FF"));
        UIManager.put("ComboBox.selectionBackground",    Color.decode("#6C63FF"));
        UIManager.put("TextField.background",            Color.decode("#181D2E"));
        UIManager.put("TextField.foreground",            Color.decode("#F0F2FF"));
        UIManager.put("TextField.caretForeground",       Color.decode("#6C63FF"));
    }

    // =========================================================================
    // BLOQUE INTACTO AUTOGENERADO POR NETBEANS DE TUS LÍNEAS ORIGINALES
    // =========================================================================

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu_proveedores = new javax.swing.JMenu();
        jMenuItem_nuevo_proveedor = new javax.swing.JMenuItem();
        jMenuItem_gestionar_proveedores = new javax.swing.JMenuItem();
        jMenu_producto = new javax.swing.JMenu();
        jMenuItem_nuevo_producto = new javax.swing.JMenuItem();
        jMenuItem_gestionar_producto = new javax.swing.JMenuItem();
        jMenuItem_ajuste_inventario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        nuevaCompra = new javax.swing.JMenuItem();
        jMenu_Factura = new javax.swing.JMenu();
        jMenuItem_punto_venta = new javax.swing.JMenuItem();
        jMenuItem_consultar_factura = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu_proveedores.setText("Proveedores");

        jMenuItem_nuevo_proveedor.setText("Nuevo Proveedor");
        jMenuItem_nuevo_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_nuevo_proveedorActionPerformed(evt);
            }
        });
        jMenu_proveedores.add(jMenuItem_nuevo_proveedor);

        jMenuItem_gestionar_proveedores.setText("Gestionar Proveedores");
        jMenuItem_gestionar_proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_gestionar_proveedoresActionPerformed(evt);
            }
        });
        jMenu_proveedores.add(jMenuItem_gestionar_proveedores);

        jMenuBar1.add(jMenu_proveedores);

        jMenu_producto.setText("Producto");

        jMenuItem_nuevo_producto.setText("Nuevo Producto");
        jMenuItem_nuevo_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_nuevo_productoActionPerformed(evt);
            }
        });
        jMenu_producto.add(jMenuItem_nuevo_producto);

        jMenuItem_gestionar_producto.setText("Gestion Productos");
        jMenuItem_gestionar_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_gestionar_productoActionPerformed(evt);
            }
        });
        jMenu_producto.add(jMenuItem_gestionar_producto);

        jMenuItem_ajuste_inventario.setText("Ajuste de Inventario");
        jMenuItem_ajuste_inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ajuste_inventarioActionPerformed(evt);
            }
        });
        jMenu_producto.add(jMenuItem_ajuste_inventario);

        jMenuBar1.add(jMenu_producto);

        jMenu2.setText("Usuarios");

        jMenuItem2.setText("Nuevo Usuario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Gestionar Usuarios");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Roles y Permisos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Compras");

        nuevaCompra.setText("Nueva Compra");
        nuevaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevaCompraActionPerformed(evt);
            }
        });
        jMenu3.add(nuevaCompra);

        jMenuBar1.add(jMenu3);

        jMenu_Factura.setText("Factura");

        jMenuItem_punto_venta.setText("Punto Venta");
        jMenuItem_punto_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_punto_ventaActionPerformed(evt);
            }
        });
        jMenu_Factura.add(jMenuItem_punto_venta);

        jMenuItem_consultar_factura.setText("Consultar Factura");
        jMenuItem_consultar_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_consultar_facturaActionPerformed(evt);
            }
        });
        jMenu_Factura.add(jMenuItem_consultar_factura);

        jMenuBar1.add(jMenu_Factura);

        setJMenuBar(jMenuBar1);

        pack();
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        Vistas.FrmRolesPermisos roles = new Vistas.FrmRolesPermisos();
        FrmNuevoUsuario usuarios = new FrmNuevoUsuario();
        Modelos.Roles rol = new Modelos.Roles();
        Modelos.RolesDAO dao = new Modelos.RolesDAO();
        Modelos.UsuarioDAO daoUsuario = new Modelos.UsuarioDAO();
        Usuario usuario = new Usuario();
        Controladores.CtrlRoles.CtrlGestionarRoles controlador = new Controladores.CtrlRoles.CtrlGestionarRoles(rol, roles, usuarios, dao);
        Controladores.CtrlUsuarios.CtrlNuevoUsuario controladorUsuario = new Controladores.CtrlUsuarios.CtrlNuevoUsuario(usuario, usuarios, daoUsuario);
        abrirVentana(usuarios, "Usuarios", "Registrar Staff");
    }

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
        Modelos.Usuario usuario = new Modelos.Usuario();
        FrmGestionarUsuarios gestionarUsuarios = new FrmGestionarUsuarios();
        Modelos.UsuarioDAO usuarioDAO = new Modelos.UsuarioDAO();
        Controladores.CtrlUsuarios.CtrlGestionarUsuarios ctrl = new Controladores.CtrlUsuarios.CtrlGestionarUsuarios(usuario, gestionarUsuarios, usuarioDAO);
        ctrl.cargarTabla();
        abrirVentana(gestionarUsuarios, "Usuarios", "Gestión de Usuarios");
    }

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
        FrmRolesPermisos roles = new FrmRolesPermisos();
        Vistas.FrmNuevoUsuario form2 = new Vistas.FrmNuevoUsuario();
        Modelos.Roles modeloRoles = new Modelos.Roles();
        Modelos.RolesDAO rolesDAO = new Modelos.RolesDAO();
        Controladores.CtrlRoles.CtrlGestionarRoles ctrlRoles = new Controladores.CtrlRoles.CtrlGestionarRoles(modeloRoles, roles, form2, rolesDAO);
        //GEN-LAST:event_jMenuItem4ActionPerformed
        abrirVentana(roles, "Usuarios", "Roles y Permisos");
    }

    private void nuevaCompraActionPerformed(java.awt.event.ActionEvent evt) {
        FrmRegistrarNuevaCompra nuevaCompra = new FrmRegistrarNuevaCompra();
        Modelos.Compra compra = new Modelos.Compra();
        Modelos.CompraDAO compraDAO = new Modelos.CompraDAO();
        Controladores.CtrlCompra.CtrlNuevaCompra ctrlCompra = new Controladores.CtrlCompra.CtrlNuevaCompra(compra, nuevaCompra, compraDAO);
        abrirVentana(nuevaCompra, "Compras", "Nueva Compra");
    }

    private void jMenuItem_punto_ventaActionPerformed(java.awt.event.ActionEvent evt) {
        FrmFacturaPuntoVenta puntoVenta = new FrmFacturaPuntoVenta();
        Controladores.CtrlFactura.CtrlPuntoVenta pV = new Controladores.CtrlFactura.CtrlPuntoVenta();
        abrirVentana(puntoVenta, "Factura", "Punto de Venta");
    }

    private void jMenuItem_consultar_facturaActionPerformed(java.awt.event.ActionEvent evt) {
        FrmFacturaConsultar consultar = new FrmFacturaConsultar();
        Controladores.CtrlFactura.CtrlConsultarFactura Con = new Controladores.CtrlFactura.CtrlConsultarFactura();
        abrirVentana(consultar, "Factura", "Consultar Factura");
    }

    private void jMenuItem_nuevo_proveedorActionPerformed(java.awt.event.ActionEvent evt) {
        Vistas.FrmNuevoProveedor form = new Vistas.FrmNuevoProveedor();
        Modelos.Proveedor proveedor = new Modelos.Proveedor();
        Modelos.ProveedorDAO dao = new Modelos.ProveedorDAO();
        Controladores.CtrlProveedor.CtrlNuevoProveedor ctrl = new Controladores.CtrlProveedor.CtrlNuevoProveedor(proveedor, form, dao);
        abrirVentana(form, "Proveedores", "Nuevo Proveedor");
    }

    private void jMenuItem_gestionar_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {
        Vistas.FrmGestionarProveedores form = new Vistas.FrmGestionarProveedores();
        Modelos.Proveedor proveedor = new Modelos.Proveedor();
        Modelos.ProveedorDAO dao = new Modelos.ProveedorDAO();
        Controladores.CtrlProveedor.CtrlGestionarProveedores ctrl = new Controladores.CtrlProveedor.CtrlGestionarProveedores(proveedor, form, dao);
        ctrl.cargarTabla();
        abrirVentana(form, "Proveedores", "Gestionar Proveedores");
    }

    private void jMenuItem_nuevo_productoActionPerformed(java.awt.event.ActionEvent evt) {
        FrmNuevoProducto vista = new FrmNuevoProducto();
        NuevoProductoController controlador = new NuevoProductoController(vista);
        abrirVentana(vista, "Producto", "Nuevo Producto");
    }

    private void jMenuItem_gestionar_productoActionPerformed(java.awt.event.ActionEvent evt) {
        FrmGestionarProductos vista = new FrmGestionarProductos();
        GestionProductosController controlador = new GestionProductosController(vista);
        abrirVentana(vista, "Producto", "Gestión de Productos");
    }

    private void jMenuItem_ajuste_inventarioActionPerformed(java.awt.event.ActionEvent evt) {
        FrmAjusteInventario vista = new FrmAjusteInventario();
        AjusteInventarioController controlador = new AjusteInventarioController(vista);
        abrirVentana(vista, "Producto", "Ajuste de Inventario");
    }

    public static void main(String[] args) {
        FlatDarkLaf.setup();
        SwingUtilities.invokeLater(() -> new FrmLogin().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem_ajuste_inventario;
    private javax.swing.JMenuItem jMenuItem_consultar_factura;
    private javax.swing.JMenuItem jMenuItem_gestionar_producto;
    private javax.swing.JMenuItem jMenuItem_gestionar_proveedores;
    private javax.swing.JMenuItem jMenuItem_nuevo_producto;
    private javax.swing.JMenuItem jMenuItem_nuevo_proveedor;
    private javax.swing.JMenuItem jMenuItem_punto_venta;
    private javax.swing.JMenu jMenu_Factura;
    private javax.swing.JMenu jMenu_producto;
    private javax.swing.JMenu jMenu_proveedores;
    private javax.swing.JMenuItem nuevaCompra;
    // End of variables declaration
}
