package model;

public class Entrada {
        
    private int id;
    private int asiento;
    private int precio;
    private String tipo;
    private String fecha;
    private String hora;
    private String nombrePelicula;
    private String sala;
    
    public Entrada(int precio, int asiento, String tipo, String fecha, String hora, String nombrePelicula, String sala) {
        
        this.precio = precio;
        this.asiento = asiento;
        this.tipo = tipo;
        this.fecha = fecha;
        this.hora = hora;
        this.nombrePelicula = nombrePelicula;
        this.sala = sala;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }   
    public int getAsiento() {
        return asiento;
    }
    public void setAsiento(int asiento) {
        this.asiento = asiento;
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
