package Controladores.CtrlCompra;

import Modelos.CompraDAO;
import Modelos.Compra;
import Modelos.SesionUsuario;
import Vistas.FrmPuntoDeCompra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class CtrlPuntoCompra implements ActionListener {

    private FrmPuntoDeCompra form;
    private CompraDAO dao;

    public CtrlPuntoCompra(FrmPuntoDeCompra form, CompraDAO dao) {
        this.form = form;
        this.dao = dao;
        iniciarFormulario();
        asignarEventos();
        
        // Bloquear edición si no tiene permiso
        if (!SesionUsuario.tienePermiso("EDICION_COMPRAS")) {
            form.btnRegistrarCompra.setVisible(false);
        }
        
        // Bloquear exportación
        if (!SesionUsuario.tienePermiso("EXPORTAR_COMPRAS")) {
            form.btnImprimirFactura.setVisible(false);
        }
    }

    private void iniciarFormulario() {
        form.txtFechaEmision.setText(
            new SimpleDateFormat("dd/MM/yyyy").format(new Date())
        );
        configurarTabla();
        
        // Llamada a la carga asíncrona para no congelar la ventana
        cargarDatosAsync();
    }

    // =====================================================
    // CARGA ASÍNCRONA DE DATOS (SWING WORKER)
    // =====================================================
    private void cargarDatosAsync() {
        // 1. Mostrar estado de carga y bloquear combos temporalmente
        form.cmbProveedor.removeAllItems();
        form.cmbProveedor.addItem("Cargando proveedores...");
        form.cmbProveedor.setEnabled(false);

        form.cmbProducto.removeAllItems();
        form.cmbProducto.addItem("Cargando productos...");
        form.cmbProducto.setEnabled(false);
        
        form.btnAgregarProducto.setEnabled(false);

        // 2. Crear el hilo en segundo plano
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            
            List<String> listaProveedores;
            List<String> listaProductos;

            @Override
            protected Void doInBackground() throws Exception {
                // Se ejecuta sin bloquear la ventana
                listaProveedores = dao.listarProveedoresCombo();
                listaProductos = dao.listarProductosCombo();
                return null;
            }

            @Override
            protected void done() {
                // Actualiza la interfaz gráfica una vez llegan los datos
                try {
                    get(); // Verifica errores
                    
                    form.cmbProveedor.removeAllItems();
                    for (String p : listaProveedores) {
                        form.cmbProveedor.addItem(p);
                    }
                    form.cmbProveedor.setEnabled(true);

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
    }

    private void configurarTabla() {
        form.tblDetalleCompra.setModel(
            new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Producto", "Cantidad", "Precio Compra",
                    "Subtotal", "IVA", "Total",
                    "Acción", "id_producto"
                }
            ) {
                boolean[] canEdit = new boolean[]{
                    false, false, false,
                    false, false, false,
                    false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit[columnIndex];
                }
            }
        );

        // OCULTAR id_producto
        form.tblDetalleCompra.getColumnModel().getColumn(7).setMinWidth(0);
        form.tblDetalleCompra.getColumnModel().getColumn(7).setMaxWidth(0);
        form.tblDetalleCompra.getColumnModel().getColumn(7).setWidth(0);
    }

    private void asignarEventos() {
        form.btnAgregarProducto.addActionListener(this);
        form.btnRegistrarCompra.addActionListener(this);
        form.btnLimpiar.addActionListener(this);

        // ELIMINAR FILA
        form.tblDetalleCompra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = form.tblDetalleCompra.columnAtPoint(e.getPoint());
                int fila = form.tblDetalleCompra.rowAtPoint(e.getPoint());

                if (col == 6 && fila >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(
                        form, "¿Quitar producto?", "Confirmar",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        ((DefaultTableModel) form.tblDetalleCompra.getModel())
                            .removeRow(fila);
                        recalcularTotales();
                    }
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // =====================================================
        // AGREGAR PRODUCTO
        // =====================================================
        if (e.getSource() == form.btnAgregarProducto) {

            // Evitar agregar si aún están cargando los datos
            if (form.cmbProducto.getSelectedItem() == null || form.cmbProducto.getSelectedItem().toString().startsWith("Cargando")) {
                return;
            }

            String cantStr = form.txtCantidad.getText().trim();
            String precioStr = form.txtPrecioCompra.getText().trim();

            if (cantStr.isEmpty() || !cantStr.matches("\\d+")) {
                JOptionPane.showMessageDialog(form, "Ingrese una cantidad válida.");
                return;
            }

            if (precioStr.isEmpty() || !precioStr.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(form, "Ingrese un precio de compra válido.");
                return;
            }

            int cantidad = Integer.parseInt(cantStr);
            double precioCompra = Double.parseDouble(precioStr);

            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(form, "La cantidad debe ser mayor a 0.");
                return;
            }

            if (precioCompra <= 0) {
                JOptionPane.showMessageDialog(form, "El precio debe ser mayor a 0.");
                return;
            }

            // DATOS PRODUCTO
            String[] partes = form.cmbProducto.getSelectedItem()
                .toString().split(" - ");

            int idProd = Integer.parseInt(partes[0].trim());
            String nombreProd = partes[1].trim();

            // VERIFICAR SI YA EXISTE EN TABLA
            int filaExistente = buscarFilaExistente(idProd);

            DefaultTableModel modelo =
                (DefaultTableModel) form.tblDetalleCompra.getModel();

            double subtotalFila = (precioCompra/1.13)*cantidad;
            double ivaFila = subtotalFila * 0.13;
            double totalFila = cantidad * precioCompra;

            if (filaExistente >= 0) {
                // ACTUALIZAR FILA EXISTENTE
                int cantidadActual = Integer.parseInt(
                    modelo.getValueAt(filaExistente, 1).toString()
                );
                int nuevaCantidad = cantidadActual + cantidad;

                double nuevoSubtotal = (precioCompra/1.13)*nuevaCantidad;
                double nuevoIva = nuevoSubtotal * 0.13;
                double nuevoTotal = nuevaCantidad * precioCompra;

                modelo.setValueAt(nuevaCantidad, filaExistente, 1);
                modelo.setValueAt(String.format("%.2f", precioCompra), filaExistente, 2);
                modelo.setValueAt(String.format("%.2f", nuevoSubtotal), filaExistente, 3);
                modelo.setValueAt(String.format("%.2f", nuevoIva), filaExistente, 4);
                modelo.setValueAt(String.format("%.2f", nuevoTotal), filaExistente, 5);

            } else {
                // NUEVA FILA
                modelo.addRow(new Object[]{
                    nombreProd,
                    cantidad,
                    String.format("%.2f", precioCompra),
                    String.format("%.2f", subtotalFila),
                    String.format("%.2f", ivaFila),
                    String.format("%.2f", totalFila),
                    "❌ Eliminar",
                    idProd
                });
            }

            // CORRECCIÓN: Limpiar ambos campos correctamente
            form.txtCantidad.setText("");
            form.txtPrecioCompra.setText("");
            recalcularTotales();
        }

        // =====================================================
        // REGISTRAR COMPRA
        // =====================================================
        if (e.getSource() == form.btnRegistrarCompra) {

            DefaultTableModel modelo =
                (DefaultTableModel) form.tblDetalleCompra.getModel();

            if (modelo.getRowCount() == 0) {
                JOptionPane.showMessageDialog(form, "No hay productos.");
                return;
            }

            // Evitar registro si los proveedores no han cargado
            if (form.cmbProveedor.getSelectedItem() == null || form.cmbProveedor.getSelectedItem().toString().startsWith("Cargando")) {
                return;
            }

            int idProveedor = Integer.parseInt(
                form.cmbProveedor.getSelectedItem()
                    .toString().split(" - ")[0]
            );

            boolean todaBien = true;

            // REGISTRAR CADA PRODUCTO POR SEPARADO
            for (int i = 0; i < modelo.getRowCount(); i++) {

                int idProducto = Integer.parseInt(
                    modelo.getValueAt(i, 7).toString()
                );

                int cantidad = Integer.parseInt(
                    modelo.getValueAt(i, 1).toString()
                );

                double precio = Double.parseDouble(
                    modelo.getValueAt(i, 2).toString().replace(",", ".")
                );

                Compra compra = new Compra();
                compra.setIdProveedor(idProveedor);
                compra.setIdUsuario(3); // Ajustar al usuario de la sesión en el futuro

                boolean resultado =
                    dao.registrarCompra(compra, idProducto, cantidad, precio);

                if (!resultado) {
                    todaBien = false;
                    JOptionPane.showMessageDialog(
                        form,
                        "Error al registrar: " + modelo.getValueAt(i, 0)
                    );
                    break;
                }
            }

            if (todaBien) {
                JOptionPane.showMessageDialog(form, "Compra registrada correctamente.");
                limpiarTodo();
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
    // BUSCAR FILA POR id_producto
    // =====================================================
    private int buscarFilaExistente(int idProducto) {
        DefaultTableModel modelo =
            (DefaultTableModel) form.tblDetalleCompra.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            int prodTabla = Integer.parseInt(
                modelo.getValueAt(i, 7).toString()
            );
            if (prodTabla == idProducto) return i;
        }
        return -1;
    }

    // =====================================================
    // RECALCULAR TOTALES GLOBALES
    // =====================================================
    private void recalcularTotales() {
        DefaultTableModel modelo =
            (DefaultTableModel) form.tblDetalleCompra.getModel();

        double subtotal = 0, iva = 0;

        for (int i = 0; i < modelo.getRowCount(); i++) {
            subtotal += Double.parseDouble(
                modelo.getValueAt(i, 3).toString().replace(",", ".")
            );
            iva += Double.parseDouble(
                modelo.getValueAt(i, 4).toString().replace(",", ".")
            );
        }

        double total = subtotal + iva;

        form.lblSubTotal.setText(String.format("$ %.2f", subtotal));
        form.lblIVA.setText(String.format("$ %.2f", iva));
        form.lblTotal.setText(String.format("$ %.2f", total));
    }

    // =====================================================
    // LIMPIAR
    // =====================================================
    private void limpiarTodo() {
        ((DefaultTableModel) form.tblDetalleCompra.getModel()).setRowCount(0);
        form.txtCantidad.setText(""); 
        form.txtPrecioCompra.setText("");
        recalcularTotales();
    }
}