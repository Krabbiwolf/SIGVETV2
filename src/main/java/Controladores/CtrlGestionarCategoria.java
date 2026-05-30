package Controladores;

import Modelos.Categoria;
import Modelos.CategoriaDAO;
import Modelos.SesionUsuario;
import Vistas.FrmGestionarCategorias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CtrlGestionarCategoria implements ActionListener {

    private final CategoriaDAO dao;
    private final FrmGestionarCategorias form;
    private SwingWorker<ArrayList<Categoria>, Void> currentWorker;

    public CtrlGestionarCategoria(Categoria c, CategoriaDAO dao, FrmGestionarCategorias form) {
        this.dao = dao;
        this.form = form;

        this.form.btnGuardar.addActionListener(this);
        this.form.btnActualizar.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);
        this.form.btnLimpiar.addActionListener(this);
        this.form.btnRefrescar.addActionListener(this);

        this.form.btnBuscarCategorias.addActionListener(this);
        this.form.btnLimpiarFiltroCategorias.addActionListener(this);
        this.form.btnExportarCategorias.addActionListener(this);

        this.form.tblCategorias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });

        agregarValidacionesDeTeclado();
        estadoInicial();
        cargarTabla();
        
        if (!SesionUsuario.tienePermiso("EDICION_PRODUCTOS")) {
            form.btnGuardar.setVisible(false);
            form.btnActualizar.setVisible(false);
            form.btnEliminar.setVisible(false);
            // Si el form se llama "form" en categorías, cambia "vista" por "form"
        }
        if (!SesionUsuario.tienePermiso("EXPORTAR_PRODUCTOS")) {
            if (form.btnExportarCategorias != null) form.btnExportarCategorias.setVisible(false);
        }
    }

    private void estadoInicial() {
        form.txtId.setText("");
        form.txtId.setEnabled(false);

        form.btnGuardar.setEnabled(true);
        form.btnActualizar.setEnabled(false);
        form.btnEliminar.setEnabled(false);

        form.chkEstado.setSelected(true);

        form.cbFiltroCategorias.removeAllItems();
        form.cbFiltroCategorias.addItem("Todos");
        form.cbFiltroCategorias.addItem("ID");
        form.cbFiltroCategorias.addItem("Nombre");
        form.cbFiltroCategorias.addItem("Descripción");
        form.cbFiltroCategorias.addItem("Estado");
        form.cbFiltroCategorias.setSelectedItem("Todos");
    }

    public void cargarTabla() {
        if (currentWorker != null && !currentWorker.isDone()) {
            currentWorker.cancel(true);
        }

        DefaultTableModel modeloCargando = crearModeloTabla();
        modeloCargando.addRow(new Object[]{"", "Cargando...", "", ""});
        form.tblCategorias.setModel(modeloCargando);
        form.tblCategorias.setRowSorter(null);

        currentWorker = new SwingWorker<ArrayList<Categoria>, Void>() {
            @Override
            protected ArrayList<Categoria> doInBackground() {
                return dao.listar();
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Categoria> lista = get();
                    llenarTabla(lista);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, "Error al cargar categorías: " + ex.getMessage());
                } finally {
                    currentWorker = null;
                }
            }
        };

        currentWorker.execute();
    }

    private DefaultTableModel crearModeloTabla() {
        return new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Descripción", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void llenarTabla(ArrayList<Categoria> lista) {
        DefaultTableModel modelo = crearModeloTabla();

        for (Categoria cat : lista) {
            modelo.addRow(new Object[]{
                cat.getIdCategoria(),
                cat.getNombre(),
                cat.getDescripcion(),
                cat.getEstado()
            });
        }

        form.tblCategorias.setModel(modelo);
        form.tblCategorias.setRowSorter(null);

        if (form.tblCategorias.getColumnModel().getColumnCount() > 0) {
            form.tblCategorias.getColumnModel().getColumn(0).setPreferredWidth(50);
            form.tblCategorias.getColumnModel().getColumn(1).setPreferredWidth(170);
            form.tblCategorias.getColumnModel().getColumn(2).setPreferredWidth(260);
            form.tblCategorias.getColumnModel().getColumn(3).setPreferredWidth(100);
        }
    }

    private void seleccionarFila() {
        int fila = form.tblCategorias.getSelectedRow();

        if (fila < 0) {
            return;
        }

        fila = form.tblCategorias.convertRowIndexToModel(fila);

        Object id = form.tblCategorias.getModel().getValueAt(fila, 0);

        if (id == null || id.toString().trim().isEmpty()) {
            return;
        }

        form.txtId.setText(id.toString());
        form.txtnombrecategoria.setText(form.tblCategorias.getModel().getValueAt(fila, 1).toString());
        form.txtDescripcion.setText(form.tblCategorias.getModel().getValueAt(fila, 2).toString());

        String estado = form.tblCategorias.getModel().getValueAt(fila, 3).toString();
        form.chkEstado.setSelected(estado.equalsIgnoreCase("Activo"));

        form.btnGuardar.setEnabled(false);
        form.btnActualizar.setEnabled(true);
        form.btnEliminar.setEnabled(true);
    }

    private Categoria obtenerCategoriaDelFormulario() {
        Categoria categoria = new Categoria();

        if (!form.txtId.getText().trim().isEmpty()) {
            categoria.setIdCategoria(Integer.parseInt(form.txtId.getText().trim()));
        }

        categoria.setNombre(form.txtnombrecategoria.getText().trim());
        categoria.setDescripcion(form.txtDescripcion.getText().trim());
        categoria.setEstado(form.chkEstado.isSelected() ? "Activo" : "Inactivo");

        return categoria;
    }

    private boolean validarCampos(boolean actualizando) {
        String nombre = form.txtnombrecategoria.getText().trim();
        String descripcion = form.txtDescripcion.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(form, "El nombre de la categoría no puede estar vacío.");
            form.txtnombrecategoria.requestFocus();
            return false;
        }

        if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(form, "La descripción no puede estar vacía.");
            form.txtDescripcion.requestFocus();
            return false;
        }

        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 ]+$")) {
            JOptionPane.showMessageDialog(form, "El nombre solo puede contener letras, números y espacios.");
            form.txtnombrecategoria.requestFocus();
            return false;
        }

        if (!descripcion.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9 ,.\\-]+$")) {
            JOptionPane.showMessageDialog(form, "La descripción contiene caracteres no permitidos.");
            form.txtDescripcion.requestFocus();
            return false;
        }

        int idActual = 0;

        if (actualizando && !form.txtId.getText().trim().isEmpty()) {
            idActual = Integer.parseInt(form.txtId.getText().trim());
        }

        if (dao.existeNombre(nombre, idActual)) {
            JOptionPane.showMessageDialog(form, "Ya existe una categoría con ese nombre.");
            form.txtnombrecategoria.requestFocus();
            return false;
        }

        return true;
    }

    private void guardarCategoria() {
        if (!validarCampos(false)) {
            return;
        }

        Categoria categoria = obtenerCategoriaDelFormulario();

        if (dao.guardar(categoria)) {
            JOptionPane.showMessageDialog(form, "Categoría guardada correctamente.");
            limpiarCampos();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(form, "Error al guardar la categoría.");
        }
    }

    private void actualizarCategoria() {
        if (form.txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(form, "Seleccione una categoría de la tabla.");
            return;
        }

        if (!validarCampos(true)) {
            return;
        }

        Categoria categoria = obtenerCategoriaDelFormulario();

        if (dao.actualizar(categoria)) {
            JOptionPane.showMessageDialog(form, "Categoría actualizada correctamente.");
            limpiarCampos();
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(form, "Error al actualizar la categoría.");
        }
    }

    private void eliminarCategoria() {
        if (form.txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(form, "Seleccione una categoría de la tabla.");
            return;
        }

        int id = Integer.parseInt(form.txtId.getText().trim());
        ArrayList<String> relaciones = dao.obtenerRelacionesCategoria(id);

        if (relaciones.isEmpty()) {
            int confirmar = JOptionPane.showConfirmDialog(
                    form,
                    "Esta categoría no tiene productos relacionados.\n"
                    + "¿Desea eliminarla permanentemente?",
                    "Confirmar eliminación definitiva",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmar == JOptionPane.YES_OPTION) {
                if (dao.eliminarFisico(id)) {
                    JOptionPane.showMessageDialog(form, "Categoría eliminada permanentemente.");
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(form, "Error al eliminar la categoría.");
                }
            }

        } else {
            String mensajeRelaciones = String.join(", ", relaciones);

            int confirmar = JOptionPane.showConfirmDialog(
                    form,
                    "Esta categoría tiene relación con: " + mensajeRelaciones + ".\n\n"
                    + "Por seguridad no se puede eliminar permanentemente.\n"
                    + "Solo se inactivará la categoría.\n\n"
                    + "¿Desea continuar?",
                    "Categoría con relaciones",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmar == JOptionPane.YES_OPTION) {
                if (dao.eliminarLogico(id)) {
                    JOptionPane.showMessageDialog(
                            form,
                            "Categoría inactivada correctamente.\n"
                            + "Relaciones encontradas: " + mensajeRelaciones
                    );
                    limpiarCampos();
                    cargarTabla();
                } else {
                    JOptionPane.showMessageDialog(form, "Error al inactivar la categoría.");
                }
            }
        }
    }

    private void filtrarCategorias() {
        DefaultTableModel modelo = (DefaultTableModel) form.tblCategorias.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        form.tblCategorias.setRowSorter(sorter);

        String texto = form.txtBuscarCategorias.getText().trim();
        String filtro = form.cbFiltroCategorias.getSelectedItem().toString();

        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
            return;
        }

        texto = java.util.regex.Pattern.quote(texto);

        int columna;

        switch (filtro) {
            case "ID":
                columna = 0;
                break;
            case "Nombre":
                columna = 1;
                break;
            case "Descripción":
                columna = 2;
                break;
            case "Estado":
                columna = 3;
                break;
            case "Todos":
            default:
                columna = -1;
                break;
        }

        if (columna == -1) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, columna));
        }
    }

    private void limpiarFiltroCategorias() {
        form.txtBuscarCategorias.setText("");
        form.cbFiltroCategorias.setSelectedItem("Todos");
        form.tblCategorias.setRowSorter(null);
    }

