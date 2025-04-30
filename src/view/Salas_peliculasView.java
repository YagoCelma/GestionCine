package view;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Salas_peliculas;
import model.Salas;
import model.Emision;
import dao.Salas_peliculasDao;
import dao.EmisionDAO;
import dao.SalasDao;
import dao.PeliculaDAO;

public class Salas_peliculasView {
    /* 
    private final Scanner sc = new Scanner(System.in);
    private final Salas_peliculasDao dao = new Salas_peliculasDao();
    private final EmisionDAO emisionDao = new EmisionDAO();
    private final SalasDao salasDao = new SalasDao();
    private final PeliculaDAO peliculaDAO = new PeliculaDAO();

    public void gestionSalas_Peliculas(){
        int opcion = 0;
        do { 
            System.out.println(" \n Gestion de Salas y peliculas");
            System.out.println("1. Establecer emisiones para peliculas");
            System.out.println("2. Eliminar salas y peliculas ");
            System.out.println("3. Mostrar salas y peliculas disponibles");
            System.out.println("4. Buscar una sala y pelicula por ID");
            System.out.println("5. Actualizar salas y peliculas");
            System.out.println("6. Volver al menu principal");
            opcion = sc.nextInt();

            switch(opcion){
                case 1 -> this.emisionPelicula();
                case 2 -> this.eliminarSalasPeliculas();
                case 3 -> this.mostrarSalasPeliculas();
                case 4 -> this.mostrarPorId();
                case 5 -> this.actualizar();
                case 6 -> System.out.println("Saliendo del menu de salas y peliculas...");
                default -> System.out.println("Opcion no valida, intentelo de nuevo ");
            }

        } while (opcion != 6);
    }

    public void emisionPelicula(){
        String respuesta = "si";
        do{
            System.out.println("Nombre de la película:");
            String nombre = sc.nextLine();

            int idPelicula = peliculaDAO.obtenerIdPeliculaPorNombre(nombre); // Falta de implementar para obtener el ID 
        
            if (idPelicula == -1) {
                System.out.println(" Película no encontrada. Primero debes añadirla al sistema.");
                return;
            }
            System.out.println("Duracion (minutos): ");
            int duracion = Integer.parseInt(sc.nextLine());
            System.out.println("Fecha de inico de la emision (YYYY-MM-DD): ");
            String fechaInicio = sc.nextLine();
            LocalDate localDateInicio = LocalDate.parse(fechaInicio, DateTimeFormatter.ISO_LOCAL_DATE);
            Date fecha_inicio_emision = Date.valueOf(localDateInicio); 
            System.out.println("Fecha de fin de la emision (YYYY-MM-DD): ");
            String fechaFin = sc.nextLine();
            LocalDate localDateFin = LocalDate.parse(fechaFin, DateTimeFormatter.ISO_LOCAL_DATE);
            Date fecha_fin_emision = Date.valueOf(localDateFin); 

            Emision emision = new Emision(nombre, duracion, fecha_inicio_emision, fecha_fin_emision, idPelicula );
            emisionDao.agregarEmision(emision);
            System.out.println("Emision agregada correctamente ");
            System.out.println("                               ");
            System.out.println("Desea seguir añadiendo peliculas para configurar la cartelera de la semana");
            respuesta = sc.nextLine();

        } while(respuesta.equals("si"));

        gestionAutomaticaSalasyPeliculas();//????
    }

    //En este metodo se gestiona de forma automatica las salas y peliculas y se insertan en la BD y en salas_peliculas 
    public void gestionAutomaticaSalasyPeliculas() { 
        ArrayList<Emision> emisiones = emisionDao.mostrarEmision();
        ArrayList<Salas> salas = dao.obtenerSala();
    
        for (Emision em : emisiones) {
            LocalDate inicio = em.getFecha_inicio_emision().toLocalDate();
            LocalDate fin = em.getFecha_fin_emision().toLocalDate();
            int duracion = em.getDuracion();
            int idPelicula = em.getIdPelicula();
    
            for (LocalDate fecha = inicio; !fecha.isAfter(fin); fecha = fecha.plusDays(1)) {
                for (Salas sala : salas) {
                    LocalTime hora = LocalTime.of(10, 0);
                    LocalTime limite = LocalTime.of(23, 45);
    
                    while (hora.plusMinutes(duracion + 15).isBefore(limite)) {
                        LocalTime horaFin = hora.plusMinutes(duracion);
    
                        Salas_peliculas sp = new Salas_peliculas(
                            Date.valueOf(fecha),
                            Date.valueOf(fecha),
                            Time.valueOf(hora),
                            Time.valueOf(horaFin),
                            sala.getId(),
                            idPelicula
                        );
    
                        dao.agregarSalaPelicula(sp);
                        hora = horaFin.plusMinutes(15); // margen de tiempo entre pelis
                    }
                }
            }
        }
    
        System.out.println(" Programación automática completada.");
    }
    
    public void eliminarSalasPeliculas(){
        System.out.println("Ingrese el ID de la sala_pelicula que desee eliminar");
        int id = sc.nextInt();
        sc.nextLine();
        if(dao.eliminarSalasPeliculas(id)){
            System.out.println("Sala_pelicula eliminada correctamente");
        }else {
            System.out.println("No se encontro ninguna Sala_pelicula con ese ID");
        }
    }

    public void mostrarSalasPeliculas(){
        ArrayList<Salas_peliculas> salasPeliculas = dao.mostrarSalasPeliculas();
        if(salasPeliculas.isEmpty()){
            System.out.println("No hay salas y peliculas registradas en este momento");
        } else {
            System.out.println("\nSalas y peliculas disponibles: ");
            for(Salas_peliculas sp : salasPeliculas){
                System.out.println(sp);
            }
        } 
    }

    public void mostrarPorId(){
        System.out.println("Ingrese el ID de la sala_pelicula que desee mostrar");
        int id = sc.nextInt();
        sc.nextLine();
        Salas_peliculas sp = dao.obtenerPorId(id);
        if(sp != null){
            System.out.println(sp);
        } else {
            System.out.println("No se encontro ninguna sala_pelicula con ese ID");
        }
    }

    public void actualizar(){
        System.out.println("Ingrese el ID de la sala_pelicula que desea actualizar:");
        int id = sc.nextInt();
        sc.nextLine();
        Salas_peliculas sp = dao.obtenerPorId(id);
    
        if(sp != null){
            System.out.println("Introduce la nueva fecha de emisión (YYYY-MM-DD):");
            String fechaPelicula = sc.nextLine();
            LocalDate localDate = LocalDate.parse(fechaPelicula, DateTimeFormatter.ISO_LOCAL_DATE);
            Date fecha = Date.valueOf(localDate);
    
            System.out.println("Introduce la nueva hora de inicio (HH:MM:SS):");
            String horaInicioStr = sc.nextLine();
            Time horaInicio = Time.valueOf(LocalTime.parse(horaInicioStr));
    
            System.out.println("Introduce la nueva hora de fin (HH:MM:SS):");
            String horaFinStr = sc.nextLine();
            Time horaFin = Time.valueOf(LocalTime.parse(horaFinStr));

            sp.setFecha_inicio_emision(fecha);
            sp.setFecha_fin_emision(fecha);
            sp.setHora_inicio(horaInicio);
            sp.setHora_fin(horaFin);
            
            dao.actualizarSalaPelicula(sp);
            System.out.println("Datos actualizados correctamente.");
    
        } else {
            System.out.println("No se encontró ninguna sala_pelicula con ese ID.");
        }
    }
*/
}
