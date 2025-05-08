package view;

import dao.CarteleraDAO;
import dao.PeliculaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Cartelera;
import model.Pelicula;

public class CarteleraView {

    private final Scanner sc = new Scanner(System.in);
    private final PeliculaDAO peliculaDAO = new PeliculaDAO();
    private final CarteleraDAO carteleraDAO = new CarteleraDAO();
    
    public void gestionCartelera(){

        int opcion = 0;

        do { 
            System.out.println("Bienvenido a la cartelera ");
            System.out.println("1. Crear cartelera ");
            System.out.println("2. Eliminar pelicula de la cartelera ");
            System.out.println("3. Mostrar cartelera ");
            System.out.println("4. Salir ");
            System.out.println("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1 -> this.crearCartelera();
                case 2 -> this.eliminarPeliCartelera();
                case 3 -> this.mostrarCartelera();
                case 4 -> System.out.println("Saliendo de la cartelera...");
                default -> System.out.println("Opcion no valida, intentelo de nuevo");
            }
            
        } while (opcion != 4);
    }

    public void crearCartelera() {
        String resp = "si";
        try {
            do { 
                System.out.println("\n ¿Qué película desea añadir?");
                String titulo = sc.nextLine();
                Pelicula peli = peliculaDAO.mostrarPeliculaByTitulo(titulo);
                if (peli != null) {
                    Cartelera cartelera = new Cartelera(titulo);
                    carteleraDAO.añadirCartelera(cartelera);
                    System.out.println("Película añadida correctamente");
                } else {
                    System.out.println("Ingrese una película que sea propiedad del cine");
                }
                System.out.println("Desea seguir añadiendo peliculas? (si/no)"); 
                resp = sc.nextLine();
            } while (resp.equalsIgnoreCase("si"));
        } catch (SQLException e) {
            System.out.println("Error al buscar la película: " + e.getMessage());
        }
    }

    public void eliminarPeliCartelera(){
        System.out.println("Ingrese el titulo de la pelicula a eliminar");
        String titulo = sc.nextLine();
        if(carteleraDAO.eliminarPeliCartelera(titulo)){
            System.out.println("Pelicula eliminada de la cartelera");
        }else{
            System.out.println("No se encontro ninguna pelicula con ese titulo");
        }
    }

    public void mostrarCartelera(){
        ArrayList<Cartelera> cartelera = carteleraDAO.mostrarCartelera();
        for(Cartelera cartel : cartelera){
            System.out.println(cartel);
        }
    }
    
}
