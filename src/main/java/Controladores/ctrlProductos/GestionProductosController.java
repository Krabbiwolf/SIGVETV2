package Controladores.ctrlProductos;

import Modelos.Producto;
import Modelos.ProductosDAO;
import Modelos.SesionUsuario;
import Vistas.FrmGestionarProductos;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.concurrent.CancellationException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import servicios.CloudinaryService;

public class GestionProductosController {

    private static final double IVA_FIJO = 13.0;

    private final ProductosDAO dao;
    private final FrmGestionarProductos vista;
    private SwingWorker<ArrayList<Producto>, Void> currentLoadWorker;
    private SwingWorker<String, Void> currentUploadWorker;
    private SwingWorker<ArrayList<String>, Void> currentCategoriasWorker;
    private SwingWorker<ImageIcon, Void> currentImageLoaderWorker;

    public GestionProductosController(FrmGestionarProductos vista) {
        this.dao = new ProductosDAO();
        this.vista = vista;
        configurarVista();
        cargarEstados();
        cargarCategoriasAsync();
        listarProductosEnTabla();
        agregarEventos();
        modoNuevo();
        
        if (!SesionUsuario.tienePermiso("EDICION_PRODUCTOS")) {
            vista.btnGuardar.setVisible(false);
            vista.btnActualizar.setVisible(false);
            vista.btnEliminar.setVisible(false);
            // Si el form se llama "form" en categorías, cambia "vista" por "form"
        }
        if (!SesionUsuario.tienePermiso("EXPORTAR_PRODUCTOS")) {
            if (vista.btnExportarCSV != null) vista.btnExportarCSV.setVisible(false);
        }
    }

    private void configurarVista() {
        vista.setTitle("Gestionar Productos");
        vista.txtIdProducto.setEditable(false);
        vista.txtIdProducto.setFocusable(false);
        vista.txtRuta.setEditable(false);
        vista.txtRuta.setFocusable(false);
        vista.lblMostrarImagen.setText("Sin imagen");
        vista.lblMostrarImagen.setHorizontalAlignment(SwingConstants.CENTER);
        vista.lblMostrarImagen.setVerticalAlignment(SwingConstants.CENTER);
        vista.tblProductos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        vista.tblProductos.setRowHeight(32);
    }

