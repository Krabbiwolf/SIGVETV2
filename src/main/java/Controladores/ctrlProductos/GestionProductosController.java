/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.ctrlProductos;

import Modelos.Producto;
import Modelos.ProductosDAO;
import Vistas.FrmGestionarProductos;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import servicios.CloudinaryService;

/**
 *
 * @author Usuario
 */
public class GestionProductosController {

   private final ProductosDAO dao;
    private final FrmGestionarProductos vista;

    public GestionProductosController(FrmGestionarProductos vista) {
        this.dao = new ProductosDAO();
        this.vista = vista;

        configurarVista();
        cargarCategorias();
        cargarEstados();
        cargarIva();
        listarProductosEnTabla();
        agregarEventos();
    }

    private void configurarVista() {
        vista.setTitle("Gestionar Productos");

        vista.txtIdProducto.setEditable(false);
        vista.txtIdProducto.setFocusable(false);

        vista.txtRuta.setEditable(false);
        vista.txtRuta.setFocusable(false);

        vista.lblMostrarImagen.setText("Sin imagen");
        vista.lblMostrarImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vista.lblMostrarImagen.setVerticalAlignment(javax.swing.SwingConstants.CENTER);

        vista.tblProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void agregarEventos() {
        vista.btnBuscar.addActionListener(e -> buscarProductosEnTabla());
        vista.btnActualizar.addActionListener(e -> actualizarProducto());
        vista.btnEliminar.addActionListener(e -> eliminarProducto());
        vista.btnLimpiar.addActionListener(e -> limpiarCampos());
        vista.btnAgregarImagen.addActionListener(e -> seleccionarImagen());

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

    private void cargarCategorias() {
        vista.cboCategoria.removeAllItems();

        ArrayList<String> categorias = dao.listarCategoriasCombo();

        for (String categoria : categorias) {
            vista.cboCategoria.addItem(categoria);
        }
    }

    private void cargarEstados() {
        vista.cboEstado.removeAllItems();
        vista.cboEstado.addItem("Activo");
        vista.cboEstado.addItem("Inactivo");
    }

    private void cargarIva() {
        vista.cbIva.removeAllItems();
        vista.cbIva.addItem("13");
        vista.cbIva.addItem("0");
    }

    private void seleccionarImagen() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar imagen");

        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Imágenes JPG, PNG, JPEG",
                "jpg",
                "jpeg",
                "png"
        );

        chooser.setFileFilter(filtro);

        int opcion = chooser.showOpenDialog(vista);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();

            mostrarImagen(archivo.getAbsolutePath());

            CloudinaryService service = new CloudinaryService();
            String urlImagen = service.subirImagen(archivo);

            if (urlImagen != null) {
                vista.txtRuta.setText(urlImagen);
                JOptionPane.showMessageDialog(vista, "Imagen subida correctamente.");
            } else {
                vista.txtRuta.setText(archivo.getAbsolutePath());
                JOptionPane.showMessageDialog(vista, "No se pudo subir a Cloudinary. Se guardó la ruta local.");
            }
        }
    }

    private void mostrarImagen(String ruta) {
        try {
            if (ruta == null || ruta.trim().isEmpty()) {
                limpiarImagen();
                return;
            }

            ImageIcon iconoOriginal;

            if (ruta.startsWith("http")) {
                URL url = new URL(ruta);
                iconoOriginal = new ImageIcon(url);
            } else {
                iconoOriginal = new ImageIcon(ruta);
            }

            int ancho = vista.lblMostrarImagen.getWidth() > 0 ? vista.lblMostrarImagen.getWidth() : 250;
            int alto = vista.lblMostrarImagen.getHeight() > 0 ? vista.lblMostrarImagen.getHeight() : 250;

            Image imagenEscalada = iconoOriginal.getImage()
                    .getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

            vista.lblMostrarImagen.setText("");
            vista.lblMostrarImagen.setIcon(new ImageIcon(imagenEscalada));

        } catch (Exception e) {
            limpiarImagen();
        }
    }

    private void limpiarImagen() {
        vista.lblMostrarImagen.setIcon(null);
        vista.lblMostrarImagen.setText("Sin imagen");
    }

    private DefaultTableModel crearModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

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

        for (Producto p : productos) {
            modelo.addRow(new Object[]{
                p.getIdProducto(),
                p.getCodigoBarras(),
                p.getNombre(),
                p.getDescripcionTecnica(),
                p.getPorcentajeIvaDetalle(),
                p.getImagenUrl(),
                p.getEstado(),
                p.getIdCategoria()
            });
        }

        vista.tblProductos.setModel(modelo);
        vista.tblProductos.setAutoCreateRowSorter(true);

        ocultarColumna(5);
        ocultarColumna(7);
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
        ArrayList<Producto> productos = dao.listarProductos();
        llenarTabla(productos);
    }

    private void buscarProductosEnTabla() {
        String texto = vista.txtBuscar.getText().trim();

        if (texto.isEmpty()) {
            listarProductosEnTabla();
            return;
        }

        ArrayList<Producto> productos = dao.buscarProductos(texto);
        llenarTabla(productos);
    }

    private String valorModelo(DefaultTableModel modelo, int fila, int columna) {
        Object valor = modelo.getValueAt(fila, columna);
        return valor == null ? "" : valor.toString();
    }

    private void seleccionarProductoTabla() {
        int filaVista = vista.tblProductos.getSelectedRow();

        if (filaVista < 0) {
            return;
        }

        int filaModelo = vista.tblProductos.convertRowIndexToModel(filaVista);
        DefaultTableModel modelo = (DefaultTableModel) vista.tblProductos.getModel();

        vista.txtIdProducto.setText(valorModelo(modelo, filaModelo, 0));
        vista.txtCodigoBarras.setText(valorModelo(modelo, filaModelo, 1));
        vista.txtNombre.setText(valorModelo(modelo, filaModelo, 2));
        vista.txtDescripcionTecnica.setText(valorModelo(modelo, filaModelo, 3));

        String iva = valorModelo(modelo, filaModelo, 4);

        if (iva.equals("13.0") || iva.equals("13")) {
            vista.cbIva.setSelectedItem("13");
        } else {
            vista.cbIva.setSelectedItem("0");
        }

        String rutaImagen = valorModelo(modelo, filaModelo, 5);
        vista.txtRuta.setText(rutaImagen);
        mostrarImagen(rutaImagen);

        vista.cboEstado.setSelectedItem(valorModelo(modelo, filaModelo, 6));

        try {
            int idCategoria = Integer.parseInt(valorModelo(modelo, filaModelo, 7));
            seleccionarCategoriaPorId(idCategoria);
        } catch (NumberFormatException e) {
            if (vista.cboCategoria.getItemCount() > 0) {
                vista.cboCategoria.setSelectedIndex(0);
            }
        }
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
        String categoriaSeleccionada = vista.cboCategoria.getSelectedItem().toString();
        String[] partes = categoriaSeleccionada.split(" - ");
        return Integer.parseInt(partes[0]);
    }

    private String obtenerRutaImagen() {
        return vista.txtRuta.getText().trim();
    }

    private void actualizarProducto() {
        try {
            if (vista.txtIdProducto.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Seleccione un producto de la tabla.");
                return;
            }

            String codigoBarras = vista.txtCodigoBarras.getText().trim();
            String nombre = vista.txtNombre.getText().trim();

            if (codigoBarras.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese el código de barras.");
                vista.txtCodigoBarras.requestFocus();
                return;
            }

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese el nombre del producto.");
                vista.txtNombre.requestFocus();
                return;
            }

            if (vista.cboCategoria.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(vista, "Seleccione una categoría.");
                return;
            }

            if (vista.cboEstado.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(vista, "Seleccione un estado.");
                return;
            }

            if (vista.cbIva.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(vista, "Seleccione el IVA.");
                return;
            }

            Producto producto = new Producto();

            producto.setIdProducto(Integer.parseInt(vista.txtIdProducto.getText().trim()));
            producto.setCodigoBarras(codigoBarras);
            producto.setNombre(nombre);
            producto.setDescripcionTecnica(vista.txtDescripcionTecnica.getText().trim());

            double iva = Double.parseDouble(vista.cbIva.getSelectedItem().toString());
            producto.setPorcentajeIvaDetalle(iva);

            producto.setImagenUrl(obtenerRutaImagen());
            producto.setEstado(vista.cboEstado.getSelectedItem().toString());
            producto.setIdCategoria(obtenerIdCategoria());

            boolean actualizado = dao.actualizarProducto(producto);

            if (actualizado) {
                JOptionPane.showMessageDialog(vista, "Producto actualizado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo actualizar el producto.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Error numérico. Revise los datos seleccionados.");
        }
    }

    private void eliminarProducto() {
        if (vista.txtIdProducto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Seleccione un producto de la tabla.");
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                vista,
                "¿Desea desactivar este producto?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            int idProducto = Integer.parseInt(vista.txtIdProducto.getText().trim());

            boolean eliminado = dao.eliminarProducto(idProducto);

            if (eliminado) {
                JOptionPane.showMessageDialog(vista, "Producto desactivado correctamente.");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "No se pudo desactivar el producto.");
            }
        }
    }

    private void limpiarCampos() {
        vista.txtIdProducto.setText("");
        vista.txtCodigoBarras.setText("");
        vista.txtNombre.setText("");
        vista.txtDescripcionTecnica.setText("");
        vista.txtRuta.setText("");
        vista.txtBuscar.setText("");

        limpiarImagen();

        if (vista.cbIva.getItemCount() > 0) {
            vista.cbIva.setSelectedIndex(0);
        }

        if (vista.cboCategoria.getItemCount() > 0) {
            vista.cboCategoria.setSelectedIndex(0);
        }

        if (vista.cboEstado.getItemCount() > 0) {
            vista.cboEstado.setSelectedIndex(0);
        }

        vista.tblProductos.clearSelection();
        listarProductosEnTabla();
        vista.txtCodigoBarras.requestFocus();
    }
}
