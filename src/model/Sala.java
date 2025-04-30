package model;

public class Sala {
    
    private int id;
    private int capacidad;

    public Sala(int capacidad){
        this.capacidad = capacidad;
    }

    public Sala(){
        
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getCapacidad() {return capacidad;}
    public void setCapacidad(int capacidad) {this.capacidad = capacidad;}
}
