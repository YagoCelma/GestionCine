package model;

import java.sql.Date;
import java.sql.Time;

public class Salas_peliculas{

    private int id;
    private String nombre;
    private Date fecha;
    private Time hora_inicio;
    private Time hora_fin;
    private int id_sala;
    private int id_pelicula;
    private int id_entrada;

    public Salas_peliculas(){}

    public Salas_peliculas(String nombre, Date fecha, Time hora_inicio, Time hora_fin ){
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
    }

    public int getId(){ return id ; }
    public String getNombre(){ return nombre; }
    public Date getFecha(){ return fecha ; }
    public Time getHora_inicio(){ return hora_inicio ; }
    public Time getHora_fin(){ return hora_fin; }
    public int getId_sala(){ return id_sala; }
    public int getId_pelicula(){ return id_pelicula; }
    public int getId_entrada(){ return id_entrada; }

    public void setId(int id){ this.id = id; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    public void setFecha(Date fecha){ this.fecha = fecha; }
    public void setHora_inicio(Time hora_inicio){ this.hora_inicio = hora_inicio ; }
    public void setHora_fin(Time hora_fin){ this.hora_fin = hora_fin; }
    public void setId_sala(int id_sala){ this.id_sala = id_sala ;}
    public void setId_pelicula(int id_pelicula){ this.id_pelicula = id_pelicula ;}
    public void setId_entrada(int id_entrada){ this.id_entrada = id_entrada ;}

    @Override
    public String toString(){
        return "Salas_peliculas{" +
        "id = "+ id +
        ", nombre =" + nombre + '\'' +
        ", fecha =" + fecha + '\'' +
        ", hora_inicio = " + hora_inicio+ '\'' +
        ", hora_fin =" + hora_fin+ '\'' +
        ", Id_sala =" + id_sala + '\'' +
        ", Id_pelicula =" + id_pelicula+ '\'' +
        ", Id_entrada =" + id_entrada + '\'' +
        "}";
    }

}