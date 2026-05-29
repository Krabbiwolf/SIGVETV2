package Controladores.CtrlCompra;

import Modelos.CompraDAO;
import Modelos.Compra;
import Modelos.SesionUsuario;
import Modelos.ConfiguracionDAO;
import Vistas.FrmPuntoDeCompra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CancellationException;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class CtrlPuntoCompra implements ActionListener {

    private FrmPuntoDeCompra form;
    private CompraDAO dao;
    private ConfiguracionDAO configuracionDAO;

    private double ivaDecimal    = 0.13;
    private double descuentoMaximo = 25.0;

    // Workers para cancelar si se abre/cierra rápido
    private SwingWorker<Void, Void> currentDatosWorker;

    public CtrlPuntoCompra(FrmPuntoDeCompra form, CompraDAO dao) {
        this.form             = form;
        this.dao              = dao;
        this.configuracionDAO = new ConfiguracionDAO();

        // Configuración síncrona mínima (rápida, no toca BD pesada)
        cargarConfiguracion();
        iniciarFormulario();
        asignarEventos();

        // Permisos
        if (!SesionUsuario.tienePermiso("EDICION_COMPRAS")) {
            form.btnRegistrarCompra.setVisible(false);
        }
        if (!SesionUsuario.tienePermiso("EXPORTAR_COMPRAS")) {
            form.btnImprimirFactura.setVisible(false);
        }
    }

    // ─────────────────────────────────────────────────────────
    //  CONFIGURACIÓN (síncrona, solo lee valores simples)
    // ─────────────────────────────────────────────────────────
    private void cargarConfiguracion() {
        ivaDecimal     = configuracionDAO.obtenerValor("iva_predeterminado", 13.00) / 100.0;
        descuentoMaximo = configuracionDAO.obtenerValor("descuento_maximo", 25.00);
    }

    // ─────────────────────────────────────────────────────────
    //  INICIO DEL FORMULARIO (no toca BD)
    // ─────────────────────────────────────────────────────────
    private void iniciarFormulario() {
        form.txtFechaEmision.setText(
            new SimpleDateFormat("dd/MM/yyyy").format(new Date())
        );
        configurarTabla();
        configurarComboDescuentos(); // Solo llena opciones, sin BD
        recalcularTotales();

        // Bloquear combos mientras cargan
        bloquearCombos("Cargando...");

        // Arrancar la carga asíncrona
        cargarDatosAsync();
    }

    // ─────────────────────────────────────────────────────────
    //  CARGA ASÍNCRONA  — UN SOLO SwingWorker, sin llamadas
    //  síncronas adicionales que bloqueen el EDT
    // ─────────────────────────────────────────────────────────
    private void cargarDatosAsync() {
        // Cancelar worker previo si existiera
        if (currentDatosWorker != null && !currentDatosWorker.isDone()) {
            currentDatosWorker.cancel(true);
        }

        currentDatosWorker = new SwingWorker<Void, Void>() {

            // Resultados que viajan del hilo de fondo al EDT
            List<String> listaProveedores;
            List<String> listaProductos;

            @Override
            protected Void doInBackground() throws Exception {
                // ← Aquí NO hay Swing, solo acceso a BD
                listaProveedores = dao.listarProveedoresCombo();
                listaProductos   = dao.listarProductosCombo();
                return null;
            }

            @Override
            protected void done() {
                try {
                    get(); // Lanza excepción si doInBackground falló

                    // Llenar proveedor
                    form.cmbProveedor.removeAllItems();
                    for (String p : listaProveedores) {
                        form.cmbProveedor.addItem(p);
                    }

                    // Llenar producto
                    form.cmbProducto.removeAllItems();
                    for (String p : listaProductos) {
                        form.cmbProducto.addItem(p);
                    }

                    habilitarCombos();

                } catch (CancellationException ex) {
                    System.out.println("Carga cancelada.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(form,
                        "Error al cargar datos: " + ex.getMessage());
                } finally {
                    currentDatosWorker = null;
                }
            }
        };

        currentDatosWorker.execute();
    }

    private void bloquearCombos(String mensaje) {
        form.cmbProveedor.removeAllItems();
        form.cmbProveedor.addItem(mensaje);
        form.cmbProveedor.setEnabled(false);

        form.cmbProducto.removeAllItems();
        form.cmbProducto.addItem(mensaje);
        form.cmbProducto.setEnabled(false);

        form.btnAgregarProducto.setEnabled(false);
    }

    private void habilitarCombos() {
        form.cmbProveedor.setEnabled(true);
        form.cmbProducto.setEnabled(true);
        form.btnAgregarProducto.setEnabled(true);
    }

    // ─────────────────────────────────────────────────────────
    //  TABLA
    // ─────────────────────────────────────────────────────────
    private void configurarTabla() {
        form.tblDetalleCompra.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "Producto", "Cantidad", "Precio Compra",
                "Subtotal", "IVA", "Total", "Acción", "id_producto"
            }
        ) {
            final boolean[] canEdit = {
                false, false, false, false, false, false, false, false
            };
            @Override
            public boolean isCellEditable(int row, int col) { return canEdit[col]; }
        });

        // Ocultar columna id_producto
        var col = form.tblDetalleCompra.getColumnModel().getColumn(7);
        col.setMinWidth(0);
        col.setMaxWidth(0);
        col.setWidth(0);
    }

    // ─────────────────────────────────────────────────────────
    //  DESCUENTOS (sin BD, solo aritmética)
    // ─────────────────────────────────────────────────────────
    private void configurarComboDescuentos() {
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

    // ─────────────────────────────────────────────────────────
    //  EVENTOS
    // ─────────────────────────────────────────────────────────
    private void asignarEventos() {
        form.btnAgregarProducto.addActionListener(this);
        form.btnRegistrarCompra.addActionListener(this);
        form.btnLimpiar.addActionListener(this);

        form.cmbDescuento.addActionListener(e -> recalcularTotales());

        form.tblDetalleCompra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col  = form.tblDetalleCompra.columnAtPoint(e.getPoint());
                int fila = form.tblDetalleCompra.rowAtPoint(e.getPoint());
                if (col == 6 && fila >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(
                        form, "¿Quitar producto?", "Confirmar",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        ((DefaultTableModel) form.tblDetalleCompra.getModel()).removeRow(fila);
                        recalcularTotales();
                    }
                }
            }
        });
    }

    // ─────────────────────────────────────────────────────────
    //  ACTION PERFORMED
    // ─────────────────────────────────────────────────────────
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == form.btnAgregarProducto) {
            agregarProducto();
        }

        else if (e.getSource() == form.btnRegistrarCompra) {
            registrarCompra();
        }

        else if (e.getSource() == form.btnLimpiar) {
            limpiarTodo();
        }
    }

    // ─────────────────────────────────────────────────────────
    //  AGREGAR PRODUCTO
    // ─────────────────────────────────────────────────────────
    private void agregarProducto() {
        // Evitar acción si aún están cargando
        if (form.cmbProducto.getSelectedItem() == null
                || form.cmbProducto.getSelectedItem().toString().startsWith("Cargando")) {
            JOptionPane.showMessageDialog(form, "Espere, los datos aún están cargando.");
            return;
        }

        String cantStr   = form.txtCantidad.getText().trim();
        String precioStr = form.txtPrecioCompra.getText().trim();

        if (cantStr.isEmpty() || !cantStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(form, "Ingrese una cantidad válida.");
            return;
        }
        if (precioStr.isEmpty() || !precioStr.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(form, "Ingrese un precio de compra válido.");
            return;
        }

        int    cantidad     = Integer.parseInt(cantStr);
        double precioCompra = Double.parseDouble(precioStr);

        if (cantidad <= 0)     { JOptionPane.showMessageDialog(form, "La cantidad debe ser mayor a 0."); return; }
        if (precioCompra <= 0) { JOptionPane.showMessageDialog(form, "El precio debe ser mayor a 0.");   return; }

        String[] partes   = form.cmbProducto.getSelectedItem().toString().split(" - ");
        int      idProd   = Integer.parseInt(partes[0].trim());
        String   nombreProd = partes[1].trim();

        DefaultTableModel modelo = (DefaultTableModel) form.tblDetalleCompra.getModel();
        int filaExistente = buscarFilaExistente(idProd);

        double subtotalFila = (precioCompra / (1 + ivaDecimal)) * cantidad;
        double ivaFila      = subtotalFila * ivaDecimal;
        double totalFila    = cantidad * precioCompra;

        if (filaExistente >= 0) {
            int    cantidadActual = Integer.parseInt(modelo.getValueAt(filaExistente, 1).toString());
            int    nuevaCantidad  = cantidadActual + cantidad;
            double nuevoSubtotal  = (precioCompra / (1 + ivaDecimal)) * nuevaCantidad;
            double nuevoIva       = nuevoSubtotal * ivaDecimal;
            double nuevoTotal     = nuevaCantidad * precioCompra;

            modelo.setValueAt(nuevaCantidad,                          filaExistente, 1);
            modelo.setValueAt(String.format("%.2f", precioCompra),   filaExistente, 2);
            modelo.setValueAt(String.format("%.2f", nuevoSubtotal),  filaExistente, 3);
            modelo.setValueAt(String.format("%.2f", nuevoIva),       filaExistente, 4);
            modelo.setValueAt(String.format("%.2f", nuevoTotal),     filaExistente, 5);
        } else {
            modelo.addRow(new Object[]{
                nombreProd,
                cantidad,
                String.format("%.2f", precioCompra),
                String.format("%.2f", subtotalFila),
                String.format("%.2f", ivaFila),
                String.format("%.2f", totalFila),
                "× Eliminar",
                idProd
            });
        }

        form.txtCantidad.setText("");
        form.txtPrecioCompra.setText("");
        recalcularTotales();
    }

    // ─────────────────────────────────────────────────────────
    //  REGISTRAR COMPRA
    // ─────────────────────────────────────────────────────────
    private void registrarCompra() {
        DefaultTableModel modelo = (DefaultTableModel) form.tblDetalleCompra.getModel();

        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(form, "No hay productos en la compra.");
            return;
        }
        if (form.cmbProveedor.getSelectedItem() == null
                || form.cmbProveedor.getSelectedItem().toString().startsWith("Cargando")) {
            JOptionPane.showMessageDialog(form, "Espere, los proveedores aún están cargando.");
            return;
        }

        int    idProveedor        = Integer.parseInt(
            form.cmbProveedor.getSelectedItem().toString().split(" - ")[0]);
        double descuentoSeleccionado = obtenerDescuentoSeleccionado();
        double factorDescuento    = 1 - (descuentoSeleccionado / 100.0);
        boolean todoBien          = true;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            int    idProducto      = Integer.parseInt(modelo.getValueAt(i, 7).toString());
            int    cantidad        = Integer.parseInt(modelo.getValueAt(i, 1).toString());
            double precioOriginal  = Double.parseDouble(
                modelo.getValueAt(i, 2).toString().replace(",", "."));
            double precioConDesc   = precioOriginal * factorDescuento;

            Compra compra = new Compra();
            compra.setIdProveedor(idProveedor);
            compra.setIdUsuario(SesionUsuario.getIdUsuarioActual());

            boolean resultado = dao.registrarCompra(compra, idProducto, cantidad, precioConDesc);

            if (!resultado) {
                todoBien = false;
                JOptionPane.showMessageDialog(form,
                    "Error al registrar: " + modelo.getValueAt(i, 0));
                break;
            }
        }

        if (todoBien) {
            JOptionPane.showMessageDialog(form, "Compra registrada correctamente.");
            limpiarTodo();
        }
    }

    // ─────────────────────────────────────────────────────────
    //  HELPERS
    // ─────────────────────────────────────────────────────────
    private int buscarFilaExistente(int idProducto) {
        DefaultTableModel modelo = (DefaultTableModel) form.tblDetalleCompra.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (Integer.parseInt(modelo.getValueAt(i, 7).toString()) == idProducto) {
                return i;
            }
        }
        return -1;
    }

    private double obtenerDescuentoSeleccionado() {
        if (form.cmbDescuento.getSelectedItem() == null) return 0.0;
        try {
            return Double.parseDouble(
                form.cmbDescuento.getSelectedItem().toString()
                    .replace("%", "").trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void recalcularTotales() {
        DefaultTableModel modelo = (DefaultTableModel) form.tblDetalleCompra.getModel();
        double subtotal = 0, iva = 0, totalBruto = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            subtotal   += Double.parseDouble(modelo.getValueAt(i, 3).toString().replace(",", "."));
            iva        += Double.parseDouble(modelo.getValueAt(i, 4).toString().replace(",", "."));
            totalBruto += Double.parseDouble(modelo.getValueAt(i, 5).toString().replace(",", "."));
        }

        double desc       = Math.min(obtenerDescuentoSeleccionado(), descuentoMaximo);
        double descuento  = totalBruto * (desc / 100.0);
        double totalFinal = totalBruto - descuento;

        form.lblSubTotal.setText(String.format("$ %.2f", subtotal));
        form.lblIVA.setText     (String.format("$ %.2f", iva));
        form.lblDescuento.setText(String.format("- $ %.2f", descuento));
        form.lblTotal.setText   (String.format("$ %.2f", totalFinal));
    }

    private void limpiarTodo() {
        ((DefaultTableModel) form.tblDetalleCompra.getModel()).setRowCount(0);
        form.txtPrecioCompra.setText("");
        form.txtCantidad.setText("");
        if (form.cmbDescuento.getItemCount() > 0) form.cmbDescuento.setSelectedIndex(0);
        recalcularTotales();
    }
}