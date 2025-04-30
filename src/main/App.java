package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import dao.ConexionDB;
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
        int opcion;
        do{
            System.out.println("Bienvenido al Gestor Cine");
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
