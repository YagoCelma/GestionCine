package model;

public class Cliente {
    
    private int id;
    private String nombre;
    private String apellidos;
    private int telefono;
    private String email;

    public Cliente(String nombre, String apellidos, int telefono, String email){

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(){
        
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellidos;}

    public void setApellido(String apellido) {this.apellidos = apellidos;}

    public int getTelefono() {return telefono;}

    public void setTelefono(int telefono) {this.telefono = telefono;}
    
    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}
