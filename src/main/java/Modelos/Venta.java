package Modelos;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    private int idVenta;
    private String tipoComprobante;
    private String numeroComprobante;
    private int idCliente;
    private int idUsuario;
    private String estado;
    
    // Si tu tabla VENTAS tiene estos campos, los usaremos. Si no, se mantienen en memoria para cálculos.
    private double subtotal;
    private double iva;
    private double descuento;
    private double totalFinal;
    
    private List<DetalleVenta> detalles;

    public Venta() {
        this.detalles = new ArrayList<>();
    }

    // Getters y Setters
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public String getTipoComprobante() { return tipoComprobante; }
    public void setTipoComprobante(String tipoComprobante) { this.tipoComprobante = tipoComprobante; }

    public String getNumeroComprobante() { return numeroComprobante; }
    public void setNumeroComprobante(String numeroComprobante) { this.numeroComprobante = numeroComprobante; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }

    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }

    public double getTotalFinal() { return totalFinal; }
    public void setTotalFinal(double totalFinal) { this.totalFinal = totalFinal; }

    public List<DetalleVenta> getDetalles() { return detalles; }
    public void agregarDetalle(DetalleVenta detalle) { this.detalles.add(detalle); }
}