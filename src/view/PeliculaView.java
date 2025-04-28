package view;

import dao.PeliculaDAO;
import java.util.Scanner;
import model.Pelicula;

public class PeliculaView {
    Scanner sc = new Scanner(System.in);
    PeliculaDAO peliculaDAO = new PeliculaDAO();

    public void menuPrincipal() {
        boolean exito = true;

        do {
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1. Añadir pelicula");
            System.out.println("2. Eliminar pelicula");
            System.out.println("3. Modificar pelicula");
            System.out.println("4. Listar peliculas");
            System.out.println("4. Volver atras");
            System.out.println("5. Salir");

            switch (elegirOpcion()) {
                case 1 -> anadirPelicula();
                case 2 -> borrarPelicula();
                case 3 -> modificarPelicula();
                // case 4 -> listarPelicula();
                case 5 -> System.exit(0);
                default -> {
                    System.out.println("Opción no válida");
                    exito = false;
                }
            }
        } while (!exito);

    }

    public int elegirOpcion() {
        int opcion = sc.nextInt();
        return opcion;
    }

    public Pelicula anadirPelicula() {
        String titulo = "";
        String director = "";
        String genero = "";
        int duracion = 0;
        String clasificacion = "";
        double precioEntrada = 0.0;
        boolean enCartelera = false;

        boolean exito;

        do {
            exito = false;
            try {
                System.out.println("Título: ");
                titulo = sc.next();

                System.out.println("Director: ");
                director = sc.next();

                System.out.println("Género: ");
                genero = sc.next();

                System.out.println("Duración (minutos): ");
                duracion = sc.nextInt();

                System.out.println("Clasificación: ");
                clasificacion = sc.next();

                System.out.println("Precio entrada: ");
                precioEntrada = sc.nextDouble();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("¿Quieres volver a intentarlo?");
                
                String respuesta = sc.next();
                respuesta = respuesta.toLowerCase();
                if (respuesta.equals("si")) {
                    exito = true;
                } else {
                    return null;
                }
            }
        } while (exito);
        System.out.println(
                "La pelicula se ha añadido correctamente");
        return new Pelicula(0, titulo, director, genero, duracion, clasificacion, precioEntrada, enCartelera); // El id se genera despues con el auto increment
    }

    public void modificarPelicula() {
        int id;
        int opcion;
        String nuevoTitulo;
        String nuevoDirector;
        String nuevoGenero;
        int nuevaDuracion;
        String nuevaClasificacion;
        double nuevoPrecio;
        boolean enCartelera;
        boolean exito;
        String respuesta;

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
                System.out.println("7. En cartelera");
                System.out.println("8. Todo");
                System.out.println("9. Salir");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1 -> {
                        System.out.println("Nuevo título: ");
                        nuevoTitulo = sc.next();
                        peliculaDAO.modificarTituloPelicula(id, nuevoTitulo);
                    }

                    case 2 -> {
                        System.out.println("Nuevo director: ");
                        nuevoDirector = sc.next();
                        peliculaDAO.modificarDirectorPelicula(id, nuevoDirector);
                    }

                    case 3 -> {
                        System.out.println("Nuevo género: ");
                        nuevoGenero = sc.next();
                        peliculaDAO.modificarGeneroPelicula(id, nuevoGenero);
                    }

                    case 4 -> {
                        System.out.println("Nueva duración (minutos): ");
                        nuevaDuracion = sc.nextInt();
                        peliculaDAO.modificarDuracionPelicula(id, nuevaDuracion);
                    }

                    case 5 -> {
                        System.out.println("Nueva clasificación: ");
                        nuevaClasificacion = sc.next();
                        peliculaDAO.modificarClasificacionPelicula(id, nuevaClasificacion);
                    }

                    case 6 -> {
                        System.out.println("Nuevo precio entrada: ");
                        nuevoPrecio = sc.nextDouble();
                        peliculaDAO.modificarPrecioPelicula(id, nuevoPrecio);
                    }

                    case 7 -> {
                        System.out.println("¿Está en cartelera? (true/false): ");
                        enCartelera = sc.nextBoolean();
                        peliculaDAO.modificarEnCartelera(id, enCartelera);
                    }

                    case 8 -> {
                        System.out.println("Nuevo título: ");
                        nuevoTitulo = sc.next();
                        System.out.println("Nuevo director: ");
                        nuevoDirector = sc.next();
                        System.out.println("Nuevo género: ");
                        nuevoGenero = sc.next();
                        System.out.println("Nueva duración (minutos): ");
                        nuevaDuracion = sc.nextInt();
                        System.out.println("Nueva clasificación: ");
                        nuevaClasificacion = sc.next();
                        System.out.println("Nuevo precio entrada: ");
                        nuevoPrecio = sc.nextDouble();
                        System.out.println("¿Está en cartelera? (true/false): ");
                        enCartelera = sc.nextBoolean();

                        peliculaDAO.modificarTituloPelicula(id, nuevoTitulo);
                        peliculaDAO.modificarDirectorPelicula(id, nuevoDirector);
                        peliculaDAO.modificarGeneroPelicula(id, nuevoGenero);
                        peliculaDAO.modificarDuracionPelicula(id, nuevaDuracion);
                        peliculaDAO.modificarClasificacionPelicula(id, nuevaClasificacion);
                        peliculaDAO.modificarPrecioPelicula(id, nuevoPrecio);
                        peliculaDAO.modificarEnCartelera(id, enCartelera);

                    }

                    case 9 -> menuPrincipal();

                    default -> System.out.println("Opción no válida");
                }
                exito = true;

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("¿Quieres volver a intentarlo? (si/no)");
                respuesta = sc.next().toLowerCase();
                if (!respuesta.equals("si")) {
                    exito = true;
                }
            }
        } while (!exito);
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

                System.out.println("La película ");
                peliculaDAO.mostrarPeliculaByID(id);
                System.out.println("se borrará");
                System.out.println("¿Quieres continuar?");
                respuesta = sc.next().toLowerCase();

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
                respuesta = sc.next().toLowerCase();
                if (!respuesta.equals("si")) {
                    exito = true;
                }
            }
        } while (!exito);

    }

}
