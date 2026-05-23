package Controladores;

import Modelos.*;
import Vistas.GestionarCategorias;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CtrlGestionarCategoria implements ActionListener {

    private Categoria c;
    private CategoriaDAO dao;
    private GestionarCategorias form;
    private SwingWorker<ArrayList<Categoria>, Void> currentWorker;

    public CtrlGestionarCategoria(Categoria c, CategoriaDAO dao, GestionarCategorias form) {
        this.c = c;
        this.dao = dao;
        this.form = form;
        this.form.btnActualizar.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);

        cargarTabla(); // asíncrono

        form.tblCategorias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila();
            }
        });
    }

    // ================== CARGA ASÍNCRONA ==================
    public void cargarTabla() {
        if (currentWorker != null && !currentWorker.isDone()) {
            currentWorker.cancel(true);
        }
        currentWorker = new SwingWorker<ArrayList<Categoria>, Void>() {
            @Override
            protected ArrayList<Categoria> doInBackground() throws Exception {
                return dao.listar();
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Categoria> lista = get();
                    DefaultTableModel modelo = new DefaultTableModel();
                    modelo.addColumn("ID");
                    modelo.addColumn("Nombre");
                    modelo.addColumn("Descripcion");
                    modelo.addColumn("Estado");
                    for (Categoria cat : lista) {
                        modelo.addRow(new Object[]{
                            cat.getIdCategoria(),
                            cat.getNombre(),
                            cat.getDescripcion(),
                            cat.getEstado()
                        });
                    }
                    form.tblCategorias.setModel(modelo);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, "Error al cargar categorías: " + ex.getMessage());
                } finally {
                    currentWorker = null;
                }
            }
        };
        currentWorker.execute();
    }

    public void seleccionarFila() {
        int fila = form.tblCategorias.getSelectedRow();
        if (fila >= 0) {
            form.txtId.setText(form.tblCategorias.getValueAt(fila, 0).toString());
            form.txtnombrecategoria.setText(form.tblCategorias.getValueAt(fila, 1).toString());
            form.txtDescripcion.setText(form.tblCategorias.getValueAt(fila, 2).toString());
            form.cbEstado.setSelectedItem(form.tblCategorias.getValueAt(fila, 3).toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == form.btnActualizar) {
            if (form.txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione una categoria de la tabla");
                return;
            }
            if (form.txtnombrecategoria.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre de la categoría no puede estar vacío");
                return;
            }
            c.setIdCategoria(Integer.parseInt(form.txtId.getText()));
            c.setNombre(form.txtnombrecategoria.getText().trim());
            c.setDescripcion(form.txtDescripcion.getText().trim());
            c.setEstado(form.cbEstado.getSelectedItem().toString());
            if (dao.actualizar(c)) {
                JOptionPane.showMessageDialog(null, "Actualizado correctamente");
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar");
            }
        }

        if (e.getSource() == form.btnEliminar) {
            if (form.txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione una categoria");
                return;
            }
            int id = Integer.parseInt(form.txtId.getText());
            if (dao.eliminarLogico(id)) {
                JOptionPane.showMessageDialog(null, "Eliminado (Estado: Inactivo)");
                cargarTabla();
                limpiarCampos();
            }
        }
    }

    public void limpiarCampos() {
        form.txtId.setText("");
        form.txtnombrecategoria.setText("");
        form.txtDescripcion.setText("");
    }
}