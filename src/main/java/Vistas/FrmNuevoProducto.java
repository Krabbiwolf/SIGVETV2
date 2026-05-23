package Vistas;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmNuevoProducto extends javax.swing.JInternalFrame {

    public FrmNuevoProducto() {
        initComponents();
        
        // Efecto Hover para el botón Guardar
        btnGuardar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnGuardar.setBackground(Color.decode("#5850DC"));
            }
            public void mouseExited(MouseEvent evt) {
                btnGuardar.setBackground(Color.decode("#6C63FF"));
            }
        });

        // Efecto Hover para el botón Limpiar
        btnLimpiar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btnLimpiar.setBackground(Color.decode("#2A3050"));
            }
            public void mouseExited(MouseEvent evt) {
                btnLimpiar.setBackground(Color.decode("#181D2E"));
            }
        });
        
        // Aquí puedes inicializar tus controladores
        // NuevoProductoController controlador = new NuevoProductoController(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        card = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lblIva = new javax.swing.JLabel();
        cbIva = new javax.swing.JComboBox<>();
        lblCategoria = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        lblImagen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtRutaImagen = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Nuevo Producto");
        setPreferredSize(new java.awt.Dimension(580, 640));
        getContentPane().setLayout(null);

        card.setBackground(new java.awt.Color(17, 21, 32));
        card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        card.setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 242, 255));
        lblTitulo.setText("✦  Registro de Producto");
        card.add(lblTitulo);
        lblTitulo.setBounds(30, 20, 300, 30);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(155, 163, 196));
        lblNombre.setText("NOMBRE DEL PRODUCTO");
        card.add(lblNombre);
        lblNombre.setBounds(30, 70, 460, 20);

        txtNombre.setBackground(new java.awt.Color(24, 29, 46));
        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(240, 242, 255));
        txtNombre.setCaretColor(new java.awt.Color(108, 99, 255));
        txtNombre.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtNombre);
        txtNombre.setBounds(30, 95, 460, 40);

        lblDescripcion.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblDescripcion.setForeground(new java.awt.Color(155, 163, 196));
        lblDescripcion.setText("DESCRIPCIÓN TÉCNICA");
        card.add(lblDescripcion);
        lblDescripcion.setBounds(30, 150, 460, 20);

        txtDescripcion.setBackground(new java.awt.Color(24, 29, 46));
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(240, 242, 255));
        txtDescripcion.setCaretColor(new java.awt.Color(108, 99, 255));
        txtDescripcion.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtDescripcion);
        txtDescripcion.setBounds(30, 175, 460, 40);

        lblIva.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblIva.setForeground(new java.awt.Color(155, 163, 196));
        lblIva.setText("IVA (%)");
        card.add(lblIva);
        lblIva.setBounds(30, 230, 220, 20);

        cbIva.setBackground(new java.awt.Color(24, 29, 46));
        cbIva.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbIva.setForeground(new java.awt.Color(240, 242, 255));
        cbIva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        card.add(cbIva);
        cbIva.setBounds(30, 255, 220, 40);

        lblCategoria.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblCategoria.setForeground(new java.awt.Color(155, 163, 196));
        lblCategoria.setText("CATEGORÍA");
        card.add(lblCategoria);
        lblCategoria.setBounds(270, 230, 220, 20);

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione..." }));
        cboCategoria.setBackground(new java.awt.Color(24, 29, 46));
        cboCategoria.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cboCategoria.setForeground(new java.awt.Color(240, 242, 255));
        cboCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        card.add(cboCategoria);
        cboCategoria.setBounds(270, 255, 220, 40);

        lblImagen.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblImagen.setForeground(new java.awt.Color(155, 163, 196));
        lblImagen.setText("IMAGEN REFERENCIAL (OPCIONAL)");
        card.add(lblImagen);
        lblImagen.setBounds(30, 310, 460, 20);

        jButton1.setBackground(new java.awt.Color(31, 38, 64));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("📁 Seleccionar");
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        card.add(jButton1);
        jButton1.setBounds(30, 335, 140, 40);

        txtRutaImagen.setBackground(new java.awt.Color(24, 29, 46));
        txtRutaImagen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtRutaImagen.setForeground(new java.awt.Color(240, 242, 255));
        txtRutaImagen.setCaretColor(new java.awt.Color(108, 99, 255));
        txtRutaImagen.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12)));
        card.add(txtRutaImagen);
        txtRutaImagen.setBounds(180, 335, 310, 40);

        btnGuardar.setBackground(new java.awt.Color(108, 99, 255));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Producto");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        card.add(btnGuardar);
        btnGuardar.setBounds(30, 405, 220, 45);

        btnLimpiar.setBackground(new java.awt.Color(24, 29, 46));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(240, 242, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setFocusPainted(false);
        card.add(btnLimpiar);
        btnLimpiar.setBounds(270, 405, 220, 45);

        getContentPane().add(card);
        card.setBounds(20, 20, 520, 560);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnLimpiar;
    private javax.swing.JPanel card;
    public javax.swing.JComboBox<String> cbIva;
    public javax.swing.JComboBox<String> cboCategoria;
    public javax.swing.JButton jButton1;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtRutaImagen;
    // End of variables declaration//GEN-END:variables
}