package view;

import dao.SalaDAO;
import java.util.*;
import model.Sala;

public class SalaView {
    
    Scanner sc = new Scanner(System.in);

    public void menuSala(){

        int opcion;
        do { 
            System.out.println("             ");
            System.out.println("Menu de Salas");
            System.out.println("1. Añadir sala");
            System.out.println("2. Borrar sala");
            System.out.println("3. Modificar sala");
            System.out.println("4. Mostrar sala");
            System.out.println("5. Listar salas");
            System.out.println("6. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> crearSala();
                case 2-> borrarSala();
                case 3-> modificarSala();
                case 4-> mostrarSala();
                case 5-> listarSalas();
                case 6-> System.out.println("Saliendo...");
                default -> System.out.println("Opcion no valida, vuelva a intentarlo");
            }
        } while (opcion != 6);
    }

    public void crearSala(){
        System.out.println("Indique el numero de filas de la sala");
        int numeroFilas = sc.nextInt();
        sc.nextLine();

        System.out.println("Indique el numero de columnas de la sala");
        int numeroColumnas = sc.nextInt();
        sc.nextLine();

        Sala sala = new Sala(numeroFilas, numeroColumnas);
        SalaDAO salaDAO = new SalaDAO();
        salaDAO.crearSala(sala);
        System.out.println("Se ha creado la sala correctamente");
    }

    public void borrarSala(){
        System.out.println("Indique el numero de ID de la sala");
        int id = sc.nextInt();
        sc.nextLine();

        SalaDAO salaDAO = new SalaDAO();
        
        if(salaDAO.borrarSala(id)){
            System.out.println("Se ha eliminado la sala correctamente");
        }else{
            System.out.println("Error al eliminar la sala");
        }
    }

    public void modificarSala(){
        System.out.println("Indique el ID de la sala");
        int id = sc.nextInt();
        sc.nextLine();
        
        SalaDAO salaDAO = new SalaDAO();
        Sala sala = salaDAO.salaPorID(id);

        if(sala != null){
            menuModificacion(sala);
        }else{
            System.out.println("Error, sala no encontrada");
        }
    }

    public void menuModificacion(Sala sala){

        int opcion;
        do{
            System.out.println("Menu de modificacion de las salas");
            System.out.println("Que quieres modificar?");
            System.out.println("1. Capacidad");
            System.out.println("2. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> modificarCapacidad(sala);
                case 2-> System.out.println("Saliendo...");
                default-> System.out.println("Valor no válido, vuelva a intentarlo");
            }

        }while(opcion != 2);
    }

    public void modificarCapacidad(Sala sala){
        System.out.println("Introduce el numero de filas de la sala");
        int numeroFilas = sc.nextInt();
        sc.nextLine();

        System.out.println("Introduce el numero de columnas de la sala");
        int numeroColumnas = sc.nextInt();
        sc.nextLine();
        
        sala.setNumeroFilas(numeroFilas);
        sala.setNumeroColumnas(numeroColumnas);
    }

    public void mostrarSala(){
        System.out.println("Indica el numero de sala");
        int id = sc.nextInt();
        sc.nextLine();

        SalaDAO salaDAO = new SalaDAO();
        Sala sala = salaDAO.salaPorID(id);
        
        if(sala != null){
            System.out.println(sala);
        }else{
            System.out.println("Error al encontrar la sala");
        }
    }

    public void listarSalas(){
        System.out.println("Listado de las salas");
        SalaDAO salaDAO = new SalaDAO();
        for(Sala sala : salaDAO.obtenerSala()){
            System.out.println(sala);
        }
    }
}
