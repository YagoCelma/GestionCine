package main;
import java.util.*;

public class GeneradorCartelera {
    public void generador() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("\n--- Menú Cron ---");
            System.out.println("1. Generar Cartelera");
            System.out.println("2. Salir");
            System.out.print("Elige una opción: ");
            
            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("Ejecutando generador de cartelera...");
                    automatizarCartelera();
                    
                    break;
                case 2:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 2); 
        
    }

    public void automatizarCartelera(){

    /*     private static final LocalTime HORA_INICIO = LocalTime.of(9, 0);
        private static final LocalTime HORA_FIN = LocalTime.of(24, 0);
        private static final int PAUSA_ENTRE_FUNCIONES_MIN = 30;
        private static final int NUM_DIAS = 7;
 
        System.out.println("🎬 Generando cartelera para la próxima semana...\n");
 
        PeliculaDAO peliculaDAO = new PeliculaDAO();
        SalaDAO salaDAO = new SalaDAO();
        Salas_peliculasDao salasPeliculasDao = new Salas_peliculasDao();
 
        List<Pelicula> peliculas = peliculaDAO.mostrarPeliculas(); // frecuencia viene de la base de datos
        List<Sala> salas = salaDAO.obtenerSalas();
 
        if (peliculas.isEmpty() || salas.isEmpty()) {
            System.out.println("❌ No hay películas o salas disponibles.");
            return;
        }
 
        // Crear lista de sesiones pendientes según frecuencia
        List<Pelicula> funcionesPendientes = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            int frecuencia = pelicula.getFrecuencia(); // asumimos getter getFrecuencia()
            for (int i = 0; i < frecuencia; i++) {
                funcionesPendientes.add(pelicula);
            }
        }
 
        Collections.shuffle(funcionesPendientes);
 
        LocalDate lunes = LocalDate.now().plusWeeks(1).with(DayOfWeek.MONDAY);
        Iterator<Pelicula> iterador = funcionesPendientes.iterator();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("EEE dd/MM - HH:mm");
 
        for (Sala sala : salas) {
            System.out.println("📽️  Sala: " + sala.getNombre());
            LocalDate dia = lunes;
 
            for (int d = 0; d < NUM_DIAS; d++) {
                LocalTime hora = HORA_INICIO;
 
                while (hora.isBefore(HORA_FIN) && iterador.hasNext()) {
                    Pelicula pelicula = iterador.next();
                    int duracion = pelicula.getDuracion();
 
                    if (hora.plusMinutes(duracion).isAfter(HORA_FIN)) break;
 
                    LocalDateTime fechaHora = LocalDateTime.of(dia, hora);
                    Timestamp timestamp = Timestamp.valueOf(fechaHora);
 
                    // Insertar en base de datos
                    Salas_peliculas sesion = new Salas_peliculas();
                    sesion.setId_sala(sala.getId());
                    sesion.setId_pelicula(pelicula.getId());
                    sesion.setFecha_hora(timestamp);
                    salasPeliculasDao.insertarSesion(sesion);
 
                    // Mostrar por consola
                    System.out.printf("  🎞️  %-25s %s (%d min)%n",
                            pelicula.getTitulo(),
                            fechaHora.format(formatoFecha),
                            duracion);
 
                    hora = hora.plusMinutes(duracion + PAUSA_ENTRE_FUNCIONES_MIN);
                }
 
                dia = dia.plusDays(1);
            }
 
            System.out.println();
        }
 
        if (iterador.hasNext()) {
            System.out.println("⚠️  No se pudieron programar todas las funciones por falta de espacio.");
        } else {
            System.out.println("✅ Cartelera generada correctamente.");
        }
    */
    }
}


