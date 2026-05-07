package Vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmGestionarProveedores extends javax.swing.JInternalFrame {

    public FrmGestionarProveedores() {
        initComponents();
        this.setSize(new Dimension(870, 460));
        this.setTitle("Gestionar Proveedores");
        aplicarEstiloMinimalistaPremium();
    }

    private void aplicarEstiloMinimalistaPremium() {
        // === FONDO GENERAL ===
        this.getContentPane().setBackground(Color.decode("#0A0C10"));

        // === TÍTULO ===
        jLabel1.setForeground(Color.decode("#F0F2FF"));
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 16));
        jLabel1.setText("✦  Gestionar Proveedores");

        // === LABELS CAMPO ===
        javax.swing.JLabel[] labelsCampo = {jLabel2, jLabel3, jLabel4};
        for (javax.swing.JLabel lbl : labelsCampo) {
            lbl.setForeground(Color.decode("#9BA3C4"));
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 10));
        }
        jLabel2.setText("NOMBRE");
        jLabel3.setText("TELÉFONO");
        jLabel4.setText("ESTADO");

        // === PANELES ===
        jPanel1.setBackground(Color.decode("#111520"));
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));

        jPanel2.setBackground(Color.decode("#111520"));
        jPanel2.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));

        // === TABLA ===
        TableProveedores.setBackground(Color.decode("#111520"));
        TableProveedores.setForeground(Color.decode("#F0F2FF"));
        TableProveedores.setGridColor(Color.decode("#1F2640"));
        TableProveedores.setRowHeight(30);
        TableProveedores.setSelectionBackground(Color.decode("#6C63FF"));
        TableProveedores.setSelectionForeground(Color.WHITE);
        TableProveedores.setShowGrid(true);
        TableProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        TableProveedores.setIntercellSpacing(new java.awt.Dimension(0, 0));
        TableProveedores.setFillsViewportHeight(true);

        // Centrar contenido columnas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        centerRenderer.setBackground(Color.decode("#111520"));
        centerRenderer.setForeground(Color.decode("#F0F2FF"));
        for (int i = 0; i < TableProveedores.getColumnModel().getColumnCount(); i++) {
            TableProveedores.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        if (TableProveedores.getColumnModel().getColumnCount() > 0) {
            TableProveedores.getColumnModel().getColumn(0).setMaxWidth(55);
            TableProveedores.getColumnModel().getColumn(1).setPreferredWidth(210);
            TableProveedores.getColumnModel().getColumn(2).setPreferredWidth(110);
        }

        // Header tabla
        TableProveedores.getTableHeader().setBackground(Color.decode("#181D2E"));
        TableProveedores.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        TableProveedores.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        TableProveedores.getTableHeader().setReorderingAllowed(false);
        TableProveedores.getTableHeader().setBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#2A3050"))
        );
        ((DefaultTableCellRenderer) TableProveedores.getTableHeader().getDefaultRenderer())
            .setHorizontalAlignment(javax.swing.JLabel.CENTER);

        // ScrollPane
        jScrollPane1.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        jScrollPane1.setBackground(Color.decode("#111520"));
        jScrollPane1.getViewport().setBackground(Color.decode("#111520"));

        // === INPUTS ===
        javax.swing.JTextField[] campos = {txtNombre, txtTelefono};
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

        // === COMBOBOX ===
        cmbEstado.setBackground(Color.decode("#181D2E"));
        cmbEstado.setForeground(Color.decode("#F0F2FF"));
        cmbEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cmbEstado.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));

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
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableProveedores = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        cmbEstado = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Gestionar Proveedores");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Telefono", "Estado"
            }
        ));
        jScrollPane1.setViewportView(TableProveedores);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 580, 200));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 600, 220));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 120, 34));

        btnEliminar.setText("Eliminar");
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 120, 34));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 180, 220));

        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        jLabel3.setText("Telefono:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, -1, -1));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 308, 220, 28));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 348, 160, 28));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));
        getContentPane().add(cmbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 308, 110, 28));

        jLabel4.setText("Estado:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, 50, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TableProveedores;
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}