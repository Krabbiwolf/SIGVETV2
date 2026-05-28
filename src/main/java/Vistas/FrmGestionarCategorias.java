package Vistas;

import Controladores.CtrlGestionarCategoria;
import Modelos.Categoria;
import Modelos.CategoriaDAO;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmGestionarCategorias extends javax.swing.JInternalFrame {

    public FrmGestionarCategorias() {
        initComponents();
        btnEliminar.setOpaque(true);
btnEliminar.setContentAreaFilled(true);
btnEliminar.setBorderPainted(false);
btnEliminar.setFocusPainted(false);

Color rojoNormal = new Color(220, 53, 69);
Color rojoDeshabilitado = new Color(180, 180, 180);

btnEliminar.setBackground(rojoNormal);
btnEliminar.setForeground(Color.WHITE);

        // Estilizar cabecera de tabla al tema Azul Corporativo
        tblCategorias.getTableHeader().setBackground(Color.decode("#DCE6F2"));
        tblCategorias.getTableHeader().setForeground(Color.decode("#2D4A8A"));
        tblCategorias.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        tblCategorias.getTableHeader().setBorder(
            javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#C5D8F5"))
        );
        ((DefaultTableCellRenderer) tblCategorias.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        txtId.setEnabled(false);
        chkEstado.setSelected(true);
        chkEstado.setOpaque(false);

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

        btnRefrescar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnRefrescar.setBackground(Color.decode("#C5D8F5")); }
            public void mouseExited(MouseEvent e)  { btnRefrescar.setBackground(Color.decode("#DCE6F2")); }
        });

        btnBuscarCategorias.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnBuscarCategorias.setBackground(Color.decode("#3A5AAA")); }
            public void mouseExited(MouseEvent e)  { btnBuscarCategorias.setBackground(Color.decode("#2D4A8A")); }
        });

        btnLimpiarFiltroCategorias.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnLimpiarFiltroCategorias.setBackground(Color.decode("#DCE6F2")); }
            public void mouseExited(MouseEvent e)  { btnLimpiarFiltroCategorias.setBackground(Color.WHITE); }
        });

        btnExportarCategorias.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnExportarCategorias.setBackground(Color.decode("#218838")); }
            public void mouseExited(MouseEvent e)  { btnExportarCategorias.setBackground(Color.decode("#28A745")); }
        });

        Categoria categoria = new Categoria();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        new CtrlGestionarCategoria(categoria, categoriaDAO, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        txtBuscarCategorias = new javax.swing.JTextField();
        btnBuscarCategorias = new javax.swing.JButton();
        cbFiltroCategorias = new javax.swing.JComboBox<>();
        btnLimpiarFiltroCategorias = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        panelEdit = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtnombrecategoria = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        chkEstado = new javax.swing.JCheckBox();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();
        btnExportarCategorias = new javax.swing.JButton();

        setBackground(new java.awt.Color(240, 244, 248));
        setClosable(true);
        setTitle("Gestionar Categorías");
        setPreferredSize(new java.awt.Dimension(850, 520));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(45, 74, 138));
        lblTitulo.setText("Gestión de Categorías");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(25, 15, 300, 30);

        txtBuscarCategorias.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtBuscarCategorias.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscarCategorias.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        txtBuscarCategorias.addActionListener(this::txtBuscarCategoriasActionPerformed);
        getContentPane().add(txtBuscarCategorias);
        txtBuscarCategorias.setBounds(25, 55, 200, 35);

        btnBuscarCategorias.setBackground(new java.awt.Color(45, 74, 138));
        btnBuscarCategorias.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarCategorias.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarCategorias.setText("Buscar");
        btnBuscarCategorias.setBorderPainted(false);
        btnBuscarCategorias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscarCategorias.setFocusPainted(false);
        getContentPane().add(btnBuscarCategorias);
        btnBuscarCategorias.setBounds(235, 55, 140, 35);

        cbFiltroCategorias.setForeground(new java.awt.Color(51, 51, 51));
        cbFiltroCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "ID", "Nombre", "Descripción", "Estado" }));
        cbFiltroCategorias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        getContentPane().add(cbFiltroCategorias);
        cbFiltroCategorias.setBounds(385, 55, 170, 35);

        btnLimpiarFiltroCategorias.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLimpiarFiltroCategorias.setForeground(new java.awt.Color(45, 74, 138));
        btnLimpiarFiltroCategorias.setText("Limpiar Filtros");
        btnLimpiarFiltroCategorias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        btnLimpiarFiltroCategorias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiarFiltroCategorias.setFocusPainted(false);
        getContentPane().add(btnLimpiarFiltroCategorias);
        btnLimpiarFiltroCategorias.setBounds(580, 55, 230, 35);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));

        tblCategorias.setForeground(new java.awt.Color(51, 51, 51));
        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripción", "Estado"
            }
        ));
        tblCategorias.setGridColor(new java.awt.Color(224, 224, 224));
        tblCategorias.setRowHeight(32);
        tblCategorias.setSelectionBackground(new java.awt.Color(197, 216, 245));
        tblCategorias.setSelectionForeground(new java.awt.Color(13, 32, 96));
        jScrollPane1.setViewportView(tblCategorias);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(25, 100, 530, 240);

        panelEdit.setBackground(new java.awt.Color(240, 244, 248));
        panelEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelEdit.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(45, 74, 138));
        jLabel2.setText("ID");
        panelEdit.add(jLabel2);
        jLabel2.setBounds(15, 15, 50, 16);

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtId.setForeground(new java.awt.Color(51, 51, 51));
        txtId.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelEdit.add(txtId);
        txtId.setBounds(15, 35, 50, 35);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(45, 74, 138));
        jLabel4.setText("NOMBRE DE CATEGORÍA");
        panelEdit.add(jLabel4);
        jLabel4.setBounds(75, 15, 150, 16);

        txtnombrecategoria.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtnombrecategoria.setForeground(new java.awt.Color(51, 51, 51));
        txtnombrecategoria.setCaretColor(new java.awt.Color(45, 74, 138));
        txtnombrecategoria.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelEdit.add(txtnombrecategoria);
        txtnombrecategoria.setBounds(75, 35, 150, 35);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(45, 74, 138));
        jLabel1.setText("DESCRIPCIÓN");
        panelEdit.add(jLabel1);
        jLabel1.setBounds(235, 15, 200, 16);

        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(51, 51, 51));
        txtDescripcion.setCaretColor(new java.awt.Color(45, 74, 138));
        txtDescripcion.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelEdit.add(txtDescripcion);
        txtDescripcion.setBounds(235, 35, 200, 35);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(45, 74, 138));
        jLabel3.setText("ESTADO");
        panelEdit.add(jLabel3);
        jLabel3.setBounds(445, 15, 70, 16);

        chkEstado.setText("Activo");
        chkEstado.setForeground(new java.awt.Color(51, 51, 51));
        panelEdit.add(chkEstado);
        chkEstado.setBounds(445, 35, 70, 35);

        getContentPane().add(panelEdit);
        panelEdit.setBounds(25, 355, 530, 100);

        btnGuardar.setBackground(new java.awt.Color(45, 74, 138));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Nueva Categoría");
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(580, 100, 230, 42);

        btnActualizar.setBackground(new java.awt.Color(45, 74, 138));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar Seleccionada");
        btnActualizar.setBorderPainted(false);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        getContentPane().add(btnActualizar);
        btnActualizar.setBounds(580, 155, 230, 42);

        btnEliminar.setBackground(new java.awt.Color(230, 57, 70));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Desactivar Categoría");
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 57, 70)));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(580, 210, 230, 42);

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(45, 74, 138));
        btnLimpiar.setText("Limpiar Edición");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);
        getContentPane().add(btnLimpiar);
        btnLimpiar.setBounds(580, 265, 230, 42);

        btnRefrescar.setBackground(new java.awt.Color(220, 230, 242));
        btnRefrescar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnRefrescar.setForeground(new java.awt.Color(45, 74, 138));
        btnRefrescar.setText("Refrescar Tabla");
        btnRefrescar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        btnRefrescar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefrescar.setFocusPainted(false);
        getContentPane().add(btnRefrescar);
        btnRefrescar.setBounds(580, 320, 230, 42);

        btnExportarCategorias.setBackground(new java.awt.Color(40, 167, 69));
        btnExportarCategorias.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnExportarCategorias.setForeground(new java.awt.Color(255, 255, 255));
        btnExportarCategorias.setText("Exportar a CSV");
        btnExportarCategorias.setBorderPainted(false);
        btnExportarCategorias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExportarCategorias.setFocusPainted(false);
        btnExportarCategorias.addActionListener(this::btnExportarCategoriasActionPerformed);
        getContentPane().add(btnExportarCategorias);
        btnExportarCategorias.setBounds(580, 413, 230, 42);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtBuscarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarCategoriasActionPerformed
    }//GEN-LAST:event_txtBuscarCategoriasActionPerformed

    private void btnExportarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarCategoriasActionPerformed
    }//GEN-LAST:event_btnExportarCategoriasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnBuscarCategorias;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnExportarCategorias;
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnLimpiarFiltroCategorias;
    public javax.swing.JButton btnRefrescar;
    public javax.swing.JComboBox<String> cbFiltroCategorias;
    public javax.swing.JCheckBox chkEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelEdit;
    public javax.swing.JTable tblCategorias;
    public javax.swing.JTextField txtBuscarCategorias;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtnombrecategoria;
    // End of variables declaration//GEN-END:variables
}