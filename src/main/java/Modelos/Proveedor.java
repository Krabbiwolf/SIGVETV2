/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author ASUS
 */
public class Proveedor {
    private int id;
    private String nombre;
    private String telefono;
    private String estado;
    private String rutaImagen;

    public Proveedor(int id, String nombre, String telefono, String estado,String rutaImagen) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.estado = estado;
        this.rutaImagen=rutaImagen;
    }

    public Proveedor(String nombre, String telefono, String estado, String rutaImagen) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.estado = estado;
        this.rutaImagen = rutaImagen;
    }

    

    public Proveedor() {
    }
    
    public String getRutaImagen() {
    return rutaImagen;
}

public void setRutaImagen(String rutaImagen) {
    this.rutaImagen = rutaImagen;
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    
}
