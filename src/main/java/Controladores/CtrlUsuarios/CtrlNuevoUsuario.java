/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.CtrlUsuarios;

import Modelos.Roles;
import Modelos.Usuario;
import Modelos.UsuarioDAO;
import Vistas.FrmNuevoUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author axele
 */
public class CtrlNuevoUsuario implements ActionListener{
    private Usuario usuario;
    private FrmNuevoUsuario form;
    private UsuarioDAO dao;
    private ArrayList<Usuario> usuarios;

    public CtrlNuevoUsuario(Usuario usuario, FrmNuevoUsuario form, UsuarioDAO dao) {
        this.usuario = usuario;
        this.form = form;
        this.dao = dao;
        
        this.form.btnGuardarUsuario.addActionListener(this);
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
                    limpiarCampos();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al crear el Usuario.");
                }
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error en el formato de los datos.");
                System.out.println(ex);
            }
        }
    }
}
