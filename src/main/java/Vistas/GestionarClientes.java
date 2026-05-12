package Vistas;

import Modelos.Cliente;
import Controladores.CtrlGestionarClientes;
import Modelos.ClienteDAO;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class GestionarClientes extends javax.swing.JInternalFrame {

    private CtrlGestionarClientes controladorGestionar;

    private static final Color BG_MAIN   = Color.decode("#0A0C10");
    private static final Color BG_TABLE  = Color.decode("#0E1219");
    private static final Color BG_INPUT  = Color.decode("#181D2E");
    private static final Color BORDER    = Color.decode("#2A3050");
    private static final Color ACCENT    = Color.decode("#6C63FF");
    private static final Color DANGER    = Color.decode("#FF5B7A");
    private static final Color TEXT_PRI  = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT  = Color.decode("#9BA3C4");

    private JPanel mainContentPanel;

    public GestionarClientes() {
        initComponents();
        this.setSize(new Dimension(1050, 680));
        this.setPreferredSize(new Dimension(1050, 680));
        this.setTitle("Gestionar Clientes");
        
        // Ejecutamos la estructura scrolleable segura
        hacerFormularioScrolleable();
        aplicarEstiloPremium();
        
        txtIdCliente.setVisible(false);
        
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        controladorGestionar = new CtrlGestionarClientes(cliente, clienteDAO, this);
    }

    // ── MAGIA SEGURA SIN DEPENDER DE LIBRERÍAS DE NETBEANS ───────────────────
    private void hacerFormularioScrolleable() {
        // Usamos un layout nulo nativo de Java, ya que aplicaremos bounds manualmente
        mainContentPanel = new JPanel(null); 
        mainContentPanel.setBackground(BG_MAIN);
        mainContentPanel.setPreferredSize(new Dimension(1000, 750));

        // Movemos los componentes del content pane original al nuevo panel
        for (Component c : getContentPane().getComponents()) {
            mainContentPanel.add(c);
        }

        JScrollPane scroll = new JScrollPane(mainContentPanel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scroll, BorderLayout.CENTER);
    }

    private void aplicarEstiloPremium() {
        JLabel lblTitulo = new JLabel("✦  Directorio y Gestión de Clientes");
        lblTitulo.setForeground(TEXT_PRI);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBounds(30, 20, 400, 30);
        mainContentPanel.add(lblTitulo);

        // Tabla
        tblClientes.setBackground(BG_TABLE);
        tblClientes.setForeground(TEXT_PRI);
        tblClientes.setRowHeight(30);
        tblClientes.setSelectionBackground(ACCENT);
        tblClientes.setSelectionForeground(Color.WHITE);
        tblClientes.getTableHeader().setBackground(Color.decode("#181D2E"));
        tblClientes.getTableHeader().setForeground(TEXT_MUT);
        tblClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        tblClientes.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER));
        
        jScrollPane1.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        jScrollPane1.setBounds(30, 70, 750, 380);

        // Botones Laterales
        estilizarBtn(btnActualizar, ACCENT, Color.WHITE);
        btnActualizar.setBounds(810, 70, 180, 45);

        estilizarBtn(btnEliminar, Color.decode("#1E0A10"), DANGER);
        btnEliminar.setBorder(BorderFactory.createLineBorder(Color.decode("#3A1520"), 1));
        btnEliminar.setBounds(810, 130, 180, 45);

        estilizarBtn(btnRefrescar, BG_INPUT, TEXT_PRI);
        btnRefrescar.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        btnRefrescar.setBounds(810, 190, 180, 45);

        // Panel de edición abajo
        JPanel panelEdit = new JPanel(null);
        panelEdit.setBackground(Color.decode("#111520"));
        panelEdit.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        panelEdit.setBounds(30, 480, 960, 180);
        mainContentPanel.add(panelEdit);

        // Fila 1 Edición
        agregarCampoEdicion(panelEdit, jLabel2, txtNombre, "NOMBRES", 20, 20, 220);
        agregarCampoEdicion(panelEdit, jLabel3, txtApellido, "APELLIDOS", 260, 20, 220);
        agregarCampoEdicion(panelEdit, jLabel4, txtDui, "DUI", 500, 20, 200);

        // Fila 2 Edición
        agregarCampoEdicion(panelEdit, jLabel5, txtTelefono, "TELÉFONO", 20, 95, 220);
        agregarCampoEdicion(panelEdit, jLabel6, txtDireccion, "DIRECCIÓN", 260, 95, 460);
        
        // Combo Box Estado
        jLabel1.setText("ESTADO");
        jLabel1.setForeground(TEXT_MUT);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel1.setBounds(740, 95, 200, 16);
        panelEdit.add(jLabel1);
        
        cbEstado.setBounds(740, 115, 200, 38);
        cbEstado.setBackground(BG_INPUT);
        cbEstado.setForeground(TEXT_PRI);
        cbEstado.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        panelEdit.add(cbEstado);
    }

    private void agregarCampoEdicion(JPanel parent, JLabel lbl, JTextField txt, String titulo, int x, int y, int w) {
        lbl.setText(titulo);
        lbl.setForeground(TEXT_MUT);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lbl.setBounds(x, y, w, 16);
        parent.add(lbl);

        txt.setBounds(x, y + 20, w, 38);
        txt.setBackground(BG_INPUT);
        txt.setForeground(TEXT_PRI);
        txt.setCaretColor(ACCENT);
        txt.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER, 1), new EmptyBorder(6, 10, 6, 10)));
        parent.add(txt);
    }

    private void estilizarBtn(JButton b, Color bg, Color fg) {
        b.setBackground(bg);
        b.setForeground(fg);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        txtIdCliente = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDui = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnRefrescar = new javax.swing.JButton();
        cbEstado = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setIconifiable(true);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "ID", "Nombre", "Apellido", "DUI", "Teléfono", "Dirección", "Estado" }
        ));
        jScrollPane1.setViewportView(tblClientes);

        btnActualizar.setText("Actualizar");
        btnEliminar.setText("Eliminar");
        btnRefrescar.setText("Refrescar");

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        // Asignamos todo de forma inicial (sin coordinadas de AbsoluteLayout conflictivas)
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(btnRefrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        
        getContentPane().add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(txtDui, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        getContentPane().add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0,0,0,0));
        
        pack();
    }

    // Variables
    public javax.swing.JButton btnActualizar, btnEliminar, btnRefrescar;
    public javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tblClientes;
    public javax.swing.JTextField txtApellido, txtDireccion, txtDui, txtIdCliente, txtNombre, txtTelefono;
}