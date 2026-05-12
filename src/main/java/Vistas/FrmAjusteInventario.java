package Vistas;

import Modelos.LoteInventario;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
 * Vista del módulo Ajuste de Inventario.
 * Esta pantalla se hizo manual para cumplir con los campos solicitados:
 * lote, stock actual, tipo de ajuste, cantidad, motivo, guardar y cancelar.
 */
public class FrmAjusteInventario extends JInternalFrame {

    public JButton btnLimpiar;
    public JButton btnRegistrarAjuste;
    public JComboBox<LoteInventario> cboLoteProducto;
    public JComboBox<String> cboTipoMovimiento;
    public JLabel lblStockActual;
    public JTable tblAjustes;
    public JTextField txtCantidad;
    public JTextArea txtMotivoAjuste;

    private JLabel jLabelTitulo;
    private JLabel jLabelLote;
    private JLabel jLabelStock;
    private JLabel jLabelTipo;
    private JLabel jLabelCantidad;
    private JLabel jLabelMotivo;
    private JScrollPane scrollMotivo;
    private JScrollPane scrollTabla;

    public FrmAjusteInventario() {
        initComponents();
    }

    private void initComponents() {
        jLabelTitulo = new JLabel("AJUSTE DE INVENTARIO");
        jLabelLote = new JLabel("Producto / lote");
        jLabelStock = new JLabel("Stock actual");
        jLabelTipo = new JLabel("Tipo de ajuste");
        jLabelCantidad = new JLabel("Cantidad");
        jLabelMotivo = new JLabel("Motivo del ajuste");

        cboLoteProducto = new JComboBox<>();
        cboTipoMovimiento = new JComboBox<>();

        lblStockActual = new JLabel("0");
        lblStockActual.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblStockActual.setBorder(BorderFactory.createEtchedBorder());

        txtCantidad = new JTextField();
        txtMotivoAjuste = new JTextArea(4, 20);
        txtMotivoAjuste.setLineWrap(true);
        txtMotivoAjuste.setWrapStyleWord(true);
        scrollMotivo = new JScrollPane(txtMotivoAjuste);

        btnRegistrarAjuste = new JButton("Guardar");
        btnLimpiar = new JButton("Cancelar");

        tblAjustes = new JTable();
        scrollTabla = new JScrollPane(tblAjustes);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ajuste de Inventario");

        jLabelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GroupLayout layout = new GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jLabelTitulo)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabelLote, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelStock, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelTipo, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelCantidad, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelMotivo, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(cboLoteProducto, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblStockActual, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboTipoMovimiento, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollMotivo, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnRegistrarAjuste, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
                .addComponent(scrollTabla, GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(jLabelTitulo, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                .addGap(15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLote, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLoteProducto, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarAjuste, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStock, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStockActual, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTipo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTipoMovimiento, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCantidad, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMotivo, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollMotivo, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                .addGap(20)
                .addComponent(scrollTabla, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        pack();
    }
}
