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
        TableProveedores = tableProveedores; 
        
        

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

        setBackground(new java.awt.Color(244, 246, 249));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestionar Proveedores");
        setPreferredSize(new java.awt.Dimension(1100, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(32, 56, 100));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitulo.setText("✦  Gestionar Proveedores");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 260, 28));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(51, 51, 51));
        lblNombre.setText("NOMBRE");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 240, 16));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtNombre.setCaretColor(new java.awt.Color(43, 68, 122));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 240, 30));

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefono.setText("TELÉFONO");
        getContentPane().add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 240, 16));

        txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(51, 51, 51));
        txtTelefono.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtTelefono.setCaretColor(new java.awt.Color(43, 68, 122));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 240, 30));

        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(51, 51, 51));
        lblEstado.setText("ESTADO");
        getContentPane().add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 240, 16));

        cmbEstado.setBackground(new java.awt.Color(255, 255, 255));
        cmbEstado.setForeground(new java.awt.Color(51, 51, 51));
        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        cmbEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        getContentPane().add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 240, 30));

        btnAgregarImagen.setBackground(new java.awt.Color(248, 249, 250));
        btnAgregarImagen.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAgregarImagen.setForeground(new java.awt.Color(43, 68, 122));
        btnAgregarImagen.setText("📁 Cargar Foto");
        btnAgregarImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(196, 205, 213)));
        btnAgregarImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarImagen.setFocusPainted(false);
        btnAgregarImagen.addActionListener(this::btnAgregarImagenActionPerformed);
        getContentPane().add(btnAgregarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 145, 30));

        lblMostrarImagen.setForeground(new java.awt.Color(51, 51, 51));
        lblMostrarImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMostrarImagen.setText("Sin imagen");
        lblMostrarImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        getContentPane().add(lblMostrarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 240, 200));

        btnGuardar.setBackground(new java.awt.Color(43, 68, 122));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Proveedor");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar.setFocusPainted(false);
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 770, 40));

        btnActualizar.setBackground(new java.awt.Color(248, 249, 250));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(43, 68, 122));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(196, 205, 213)));
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 380, 35));

        btnLimpiar.setBackground(new java.awt.Color(248, 249, 250));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(43, 68, 122));
        btnLimpiar.setText("Limpiar Campos");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(196, 205, 213)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setFocusPainted(false);
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 380, 35));

        btnEliminar.setBackground(new java.awt.Color(255, 245, 245));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(220, 53, 69));
        btnEliminar.setText("Eliminar Seleccionados");
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 53, 69)));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.setFocusPainted(false);
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 115, 770, 35));

        btnVerDetalle.setBackground(new java.awt.Color(248, 249, 250));
        btnVerDetalle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnVerDetalle.setForeground(new java.awt.Color(43, 68, 122));
        btnVerDetalle.setText("Ver Detalle");
        btnVerDetalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(196, 205, 213)));
        btnVerDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVerDetalle.setFocusPainted(false);
        getContentPane().add(btnVerDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 770, 35));

        scrollTabla.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tableProveedores.setBackground(new java.awt.Color(255, 255, 255));
        tableProveedores.setForeground(new java.awt.Color(51, 51, 51));
        tableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sel", "Id", "Nombre", "Teléfono", "Estado", "RutaImagen"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProveedores.setGridColor(new java.awt.Color(209, 217, 230));
        tableProveedores.setRowHeight(35);
        tableProveedores.setSelectionBackground(new java.awt.Color(217, 226, 243));
        tableProveedores.setSelectionForeground(new java.awt.Color(0, 0, 0));
        scrollTabla.setViewportView(tableProveedores);

        getContentPane().add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 770, 380));

        btnExportarCSV.setBackground(new java.awt.Color(43, 68, 122));
        btnExportarCSV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnExportarCSV.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarCSV.setText("Exportar CSV");
        btnExportarCSV.setBorderPainted(false);
        btnExportarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExportarCSV.setFocusPainted(false);
        getContentPane().add(btnExportarCSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 600, 130, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO lógica aquí
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