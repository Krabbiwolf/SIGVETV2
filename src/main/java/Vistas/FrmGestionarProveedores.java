package Vistas;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmGestionarProveedores extends javax.swing.JInternalFrame {

    public static javax.swing.JTable TableProveedores;

    public FrmGestionarProveedores() {
        initComponents();
        TableProveedores = tableProveedores; // Alias para evitar errores en tu controlador
        
        // Estilizar cabecera de tabla
        tableProveedores.getTableHeader().setBackground(Color.decode("#181D2E"));
        tableProveedores.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tableProveedores.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        tableProveedores.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050")));
        ((DefaultTableCellRenderer) tableProveedores.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Efectos Hover
        btnActualizar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnActualizar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnActualizar.setBackground(Color.decode("#6C63FF")); }
        });

        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnEliminar.setBackground(Color.decode("#FF5B7A")); btnEliminar.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e) { btnEliminar.setBackground(Color.decode("#1E0A10")); btnEliminar.setForeground(Color.decode("#FF5B7A")); }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        panelTabla = new javax.swing.JPanel();
        scrollTabla = new javax.swing.JScrollPane();
        tableProveedores = new javax.swing.JTable();
        panelBotones = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        panelEdicion = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestionar Proveedores");
        setPreferredSize(new java.awt.Dimension(880, 480));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 242, 255));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("✦  Gestionar Proveedores");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(0, 14, 880, 28);

        panelTabla.setBackground(new java.awt.Color(17, 21, 32));
        panelTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelTabla.setLayout(null);

        scrollTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));

        tableProveedores.setBackground(new java.awt.Color(14, 18, 25));
        tableProveedores.setForeground(new java.awt.Color(240, 242, 255));
        tableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Teléfono", "Estado"
            }
        ));
        tableProveedores.setGridColor(new java.awt.Color(26, 31, 48));
        tableProveedores.setRowHeight(30);
        tableProveedores.setSelectionBackground(new java.awt.Color(108, 99, 255));
        tableProveedores.setSelectionForeground(new java.awt.Color(255, 255, 255));
        scrollTabla.setViewportView(tableProveedores);

        panelTabla.add(scrollTabla);
        scrollTabla.setBounds(10, 10, 580, 210);

        getContentPane().add(panelTabla);
        panelTabla.setBounds(12, 50, 600, 230);

        panelBotones.setBackground(new java.awt.Color(17, 21, 32));
        panelBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelBotones.setLayout(null);

        btnActualizar.setBackground(new java.awt.Color(108, 99, 255));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorderPainted(false);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        panelBotones.add(btnActualizar);
        btnActualizar.setBounds(18, 40, 130, 36);

        btnEliminar.setBackground(new java.awt.Color(30, 10, 16));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 91, 122));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setFocusPainted(false);
        panelBotones.add(btnEliminar);
        btnEliminar.setBounds(18, 94, 130, 36);

        getContentPane().add(panelBotones);
        panelBotones.setBounds(624, 50, 166, 230);

        panelEdicion.setBackground(new java.awt.Color(10, 12, 16));
        panelEdicion.setLayout(null);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(155, 163, 196));
        lblNombre.setText("NOMBRE");
        panelEdicion.add(lblNombre);
        lblNombre.setBounds(0, 0, 220, 16);

        txtNombre.setBackground(new java.awt.Color(24, 29, 46));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(240, 242, 255));
        txtNombre.setCaretColor(new java.awt.Color(108, 99, 255));
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelEdicion.add(txtNombre);
        txtNombre.setBounds(0, 18, 220, 36);

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(155, 163, 196));
        lblTelefono.setText("TELÉFONO");
        panelEdicion.add(lblTelefono);
        lblTelefono.setBounds(236, 0, 160, 16);

        txtTelefono.setBackground(new java.awt.Color(24, 29, 46));
        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(240, 242, 255));
        txtTelefono.setCaretColor(new java.awt.Color(108, 99, 255));
        txtTelefono.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelEdicion.add(txtTelefono);
        txtTelefono.setBounds(236, 18, 160, 36);

        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(155, 163, 196));
        lblEstado.setText("ESTADO");
        panelEdicion.add(lblEstado);
        lblEstado.setBounds(412, 0, 120, 16);

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        cmbEstado.setBackground(new java.awt.Color(24, 29, 46));
        cmbEstado.setForeground(new java.awt.Color(240, 242, 255));
        cmbEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelEdicion.add(cmbEstado);
        cmbEstado.setBounds(412, 18, 120, 36);

        getContentPane().add(panelEdicion);
        panelEdicion.setBounds(24, 300, 550, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO lógica aquí
    }//GEN-LAST:event_btnActualizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelEdicion;
    private javax.swing.JPanel panelTabla;
    private javax.swing.JScrollPane scrollTabla;
    public static javax.swing.JTable tableProveedores;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}