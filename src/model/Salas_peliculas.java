package model;

import java.sql.Date;
import java.sql.Time;

public class Salas_peliculas {

    private int id;
    private Date fecha_inicio_emision;
    private Date fecha_fin_emision;
    private Time hora_inicio;
    private Time hora_fin;
    private int id_sala;
    private int id_pelicula;

    public Salas_peliculas() {}

    public Salas_peliculas(Date fecha_inicio_emision, Date fecha_fin_emision,Time hora_inicio, Time hora_fin,int id_sala, int id_pelicula) {
        this.fecha_inicio_emision = fecha_inicio_emision;
        this.fecha_fin_emision = fecha_fin_emision;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.id_sala = id_sala;
        this.id_pelicula = id_pelicula;
    }

    public int getId() { return id; }
    public Date getFecha_inicio_emision() { return fecha_inicio_emision; }
    public Date getFecha_fin_emision() { return fecha_fin_emision; }
    public Time getHora_inicio() { return hora_inicio; }
    public Time getHora_fin() { return hora_fin; }
    public int getId_sala() { return id_sala; }
    public int getId_pelicula() { return id_pelicula; }

    public void setId(int id) { this.id = id; }
    public void setFecha_inicio_emision(Date fecha_inicio_emision) { this.fecha_inicio_emision = fecha_inicio_emision; }
    public void setFecha_fin_emision(Date fecha_fin_emision) { this.fecha_fin_emision = fecha_fin_emision; }
    public void setHora_inicio(Time hora_inicio) { this.hora_inicio = hora_inicio; }
    public void setHora_fin(Time hora_fin) { this.hora_fin = hora_fin; }
    public void setId_sala(int id_sala) { this.id_sala = id_sala; }
    public void setId_pelicula(int id_pelicula) { this.id_pelicula = id_pelicula; }

    @Override
    public String toString() {
        return "Salas_peliculas{" +
                "id=" + id +
                ", fecha_inicio_emision=" + fecha_inicio_emision +
                ", fecha_fin_emision=" + fecha_fin_emision +
                ", hora_inicio=" + hora_inicio +
                ", hora_fin=" + hora_fin +
                ", id_sala=" + id_sala +
                ", id_pelicula=" + id_pelicula +
                '}';
    }
}