package Modelos;

/**
 * Modelo usado por el combo del módulo de Ajuste de Inventario.
 * Representa un lote activo con su producto, stock inicial y stock actual.
 */
public class LoteInventario {

    private int idLote;
    private String nombreProducto;
    private String numeroLote;
    private int stockInicial;
    private int stockActual;
    private double precioCompra;
    private double precioVenta;

    public LoteInventario() {
    }

    public LoteInventario(int idLote, String nombreProducto, String numeroLote,
                          int stockInicial, int stockActual,
                          double precioCompra, double precioVenta) {
        this.idLote = idLote;
        this.nombreProducto = nombreProducto;
        this.numeroLote = numeroLote;
        this.stockInicial = stockInicial;
        this.stockActual = stockActual;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        this.numeroLote = numeroLote;
    }

    public int getStockInicial() {
        return stockInicial;
    }

    public void setStockInicial(int stockInicial) {
        this.stockInicial = stockInicial;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Override
    public String toString() {
        return idLote + " - " + nombreProducto
                + " | Lote: " + numeroLote
                + " | Stock actual: " + stockActual;
    }
}
