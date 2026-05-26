package Vistas;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmGestionarProductos extends javax.swing.JInternalFrame {

    public FrmGestionarProductos() {
        initComponents();
        txtRuta.setVisible(false);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblIdProducto = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        lblCodBarras = new javax.swing.JLabel();
        txtCodigoBarras = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcionTecnica = new javax.swing.JTextField();
        lblIva = new javax.swing.JLabel();
        Iva13 = new javax.swing.JLabel();
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        lblEstado2 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        lblImagen = new javax.swing.JLabel();
        btnAgregarImagen = new javax.swing.JButton();
        lblPreview = new javax.swing.JLabel();
        lblMostrarImagen = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        cboFiltroBusqueda = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVerDetalle = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnExportarCSV = new javax.swing.JButton();
        txtRuta = new javax.swing.JTextField();

        setBackground(new java.awt.Color(244, 246, 249));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestionar Productos");
        setPreferredSize(new java.awt.Dimension(1170, 710));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(43, 68, 122));
        lblTitulo.setText("■ Gestión y Stock de Productos");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, 30));

        lblIdProducto.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblIdProducto.setForeground(new java.awt.Color(51, 51, 51));
        lblIdProducto.setText("ID");
        getContentPane().add(lblIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 90, 16));

        txtIdProducto.setEditable(false);
        txtIdProducto.setBackground(new java.awt.Color(245, 245, 245));
        txtIdProducto.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        txtIdProducto.setForeground(new java.awt.Color(43, 68, 122));
        txtIdProducto.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 90, 30));

        lblCodBarras.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCodBarras.setForeground(new java.awt.Color(51, 51, 51));
        lblCodBarras.setText("CÓDIGO DE BARRAS");
        getContentPane().add(lblCodBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 200, 16));

        txtCodigoBarras.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigoBarras.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCodigoBarras.setForeground(new java.awt.Color(51, 51, 51));
        txtCodigoBarras.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtCodigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 200, 30));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(51, 51, 51));
        lblNombre.setText("NOMBRE DEL PRODUCTO");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 310, 16));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 310, 30));

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        lblDescripcion.setText("DESCRIPCIÓN TÉCNICA");
        getContentPane().add(lblDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 310, 16));

        txtDescripcionTecnica.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcionTecnica.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDescripcionTecnica.setForeground(new java.awt.Color(51, 51, 51));
        txtDescripcionTecnica.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtDescripcionTecnica, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 310, 30));

        lblIva.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblIva.setForeground(new java.awt.Color(51, 51, 51));
        lblIva.setText("IVA (%)");
        getContentPane().add(lblIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 145, 16));

        Iva13.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        Iva13.setForeground(new java.awt.Color(51, 51, 51));
        Iva13.setText("  13 %");
        Iva13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        getContentPane().add(Iva13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 145, 30));

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCategoria.setForeground(new java.awt.Color(51, 51, 51));
        lblCategoria.setText("CATEGORÍA");
        getContentPane().add(lblCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 240, 145, 16));

        cboCategoria.setBackground(new java.awt.Color(255, 255, 255));
        cboCategoria.setForeground(new java.awt.Color(51, 51, 51));
        cboCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        getContentPane().add(cboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 260, 145, 30));

        lblEstado2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblEstado2.setForeground(new java.awt.Color(51, 51, 51));
        lblEstado2.setText("ESTADO");
        getContentPane().add(lblEstado2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 145, 16));

        cboEstado.setBackground(new java.awt.Color(255, 255, 255));
        cboEstado.setForeground(new java.awt.Color(51, 51, 51));
        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cboEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        getContentPane().add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 145, 30));

        lblImagen.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblImagen.setForeground(new java.awt.Color(51, 51, 51));
        lblImagen.setText("IMAGEN");
        getContentPane().add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 300, 145, 16));

        btnAgregarImagen.setBackground(new java.awt.Color(248, 249, 250));
        btnAgregarImagen.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAgregarImagen.setForeground(new java.awt.Color(43, 68, 122));
        btnAgregarImagen.setText("📁 Cargar Foto");
        btnAgregarImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        btnAgregarImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAgregarImagen.setFocusPainted(false);
        btnAgregarImagen.addActionListener(this::btnAgregarImagenActionPerformed);
        getContentPane().add(btnAgregarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 320, 145, 30));

        lblPreview.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblPreview.setForeground(new java.awt.Color(51, 51, 51));
        lblPreview.setText("VISTA PREVIA DEL PRODUCTO");
        getContentPane().add(lblPreview, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 310, 16));

        lblMostrarImagen.setForeground(new java.awt.Color(51, 51, 51));
        lblMostrarImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMostrarImagen.setText("Sin imagen");
        lblMostrarImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        getContentPane().add(lblMostrarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 310, 120));

        lblBuscar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(51, 51, 51));
        lblBuscar.setText("BÚSQUEDA RÁPIDA");
        getContentPane().add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, 200, 16));

        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 200, 30));

        btnBuscar.setBackground(new java.awt.Color(43, 68, 122));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscar.setFocusPainted(false);
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, 100, 30));

        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(51, 51, 51));
        lblEstado.setText("FILTRAR BÚSQUEDA");
        getContentPane().add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 570, 310, 16));

        cboFiltroBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        cboFiltroBusqueda.setForeground(new java.awt.Color(51, 51, 51));
        cboFiltroBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Activos" }));
        cboFiltroBusqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        getContentPane().add(cboFiltroBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 310, 30));

        btnGuardar.setBackground(new java.awt.Color(43, 68, 122));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Producto");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar.setFocusPainted(false);
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 760, 35));

        btnActualizar.setBackground(new java.awt.Color(248, 249, 250));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(43, 68, 122));
        btnActualizar.setText("Actualizar Producto");
        btnActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 65, 375, 35));

        btnLimpiar.setBackground(new java.awt.Color(248, 249, 250));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(43, 68, 122));
        btnLimpiar.setText("Limpiar Campos");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setFocusPainted(false);
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 65, 375, 35));

        btnEliminar.setBackground(new java.awt.Color(255, 245, 245));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(220, 53, 69));
        btnEliminar.setText("Desactivar Seleccionados");
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 53, 69)));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminar.setFocusPainted(false);
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 760, 35));

        btnVerDetalle.setBackground(new java.awt.Color(248, 249, 250));
        btnVerDetalle.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnVerDetalle.setForeground(new java.awt.Color(43, 68, 122));
        btnVerDetalle.setText("Ver Detalle");
        btnVerDetalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 217, 230)));
        btnVerDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVerDetalle.setFocusPainted(false);
        getContentPane().add(btnVerDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 155, 760, 35));

        scrollTabla.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblProductos.setBackground(new java.awt.Color(255, 255, 255));
        tblProductos.setForeground(new java.awt.Color(51, 51, 51));
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código", "Nombre", "Desc.", "IVA", "Cat", "Estado"
            }
        ));
        tblProductos.setGridColor(new java.awt.Color(209, 217, 230));
        tblProductos.setRowHeight(35);
        tblProductos.setSelectionBackground(new java.awt.Color(217, 226, 243));
        tblProductos.setSelectionForeground(new java.awt.Color(0, 0, 0));
        scrollTabla.setViewportView(tblProductos);

        getContentPane().add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 200, 760, 420));

        btnExportarCSV.setBackground(new java.awt.Color(43, 68, 122));
        btnExportarCSV.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnExportarCSV.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarCSV.setText("Exportar CSV");
        btnExportarCSV.setBorderPainted(false);
        btnExportarCSV.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExportarCSV.setFocusPainted(false);
        getContentPane().add(btnExportarCSV, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 630, 130, 30));

        txtRuta.setBackground(new java.awt.Color(24, 29, 46));
        getContentPane().add(txtRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
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
    }//GEN-LAST:event_btnAgregarImagenActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Iva13;
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnAgregarImagen;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnExportarCSV;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnVerDetalle;
    public javax.swing.JComboBox<String> cboCategoria;
    public javax.swing.JComboBox<String> cboEstado;
    public javax.swing.JComboBox<String> cboFiltroBusqueda;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCodBarras;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblEstado2;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblIva;
    public javax.swing.JLabel lblMostrarImagen;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPreview;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JScrollPane scrollTabla;
    public javax.swing.JTable tblProductos;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtCodigoBarras;
    public javax.swing.JTextField txtDescripcionTecnica;
    public javax.swing.JTextField txtIdProducto;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}