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
        
        // Estilizar cabecera de tabla
        tblProductos.getTableHeader().setBackground(Color.decode("#181D2E"));
        tblProductos.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tblProductos.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        tblProductos.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050")));
        ((DefaultTableCellRenderer) tblProductos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        // Efectos Hover a Botones Principales
        btnActualizar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnActualizar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnActualizar.setBackground(Color.decode("#6C63FF")); }
        });

        btnLimpiar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnLimpiar.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e)  { btnLimpiar.setBackground(Color.decode("#181D2E")); }
        });

        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnEliminar.setBackground(Color.decode("#FF5B7A")); btnEliminar.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e) { btnEliminar.setBackground(Color.decode("#1E0A10")); btnEliminar.setForeground(Color.decode("#FF5B7A")); }
        });
        
        btnBuscar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnBuscar.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e) { btnBuscar.setBackground(Color.decode("#1F2640")); }
        });

        btnAgregarImagen.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnAgregarImagen.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e) { btnAgregarImagen.setBackground(Color.decode("#1F2640")); }
        });
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
        cbIva = new javax.swing.JComboBox<>();
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        lblImagen = new javax.swing.JLabel();
        btnAgregarImagen = new javax.swing.JButton();
        txtRuta = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblPreview = new javax.swing.JLabel();
        lblMostrarImagen = new javax.swing.JLabel();
        scrollTabla = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Gestionar Productos");
        setPreferredSize(new java.awt.Dimension(1150, 640));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 242, 255));
        lblTitulo.setText("✦  Gestión y Stock de Productos");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(25, 20, 400, 30);

        lblIdProducto.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblIdProducto.setForeground(new java.awt.Color(155, 163, 196));
        lblIdProducto.setText("ID");
        getContentPane().add(lblIdProducto);
        lblIdProducto.setBounds(25, 70, 90, 16);

        txtIdProducto.setEditable(false);
        txtIdProducto.setBackground(new java.awt.Color(14, 18, 25));
        txtIdProducto.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtIdProducto.setForeground(new java.awt.Color(240, 242, 255));
        txtIdProducto.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtIdProducto);
        txtIdProducto.setBounds(25, 90, 90, 38);

        lblCodBarras.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCodBarras.setForeground(new java.awt.Color(155, 163, 196));
        lblCodBarras.setText("CÓDIGO DE BARRAS");
        getContentPane().add(lblCodBarras);
        lblCodBarras.setBounds(130, 70, 205, 16);

        txtCodigoBarras.setBackground(new java.awt.Color(24, 29, 46));
        txtCodigoBarras.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCodigoBarras.setForeground(new java.awt.Color(240, 242, 255));
        txtCodigoBarras.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtCodigoBarras);
        txtCodigoBarras.setBounds(130, 90, 205, 38);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(155, 163, 196));
        lblNombre.setText("NOMBRE DEL PRODUCTO");
        getContentPane().add(lblNombre);
        lblNombre.setBounds(25, 143, 310, 16);

        txtNombre.setBackground(new java.awt.Color(24, 29, 46));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(240, 242, 255));
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtNombre);
        txtNombre.setBounds(25, 163, 310, 38);

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(155, 163, 196));
        lblDescripcion.setText("DESCRIPCIÓN TÉCNICA");
        getContentPane().add(lblDescripcion);
        lblDescripcion.setBounds(25, 216, 310, 16);

        txtDescripcionTecnica.setBackground(new java.awt.Color(24, 29, 46));
        txtDescripcionTecnica.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDescripcionTecnica.setForeground(new java.awt.Color(240, 242, 255));
        txtDescripcionTecnica.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtDescripcionTecnica);
        txtDescripcionTecnica.setBounds(25, 236, 310, 38);

        lblIva.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblIva.setForeground(new java.awt.Color(155, 163, 196));
        lblIva.setText("IVA (%)");
        getContentPane().add(lblIva);
        lblIva.setBounds(25, 289, 145, 16);

        cbIva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "13", "0" }));
        cbIva.setBackground(new java.awt.Color(24, 29, 46));
        cbIva.setForeground(new java.awt.Color(240, 242, 255));
        cbIva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        getContentPane().add(cbIva);
        cbIva.setBounds(25, 309, 145, 38);

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCategoria.setForeground(new java.awt.Color(155, 163, 196));
        lblCategoria.setText("CATEGORÍA");
        getContentPane().add(lblCategoria);
        lblCategoria.setBounds(190, 289, 145, 16);

        cboCategoria.setBackground(new java.awt.Color(24, 29, 46));
        cboCategoria.setForeground(new java.awt.Color(240, 242, 255));
        cboCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        getContentPane().add(cboCategoria);
        cboCategoria.setBounds(190, 309, 145, 38);

        lblEstado.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(155, 163, 196));
        lblEstado.setText("ESTADO");
        getContentPane().add(lblEstado);
        lblEstado.setBounds(25, 362, 145, 16);

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cboEstado.setBackground(new java.awt.Color(24, 29, 46));
        cboEstado.setForeground(new java.awt.Color(240, 242, 255));
        cboEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        getContentPane().add(cboEstado);
        cboEstado.setBounds(25, 382, 145, 38);

        lblImagen.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblImagen.setForeground(new java.awt.Color(155, 163, 196));
        lblImagen.setText("IMAGEN");
        getContentPane().add(lblImagen);
        lblImagen.setBounds(190, 362, 145, 16);

        btnAgregarImagen.setBackground(new java.awt.Color(31, 38, 64));
        btnAgregarImagen.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnAgregarImagen.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarImagen.setText("📁 Cargar Foto");
        btnAgregarImagen.setBorderPainted(false);
        btnAgregarImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarImagen.setFocusPainted(false);
        btnAgregarImagen.addActionListener(this::btnAgregarImagenActionPerformed);
        getContentPane().add(btnAgregarImagen);
        btnAgregarImagen.setBounds(190, 382, 145, 38);

        txtRuta.setBackground(new java.awt.Color(24, 29, 46));
        getContentPane().add(txtRuta);
        txtRuta.setBounds(0, 0, 0, 0);

        btnActualizar.setBackground(new java.awt.Color(108, 99, 255));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Guardar Cambios");
        btnActualizar.setBorderPainted(false);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setFocusPainted(false);
        getContentPane().add(btnActualizar);
        btnActualizar.setBounds(360, 90, 250, 42);

        btnLimpiar.setBackground(new java.awt.Color(24, 29, 46));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(240, 242, 255));
        btnLimpiar.setText("Limpiar Campos");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setFocusPainted(false);
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(360, 142, 250, 42);

        btnEliminar.setBackground(new java.awt.Color(30, 10, 16));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 91, 122));
        btnEliminar.setText("Desactivar Producto");
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setFocusPainted(false);
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(360, 194, 250, 42);

        lblBuscar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(155, 163, 196));
        lblBuscar.setText("BÚSQUEDA RÁPIDA");
        getContentPane().add(lblBuscar);
        lblBuscar.setBounds(360, 256, 250, 16);

        txtBuscar.setBackground(new java.awt.Color(24, 29, 46));
        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(240, 242, 255));
        txtBuscar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        getContentPane().add(txtBuscar);
        txtBuscar.setBounds(360, 276, 160, 38);

        btnBuscar.setBackground(new java.awt.Color(31, 38, 64));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setFocusPainted(false);
        getContentPane().add(btnBuscar);
        btnBuscar.setBounds(530, 276, 80, 38);

        lblPreview.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblPreview.setForeground(new java.awt.Color(155, 163, 196));
        lblPreview.setText("VISTA PREVIA DEL PRODUCTO");
        getContentPane().add(lblPreview);
        lblPreview.setBounds(360, 330, 250, 16);

        lblMostrarImagen.setForeground(new java.awt.Color(155, 163, 196));
        lblMostrarImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMostrarImagen.setText("Sin imagen");
        lblMostrarImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        getContentPane().add(lblMostrarImagen);
        lblMostrarImagen.setBounds(360, 350, 250, 200);

        scrollTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));

        tblProductos.setBackground(new java.awt.Color(14, 18, 25));
        tblProductos.setForeground(new java.awt.Color(240, 242, 255));
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Código", "Nombre", "Desc.", "IVA", "Cat", "Estado"
            }
        ));
        tblProductos.setGridColor(new java.awt.Color(26, 31, 48));
        tblProductos.setRowHeight(32);
        tblProductos.setSelectionBackground(new java.awt.Color(108, 99, 255));
        tblProductos.setSelectionForeground(new java.awt.Color(255, 255, 255));
        scrollTabla.setViewportView(tblProductos);

        getContentPane().add(scrollTabla);
        scrollTabla.setBounds(635, 90, 480, 485);

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnAgregarImagen;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JComboBox<String> cbIva;
    public javax.swing.JComboBox<String> cboCategoria;
    public javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblCodBarras;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblEstado;
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