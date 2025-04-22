
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Empleado;

public class EmpleadoView {

    private final Scanner sc = new Scanner(System.in);
    
    public void gestionEmpleados(){
        int respuesta;
        do{
            System.out.println("\n Sistema de gestión de empleados");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Eliminar empleado");
            System.out.println("3. Mostrar empleados");
            System.out.println("4. Buscar empleado por ID");
            System.out.println("5. Actualizar empleado");
            System.out.println("6. Volver al menu principal");
            respuesta = sc. nextInt();

            switch(respuesta){
                case 1 -> agregarEmpleado();
            }

        } while(respuesta!= 6);
    }

    public void agregarEmpleado(){
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Apellido: ");
        String apellido = sc.nextLine();
        System.out.println("Telefono: ");
        int telefono = sc.nextInt();
        sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Puesto: ");
        String puesto = sc.nextLine();
        System.out.println("Salario: ");
        double salario = sc.nextDouble();
        sc.nextLine();
        System.out.println("Fecha contratacion (YYYY-MM-DD): ");
        String fecha = sc.nextLine();
        LocalDate localDate = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
        Date fechaContratacion = Date.valueOf(localDate); 
        
        Empleado empleado = new Empleado(nombre, apellido, telefono, email, puesto, salario, fechaContratacion);


    }

}
