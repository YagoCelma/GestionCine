package model;

public class Entrada {
        
    private int id;
    private int idSalaPelicula;
    private int asiento;
    private double precio;
    private String dniSocio; 

    
    public Entrada(int idSalaPelicula, int asiento, double precio) {
        
        this.idSalaPelicula = idSalaPelicula;
        this.asiento = asiento;
        this.precio = precio;
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

    public int getidSalaPelicula() {
        return idSalaPelicula;
    }
    public void setidSalaPelicula(int idSalaPelicula) {
        this.idSalaPelicula = idSalaPelicula;
    }

    public double getPrecio(){
        return precio;
    }
    public void setPrecio(double precio){this.precio = precio;}
    public void setDniSocio(String dniSocio) { this.dniSocio = dniSocio; }

        @Override
    public String toString() {
        return "Entrada{" +
               "id=" + id +
               ", idSalaPelicula=" + idSalaPelicula +
               ", asiento=" + asiento +
               ", precio=" + precio +
               ", dniSocio='" + dniSocio + '\'' +
               '}';
    }
    
}
