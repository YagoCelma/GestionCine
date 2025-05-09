package model;

import java.sql.Time;
import java.util.ArrayList;

public class Salas_peliculas {

    private int id;
    private String nombrePelicula;
    private Time hora_inicio;
    private Time hora_fin;
    private int id_sala;
    private double precioBase;
    private int id_pelicula;
    private char[][] asientos;
    private Sala sala;

    public Salas_peliculas() {}

    public Salas_peliculas(String nombrePelicula, Time hora_inicio, Time hora_fin, int id_sala, double precioBase, int id_pelicula) {
        this.nombrePelicula = nombrePelicula;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.id_sala = id_sala;
        this.precioBase = precioBase;
        this.id_pelicula = id_pelicula;
        
    }

    public Salas_peliculas(int id, double precioBase, Sala sala, int idSala, int id_pelicula) {
        this.id = id;
        this.precioBase = precioBase;
        this.id_sala = id_sala;
        this.id_pelicula = id_pelicula;
        this.asientos = generarSala(sala);
    }

    public int getId() { return id; }
    public String getNombrePelicula() { return nombrePelicula; }
    public Time getHora_inicio() { return hora_inicio; }
    public Time getHora_fin() { return hora_fin; }
    public int getId_sala() { return id_sala; }
    public double getPrecioBase() { return precioBase; }
    public int getIdPelicula(){ return id_pelicula; }
    public char[][] getAsientos() {return this.asientos;}
    

    public void setId(int id) { this.id = id; }
    public void setNombrePelicula(String nombrePelicula) { this.nombrePelicula = nombrePelicula; }
    public void setHora_inicio(Time hora_inicio) { this.hora_inicio = hora_inicio; }
    public void setHora_fin(Time hora_fin) { this.hora_fin = hora_fin; }
    public void setId_sala(int id_sala) { this.id_sala = id_sala; }
    public void setPrecioBase(double precioBase ) { this.precioBase = precioBase; }
    public void setIdPelicula(int id_pelicula ) { this.id_pelicula = id_pelicula; }
    public void setAsientos(char[][] asientos) {this.asientos = asientos;}

    @Override
    public String toString() {
        return "Salas_peliculas{" +
                "id=" + id +
                ", Nombre_pelicula=" + nombrePelicula +
                ", hora_inicio=" + hora_inicio +
                ", hora_fin=" + hora_fin +
                ", id_sala=" + id_sala +
                ", precio_base=" + precioBase +
                ", id_pelicula=" + id_pelicula +
                '}';
    }

    public char[][] generarSala(Sala sala) {
        int filas = sala.getNumeroFilas();      
        int columnas = sala.getNumeroColumnas(); 
        char[][] matriz = new char[filas][columnas];

   
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = ' ';
            }
        }

        for (int j = 0; j < columnas; j++) {
            if (j % 2 == 0) {
                matriz[0][j] = 'D';
            }
        }

        for (int i = 1; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (j != columnas / 2) {
                    matriz[i][j] = '█';
                }
            }
        }

        return matriz;
    }

public void mostrarSala() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_RED = "\u001B[31m";

    System.out.println("\n       PANTALLA / ESCENARIO\n");

    for (int i = 0; i < asientos.length; i++) {
        System.out.printf("%2d ", i);
        for (int j = 0; j < asientos[i].length; j++) {
            char asiento = asientos[i][j];
            switch (asiento) {
                case '█': // Disponible
                case 'D':
                    System.out.print(ANSI_GREEN + asiento + " " + ANSI_RESET);
                    break;
                case 'X': // Ocupado
                case '@':
                    System.out.print(ANSI_RED + asiento + " " + ANSI_RESET);
                    break;
                default:
                    System.out.print(asiento + " ");
            }
        }
        System.out.println(" " + i);
    }

    System.out.print("   ");
    for (int j = 0; j < asientos[0].length; j++) {
        System.out.print((j < 10 ? " " : "") + j % 10 + " ");
    }
    System.out.println("\n");
}

    public boolean reservarAsiento(int fila, int columna, boolean esDiscapacitado) {
        if (fila < 0 || fila >= asientos.length || columna < 0 || columna >= asientos[fila].length) {
            System.out.println("Fuera de rango.");
            return false;
        }

        char estado = asientos[fila][columna];
        if (estado == '█') {
            asientos[fila][columna] = 'X';
            return true;
        }

        if (estado == 'D') {
            if (esDiscapacitado) {
                asientos[fila][columna] = '@';
                return true;
            } else {
                System.out.println("Reservado para personas con discapacidad.");
                return false;
            }
        }

        System.out.println("No disponible.");
        return false;
    }

    public void aplicarReservas(ArrayList<Entrada> entradas) {
    for (Entrada e : entradas) {
        int fila = e.getAsiento() / 100;
        int columna = e.getAsiento() % 100;
        if (fila >= 0 && fila < asientos.length && columna >= 0 && columna < asientos[0].length) {
            if (asientos[fila][columna] == '█') {
                asientos[fila][columna] = 'X';
            }
        }
    }
}

}