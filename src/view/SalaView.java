package view;

import model.Sala;
import dao.SalaDAO;
import java.util.*;

public class SalaView {
    
    Scanner sc = new Scanner(System.in);

    public void menuSala(){

        int opcion;
        do { 
            System.out.println("Menu de Salas");
            System.out.println("1. Añadir sala");
            System.out.println("2. Borrar sala");
            System.out.println("3. Modificar sala");
            System.out.println("4. Mostrar sala");
            System.out.println("5. Listar salas");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> crearSala();
                case 2-> borrarSala();
                case 3-> modificarSala();
                case 4-> mostrarSala();
                case 5-> listarSalas();
                default -> System.out.println("Opcion no valida, vuelva a intentarlo");
            }
        } while (opcion != 6);
    }

    public void crearSala(){
        System.out.println("Indique la capacidad de la sala");
        int capacidad = sc.nextInt();
        sc.nextLine();

        Sala sala = new Sala(capacidad);
        SalaDAO salaDAO = new SalaDAO();
        salaDAO.crearSala(sala);
        System.out.println("Se ha creado la sala correctamente");
    }

    public void borrarSala(){
        System.out.println("Indique el numero de ID de la sala");
        int id = sc.nextInt();
        sc.nextLine();
    }
}
