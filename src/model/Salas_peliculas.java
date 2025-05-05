package model;

import java.sql.Time;

public class Salas_peliculas {

    private int id;
    private String nombrePelicula;
    private Time hora_inicio;
    private Time hora_fin;
    private int id_sala;
    private double precioBase;

    public Salas_peliculas() {}

    public Salas_peliculas(String nombrePelicula,Time hora_inicio, Time hora_fin,int id_sala, double precioBase) {
        this.nombrePelicula = nombrePelicula;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.id_sala = id_sala;
        this.precioBase = precioBase;
        
    }

    public int getId() { return id; }
    public String getNombrePelicula() { return nombrePelicula; }
    public Time getHora_inicio() { return hora_inicio; }
    public Time getHora_fin() { return hora_fin; }
    public int getId_sala() { return id_sala; }
    public double getPrecioBase() { return precioBase; }
    

    public void setId(int id) { this.id = id; }
    public void setNombrePelicula(String nombrePelicula) { this.nombrePelicula = nombrePelicula; }
    public void setHora_inicio(Time hora_inicio) { this.hora_inicio = hora_inicio; }
    public void setHora_fin(Time hora_fin) { this.hora_fin = hora_fin; }
    public void setId_sala(int id_sala) { this.id_sala = id_sala; }
    public void setPrecioBase(double precioBase ) { this.precioBase = precioBase; }

    @Override
    public String toString() {
        return "Salas_peliculas{" +
                "id=" + id +
                ", Nombre_pelicula=" + nombrePelicula +
                ", hora_inicio=" + hora_inicio +
                ", hora_fin=" + hora_fin +
                ", id_sala=" + id_sala +
                ", precio_base=" + precioBase +
                '}';
    }
}