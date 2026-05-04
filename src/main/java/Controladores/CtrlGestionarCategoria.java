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

    public CtrlGestionarCategoria(Categoria c, CategoriaDAO dao, GestionarCategorias form) {
        this.c = c;
        this.dao = dao;
        this.form = form;
        this.form.btnActualizar.addActionListener(this);
        this.form.btnEliminar.addActionListener(this);

        cargarTabla(); 

        form.tblCategorias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                seleccionarFila(); 
            }
        });
    }

    public void cargarTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Estado");

        ArrayList<Categoria> lista = dao.listar();
        for (Categoria cat : lista) {
            modelo.addRow(new Object[]{
                cat.getIdCategoria(),
                cat.getNombre(), 
                cat.getDescripcion(),
                cat.getEstado()
            });
        }
        form.tblCategorias.setModel(modelo);
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

        // --- ACTUALIZAR ---
        if (e.getSource() == form.btnActualizar) {

            // Validación de selección
            if (form.txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione una categoria de la tabla");
                return;
            }
            
            // Validación de campo nombre vacío (Evita el error de SQL 'cannot be null')
            if (form.txtnombrecategoria.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre de la categoría no puede estar vacío");
                return;
            }

            // ASIGNACIÓN DE VALORES AL OBJETO (Corregido)
            c.setIdCategoria(Integer.parseInt(form.txtId.getText()));
            c.setNombre(form.txtnombrecategoria.getText().trim()); // <-- FALTA ESTA LÍNEA
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

        // --- ELIMINAR ---
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

    // Método extra para limpiar la interfaz después de una acción
    public void limpiarCampos() {
        form.txtId.setText("");
        form.txtnombrecategoria.setText("");
        form.txtDescripcion.setText("");
    }
}