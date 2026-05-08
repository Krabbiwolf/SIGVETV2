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

    private static final int FW = 300;   
    private static final int FH = 42;    
    private static final int LH = 16;    
    private static final int X  = 22;    

    public FrmLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("SIGVET - Portal de Acceso");
        
        aplicarEstiloLoginSIGVET();
        
        btnIngresar.addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Credenciales requeridas", "Seguridad SIGVET", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.login(user, pass);

        if (u != null) {
            this.dispose();
            new MDI().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Acceso denegado: Usuario o clave inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void aplicarEstiloLoginSIGVET() {
        this.getContentPane().setBackground(BG_DEEP);

        panelPrincipal.setBackground(BG_CARD);
        panelPrincipal.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER, 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setText("SIGVET");

        lblSubtitulo.setForeground(TEXT_MUT);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setText("Bienvenido al ecosistema SIGVET");

        lblUsuario.setForeground(TEXT_MUT);
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblUsuario.setText("USUARIO");

        lblPassword.setForeground(TEXT_MUT);
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblPassword.setText("CONTRASEÑA");

        javax.swing.JTextField[] inputs = {txtUsuario, txtPassword};
        for (javax.swing.JTextField in : inputs) {
            in.setBackground(BG_INPUT);
            in.setForeground(TEXT_PRI);
            in.setCaretColor(ACCENT);
            in.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            in.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER, 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
            ));
            
            in.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent e) {
                    in.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(ACCENT, 1),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)
                    ));
                }
                public void focusLost(java.awt.event.FocusEvent e) {
                    in.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER, 1),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)
                    ));
                }
            });
        }

        btnIngresar.setBackground(ACCENT);
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setOpaque(true);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        btnIngresar.setText("Iniciar Sesión");
        
        btnIngresar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnIngresar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnIngresar.setBackground(ACCENT); }
            public void mousePressed(MouseEvent e) { btnIngresar.setBackground(Color.decode("#4A42C8")); }
            public void mouseReleased(MouseEvent e){ btnIngresar.setBackground(ACCENT); }
        });

        lblFooter.setForeground(TEXT_HINT);
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
        lblFooter.setText("© 2026 SIGVET ERP — Todos los derechos reservados");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        lblFooter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelPrincipal.add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, 344, 32));
        panelPrincipal.add(lblSubtitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 344, 20));
        panelPrincipal.add(lblUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(X, 146, FW, LH));
        panelPrincipal.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(X, 164, FW, FH));
        panelPrincipal.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(X, 222, FW, LH));
        panelPrincipal.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(X, 240, FW, FH));
        panelPrincipal.add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(X, 308, FW, 46));
        panelPrincipal.add(lblFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 344, 16));

        getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 30, 344, 416));

        setSize(new java.awt.Dimension(440, 510));
    }// </editor-fold>                        

    public static void main(String args[]) {
        com.formdev.flatlaf.FlatDarkLaf.setup();
        java.awt.EventQueue.invokeLater(() -> new FrmLogin().setVisible(true));
    }

    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel lblFooter;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
}