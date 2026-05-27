package Vistas;

import Controladores.CtrlMaestroDetalle.CtrlMaestroDetalle;
import Modelos.MaestroDetalleDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

public class FrmMaestroDetalle extends javax.swing.JInternalFrame implements MaestroDetalleVista {

    public static final String CLIENTES_FACTURAS = "CLIENTES_FACTURAS";
    public static final String PROVEEDORES_COMPRAS = "PROVEEDORES_COMPRAS";
    public static final String CATEGORIAS_PRODUCTOS = "CATEGORIAS_PRODUCTOS";
    public static final String PRODUCTOS_LOTES = "PRODUCTOS_LOTES";

    private final String tipo;

    public JLabel lblTitulo;
    public JLabel lblMaestro;
    public JLabel lblDetalle;
    public JLabel lblInfo;
    public JTextField txtBuscar;
    public JButton btnBuscar;
    public JButton btnActualizar;
    public JButton btnLimpiar;
    public JTable tblMaestro;
    public JTable tblDetalle;

    public FrmMaestroDetalle(String tipo) {
        this.tipo = tipo;
        construirVista();
        new CtrlMaestroDetalle(this, new MaestroDetalleDAO());
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public void configurarTextos(String titulo, String maestro, String detalle, String placeholder) {
        setTitle(titulo);
        lblTitulo.setText("✦  " + titulo);
        lblMaestro.setText(maestro);
        lblDetalle.setText(detalle);
        txtBuscar.setToolTipText(placeholder);
    }

    private void construirVista() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setPreferredSize(new Dimension(1050, 650));
        setSize(1050, 650);

        JPanel root = new JPanel(new BorderLayout(14, 14));
        root.setBackground(Color.decode("#0A0C10"));
        root.setBorder(BorderFactory.createEmptyBorder(18, 22, 18, 22));
        setContentPane(root);

        lblTitulo = new JLabel("✦  Maestro-Detalle");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.decode("#F0F2FF"));
        root.add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.setOpaque(false);
        root.add(centro, BorderLayout.CENTER);

        JPanel filtros = new JPanel(new BorderLayout(10, 0));
        filtros.setOpaque(false);
        txtBuscar = new JTextField();
        txtBuscar.setBackground(Color.decode("#181D2E"));
        txtBuscar.setForeground(Color.decode("#F0F2FF"));
        txtBuscar.setCaretColor(Color.decode("#6C63FF"));
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#2A3050")),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        filtros.add(txtBuscar, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        botones.setOpaque(false);
        btnBuscar = crearBoton("Buscar", "#6C63FF", "#FFFFFF");
        btnActualizar = crearBoton("Actualizar", "#1F2640", "#F0F2FF");
        btnLimpiar = crearBoton("Limpiar", "#1E0A10", "#FF5B7A");
        botones.add(btnBuscar);
        botones.add(btnActualizar);
        botones.add(btnLimpiar);
        filtros.add(botones, BorderLayout.EAST);
        centro.add(filtros, BorderLayout.NORTH);

        JPanel panelMaestro = crearPanelTabla();
        lblMaestro = crearTituloTabla("Maestro");
        tblMaestro = crearTabla();
        panelMaestro.add(lblMaestro, BorderLayout.NORTH);
        panelMaestro.add(crearScrollTabla(tblMaestro), BorderLayout.CENTER);

        JPanel panelDetalle = crearPanelTabla();
        lblDetalle = crearTituloTabla("Detalle");
        tblDetalle = crearTabla();
        panelDetalle.add(lblDetalle, BorderLayout.NORTH);
        panelDetalle.add(crearScrollTabla(tblDetalle), BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panelMaestro, panelDetalle);
        split.setResizeWeight(0.46);
        split.setDividerSize(8);
        split.setBorder(null);
        split.setBackground(Color.decode("#0A0C10"));
        centro.add(split, BorderLayout.CENTER);

        JPanel pie = new JPanel(new GridLayout(1, 1));
        pie.setOpaque(false);
        lblInfo = new JLabel("Selecciona un registro maestro para ver su detalle.");
        lblInfo.setForeground(Color.decode("#9BA3C4"));
        lblInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        pie.add(lblInfo);
        root.add(pie, BorderLayout.SOUTH);
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(Color.decode("#111520"));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#2A3050")),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));
        return panel;
    }

    private JLabel crearTituloTabla(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(Color.decode("#F0F2FF"));
        return label;
    }

    private JTable crearTabla() {
        JTable tabla = new JTable();
        tabla.setBackground(Color.decode("#0E1219"));
        tabla.setForeground(Color.decode("#F0F2FF"));
        tabla.setGridColor(Color.decode("#1A1F30"));
        tabla.setRowHeight(30);
        tabla.setSelectionBackground(Color.decode("#6C63FF"));
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setFillsViewportHeight(true);
        tabla.setOpaque(true);
        tabla.setShowGrid(true);

        tabla.getTableHeader().setBackground(Color.decode("#181D2E"));
        tabla.getTableHeader().setForeground(Color.decode("#9BA3C4"));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tabla.getTableHeader().setReorderingAllowed(false);
        ((DefaultTableCellRenderer) tabla.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        return tabla;
    }

    private JScrollPane crearScrollTabla(JTable tabla) {
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setBackground(Color.decode("#0E1219"));
        scroll.getViewport().setBackground(Color.decode("#0E1219"));
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getHorizontalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    private JButton crearBoton(String texto, String fondo, String frente) {
        JButton boton = new JButton(texto);
        boton.setBackground(Color.decode(fondo));
        boton.setForeground(Color.decode(frente));
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(9, 16, 9, 16));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
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
    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    @Override
    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    @Override
    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    @Override
    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    @Override
    public JTable getTblMaestro() {
        return tblMaestro;
    }

    @Override
    public JTable getTblDetalle() {
        return tblDetalle;
    }

}
