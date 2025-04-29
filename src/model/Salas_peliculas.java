package model;

import java.sql.Date;
import java.sql.Time;

public class Salas_peliculas{

    private int id;
    private String nombre;
    private int duracion;
    private Date fecha_inicio_emision;
    private Date fecha_fin_emision;
    private Time hora_inicio;
    private Time hora_fin;
    private int id_sala;
    private int id_pelicula;
    private int id_entrada;

    public Salas_peliculas(){}

    public Salas_peliculas(String nombre,int duracion, Date fecha_inicio_emision, Date fecha_fin_emision){
        this.nombre = nombre;
        this.duracion = duracion;
        this.fecha_inicio_emision = fecha_inicio_emision;
        this.fecha_fin_emision = fecha_fin_emision;
    }

    public int getId(){ return id ; }
    public String getNombre(){ return nombre; }
    public int getDuracion(){ return duracion; }
    public Time getHora_inicio(){ return hora_inicio ; }
    public Time getHora_fin(){ return hora_fin; }
    public int getId_sala(){ return id_sala; }
    public int getId_pelicula(){ return id_pelicula; }
    public int getId_entrada(){ return id_entrada; }

    public void setId(int id){ this.id = id; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    public void setDuracion(int duracion){ this.duracion = duracion; }
    public void setFecha_inicio_emision(Date fecha_inicio_emision){ this.fecha_inicio_emision = fecha_inicio_emision; }
    public void setFecha_fin_emision(Date fecha_fin_emision){ this.fecha_fin_emision = fecha_fin_emision; }
    public void setHora_inicio(Time hora_inicio){ this.hora_inicio = hora_inicio ; }
    public void setHora_fin(Time hora_fin){ this.hora_fin = hora_fin; }
    public void setId_sala(int id_sala){ this.id_sala = id_sala ;}
    public void setId_pelicula(int id_pelicula){ this.id_pelicula = id_pelicula ;}
    public void setId_entrada(int id_entrada){ this.id_entrada = id_entrada ;}

    @Override
    public String toString(){ //cambiar hora inicio y fin ya que es automatico
        return "Salas_peliculas{" +
        "id = "+ id +
        ", nombre =" + nombre + '\'' +
        ", fecha_inicio_emision =" + fecha_inicio_emision + '\'' +
        ", fecha_fin_emision =" + fecha_fin_emision + '\'' +
        ", hora_inicio = " + hora_inicio+ '\'' +
        ", hora_fin =" + hora_fin+ '\'' +
        ", Id_sala =" + id_sala + '\'' +
        ", Id_pelicula =" + id_pelicula+ '\'' +
        ", Id_entrada =" + id_entrada + '\'' +
        "}";
    }

}