package view;

import dao.FlujoCajaDAO;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;
import model.FlujoCaja;


public class FlujoCajaView {
    Scanner scanner = new Scanner(System.in);


    public void mostrarMenu() {
        FlujoCajaDAO dao = new FlujoCajaDAO();
        
        int opcion;
        do {
            System.out.println("\n----- FLUJO DE CAJA -----");
            System.out.println("1. Registrar ingreso");
            System.out.println("2. Registrar gasto");
            System.out.println("3. Registrar gastos automáticos de nóminas");
            System.out.println("4. Ver saldo actual");
            System.out.println("5. Ver ingresos y gastos del mes");
            System.out.println("6. Ver resumen por categoría");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
 
            switch (opcion) {
                case 1 -> registrarMovimiento("INGRESO");
                case 2 -> registrarMovimiento("GASTO");
                case 3 -> dao.generarGastosNominas();
                case 4 -> {
                    System.out.printf("Saldo actual:");
                    dao.calcularSaldoActual();
                }
                case 5 -> {
                    System.out.printf("Ingresos del mes:");
                    dao.ingresosDelMes();
                    System.out.printf("Gastos del mes:");
                    dao.gastosDelMes();
                }
                case 6 -> dao.mostrarResumenPorCategoria();
                case 0 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
 
    private void registrarMovimiento(String tipo) {

        FlujoCajaDAO dao = new FlujoCajaDAO();

        FlujoCaja movimiento = new FlujoCaja();
        movimiento.setTipo(tipo);
 
        System.out.print("Descripción: ");
        movimiento.setDescripcion(scanner.nextLine());
 
        System.out.print("Cantidad (€): ");
        movimiento.setCantidad(scanner.nextDouble());
 
        scanner.nextLine(); // limpiar buffer
        System.out.print("Categoría (ej: SERVICIO, NOMINA, COMPRA, OTRO): ");
        movimiento.setCategoria(scanner.nextLine());
 
        movimiento.setFecha(Date.valueOf(LocalDate.now()));
 
        dao.registrarMovimiento(movimiento);
        System.out.println(tipo + " registrado con éxito.");
    }
    
}




//