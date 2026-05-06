/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.sql.Date;

/**
 *
 * @author axele
 */
public class Compra {
    private String numeroComprobante;
    private Date fechaHora;
    private String estado;
    private int idProveedor;
    private int idUsuario;

    public Compra() {
    }

    public Compra(Date fechaHora, String estado, int idProveedor, int idUsuario) {
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.idProveedor = idProveedor;
        this.idUsuario = idUsuario;
    }

    public Compra(String numeroComprobante, Date fechaHora, String estado, int idProveedor, int idUsuario) {
        this.numeroComprobante = numeroComprobante;
        this.fechaHora = fechaHora;
        this.estado = estado;
        this.idProveedor = idProveedor;
        this.idUsuario = idUsuario;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }    
    
}