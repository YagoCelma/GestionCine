package model;

import java.sql.Date;

public class Emision {
    int id;
    String nombre;
    int duracion;
    Date fecha_inicio_emision;
    Date fecha_fin_emision;

    public Emision(String nombre, int duracion, Date fecha_inicio_emision, Date fecha_fin_emision){
        this.nombre = nombre;
        this.duracion = duracion;
        this.fecha_inicio_emision = fecha_inicio_emision;
        this.fecha_fin_emision = fecha_fin_emision;
    }

    public int getId(){ return id; }
    public String getNombre(){ return nombre; }
    public int getDuracion(){ return duracion; }
    public Date getFecha_inicio_emision(){ return fecha_inicio_emision; }
    public Date getFecha_fin_emision(){ return fecha_fin_emision; }

}
