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
import javax.swing.SwingWorker;

public class CtrlGestionarRoles implements ActionListener {

    private Roles rol;
    private FrmRolesPermisos form;
    private FrmNuevoUsuario form2;
    private RolesDAO dao;
    private SwingWorker<ArrayList<Roles>, Void> currentWorker;

    public CtrlGestionarRoles(Roles rol, FrmRolesPermisos form, FrmNuevoUsuario form2, RolesDAO dao) {
        this.rol = rol;
        this.form = form;
        this.form2 = form2;
        this.dao = dao;

        this.form.btnGuardarRol.addActionListener(this);

        cargarRolesAsync(); // 🔥 asíncrono
    }

    public void limpiarCampos() {
        form.txtRol.setText("");
        form.txtDescripcion.setText("");
        
        // Limpiar Módulo Usuarios
        form.checkVerUsuarios.setSelected(false);
        form.checkGestionUsuarios.setSelected(false);
        form.checkExportarUsuarios.setSelected(false);

        // Limpiar Módulo Productos
        form.checkVerProductos.setSelected(false);
        form.checkGestionProductos.setSelected(false);
        form.checkExportarProductos.setSelected(false);

        // Limpiar Módulo Ventas
        form.checkVerVentas.setSelected(false);
        form.checkRegistroCompraVenta.setSelected(false);
        form.checkExportarVentas.setSelected(false);

        // Limpiar Módulo Compras
        form.checkVerCompras.setSelected(false);
        form.checkGestionCompra.setSelected(false);
        form.checkExportarCompras.setSelected(false);

        // Limpiar Módulo Lotes
        form.checkVerLotes.setSelected(false);
        form.checkGestionLotes.setSelected(false);
        form.checkExportarLotes.setSelected(false);

        // Limpiar Módulo Terceros
        form.checkVerTerceros.setSelected(false);
        form.checkGestionTerceros.setSelected(false);
        form.checkExportarTerceros.setSelected(false);

        // Limpiar Módulo Reportes
        form.checkVerReportes.setSelected(false);
        form.checkGestionReportes.setSelected(false);
        form.checkExportarReportes.setSelected(false);
    }

    // ================== CARGA ASÍNCRONA DE ROLES ==================
    private void cargarRolesAsync() {
        if (currentWorker != null && !currentWorker.isDone()) {
            currentWorker.cancel(true);
        }
        currentWorker = new SwingWorker<ArrayList<Roles>, Void>() {
            @Override
            protected ArrayList<Roles> doInBackground() throws Exception {
                return dao.listarRoles();
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Roles> roles = get();
                    form2.comboRoles.removeAllItems();
                    for (Roles rol : roles) {
                        form2.comboRoles.addItem(rol);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, "Error al cargar roles: " + ex.getMessage());
                } finally {
                    currentWorker = null;
                }
            }
        };
        currentWorker.execute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == form.btnGuardarRol) {
            try {
                String nombre_cargo = form.txtRol.getText().trim();
                String descripcion = form.txtDescripcion.getText().trim();

                if (nombre_cargo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre del Rol es obligatorio.");
                    return;
                }
                if (descripcion.isEmpty()) {
                    descripcion = "(Sin descripcion)";
                }

                rol.setNombre_cargo(nombre_cargo);
                rol.setDescripcion(descripcion);

                ArrayList<String> permisos = new ArrayList<>();
                
                // --- Módulo Usuarios ---
                if(form.checkVerUsuarios.isSelected()) permisos.add("LECTURA_USUARIOS");
                if(form.checkGestionUsuarios.isSelected()) permisos.add("EDICION_USUARIOS");
                if(form.checkExportarUsuarios.isSelected()) permisos.add("EXPORTAR_USUARIOS");

                // --- Módulo Productos ---
                if(form.checkVerProductos.isSelected()) permisos.add("LECTURA_PRODUCTOS");
                if(form.checkGestionProductos.isSelected()) permisos.add("EDICION_PRODUCTOS");
                if(form.checkExportarProductos.isSelected()) permisos.add("EXPORTAR_PRODUCTOS");

                // --- Módulo Ventas ---
                if(form.checkVerVentas.isSelected()) permisos.add("LECTURA_VENTAS");
                if(form.checkRegistroCompraVenta.isSelected()) permisos.add("EDICION_VENTAS");
                if(form.checkExportarVentas.isSelected()) permisos.add("EXPORTAR_VENTAS");

                // --- Módulo Compras ---
                if(form.checkVerCompras.isSelected()) permisos.add("LECTURA_COMPRAS");
                if(form.checkGestionCompra.isSelected()) permisos.add("EDICION_COMPRAS");
                if(form.checkExportarCompras.isSelected()) permisos.add("EXPORTAR_COMPRAS");

                // --- Módulo Lotes ---
                if(form.checkVerLotes.isSelected()) permisos.add("LECTURA_LOTES");
                if(form.checkGestionLotes.isSelected()) permisos.add("EDICION_LOTES");
                if(form.checkExportarLotes.isSelected()) permisos.add("EXPORTAR_LOTES");

                // --- Módulo Terceros ---
                if(form.checkVerTerceros.isSelected()) permisos.add("LECTURA_TERCEROS");
                if(form.checkGestionTerceros.isSelected()) permisos.add("EDICION_TERCEROS");
                if(form.checkExportarTerceros.isSelected()) permisos.add("EXPORTAR_TERCEROS");

                // --- Módulo Reportes ---
                if(form.checkVerReportes.isSelected()) permisos.add("LECTURA_REPORTES");
                if(form.checkGestionReportes.isSelected()) permisos.add("EDICION_REPORTES");
                if(form.checkExportarReportes.isSelected()) permisos.add("EXPORTAR_REPORTES");

                if (permisos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe asignar al menos un permiso al rol.");
                    return;
                }

                if (dao.registrarRol(rol, permisos)) {
                    JOptionPane.showMessageDialog(null, "¡Rol creado exitosamente!");
                    limpiarCampos();
                    cargarRolesAsync(); // 🔥 recarga asíncrona
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear el Rol.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error en el formato de los datos.");
                System.out.println(ex);
            }
        }
    }
}