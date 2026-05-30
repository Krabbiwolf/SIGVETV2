package Controladores.CtrlPuntoVenta;

import Modelos.ConfiguracionDAO;
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
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class CtrlPuntoVenta implements ActionListener {

    private FrmPuntoDeVenta form;
    private VentaDAO dao;
    private ConfiguracionDAO configuracionDAO;

    private double subtotalGlobal = 0.0;
    private double ivaGlobal = 0.0;
    private double descuentoGlobal = 0.0;
    private double totalFinal = 0.0;

    private double ivaDecimal = 0.13;
    private double descuentoMaximo = 15.0;

    public CtrlPuntoVenta(
            FrmPuntoDeVenta form,
            VentaDAO dao
    ) {
        this.form = form;
        this.dao = dao;
        this.configuracionDAO = new ConfiguracionDAO();

        cargarConfiguracion();
        iniciarFormulario();
        asignarEventos();
        
        // Bloquear edición si no tiene permiso
        if (!SesionUsuario.tienePermiso("EDICION_VENTAS")) {
            form.btnFacturar.setVisible(false); // O como se llame tu botón de realizar venta
        }
        
        // Bloquear exportación
        if (!SesionUsuario.tienePermiso("EXPORTAR_VENTAS")) {
            form.btnImprimirFactura.setVisible(false);
        }
    }

    private void cargarConfiguracion() {
        double ivaPorcentaje = configuracionDAO.obtenerValor("iva_predeterminado", 13.00);
        ivaDecimal = ivaPorcentaje / 100.0;

        descuentoMaximo = configuracionDAO.obtenerValor("descuento_maximo", 15.00);
    }

    private void iniciarFormulario() {

        form.txtFechaEmision.setText(
                new SimpleDateFormat("dd/MM/yyyy")
                        .format(new Date())
        );

        cargarComboDescuentos();

        configurarTabla();
        
        // Llamada a la carga asíncrona en lugar de bloquear el hilo principal
        cargarDatosAsync();
        
        actualizarEtiquetasTotales();
    }

    // =====================================================
    // CARGA ASÍNCRONA DE DATOS (SWING WORKER)
    // =====================================================
    private void cargarDatosAsync() {
        // 1. Mostrar estado de carga en la vista y bloquear los combos temporalmente
        form.cmbCliente.removeAllItems();
        form.cmbCliente.addItem("Cargando clientes...");
        form.cmbCliente.setEnabled(false);

        form.cmbProducto.removeAllItems();
        form.cmbProducto.addItem("Cargando productos...");
        form.cmbProducto.setEnabled(false);
        
        form.btnAgregarProducto.setEnabled(false);

        // 2. Crear el hilo en segundo plano
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            
            List<String> listaClientes;
            List<String> listaProductos;

            @Override
            protected Void doInBackground() throws Exception {
                // Esto ocurre en segundo plano (No congela la ventana)
                listaClientes = dao.listarClientesCombo();
                listaProductos = dao.listarProductosCombo();
                return null;
            }

            @Override
            protected void done() {
                // Esto se ejecuta cuando doInBackground termina (Actualiza la interfaz de forma segura)
                try {
                    get(); // Verifica si hubo errores en el proceso en segundo plano
                    
                    form.cmbCliente.removeAllItems();
                    for (String c : listaClientes) {
                        form.cmbCliente.addItem(c);
                    }
                    form.cmbCliente.setEnabled(true);

                    form.cmbProducto.removeAllItems();
                    for (String p : listaProductos) {
                        form.cmbProducto.addItem(p);
                    }
                    form.cmbProducto.setEnabled(true);
                    
                    form.btnAgregarProducto.setEnabled(true);

                } catch (Exception ex) {
                    System.out.println("Error en la carga asíncrona: " + ex.getMessage());
                    JOptionPane.showMessageDialog(form, "Error al cargar los datos del sistema.");
                }
            }
        };

        // 3. Ejecutar el hilo
        worker.execute();
    } // <--- ¡Esta era la llave de cierre que Git te había borrado!

    private void cargarComboDescuentos() {
        form.cmbDescuento.removeAllItems();

        int max = (int) descuentoMaximo;

        for (int i = 0; i <= max; i += 5) {
            form.cmbDescuento.addItem(i + "%");
        }

        if (max % 5 != 0) {
            form.cmbDescuento.addItem(max + "%");
        }

        if (form.cmbDescuento.getItemCount() == 0) {
            form.cmbDescuento.addItem("0%");
        }

        form.cmbDescuento.setSelectedIndex(0);
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

        form.tblDetalleFactura
                .getColumnModel().getColumn(7).setMinWidth(0);
        form.tblDetalleFactura
                .getColumnModel().getColumn(7).setMaxWidth(0);
        form.tblDetalleFactura
                .getColumnModel().getColumn(7).setWidth(0);

        form.tblDetalleFactura
                .getColumnModel().getColumn(8).setMinWidth(0);
        form.tblDetalleFactura
                .getColumnModel().getColumn(8).setMaxWidth(0);
        form.tblDetalleFactura
                .getColumnModel().getColumn(8).setWidth(0);
    }

    private void asignarEventos() {

        form.btnAgregarProducto.addActionListener(this);
        form.btnFacturar.addActionListener(this);
        form.btnLimpiar.addActionListener(this);

        form.cmbDescuento.addActionListener(
                e -> calcularTotalesGenerales()
        );

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

        if (e.getSource() == form.btnAgregarProducto) {

            if (form.cmbProducto.getSelectedItem() == null || form.cmbProducto.getSelectedItem().toString().startsWith("Cargando")) {
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

            String[] partes = form.cmbProducto
                    .getSelectedItem().toString().split(" - ");

            int idProd = Integer.parseInt(partes[0].trim());
            String nombreProd = partes[1].trim();

            ArrayList<LoteDisponible> lotes =
                    dao.obtenerLotesDisponibles(idProd);

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

                if (filaExistente >= 0) {

                    int cantidadActual = Integer.parseInt(
                            modelo.getValueAt(filaExistente, 1).toString()
                    );

                    int nuevaCantidad = cantidadActual + cantidadTomada;

                    double subtotalFila = nuevaCantidad * precioUnidad;
                    double ivaFila = subtotalFila * ivaDecimal;
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

                    double subtotalFila = precioUnidad * cantidadTomada;
                    double ivaFila = subtotalFila * ivaDecimal;
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

        if (e.getSource() == form.btnFacturar) {

            DefaultTableModel modelo =
                    (DefaultTableModel) form.tblDetalleFactura.getModel();

            if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(form, "No hay productos.");
                return;
            }

            if (form.cmbCliente.getSelectedItem() == null || form.cmbCliente.getSelectedItem().toString().startsWith("Cargando")) return;

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

            double descuentoSeleccionado = obtenerDescuentoSeleccionado();

            if (dao.guardarVenta(nuevaVenta, descuentoSeleccionado)) {
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

        if (e.getSource() == form.btnLimpiar) {
            limpiarTodo();
        }
    }

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

    private double obtenerDescuentoSeleccionado() {
        String descStr =
                form.cmbDescuento.getSelectedItem() != null
                ? form.cmbDescuento.getSelectedItem().toString()
                : "0%";

        return Double.parseDouble(descStr.replace("%", ""));
    }

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

        double descuentoSeleccionado = obtenerDescuentoSeleccionado();

        if (descuentoSeleccionado > descuentoMaximo) {
            descuentoSeleccionado = descuentoMaximo;
        }

        descuentoGlobal = subtotalGlobal * (descuentoSeleccionado / 100.0);

        totalFinal = (subtotalGlobal - descuentoGlobal) + ivaGlobal;

        actualizarEtiquetasTotales();
    }

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