/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author axele
 */
public class Roles {
    private int idRol;
    private String nombre_cargo;
    private String descripcion;

    public Roles() {
    }

    public Roles(int idRol, String nombre_cargo, String descripcion) {
        this.idRol = idRol;
        this.nombre_cargo = nombre_cargo;
        this.descripcion = descripcion;
    }

    public Roles(String nombre_cargo, String descripcion) {
        this.nombre_cargo = nombre_cargo;
        this.descripcion = descripcion;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre_cargo() {
        return nombre_cargo;
    }

    public void setNombre_cargo(String nombre_cargo) {
        this.nombre_cargo = nombre_cargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return nombre_cargo;
    }
    
}