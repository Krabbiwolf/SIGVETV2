package Controladores.CtrlMaestroDetalle;

import Modelos.MaestroDetalleDAO;
import Vistas.MaestroDetalleVista;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.concurrent.CancellationException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class CtrlMaestroDetalle {

    private final MaestroDetalleVista vista;
    private final MaestroDetalleDAO dao;
    private boolean cargando = false;
    private SwingWorker<DefaultTableModel, Void> maestroWorker;
    private SwingWorker<DefaultTableModel, Void> detalleWorker;

    public CtrlMaestroDetalle(MaestroDetalleVista vista, MaestroDetalleDAO dao) {
        this.vista = vista;
        this.dao = dao;
        configurarTitulos();
        configurarEventos();
        cargarMaestro();
    }

    private void configurarTitulos() {
        switch (vista.getTipo()) {
            case MaestroDetalleVista.CLIENTES_FACTURAS:
                vista.configurarTextos(
                        "Clientes - Facturas/Ventas",
                        "Clientes",
                        "Facturas o ventas del cliente seleccionado",
                        "Buscar por nombre, DUI o teléfono"
                );
                break;
            case MaestroDetalleVista.PROVEEDORES_COMPRAS:
                vista.configurarTextos(
                        "Proveedores - Compras",
                        "Proveedores",
                        "Compras del proveedor seleccionado",
                        "Buscar por proveedor o teléfono"
                );
                break;
            case MaestroDetalleVista.CATEGORIAS_PRODUCTOS:
                vista.configurarTextos(
                        "Categorías - Productos",
                        "Categorías",
                        "Productos de la categoría seleccionada",
                        "Buscar por categoría o descripción"
                );
                break;
            case MaestroDetalleVista.PRODUCTOS_LOTES:
                vista.configurarTextos(
                        "Productos - Lotes",
                        "Productos",
                        "Lotes del producto seleccionado",
                        "Buscar por producto o código de barras"
                );
                break;
            default:
                vista.configurarTextos(
                        "Maestro-Detalle",
                        "Maestro",
                        "Detalle",
                        "Buscar"
                );
                break;
        }
    }

    private void configurarEventos() {
        vista.getBtnBuscar().addActionListener((ActionEvent e) -> cargarMaestro());
        vista.getBtnActualizar().addActionListener((ActionEvent e) -> cargarMaestro());
        vista.getBtnLimpiar().addActionListener((ActionEvent e) -> {
            vista.getTxtBuscar().setText("");
            cargarMaestro();
        });
        vista.getTxtBuscar().addActionListener((ActionEvent e) -> cargarMaestro());

        vista.getTblMaestro().getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting() && !cargando) {
                cargarDetalle();
            }
        });
    }

    private void cargarMaestro() {
        cancelarWorker(maestroWorker);
        cancelarWorker(detalleWorker);

        final String filtro = vista.getTxtBuscar().getText();
        final String tipo = vista.getTipo();

        cargando = true;
        limpiarTabla(vista.getTblMaestro());
        limpiarTabla(vista.getTblDetalle());
        vista.getLblInfo().setText("Selecciona un registro maestro para ver su detalle.");

        maestroWorker = new SwingWorker<DefaultTableModel, Void>() {
            @Override
            protected DefaultTableModel doInBackground() {
                return obtenerModeloMaestro(tipo, filtro);
            }

            @Override
            protected void done() {
                try {
                    DefaultTableModel modelo = get();
                    vista.getTblMaestro().setModel(modelo);
                    ajustarAnchoColumnas(vista.getTblMaestro());
                    limpiarTabla(vista.getTblDetalle());
                    vista.getLblInfo().setText("Selecciona un registro maestro para ver su detalle.");

                    cargando = false;

                    if (modelo.getRowCount() > 0 && modelo.getColumnCount() > 1) {
                        vista.getTblMaestro().setRowSelectionInterval(0, 0);
                        cargarDetalle();
                    }
                } catch (CancellationException ex) {
                    // La carga anterior se canceló porque el usuario hizo otra búsqueda.
                } catch (Exception ex) {
                    cargando = false;
                    JOptionPane.showMessageDialog(obtenerComponenteVista(), "Error al cargar maestro: " + ex.getMessage());
                } finally {
                    maestroWorker = null;
                }
            }
        };

        maestroWorker.execute();
    }

    private DefaultTableModel obtenerModeloMaestro(String tipo, String filtro) {
        switch (tipo) {
            case MaestroDetalleVista.CLIENTES_FACTURAS:
                return dao.listarClientes(filtro);
            case MaestroDetalleVista.PROVEEDORES_COMPRAS:
                return dao.listarProveedores(filtro);
            case MaestroDetalleVista.CATEGORIAS_PRODUCTOS:
                return dao.listarCategorias(filtro);
            case MaestroDetalleVista.PRODUCTOS_LOTES:
                return dao.listarProductos(filtro);
            default:
                return new DefaultTableModel();
        }
    }

    private void cargarDetalle() {
        int filaVista = vista.getTblMaestro().getSelectedRow();
        if (filaVista < 0 || vista.getTblMaestro().getColumnCount() == 0) {
            return;
        }

        int filaModelo = vista.getTblMaestro().convertRowIndexToModel(filaVista);
        Object valorId = vista.getTblMaestro().getModel().getValueAt(filaModelo, 0);
        int id = convertirEntero(valorId);
        if (id <= 0) {
            return;
        }

        cancelarWorker(detalleWorker);

        final String tipo = vista.getTipo();
        final int idMaestro = id;

        limpiarTabla(vista.getTblDetalle());
        vista.getLblInfo().setText("Selecciona un registro maestro para ver su detalle.");

        detalleWorker = new SwingWorker<DefaultTableModel, Void>() {
            @Override
            protected DefaultTableModel doInBackground() {
                return obtenerModeloDetalle(tipo, idMaestro);
            }

            @Override
            protected void done() {
                try {
                    DefaultTableModel detalle = get();
                    vista.getTblDetalle().setModel(detalle);
                    ajustarAnchoColumnas(vista.getTblDetalle());
                    vista.getLblInfo().setText("Detalle cargado para el ID maestro: " + idMaestro
                            + " | Registros encontrados: " + detalle.getRowCount());
                } catch (CancellationException ex) {
                    // La carga anterior se canceló porque el usuario seleccionó otro registro.
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(obtenerComponenteVista(), "Error al cargar detalle: " + ex.getMessage());
                } finally {
                    detalleWorker = null;
                }
            }
        };

        detalleWorker.execute();
    }

    private DefaultTableModel obtenerModeloDetalle(String tipo, int id) {
        switch (tipo) {
            case MaestroDetalleVista.CLIENTES_FACTURAS:
                return dao.listarFacturasVentasPorCliente(id);
            case MaestroDetalleVista.PROVEEDORES_COMPRAS:
                return dao.listarComprasPorProveedor(id);
            case MaestroDetalleVista.CATEGORIAS_PRODUCTOS:
                return dao.listarProductosPorCategoria(id);
            case MaestroDetalleVista.PRODUCTOS_LOTES:
                return dao.listarLotesPorProducto(id);
            default:
                return new DefaultTableModel();
        }
    }

    private void cancelarWorker(SwingWorker<?, ?> worker) {
        if (worker != null && !worker.isDone()) {
            worker.cancel(true);
        }
    }

    private void limpiarTabla(JTable tabla) {
        tabla.clearSelection();
        tabla.setModel(new DefaultTableModel());
        tabla.revalidate();
        tabla.repaint();
    }

    private int convertirEntero(Object valor) {
        if (valor == null) {
            return 0;
        }
        try {
            return Integer.parseInt(valor.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private Component obtenerComponenteVista() {
        return vista instanceof Component ? (Component) vista : null;
    }

    private void ajustarAnchoColumnas(JTable tabla) {
        if (tabla.getColumnCount() == 0) {
            return;
        }

        int columnas = tabla.getColumnCount();
        int[] anchos = new int[columnas];
        int total = 0;

        for (int i = 0; i < columnas; i++) {
            anchos[i] = calcularAnchoColumna(tabla, i);
            total += anchos[i];
        }

        int anchoDisponible = obtenerAnchoDisponible(tabla);
        if (anchoDisponible > 0 && total < anchoDisponible) {
            int extra = anchoDisponible - total - 4;
            if (extra > 0) {
                int extraPorColumna = extra / columnas;
                int sobrante = extra % columnas;
                for (int i = 0; i < columnas; i++) {
                    anchos[i] += extraPorColumna;
                    if (i == columnas - 1) {
                        anchos[i] += sobrante;
                    }
                }
            }
        }

        for (int i = 0; i < columnas; i++) {
            TableColumn columna = tabla.getColumnModel().getColumn(i);
            columna.setPreferredWidth(anchos[i]);
            columna.setWidth(anchos[i]);
        }

        tabla.revalidate();
        tabla.repaint();
    }

    private int calcularAnchoColumna(JTable tabla, int columnaVista) {
        String nombre = tabla.getColumnName(columnaVista);
        int minimo = minimoPorColumna(nombre);
        int maximo = maximoPorColumna(nombre);
        int ancho = minimo;

        TableColumn columna = tabla.getColumnModel().getColumn(columnaVista);
        Object headerValue = columna.getHeaderValue();
        if (headerValue != null) {
            Component header = tabla.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(tabla, headerValue, false, false, -1, columnaVista);
            ancho = Math.max(ancho, header.getPreferredSize().width + 24);
        }

        int filasRevisar = Math.min(tabla.getRowCount(), 80);
        for (int fila = 0; fila < filasRevisar; fila++) {
            TableCellRenderer renderer = tabla.getCellRenderer(fila, columnaVista);
            Component componente = tabla.prepareRenderer(renderer, fila, columnaVista);
            ancho = Math.max(ancho, componente.getPreferredSize().width + 24);
        }

        return Math.min(Math.max(ancho, minimo), maximo);
    }

    private int obtenerAnchoDisponible(JTable tabla) {
        if (tabla.getParent() instanceof JViewport) {
            return tabla.getParent().getWidth();
        }
        return tabla.getWidth();
    }

    private int minimoPorColumna(String nombre) {
        String n = normalizar(nombre);
        if (n.equals("id")) {
            return 65;
        }
        if (n.contains("iva")) {
            return 75;
        }
        if (n.contains("stock") || n.contains("cantidad")) {
            return 95;
        }
        if (n.contains("precio") || n.contains("subtotal") || n.contains("total")) {
            return 105;
        }
        if (n.contains("fecha") || n.contains("vencimiento")) {
            return 125;
        }
        if (n.contains("estado")) {
            return 95;
        }
        return 115;
    }

    private int maximoPorColumna(String nombre) {
        String n = normalizar(nombre);
        if (n.equals("id")) {
            return 85;
        }
        if (n.contains("descripcion")) {
            return 330;
        }
        if (n.contains("producto") || n.contains("proveedor") || n.contains("cliente") || n.contains("categoria")) {
            return 260;
        }
        if (n.contains("correo") || n.contains("direccion")) {
            return 280;
        }
        if (n.contains("fecha") || n.contains("vencimiento")) {
            return 170;
        }
        if (n.contains("codigo") || n.contains("comprobante") || n.contains("telefono") || n.contains("lote")) {
            return 170;
        }
        if (n.contains("estado")) {
            return 125;
        }
        return 210;
    }

    private String normalizar(String texto) {
        return texto == null ? "" : texto.toLowerCase().trim();
    }
}
