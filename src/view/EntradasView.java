package view;

import java.util.Scanner;

public class EntradasView {
    Scanner sc = new Scanner(System.in);
    
    
    public void menuEntrada(){
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

        Entrada entrada = new Entrada(precio, tipo, fecha, hora, tipoEntrada, nombrePelicula, sala);
        EntradaDAO entradaDAO = new EntradaDAO();
        entradaDAO.añadirEntrada(entrada);
        System.out.println("Entrada añadida correctamente con ID: " + entrada.getIdEntrada());

        
    }
    public void borrarEntrada(){
        System.out.println("Introduzca el ID de la entrada a borrar");
        int idEntrada = sc.nextInt();
        sc.nextLine();


        

        
    }
    public void modificarEntrada(){
        System.out.println("Introduzca el ID de la entrada a modificar");
        int idEntrada = sc.nextInt();
        sc.nextLine();
        
    }
    public void listarEntrada(){
        System.out.println("Lista de Entradas:");
        
    }

    public void buscarEntradaID(){
        System.out.println("Introduzca el ID de la entrada a buscar");
        int idEntrada = sc.nextInt();
        sc.nextLine();
        
    }
   
}