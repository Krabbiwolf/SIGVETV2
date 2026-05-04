/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.ctrlProductos;

import Modelos.Producto;
import Modelos.ProductosDAO;
import Vistas.FrmNuevoProducto;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import servicios.CloudinaryService;


/**
 *
 * @author Usuario
 */
public class NuevoProductoController {
    
private final ProductosDAO dao;
    private final FrmNuevoProducto vista;

    public NuevoProductoController(FrmNuevoProducto vista) {
        this.dao = new ProductosDAO();
        this.vista = vista;

        configurarVista();
        cargarCategorias();
        cargarIva();
        agregarEventos();
    }

    private void configurarVista() {
        vista.setTitle("Nuevo Producto");
        vista.txtRutaImagen.setEditable(false);
        vista.txtRutaImagen.setFocusable(false);
    }

    private void agregarEventos() {
        vista.btnGuardar.addActionListener(e -> guardarProducto());
        vista.btnLimpiar.addActionListener(e -> limpiarCampos());
        vista.jButton1.addActionListener(e -> seleccionarImagen());
    }

    private void cargarCategorias() {
        vista.cboCategoria.removeAllItems();

        ArrayList<String> categorias = dao.listarCategoriasCombo();

        for (String categoria : categorias) {
            vista.cboCategoria.addItem(categoria);
        }
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

            CloudinaryService service = new CloudinaryService();
            String urlImagen = service.subirImagen(archivo);

            if (urlImagen != null) {
                vista.txtRutaImagen.setText(urlImagen);
                JOptionPane.showMessageDialog(vista, "Imagen subida correctamente.");
            } else {
                vista.txtRutaImagen.setText(archivo.getAbsolutePath());
                JOptionPane.showMessageDialog(vista, "No se pudo subir a Cloudinary. Se guardó la ruta local.");
            }
        }
    }

    private int obtenerIdCategoria() {
        String categoriaSeleccionada = vista.cboCategoria.getSelectedItem().toString();
        String[] partes = categoriaSeleccionada.split(" - ");
        return Integer.parseInt(partes[0]);
    }

    private String generarCodigoAutomatico() {
        return "PRD-" + System.currentTimeMillis();
    }

    private void guardarProducto() {
        String nombre = vista.txtNombre.getText().trim();
        String descripcion = vista.txtDescripcion.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Ingrese el nombre del producto.");
            vista.txtNombre.requestFocus();
            return;
        }

        if (vista.cbIva.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione el IVA.");
            return;
        }

        if (vista.cboCategoria.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(vista, "Seleccione una categoría.");
            return;
        }

        Producto producto = new Producto();

        producto.setCodigoBarras(generarCodigoAutomatico());
        producto.setNombre(nombre);
        producto.setDescripcionTecnica(descripcion);
        producto.setPorcentajeIvaDetalle(Double.parseDouble(vista.cbIva.getSelectedItem().toString()));
        producto.setImagenUrl(vista.txtRutaImagen.getText().trim());
        producto.setEstado("Activo");
        producto.setIdCategoria(obtenerIdCategoria());

        boolean guardado = dao.guardarProducto(producto);

        if (guardado) {
            JOptionPane.showMessageDialog(vista, "Producto guardado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(vista, "No se pudo guardar el producto.");
        }
    }

    private void limpiarCampos() {
        vista.txtNombre.setText("");
        vista.txtDescripcion.setText("");
        vista.txtRutaImagen.setText("");

        if (vista.cbIva.getItemCount() > 0) {
            vista.cbIva.setSelectedIndex(0);
        }

        if (vista.cboCategoria.getItemCount() > 0) {
            vista.cboCategoria.setSelectedIndex(0);
        }

        vista.txtNombre.requestFocus();
    }
    
}
