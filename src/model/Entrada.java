package model;

public class Entrada {
        
    private int idEntrada = 0;
    private int precio = 0;
    private String tipo = null;
    private String fecha = null;
    private String hora = null;
    private String tipoEntrada = null;
    private String nombrePelicula = null;
    private String sala = null;
    
    public Entrada(int idEntrada, int precio, String tipo, String fecha, String hora, String tipoEntrada, String nombrePelicula, String sala) {
        this.idEntrada = idEntrada;
        this.precio = precio;
        this.tipo = tipo;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoEntrada = tipoEntrada;
        this.nombrePelicula = nombrePelicula;
        this.sala = sala;
    }
    public int getIdEntrada() {
        return idEntrada;
    }
    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }   
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public String getTipoEntrada() {
        return tipoEntrada;
    }
    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }
    public String getNombrePelicula() {
        return nombrePelicula;
    }
    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }
    public String getSala() {
        return sala;
    }
    public void setSala(String sala) {
        this.sala = sala;
    }
    
}
