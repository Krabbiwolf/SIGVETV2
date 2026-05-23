package Vistas;

import Modelos.Usuario;
import Modelos.UsuarioDAO;
import Modelos.SesionUsuario;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class FrmLogin extends javax.swing.JFrame {

    public FrmLogin() {
        initComponents();
        // ESTO EVITA QUE SEA UNA VENTANITA PEQUEÑA
        this.setSize(450, 520);
        this.setLocationRelativeTo(null); 
        
        // Efecto visual botón
        btnIngresar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnIngresar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnIngresar.setBackground(Color.decode("#6C63FF")); }
        });
    }

    private void iniciarSesion() {
        String user = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Credenciales requeridas", "Seguridad SIGVET", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UsuarioDAO dao = new UsuarioDAO();
        // IMPORTANTE: Si dao.login() va a la nube, aquí podrías tener un milisegundo de lag, 
        // pero en el login es aceptable. El problema mayor es en las tablas llenas de datos.
        Usuario u = dao.login(user, pass);

        if (u != null) {
            SesionUsuario.iniciarSesion(u);
            this.dispose();
            new MDI(SesionUsuario.getNombreUsuarioActual(), SesionUsuario.getRolUsuarioActual()).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Acceso denegado: Usuario o clave inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new javax.swing.JPanel();
        panelCard = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SIGVET - Iniciar Sesión");
        setResizable(false);
        getContentPane().setLayout(null);

        panelFondo.setBackground(new java.awt.Color(10, 12, 16));
        panelFondo.setLayout(null);

        panelCard.setBackground(new java.awt.Color(17, 21, 32));
        panelCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelCard.setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 242, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("SIGVET ERP");
        panelCard.add(lblTitulo);
        lblTitulo.setBounds(0, 30, 350, 36);

        lblSub.setForeground(new java.awt.Color(155, 163, 196));
        lblSub.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub.setText("Ingresa tus credenciales");
        panelCard.add(lblSub);
        lblSub.setBounds(0, 70, 350, 16);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(155, 163, 196));
        jLabel1.setText("USUARIO");
        panelCard.add(jLabel1);
        jLabel1.setBounds(30, 120, 290, 15);

        txtUsuario.setBackground(new java.awt.Color(24, 29, 46));
        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(240, 242, 255));
        txtUsuario.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtUsuario.setCaretColor(new java.awt.Color(108, 99, 255));
        panelCard.add(txtUsuario);
        txtUsuario.setBounds(30, 140, 290, 40);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 163, 196));
        jLabel2.setText("CONTRASEÑA");
        panelCard.add(jLabel2);
        jLabel2.setBounds(30, 200, 290, 15);

        txtPassword.setBackground(new java.awt.Color(24, 29, 46));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(240, 242, 255));
        txtPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtPassword.setCaretColor(new java.awt.Color(108, 99, 255));
        panelCard.add(txtPassword);
        txtPassword.setBounds(30, 220, 290, 40);

        btnIngresar.setBackground(new java.awt.Color(108, 99, 255));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("Iniciar Sesión");
        btnIngresar.setBorderPainted(false);
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.setFocusPainted(false);
        btnIngresar.addActionListener(this::btnIngresarActionPerformed);
        panelCard.add(btnIngresar);
        btnIngresar.setBounds(30, 300, 290, 45);

        panelFondo.add(panelCard);
        panelCard.setBounds(50, 40, 350, 400);

        getContentPane().add(panelFondo);
        panelFondo.setBounds(0, 0, 450, 520);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_btnIngresarActionPerformed

    public static void main(String args[]) {
        // Look And Feel del sistema para compilar sin errores de dependencias externas
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.err.println("No se pudo cargar el tema visual del sistema");
        }
        java.awt.EventQueue.invokeLater(() -> new FrmLogin().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelCard;
    private javax.swing.JPanel panelFondo;
    public javax.swing.JPasswordField txtPassword;
    public javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}