package view;

import dao.Salas_peliculasDao;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
                case 1 -> this.agregarSalaPelicula();
            }

        } while (opcion != 6);
    }

    public void agregarSalaPelicula(){
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
    
}
