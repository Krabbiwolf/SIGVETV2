/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.CtrlRoles;

import Modelos.Roles;
import Modelos.RolesDAO;
import Vistas.FrmNuevoUsuario;
import Vistas.FrmRolesPermisos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author axele
 */
public class CtrlGestionarRoles implements ActionListener {

    private Roles rol;
    private FrmRolesPermisos form;
    private FrmNuevoUsuario form2;
    private RolesDAO dao;
    private ArrayList<Roles> roles;

    public CtrlGestionarRoles(
            Roles rol,
            FrmRolesPermisos form,
            FrmNuevoUsuario form2,
            RolesDAO dao
    ) {

        this.rol = rol;
        this.form = form;
        this.form2 = form2;
        this.dao = dao;

        this.form.btnGuardarRol.addActionListener(this);
    }

    public void limpiarCampos() {

        form.txtRol.setText("");
        form.txtDescripcion.setText("");

        // LIMPIAR CHECKBOXES
        form.checkGestionProductoProveedor.setSelected(false);
        form.checkRegistroCompraVenta.setSelected(false);
        form.checkGestionUsuarios.setSelected(false);
        form.checkVerProductoProveedor.setSelected(false);
        form.checkVerCompraVenta.setSelected(false);
        form.checkVerUsuarios.setSelected(false);
    }

    public void cargarRoles() {

        roles = dao.listarRoles();

        form2.comboRoles.removeAllItems();

        for (Roles rol : roles) {
            form2.comboRoles.addItem(rol);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == form.btnGuardarRol) {

            try {
                String nombre_cargo =
                form.txtRol.getText().trim();

                String descripcion =
                form.txtDescripcion.getText().trim();

                // =========================
                // VALIDACIONES
                // =========================

                if (nombre_cargo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre del Rol es obligatorio."
                    );
                    return;
                }

                if (descripcion.isEmpty()) {
                    descripcion = "(Sin descripcion)";
                }

                // =========================
                // DATOS DEL ROL
                // =========================

                rol.setNombre_cargo(nombre_cargo);
                rol.setDescripcion(descripcion);

                // =========================
                // LISTA DE PERMISOS
                // =========================

                ArrayList<String> permisos = new ArrayList<>();

                if (form.checkGestionProductoProveedor.isSelected()) {
                    permisos.add(
                    "Gestion Productos y Proveedores"
                    );
                }

                if (form.checkRegistroCompraVenta.isSelected()) {
                    permisos.add(
                    "Registro Compra y Ventas"
                    );
                }

                if (form.checkGestionUsuarios.isSelected()) {
                    permisos.add(
                    "Gestion Usuarios"
                    );
                }

                if (form.checkVerProductoProveedor.isSelected()) {
                    permisos.add(
                    "Ver Productos y Proveedores"
                    );
                }

                if (form.checkVerCompraVenta.isSelected()) {
                    permisos.add(
                    "Ver Compra y Ventas"
                    );
                }

                if (form.checkVerUsuarios.isSelected()) {
                    permisos.add(
                    "Ver Usuarios"
                    );
                }

                // =========================
                // VALIDAR PERMISOS
                // =========================

                if (permisos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un permiso."
                    );
                    return;
                }

                // =========================
                // GUARDAR ROL Y PERMISOS
                // =========================

                if (dao.registrarRol(rol, permisos)) {
                    JOptionPane.showMessageDialog(null, "¡Rol creado exitosamente!");
                    limpiarCampos();
                    cargarRoles();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear el Rol."
                    );
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en el formato de los datos."
                );
                System.out.println(ex);
            }
        }
    }
}