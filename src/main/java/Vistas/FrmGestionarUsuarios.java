package Vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmGestionarUsuarios extends javax.swing.JInternalFrame {

    public FrmGestionarUsuarios() {
        initComponents();
        aplicarEstiloMinimalistaPremium();
    }

    private void aplicarEstiloMinimalistaPremium() {
        // === FONDO PRINCIPAL ===
        this.getContentPane().setBackground(Color.decode("#0A0C10"));

        // === TÍTULO ===
        jLabel1.setForeground(Color.decode("#F0F2FF"));
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 20));
        jLabel1.setText("✦  Gestión de Usuarios");

        // === LABELS CAMPO ===
        jLabel2.setForeground(Color.decode("#9BA3C4"));
        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel2.setText("CONTRASEÑA");

        jLabel3.setForeground(Color.decode("#9BA3C4"));
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel3.setText("ESTADO");

        jLabel4.setForeground(Color.decode("#9BA3C4"));
        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel4.setText("TELÉFONO");

        // === INPUTS ===
        txtNuevaPassword.setBackground(Color.decode("#181D2E"));
        txtNuevaPassword.setForeground(Color.decode("#F0F2FF"));
        txtNuevaPassword.setCaretColor(Color.decode("#6C63FF"));
        txtNuevaPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNuevaPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#2A3050"), 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        txtNuevaPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                txtNuevaPassword.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.decode("#6C63FF"), 1),
                    BorderFactory.createEmptyBorder(6, 10, 6, 10)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                txtNuevaPassword.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.decode("#2A3050"), 1),
                    BorderFactory.createEmptyBorder(6, 10, 6, 10)
                ));
            }
        });

        txtNuevoTelefono.setBackground(Color.decode("#181D2E"));
        txtNuevoTelefono.setForeground(Color.decode("#F0F2FF"));
        txtNuevoTelefono.setCaretColor(Color.decode("#6C63FF"));
        txtNuevoTelefono.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtNuevoTelefono.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#2A3050"), 1),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        txtNuevoTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                txtNuevoTelefono.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.decode("#6C63FF"), 1),
                    BorderFactory.createEmptyBorder(6, 10, 6, 10)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                txtNuevoTelefono.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.decode("#2A3050"), 1),
                    BorderFactory.createEmptyBorder(6, 10, 6, 10)
                ));
            }
        });

        // === COMBOBOX ===
        comboNuevoEstado.setBackground(Color.decode("#181D2E"));
        comboNuevoEstado.setForeground(Color.decode("#F0F2FF"));
        comboNuevoEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboNuevoEstado.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));

        // === BOTÓN ACTUALIZAR ===
        btnActualizarUsuario.setBackground(Color.decode("#6C63FF"));
        btnActualizarUsuario.setForeground(Color.WHITE);
        btnActualizarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnActualizarUsuario.setFocusPainted(false);
        btnActualizarUsuario.setBorderPainted(false);
        btnActualizarUsuario.setOpaque(true);
        btnActualizarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnActualizarUsuario.setText("Actualizar");
        btnActualizarUsuario.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnActualizarUsuario.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnActualizarUsuario.setBackground(Color.decode("#6C63FF")); }
        });

        // === BOTÓN ELIMINAR ===
        btnEliminarUsuario.setBackground(Color.decode("#1E0F14"));
        btnEliminarUsuario.setForeground(Color.decode("#FF5B7A"));
        btnEliminarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEliminarUsuario.setFocusPainted(false);
        btnEliminarUsuario.setBorder(BorderFactory.createLineBorder(Color.decode("#3A1520"), 1));
        btnEliminarUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminarUsuario.setText("Eliminar");
        btnEliminarUsuario.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnEliminarUsuario.setBackground(Color.decode("#FF5B7A"));
                btnEliminarUsuario.setForeground(Color.WHITE);
            }
            public void mouseExited(MouseEvent e) {
                btnEliminarUsuario.setBackground(Color.decode("#1E0F14"));
                btnEliminarUsuario.setForeground(Color.decode("#FF5B7A"));
            }
        });

        // === TABLA USUARIOS ===
        tableUsuarios.setBackground(Color.decode("#111520"));
        tableUsuarios.setForeground(Color.decode("#F0F2FF"));
        tableUsuarios.setGridColor(Color.decode("#1F2640"));
        tableUsuarios.setRowHeight(30);
        tableUsuarios.setSelectionBackground(Color.decode("#6C63FF"));
        tableUsuarios.setSelectionForeground(Color.WHITE);
        tableUsuarios.setShowGrid(true);
        tableUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tableUsuarios.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tableUsuarios.setFillsViewportHeight(true);

        tableUsuarios.getTableHeader().setBackground(Color.decode("#181D2E"));
        tableUsuarios.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tableUsuarios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tableUsuarios.getTableHeader().setReorderingAllowed(false);
        tableUsuarios.getTableHeader().setBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050"))
        );
        ((DefaultTableCellRenderer) tableUsuarios.getTableHeader().getDefaultRenderer())
            .setHorizontalAlignment(javax.swing.JLabel.LEFT);

        jScrollPane1.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        jScrollPane1.setBackground(Color.decode("#111520"));
        jScrollPane1.getViewport().setBackground(Color.decode("#111520"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUsuarios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboNuevoEstado = new javax.swing.JComboBox<>();
        txtNuevaPassword = new javax.swing.JTextField();
        btnEliminarUsuario = new javax.swing.JButton();
        btnActualizarUsuario = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNuevoTelefono = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("Gestión de Usuarios");

        tableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
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
        jScrollPane1.setViewportView(tableUsuarios);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Contraseña:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Estado:");

        comboNuevoEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));

        btnEliminarUsuario.setText("Eliminar");

        btnActualizarUsuario.setText("Actualizar");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Telefono:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(295, 295, 295)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnActualizarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboNuevoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNuevaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNuevoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNuevaPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNuevoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(comboNuevoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizarUsuario;
    public javax.swing.JButton btnEliminarUsuario;
    public javax.swing.JComboBox<String> comboNuevoEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tableUsuarios;
    public javax.swing.JTextField txtNuevaPassword;
    public javax.swing.JTextField txtNuevoTelefono;
    // End of variables declaration//GEN-END:variables
}