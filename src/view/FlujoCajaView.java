package view;

import dao.FlujoCajaDAO;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;
import model.FlujoCaja;


public class FlujoCajaView {
    PeliculaView peliculaView = new PeliculaView();
    Scanner scanner = new Scanner(System.in);

    
    public void menuPrincipal() {
        boolean exito = false;
        do { 
            System.out.println("Menú principal");
            System.out.println("1. Registrar cobro");
            System.out.println("2. Registrar pago");
            System.out.println("3. Mostrar balance total");
            System.out.println("4. Mostrar balance del mes");
            System.out.println("4. Registrar gastos de nóminas");
            System.out.println("4. Volver atras");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
        
            switch (peliculaView.elegirOpcion()) {
                /*case 1 -> {
                mostrarIngresos();
                exito = true;
            }*/

                case 2 -> {
                    mostrarGastos();
                    exito = true;
                }
                case 3 -> {
                    mostrarBalance();
                    exito = true;
                }
                case 4 -> menuPrincipal();
                
                case 5 -> System.exit(0);
                default -> {
                    System.out.println("Opción no válida");
                    exito = true;
                }
            }
        } while (!exito);

    }

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