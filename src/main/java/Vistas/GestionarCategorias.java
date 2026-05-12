package Vistas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class GestionarCategorias extends javax.swing.JInternalFrame {

    private static final Color BG_MAIN   = Color.decode("#0A0C10");
    private static final Color BG_CARD   = Color.decode("#111520");
    private static final Color BG_TABLE  = Color.decode("#0E1219");
    private static final Color BG_INPUT  = Color.decode("#181D2E");
    private static final Color BORDER    = Color.decode("#2A3050");
    private static final Color ACCENT    = Color.decode("#6C63FF");
    private static final Color DANGER    = Color.decode("#FF5B7A");
    private static final Color TEXT_PRI  = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT  = Color.decode("#9BA3C4");

    public GestionarCategorias() {
        initComponents();
        this.setSize(new Dimension(850, 520));
        this.setPreferredSize(new Dimension(850, 520));
        this.setTitle("Gestionar Categorías");
        aplicarEstiloPremium();
    }

    private void aplicarEstiloPremium() {
        getContentPane().setBackground(BG_MAIN);
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("✦  Gestión de Categorías");
        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBounds(25, 15, 300, 30);
        getContentPane().add(lblTitulo);

        // Tabla
        tblCategorias.setBackground(BG_TABLE);
        tblCategorias.setForeground(TEXT_PRI);
        tblCategorias.setRowHeight(30);
        tblCategorias.setSelectionBackground(ACCENT);
        tblCategorias.setSelectionForeground(Color.WHITE);
        tblCategorias.getTableHeader().setBackground(Color.decode("#181D2E"));
        tblCategorias.getTableHeader().setForeground(TEXT_MUT);
        tblCategorias.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tblCategorias.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));
        
        jScrollPane1.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        jScrollPane1.setBounds(25, 60, 580, 260);
        getContentPane().add(jScrollPane1);

        // Botones Laterales
        btnActualizar.setText("Actualizar"); // <-- TEXTO AÑADIDO
        estilizarBtn(btnActualizar, ACCENT, Color.WHITE);
        btnActualizar.setBounds(630, 60, 180, 42);
        btnActualizar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnActualizar.setBackground(Color.decode("#5850DC")); }
            public void mouseExited(MouseEvent e)  { btnActualizar.setBackground(ACCENT); }
        });
        getContentPane().add(btnActualizar);

        btnEliminar.setText("Eliminar"); // <-- TEXTO AÑADIDO
        estilizarBtn(btnEliminar, Color.decode("#1E0A10"), DANGER);
        btnEliminar.setBorder(BorderFactory.createLineBorder(Color.decode("#3A1520"), 1));
        btnEliminar.setBounds(630, 115, 180, 42);
        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnEliminar.setBackground(DANGER); btnEliminar.setForeground(Color.WHITE); }
            public void mouseExited(MouseEvent e)  { btnEliminar.setBackground(Color.decode("#1E0A10")); btnEliminar.setForeground(DANGER); }
        });
        getContentPane().add(btnEliminar);

        // Panel Inferior de Edición
        JPanel panelEdit = new JPanel(null);
        panelEdit.setBackground(BG_CARD);
        panelEdit.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        panelEdit.setBounds(25, 340, 785, 110);
        getContentPane().add(panelEdit);

        jLabel2.setText("ID");
        jLabel2.setForeground(TEXT_MUT);
        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel2.setBounds(20, 15, 60, 16);
        panelEdit.add(jLabel2);
        txtId.setBounds(20, 35, 60, 38);
        estilizarCampo(txtId);
        txtId.setBackground(Color.decode("#0E1219"));
        panelEdit.add(txtId);

        jLabel4.setText("NOMBRE");
        jLabel4.setForeground(TEXT_MUT);
        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel4.setBounds(100, 15, 200, 16);
        panelEdit.add(jLabel4);
        txtnombrecategoria.setBounds(100, 35, 200, 38);
        estilizarCampo(txtnombrecategoria);
        panelEdit.add(txtnombrecategoria);

        jLabel1.setText("DESCRIPCIÓN");
        jLabel1.setForeground(TEXT_MUT);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel1.setBounds(320, 15, 280, 16);
        panelEdit.add(jLabel1);
        txtDescripcion.setBounds(320, 35, 280, 38);
        estilizarCampo(txtDescripcion);
        panelEdit.add(txtDescripcion);

        jLabel3.setText("ESTADO");
        jLabel3.setForeground(TEXT_MUT);
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel3.setBounds(620, 15, 140, 16);
        panelEdit.add(jLabel3);
        cbEstado.setBounds(620, 35, 140, 38);
        cbEstado.setBackground(BG_INPUT);
        cbEstado.setForeground(TEXT_PRI);
        cbEstado.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        panelEdit.add(cbEstado);
    }

    private void estilizarCampo(JTextField f) {
        f.setBackground(BG_INPUT);
        f.setForeground(TEXT_PRI);
        f.setCaretColor(ACCENT);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER, 1), new EmptyBorder(6, 10, 6, 10)));
    }

    private void estilizarBtn(JButton b, Color bg, Color fg) {
        b.setBackground(bg);
        b.setForeground(fg);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        txtDescripcion = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        cbEstado = new javax.swing.JComboBox<>();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtnombrecategoria = new javax.swing.JTextField();

        setClosable(true);
        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "ID", "Nombre", "Descripción", "Estado" }
        ));
        jScrollPane1.setViewportView(tblCategorias);
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        
        pack();
    }

    public javax.swing.JButton btnActualizar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblCategorias;
    public javax.swing.JTextField txtDescripcion, txtId, txtnombrecategoria;
}