package Vistas;

import Controladores.CtrlNuevoCliente;
import Modelos.Cliente;
import Modelos.ClienteDAO;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NuevoCliente extends javax.swing.JInternalFrame {

    private static final Color BG_MAIN   = Color.decode("#0A0C10");
    private static final Color BG_CARD   = Color.decode("#111520");
    private static final Color BG_INPUT  = Color.decode("#181D2E");
    private static final Color BORDER    = Color.decode("#2A3050");
    private static final Color ACCENT    = Color.decode("#6C63FF");
    private static final Color TEXT_PRI  = Color.decode("#F0F2FF");
    private static final Color TEXT_MUT  = Color.decode("#9BA3C4");

    public NuevoCliente() {
        initComponents();
        this.setSize(new Dimension(620, 480));
        this.setPreferredSize(new Dimension(620, 480));
        this.setTitle("Nuevo Cliente");
        aplicarEstiloPremium();
        
        Cliente cliente = new Cliente();
        ClienteDAO clienteDAO = new ClienteDAO();
        CtrlNuevoCliente controlador = new CtrlNuevoCliente(cliente, clienteDAO, this);
    }

    private void aplicarEstiloPremium() {
        getContentPane().setBackground(BG_MAIN);
        getContentPane().setLayout(null);

        JPanel card = new JPanel(null);
        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createLineBorder(BORDER, 1));
        card.setBounds(30, 20, 540, 390);
        getContentPane().add(card);

        jLabel6.setText("✦  Registro de Nuevo Cliente");
        jLabel6.setForeground(TEXT_PRI);
        jLabel6.setFont(new Font("Segoe UI", Font.BOLD, 18));
        jLabel6.setBounds(20, 15, 300, 30);
        card.add(jLabel6);

        // Fila 1
        jLabel1.setText("NOMBRES");
        jLabel1.setForeground(TEXT_MUT);
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel1.setBounds(20, 65, 240, 16);
        card.add(jLabel1);
        txtNombre.setBounds(20, 85, 240, 38);
        estilizarCampo(txtNombre);
        card.add(txtNombre);

        jLabel2.setText("APELLIDOS");
        jLabel2.setForeground(TEXT_MUT);
        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel2.setBounds(280, 65, 240, 16);
        card.add(jLabel2);
        txtApellido.setBounds(280, 85, 240, 38);
        estilizarCampo(txtApellido);
        card.add(txtApellido);

        // Fila 2
        jLabel3.setText("DUI");
        jLabel3.setForeground(TEXT_MUT);
        jLabel3.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel3.setBounds(20, 140, 240, 16);
        card.add(jLabel3);
        txtDui.setBounds(20, 160, 240, 38);
        estilizarCampo(txtDui);
        card.add(txtDui);

        jLabel4.setText("TELÉFONO");
        jLabel4.setForeground(TEXT_MUT);
        jLabel4.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel4.setBounds(280, 140, 240, 16);
        card.add(jLabel4);
        txtTelefono.setBounds(280, 160, 240, 38);
        estilizarCampo(txtTelefono);
        card.add(txtTelefono);

        // Fila 3
        jLabel5.setText("DIRECCIÓN COMPLETA");
        jLabel5.setForeground(TEXT_MUT);
        jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 10));
        jLabel5.setBounds(20, 215, 500, 16);
        card.add(jLabel5);
        txtDireccion.setBounds(20, 235, 500, 38);
        estilizarCampo(txtDireccion);
        card.add(txtDireccion);

        // Botón
        btnGuardar.setText("Guardar Cliente");
        btnGuardar.setBackground(ACCENT);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setBounds(20, 310, 500, 45);
        card.add(btnGuardar);
    }

    private void estilizarCampo(JTextField f) {
        f.setBackground(BG_INPUT);
        f.setForeground(TEXT_PRI);
        f.setCaretColor(ACCENT);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BORDER, 1), new EmptyBorder(6, 10, 6, 10)));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDui = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setClosable(true);
        pack();
    }

    public javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    public javax.swing.JTextField txtApellido, txtDireccion, txtDui, txtNombre, txtTelefono;
}