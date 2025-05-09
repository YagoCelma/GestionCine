package model;

public class Sala {
    
    private int id;
    private int numeroFilas;
    private int numeroColumnas;

    public Sala(int numeroFilas, int numeroColumnas){
        this.numeroFilas = numeroFilas;
        this.numeroColumnas = numeroColumnas;
    }

    public Sala(){
        
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    
    public int getNumeroFilas() {return numeroFilas;}
    public void setNumeroFilas(int numeroFilas) {this.numeroFilas = numeroFilas;}

    public int getNumeroColumnas() {return numeroColumnas;}
    public void setNumeroColumnas(int numeroColumnas) {this.numeroColumnas = numeroColumnas;}

        @Override
    public String toString() {
        return "Sala{" +
               "id=" + id +
               ", numeroFilas=" + numeroFilas +
               ", numeroColumnas=" + numeroColumnas +
               '}';
    }
}
