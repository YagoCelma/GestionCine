package model;

public class Proveedor{

    private int id;
    private String nombre;
    private String tipo;
    private int telefono;
    private String email;

    public Proveedor(){

    }
    
    public Proveedor(String nombre, String tipo, int telefono, String email){

        this.nombre = nombre;
        this.tipo = tipo;
        this.telefono = telefono;
        this.email = email;
    }
    
    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}

    public String getTipo(){return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    public int getTelefono() {return telefono;}
    public void setTelefono(int telefono) {this.telefono = telefono;}

    public String getEmail(){return email;}
    public void setEmail(String email) {this.email = email;}
}