package view;

import dao.InventarioPeliculaDAO;
import dao.PeliculaDAO;
import java.util.List;
import java.util.Scanner;
import model.InventarioPelicula;
import model.Pelicula;

public class InventarioPeliculaView {
    Scanner sc = new Scanner(System.in);
    InventarioPeliculaDAO inventarioDAO = new InventarioPeliculaDAO();
    PeliculaDAO peliculaDAO = new PeliculaDAO();

    public void menuPrincipal() {
        boolean exito = true;

        do {
            System.out.println("Menú Principal");
            System.out.println("1. Añadir película al inventario");
            System.out.println("2. Eliminar película del inventario");
            System.out.println("3. Modificar inventario");
            System.out.println("4. Listar inventario");
            System.out.println("5. Volver atrás");
            System.out.println("6. Salir");

            int opcion = elegirOpcion();
            switch (opcion) {
                case 1 ->
                    anadirInventario();
                    
                case 2 ->
                    borrarInventario();
                    
                case 3 ->
                    modificarInventario();
                    
                case 4 ->
                    listarInventario();
                    
                case 5 ->
                    menuPrincipal();
                case 6 ->
                    System.exit(0);
                    
                default ->
                    System.out.println("Opción no válida");
                
            }
        } while (!exito);
    }

    private int elegirOpcion() {
        System.out.print("Elige una opción: ");
        return sc.nextInt();
    }

    private void anadirInventario() {
        try {

            System.out.print("ID de la película: ");
            int idPelicula = sc.nextInt();
            
            Pelicula pelicula = peliculaDAO.mostrarPeliculaByID(idPelicula);
            if (pelicula == null) {
                System.out.println("No existe una película con ese ID");
                return;
            }

            System.out.print("Número de copias: ");
            int copias = sc.nextInt();

            InventarioPelicula inventario = new InventarioPelicula(pelicula, copias);
            if (inventarioDAO.insertarInventario(inventario)) {
                System.out.println("La película se ha añadido al inventario correctamente");
            } else {
                System.out.println("Error al añadir la película al inventario");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void modificarInventario() {
        try {

            System.out.print("ID del inventario: ");
            int id = sc.nextInt();

            InventarioPelicula inventario = inventarioDAO.buscar(id);
            if (inventario == null) {
                System.out.println("No existe un inventario con ese ID");
                return;
            }

            System.out.print("Nuevo número de copias: ");
            int nuevasCopias = sc.nextInt();

            inventario.setCopias(nuevasCopias);
            inventario.setCopiasDisponibles(nuevasCopias);

            if (inventarioDAO.actualizarInventario(inventario)) {
                System.out.println("El inventario se ha actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el inventario");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarInventario() {
        try {
            List<InventarioPelicula> inventario = inventarioDAO.listar();
            if (inventario != null && !inventario.isEmpty()) {
                for (InventarioPelicula item : inventario) {
                    System.out.println("\nID: " + item.getId());
                    System.out.println("Película: " + item.getPelicula().getTitulo());
                    System.out.println("Copias totales: " + item.getCopias());
                    System.out.println("Copias disponibles: " + item.getCopiasDisponibles());
                    System.out.println("-------------------------");
                }
            } else {
                System.out.println("No hay películas en el inventario");
            }
        } catch (Exception e) {
            System.out.println("Error al listar el inventario: " + e.getMessage());
        }
    }

    private void borrarInventario() {
        try {
            System.out.print("ID del inventario: ");
            int id = sc.nextInt();

            InventarioPelicula inventario = inventarioDAO.buscar(id);
            if (inventario == null) {
                System.out.println("No existe un inventario con ese ID");
                return;
            }

            System.out.println("La película " + inventario.getPelicula().getTitulo() + " se eliminará del inventario");
            System.out.print("¿Quieres continuar? (si/no): ");
            String respuesta = sc.next().toLowerCase();

            if (respuesta.equals("si")) {
                if (inventarioDAO.eliminarInventario(id)) {
                    System.out.println("La película ha sido eliminada del inventario con éxito");
                } else {
                    System.out.println("Error al eliminar la película del inventario");
                }
            } else {
                System.out.println("Se ha cancelado la operación");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
} 