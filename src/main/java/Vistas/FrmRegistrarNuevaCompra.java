package Vistas;



import Modelos.Producto;

import Modelos.ProductosDAO;

import Modelos.Proveedor;

import Modelos.ProveedorDAO;

import java.awt.Color;

import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.JComponent;

import javax.swing.JSpinner;

import javax.swing.SwingWorker;



public class FrmRegistrarNuevaCompra extends javax.swing.JInternalFrame {

    private ProveedorDAO proveedorDAO = new ProveedorDAO();

    private ProductosDAO productosDAO = new ProductosDAO();

    private SwingWorker<ArrayList<Proveedor>, Void> proveedorWorker;

    private SwingWorker<ArrayList<Producto>, Void> productoWorker;

    

    public FrmRegistrarNuevaCompra() {

        initComponents();

        

        estilizarSpinner(SpinCantidadProductos);

        estilizarSpinner(SpinPrecio);

        

        btnGuardarCompra.addMouseListener(new MouseAdapter() {

            public void mouseEntered(MouseEvent e) { btnGuardarCompra.setBackground(Color.decode("#5850DC")); }

            public void mouseExited(MouseEvent e)  { btnGuardarCompra.setBackground(Color.decode("#6C63FF")); }

        });



        // Carga asíncrona de combos

        cargarProveedores();

        cargarProductos();

    }

    

    private void estilizarSpinner(JSpinner spinner) {

        JComponent editor = spinner.getEditor();

        if (editor instanceof JSpinner.DefaultEditor) {

            JSpinner.DefaultEditor defaultEditor = (JSpinner.DefaultEditor) editor;

            defaultEditor.getTextField().setBackground(Color.decode("#181D2E"));

            defaultEditor.getTextField().setForeground(Color.decode("#F0F2FF"));

            defaultEditor.getTextField().setCaretColor(Color.decode("#6C63FF"));

        }

    }

    

    // ================== CARGA ASÍNCRONA ==================

    public void cargarProveedores(){

        if (proveedorWorker != null && !proveedorWorker.isDone()) {

            proveedorWorker.cancel(true);

        }

        proveedorWorker = new SwingWorker<ArrayList<Proveedor>, Void>() {

            @Override

            protected ArrayList<Proveedor> doInBackground() throws Exception {

                return proveedorDAO.listar();

            }



            @Override

            protected void done() {

                try {

                    ArrayList<Proveedor> proveedores = get();

                    comboProveedor.removeAllItems();

                    for (Proveedor proveedor : proveedores) {

                        comboProveedor.addItem(proveedor);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();

                } finally {

                    proveedorWorker = null;

                }

            }

        };

        proveedorWorker.execute();

    }

    

    public void cargarProductos(){

        if (productoWorker != null && !productoWorker.isDone()) {

            productoWorker.cancel(true);

        }

        productoWorker = new SwingWorker<ArrayList<Producto>, Void>() {

            @Override

            protected ArrayList<Producto> doInBackground() throws Exception {

                return productosDAO.listarProductos();

            }



            @Override

            protected void done() {

                try {

                    ArrayList<Producto> productos = get();

                    comboProducto.removeAllItems();

                    for (Producto producto : productos) {

                        comboProducto.addItem(producto);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();

                } finally {

                    productoWorker = null;

                }

            }

        };

        productoWorker.execute();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCard = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboProveedor = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comboProducto = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        SpinCantidadProductos = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        SpinPrecio = new javax.swing.JSpinner();
        btnGuardarCompra = new javax.swing.JButton();

        setBackground(new java.awt.Color(10, 12, 16));
        setClosable(true);
        setMaximizable(true);
        setTitle("Registrar Nueva Compra");
        setPreferredSize(new java.awt.Dimension(550, 480));
        getContentPane().setLayout(null);

        panelCard.setBackground(new java.awt.Color(17, 21, 32));
        panelCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelCard.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 242, 255));
        jLabel1.setText("✦  Registrar Nueva Compra");
        panelCard.add(jLabel1);
        jLabel1.setBounds(30, 20, 300, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(155, 163, 196));
        jLabel4.setText("PROVEEDOR");
        panelCard.add(jLabel4);
        jLabel4.setBounds(30, 70, 420, 16);

        comboProveedor.setBackground(new java.awt.Color(24, 29, 46));
        comboProveedor.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        comboProveedor.setForeground(new java.awt.Color(240, 242, 255));
        comboProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelCard.add(comboProveedor);
        comboProveedor.setBounds(30, 90, 420, 38);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(155, 163, 196));
        jLabel2.setText("PRODUCTO");
        panelCard.add(jLabel2);
        jLabel2.setBounds(30, 145, 420, 16);

        comboProducto.setBackground(new java.awt.Color(24, 29, 46));
        comboProducto.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        comboProducto.setForeground(new java.awt.Color(240, 242, 255));
        comboProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelCard.add(comboProducto);
        comboProducto.setBounds(30, 165, 420, 38);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(155, 163, 196));
        jLabel3.setText("CANTIDAD");
        panelCard.add(jLabel3);
        jLabel3.setBounds(30, 220, 200, 16);

        SpinCantidadProductos.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        SpinCantidadProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelCard.add(SpinCantidadProductos);
        SpinCantidadProductos.setBounds(30, 240, 200, 38);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(155, 163, 196));
        jLabel6.setText("PRECIO");
        panelCard.add(jLabel6);
        jLabel6.setBounds(250, 220, 200, 16);

        SpinPrecio.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));
        SpinPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 48, 80)));
        panelCard.add(SpinPrecio);
        SpinPrecio.setBounds(250, 240, 200, 38);

        btnGuardarCompra.setBackground(new java.awt.Color(108, 99, 255));
        btnGuardarCompra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarCompra.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCompra.setText("Guardar");
        btnGuardarCompra.setBorderPainted(false);
        btnGuardarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCompra.setFocusPainted(false);
        panelCard.add(btnGuardarCompra);
        btnGuardarCompra.setBounds(30, 320, 420, 45);

        getContentPane().add(panelCard);
        panelCard.setBounds(30, 20, 480, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JSpinner SpinCantidadProductos;
    public javax.swing.JSpinner SpinPrecio;
    public javax.swing.JButton btnGuardarCompra;
    public javax.swing.JComboBox<Modelos.Producto> comboProducto;
    public javax.swing.JComboBox<Modelos.Proveedor> comboProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel panelCard;
    // End of variables declaration//GEN-END:variables
}