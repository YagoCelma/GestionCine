package model;

import java.sql.Date;

public class CompraButaca {
    private int id;
    private int idSesion;
    private int idCliente;
    private int numButaca;
    private double precio;
    private Date fechaCompra;
    private String estado; // CONFIRMADA, PENDIENTE, CANCELADA

    

    public CompraButaca(int idSesion, int idCliente, int numButaca, double precio) {
        this.idSesion = idSesion;
        this.idCliente = idCliente;
        this.numButaca = numButaca;
        this.precio = precio;
        this.fechaCompra = new Date(System.currentTimeMillis());
        this.estado = "PENDIENTE";
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(int idSesion) {
        this.idSesion = idSesion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getNumButaca() {
        return numButaca;
    }

    public void setNumButaca(int numButaca) {
        this.numButaca = numButaca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
} 