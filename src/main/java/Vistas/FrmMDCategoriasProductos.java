package Vistas;

import Controladores.CtrlMaestroDetalle.CtrlMaestroDetalle;
import Modelos.MaestroDetalleDAO;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmMDCategoriasProductos extends javax.swing.JInternalFrame implements MaestroDetalleVista {

    public FrmMDCategoriasProductos() {
        initComponents();
        configurarComponentes();
        
                            btnActualizar.setBackground(new Color(45, 74, 138));
    btnActualizar.setForeground(Color.WHITE);

    btnActualizar.setOpaque(true);
    btnActualizar.setContentAreaFilled(true);

    btnActualizar.setFocusPainted(false);
    btnActualizar.setBorderPainted(false);
        
        // Agregar eventos Hover para botones (garantizando legibilidad con texto blanco y azul)
        btnBuscar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnBuscar.setBackground(Color.decode("#3A5AAA")); }
            public void mouseExited(MouseEvent e)  { btnBuscar.setBackground(Color.decode("#2D4A8A")); }
        });
        
        btnActualizar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnActualizar.setBackground(Color.decode("#3A5AAA")); }
            public void mouseExited(MouseEvent e)  { btnActualizar.setBackground(Color.decode("#2D4A8A")); }
        });
        
        btnLimpiar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnLimpiar.setBackground(Color.decode("#DCE6F2")); }
            public void mouseExited(MouseEvent e)  { btnLimpiar.setBackground(Color.WHITE); }
        });

        new CtrlMaestroDetalle(this, new MaestroDetalleDAO());
    }

    @Override
    public String getTipo() {
        return MaestroDetalleVista.CATEGORIAS_PRODUCTOS;
    }

    @Override
    public void configurarTextos(String titulo, String maestro, String detalle, String placeholder) {
        setTitle(titulo);
        
        lblMaestro.setText(maestro);
        lblDetalle.setText(detalle);
        txtBuscar.setToolTipText(placeholder);
    }

    private void configurarComponentes() {
        // Fondo general Light Corporativo
        getContentPane().setBackground(Color.decode("#F0F4F8"));
        setSize(1100, 680);

        configurarTabla(tblMaestro);
        configurarTabla(tblDetalle);

        // Fondos de los ScrollPanes a Blanco
        scrollMaestro.getViewport().setBackground(Color.WHITE);
        scrollDetalle.getViewport().setBackground(Color.WHITE);
        scrollMaestro.getVerticalScrollBar().setUnitIncrement(16);
        scrollDetalle.getVerticalScrollBar().setUnitIncrement(16);
        scrollMaestro.getHorizontalScrollBar().setUnitIncrement(16);
        scrollDetalle.getHorizontalScrollBar().setUnitIncrement(16);
    }

    private void configurarTabla(javax.swing.JTable tabla) {
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setFillsViewportHeight(true);
        tabla.setOpaque(true);
        tabla.setShowGrid(true);
        // Colores de cabecera Azul Corporativo
        tabla.getTableHeader().setBackground(Color.decode("#DCE6F2"));
        tabla.getTableHeader().setForeground(Color.decode("#2D4A8A"));
        tabla.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        tabla.getTableHeader().setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#C5D8F5")));
        tabla.getTableHeader().setReorderingAllowed(false);
        ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public JLabel getLblTitulo() {
        return lblTitulo;
    }

    @Override
    public JLabel getLblMaestro() {
        return lblMaestro;
    }

    @Override
    public JLabel getLblDetalle() {
        return lblDetalle;
    }

    @Override
    public JLabel getLblInfo() {
        return lblInfo;
    }

    @Override
    public javax.swing.JTextField getTxtBuscar() {
        return txtBuscar;
    }

    @Override
    public javax.swing.JButton getBtnBuscar() {
        return btnBuscar;
    }

    @Override
    public javax.swing.JButton getBtnActualizar() {
        return btnActualizar;
    }

    @Override
    public javax.swing.JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    @Override
    public javax.swing.JTable getTblMaestro() {
        return tblMaestro;
    }

    @Override
    public javax.swing.JTable getTblDetalle() {
        return tblDetalle;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        panelFiltros = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        panelMaestro = new javax.swing.JPanel();
        lblMaestro = new javax.swing.JLabel();
        scrollMaestro = new javax.swing.JScrollPane();
        tblMaestro = new javax.swing.JTable();
        panelDetalle = new javax.swing.JPanel();
        lblDetalle = new javax.swing.JLabel();
        scrollDetalle = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        lblInfo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(240, 244, 248));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Categorías - Productos");
        setPreferredSize(new java.awt.Dimension(1100, 680));
        getContentPane().setLayout(null);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(45, 74, 138));
        lblTitulo.setText("Categorías - Productos");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(30, 20, 520, 32);

        panelFiltros.setBackground(new java.awt.Color(240, 244, 248));
        panelFiltros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelFiltros.setLayout(null);

        lblBuscar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblBuscar.setForeground(new java.awt.Color(45, 74, 138));
        lblBuscar.setText("BUSCAR");
        panelFiltros.add(lblBuscar);
        lblBuscar.setBounds(20, 14, 210, 16);

        txtBuscar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(51, 51, 51));
        txtBuscar.setToolTipText("Buscar por categoría o descripción");
        txtBuscar.setCaretColor(new java.awt.Color(45, 74, 138));
        txtBuscar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)), javax.swing.BorderFactory.createEmptyBorder(6, 10, 6, 10)));
        panelFiltros.add(txtBuscar);
        txtBuscar.setBounds(20, 34, 520, 38);

        btnBuscar.setBackground(new java.awt.Color(45, 74, 138));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscar.setFocusPainted(false);
        panelFiltros.add(btnBuscar);
        btnBuscar.setBounds(560, 34, 130, 38);

        btnActualizar.setBackground(new java.awt.Color(45, 74, 138));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setFocusPainted(false);
        panelFiltros.add(btnActualizar);
        btnActualizar.setBounds(700, 34, 130, 38);

        btnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(45, 74, 138));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setFocusPainted(false);
        panelFiltros.add(btnLimpiar);
        btnLimpiar.setBounds(840, 34, 130, 38);

        getContentPane().add(panelFiltros);
        panelFiltros.setBounds(30, 65, 1020, 92);

        panelMaestro.setBackground(new java.awt.Color(240, 244, 248));
        panelMaestro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelMaestro.setLayout(null);

        lblMaestro.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblMaestro.setForeground(new java.awt.Color(45, 74, 138));
        lblMaestro.setText("Categorías");
        panelMaestro.add(lblMaestro);
        lblMaestro.setBounds(18, 10, 400, 22);

        tblMaestro.setForeground(new java.awt.Color(51, 51, 51));
        tblMaestro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblMaestro.setGridColor(new java.awt.Color(224, 224, 224));
        tblMaestro.setRowHeight(30);
        tblMaestro.setSelectionBackground(new java.awt.Color(197, 216, 245));
        tblMaestro.setSelectionForeground(new java.awt.Color(13, 32, 96));
        scrollMaestro.setViewportView(tblMaestro);

        panelMaestro.add(scrollMaestro);
        scrollMaestro.setBounds(18, 40, 984, 165);

        getContentPane().add(panelMaestro);
        panelMaestro.setBounds(30, 170, 1020, 220);

        panelDetalle.setBackground(new java.awt.Color(240, 244, 248));
        panelDetalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(197, 216, 245)));
        panelDetalle.setLayout(null);

        lblDetalle.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblDetalle.setForeground(new java.awt.Color(45, 74, 138));
        lblDetalle.setText("Productos de la categoría seleccionada");
        panelDetalle.add(lblDetalle);
        lblDetalle.setBounds(18, 10, 620, 22);

        tblDetalle.setForeground(new java.awt.Color(51, 51, 51));
        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDetalle.setGridColor(new java.awt.Color(224, 224, 224));
        tblDetalle.setRowHeight(30);
        tblDetalle.setSelectionBackground(new java.awt.Color(197, 216, 245));
        tblDetalle.setSelectionForeground(new java.awt.Color(13, 32, 96));
        scrollDetalle.setViewportView(tblDetalle);

        panelDetalle.add(scrollDetalle);
        scrollDetalle.setBounds(18, 40, 984, 165);

        getContentPane().add(panelDetalle);
        panelDetalle.setBounds(30, 405, 1020, 220);

        lblInfo.setForeground(new java.awt.Color(51, 51, 51));
        lblInfo.setText("Selecciona un registro maestro para ver su detalle.");
        getContentPane().add(lblInfo);
        lblInfo.setBounds(30, 632, 1020, 22);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel lblBuscar;
    public javax.swing.JLabel lblDetalle;
    public javax.swing.JLabel lblInfo;
    public javax.swing.JLabel lblMaestro;
    public javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelDetalle;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JPanel panelMaestro;
    private javax.swing.JScrollPane scrollDetalle;
    private javax.swing.JScrollPane scrollMaestro;
    public javax.swing.JTable tblDetalle;
    public javax.swing.JTable tblMaestro;
    public javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}