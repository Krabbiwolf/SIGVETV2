package Controladores.CtrlProveedor;

import Modelos.Proveedor;
import Modelos.ProveedorDAO;
import Vistas.FrmGestionarProveedores;
import servicios.CloudinaryService; // Igual que en productos

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class CtrlGestionarProveedores implements ActionListener {

    private Proveedor proveedor;
    private FrmGestionarProveedores form;
    private ProveedorDAO dao;
    private ArrayList<Proveedor> proveedores;

    // ── Workers (mismo patrón que GestionProductosController) ──────────────────
    private SwingWorker<ArrayList<Proveedor>, Void> currentLoadWorker;
    private SwingWorker<String, Void>               currentUploadWorker;
    private SwingWorker<ImageIcon, Void>            currentImageLoaderWorker;

    /** URL de la imagen actualmente seleccionada/subida. */
    private String urlImagenActual = "";

    // ───────────────────────────────────────────────────────────────────────────
    public CtrlGestionarProveedores(Proveedor proveedor,
                                    FrmGestionarProveedores form,
                                    ProveedorDAO dao) {
        this.proveedor = proveedor;
        this.form      = form;
        this.dao       = dao;

        configurarTabla();
        asignarListeners();
        form.btnVerDetalle.setEnabled(false); // Deshabilitado hasta que se seleccione una fila
        cargarTabla();
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  CONFIGURACIÓN INICIAL
    // ══════════════════════════════════════════════════════════════════════════

    private void configurarTabla() {
        form.tableProveedores.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Sel", "Id", "Nombre", "Teléfono", "Estado", "RutaImagen"}
        ) {
            final Class<?>[] types = {
                Boolean.class, Object.class, Object.class,
                Object.class,  Object.class, Object.class
            };
            final boolean[] canEdit = {true, false, false, false, false, false};

            @Override public Class<?> getColumnClass(int col)            { return types[col]; }
            @Override public boolean  isCellEditable(int row, int col)   { return canEdit[col]; }
        });

        // Checkbox pequeño
        form.tableProveedores.getColumnModel().getColumn(0).setMaxWidth(40);

        // Ocultar columna RutaImagen
        ocultarColumna(5);
    }

    private void ocultarColumna(int indice) {
        var col = form.tableProveedores.getColumnModel().getColumn(indice);
        col.setMinWidth(0);
        col.setMaxWidth(0);
        col.setWidth(0);
    }

    private void asignarListeners() {
        form.btnGuardar.addActionListener(this);
        form.btnActualizar.addActionListener(this);
        form.btnEliminar.addActionListener(this);
        form.btnLimpiar.addActionListener(this);
        form.btnAgregarImagen.addActionListener(this);
        form.btnVerDetalle.addActionListener(this);
        form.btnExportarCSV.addActionListener(this);

        // Clic en la tabla → cargar fila (ignorar columna checkbox)
        form.tableProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int col = form.tableProveedores.columnAtPoint(e.getPoint());
                if (col != 0) cargarDatosFilaSeleccionada();
            }
        });
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  CARGA ASÍNCRONA DE TABLA  (SwingWorker — igual que productos)
    // ══════════════════════════════════════════════════════════════════════════

    public void cargarTabla() {
        if (currentLoadWorker != null && !currentLoadWorker.isDone()) {
            currentLoadWorker.cancel(true);
        }
        currentLoadWorker = new SwingWorker<ArrayList<Proveedor>, Void>() {
            @Override
            protected ArrayList<Proveedor> doInBackground() throws Exception {
                return dao.listar();
            }

            @Override
            protected void done() {
                try {
                    proveedores = get();
                    DefaultTableModel modelo = (DefaultTableModel) form.tableProveedores.getModel();
                    modelo.setRowCount(0);
                    for (Proveedor p : proveedores) {
                        modelo.addRow(new Object[]{
                            false,
                            p.getId(),
                            p.getNombre(),
                            p.getTelefono(),
                            p.getEstado(),
                            p.getRutaImagen() != null ? p.getRutaImagen() : ""
                        });
                    }
                } catch (CancellationException ex) {
                    System.out.println("Carga de proveedores cancelada.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form,
                        "Error al cargar proveedores: " + ex.getMessage());
                } finally {
                    currentLoadWorker = null;
                }
            }
        };
        currentLoadWorker.execute();
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  IMAGEN — SUBIDA ASÍNCRONA  (SwingWorker en lugar de new Thread)
    // ══════════════════════════════════════════════════════════════════════════

    private void seleccionarImagen() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccionar imagen");
        selector.setFileFilter(
            new FileNameExtensionFilter("Imágenes JPG, PNG, JPEG", "jpg", "jpeg", "png"));

        if (selector.showOpenDialog(form) != JFileChooser.APPROVE_OPTION) return;

        File archivo = selector.getSelectedFile();

        // Feedback inmediato al usuario
        limpiarImagen();
        form.lblMostrarImagen.setText("Subiendo a la nube...");
        form.btnGuardar.setEnabled(false);
        form.btnActualizar.setEnabled(false);

        // Cancelar subida previa si aún está en curso
        if (currentUploadWorker != null && !currentUploadWorker.isDone()) {
            currentUploadWorker.cancel(true);
        }

        currentUploadWorker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                // Mismo servicio que usa GestionProductosController
                CloudinaryService service = new CloudinaryService();
                return service.subirImagen(archivo);
            }

            @Override
            protected void done() {
                try {
                    String url = get();
                    if (url != null && !url.trim().isEmpty()) {
                        urlImagenActual = url;
                        mostrarImagen(url);
                    } else {
                        // Fallback: ruta local si Cloudinary falla
                        urlImagenActual = archivo.getAbsolutePath();
                        mostrarImagen(urlImagenActual);
                        JOptionPane.showMessageDialog(form,
                            "No se pudo subir a Cloudinary. Se usará la ruta local.");
                    }
                } catch (CancellationException ex) {
                    System.out.println("Subida de imagen cancelada.");
                    limpiarImagen();
                } catch (Exception ex) {
                    urlImagenActual = archivo.getAbsolutePath();
                    mostrarImagen(urlImagenActual);
                    JOptionPane.showMessageDialog(form,
                        "Error al subir imagen: " + ex.getMessage());
                    ex.printStackTrace();
                } finally {
                    currentUploadWorker = null;
                    // Restaurar botones según modo
                    restaurarBotones();
                }
            }
        };
        currentUploadWorker.execute();
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  IMAGEN — VISUALIZACIÓN ASÍNCRONA  (SwingWorker — igual que productos)
    // ══════════════════════════════════════════════════════════════════════════

    private void mostrarImagen(String ruta) {
        if (ruta == null || ruta.trim().isEmpty()) {
            limpiarImagen();
            return;
        }

        limpiarImagen();
        form.lblMostrarImagen.setText("Cargando vista previa...");

        if (currentImageLoaderWorker != null && !currentImageLoaderWorker.isDone()) {
            currentImageLoaderWorker.cancel(true);
        }

        currentImageLoaderWorker = new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                ImageIcon icono;
                if (ruta.startsWith("http")) {
                    icono = new ImageIcon(new URL(ruta));
                } else {
                    icono = new ImageIcon(ruta);
                }
                int ancho = form.lblMostrarImagen.getWidth()  > 0 ? form.lblMostrarImagen.getWidth()  : 150;
                int alto  = form.lblMostrarImagen.getHeight() > 0 ? form.lblMostrarImagen.getHeight() : 150;
                Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }

            @Override
            protected void done() {
                try {
                    form.lblMostrarImagen.setText("");
                    form.lblMostrarImagen.setIcon(get());
                } catch (CancellationException ex) {
                    // Cancelado por clic posterior — no hacer nada
                } catch (Exception ex) {
                    limpiarImagen();
                    form.lblMostrarImagen.setText("Error al cargar imagen");
                } finally {
                    currentImageLoaderWorker = null;
                }
            }
        };
        currentImageLoaderWorker.execute();
    }

    private void limpiarImagen() {
        form.lblMostrarImagen.setIcon(null);
        form.lblMostrarImagen.setText("Sin imagen");
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  HELPERS
    // ══════════════════════════════════════════════════════════════════════════

    /** Habilita Guardar o Actualizar según si hay un proveedor cargado en el form. */
    private void restaurarBotones() {
        boolean modoNuevo = form.txtNombre.getText().trim().isEmpty()
                         || form.tableProveedores.getSelectedRow() == -1;
        form.btnGuardar.setEnabled(true);
        form.btnActualizar.setEnabled(!modoNuevo);
        form.btnVerDetalle.setEnabled(!modoNuevo);
    }

    public void limpiarCampos() {
        form.txtNombre.setText("");
        form.txtTelefono.setText("");
        form.cmbEstado.setSelectedIndex(0);
        form.tableProveedores.clearSelection();
        urlImagenActual = "";
        limpiarImagen();
        form.btnGuardar.setEnabled(true);
        form.btnActualizar.setEnabled(false);
        form.btnVerDetalle.setEnabled(false);
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  CARGA DE FILA SELECCIONADA (con imagen asíncrona)
    // ══════════════════════════════════════════════════════════════════════════

    public void cargarDatosFilaSeleccionada() {
        int fila = form.tableProveedores.getSelectedRow();
        if (fila < 0) return;

        form.btnGuardar.setEnabled(false);
        form.btnActualizar.setEnabled(true);
        form.btnVerDetalle.setEnabled(true);

        form.txtNombre.setText(
            form.tableProveedores.getValueAt(fila, 2).toString());
        form.txtTelefono.setText(
            form.tableProveedores.getValueAt(fila, 3).toString());
        form.cmbEstado.setSelectedItem(
            form.tableProveedores.getValueAt(fila, 4).toString());

        Object rutaObj = form.tableProveedores.getValueAt(fila, 5);
        String urlImg  = rutaObj != null ? rutaObj.toString() : "";
        urlImagenActual = urlImg;

        mostrarImagen(urlImg); // ← asíncrono, no bloquea la UI
    }

    // ══════════════════════════════════════════════════════════════════════════
    //  ACTION PERFORMED
    // ══════════════════════════════════════════════════════════════════════════

    @Override
    public void actionPerformed(ActionEvent e) {

        // ── AGREGAR IMAGEN ────────────────────────────────────────────────────
        if (e.getSource() == form.btnAgregarImagen) {
            seleccionarImagen();
        }

        // ── GUARDAR ──────────────────────────────────────────────────────────
        else if (e.getSource() == form.btnGuardar) {
            try {
                String nombre   = form.txtNombre.getText().trim();
                String telefono = form.txtTelefono.getText().trim();
                String estado   = form.cmbEstado.getSelectedItem().toString();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(form, "El nombre del proveedor es obligatorio.");
                    return;
                }
                if (dao.existeProveedor(nombre)) {
                    JOptionPane.showMessageDialog(form, "Ya existe un proveedor con ese nombre.");
                    return;
                }
                if (!telefono.isEmpty() && !telefono.matches("^[267]\\d{3}-?\\d{4}$")) {
                    JOptionPane.showMessageDialog(form, "Formato de teléfono incorrecto. Ej: 7123-4567.");
                    return;
                }

                proveedor.setNombre(nombre);
                proveedor.setTelefono(telefono);
                proveedor.setEstado(estado);
                proveedor.setRutaImagen(urlImagenActual);

                if (dao.registrar(proveedor)) {
                    JOptionPane.showMessageDialog(form, "¡Proveedor guardado exitosamente!");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(form, "Error al guardar el proveedor.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "Error en los datos: " + ex.getMessage());
            }
        }

        // ── ACTUALIZAR ───────────────────────────────────────────────────────
        else if (e.getSource() == form.btnActualizar) {
            int filaSeleccionada = form.tableProveedores.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(form, "Seleccione un registro para actualizar.");
                return;
            }
            try {
                int    id       = Integer.parseInt(
                    form.tableProveedores.getValueAt(filaSeleccionada, 1).toString());
                String nombre   = form.txtNombre.getText().trim();
                String telefono = form.txtTelefono.getText().trim();
                String estado   = form.cmbEstado.getSelectedItem().toString();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(form, "El nombre no puede estar vacío.");
                    return;
                }
                if (!telefono.isEmpty() && !telefono.matches("^[267]\\d{3}-?\\d{4}$")) {
                    JOptionPane.showMessageDialog(form, "Formato de teléfono incorrecto. Ej: 7123-4567.");
                    return;
                }

                proveedor.setId(id);
                proveedor.setNombre(nombre);
                proveedor.setTelefono(telefono);
                proveedor.setEstado(estado);
                proveedor.setRutaImagen(urlImagenActual);

                if (dao.actualizar(proveedor)) {
                    JOptionPane.showMessageDialog(form, "Proveedor actualizado con éxito.");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(form, "Error al actualizar el proveedor.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(form, "Error al procesar los datos: " + ex.getMessage());
            }
        }

        // ── ELIMINAR / INACTIVAR MÚLTIPLE ────────────────────────────────────
        else if (e.getSource() == form.btnEliminar) {
            DefaultTableModel modelo = (DefaultTableModel) form.tableProveedores.getModel();
            boolean hayMarcados = false;
            for (int i = 0; i < modelo.getRowCount(); i++) {
                if (Boolean.TRUE.equals(modelo.getValueAt(i, 0))) { hayMarcados = true; break; }
            }
            if (!hayMarcados) {
                JOptionPane.showMessageDialog(form,
                    "Marque al menos un proveedor con la casilla para eliminar/desactivar.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(form,
                "¿Desea eliminar los proveedores seleccionados?\n" +
                "(Si tienen compras asociadas serán INACTIVADOS automáticamente).",
                "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean huboInactivaciones = false;
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    if (Boolean.TRUE.equals(modelo.getValueAt(i, 0))) {
                        int id = Integer.parseInt(modelo.getValueAt(i, 1).toString());
                        if (!dao.eliminar(id)) {
                            dao.inactivar(id);
                            huboInactivaciones = true;
                        }
                    }
                }
                JOptionPane.showMessageDialog(form, huboInactivaciones
                    ? "Proceso completado.\nAlgunos proveedores fueron INACTIVADOS por tener compras asociadas."
                    : "Proveedores eliminados correctamente.");
                limpiarCampos();
                cargarTabla();
            }
        }

        // ── VER DETALLE ───────────────────────────────────────────────────────
        else if (e.getSource() == form.btnVerDetalle) {
            int fila = form.tableProveedores.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(form, "Seleccione una fila para ver los detalles.");
                return;
            }

            String id       = form.tableProveedores.getValueAt(fila, 1).toString();
            String nombre   = form.tableProveedores.getValueAt(fila, 2).toString();
            String telefono = form.tableProveedores.getValueAt(fila, 3).toString();
            String estado   = form.tableProveedores.getValueAt(fila, 4).toString();
            String urlImg   = form.tableProveedores.getValueAt(fila, 5) != null
                              ? form.tableProveedores.getValueAt(fila, 5).toString() : "";

            String mensaje = String.format(
                "Datos del Proveedor:%n%nID: %s%nNombre: %s%nTeléfono: %s%nEstado: %s",
                id, nombre, telefono, estado);

            // Cargar imagen del detalle en segundo plano
            new SwingWorker<ImageIcon, Void>() {
                @Override
                protected ImageIcon doInBackground() throws Exception {
                    if (urlImg.isEmpty()) return null;
                    Image img = new ImageIcon(new URL(urlImg))
                                    .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    return new ImageIcon(img);
                }

                @Override
                protected void done() {
                    ImageIcon icono = null;
                    try { icono = get(); } catch (Exception ignored) {}
                    JOptionPane.showMessageDialog(form, mensaje,
                        "Detalles de " + nombre, JOptionPane.INFORMATION_MESSAGE, icono);
                }
            }.execute();
        }

        // ── EXPORTAR CSV ──────────────────────────────────────────────────────
        else if (e.getSource() == form.btnExportarCSV) {
            if (form.tableProveedores.getRowCount() == 0) {
                JOptionPane.showMessageDialog(form, "No hay datos para exportar.");
                return;
            }
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("Archivo CSV (*.csv)", "csv"));
            if (fc.showSaveDialog(form) == JFileChooser.APPROVE_OPTION) {
                String ruta = fc.getSelectedFile().getAbsolutePath();
                if (!ruta.toLowerCase().endsWith(".csv")) ruta += ".csv";
                try (FileWriter fw = new FileWriter(ruta)) {
                    DefaultTableModel m = (DefaultTableModel) form.tableProveedores.getModel();
                    // Cabeceras (sin Sel ni RutaImagen)
                    for (int i = 1; i < m.getColumnCount() - 1; i++) {
                        fw.write(m.getColumnName(i) + (i == m.getColumnCount() - 2 ? "" : ","));
                    }
                    fw.write("\n");
                    // Filas
                    for (int i = 0; i < m.getRowCount(); i++) {
                        for (int j = 1; j < m.getColumnCount() - 1; j++) {
                            String dato = m.getValueAt(i, j) != null ? m.getValueAt(i, j).toString() : "";
                            if (dato.contains(",")) dato = "\"" + dato + "\"";
                            fw.write(dato + (j == m.getColumnCount() - 2 ? "" : ","));
                        }
                        fw.write("\n");
                    }
                    JOptionPane.showMessageDialog(form, "Datos exportados correctamente.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, "Error al exportar: " + ex.getMessage());
                }
            }
        }

        // ── LIMPIAR ───────────────────────────────────────────────────────────
        else if (e.getSource() == form.btnLimpiar) {
            limpiarCampos();
        }
    }
}