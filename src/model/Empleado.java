import java.sql.Date;

public class Empleado {

    private int id;
    private String nombre;
    private String apellido;
    private int telefono;
    private String email;
    private String puesto;
    private double salario;
    private Date fechaContratacion;

    public Empleado(String nombre, String apellido, int telefono, String email, String puesto, double salario, Date fechaContratacion ){
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    public int getId(){return id; }
    public String getNombre(){ return  nombre; }
    public String getApellido(){ return apellido; }
    public int getTelefono(){ return telefono; }
    public String getEmail(){ return email; }
    public String getPuesto(){ return puesto; }
    public double getSalario(){ return salario; }
    public Date getFechaContratacion(){ return fechaContratacion; }

    public void setId(int id){ this.id = id; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    public void setApellido(String apellido){ this.apellido = apellido; }
    public void setTelefono(int telefono){ this.telefono = telefono; }
    public void setEmail(String email){ this.email = email; }
    public void setPuesto(String puesto){ this.puesto = puesto; }
    public void setSalario(double salario){ this.salario = salario; }
    public void setFechaContratacion(Date fechaContratacion){ this.fechaContratacion = fechaContratacion; }

    @Override
    public String toString(){
        return "Empleado{"+
        "id=" + id+
        ", nombre= "+ nombre + '\'' +
        ", apellido= "+ apellido + '\'' +
        ", telefono=" + telefono + '\'' +
        ", email=" + email + '\'' +
        ", puesto=" + puesto + '\'' +
        ", salario=" +salario+
        ", fechaContratacion=" +fechaContratacion+
        "}";
    }

}
