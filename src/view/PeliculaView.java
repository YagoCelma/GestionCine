package view;

import dao.PeliculaDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import model.Pelicula;

public class PeliculaView {
    Scanner sc = new Scanner(System.in);
    PeliculaDAO peliculaDAO = new PeliculaDAO();

    public void menuPrincipal() {
        boolean exito = true;

        do {
            System.out.println("                     ");
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1. Añadir pelicula");
            System.out.println("2. Eliminar pelicula");
            System.out.println("3. Modificar pelicula");
            System.out.println("4. Listar peliculas");
            System.out.println("5. Volver atras");
            System.out.println("6. Salir");

            switch (elegirOpcion()) {
                case 1 -> anadirPelicula();
                case 2 -> borrarPelicula();
                case 3 -> modificarPelicula();
                case 4 -> listarPeliculas();
                case 5 -> menuPrincipal();
                case 6 -> System.exit(0);
                default -> {
                    System.out.println("Opción no válida");
                    exito = false;
                }
            }
        } while (!exito);

    }

    public int elegirOpcion() {
        int opcion = sc.nextInt();
        sc.nextLine();
        return opcion;
    }

    public void anadirPelicula() {
        String titulo = "";
        String director = "";
        String genero = "";
        int duracion = 0;
        String clasificacion = "";
        double precioEntrada = 0.0;
        Date fechaInicio = null;
        Date fechaFin = null;

        boolean exito;

        do {
            exito = false;
            try {
                System.out.println("Título: ");
                titulo = sc.nextLine();

                System.out.println("Director: ");
                director = sc.nextLine();

                System.out.println("Género: ");
                genero = sc.nextLine();

                System.out.println("Duración (minutos): ");
                duracion = sc.nextInt();
                sc.nextLine();

                System.out.println("Clasificación: ");
                clasificacion = sc.nextLine();

                System.out.println("Precio entrada: ");
                precioEntrada = sc.nextDouble();
                sc.nextLine();

                System.out.println("Fecha inicio (yyyy-MM-dd): ");
                String fechaInicioStr = sc.nextLine();
                fechaInicio = java.sql.Date.valueOf(fechaInicioStr);

                System.out.println("Fecha fin (yyyy-MM-dd): ");
                String fechaFinStr = sc.nextLine();
                fechaFin = java.sql.Date.valueOf(fechaFinStr);

                Pelicula pelicula = new Pelicula(titulo, director, genero, duracion, clasificacion, precioEntrada, fechaInicio, fechaFin);
                PeliculaDAO peliculaDao = new PeliculaDAO();
                peliculaDao.insertarPelicula(pelicula);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("¿Quieres volver a intentarlo?");
                
                String respuesta = sc.nextLine();
                respuesta = respuesta.toLowerCase();
                if (respuesta.equals("si")) {
                    exito = true;
                } else {
                    exito = false;
                }
            }
        } while (exito);
        System.out.println("La pelicula se ha añadido correctamente");

    }

    public void modificarPelicula() {
        int id;
        String nuevoTitulo = "";
        String nuevoDirector = "";
        String nuevoGenero = "";
        int nuevaDuracion = 0;
        String nuevaClasificacion = "";
        double nuevoPrecio = 0.0;
        Date nuevaFechaInicio = null;
        Date nuevaFechaFin = null;
        String respuesta;
        boolean exito;

        do {
            exito = false;
            try {
                System.out.println("ID de la película: ");
                id = sc.nextInt();

                System.out.println("¿Qué quieres modificar?");
                System.out.println("1. Título");
                System.out.println("2. Director");
                System.out.println("3. Género");
                System.out.println("4. Duración");
                System.out.println("5. Clasificación");
                System.out.println("6. Precio entrada");
                System.out.println("7. Fechas de proyección");
                System.out.println("8. Salir al menu principal");

                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 -> {
                        System.out.println("Nuevo título: ");
                        nuevoTitulo = sc.nextLine();
                        peliculaDAO.modificarTituloPelicula(id, nuevoTitulo);
                    }

                    case 2 -> {
                        System.out.println("Nuevo director: ");
                        nuevoDirector = sc.nextLine();
                        peliculaDAO.modificarDirectorPelicula(id, nuevoDirector);
                    }

                    case 3 -> {
                        System.out.println("Nuevo género: ");
                        nuevoGenero = sc.nextLine();
                        peliculaDAO.modificarGeneroPelicula(id, nuevoGenero);
                    }

                    case 4 -> {
                        System.out.println("Nueva duración (minutos): ");
                        nuevaDuracion = sc.nextInt();
                        sc.nextLine();
                        peliculaDAO.modificarDuracionPelicula(id, nuevaDuracion);
                    }

                    case 5 -> {
                        System.out.println("Nueva clasificación: ");
                        nuevaClasificacion = sc.nextLine();
                        peliculaDAO.modificarClasificacionPelicula(id, nuevaClasificacion);
                    }

                    case 6 -> {
                        System.out.println("Nuevo precio entrada: ");
                        nuevoPrecio = sc.nextDouble();
                        sc.nextLine();
                        peliculaDAO.modificarPrecioPelicula(id, nuevoPrecio);
                    }

                    case 7 -> {
                        System.out.println("Nueva fecha inicio (yyyy-MM-dd): ");
                        String fechaInicioStr = sc.nextLine();
                        nuevaFechaInicio = java.sql.Date.valueOf(fechaInicioStr);
                        
                        System.out.println("Nueva fecha fin (yyyy-MM-dd): ");
                        String fechaFinStr = sc.nextLine();
                        nuevaFechaFin = java.sql.Date.valueOf(fechaFinStr);
                        
                        peliculaDAO.modificarFechasPelicula(id, nuevaFechaInicio, nuevaFechaFin);
                    }

                    case 8 -> menuPrincipal();

                    default -> System.out.println("Opción no válida");
                }
                exito = true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("¿Quieres volver a intentarlo? (si/no)");
                respuesta = sc.nextLine().toLowerCase();
                if (!respuesta.equals("si")) {
                    exito = true;
                }
            }
        } while (!exito);
    }

    public void listarPeliculas() {
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        try {
            peliculaDAO.mostrarPeliculas();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void borrarPelicula() {
        int id;
        String respuesta;
        boolean exito;

        do {
            exito = false;
            try {
                System.out.println("ID de la película: ");
                id = sc.nextInt();
                sc.nextLine();

                System.out.println("La película ");
                peliculaDAO.mostrarPeliculaByID(id);
                System.out.println("se borrará");
                System.out.println("¿Quieres continuar?");
                respuesta = sc.nextLine().toLowerCase();

                if ("si".equals(respuesta)) {
                    peliculaDAO.borrarPelicula(id);
                    System.out.println("La película ha sido eliminada con éxito");
                } else {
                    System.out.println("Se ha cancelado la operación");
                }
                exito = true;

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("¿Quieres volver a intentarlo? (si/no)");
                respuesta = sc.nextLine().toLowerCase();
                if (!respuesta.equals("si")) {
                    exito = true;
                }
            }
        } while (!exito);

    }

}
