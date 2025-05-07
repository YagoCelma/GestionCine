package main;

import dao.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import view.*;


public class App {

    public static void main(String[] args) throws Exception {

        ClienteView clienteView = new ClienteView();
        EmpleadoView empleadoView = new EmpleadoView();
        PeliculaView peliculasView = new PeliculaView();
        SalaView salaView = new SalaView();
        ProveedorView proveedorView = new ProveedorView();
        InventarioPeliculaView inventarioPeliculaView = new InventarioPeliculaView();
        EntradasView entradasView = new EntradasView();
        Salas_peliculasView salas_peliculasView = new Salas_peliculasView();
        CineConfigEditor cineConfigEditor = new CineConfigEditor();

        Scanner sc = new Scanner(System.in);
        int resp ; 
        int opcion;
        int opcion2;
        String admin = "administrator";
        String contraAdmin = "admin123";
        String emp = "empleado";
        String contraEmp = "empleado123";
        
    // Verificación de conexión inicial
        try (Connection conn = ConexionDB.getConnection()) {
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("Error al conectar a la base de datos.");
                return;
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
            return;
        }
        
        do{
            System.out.println("\n Bienvenido a CineMax");
            System.out.println("                       ");
            System.out.println("1. Iniciar sesion como administrador");
            System.out.println("2. Iniciar sesion como empleado");
            System.out.println("3. Salir");
            resp = sc.nextInt();
            sc.nextLine();
            
            switch(resp){
                case 1 -> {
                    System.out.println("Introduzca su usuario de administrador");
                    String administrador = sc.nextLine();
                    System.out.println("Introduca la contraseña de administrador");
                    String contraAdministrador = sc.nextLine();

                    if(administrador.equals(admin) && contraAdministrador.equals(contraAdmin)){
                        do{
                            System.out.println("                                            ");
                            System.out.println("Bienvenido al Gestor Cine como adiminstrador");
                            System.out.println("--------------------------------------------");
                            System.out.println("1. Cliente");
                            System.out.println("2. Empleado");
                            System.out.println("3. Pelicula");
                            System.out.println("4. Sala");
                            System.out.println("5. Proveedores");
                            System.out.println("6. Inventario peliculas");
                            System.out.println("7. Salas y peliculas ");
                            System.out.println("8. Configuración del cine");
                            System.out.println("9. Salir");
                            System.out.print("Seleccione una opción: ");
                            opcion = sc.nextInt();
                            sc.nextLine();
                
                            switch(opcion){
                                case 1-> clienteView.menuCliente();
                                case 2-> empleadoView.gestionEmpleados();
                                case 3-> peliculasView.menuPrincipal();
                                case 4-> salaView.menuSala();
                                case 5-> proveedorView.menuProveedor();
                                case 6 -> inventarioPeliculaView.menuPrincipal();
                                case 7 -> salas_peliculasView.gestionSalas_Peliculas();
                                case 8 ->cineConfigEditor.configuración();
                                case 9-> System.out.println("Hasta pronto...");
                                default->System.out.println("Valor no valido, vuelve a intentarlo");
                            }
                        }while(opcion != 9);
                    } else{
                        System.out.println("usuario o contraseña incorrectos");
                    }
                }

                case 2 -> {
                    System.out.println("Introduzca un usuario de empleado");
                    String empleado = sc.nextLine();
                    System.out.println("Introduca la contraseña de empleado");
                    String contraEmpleado = sc.nextLine();

                    if(empleado.equals(emp) && contraEmpleado.equals(contraEmp)){
                        do { 
                            System.out.println("                                ");
                            System.out.println("Bienvenido al cine como empleado");
                            System.out.println("------------------------------- ");
                            System.out.println("1. Cliente");
                            System.out.println("2. Entradas");
                            System.out.println("3. Consultar salas y peliculas");
                            System.out.println("4. Salir");
                            System.out.print("Seleccione una opción: ");
                            opcion2 = sc.nextInt();
                            sc.nextLine();

                            switch(opcion2){
                                case 1 -> clienteView.menuCliente();
                                case 2 -> entradasView.menuEntrada();
                                case 3 -> salas_peliculasView.mostrar();
                                case 4-> System.out.println("Hasta pronto...");
                                default->System.out.println("Valor no valido, vuelve a intentarlo");
                            }

                        } while (opcion2 != 4);
                    }else {
                        System.out.println("usuario o contraseña incorrectos");
                    }
                }

                case 3 -> System.out.println("Gracias por usar CineMax. ¡Hasta la próxima!");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            } 
            
        }while(resp !=3);
    }
}