    private void agregarEventos() {
        quitarEventosExistentes(vista.btnGuardar);
        quitarEventosExistentes(vista.btnActualizar);
        quitarEventosExistentes(vista.btnEliminar);
        quitarEventosExistentes(vista.btnExportarCSV);
        quitarEventosExistentes(vista.btnVerDetalle);
        quitarEventosExistentes(vista.btnBuscar);
        quitarEventosExistentes(vista.btnLimpiar);
        quitarEventosExistentes(vista.btnAgregarImagen);
        quitarEventosExistentes(vista.cboFiltroBusqueda);

        vista.btnGuardar.addActionListener(e -> guardarProducto());
        vista.btnActualizar.addActionListener(e -> actualizarProducto());
        vista.btnEliminar.addActionListener(e -> eliminarProductosSeleccionados());
        vista.btnExportarCSV.addActionListener(e -> exportarProductosCSV());
        vista.btnVerDetalle.addActionListener(e -> verDetalleProducto());
        vista.btnBuscar.addActionListener(e -> buscarProductosEnTabla());
        vista.btnLimpiar.addActionListener(e -> limpiarCampos());
        vista.btnAgregarImagen.addActionListener(e -> seleccionarImagen());
        vista.cboFiltroBusqueda.addActionListener(e -> buscarProductosEnTabla());

        vista.txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarProductosEnTabla();
            }
        });

        vista.tblProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                seleccionarProductoTabla();
            }
        });

        vista.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
            @Override
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent e) {
                listarProductosEnTabla();
            }
        });
    }

    private void quitarEventosExistentes(javax.swing.AbstractButton boton) {
        for (ActionListener listener : boton.getActionListeners()) {
            boton.removeActionListener(listener);
        }
    }

    private void quitarEventosExistentes(javax.swing.JComboBox<?> combo) {
        for (ActionListener listener : combo.getActionListeners()) {
            combo.removeActionListener(listener);
        }
    }

    private void modoNuevo() {
        vista.btnGuardar.setEnabled(true);
        vista.btnActualizar.setEnabled(false);
        vista.btnVerDetalle.setEnabled(false);
    }

    private void modoEdicion() {
        vista.btnGuardar.setEnabled(false);
        vista.btnActualizar.setEnabled(true);
        vista.btnVerDetalle.setEnabled(true);
    }

    private void cargarCategoriasAsync() {
        if (currentCategoriasWorker != null && !currentCategoriasWorker.isDone()) {
            currentCategoriasWorker.cancel(true);
        }
        currentCategoriasWorker = new SwingWorker<ArrayList<String>, Void>() {
            @Override
            protected ArrayList<String> doInBackground() throws Exception {
                return dao.listarCategoriasCombo();
            }

            @Override
            protected void done() {
                try {
                    ArrayList<String> categorias = get();
                    vista.cboCategoria.removeAllItems();
                    if (categorias != null) {
                        for (String categoria : categorias) {
                            vista.cboCategoria.addItem(categoria);
                        }
                    }
                } catch (CancellationException e) {
                    System.out.println("Carga de categorías cancelada.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Error al cargar categorías: " + ex.getMessage());
                    ex.printStackTrace();
                } finally {
                    currentCategoriasWorker = null;
                }
            }
        };
        currentCategoriasWorker.execute();
    }

    private void cargarEstados() {
        vista.cboEstado.removeAllItems();
        vista.cboEstado.addItem("Activo");
        vista.cboEstado.addItem("Inactivo");

        vista.cboFiltroBusqueda.removeAllItems();
        vista.cboFiltroBusqueda.addItem("Todos");
        vista.cboFiltroBusqueda.addItem("Activo");
        vista.cboFiltroBusqueda.addItem("Inactivo");
    }

    // ================== CARGA DE IMAGEN ASÍNCRONA ==================
    private void seleccionarImagen() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar imagen");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes JPG, PNG, JPEG", "jpg", "jpeg", "png");
        chooser.setFileFilter(filtro);
        
        int opcion = chooser.showOpenDialog(vista);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            
            // Preparar UI para la carga
            vista.lblMostrarImagen.setIcon(null);
            vista.lblMostrarImagen.setText("Subiendo a la nube...");
            vista.btnGuardar.setEnabled(false);
            vista.btnActualizar.setEnabled(false);

            if (currentUploadWorker != null && !currentUploadWorker.isDone()) {
                currentUploadWorker.cancel(true);
            }
            
            currentUploadWorker = new SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() throws Exception {
                    CloudinaryService service = new CloudinaryService();
                    return service.subirImagen(archivo);
                }

                @Override
                protected void done() {
                    try {
                        String urlImagen = get();
                        if (urlImagen != null && !urlImagen.trim().isEmpty()) {
                            vista.txtRuta.setText(urlImagen);
                            mostrarImagen(urlImagen); // Muestra la imagen descargándola asíncronamente
                        } else {
                            vista.txtRuta.setText(archivo.getAbsolutePath());
                            mostrarImagen(archivo.getAbsolutePath());
                            JOptionPane.showMessageDialog(vista, "No se pudo subir a Cloudinary. Se usará la ruta local.");
                        }
                    } catch (CancellationException e) {
                        System.out.println("Subida de imagen cancelada.");
                        limpiarImagen();
                    } catch (Exception ex) {
                        vista.txtRuta.setText(archivo.getAbsolutePath());
                        mostrarImagen(archivo.getAbsolutePath());
                        JOptionPane.showMessageDialog(vista, "Error al subir imagen: " + ex.getMessage());
                        ex.printStackTrace();
                    } finally {
                        currentUploadWorker = null;
                        // Restaurar los botones según el modo
                        if (vista.txtIdProducto.getText().trim().isEmpty()) {
                            vista.btnGuardar.setEnabled(true);
                        } else {
                            vista.btnActualizar.setEnabled(true);
                        }
                    }
                }
            };
            currentUploadWorker.execute();
        }
    }

    private void mostrarImagen(String ruta) {
        if (ruta == null || ruta.trim().isEmpty()) {
            limpiarImagen();
            return;
        }

        vista.lblMostrarImagen.setIcon(null);
        vista.lblMostrarImagen.setText("Cargando vista previa...");

        if (currentImageLoaderWorker != null && !currentImageLoaderWorker.isDone()) {
            currentImageLoaderWorker.cancel(true);
        }

        currentImageLoaderWorker = new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                ImageIcon iconoOriginal;
                if (ruta.startsWith("http")) {
                    URL url = new URL(ruta);
                    iconoOriginal = new ImageIcon(url);
                } else {
                    iconoOriginal = new ImageIcon(ruta);
                }
                
                int ancho = vista.lblMostrarImagen.getWidth() > 0 ? vista.lblMostrarImagen.getWidth() : 250;
                int alto = vista.lblMostrarImagen.getHeight() > 0 ? vista.lblMostrarImagen.getHeight() : 250;
                
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                return new ImageIcon(imagenEscalada);
            }

            @Override
            protected void done() {
                try {
                    ImageIcon icon = get();
                    vista.lblMostrarImagen.setText("");
                    vista.lblMostrarImagen.setIcon(icon);
                } catch (CancellationException e) {
                    // Carga cancelada por un clic posterior
                } catch (Exception e) {
                    limpiarImagen();
                    vista.lblMostrarImagen.setText("Error al cargar vista previa");
                } finally {
                    currentImageLoaderWorker = null;
                }
            }
        };
        currentImageLoaderWorker.execute();
    }

    private void limpiarImagen() {
        vista.lblMostrarImagen.setIcon(null);
        vista.lblMostrarImagen.setText("Sin imagen");
    }

    private DefaultTableModel crearModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Boolean.class;
                return Object.class;
            }
        };
        modelo.addColumn("Seleccionar");
        modelo.addColumn("ID");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        modelo.addColumn("IVA");
        modelo.addColumn("Imagen");
        modelo.addColumn("Estado");
        modelo.addColumn("ID Categoría");
        return modelo;
    }

    private void llenarTabla(ArrayList<Producto> productos) {
        DefaultTableModel modelo = crearModeloTabla();
        if (productos != null) {
            for (Producto p : productos) {
                modelo.addRow(new Object[]{
                    false,
                    p.getIdProducto(),
                    p.getCodigoBarras(),
                    p.getNombre(),
                    p.getDescripcionTecnica(),
                    IVA_FIJO,
                    p.getImagenUrl(),
                    p.getEstado(),
                    p.getIdCategoria()
                });
            }
        }
        vista.tblProductos.setModel(modelo);
        vista.tblProductos.setAutoCreateRowSorter(true);
        configurarColumnasTabla();
    }

    private void configurarColumnasTabla() {
        fijarAnchoColumna(0, 80);
        fijarAnchoColumna(1, 45);
        fijarAnchoColumna(2, 100);
        fijarAnchoColumna(3, 145);
        fijarAnchoColumna(5, 45);
        fijarAnchoColumna(7, 75);
        ocultarColumna(6);
        ocultarColumna(8);
    }

    private void fijarAnchoColumna(int indice, int ancho) {
        if (vista.tblProductos.getColumnModel().getColumnCount() > indice) {
            TableColumn columna = vista.tblProductos.getColumnModel().getColumn(indice);
            columna.setPreferredWidth(ancho);
        }
    }

    private void ocultarColumna(int indice) {
        if (vista.tblProductos.getColumnModel().getColumnCount() > indice) {
            TableColumn columna = vista.tblProductos.getColumnModel().getColumn(indice);
            columna.setMinWidth(0);
            columna.setMaxWidth(0);
            columna.setPreferredWidth(0);
        }
    }

    private void listarProductosEnTabla() {
        cargarProductosAsync("", "Todos");
    }

    private void buscarProductosEnTabla() {
        String texto = vista.txtBuscar.getText().trim();
        String estado = vista.cboFiltroBusqueda.getSelectedItem() == null ? "Todos" : vista.cboFiltroBusqueda.getSelectedItem().toString();
        cargarProductosAsync(texto, estado);
    }

    private void cargarProductosAsync(String texto, String estado) {
        if (currentLoadWorker != null && !currentLoadWorker.isDone()) {
            currentLoadWorker.cancel(true);
        }
        currentLoadWorker = new SwingWorker<ArrayList<Producto>, Void>() {
            @Override
            protected ArrayList<Producto> doInBackground() throws Exception {
                return dao.buscarProductos(texto, estado);
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Producto> productos = get();
                    llenarTabla(productos == null ? new ArrayList<>() : productos);
                } catch (CancellationException e) {
                    System.out.println("Carga de productos cancelada.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista,
                            "Error al cargar productos: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                } finally {
                    currentLoadWorker = null;
                }
            }
        };
        currentLoadWorker.execute();
    }

    private String valorModelo(DefaultTableModel modelo, int fila, int columna) {
        Object valor = modelo.getValueAt(fila, columna);
        return valor == null ? "" : valor.toString();
    }

    private void seleccionarProductoTabla() {
        int filaVista = vista.tblProductos.getSelectedRow();
        if (filaVista < 0) return;
        
        int columnaClic = vista.tblProductos.getSelectedColumn();
        if (columnaClic == 0) return; // Evitar cargar si se hace clic en el Checkbox

        int filaModelo = vista.tblProductos.convertRowIndexToModel(filaVista);
        DefaultTableModel modelo = (DefaultTableModel) vista.tblProductos.getModel();

        vista.txtIdProducto.setText(valorModelo(modelo, filaModelo, 1));
        vista.txtCodigoBarras.setText(valorModelo(modelo, filaModelo, 2));
        vista.txtNombre.setText(valorModelo(modelo, filaModelo, 3));
        vista.txtDescripcionTecnica.setText(valorModelo(modelo, filaModelo, 4));
        String rutaImagen = valorModelo(modelo, filaModelo, 6);
        vista.txtRuta.setText(rutaImagen);
        
        mostrarImagen(rutaImagen); // Se carga asíncronamente gracias a la mejora
        
        vista.cboEstado.setSelectedItem(valorModelo(modelo, filaModelo, 7));
        try {
            int idCategoria = Integer.parseInt(valorModelo(modelo, filaModelo, 8));
            seleccionarCategoriaPorId(idCategoria);
        } catch (NumberFormatException e) {
            if (vista.cboCategoria.getItemCount() > 0) vista.cboCategoria.setSelectedIndex(0);
        }
        modoEdicion();
    }

    private void seleccionarCategoriaPorId(int idCategoria) {
        for (int i = 0; i < vista.cboCategoria.getItemCount(); i++) {
            String item = vista.cboCategoria.getItemAt(i).toString();
            if (item.startsWith(idCategoria + " - ")) {
                vista.cboCategoria.setSelectedIndex(i);
                break;
            }
        }
    }

    private int obtenerIdCategoria() {
        if (vista.cboCategoria.getSelectedItem() == null) {
            throw new NumberFormatException("Categoría no seleccionada");
        }
        String categoriaSeleccionada = vista.cboCategoria.getSelectedItem().toString();
        String[] partes = categoriaSeleccionada.split(" - ");
        return Integer.parseInt(partes[0]);
    }

    private String obtenerRutaImagen() {
        return vista.txtRuta.getText().trim();
    }

    private String generarCodigoAutomatico() {
        return "PRD-" + System.currentTimeMillis();
    }

    private Producto leerProductoDesdeFormulario(boolean requiereId) {
        String codigoBarras = vista.txtCodigoBarras.getText().trim();
        String nombre = vista.txtNombre.getText().trim();
        String descripcion = vista.txtDescripcionTecnica.getText().trim();

        if (requiereId && vista.txtIdProducto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto de la tabla.");
            return null;
        }
        if (codigoBarras.isEmpty()) {
            codigoBarras = generarCodigoAutomatico();
            vista.txtCodigoBarras.setText(codigoBarras);
        }
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el nombre del producto.");
            vista.txtNombre.requestFocus();
            return null;
        }
        if (vista.cboCategoria.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione una categoría.");
            return null;
        }

        Producto producto = new Producto();
        if (requiereId) {
            producto.setIdProducto(Integer.parseInt(vista.txtIdProducto.getText().trim()));
        }
        producto.setCodigoBarras(codigoBarras);
        producto.setNombre(nombre);
        producto.setDescripcionTecnica(descripcion);
        producto.setPorcentajeIvaDetalle(IVA_FIJO);
        producto.setImagenUrl(obtenerRutaImagen());
        producto.setEstado(vista.cboEstado.getSelectedItem() == null ? "Activo" : vista.cboEstado.getSelectedItem().toString());
        producto.setIdCategoria(obtenerIdCategoria());
        return producto;
    }

    private void guardarProducto() {
        try {
            Producto producto = leerProductoDesdeFormulario(false);
            if (producto == null) return;

            boolean guardado = dao.guardarProducto(producto);
            if (guardado) {
                JOptionPane.showMessageDialog(vista, "Producto guardado correctamente con IVA fijo del 13%.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo guardar el producto.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Error numérico. Revise la categoría seleccionada.");
        }
    }

    private void actualizarProducto() {
        try {
            Producto producto = leerProductoDesdeFormulario(true);
            if (producto == null) return;

            boolean actualizado = dao.actualizarProducto(producto);
            if (actualizado) {
                JOptionPane.showMessageDialog(vista, "Producto actualizado correctamente con IVA fijo del 13%.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo actualizar el producto.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Error numérico. Revise los datos seleccionados.");
        }
    }

    private ArrayList<Integer> obtenerIdsMarcadosOSeleccionados() {
        ArrayList<Integer> ids = new ArrayList<>();
        DefaultTableModel modelo = (DefaultTableModel) vista.tblProductos.getModel();

        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object marcado = modelo.getValueAt(i, 0);
            if (Boolean.TRUE.equals(marcado)) {
                ids.add(Integer.parseInt(valorModelo(modelo, i, 1)));
            }
        }

        if (ids.isEmpty()) {
            int[] filasSeleccionadas = vista.tblProductos.getSelectedRows();
            for (int filaVista : filasSeleccionadas) {
                int filaModelo = vista.tblProductos.convertRowIndexToModel(filaVista);
                ids.add(Integer.parseInt(valorModelo(modelo, filaModelo, 1)));
            }
        }
        return ids;
    }

    private void eliminarProductosSeleccionados() {
        ArrayList<Integer> ids = obtenerIdsMarcadosOSeleccionados();
        if (ids.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Marque uno o varios productos con el checkbox o seleccione filas de la tabla.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(vista,
                "¿Desea desactivar " + ids.size() + " producto(s) seleccionado(s)?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean eliminado = dao.eliminarProductos(ids);
            if (eliminado) {
                JOptionPane.showMessageDialog(vista, "Producto(s) desactivado(s) correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudieron desactivar los productos seleccionados.");
            }
        }
    }

    private int obtenerFilaDetalle() {
        int filaVista = vista.tblProductos.getSelectedRow();
        if (filaVista >= 0) {
            return vista.tblProductos.convertRowIndexToModel(filaVista);
        }
        DefaultTableModel modelo = (DefaultTableModel) vista.tblProductos.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            if (Boolean.TRUE.equals(modelo.getValueAt(i, 0))) {
                return i;
            }
        }
        return -1;
    }

    private void verDetalleProducto() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblProductos.getModel();
        int filaModelo = obtenerFilaDetalle();
        if (filaModelo < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto para ver el detalle.");
            return;
        }

        String id = valorModelo(modelo, filaModelo, 1);
        String codigo = valorModelo(modelo, filaModelo, 2);
        String nombre = valorModelo(modelo, filaModelo, 3);
        String descripcion = valorModelo(modelo, filaModelo, 4);
        String iva = valorModelo(modelo, filaModelo, 5);
        String imagen = valorModelo(modelo, filaModelo, 6);
        String estado = valorModelo(modelo, filaModelo, 7);
        String idCategoria = valorModelo(modelo, filaModelo, 8);

        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setPreferredSize(new Dimension(560, 330));

        JLabel lblImagen = new JLabel("Cargando...", SwingConstants.CENTER);
        lblImagen.setPreferredSize(new Dimension(220, 220));
        lblImagen.setBorder(BorderFactory.createEtchedBorder());
        cargarImagenEnLabel(imagen, lblImagen, 220, 220);

        JPanel panelDatos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        agregarDato(panelDatos, gbc, "ID:", id);
        agregarDato(panelDatos, gbc, "Código:", codigo);
        agregarDato(panelDatos, gbc, "Nombre:", nombre);
        agregarDato(panelDatos, gbc, "IVA:", iva + " %");
        agregarDato(panelDatos, gbc, "Estado:", estado);
        agregarDato(panelDatos, gbc, "ID Categoría:", idCategoria);

        JTextArea txtDescripcion = new JTextArea(descripcion);
        txtDescripcion.setEditable(false);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(BorderFactory.createTitledBorder("Descripción técnica"));

        panel.add(lblImagen, BorderLayout.WEST);
        panel.add(panelDatos, BorderLayout.CENTER);
        panel.add(new JScrollPane(txtDescripcion), BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(vista, panel, "Detalle del producto", JOptionPane.INFORMATION_MESSAGE);
    }

    private void agregarDato(JPanel panel, GridBagConstraints gbc, String etiqueta, String valor) {
        gbc.gridx = 0;
        panel.add(new JLabel(etiqueta), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(valor == null || valor.trim().isEmpty() ? "-" : valor), gbc);
        gbc.gridy++;
    }

    private void cargarImagenEnLabel(String ruta, JLabel label, int ancho, int alto) {
        if (ruta == null || ruta.trim().isEmpty()) {
            label.setText("Sin imagen");
            return;
        }
        
        new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                ImageIcon icono;
                if (ruta.startsWith("http")) {
                    icono = new ImageIcon(new URL(ruta));
                } else {
                    icono = new ImageIcon(ruta);
                }
                Image img = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                return new ImageIcon(img);
            }

            @Override
            protected void done() {
                try {
                    label.setText("");
                    label.setIcon(get());
                } catch (Exception e) {
                    label.setText("Imagen no disponible");
                }
            }
        }.execute();
    }

    private void exportarProductosCSV() {
        if (vista.tblProductos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(vista, "No hay productos para exportar.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar reporte de productos");
        chooser.setSelectedFile(new File("productos.csv"));
        int opcion = chooser.showSaveDialog(vista);
        if (opcion != JFileChooser.APPROVE_OPTION) return;

        File archivo = chooser.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".csv")) {
            archivo = new File(archivo.getAbsolutePath() + ".csv");
        }

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(archivo.toPath()), StandardCharsets.UTF_8))) {
            
            // 1. Escribir el BOM para que Excel detecte correctamente el UTF-8 (tildes, eñes)
            bw.write("\ufeff");
            
            // 2. Cabeceras separadas por PUNTO Y COMA (;)
            bw.write("ID;Codigo;Nombre;Descripcion;IVA;Estado;IdCategoria;Imagen");
            bw.newLine();
            
            DefaultTableModel modelo = (DefaultTableModel) vista.tblProductos.getModel();
            for (int filaVista = 0; filaVista < vista.tblProductos.getRowCount(); filaVista++) {
                int filaModelo = vista.tblProductos.convertRowIndexToModel(filaVista);
                
                // 3. Filas concatenadas con PUNTO Y COMA (;)
                bw.write(csv(valorModelo(modelo, filaModelo, 1)) + ";"
                        + csv(valorModelo(modelo, filaModelo, 2)) + ";"
                        + csv(valorModelo(modelo, filaModelo, 3)) + ";"
                        + csv(valorModelo(modelo, filaModelo, 4)) + ";"
                        + csv(valorModelo(modelo, filaModelo, 5)) + ";"
                        + csv(valorModelo(modelo, filaModelo, 7)) + ";"
                        + csv(valorModelo(modelo, filaModelo, 8)) + ";"
                        + csv(valorModelo(modelo, filaModelo, 6)));
                bw.newLine();
            }
            JOptionPane.showMessageDialog(vista, "CSV exportado correctamente:\n" + archivo.getAbsolutePath());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista, "Error al exportar CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String csv(String texto) {
        if (texto == null) return "\"\"";
        return "\"" + texto.replace("\"", "\"\"") + "\"";
    }

    private void limpiarCampos() {
        vista.txtIdProducto.setText("");
        vista.txtCodigoBarras.setText("");
        vista.txtNombre.setText("");
        vista.txtDescripcionTecnica.setText("");
        vista.txtRuta.setText("");
        vista.txtBuscar.setText("");
        limpiarImagen();
        if (vista.cboEstado.getItemCount() > 0) vista.cboEstado.setSelectedIndex(0);
        if (vista.cboFiltroBusqueda.getItemCount() > 0) vista.cboFiltroBusqueda.setSelectedIndex(0);
        if (vista.cboCategoria.getItemCount() > 0) vista.cboCategoria.setSelectedIndex(0);
        vista.tblProductos.clearSelection();
        listarProductosEnTabla();
        modoNuevo();
        vista.txtCodigoBarras.requestFocus();
    }
}