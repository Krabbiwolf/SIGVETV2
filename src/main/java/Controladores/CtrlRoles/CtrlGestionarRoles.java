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
public class CtrlGestionarRoles implements ActionListener{
    private Roles rol;
    private FrmRolesPermisos form;
    private FrmNuevoUsuario form2;
    private RolesDAO dao;
    private ArrayList<Roles> roles;

    public CtrlGestionarRoles(Roles rol, FrmRolesPermisos form, FrmNuevoUsuario form2, RolesDAO dao) {
        this.rol = rol;
        this.form = form;
        this.form2 = form2;
        this.dao = dao;
        
        this.form.btnGuardarRol.addActionListener(this);
    }
    
    public void limpiarCampos(){
        form.txtRol.setText("");
        form.txtDescripcion.setText("");
    }
    
    public void cargarRoles(){
        roles = dao.listarRoles();
        form2.comboRoles.removeAllItems();
        
        for(Roles rol : roles){
            form2.comboRoles.addItem(rol);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == form.btnGuardarRol){
            try{
                String nombre_cargo = form.txtRol.getText();
                String descripcion = form.txtDescripcion.getText();
                
                if(nombre_cargo.isEmpty()){
                    JOptionPane.showMessageDialog(null, "El nombre del Rol es obligatorio.");
                    return;
                }
                
                if(descripcion.isEmpty()){
                    descripcion = "(Sin descripcion)";
                }
                
                rol.setNombre_cargo(nombre_cargo);
                rol.setDescripcion(descripcion);
                
                if(dao.registrarRol(rol)){
                    JOptionPane.showMessageDialog(null, "¡Rol creado exitosamente!");
                    limpiarCampos();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al crear el Rol.");
                }
                limpiarCampos();
                cargarRoles();
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error en el formato de los datos.");
                System.out.println(ex);
            }
        }
    }
}
