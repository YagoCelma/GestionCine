package view;

import dao.Salas_peliculasDao;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import model.Salas_peliculas;

public class Salas_peliculasView {

    private final Scanner sc = new Scanner(System.in);
    private final Salas_peliculasDao dao = new Salas_peliculasDao();

    public void gestionSalas_Peliculas(){
        int opcion = 0;
        do { 
            System.out.println(" \n Gestion de Salas y peliculas");
            System.out.println("1. Agregar salas y peliculas");
            System.out.println("2. Eliminar salas y peliculas ");
            System.out.println("3. Mostrar salas y peliculas disponibles");
            System.out.println("4. Buscar una sala y pelicula por ID");
            System.out.println("5. Actualizar salas y peliculas");
            System.out.println("6. Volver al menu principal");
            opcion = sc.nextInt();

            switch(opcion){
                case 1 -> this.agregarSalasPelicula();
                case 2 -> this.eliminarSalasPeliculas();
                case 3 -> this.mostrarSalasPeliculas();
                case 4 -> this.mostrarPorId();
                case 5 -> this.actualizar();
                case 6 -> System.out.println("Saliendo del menu de salas y peliculas...");
                default -> System.out.println("Opcion no valida, intentelo de nuevo ");
            }

        } while (opcion != 6);
    }

    public void agregarSalasPelicula(){
        System.out.println("Nombre de la pelicula: ");
        String nombre = sc.nextLine();
        System.out.println("Fecha (YYYY-MM-DD): ");
        String fechaPelicula = sc.nextLine();
        LocalDate localDate = LocalDate.parse(fechaPelicula, DateTimeFormatter.ISO_LOCAL_DATE);
        Date fecha = Date.valueOf(localDate); 
        System.out.println("Hora de inicio (HH:MM:SS): ");
        String horaInicioStr = sc.nextLine();
        LocalTime horaInicio = LocalTime.parse(horaInicioStr, DateTimeFormatter.ISO_LOCAL_TIME);
        Time horaInicioSql = Time.valueOf(horaInicio);
        System.out.println("Hora de fin (HH:MM:SS): ");
        String horaFinStr = sc.nextLine();
        LocalTime horaFin = LocalTime.parse(horaFinStr, DateTimeFormatter.ISO_LOCAL_TIME);
        Time horaFinSql = Time.valueOf(horaFin);

        Salas_peliculas salas_peliculas = new Salas_peliculas(nombre, fecha, horaInicioSql, horaFinSql);
        dao.agregarSalaPelicula(salas_peliculas);
        System.out.println("Sala y pelicula agregadas correctamente con ID: " + salas_peliculas.getId());
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
