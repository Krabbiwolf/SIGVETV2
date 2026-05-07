package Vistas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

public class FrmNuevoProducto extends javax.swing.JInternalFrame {

    public FrmNuevoProducto() {
        initComponents();
        aplicarEstiloMinimalistaPremium();
    }

    private void aplicarEstiloMinimalistaPremium() {
        // === FONDO PRINCIPAL ===
        this.getContentPane().setBackground(Color.decode("#0A0C10"));

        // === TÍTULO ===
        jLabel4.setForeground(Color.decode("#F0F2FF"));
        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel4.setText("✦  Nuevo Producto");

        // === LABELS ===
        javax.swing.JLabel[] labels = {jLabel1, jLabel2, jLabel3, jLabel5, jLabel6};
        String[] textos = {"DESCRIPCIÓN", "NOMBRE", "CATEGORÍA", "IVA (%)", "IMAGEN"};
        for (int i = 0; i < labels.length; i++) {
            labels[i].setForeground(Color.decode("#9BA3C4"));
            labels[i].setFont(new Font("Segoe UI", Font.BOLD, 10));
            labels[i].setText(textos[i]);
        }

        // === INPUTS con efecto focus ===
        javax.swing.JTextField[] campos = {txtNombre, txtDescripcion, txtRutaImagen};
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
        javax.swing.JComboBox[] combos = {cboCategoria, cbIva};
        for (javax.swing.JComboBox c : combos) {
            c.setBackground(Color.decode("#181D2E"));
            c.setForeground(Color.decode("#F0F2FF"));
            c.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            c.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        }

        // === BOTÓN GUARDAR ===
        btnGuardar.setBackground(Color.decode("#6C63FF"));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setOpaque(true);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setText("Guardar Producto");
        btnGuardar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnGuardar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnGuardar.setBackground(Color.decode("#6C63FF")); }
            public void mousePressed(MouseEvent e) { btnGuardar.setBackground(Color.decode("#4A42C8")); }
            public void mouseReleased(MouseEvent e){ btnGuardar.setBackground(Color.decode("#6C63FF")); }
        });

        // === BOTÓN LIMPIAR ===
        btnLimpiar.setBackground(Color.decode("#181D2E"));
        btnLimpiar.setForeground(Color.decode("#9BA3C4"));
        btnLimpiar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

        // === BOTÓN SELECCIONAR IMAGEN ===
        jButton1.setBackground(Color.decode("#1F2640"));
        jButton1.setForeground(Color.decode("#F0F2FF"));
        jButton1.setFont(new Font("Segoe UI", Font.BOLD, 11));
        jButton1.setFocusPainted(false);
        jButton1.setBorder(BorderFactory.createLineBorder(Color.decode("#2A3050"), 1));
        jButton1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButton1.setText("📁 Seleccionar");
        jButton1.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { jButton1.setBackground(Color.decode("#2A3050")); }
            public void mouseExited(MouseEvent e)  { jButton1.setBackground(Color.decode("#1F2640")); }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        cbIva = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtRutaImagen = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setText("Descripcion");

        jLabel2.setText("Nombre ");

        jLabel3.setText("Categoria");

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnGuardar.setText("Guardar");

        btnLimpiar.setText("Limpiar");

        jLabel4.setText("NUEVO PRODUCTO");

        cbIva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "13", "0" }));

        jLabel5.setText("Iva");

        jLabel6.setText("Imagen");

        jButton1.setText("Seleccionar Imagen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtRutaImagen))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cboCategoria, 0, 177, Short.MAX_VALUE)
                                        .addComponent(btnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbIva, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 125, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(238, 238, 238)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(txtRutaImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnGuardar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JComboBox<String> cbIva;
    public javax.swing.JComboBox<String> cboCategoria;
    public javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtRutaImagen;
    // End of variables declaration//GEN-END:variables
}