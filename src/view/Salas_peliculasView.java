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

public class Salas_peliculasView {

    private final Scanner sc = new Scanner(System.in);
    private final Salas_peliculasDao dao = new Salas_peliculasDao();
    private final EmisionDAO emisionDao = new EmisionDAO();

    public void gestionSalas_Peliculas(){
        int opcion = 0;
        do { 
            System.out.println(" \n Gestion de Salas y peliculas");
            System.out.println("1. Establecer emision de una pelicula");
            System.out.println("2. Automatizar gestion de salas y peliculas");
            System.out.println("3. Eliminar salas y peliculas ");
            System.out.println("4. Mostrar salas y peliculas disponibles");
            System.out.println("5. Buscar una sala y pelicula por ID");
            System.out.println("6. Actualizar salas y peliculas");
            System.out.println("7. Volver al menu principal");
            opcion = sc.nextInt();

            switch(opcion){
                case 1 -> this.emisionPelicula();
                case 2 -> this.gestionAutomaticaSalasyPeliculas();
                /*case 2 -> this.eliminarSalasPeliculas();
                case 3 -> this.mostrarSalasPeliculas();
                case 4 -> this.mostrarPorId();
                case 5 -> this.actualizar();
                case 6 -> System.out.println("Saliendo del menu de salas y peliculas...");
                default -> System.out.println("Opcion no valida, intentelo de nuevo ");*/
            }

        } while (opcion != 7);
    }

    public void emisionPelicula(){
        System.out.println("Nombre de la pelicula: ");
        String nombre = sc.nextLine();
        System.out.println("Duracion: ");
        int duracion = Integer.parseInt(sc.nextLine());
        System.out.println("Fecha de inico de la emision (YYYY-MM-DD): ");
        String fechaInicio = sc.nextLine();
        LocalDate localDateInicio = LocalDate.parse(fechaInicio, DateTimeFormatter.ISO_LOCAL_DATE);
        Date fecha_inicio_emision = Date.valueOf(localDateInicio); 
        System.out.println("Fecha de fin de la emision (YYYY-MM-DD): ");
        String fechaFin = sc.nextLine();
        LocalDate localDateFin = LocalDate.parse(fechaFin, DateTimeFormatter.ISO_LOCAL_DATE);
        Date fecha_fin_emision = Date.valueOf(localDateFin); 

        Emision emision = new Emision(nombre, duracion, fecha_inicio_emision, fecha_fin_emision );
        emisionDao.agregarEmision(emision);
        System.out.println("Emision agregada correctamente ");
        
    }

    public void gestionAutomaticaSalasyPeliculas(){ //Hacer aqui el algoritmo que asigne automaticamente todo y esto sera lo que se inserte en la Base de datos salas_peliculas
        ArrayList<Emision> emisiones = emisionDao.mostrarEmision();
        ArrayList<Salas> salas = dao.obtenerSala();

        for(Emision em : emisiones){
            LocalDate inicio = em.getFecha_inicio_emision().toLocalDate();
            LocalDate fin = em.getFecha_fin_emision().toLocalDate();
            int duracion = em.getDuracion(); // en minutos

            for (LocalDate fecha = inicio; !fecha.isAfter(fin); fecha = fecha.plusDays(1)) {
                for (Salas sala : salas) {
                    LocalTime hora = LocalTime.of(10, 0); // El cine abre a las 10:00
                    LocalTime limite = LocalTime.of(23, 45); // Última función inicia a más tardar a esta hora
                    while (hora.plusMinutes(duracion + 15).isBefore(limite)) {
                        LocalTime horaFin = hora.plusMinutes(duracion);
                        Salas_peliculas sp = new Salas_peliculas(
                            em.getNombre(),
                            Date.valueOf(fecha),
                            Date.valueOf(fecha),
                            Time.valueOf(hora),
                            Time.valueOf(horaFin),
                            sala.getId(),
                            em.getId(),
                            0 // id_entrada si no lo usas aún
                        );
                        dao.agregarSalaPelicula(sp);
                        hora = horaFin.plusMinutes(15); // Tiempo de limpieza
                    }
                }
            }    
        }
        System.out.println("Funciones programadas automáticamente.");
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
        System.out.println("Ingrese el ID de la sala_pelicula que desee actualizar");
        int id = sc. nextInt();
        sc.nextLine();
        Salas_peliculas sp = dao.obtenerPorId(id);
        if(sp != null){
            System.out.println("Introduce el nuevo nombre de la pelicula a actualizar en sala_pelicula");
            String nombre = sc.nextLine();
            System.out.println("Introduce la nueva fecha de la sala_pelicula a actualizar (YYYY-MM-DD): ");
            String fechaPelicula = sc.nextLine();
            LocalDate localDate = LocalDate.parse(fechaPelicula, DateTimeFormatter.ISO_LOCAL_DATE);
            Date fecha = Date.valueOf(localDate); 
            System.out.println("Introduce la nueva hora_inicio de la sala_pelicula a actualizar (HH:MM:SS):");
            String horaInicioStr = sc.nextLine();
            LocalTime horaInicio = LocalTime.parse(horaInicioStr, DateTimeFormatter.ISO_LOCAL_TIME);
            Time horaInicioSql = Time.valueOf(horaInicio);
            System.out.println("Introduce la nueva hora_fin de la sala_pelicula a actualizar (HH:MM:SS):");
            String horaFinStr = sc.nextLine();
            LocalTime horaFin = LocalTime.parse(horaFinStr, DateTimeFormatter.ISO_LOCAL_TIME);
            Time horaFinSql = Time.valueOf(horaFin);

            Salas_peliculas salas_peliculas = new Salas_peliculas();
            salas_peliculas.setNombre(nombre);
            salas_peliculas.setFecha(fecha);
            salas_peliculas.setHora_inicio(horaInicioSql);
            salas_peliculas.setHora_fin(horaFinSql);

            dao.actualizarSalaPelicula(salas_peliculas);
            System.out.println("Datos actualizados correctamente");
            
        }else {
            System.out.println("No se encontro ninguna sala_pelicula con ese ID ");
        }
    }

}