private void exportarCategoriasCSV() {
        exportarTablaCSV(form.tblCategorias, "categorias");
    }

    private void exportarTablaCSV(JTable tabla, String nombreArchivoSugerido) {
        if (tabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(form, "No hay datos para exportar.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar archivo CSV");
        fileChooser.setSelectedFile(new File(nombreArchivoSugerido + ".csv"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo CSV", "csv"));

        int seleccion = fileChooser.showSaveDialog(form);

        if (seleccion != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File archivo = fileChooser.getSelectedFile();

        if (!archivo.getName().toLowerCase().endsWith(".csv")) {
            archivo = new File(archivo.getAbsolutePath() + ".csv");
        }

        // Agregamos codificación UTF-8 para evitar problemas con tildes y eñes
        try (java.io.FileWriter fw = new java.io.FileWriter(archivo, java.nio.charset.StandardCharsets.UTF_8)) {

            // Escribir el BOM para que Excel detecte correctamente el UTF-8
            fw.write("\ufeff");

            for (int i = 0; i < tabla.getColumnCount(); i++) {
                fw.write(escaparCSV(tabla.getColumnName(i)));

                if (i < tabla.getColumnCount() - 1) {
                    fw.write(";"); // Cambiado a PUNTO Y COMA
                }
            }

            fw.write("\n");

            for (int filaVista = 0; filaVista < tabla.getRowCount(); filaVista++) {
                int filaModelo = tabla.convertRowIndexToModel(filaVista);

                for (int col = 0; col < tabla.getColumnCount(); col++) {
                    Object valor = tabla.getModel().getValueAt(filaModelo, col);
                    fw.write(escaparCSV(valor == null ? "" : valor.toString()));

                    if (col < tabla.getColumnCount() - 1) {
                        fw.write(";"); // Cambiado a PUNTO Y COMA
                    }
                }

                fw.write("\n");
            }

            JOptionPane.showMessageDialog(form, "Archivo CSV exportado correctamente.");

        } catch (java.io.IOException e) {
            JOptionPane.showMessageDialog(form, "Error al exportar CSV: " + e.getMessage());
        }
    }

    private String escaparCSV(String texto) {
        if (texto == null) {
            return "";
        }

        // Buscamos punto y coma (;) en lugar de coma (,)
        if (texto.contains(";") || texto.contains("\"") || texto.contains("\n")) {
            texto = texto.replace("\"", "\"\"");
            return "\"" + texto + "\"";
        }

        return texto;
    }

    private void limpiarCampos() {
        form.txtId.setText("");
        form.txtnombrecategoria.setText("");
        form.txtDescripcion.setText("");
        form.chkEstado.setSelected(true);

        form.btnGuardar.setEnabled(true);
        form.btnActualizar.setEnabled(false);
        form.btnEliminar.setEnabled(false);

        form.tblCategorias.clearSelection();
    }

    private void agregarValidacionesDeTeclado() {
        form.txtnombrecategoria.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();

                if (!Character.isLetterOrDigit(ch) && ch != ' ' && ch != '\b') {
                    e.consume();
                    return;
                }

                if (form.txtnombrecategoria.getText().length() >= 50 && ch != '\b') {
                    e.consume();
                }
            }
        });

        form.txtDescripcion.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();

                if (!Character.isLetterOrDigit(ch)
                        && ch != ' '
                        && ch != ','
                        && ch != '.'
                        && ch != '-'
                        && ch != '\b') {
                    e.consume();
                    return;
                }

                if (form.txtDescripcion.getText().length() >= 150 && ch != '\b') {
                    e.consume();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == form.btnGuardar) {
            guardarCategoria();
        } else if (source == form.btnActualizar) {
            actualizarCategoria();
        } else if (source == form.btnEliminar) {
            eliminarCategoria();
        } else if (source == form.btnLimpiar) {
            limpiarCampos();
        } else if (source == form.btnRefrescar) {
            limpiarCampos();
            cargarTabla();
        } else if (source == form.btnBuscarCategorias) {
            filtrarCategorias();
        } else if (source == form.btnLimpiarFiltroCategorias) {
            limpiarFiltroCategorias();
        } else if (source == form.btnExportarCategorias) {
            exportarCategoriasCSV();
        }
    }
}