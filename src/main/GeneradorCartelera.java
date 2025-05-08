package main;
import java.util.Scanner;

public class GeneradorCartelera {
    public static void main(String[] args) {
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
                    
                    break;
                case 2:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 2); 
        
        scanner.close();
    }
}


