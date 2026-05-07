package Vistas;

import Modelos.Usuario;
import Modelos.UsuarioDAO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FrmLogin extends javax.swing.JFrame {

    private static final Color BG_DEEP   = Color.decode("#0A0C10");
    private static final Color BG_CARD   = Color.decode("#111520");
    private static final Color BG_INPUT  = Color.decode("#181D2E");
    private static final Color BORDER    = Color.decode("#2A3050");
    private static final Color ACCENT    = Color.decode("#6C63FF");
    private static final Color ACCENT2   = Color.decode("#FF6B9D");
    private static final Color TEXT_PRI  = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT  = Color.decode("#9BA3C4");
    private static final Color TEXT_HINT = Color.decode("#5A6280");

    // Todos los campos con el mismo ancho/alto
    private static final int FW = 300;   // field width
    private static final int FH = 42;    // field height
    private static final int LH = 16;    // label height
    private static final int X  = 22;    // left margin dentro del card

    // ── Componentes ─────────────────────────────────────────────────────────
    private JPanel         panelPrincipal;
    private JLabel         lblLogo, lblTitulo, lblSubtitulo;
    private JLabel         lblUsuario, lblPassword;
    private JTextField     txtUsuario;
    private JPasswordField txtPassword;
    private JButton        btnIngresar;
    private JLabel         lblFooter;

    public FrmLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("NexStore — Access Portal");
        aplicarEstilo();
        btnIngresar.addActionListener(e -> iniciarSesion());
        txtPassword.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();
        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor ingresa usuario y contraseña.",
                "Campos requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.login(user, pass);
        if (u != null) {
            this.dispose();
            new MDI().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                "Usuario o contraseña incorrectos.",
                "Acceso denegado", JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtPassword.requestFocus();
        }
    }

    private void aplicarEstilo() {
        getContentPane().setBackground(BG_DEEP);
        panelPrincipal.setBackground(BG_CARD);
        panelPrincipal.setBorder(BorderFactory.createLineBorder(BORDER, 1));

        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblSubtitulo.setForeground(TEXT_MUT);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        lblUsuario.setForeground(TEXT_MUT);
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 10));

        lblPassword.setForeground(TEXT_MUT);
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 10));

        estilizarInput(txtUsuario);
        txtPassword.setBackground(BG_INPUT);
        txtPassword.setForeground(TEXT_PRI);
        txtPassword.setCaretColor(ACCENT);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBorderNormal(txtPassword);
        txtPassword.addFocusListener(focusListener(txtPassword));

        btnIngresar.setBackground(ACCENT);
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setOpaque(true);
        btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIngresar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnIngresar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnIngresar.setBackground(ACCENT); }
        });

        lblFooter.setForeground(TEXT_HINT);
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 9));
        lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void estilizarInput(JTextField f) {
        f.setBackground(BG_INPUT);
        f.setForeground(TEXT_PRI);
        f.setCaretColor(ACCENT);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBorderNormal(f);
        f.addFocusListener(focusListener(f));
    }

    private FocusAdapter focusListener(JComponent c) {
        return new FocusAdapter() {
            public void focusGained(FocusEvent e) { setBorderAccent(c); }
            public void focusLost(FocusEvent e)   { setBorderNormal(c); }
        };
    }

    private void setBorderNormal(JComponent c) {
        c.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            new EmptyBorder(8, 12, 8, 12)));
    }

    private void setBorderAccent(JComponent c) {
        c.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ACCENT, 1),
            new EmptyBorder(8, 12, 8, 12)));
    }

    private ImageIcon crearLogoIcon(int size) {
        java.awt.image.BufferedImage img =
            new java.awt.image.BufferedImage(size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, ACCENT, size, size, ACCENT2);
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, size, size, 12, 12);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Segoe UI", Font.BOLD, (int)(size * 0.54)));
        FontMetrics fm = g2.getFontMetrics();
        String txt = "N";
        g2.drawString(txt, (size - fm.stringWidth(txt)) / 2,
            (size + fm.getAscent() - fm.getDescent()) / 2);
        g2.dispose();
        return new ImageIcon(img);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        panelPrincipal = new JPanel();
        lblLogo        = new JLabel();
        lblTitulo      = new JLabel();
        lblSubtitulo   = new JLabel();
        lblUsuario     = new JLabel();
        txtUsuario     = new JTextField();
        lblPassword    = new JLabel();
        txtPassword    = new JPasswordField();
        btnIngresar    = new JButton();
        lblFooter      = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        // Logo 46x46 centrado en card de 344px → x=(344-46)/2=149
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setIcon(crearLogoIcon(46));
        panelPrincipal.add(lblLogo,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 28, 46, 46));

        // Título centrado
        lblTitulo.setText("NexStore");
        panelPrincipal.add(lblTitulo,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 82, 344, 36));

        // Subtítulo
        lblSubtitulo.setText("Ingresa tus credenciales para continuar");
        panelPrincipal.add(lblSubtitulo,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 344, 20));

        // Separador
        JSeparator sep = new JSeparator();
        sep.setForeground(BORDER);
        panelPrincipal.add(sep,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(X, 152, FW, 1));

        // Label usuario
        lblUsuario.setText("USUARIO");
        panelPrincipal.add(lblUsuario,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(X, 164, FW, LH));

        // Campo usuario
        panelPrincipal.add(txtUsuario,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(X, 182, FW, FH));

        // Label password
        lblPassword.setText("CONTRASEÑA");
        panelPrincipal.add(lblPassword,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(X, 240, FW, LH));

        // Campo password
        panelPrincipal.add(txtPassword,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(X, 258, FW, FH));

        // Botón login
        btnIngresar.setText("Iniciar Sesión");
        panelPrincipal.add(btnIngresar,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(X, 326, FW, 46));

        // Footer
        lblFooter.setText("© 2025 NexStore ERP — Todos los derechos reservados");
        panelPrincipal.add(lblFooter,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 344, 16));

        // Card en ventana: card=344x416, ventana=440x510, margen=(440-344)/2=48
        getContentPane().add(panelPrincipal,
            new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 40, 344, 416));

        setSize(440, 510);
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        try { com.formdev.flatlaf.FlatDarkLaf.setup(); } catch (Exception e) {}
        java.awt.EventQueue.invokeLater(() -> new FrmLogin().setVisible(true));
    }
}