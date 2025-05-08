package model;

public class Cliente {
    
    private int id;
    private String nombre;
    private String apellidos;
    private String dni;
    private String telefono;
    private String email;

    public Cliente(String nombre, String apellidos,String dni, String telefono, String email){

        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.email = email;
    }

    public Cliente(){
        
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDNI() {return dni;}

    public void setDNI(String dni) {this.dni = dni;}

    public String getApellido() {return apellidos;}

    public void setApellido(String apellido) {this.apellidos = apellidos;}

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}
    
    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}

