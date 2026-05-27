package Vistas;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

public interface MaestroDetalleVista {

    String CLIENTES_FACTURAS = "CLIENTES_FACTURAS";
    String PROVEEDORES_COMPRAS = "PROVEEDORES_COMPRAS";
    String CATEGORIAS_PRODUCTOS = "CATEGORIAS_PRODUCTOS";
    String PRODUCTOS_LOTES = "PRODUCTOS_LOTES";

    String getTipo();

    void configurarTextos(String titulo, String maestro, String detalle, String placeholder);

    JLabel getLblTitulo();

    JLabel getLblMaestro();

    JLabel getLblDetalle();

    JLabel getLblInfo();

    JTextField getTxtBuscar();

    JButton getBtnBuscar();

    JButton getBtnActualizar();

    JButton getBtnLimpiar();

    JTable getTblMaestro();

    JTable getTblDetalle();
}
