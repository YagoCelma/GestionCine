package view;

import java.util.Scanner;

public class Entradas {
    Scanner sc = new Scanner(System.in);
    // Atributos de la clase Entradas
    private int idEntrada = 0;
    private int precio = 0;
    private String tipo = null;
    private String fecha = null;
    private String hora = null;
    private String tipoEntrada = null;
    private String nombrePelicula = null;
    private String sala = null;
    
    public void menuCliente(){
        int opcion;
        do{
            System.out.println("Menu de Entradas");
            System.out.println("1. Añadir entrada");
            System.out.println("2. Borrar entrada");
            System.out.println("3. Modificar entrada");
            System.out.println("4. Listar entradas");
            System.out.println("5. Buscar entrada por ID");
            System.out.println("6. Salir");
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            sc.nextLine();
    
            switch(opcion){
                case 1-> añadirEntrada();
                case 2-> borrarEntrada();
                case 3-> modificarEntrada();
                case 4-> listarEntrada();
                case 5 -> buscarEntradaID();
                default -> System.out.println("Seleccione una opcion que sea valida");
            }
        }while(opcion != 6);
    }
    public void añadirEntrada(){
        System.out.println("Introduzca el precio");
        int precio = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduzca el tipo de entrada");
        String tipo = sc.nextLine();
        System.out.println("Introduzca la fecha");
        String fecha = sc.nextLine();
        System.out.println("Introduzca la hora");
        String hora = sc.nextLine();
        System.out.println("Introduzca el tipo de entrada (normal o VIP)");
        String tipoEntrada = sc.nextLine();
        System.out.println("Introduzca el nombre de la pelicula");
        String nombrePelicula = sc.nextLine();
        System.out.println("Introduzca la sala");
        String sala = sc.nextLine();

        // Crear un objeto Entrada y añadirlo a la base de datos
    }
}