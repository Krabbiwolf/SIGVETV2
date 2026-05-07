package Vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmGestionarProductos extends javax.swing.JInternalFrame {

    public FrmGestionarProductos() {
        initComponents();
        aplicarEstiloMinimalistaPremium();
    }

    private void aplicarEstiloMinimalistaPremium() {
        // === FONDO PRINCIPAL ===
        this.getContentPane().setBackground(Color.decode("#0A0C10"));

        // === TÍTULO ===
        jLabel10.setForeground(Color.decode("#F0F2FF"));
        jLabel10.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel10.setText("✦  Gestionar Productos");

        // === LABELS ===
        javax.swing.JLabel[] labels = {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel11};
        String[] textos = {"ID PRODUCTO", "CÓD. BARRAS", "NOMBRE", "DESCRIPCIÓN", "IVA (%)", "IMAGEN", "CATEGORÍA", "ESTADO", "BUSCAR", "VISTA PREVIA"};
        for (int i = 0; i < labels.length; i++) {
            labels[i].setForeground(Color.decode("#9BA3C4"));
            labels[i].setFont(new Font("Segoe UI", Font.BOLD, 10));
            labels[i].setText(textos[i]);
        }

        // === INPUTS con efecto focus ===
        javax.swing.JTextField[] campos = {txtIdProducto, txtCodigoBarras, txtNombre, txtDescripcionTecnica, txtBuscar, txtRuta};
        for (javax.swing.JTextField c : campos) {
            c.setBackground(Color.decode("#181D2E"));
            c.setForeground(Color.decode("#F0F2FF"));
            c.setCaretColor(Color.decode("#6C63FF"));
            c.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            c.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#2A3050"), 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
            ));
            c.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent e) {
                    ((javax.swing.JTextField) e.getSource()).setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#6C63FF"), 1),
                        BorderFactory.createEmptyBorder(6, 10, 6, 10)
                    ));
                }
                public void focusLost(java.awt.event.FocusEvent e) {
                    ((javax.swing.JTextField) e.getSource()).setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#2A3050"), 1),
                        BorderFactory.createEmptyBorder(6, 10, 6, 10)
                    ));
                }
            });
        }

        // === COMBOBOXES ===
        javax.swing.JComboBox[] combos = {cboCategoria, cboEstado, cbIva};
        for (javax.swing.JComboBox c : combos) {
            c.setBackground(Color.decode("#181D2E"));
            c.setForeground(Color.decode("#F0F2FF"));
            c.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            c.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        }

        // === BOTÓN ACTUALIZAR ===
        btnActualizar.setBackground(Color.decode("#6C63FF"));
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setBorderPainted(false);
        btnActualizar.setOpaque(true);
        btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnActualizar.setText("Actualizar");
        btnActualizar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnActualizar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnActualizar.setBackground(Color.decode("#6C63FF")); }
        });

        // === BOTÓN BUSCAR ===
        btnBuscar.setBackground(Color.decode("#1F2640"));
        btnBuscar.setForeground(Color.decode("#F0F2FF"));
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.setText("🔍 Buscar");
        btnBuscar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnBuscar.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e)  { btnBuscar.setBackground(Color.decode("#1F2640")); }
        });

        // === BOTÓN LIMPIAR ===
        btnLimpiar.setBackground(Color.decode("#181D2E"));
        btnLimpiar.setForeground(Color.decode("#9BA3C4"));
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnLimpiar.setBackground(Color.decode("#1F2640"));
                btnLimpiar.setForeground(Color.decode("#F0F2FF"));
            }
            public void mouseExited(MouseEvent e) {
                btnLimpiar.setBackground(Color.decode("#181D2E"));
                btnLimpiar.setForeground(Color.decode("#9BA3C4"));
            }
        });

        // === BOTÓN ELIMINAR ===
        btnEliminar.setBackground(Color.decode("#1E0F14"));
        btnEliminar.setForeground(Color.decode("#FF5B7A"));
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorder(BorderFactory.createLineBorder(Color.decode("#3A1520"), 1));
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.setText("Eliminar");
        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnEliminar.setBackground(Color.decode("#FF5B7A"));
                btnEliminar.setForeground(Color.WHITE);
            }
            public void mouseExited(MouseEvent e) {
                btnEliminar.setBackground(Color.decode("#1E0F14"));
                btnEliminar.setForeground(Color.decode("#FF5B7A"));
            }
        });

        // === BOTÓN AGREGAR IMAGEN ===
        btnAgregarImagen.setBackground(Color.decode("#1F2640"));
        btnAgregarImagen.setForeground(Color.decode("#F0F2FF"));
        btnAgregarImagen.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnAgregarImagen.setFocusPainted(false);
        btnAgregarImagen.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        btnAgregarImagen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregarImagen.setText("📁 Agregar Imagen");
        btnAgregarImagen.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnAgregarImagen.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e)  { btnAgregarImagen.setBackground(Color.decode("#1F2640")); }
        });

        // === IMAGEN PREVIEW LABEL ===
        lblMostrarImagen.setBorder(BorderFactory.createDashedBorder(Color.decode("#2A3050"), 3, 4));
        lblMostrarImagen.setBackground(Color.decode("#111520"));
        lblMostrarImagen.setOpaque(true);
        lblMostrarImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMostrarImagen.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        lblMostrarImagen.setForeground(Color.decode("#2A3050"));
        lblMostrarImagen.setText("Sin imagen");
        lblMostrarImagen.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        // === TABLA PRODUCTOS ===
        tblProductos.setBackground(Color.decode("#111520"));
        tblProductos.setForeground(Color.decode("#F0F2FF"));
        tblProductos.setGridColor(Color.decode("#1F2640"));
        tblProductos.setRowHeight(30);
        tblProductos.setSelectionBackground(Color.decode("#6C63FF"));
        tblProductos.setSelectionForeground(Color.WHITE);
        tblProductos.setShowGrid(true);
        tblProductos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblProductos.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblProductos.setFillsViewportHeight(true);

        tblProductos.getTableHeader().setBackground(Color.decode("#181D2E"));
        tblProductos.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tblProductos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.getTableHeader().setBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050"))
        );

        jScrollPane1.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        jScrollPane1.setBackground(Color.decode("#111520"));
        jScrollPane1.getViewport().setBackground(Color.decode("#111520"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCodigoBarras = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDescripcionTecnica = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboEstado = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        cbIva = new javax.swing.JComboBox<>();
        btnAgregarImagen = new javax.swing.JButton();
        txtRuta = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lblMostrarImagen = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Id Producto");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 105, 93, 37));

        txtIdProducto.setEditable(Boolean.FALSE);
        getContentPane().add(txtIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(139, 112, 87, -1));

        jLabel2.setText("Codigo de barras ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 148, 126, 32));
        getContentPane().add(txtCodigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 153, 138, -1));

        jLabel3.setText("Nombre producto");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 198, 103, 26));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 200, 161, -1));

        jLabel4.setText("Desccripcion ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 242, 103, 30));
        getContentPane().add(txtDescripcionTecnica, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 246, 340, -1));

        jLabel5.setText("Iva");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 297, 103, 24));

        jLabel6.setText("Imagen");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 348, 103, 28));

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 400, 151, -1));

        jLabel7.setText("Categoria");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 111, 36));

        jLabel8.setText("Estado");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 111, 24));

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        getContentPane().add(cboEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 440, 151, -1));

        jLabel9.setText("Buscar Producto");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 146, 33));

        btnBuscar.setText("Buscar");
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, 123, 36));
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 332, -1));

        btnActualizar.setText("Actualizar");
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, 123, 36));

        btnEliminar.setText("Eliminar");
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 190, 123, 36));

        btnLimpiar.setText("Limpiar");
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 130, 123, 36));

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProductos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 720, 211));

        jLabel10.setText("GESTIONAR PRODUCTOS ");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 6, 348, 47));

        cbIva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "13", "0" }));
        getContentPane().add(cbIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 298, 197, -1));

        btnAgregarImagen.setText("Agregar Imagen");
        btnAgregarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarImagenActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 180, -1));
        getContentPane().add(txtRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, 360, -1));

        jLabel11.setText("Imagen Seleccionada");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 290, 130, 30));

        lblMostrarImagen.setText(".");
        getContentPane().add(lblMostrarImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 340, 280, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarImagenActionPerformed
        // TODO add your handling code here:
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblMostrarImagen;
    public javax.swing.JTable tblProductos;
    public javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtCodigoBarras;
    public javax.swing.JTextField txtDescripcionTecnica;
    public javax.swing.JTextField txtIdProducto;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}