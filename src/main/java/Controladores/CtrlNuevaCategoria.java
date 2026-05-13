package Controladores;

import Modelos.Categoria;
import Modelos.CategoriaDAO;
import Vistas.NuevaCategoria;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class CtrlNuevaCategoria implements ActionListener {

    private Categoria c;
    private CategoriaDAO dao;
    private NuevaCategoria form;

    public CtrlNuevaCategoria(Categoria c, CategoriaDAO dao, NuevaCategoria form) {
        this.c = c;
        this.dao = dao;
        this.form = form;
        this.form.btnGuardar.addActionListener(this);
    }

    public void limpiar() {
        form.txtnombrecategoria.setText(""); 
        form.txtDescripcion.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == form.btnGuardar) {
        // Aquí SÍ existen 'form', 'c' y 'dao'
        String nombre = form.txtnombrecategoria.getText().trim();
        String descripcion = form.txtDescripcion.getText().trim();

        if (nombre.isEmpty() || descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos.");
            return;
        }

        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        c.setEstado("ACTIVO");

        if (dao.guardar(c)) {
            JOptionPane.showMessageDialog(null, "Guardado exitosamente");
            limpiar();
        }
    }
    }
}