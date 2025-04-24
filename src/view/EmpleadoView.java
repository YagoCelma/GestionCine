package view;

import dao.EmpleadoDao;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Empleado;

public class EmpleadoView {

    private final Scanner sc = new Scanner(System.in);
    private final EmpleadoDao dao = new EmpleadoDao();
    
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
            sc.nextLine();

            switch(respuesta){
                case 1 -> this.agregarEmpleado();
                case 2 -> this.eliminarEmpleado();
                case 3 -> this.mostrarEmpleados();
                case 4 -> this.buscarPorId();
                case 5 -> this.actualizarEmpleado();
                case 6 -> System.out.println("Saliendo del menu de empleados...");
                default -> System.out.println("Opción no valida, intentelo de nuevo");
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
        EmpleadoDao empleadoDao = new EmpleadoDao();
        empleadoDao.añadirEmpleado(empleadoDao);
        System.out.println("Empleado añadido con ID: " + empleado.getId());
    }

    public void eliminarEmpleado(){
        System.out.println("Introduzca el Id del empleado a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        EmpleadoDao empleadoDao = new EmpleadoDao();
        if(empleadoDao.eliminarEmpleado(id)){
            System.out.println("Empleado eliminado correctamente");
        } else {
            System.out.println("No se ha encontrado ningun empleado");
        }
    }

    public void mostrarEmpleados(){
        ArrayList<Empleado> empleados = dao.mostrarEmpleados();
        System.out.println("\nLista de empleados: ");
        for (Empleado emp : empleados){
            System.out.println(emp);
        }
    }

    public void buscarPorId(){
        System.out.println("Introduzca el ID del empleado a buscar");
        int id = sc.nextInt();
        sc.nextLine();
        Empleado emp = dao.obtenerPorId(id);
        if(emp != null){
            System.out.println(emp);
        } else{
            System.err.println("No se encontro ningun empleado con ese ID");
        }
    }

    public void actualizarEmpleado(){
        System.out.println("Introduce el ID del epleado a actualizar");
        int id = sc.nextInt();
        sc.nextLine();
        EmpleadoDao empleadoDao = new EmpleadoDao();
        Empleado empleado = empleadoDao.obtenerPorId(id);
        if (empleado != null){
            System.out.println("Introduce el nuevo nombre del empleado");
            String nombre = sc.nextLine();
            System.out.println("Introduce el nuevo apelido del empleado");
            String apellido = sc.nextLine();
            System.out.println("Introduce el nuevo telefono del empleado");
            int tlf = sc.nextInt();
            sc.nextLine();
            System.out.println("Introduce el nuevo email del empleado");
            String email = sc.nextLine();
            System.out.println("Introduce el nuevo puesto del empleado");
            String puesto = sc.nextLine();
            System.out.println("Introduce el nuevo salario del empleado");
            double salario = sc.nextDouble();
            sc.nextLine();
            System.out.println("Introduce la nueva fecha de contratacion del empleado");
            String fecha = sc.nextLine();
            LocalDate localDate = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
            Date fechaContratacion = Date.valueOf(localDate); 
            
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setTelefono(tlf);
            empleado.setEmail(email);
            empleado.setPuesto(puesto);
            empleado.setSalario(salario);
            empleado.setFechaContratacion(fechaContratacion);
            
            empleadoDao.actualizarEmpleado(empleado);
            System.out.println("Empleado actualizado correctamente");
            
        } else {
            System.out.println("No se encontro ningun empleado con ese ID");
        }



    }


}
