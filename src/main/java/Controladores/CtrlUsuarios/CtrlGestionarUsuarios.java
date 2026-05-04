/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.CtrlUsuarios;

import Modelos.Usuario;
import Modelos.UsuarioDAO;
import Vistas.FrmGestionarUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author axele
 */
public class CtrlGestionarUsuarios implements ActionListener {
    private Usuario usuario;
    private FrmGestionarUsuarios form;
    private UsuarioDAO dao;
    private ArrayList<Usuario> usuarios;

    public CtrlGestionarUsuarios(Usuario usuario, FrmGestionarUsuarios form, UsuarioDAO dao) {
        this.usuario = usuario;
        this.form = form;
        this.dao = dao;
        
        this.form.btnActualizarUsuario.addActionListener(this);
        this.form.btnEliminarUsuario.addActionListener(this);
        
        this.form.tableUsuarios.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e){
                cargarDatosFilaSeleccionada();
            }
        });
    }
    
    public void cargarTabla(){
        DefaultTableModel modeloTabla = (DefaultTableModel) Vistas.FrmGestionarUsuarios.tableUsuarios.getModel();
        modeloTabla.setRowCount(0);
        
        usuarios = dao.listarUsuarios();
        Object[] fila = new Object[10];
        
        for(Usuario usuario : usuarios){
            fila[0] = usuario.getIdUsuario();
            fila[1] = usuario.getNombre();
            fila[2] = usuario.getApellido();
            fila[3] = usuario.getDui();
            fila[4] = usuario.getTelefono();
            fila[5] = usuario.getUsuario();
            fila[6] = usuario.getPassword();
            fila[7] = usuario.getImagenURL();
            fila[8] = usuario.getEstado();
            fila[9] = usuario.getRolCargo();
            
            modeloTabla.addRow(fila);
        }
    }
    
    public void limpiarCampos(){
        form.txtNuevaPassword.setText("");
        form.txtNuevoTelefono.setText("");
        form.comboNuevoEstado.setSelectedIndex(0);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == form.btnActualizarUsuario){
            int filaSeleccionada = form.tableUsuarios.getSelectedRow();
            
            if(filaSeleccionada == -1){
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila de la tabla para actualizar.");
                return;
            }
            
            try{
                int idUsuario = Integer.parseInt(form.tableUsuarios.getValueAt(filaSeleccionada, 0).toString());
                
                String password = form.txtNuevaPassword.getText().trim();
                String telefono = form.txtNuevoTelefono.getText().trim();
                String estado = form.comboNuevoEstado.getSelectedItem().toString();
                
                if(password.isEmpty()){
                    JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacío.");
                    return;
                }
                
                String regexTelefono = "^[267]\\d{3}-?\\d{4}$";
                if (!telefono.matches(regexTelefono) && !telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Formato de teléfono incorrecto. Ej: 7123-4567");
                    return;
                }
                
                usuario.setIdUsuario(idUsuario);
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
                    }else{
                        JOptionPane.showMessageDialog(null, "Error al eliminar el usuario. Es posible que tenga registros asociados.");
                    }
                }                
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error al intentar eliminar: " + ex.getMessage());
                System.out.println(ex);
            }
        }
    }
    
    public void cargarDatosFilaSeleccionada(){
        int filaSeleccionada = form.tableUsuarios.getSelectedRow();
        
        if(filaSeleccionada >= 0){
            String password = form.tableUsuarios.getValueAt(filaSeleccionada, 6).toString();
            String telefono = form.tableUsuarios.getValueAt(filaSeleccionada, 4).toString();
            String estado = form.tableUsuarios.getValueAt(filaSeleccionada, 8).toString();
            
            form.txtNuevaPassword.setText(password);
            form.txtNuevoTelefono.setText(telefono);
            form.comboNuevoEstado.setSelectedItem(estado);
        }
    }
}
