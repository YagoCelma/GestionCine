package model;

public class Cartelera {

    String titulo;

    public Cartelera(){}

    public Cartelera(String titulo){
        this.titulo = titulo;
    }

    public String getTitulo(){ return titulo; }
    public void setTitulo(String titulo){ this.titulo = titulo; }

    @Override
    public String toString(){
        return "Cartelera{"+
        "Titulo: " +  titulo +
        "}";
    }
    
}
