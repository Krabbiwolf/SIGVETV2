package Controladores.ctrlProductos;

import Modelos.AjusteInventarioDAO;
import Modelos.AjusteInventarioDAO.ResultadoAjuste;
import Modelos.LoteInventario;
import Modelos.SesionUsuario;
import Vistas.FrmAjusteInventario;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

public class AjusteInventarioController {

    private final AjusteInventarioDAO dao;
    private final FrmAjusteInventario vista;
    private SwingWorker<ArrayList<LoteInventario>, Void> currentLotesWorker;
    private SwingWorker<ArrayList<Object[]>, Void> currentAjustesWorker;

    public AjusteInventarioController(FrmAjusteInventario vista) {
        this.dao = new AjusteInventarioDAO();
        this.vista = vista;
        configurarVista();
        cargarTiposAjuste();
        cargarLotesProductos(); // asíncrono con indicador visual
        listarAjustesTabla();   // asíncrono
        agregarEventos();
        actualizarStockActual(); // este es rápido
    }

    private void configurarVista() {
        vista.setTitle("Ajuste de Inventario");
        vista.txtCantidad.setText("1");
        vista.tblAjustes.setModel(crearModeloTabla());
        validarSoloNumeros(vista.txtCantidad);
        vista.setCursor(Cursor.getDefaultCursor());
    }

    private void agregarEventos() {
        vista.btnRegistrarAjuste.addActionListener(e -> registrarAjuste());
        vista.btnLimpiar.addActionListener(e -> cancelar());
        vista.cboLoteProducto.addActionListener(e -> actualizarStockActual());
    }

    private void cargarTiposAjuste() {
        vista.cboTipoMovimiento.removeAllItems();
        vista.cboTipoMovimiento.addItem("Ingreso");
        vista.cboTipoMovimiento.addItem("Salida");
    }

