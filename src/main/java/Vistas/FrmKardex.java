package Vistas;

import java.awt.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FrmKardex extends JInternalFrame {

    public JSpinner spFechaInicio;
    public JSpinner spFechaFin;
    public JComboBox<String> cboTipoMovimiento;
    public JTextField txtBuscar;
    public JButton btnFiltrar;
    public JButton btnLimpiar;
    public JButton btnExportar;
    public JTable tblKardex;

    public FrmKardex() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Kardex - Historial de Movimientos");
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        setSize(1100, 620);
        setLayout(new BorderLayout(10, 10));

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panelPrincipal.setBackground(new Color(20, 24, 36));

        JPanel panelFiltros = new JPanel(new GridBagLayout());
        panelFiltros.setBackground(new Color(17, 21, 32));
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros de búsqueda"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblInicio = new JLabel("Fecha Inicio:");
        lblInicio.setForeground(Color.WHITE);

        JLabel lblFin = new JLabel("Fecha Fin:");
        lblFin.setForeground(Color.WHITE);

        JLabel lblTipo = new JLabel("Tipo Movimiento:");
        lblTipo.setForeground(Color.WHITE);

        JLabel lblBuscar = new JLabel("Buscar:");
        lblBuscar.setForeground(Color.WHITE);

        spFechaInicio = new JSpinner(new SpinnerDateModel());
        spFechaFin = new JSpinner(new SpinnerDateModel());
        
        java.util.Calendar calendarInicio = java.util.Calendar.getInstance();
calendarInicio.add(java.util.Calendar.MONTH, -1);
spFechaInicio.setValue(calendarInicio.getTime());

java.util.Calendar calendarFin = java.util.Calendar.getInstance();
calendarFin.add(java.util.Calendar.DAY_OF_MONTH, 1);
spFechaFin.setValue(calendarFin.getTime());

        JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(spFechaInicio, "yyyy-MM-dd");
        JSpinner.DateEditor editorFin = new JSpinner.DateEditor(spFechaFin, "yyyy-MM-dd");

        spFechaInicio.setEditor(editorInicio);
        spFechaFin.setEditor(editorFin);

        cboTipoMovimiento = new JComboBox<>();
        cboTipoMovimiento.addItem("Todos");
        cboTipoMovimiento.addItem("ENTRADA");
        cboTipoMovimiento.addItem("SALIDA");
        cboTipoMovimiento.addItem("AJUSTE");

        txtBuscar = new JTextField();
        txtBuscar.setToolTipText("Buscar por código de barras o nombre del producto");

        btnFiltrar = new JButton("Filtrar / Buscar");
        btnLimpiar = new JButton("Limpiar");
        btnExportar = new JButton("Exportar Excel");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFiltros.add(lblInicio, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panelFiltros.add(spFechaInicio, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panelFiltros.add(lblFin, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        panelFiltros.add(spFechaFin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFiltros.add(lblTipo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panelFiltros.add(cboTipoMovimiento, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panelFiltros.add(lblBuscar, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 1;
        panelFiltros.add(txtBuscar, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 0;
        panelFiltros.add(btnFiltrar, gbc);

        gbc.gridx = 4;
        gbc.gridy = 1;
        panelFiltros.add(btnLimpiar, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        panelFiltros.add(btnExportar, gbc);

        tblKardex = new JTable();
        tblKardex.setRowHeight(28);
        tblKardex.getTableHeader().setReorderingAllowed(false);

        JScrollPane scroll = new JScrollPane(tblKardex);
        scroll.setBorder(BorderFactory.createTitledBorder("Historial de movimientos"));

        panelPrincipal.add(panelFiltros, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    public Date getFechaInicio() {
        return (Date) spFechaInicio.getValue();
    }

    public Date getFechaFin() {
        return (Date) spFechaFin.getValue();
    }

    public void cargarModelo(DefaultTableModel modelo) {
        tblKardex.setModel(modelo);
    }
}