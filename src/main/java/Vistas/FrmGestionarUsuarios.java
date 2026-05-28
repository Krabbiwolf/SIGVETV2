package Vistas;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmGestionarUsuarios extends javax.swing.JInternalFrame {

    public FrmGestionarUsuarios() {
        initComponents();
        
        // Estilizar cabecera de tabla
        tableUsuarios.getTableHeader().setBackground(Color.decode("#181D2E"));
        tableUsuarios.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tableUsuarios.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        tableUsuarios.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050")));
        ((DefaultTableCellRenderer) tableUsuarios.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Efectos Hover a Botones
        btnActualizarUsuario.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnActualizarUsuario.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnActualizarUsuario.setBackground(Color.decode("#6C63FF")); }
        });

        btnEliminarUsuario.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnEliminarUsuario.setBackground(Color.decode("#FF5B7A")); btnEliminarUsuario.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e) { btnEliminarUsuario.setBackground(Color.decode("#1E0A10")); btnEliminarUsuario.setForeground(Color.decode("#FF5B7A")); }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTituloOLD = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        tableUsuarios = new javax.swing.JTable();
        lblContraseña = new javax.swing.JLabel();
        txtNuevaPassword = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtNuevoTelefono = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        comboNuevoEstado = new javax.swing.JComboBox<>();
        btnActualizarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestión de Usuarios");
        setPreferredSize(new java.awt.Dimension(820, 560));
        getContentPane().setLayout(null);

        lblTituloOLD.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTituloOLD.setForeground(new java.awt.Color(240, 242, 255));
        lblTituloOLD.setText("✦  Gestión de Usuarios");
        getContentPane().add(lblTituloOLD);
        lblTituloOLD.setBounds(20, 15, 400, 30);

        scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));

        tableUsuarios.setBackground(new java.awt.Color(14, 18, 25));
        tableUsuarios.setForeground(new java.awt.Color(240, 242, 255));
        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido", "DUI", "Telefono", "Username", "Contraseña", "Imagen", "Estado", "Rol"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUsuarios.setGridColor(new java.awt.Color(26, 31, 48));
        tableUsuarios.setRowHeight(30);
        tableUsuarios.setSelectionBackground(new java.awt.Color(108, 99, 255));
        tableUsuarios.setSelectionForeground(new java.awt.Color(255, 255, 255));
        scrollPane.setViewportView(tableUsuarios);

        getContentPane().add(scrollPane);
        scrollPane.setBounds(20, 50, 760, 280);

        lblContraseña.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblContraseña.setForeground(new java.awt.Color(155, 163, 196));
        lblContraseña.setText("NUEVA CONTRASEÑA");
        getContentPane().add(lblContraseña);
        lblContraseña.setBounds(20, 350, 240, 15);

        txtNuevaPassword.setBackground(new java.awt.Color(24, 29, 46));
        txtNuevaPassword.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNuevaPassword.setForeground(new java.awt.Color(240, 242, 255));
        txtNuevaPassword.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtNuevaPassword);
        txtNuevaPassword.setBounds(20, 370, 240, 36);

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(155, 163, 196));
        lblTelefono.setText("NUEVO TELÉFONO");
        getContentPane().add(lblTelefono);
        lblTelefono.setBounds(274, 350, 180, 15);

        txtNuevoTelefono.setBackground(new java.awt.Color(24, 29, 46));
        txtNuevoTelefono.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNuevoTelefono.setForeground(new java.awt.Color(240, 242, 255));
        txtNuevoTelefono.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtNuevoTelefono);
        txtNuevoTelefono.setBounds(274, 370, 180, 36);

        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(155, 163, 196));
        lblEstado.setText("ESTADO");
        getContentPane().add(lblEstado);
        lblEstado.setBounds(468, 350, 160, 15);

        comboNuevoEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        comboNuevoEstado.setBackground(new java.awt.Color(24, 29, 46));
        comboNuevoEstado.setForeground(new java.awt.Color(240, 242, 255));
        comboNuevoEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        getContentPane().add(comboNuevoEstado);
        comboNuevoEstado.setBounds(468, 370, 160, 36);

        btnActualizarUsuario.setBackground(new java.awt.Color(108, 99, 255));
        btnActualizarUsuario.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizarUsuario.setText("Actualizar");
        btnActualizarUsuario.setBorderPainted(false);
        btnActualizarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizarUsuario.setFocusPainted(false);
        getContentPane().add(btnActualizarUsuario);
        btnActualizarUsuario.setBounds(20, 420, 120, 36);

        btnEliminarUsuario.setBackground(new java.awt.Color(30, 10, 16));
        btnEliminarUsuario.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEliminarUsuario.setForeground(new java.awt.Color(255, 91, 122));
        btnEliminarUsuario.setText("Eliminar");
        btnEliminarUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnEliminarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarUsuario.setFocusPainted(false);
        getContentPane().add(btnEliminarUsuario);
        btnEliminarUsuario.setBounds(154, 420, 120, 36);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizarUsuario;
    public javax.swing.JButton btnEliminarUsuario;
    public javax.swing.JComboBox<String> comboNuevoEstado;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTituloOLD;
    private javax.swing.JScrollPane scrollPane;
    public static javax.swing.JTable tableUsuarios;
    public javax.swing.JTextField txtNuevaPassword;
    public javax.swing.JTextField txtNuevoTelefono;
    // End of variables declaration//GEN-END:variables
}