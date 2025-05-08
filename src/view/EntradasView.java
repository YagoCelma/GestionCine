package view;
import dao.ClienteDAO;
import dao.EntradaDAO;
import java.util.*;
import model.Entrada;
import dao.Salas_peliculasDao;
import model.Salas_peliculas;


public class EntradasView {
    
    Scanner sc = new Scanner(System.in);
    
    
    public void menuEntrada(){
        int opcion;
        do{
            System.out.println("Menu de Entradas");
            System.out.println("1. Vender entrada");
            System.out.println("2. Borrar entrada");
            System.out.println("3. Modificar entrada");
            System.out.println("4. Listar entradas");
            System.out.println("5. Buscar entrada por ID");
            System.out.println("6. Salir");
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            sc.nextLine();
    
            switch(opcion){
                case 1-> venderEntrada();
                case 2-> borrarEntrada();
                case 3-> modificarEntrada();
                case 4-> listarEntrada();
                case 5 -> buscarEntradaID();
                case 6-> System.out.println("Saliendo...");
                default -> System.out.println("Seleccione una opcion que sea valida");
            }
        }while(opcion != 6);
    }
    
    public void venderEntrada(){
        Salas_peliculasDao sp = new Salas_peliculasDao();
        System.out.println("---PELICULAS DISPONIBLES---");
        sp.mostrar(); //En el dao de salaPeliculas listar las disponibles con su id

        System.out.println("ID de la sala pelicula");
        int idSalaPelicula = sc.nextInt();
        sc.nextLine();

        Salas_peliculas salaPelicula = sp.salaPeliculaPorID(idSalaPelicula);
        double precioBase = sp.obtenerPrecioBase(idSalaPelicula); //Falta aun de implementar
        if(salaPelicula == null) {
            System.out.println("Error: ID de sala no encontrado");
            return;
        }

        System.out.println("\n--- MAPA DE ASIENTOS ---");
        salaPelicula.mostrarSala();

        System.out.print("\nIngrese fila del asiento: ");
        int fila = sc.nextInt();
        System.out.print("Ingrese columna del asiento: ");
        int columna = sc.nextInt();
        sc.nextLine();

        boolean esDiscapacitado = false;
        if(salaPelicula.getAsientos()[fila][columna] == 'D') {
            System.out.print("¿Es para persona con discapacidad? (s/n): ");
            esDiscapacitado = sc.nextLine().equalsIgnoreCase("s");
        }
        
        // Intentar reservar el asiento
        if(!salaPelicula.reservarAsiento(fila, columna, esDiscapacitado)) {
            System.out.println("No se pudo reservar el asiento. Puede que ya esté ocupado.");
            return;
        }
        
        // Calcular número de asiento único
        int numeroAsiento = fila * 100 + columna;

        System.out.println("\nTIPOS DE DESCUENTO:");
        System.out.println("0. Ninguno (precio completo)");
        System.out.println("1. Socio (20% desc.)");
        System.out.println("2. Niño (40% desc.)");
        System.out.println("3. Jubilado (50% desc.)");
        System.out.print("Seleccione opción: ");
        int tipoDescuento = sc.nextInt();
        sc.nextLine();

        double precioFinal = calcularPrecioDescuento(precioBase, tipoDescuento);

        Entrada entrada = new Entrada(numeroAsiento, idSalaPelicula, precioFinal);

        if(tipoDescuento == 1){
            System.out.println("Introduzca DNI del socio:");
            entrada.setDniSocio(sc.nextLine());
        }
      
        EntradaDAO entradaDAO = new EntradaDAO();
        entradaDAO.añadirEntrada(entrada);
        System.out.println("Entrada creada. Precio final:" + precioFinal);

        
    }
    public void borrarEntrada(){
        System.out.println("Introduzca el ID de la entrada a borrar");
        int id = sc.nextInt();
        sc.nextLine();
        EntradaDAO entradaDAO = new EntradaDAO();
        if(entradaDAO.borrarEntrada(id)){
            System.out.println("Entrada borrada correctamente");
        }else{
            System.out.println("No se ha podido borrar la entrada, verifique el ID");
        }
        
        
    }
    public void modificarEntrada(){
        System.out.println("Introduzca el ID de la entrada a modificar");
        int id = sc.nextInt();
        sc.nextLine();
        EntradaDAO entradaDAO = new EntradaDAO();
        Entrada entrada = entradaDAO.buscarEntradaPorID(id);
        if(entrada != null){
            menuModificarEntrada(entrada);
        }else{  
            System.out.println("No se ha podido modificar la entrada, verifique el ID");
        }

        
    }
    public void menuModificarEntrada(Entrada entrada){
       int opcion;
       do{
            System.out.println("Menu de Modificacion de Entrada");
            System.out.println("1. Modificar precio");
            System.out.println("2. Modificar asiento");
            System.out.println("3. Salir");
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            sc.nextLine();
    
            switch(opcion){
                case 1-> entrada.setPrecio(sc.nextDouble());
                case 2-> entrada.setAsiento(sc.nextInt());
                case 3-> System.out.println("Saliendo...");
                default -> System.out.println("Seleccione una opcion que sea valida");
            }
        }while(opcion != 3);
        EntradaDAO entradaDAO = new EntradaDAO();
        entradaDAO.modificarEntrada(entrada);
        System.out.println("Entrada modificada correctamente con ID: " + entrada.getId());
    }
    
    public void listarEntrada(){
        System.out.println("Lista de Entradas:");
        EntradaDAO entradaDAO = new EntradaDAO();
        for (Entrada entrada : entradaDAO.listarEntradas()) {
            System.out.println(entrada);
        }
        
    }

    public void buscarEntradaID(){
        System.out.println("Introduzca el ID de la entrada a buscar");
        int id = sc.nextInt();
        sc.nextLine();
        EntradaDAO entradaDAO = new EntradaDAO();
        Entrada entrada = entradaDAO.buscarEntradaPorID(id);
        if(entrada != null){
            System.out.println("Entrada encontrada: " + entrada);
        }else{  
            System.out.println("No se ha podido encontrar la entrada, verifique el ID");
        }
        
    }

    private double calcularPrecioDescuento(double precioBase, int tipoDescuento) {
        return switch(tipoDescuento) {
            case 1 -> precioBase * 0.8;
            case 2 -> precioBase * 0.6;
            case 3 -> precioBase * 0.5;
            default -> precioBase;
        };
    }

}
