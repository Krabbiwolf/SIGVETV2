/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores.CtrlCompra;

import Modelos.Compra;
import Modelos.CompraDAO;
import Modelos.Producto;
import Modelos.Proveedor;
import Vistas.FrmRegistrarNuevaCompra;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author axele
 */
public class CtrlNuevaCompra implements ActionListener{
    private Compra compra;
    private FrmRegistrarNuevaCompra form;
    private CompraDAO dao;

    public CtrlNuevaCompra(Compra compra, FrmRegistrarNuevaCompra form, CompraDAO dao) {
        this.compra = compra;
        this.form = form;
        this.dao = dao;
        
        this.form.btnGuardarCompra.addActionListener(this);
    }
    
    public void limpiarCampos(){
        form.comboProveedor.setSelectedIndex(0);
        form.comboProducto.setSelectedIndex(0);
        form.SpinCantidadProductos.setValue(0);
        form.SpinPrecio.setValue(0.0);
    }
    
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == form.btnGuardarCompra){
            try{
                Proveedor proveedor = (Proveedor)form.comboProveedor.getSelectedItem();
                Producto producto = (Producto)form.comboProducto.getSelectedItem();
                int cantidad = (int)form.SpinCantidadProductos.getValue();
                double precio = (double)form.SpinPrecio.getValue();
                double precioSinIVA = precio - (precio*0.13);
                
                if(cantidad <= 0 || precio <= 0){
                    JOptionPane.showMessageDialog(null, "El precio y la cantidad no pueden ser negativos ni cero.");
                    return;
                }
                
                compra.setIdProveedor(proveedor.getId());
                compra.setIdUsuario(3);
                
                if(dao.registrarCompra(compra, producto.getIdProducto(), cantidad, precioSinIVA)){
                    JOptionPane.showMessageDialog(null, "Compra registrada correctamente.");
                    limpiarCampos();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al registrar compra.");
                }                
                
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error en el formato de los datos.");
                System.out.println(ex);
            }
        }
    }
}