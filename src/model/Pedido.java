package model;

import java.sql.Time;

public class Pedido {
    
    private int id;
    private int idProveedor;
    private Time fechaPedido;
    private int idArticulo;
    private int cantidad;
    private double precioTotal;

    public Pedido(){

    }

    public Pedido(int idProveedor, Time fechaPedido, int idArticulo, int cantidad, double precioTotal){

        this.idProveedor = idProveedor;
        this.fechaPedido = fechaPedido;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    public Pedido(int id, int idProveedor, Time fechaPedido, int idArticulo, int cantidad, double precioTotal){

        this.id = id;
        this.idProveedor = idProveedor;
        this.fechaPedido = fechaPedido;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    public int getId() { return id; } 
    public void setId(int id) { this.id = id; }

    public int getIdProveedor() { return idProveedor; } 
    public void setIdProveedor(int idProveedor) { this.idProveedor = idProveedor; }

    public Time getFechaPedido() { return fechaPedido; } 
    public void setFechaPedido(Time fechaPedido) { this.fechaPedido = fechaPedido; }

    public int getIdArticulo() { return idArticulo; } 
    public void setIdArticulo(int idArticulo) { this.idArticulo = idArticulo; }

    public int getCantidad() { return cantidad; } 
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioTotal() { return precioTotal; } 
    public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }
}
