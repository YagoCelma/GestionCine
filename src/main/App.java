package main;

import dao.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import view.*;


public class App {

    public static void main(String[] args) throws Exception {
        
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

        ClienteView clienteView = new ClienteView();
        EmpleadoView empleadoView = new EmpleadoView();
        PeliculaView peliculasView = new PeliculaView();
        SalaView salaView = new SalaView();

        Scanner sc = new Scanner(System.in);
        int resp ; 
        int opcion;
        String admin = "administrator";
        String contraAdmin = "aws23@12jk";
        String emp = "empleado";
        String contraEmp = "añle.,45%,";
        
        do{
            System.out.println("1. Bienvenido a CineMax");
            System.out.println("                       ");
            System.out.println("2. Iniciar sesion como administrador");
            System.out.println("3. Iniciar sesion como empleado");
            System.out.println("4. Salir");
            resp = sc.nextInt();
            
            switch(resp){
                case 1 -> {
                    System.out.println("Introduzca su usuario de administrador");
                    String administrador = sc.nextLine();
                    System.out.println("Introduca la contraseña de administrador");
                    String contraAdministrador = sc.nextLine();
                    if(administrador.equals(admin) && contraAdministrador.equals(contraAdmin)){
                        do{
                            System.out.println("Bienvenido al Gestor Cine como adiminstrador");
                            System.out.println("--------------------------------------------");
                            System.out.println("1. Cliente");
                            System.out.println("2. Empleado");
                            System.out.println("3. Pelicula");
                            System.out.println("4. Sala");
                            //System.out.println("4. Sala"); Aqui podria ir para crear la cartelera de la semana
                            System.out.println("5. Salir");
                            opcion = sc.nextInt();
                            sc.nextLine();
                
                            switch(opcion){
                                case 1-> clienteView.menuCliente();
                                case 2-> empleadoView.gestionEmpleados();
                                case 3-> peliculasView.menuPrincipal();
                                case 4-> salaView.menuSala();
                                case 5-> System.out.println("Hasta pronto...");
                                default->System.out.println("Valor no valido, vuelve a intentarlo");
                            }
                        }while(opcion != 5);

                    } else{
                        System.out.println("Introduzca un usuario y contraseña validos");
                        return;
                    }
                }

                case 2 -> {
                    System.out.println("Introduzca un usuario de empleado");
                    String empleado = sc.nextLine();
                    System.out.println("Introduca la contraseña de empleado");
                    String contraEmpleado = sc.nextLine();
                    if(empleado.equals(emp) && contraEmpleado.equals(contraEmp)){
                        System.out.println("Bienvenido al cine como empleado");
                        System.out.println("------------------------------- ");
                        System.out.println("1. Cliente");
                        System.out.println("2. Entradas");
                        System.out.println("");

                    }else {
                        System.out.println("Introduzca un usuario y contraseña validos");
                    }

                }
            }  
        }while(resp !=4);


        do{
            System.out.println("Bienvenido al Gestor Cine como adiminstrador");
            System.out.println("-------------------------");
            System.out.println("1. Cliente");
            System.out.println("2. Empleado");
            System.out.println("3. Pelicula");
            System.out.println("4. Sala");
            System.out.println("5. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> clienteView.menuCliente();
                case 2-> empleadoView.gestionEmpleados();
                case 3-> peliculasView.menuPrincipal();
                case 4-> salaView.menuSala();
                case 5-> System.out.println("Hasta pronto...");
                default->System.out.println("Valor no valido, vuelve a intentarlo");
            }
        }while(opcion != 5);
    }
}
