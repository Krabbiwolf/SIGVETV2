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
        
        btnEliminar.setOpaque(true);
btnEliminar.setContentAreaFilled(true);
btnEliminar.setBorderPainted(false);
btnEliminar.setFocusPainted(false);

btnEliminar.setBackground(new Color(220, 53, 69)); // rojo
btnEliminar.setForeground(Color.WHITE);

// color cuando está deshabilitado
btnEliminar.setDisabledIcon(null);
btnEliminar.setEnabled(true);
btnEliminar.setUI(new javax.swing.plaf.basic.BasicButtonUI());

        TableProveedores = tableProveedores; 
        
        // Estilizar cabecera de tabla al tema Azul Corporativo
        tableProveedores.getTableHeader().setBackground(Color.decode("#DCE6F2"));
        tableProveedores.getTableHeader().setForeground(Color.decode("#2D4A8A"));
        tableProveedores.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        tableProveedores.getTableHeader().setBorder(
            javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#C5D8F5"))
        );
        ((DefaultTableCellRenderer) tableProveedores.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Efectos Hover a Botones
        btnGuardar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnGuardar.setBackground(Color.decode("#3A5AAA")); }
            public void mouseExited(MouseEvent e)  { btnGuardar.setBackground(Color.decode("#2D4A8A")); }
        });

        btnActualizar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnActualizar.setBackground(Color.decode("#3A5AAA")); }
            public void mouseExited(MouseEvent e)  { btnActualizar.setBackground(Color.decode("#2D4A8A")); }
        });

        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnEliminar.setBackground(Color.decode("#C52835")); }
            public void mouseExited(MouseEvent e)  { btnEliminar.setBackground(Color.decode("#E63946")); }
        });

        btnLimpiar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnLimpiar.setBackground(Color.decode("#DCE6F2")); }
            public void mouseExited(MouseEvent e)  { btnLimpiar.setBackground(Color.WHITE); }
        });

        btnVerDetalle.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnVerDetalle.setBackground(Color.decode("#C5D8F5")); }
            public void mouseExited(MouseEvent e)  { btnVerDetalle.setBackground(Color.decode("#DCE6F2")); }
        });

        btnAgregarImagen.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnAgregarImagen.setBackground(Color.decode("#3A5AAA")); }
            public void mouseExited(MouseEvent e)  { btnAgregarImagen.setBackground(Color.decode("#2D4A8A")); }
        });

        btnExportarCSV.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnExportarCSV.setBackground(Color.decode("#218838")); }
            public void mouseExited(MouseEvent e)  { btnExportarCSV.setBackground(Color.decode("#28A745")); }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        btnAgregarImagen = new javax.swing.JButton();
        lblMostrarImagen = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVerDetalle = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tableProveedores = new javax.swing.JTable();
        btnExportarCSV = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 244, 248));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestionar Proveedores");
        setPreferredSize(new java.awt.Dimension(1100, 650));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(45, 74, 138));
        lblTitulo.setText("Gestión de Proveedores");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(20, 20, 300, 30);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(45, 74, 138));
        lblNombre.setText("NOMBRE DEL PROVEEDOR");
        getContentPane().add(lblNombre);
        lblNombre.setBounds(20, 70, 260, 16);

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtNombre);
        txtNombre.setBounds(20, 90, 260, 38);

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(45, 74, 138));
        lblTelefono.setText("TELÉFONO");
        getContentPane().add(lblTelefono);
        lblTelefono.setBounds(20, 140, 260, 16);

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(51, 51, 51));
        txtTelefono.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtTelefono);
        txtTelefono.setBounds(20, 160, 260, 38);

        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(45, 74, 138));
        lblEstado.setText("ESTADO");
        getContentPane().add(lblEstado);
        lblEstado.setBounds(20, 210, 260, 16);

        cmbEstado.setForeground(new java.awt.Color(51, 51, 51));
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cmbEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        getContentPane().add(cmbEstado);
        cmbEstado.setBounds(20, 230, 260, 38);

        btnAgregarImagen.setBackground(new java.awt.Color(45, 74, 138));
        btnAgregarImagen.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAgregarImagen.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarImagen.setText("📁 Cargar Foto");
        btnAgregarImagen.setBorderPainted(false);
        btnAgregarImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarImagen.setFocusPainted(false);
        btnAgregarImagen.addActionListener(this::btnAgregarImagenActionPerformed);
        getContentPane().add(btnAgregarImagen);
        btnAgregarImagen.setBounds(20, 280, 260, 38);

        lblMostrarImagen.setForeground(new java.awt.Color(51, 51, 51));
        lblMostrarImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMostrarImagen.setText("Sin imagen");
        lblMostrarImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        getContentPane().add(lblMostrarImagen);
        lblMostrarImagen.setBounds(20, 330, 260, 200);

        btnGuardar.setBackground(new java.awt.Color(45, 74, 138));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(300, 90, 130, 42);

        btnActualizar.setBackground(new java.awt.Color(45, 74, 138));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorderPainted(false);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        getContentPane().add(btnActualizar);
        btnActualizar.setBounds(440, 90, 130, 42);

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(45, 74, 138));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setFocusPainted(false);
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(580, 90, 130, 42);

        btnEliminar.setBackground(new java.awt.Color(230, 57, 70));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Desactivar");
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 57, 70)));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setFocusPainted(false);
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(720, 90, 130, 42);

        btnVerDetalle.setBackground(new java.awt.Color(220, 230, 242));
        btnVerDetalle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnVerDetalle.setForeground(new java.awt.Color(45, 74, 138));
        btnVerDetalle.setText("Ver Detalle");
        btnVerDetalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        btnVerDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerDetalle.setFocusPainted(false);
        getContentPane().add(btnVerDetalle);
        btnVerDetalle.setBounds(860, 90, 130, 42);

        scrollTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));

        tableProveedores.setForeground(new java.awt.Color(51, 51, 51));
        tableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Teléfono", "Estado"
            }
        ));
        tableProveedores.setGridColor(new java.awt.Color(224, 224, 224));
        tableProveedores.setRowHeight(32);
        tableProveedores.setSelectionBackground(new java.awt.Color(197, 216, 245));
        tableProveedores.setSelectionForeground(new java.awt.Color(13, 32, 96));
        scrollTabla.setViewportView(tableProveedores);

        getContentPane().add(scrollTabla);
        scrollTabla.setBounds(300, 140, 760, 440);

        btnExportarCSV.setBackground(new java.awt.Color(40, 167, 69));
        btnExportarCSV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnExportarCSV.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarCSV.setText("Exportar CSV");
        btnExportarCSV.setBorderPainted(false);
        btnExportarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportarCSV.setFocusPainted(false);
        getContentPane().add(btnExportarCSV);
        btnExportarCSV.setBounds(930, 600, 130, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
    }//GEN-LAST:event_btnAgregarImagenActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnAgregarImagen;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnExportarCSV;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnVerDetalle;
    public javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel lblEstado;
    public javax.swing.JLabel lblMostrarImagen;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollTabla;
    public static javax.swing.JTable tableProveedores;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}