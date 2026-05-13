/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

public class Kardex {

    private int idMovimiento;
    private String fechaHora;
    private String tipoMovimiento;
    private String producto;
    private String codigoBarras;
    private String lote;
    private int cantidad;
    private String usuario;
    private String respaldoMotivo;

    public Kardex() {
    }

    public Kardex(int idMovimiento, String fechaHora, String tipoMovimiento, String producto,
                  String codigoBarras, String lote, int cantidad, String usuario, String respaldoMotivo) {
        this.idMovimiento = idMovimiento;
        this.fechaHora = fechaHora;
        this.tipoMovimiento = tipoMovimiento;
        this.producto = producto;
        this.codigoBarras = codigoBarras;
        this.lote = lote;
        this.cantidad = cantidad;
        this.usuario = usuario;
        this.respaldoMotivo = respaldoMotivo;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRespaldoMotivo() {
        return respaldoMotivo;
    }

    public void setRespaldoMotivo(String respaldoMotivo) {
        this.respaldoMotivo = respaldoMotivo;
    }
}
