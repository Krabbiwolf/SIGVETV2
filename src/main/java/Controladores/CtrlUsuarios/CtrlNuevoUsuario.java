/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.CtrlUsuarios;

import Modelos.Roles;
import Modelos.SesionUsuario;
import Modelos.Usuario;
import Modelos.UsuarioDAO;
import Vistas.FrmNuevoUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author axele
 */
public class CtrlNuevoUsuario implements ActionListener{
    private Usuario usuario;
    private FrmNuevoUsuario form;
    private UsuarioDAO dao;
    private ArrayList<Usuario> usuarios;
    private SwingWorker<ArrayList<Usuario>, Void> currentWorker;

    public CtrlNuevoUsuario(Usuario usuario, FrmNuevoUsuario form, UsuarioDAO dao) {
        this.usuario = usuario;
        this.form = form;
        this.dao = dao;
        
        this.form.btnGuardarUsuario.addActionListener(this);
        this.form.btnActualizarUsuario.addActionListener(this);
        this.form.btnEliminarUsuario.addActionListener(this);
        this.form.btnLimpiar.addActionListener(this);
        this.form.btnVerInfo.addActionListener(this);
        this.form.btnExportar.addActionListener(this);
        
        this.form.txtBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String textoBusqueda = form.txtBuscador.getText().trim();
                DefaultTableModel modelo = (DefaultTableModel) form.tableUsuarios.getModel();

                javax.swing.table.TableRowSorter<DefaultTableModel> trs = new javax.swing.table.TableRowSorter<>(modelo);
                form.tableUsuarios.setRowSorter(trs);

                if (textoBusqueda.length() == 0) {
                    trs.setRowFilter(null);
                } else {
                    trs.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + textoBusqueda));
                }
            }
        });
            
        form.tableUsuarios.getColumnModel().getColumn(6).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (value != null && !value.toString().isEmpty()) {
                    setText("••••••••");
                } else {
                    setText("");
                }

                return this;
            }
        });
        
        this.form.tableUsuarios.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                cargarDatosFilaSeleccionada();
            }
        });
        
        cargarTabla();
        
        // Bloquear edición si no tiene permiso
        if (!SesionUsuario.tienePermiso("EDICION_USUARIOS")) {
            form.btnGuardarUsuario.setVisible(false);
            form.btnActualizarUsuario.setVisible(false);
            form.btnEliminarUsuario.setVisible(false);
        }
        
        // Bloquear exportación
        if (!SesionUsuario.tienePermiso("EXPORTAR_USUARIOS")) {
            form.btnExportar.setVisible(false);
        }
    }
    
    public void cargarTabla() {
        if (currentWorker != null && !currentWorker.isDone()) {
            currentWorker.cancel(true);
        }
        currentWorker = new SwingWorker<ArrayList<Usuario>, Void>() {
            @Override
            protected ArrayList<Usuario> doInBackground() throws Exception {
                return dao.listarUsuarios();
            }

            @Override
            protected void done() {
                try {
                    usuarios = get();
                    DefaultTableModel modeloTabla = (DefaultTableModel) form.tableUsuarios.getModel();
                    modeloTabla.setRowCount(0);
                    for (Usuario u : usuarios) {
                        modeloTabla.addRow(new Object[]{
                            u.getIdUsuario(),
                            u.getNombre(),
                            u.getApellido(),
                            u.getDui(),
                            u.getTelefono(),
                            u.getUsuario(),
                            u.getPassword(),
                            u.getImagenURL(),
                            u.getEstado(),
                            u.getRolCargo()
                        });
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, "Error al cargar usuarios: " + ex.getMessage());
                } finally {
                    currentWorker = null;
                }
            }
        };
        currentWorker.execute();
    }
    
    public void limpiarCampos(){
        form.txtNombres.setText("");
        form.txtApellidos.setText("");
        form.txtDUI.setText("");
        form.txtTelefono.setText("");
        form.txtUsuario.setText("");
        form.txtPassword.setText("");
        form.comboRoles.setSelectedIndex(0);
        form.comboEstado.setSelectedIndex(0);
        form.txtRutaImagen.setText("");
        
        form.btnGuardarUsuario.setEnabled(true);
        form.tableUsuarios.clearSelection();
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == form.btnGuardarUsuario){
            try{
                String nombre = form.txtNombres.getText().trim();
                String apellido = form.txtApellidos.getText().trim();
                String dui = form.txtDUI.getText().trim();
                String telefono = form.txtTelefono.getText().trim();
                String username = form.txtUsuario.getText().trim();
                String password = form.txtPassword.getText().trim();
                String imagen = form.txtRutaImagen.getText().trim();
                String estado = form.comboEstado.getSelectedItem().toString();
                Roles rol = (Roles)form.comboRoles.getSelectedItem();
                
                if(nombre.isEmpty() || apellido.isEmpty() || username.isEmpty() || password.isEmpty()){
                    JOptionPane.showMessageDialog(null, "El nombre, apellido, usuario y contraseña son obligatorios.");
                    return;
                }
                
                String regexTelefono = "^[267]\\d{3}-?\\d{4}$";
                if (!telefono.matches(regexTelefono) && !telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Formato de teléfono incorrecto. Ejemplos válidos: 7123-4567 o 71234567.");
                    return;
                }
                
                String regexDui = "^\\d{8}-\\d$";
                if (!dui.matches(regexDui) && !dui.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Formato de DUI incorrecto. Ej: 12345678-9");
                    return;
                }
                
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setDui(dui);
                usuario.setTelefono(telefono);
                usuario.setUsuario(username);
                usuario.setPassword(password);
                usuario.setImagenURL(imagen);
                usuario.setEstado(estado);
                usuario.setIdRol(rol.getIdRol());
                
                if(dao.registrarUsuario(usuario)){
                    JOptionPane.showMessageDialog(null, "¡Usuario creado exitosamente!");
                    cargarTabla();
                    limpiarCampos();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al crear el Usuario.");
                }
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error en el formato de los datos.");
                System.out.println(ex);
            }
        }
        
        if(e.getSource() == form.btnActualizarUsuario){
            int filaSeleccionada = form.tableUsuarios.getSelectedRow();
            if(filaSeleccionada == -1){
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila de la tabla para actualizar.");
                return;
            }            
            try{
                int idUsuario = Integer.parseInt(form.tableUsuarios.getValueAt(filaSeleccionada, 0).toString());
                String nombre = form.txtNombres.getText().trim();
                String apellido = form.txtApellidos.getText().trim();
                String dui = form.txtDUI.getText().trim();
                String telefono = form.txtTelefono.getText().trim();
                String username = form.txtUsuario.getText().trim();
                String password = form.txtPassword.getText().trim();
                String imagen = form.txtRutaImagen.getText().trim();
                String estado = form.comboEstado.getSelectedItem().toString();
                Roles rol = (Roles)form.comboRoles.getSelectedItem();
                
                if(password.isEmpty()){
                    JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacío.");
                    return;
                }
                
                String regexTelefono = "^[267]\\d{3}-?\\d{4}$";
                if (!telefono.matches(regexTelefono) && !telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Formato de teléfono incorrecto. Ej: 7123-4567");
                    return;
                }
                
                String regexDui = "^\\d{8}-\\d$";
                if (!dui.matches(regexDui) && !dui.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Formato de DUI incorrecto. Ej: 12345678-9");
                    return;
                }
                
                usuario.setIdUsuario(idUsuario);
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setDui(dui);
                usuario.setUsuario(username);
                usuario.setImagenURL(imagen);
                usuario.setRolCargo(rol.getNombre_cargo());
                usuario.setIdRol(rol.getIdRol());
                usuario.setPassword(password);
                usuario.setTelefono(telefono);
                usuario.setEstado(estado);
                if(dao.actualizarUsuario(usuario)){
                    JOptionPane.showMessageDialog(null, "Usuario actualizado con éxito.");
                    cargarTabla();
                    limpiarCampos();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al actualizar el usuario en la base de datos.");
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al procesar los datos: " + ex.getMessage());
                System.out.println(ex);
            }
        }
        
        if(e.getSource() == form.btnEliminarUsuario){
            int filaSeleccionada = form.tableUsuarios.getSelectedRow();
            if(filaSeleccionada == -1){
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila de la tabla para eliminar.");
                return;
            }
            try{
                int idUsuario = Integer.parseInt(form.tableUsuarios.getValueAt(filaSeleccionada, 0).toString());
                int confirmarEliminacion = JOptionPane.showConfirmDialog(null,
                        "¿Está seguro que desea eliminar este usuario?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                if(confirmarEliminacion == JOptionPane.YES_OPTION){
                    if(dao.eliminarUsuario(idUsuario)){
                        JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
                        cargarTabla();
                        limpiarCampos();
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al eliminar el usuario. Es posible que tenga registros asociados.");
                    }
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al intentar eliminar: " + ex.getMessage());
                System.out.println(ex);
            }
        }
        
        if(e.getSource() == form.btnLimpiar){
            limpiarCampos();
        }
        
        if (e.getSource() == form.btnVerInfo) {
            int filaSeleccionada = form.tableUsuarios.getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un usuario de la tabla para ver su información.");
                return;
            }

            try {
                Vistas.FrmUsuarioModal modal = new Vistas.FrmUsuarioModal();

                modal.txtNombresEmpleado.setText(form.tableUsuarios.getValueAt(filaSeleccionada, 1).toString());
                modal.txtApellidosEmpleado.setText(form.tableUsuarios.getValueAt(filaSeleccionada, 2).toString());
                modal.txtDUIEmpleado.setText(form.tableUsuarios.getValueAt(filaSeleccionada, 3).toString());
                modal.txtTelefonoEmpleado.setText(form.tableUsuarios.getValueAt(filaSeleccionada, 4).toString());
                modal.txtUsuarioEmpleado.setText(form.tableUsuarios.getValueAt(filaSeleccionada, 5).toString());
                modal.txtRolEmpleado.setText(form.tableUsuarios.getValueAt(filaSeleccionada, 9).toString());
                modal.txtEstadoEmpleado.setText(form.tableUsuarios.getValueAt(filaSeleccionada, 8).toString());

                String rutaImagen = form.tableUsuarios.getValueAt(filaSeleccionada, 7).toString();

                if (rutaImagen != null && !rutaImagen.isEmpty()) {
                    java.io.File archivoImagen = new java.io.File(rutaImagen);

                    if (archivoImagen.exists()) {
                        javax.swing.ImageIcon iconoOriginal = new javax.swing.ImageIcon(rutaImagen);

                        java.awt.Image imgEscalada = iconoOriginal.getImage().getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);

                        modal.lblImagenPreviewEmpleado.setIcon(new javax.swing.ImageIcon(imgEscalada));
                        modal.lblImagenPreviewEmpleado.setText("");
                    } else {
                        modal.lblImagenPreviewEmpleado.setIcon(null);
                        modal.lblImagenPreviewEmpleado.setText("Imagen no encontrada");
                    }
                } else {
                    modal.lblImagenPreviewEmpleado.setIcon(null);
                    modal.lblImagenPreviewEmpleado.setText("Sin imagen");
                }

                form.getDesktopPane().add(modal);
                modal.setVisible(true);
                modal.toFront();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al abrir el modal de usuario: " + ex.getMessage());
                System.out.println(ex);
            }
        }
        
        if (e.getSource() == form.btnExportar) {
            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setDialogTitle("Seleccionar ubicación para guardar");

            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos CSV (*.csv)", "csv"));

            int seleccion = fileChooser.showSaveDialog(form);

            if (seleccion == javax.swing.JFileChooser.APPROVE_OPTION) {
                java.io.File archivo = fileChooser.getSelectedFile();
                String rutaArchivo = archivo.getAbsolutePath();
                
                if (!rutaArchivo.toLowerCase().endsWith(".csv")) {
                    rutaArchivo += ".csv";
                }

                try (java.io.BufferedWriter bw = new java.io.BufferedWriter(
                        new java.io.OutputStreamWriter(new java.io.FileOutputStream(rutaArchivo), java.nio.charset.StandardCharsets.UTF_8))) {
                    
                    // Esto es un BOM (Byte Order Mark). Obliga a Excel a leer el archivo en UTF-8 para que la "ñ" se vea perfecta.
                    bw.write("\ufeff");

                    // 1. Escribir los encabezados de las columnas
                    for (int i = 0; i < form.tableUsuarios.getColumnCount(); i++) {
                        bw.write(form.tableUsuarios.getColumnName(i));
                        if (i < form.tableUsuarios.getColumnCount() - 1) {
                            bw.write(";"); // 🔥 CAMBIO: Usamos punto y coma
                        }
                    }
                    bw.newLine();

                    for (int i = 0; i < form.tableUsuarios.getRowCount(); i++) {
                        for (int j = 0; j < form.tableUsuarios.getColumnCount(); j++) {
                            Object valorCelda = form.tableUsuarios.getValueAt(i, j);
                            String textoCelda = (valorCelda != null) ? valorCelda.toString() : "";
                            
                            // Si el texto tiene punto y coma, lo envolvemos en comillas para que no rompa la columna
                            if (textoCelda.contains(";")) {
                                textoCelda = "\"" + textoCelda + "\"";
                            }
                            
                            bw.write(textoCelda);
                            
                            if (j < form.tableUsuarios.getColumnCount() - 1) {
                                bw.write(";"); // 🔥 CAMBIO: Usamos punto y coma
                            }
                        }
                        bw.newLine();
                    }

                    JOptionPane.showMessageDialog(form, "¡Datos exportados exitosamente a CSV!");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(form, "Error al exportar el archivo: " + ex.getMessage());
                    System.out.println(ex);
                }
            }
        }
    }
    
    public void cargarDatosFilaSeleccionada(){
        int filaSeleccionada = form.tableUsuarios.getSelectedRow();
        if(filaSeleccionada >= 0){
            String nombre = form.tableUsuarios.getValueAt(filaSeleccionada, 1).toString();
            String apellido = form.tableUsuarios.getValueAt(filaSeleccionada, 2).toString();
            String dui = form.tableUsuarios.getValueAt(filaSeleccionada, 3).toString();
            String username = form.tableUsuarios.getValueAt(filaSeleccionada, 5).toString();
            String imagen = form.tableUsuarios.getValueAt(filaSeleccionada, 7).toString();
            String rol = form.tableUsuarios.getValueAt(filaSeleccionada, 9).toString();
            String password = form.tableUsuarios.getValueAt(filaSeleccionada, 6).toString();
            String telefono = form.tableUsuarios.getValueAt(filaSeleccionada, 4).toString();
            String estado = form.tableUsuarios.getValueAt(filaSeleccionada, 8).toString();
            form.txtNombres.setText(nombre);
            form.txtApellidos.setText(apellido);
            form.txtDUI.setText(dui);
            form.txtUsuario.setText(username);
            form.txtRutaImagen.setText(imagen);
            form.comboRoles.setSelectedItem(rol);
            form.txtPassword.setText(password);
            form.txtTelefono.setText(telefono);
            form.comboEstado.setSelectedItem(estado);
            
            form.btnGuardarUsuario.setEnabled(false);
        }
    }
}
