package Vistas;

import java.awt.Color;
import Controladores.CtrlGestionarCategoria; // O el nombre correcto de tu controlador

public class GestionarCategorias extends javax.swing.JInternalFrame {

    public GestionarCategorias() {
        initComponents();
        // Decoración extra para la tabla que el .form no soporta visualmente en NetBeans
        tblCategorias.getTableHeader().setBackground(Color.decode("#181D2E"));
        tblCategorias.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tblCategorias.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
        tblCategorias.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        panelEdit = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtnombrecategoria = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setTitle("Gestionar Categorías");
        setPreferredSize(new java.awt.Dimension(850, 520));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(240, 242, 255));
        lblTitulo.setText("✦ Gestión de Categorías");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(25, 15, 300, 30);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        jScrollPane1.setForeground(new java.awt.Color(245, 245, 245));

        tblCategorias.setBackground(new java.awt.Color(14, 18, 25));
        tblCategorias.setForeground(new java.awt.Color(240, 242, 255));
        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripción", "Estado"
            }
        ));
        tblCategorias.setRowHeight(30);
        tblCategorias.setSelectionBackground(new java.awt.Color(108, 99, 255));
        tblCategorias.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblCategorias);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(25, 60, 580, 260);

        btnActualizar.setBackground(new java.awt.Color(108, 99, 255));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorderPainted(false);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);
        getContentPane().add(btnActualizar);
        btnActualizar.setBounds(630, 60, 180, 42);

        btnEliminar.setBackground(new java.awt.Color(30, 10, 16));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 91, 122));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(58, 21, 32)));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);
        getContentPane().add(btnEliminar);
        btnEliminar.setBounds(630, 115, 180, 42);

        panelEdit.setBackground(new java.awt.Color(17, 21, 32));
        panelEdit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelEdit.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 163, 196));
        jLabel2.setText("ID");
        panelEdit.add(jLabel2);
        jLabel2.setBounds(20, 15, 60, 16);

        txtId.setBackground(new java.awt.Color(14, 18, 25));
        txtId.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtId.setForeground(new java.awt.Color(240, 242, 255));
        txtId.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelEdit.add(txtId);
        txtId.setBounds(20, 35, 60, 38);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(155, 163, 196));
        jLabel4.setText("NOMBRE");
        panelEdit.add(jLabel4);
        jLabel4.setBounds(100, 15, 200, 16);

        txtnombrecategoria.setBackground(new java.awt.Color(24, 29, 46));
        txtnombrecategoria.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtnombrecategoria.setForeground(new java.awt.Color(240, 242, 255));
        txtnombrecategoria.setCaretColor(new java.awt.Color(108, 99, 255));
        txtnombrecategoria.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelEdit.add(txtnombrecategoria);
        txtnombrecategoria.setBounds(100, 35, 200, 38);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(155, 163, 196));
        jLabel1.setText("DESCRIPCIÓN");
        panelEdit.add(jLabel1);
        jLabel1.setBounds(320, 15, 280, 16);

        txtDescripcion.setBackground(new java.awt.Color(24, 29, 46));
        txtDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(240, 242, 255));
        txtDescripcion.setCaretColor(new java.awt.Color(108, 99, 255));
        txtDescripcion.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelEdit.add(txtDescripcion);
        txtDescripcion.setBounds(320, 35, 280, 38);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(155, 163, 196));
        jLabel3.setText("ESTADO");
        panelEdit.add(jLabel3);
        jLabel3.setBounds(620, 15, 140, 16);

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        cbEstado.setBackground(new java.awt.Color(24, 29, 46));
        cbEstado.setForeground(new java.awt.Color(240, 242, 255));
        cbEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelEdit.add(cbEstado);
        cbEstado.setBounds(620, 35, 140, 38);

        getContentPane().add(panelEdit);
        panelEdit.setBounds(25, 340, 785, 110);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO lógica del controlador
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO lógica del controlador
    }//GEN-LAST:event_btnEliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelEdit;
    public javax.swing.JTable tblCategorias;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtnombrecategoria;
    // End of variables declaration//GEN-END:variables
}