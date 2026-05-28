package Controladores.CtrlPuntoVenta;

import Modelos.DetalleVenta;
import Modelos.LoteDisponible;
import Modelos.SesionUsuario;
import Modelos.Venta;
import Modelos.VentaDAO;
import Vistas.FrmPuntoDeVenta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CtrlPuntoVenta implements ActionListener {

    private FrmPuntoDeVenta form;
    private VentaDAO dao;

    private double subtotalGlobal = 0.0;
    private double ivaGlobal = 0.0;
    private double descuentoGlobal = 0.0;
    private double totalFinal = 0.0;

    public CtrlPuntoVenta(
            FrmPuntoDeVenta form,
            VentaDAO dao
    ) {
        this.form = form;
        this.dao = dao;
        iniciarFormulario();
        asignarEventos();
        
        // Bloquear edición si no tiene permiso
        if (!SesionUsuario.tienePermiso("EDICION_VENTAS")) {
            form.btnFacturar.setVisible(false);
        }
        
        // Bloquear exportación
        if (!SesionUsuario.tienePermiso("EXPORTAR_VENTAS")) {
            form.btnImprimirFactura.setVisible(false);
        }
    }

    private void iniciarFormulario() {

        form.txtFechaEmision.setText(
                new SimpleDateFormat("dd/MM/yyyy")
                        .format(new Date())
        );

        form.cmbDescuento.removeAllItems();
        form.cmbDescuento.addItem("0%");
        form.cmbDescuento.addItem("5%");
        form.cmbDescuento.addItem("10%");
        form.cmbDescuento.addItem("15%");

        configurarTabla();
        cargarClientes();
        cargarProductos();
        actualizarEtiquetasTotales();
    }

    private void configurarTabla() {

        form.tblDetalleFactura.setModel(
                new DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                            "Producto",
                            "Cantidad",
                            "Precio Unitario",
                            "Subtotal",
                            "IVA",
                            "Total",
                            "Acción",
                            "id_producto",
                            "id_lote"
                        }
                ) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false,
                        false, false, false,
                        false, false, false
                    };

                    @Override
                    public boolean isCellEditable(
                            int rowIndex,
                            int columnIndex
                    ) {
                        return canEdit[columnIndex];
                    }
                }
        );

        // OCULTAR id_producto
        form.tblDetalleFactura
                .getColumnModel().getColumn(7).setMinWidth(0);
        form.tblDetalleFactura
                .getColumnModel().getColumn(7).setMaxWidth(0);
        form.tblDetalleFactura
                .getColumnModel().getColumn(7).setWidth(0);

        // OCULTAR id_lote
        form.tblDetalleFactura
                .getColumnModel().getColumn(8).setMinWidth(0);
        form.tblDetalleFactura
                .getColumnModel().getColumn(8).setMaxWidth(0);
        form.tblDetalleFactura
                .getColumnModel().getColumn(8).setWidth(0);
    }

    private void cargarClientes() {

        form.cmbCliente.removeAllItems();

        for (String c : dao.listarClientesCombo()) {
            form.cmbCliente.addItem(c);
        }
    }

    private void cargarProductos() {

        form.cmbProducto.removeAllItems();

        for (String p : dao.listarProductosCombo()) {
            form.cmbProducto.addItem(p);
        }
    }

    private void asignarEventos() {

        form.btnAgregarProducto.addActionListener(this);
        form.btnFacturar.addActionListener(this);
        form.btnLimpiar.addActionListener(this);

        form.cmbDescuento.addActionListener(
                e -> calcularTotalesGenerales()
        );

        // ELIMINAR FILA
        form.tblDetalleFactura.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent e) {

                        int col = form.tblDetalleFactura
                                .columnAtPoint(e.getPoint());

                        int fila = form.tblDetalleFactura
                                .rowAtPoint(e.getPoint());

                        if (col == 6 && fila >= 0) {

                            int confirm = JOptionPane.showConfirmDialog(
                                    form,
                                    "¿Quitar producto?",
                                    "Confirmar",
                                    JOptionPane.YES_NO_OPTION
                            );

                            if (confirm == JOptionPane.YES_OPTION) {

                                ((DefaultTableModel)
                                        form.tblDetalleFactura.getModel())
                                        .removeRow(fila);

                                calcularTotalesGenerales();
                            }
                        }
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // =====================================================
        // AGREGAR PRODUCTO
        // =====================================================
        if (e.getSource() == form.btnAgregarProducto) {

            if (form.cmbProducto.getSelectedItem() == null) {
                return;
            }

            String cantStr = form.txtCantidad.getText().trim();

            if (cantStr.isEmpty() || !cantStr.matches("\\d+")) {
                JOptionPane.showMessageDialog(
                        form, "Ingrese una cantidad válida."
                );
                return;
            }

            int cantidadSolicitada = Integer.parseInt(cantStr);

            // DATOS PRODUCTO
            String[] partes = form.cmbProducto
                    .getSelectedItem().toString().split(" - ");

            int idProd = Integer.parseInt(partes[0].trim());
            String nombreProd = partes[1].trim();

            // OBTENER LOTES
            ArrayList<LoteDisponible> lotes =
                    dao.obtenerLotesDisponibles(idProd);

            // VALIDAR STOCK REAL
            int stockTotal = 0;

            for (LoteDisponible lote : lotes) {

                int yaEnFactura = obtenerCantidadEnFactura(
                        idProd, lote.getIdLote()
                );

                int stockDisponibleReal =
                        lote.getStock() - yaEnFactura;

                if (stockDisponibleReal > 0) {
                    stockTotal += stockDisponibleReal;
                }
            }

            if (cantidadSolicitada > stockTotal) {
                JOptionPane.showMessageDialog(
                        form,
                        "Stock insuficiente.\nDisponible: " + stockTotal
                );
                return;
            }

            // REPARTIR ENTRE LOTES
            int cantidadRestante = cantidadSolicitada;

            DefaultTableModel modelo =
                    (DefaultTableModel) form.tblDetalleFactura.getModel();

            for (LoteDisponible lote : lotes) {

                if (cantidadRestante <= 0) break;

                int yaEnFactura = obtenerCantidadEnFactura(
                        idProd, lote.getIdLote()
                );

                int stockDisponibleReal =
                        lote.getStock() - yaEnFactura;

                if (stockDisponibleReal <= 0) continue;

                int cantidadTomada =
                        (stockDisponibleReal >= cantidadRestante)
                        ? cantidadRestante
                        : stockDisponibleReal;

                double precioUnidad = lote.getPrecioVenta();

                int filaExistente = buscarFilaExistente(
                        idProd, lote.getIdLote()
                );

                // SI YA EXISTE ESA FILA
                if (filaExistente >= 0) {

                    int cantidadActual = Integer.parseInt(
                            modelo.getValueAt(filaExistente, 1).toString()
                    );

                    int nuevaCantidad = cantidadActual + cantidadTomada;

                    double subtotalFila = nuevaCantidad * precioUnidad;
                    double ivaFila = subtotalFila * 0.13;
                    double totalFila = subtotalFila + ivaFila;

                    modelo.setValueAt(nuevaCantidad, filaExistente, 1);
                    modelo.setValueAt(
                            String.format("%.2f", subtotalFila),
                            filaExistente, 3
                    );
                    modelo.setValueAt(
                            String.format("%.2f", ivaFila),
                            filaExistente, 4
                    );
                    modelo.setValueAt(
                            String.format("%.2f", totalFila),
                            filaExistente, 5
                    );

                } else {

                    // NUEVA FILA
                    double subtotalFila = precioUnidad * cantidadTomada;
                    double ivaFila = subtotalFila * 0.13;
                    double totalFila = subtotalFila + ivaFila;

                    modelo.addRow(new Object[]{
                        nombreProd,
                        cantidadTomada,
                        String.format("%.2f", precioUnidad),
                        String.format("%.2f", subtotalFila),
                        String.format("%.2f", ivaFila),
                        String.format("%.2f", totalFila),
                        "❌ Eliminar",
                        idProd,
                        lote.getIdLote()
                    });
                }

                cantidadRestante -= cantidadTomada;
            }

            form.txtCantidad.setText("");
            calcularTotalesGenerales();
        }

        // =====================================================
        // FACTURAR
        // =====================================================
        if (e.getSource() == form.btnFacturar) {

            DefaultTableModel modelo =
                    (DefaultTableModel) form.tblDetalleFactura.getModel();

            if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(form, "No hay productos.");
                return;
            }

            if (form.cmbCliente.getSelectedItem() == null) return;

            Venta nuevaVenta = new Venta();

            nuevaVenta.setIdCliente(
                    Integer.parseInt(
                            form.cmbCliente.getSelectedItem()
                                    .toString().split(" - ")[0]
                    )
            );

            nuevaVenta.setTipoComprobante("Ticket");
            nuevaVenta.setEstado("Completada");
            nuevaVenta.setIdUsuario(3);

            // RECORRER TABLA
            for (int i = 0; i < modelo.getRowCount(); i++) {

                DetalleVenta d = new DetalleVenta();

                d.setCantidad(
                        Integer.parseInt(
                                modelo.getValueAt(i, 1).toString()
                        )
                );

                d.setPrecioUnitario(
                        Double.parseDouble(
                                modelo.getValueAt(i, 2)
                                        .toString().replace(",", ".")
                        )
                );

                d.setIdProducto(
                        Integer.parseInt(
                                modelo.getValueAt(i, 7).toString()
                        )
                );

                d.setIdLote(
                        Integer.parseInt(
                                modelo.getValueAt(i, 8).toString()
                        )
                );

                nuevaVenta.agregarDetalle(d);
            }

            // GUARDAR
            if (dao.guardarVenta(nuevaVenta)) {
                JOptionPane.showMessageDialog(
                        form, "Venta guardada correctamente."
                );
                limpiarTodo();
            } else {
                JOptionPane.showMessageDialog(
                        form, "Error al guardar venta."
                );
            }
        }

        // =====================================================
        // LIMPIAR
        // =====================================================
        if (e.getSource() == form.btnLimpiar) {
            limpiarTodo();
        }
    }

    // =====================================================
    // BUSCAR FILA EXISTENTE
    // =====================================================
    private int buscarFilaExistente(int idProducto, int idLote) {

        DefaultTableModel modelo =
                (DefaultTableModel) form.tblDetalleFactura.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {

            int prodTabla = Integer.parseInt(
                    modelo.getValueAt(i, 7).toString()
            );

            int loteTabla = Integer.parseInt(
                    modelo.getValueAt(i, 8).toString()
            );

            if (prodTabla == idProducto && loteTabla == idLote) {
                return i;
            }
        }

        return -1;
    }

    // =====================================================
    // OBTENER CANTIDAD EN FACTURA
    // =====================================================
    private int obtenerCantidadEnFactura(int idProducto, int idLote) {

        int cantidad = 0;

        DefaultTableModel modelo =
                (DefaultTableModel) form.tblDetalleFactura.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {

            int prodTabla = Integer.parseInt(
                    modelo.getValueAt(i, 7).toString()
            );

            int loteTabla = Integer.parseInt(
                    modelo.getValueAt(i, 8).toString()
            );

            if (prodTabla == idProducto && loteTabla == idLote) {
                cantidad += Integer.parseInt(
                        modelo.getValueAt(i, 1).toString()
                );
            }
        }

        return cantidad;
    }

    // =====================================================
    // CALCULAR TOTALES
    // =====================================================
    private void calcularTotalesGenerales() {

        DefaultTableModel modelo =
                (DefaultTableModel) form.tblDetalleFactura.getModel();

        subtotalGlobal = 0.0;
        ivaGlobal = 0.0;

        for (int i = 0; i < modelo.getRowCount(); i++) {

            subtotalGlobal += Double.parseDouble(
                    modelo.getValueAt(i, 3)
                            .toString().replace(",", ".")
            );

            ivaGlobal += Double.parseDouble(
                    modelo.getValueAt(i, 4)
                            .toString().replace(",", ".")
            );
        }

        String descStr =
                form.cmbDescuento.getSelectedItem() != null
                ? form.cmbDescuento.getSelectedItem().toString()
                : "0%";

        descuentoGlobal = subtotalGlobal * (
                Double.parseDouble(descStr.replace("%", "")) / 100.0
        );

        totalFinal = (subtotalGlobal - descuentoGlobal) + ivaGlobal;

        actualizarEtiquetasTotales();
    }

    // =====================================================
    // ACTUALIZAR LABELS
    // =====================================================
    private void actualizarEtiquetasTotales() {

        form.lblSubTotal.setText(
                String.format("$ %.2f", subtotalGlobal)
        );

        form.lblIVA.setText(
                String.format("$ %.2f", ivaGlobal)
        );

        form.lblDescuento.setText(
                String.format("- $ %.2f", descuentoGlobal)
        );

        form.lblTotal.setText(
                String.format("$ %.2f", totalFinal)
        );
    }

    // =====================================================
    // LIMPIAR
    // =====================================================
    private void limpiarTodo() {

        ((DefaultTableModel) form.tblDetalleFactura.getModel())
                .setRowCount(0);

        form.txtCantidad.setText("");

        if (form.cmbDescuento.getItemCount() > 0) {
            form.cmbDescuento.setSelectedIndex(0);
        }

        calcularTotalesGenerales();
    }
}