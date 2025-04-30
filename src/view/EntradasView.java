package view;
import dao.EntradaDAO;
import java.util.*;
import model.Entrada;


public class EntradasView {
    Scanner sc = new Scanner(System.in);
    
    
    public void menuEntrada(){
        int opcion;
        do{
            System.out.println("Menu de Entradas");
            System.out.println("1. Añadir entrada");
            System.out.println("2. Borrar entrada");
            System.out.println("3. Modificar entrada");
            System.out.println("4. Listar entradas");
            System.out.println("5. Buscar entrada por ID");
            System.out.println("6. Salir");
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            sc.nextLine();
    
            switch(opcion){
                case 1-> añadirEntrada();
                case 2-> borrarEntrada();
                case 3-> modificarEntrada();
                case 4-> listarEntrada();
                case 5 -> buscarEntradaID();
                default -> System.out.println("Seleccione una opcion que sea valida");
            }
        }while(opcion != 6);
    }
    public void añadirEntrada(){
        System.out.println("Introduzca el precio");
        int precio = sc.nextInt();
        System.out.println("Introduzca el tipo de entrada");
        String tipo = sc.nextLine();
        System.out.println("Introduzca la fecha");
        String fecha = sc.nextLine();
        System.out.println("Introduzca la hora");
        String hora = sc.nextLine();
        System.out.println("Introduzca el tipo de entrada (normal o VIP)");
        String tipoEntrada = sc.nextLine();
        System.out.println("Introduzca el nombre de la pelicula");
        String nombrePelicula = sc.nextLine();
        System.out.println("Introduzca la sala");
        String sala = sc.nextLine();

        Entrada entrada = new Entrada(precio, tipo, fecha, hora, tipoEntrada, nombrePelicula, sala);
        EntradaDAO entradaDAO = new EntradaDAO();
        entradaDAO.añadirEntrada(entrada);
        System.out.println("Entrada añadida correctamente con ID: " + entrada.getId());

        
    }
    public void borrarEntrada(){
        System.out.println("Introduzca el ID de la entrada a borrar");
        int idEntrada = sc.nextInt();
        sc.nextLine();
        EntradaDAO entradaDAO = new EntradaDAO();
        if(entradaDAO.borrarEntrada(idEntrada)){
            System.out.println("Entrada borrada correctamente");
        }else{
            System.out.println("No se ha podido borrar la entrada, verifique el ID");
        }
        
        
    }
    public void modificarEntrada(){
        System.out.println("Introduzca el ID de la entrada a modificar");
        int idEntrada = sc.nextInt();
        sc.nextLine();
        EntradaDAO entradaDAO = new EntradaDAO();
        Entrada entrada = entradaDAO.buscarEntradaPorID(idEntrada);
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
            System.out.println("2. Modificar tipo");
            System.out.println("3. Modificar fecha");
            System.out.println("4. Modificar hora");
            System.out.println("5. Modificar tipo de entrada (normal, niño o mayores)=)");
            System.out.println("6. Modificar nombre de la pelicula");
            System.out.println("7. Modificar sala");
            System.out.println("8. Salir");
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            sc.nextLine();
    
            switch(opcion){
                case 1-> entrada.setPrecio(sc.nextInt());
                case 2-> entrada.setTipo(sc.nextLine());
                case 3-> entrada.setFecha(sc.nextLine());
                case 4-> entrada.setHora(sc.nextLine());
                case 5-> entrada.setTipoEntrada(sc.nextLine());
                case 6-> entrada.setNombrePelicula(sc.nextLine());
                case 7-> entrada.setSala(sc.nextLine());
                default -> System.out.println("Seleccione una opcion que sea valida");
            }
        }while(opcion != 8);
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
        int idEntrada = sc.nextInt();
        sc.nextLine();
        EntradaDAO entradaDAO = new EntradaDAO();
        Entrada entrada = entradaDAO.buscarEntradaPorID(idEntrada);
        if(entrada != null){
            System.out.println("Entrada encontrada: " + entrada);
        }else{  
            System.out.println("No se ha podido encontrar la entrada, verifique el ID");
        }
        
    }
}
   