    // ================== CARGA ASÍNCRONA DE LOTES CON CURSOR DE ESPERA ==================
    private void cargarLotesProductos() {
        if (currentLotesWorker != null && !currentLotesWorker.isDone()) {
            currentLotesWorker.cancel(true);
        }
        // Cambiar cursor a espera
        vista.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        currentLotesWorker = new SwingWorker<ArrayList<LoteInventario>, Void>() {
            @Override
            protected ArrayList<LoteInventario> doInBackground() throws Exception {
                return dao.listarLotesActivos();
            }

            @Override
            protected void done() {
                try {
                    ArrayList<LoteInventario> lotes = get();
                    vista.cboLoteProducto.removeAllItems();
                    if (lotes != null && !lotes.isEmpty()) {
                        for (LoteInventario lote : lotes) {
                            vista.cboLoteProducto.addItem(lote);
                        }
                        actualizarStockActual();
                    } else {
                        vista.lblStockActual.setText("0");
                        String error = dao.getUltimoError();
                        if (error != null && !error.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(vista,
                                    "No se cargaron lotes.\nDetalle: " + error,
                                    "Ajuste de Inventario", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Error al cargar lotes: " + ex.getMessage());
                    ex.printStackTrace();
                } finally {
                    vista.setCursor(Cursor.getDefaultCursor());
                    currentLotesWorker = null;
                }
            }
        };
        currentLotesWorker.execute();
    }

    // ================== CARGA ASÍNCRONA DE TABLA DE AJUSTES ==================
    private void listarAjustesTabla() {
        if (currentAjustesWorker != null && !currentAjustesWorker.isDone()) {
            currentAjustesWorker.cancel(true);
        }
        currentAjustesWorker = new SwingWorker<ArrayList<Object[]>, Void>() {
            @Override
            protected ArrayList<Object[]> doInBackground() throws Exception {
                return dao.listarAjustesInventario();
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Object[]> ajustes = get();
                    DefaultTableModel modelo = crearModeloTabla();
                    if (ajustes != null) {
                        for (Object[] fila : ajustes) {
                            modelo.addRow(fila);
                        }
                    }
                    vista.tblAjustes.setModel(modelo);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Error al listar ajustes: " + ex.getMessage());
                    ex.printStackTrace();
                } finally {
                    currentAjustesWorker = null;
                }
            }
        };
        currentAjustesWorker.execute();
    }

    private void actualizarStockActual() {
        LoteInventario lote = obtenerLoteSeleccionado();
        if (lote == null) {
            vista.lblStockActual.setText("0");
            return;
        }
        // Esta consulta es rápida, pero si se vuelve pesada se puede poner también en worker.
        LoteInventario loteActualizado = dao.obtenerLotePorId(lote.getIdLote());
        if (loteActualizado != null) {
            lote.setStockInicial(loteActualizado.getStockInicial());
            lote.setStockActual(loteActualizado.getStockActual());
            vista.lblStockActual.setText(String.valueOf(loteActualizado.getStockActual()));
            vista.cboLoteProducto.repaint();
        } else {
            vista.lblStockActual.setText(String.valueOf(lote.getStockActual()));
        }
    }

    private LoteInventario obtenerLoteSeleccionado() {
        Object seleccionado = vista.cboLoteProducto.getSelectedItem();
        return (seleccionado instanceof LoteInventario) ? (LoteInventario) seleccionado : null;
    }

    private DefaultTableModel crearModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID Movimiento");
        modelo.addColumn("Tipo");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Fecha");
        modelo.addColumn("Producto");
        modelo.addColumn("Lote");
        modelo.addColumn("Stock Inicial");
        modelo.addColumn("Stock Actual");
        modelo.addColumn("Precio Compra");
        modelo.addColumn("Precio Venta");
        modelo.addColumn("Motivo");
        modelo.addColumn("ID Usuario");
        return modelo;
    }

    private void registrarAjuste() {
        LoteInventario lote = obtenerLoteSeleccionado();
        if (lote == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione el producto y lote que desea ajustar.");
            return;
        }
        if (vista.cboTipoMovimiento.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione el tipo de ajuste.");
            return;
        }
        int idUsuario = SesionUsuario.getIdUsuarioActual();
        if (idUsuario <= 0) {
            JOptionPane.showMessageDialog(vista,
                    "No se encontró el usuario de la sesión.\nCierra sesión e inicia nuevamente.",
                    "Ajuste de Inventario", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String cantidadTexto = vista.txtCantidad.getText().trim();
        String motivo = vista.txtMotivoAjuste.getText().trim();
        if (cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese la cantidad del ajuste.");
            vista.txtCantidad.requestFocus();
            return;
        }
        if (motivo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el motivo del ajuste.");
            vista.txtMotivoAjuste.requestFocus();
            return;
        }
        try {
            int cantidad = Integer.parseInt(cantidadTexto);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(vista, "La cantidad debe ser mayor que cero.");
                vista.txtCantidad.requestFocus();
                return;
            }
            actualizarStockActual();
            int stockActual = Integer.parseInt(vista.lblStockActual.getText());
            String tipoAjuste = vista.cboTipoMovimiento.getSelectedItem().toString();
            if (esTipoSalida(tipoAjuste) && cantidad > stockActual) {
                JOptionPane.showMessageDialog(vista,
                        "No puede restar " + cantidad + " unidades porque el stock actual es " + stockActual + ".");
                vista.txtCantidad.requestFocus();
                return;
            }
            ResultadoAjuste resultado = dao.registrarAjusteInventario(
                    lote.getIdLote(),
                    tipoAjuste,
                    cantidad,
                    motivo,
                    idUsuario,
                    SesionUsuario.getNombreUsuarioActual()
            );
            if (resultado.isCorrecto()) {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje());
                cargarLotesProductos();   // recarga asíncrona
                listarAjustesTabla();     // recarga asíncrona
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, resultado.getMensaje());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "La cantidad debe ser un número entero válido.");
        }
    }

    private boolean esTipoSalida(String tipoAjuste) {
        if (tipoAjuste == null) return false;
        String tipo = tipoAjuste.trim().toUpperCase();
        return tipo.contains("SALIDA") || tipo.contains("RESTA");
    }

    private void limpiarCampos() {
        if (vista.cboLoteProducto.getItemCount() > 0) vista.cboLoteProducto.setSelectedIndex(0);
        if (vista.cboTipoMovimiento.getItemCount() > 0) vista.cboTipoMovimiento.setSelectedIndex(0);
        vista.txtCantidad.setText("1");
        vista.txtMotivoAjuste.setText("");
        actualizarStockActual();
        vista.txtCantidad.requestFocus();
    }

    private void cancelar() {
        limpiarCampos();
        vista.dispose();
    }

    private void validarSoloNumeros(javax.swing.JTextField campo) {
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
        });
    }
}