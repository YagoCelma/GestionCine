package model;

import java.sql.Date;

public class Emision {
    private int id;
    private String nombre;
    private int duracion;
    private Date fecha_inicio_emision;
    private Date fecha_fin_emision;
    private int idPelicula;

    public Emision() {}

    public Emision(String nombre, int duracion, Date fecha_inicio_emision, Date fecha_fin_emision, int idPelicula) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.fecha_inicio_emision = fecha_inicio_emision;
        this.fecha_fin_emision = fecha_fin_emision;
        this.idPelicula = idPelicula;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getDuracion() { return duracion; }
    public Date getFecha_inicio_emision() { return fecha_inicio_emision; }
    public Date getFecha_fin_emision() { return fecha_fin_emision; }
    public int getIdPelicula() { return idPelicula; }

    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDuracion(int duracion) { this.duracion = duracion; }
    public void setFecha_inicio_emision(Date fecha_inicio_emision) { this.fecha_inicio_emision = fecha_inicio_emision; }
    public void setFecha_fin_emision(Date fecha_fin_emision) { this.fecha_fin_emision = fecha_fin_emision; }
    public void setIdPelicula(int idPelicula) { this.idPelicula = idPelicula; }
}