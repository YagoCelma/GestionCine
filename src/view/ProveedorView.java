package view;

import java.lang.reflect.Array;
import java.util.Scanner;
import dao.ProveedorDAO;
import model.Proveedor;

public class ProveedorView {
    
    Scanner sc = new Scanner(System.in);

    public void menuProveedor(){
        int opcion;
        do{
            System.out.println("Menu Proveedor");
            System.out.println("1. Agregar proveedor");
            System.out.println("2. Borrar proveedor");
            System.out.println("3. Modificar proveedor");
            System.out.println("4. Listar proveedor");
            System.out.println("5. Proveedor por ID");
            System.out.println("6. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> agregarProveedor();
                case 2-> borrarProveedor();
                case 3-> modificarProveedor();
                case 4-> listarProveedor();
                case 5-> proveedorPorID();
                case 6-> System.out.println("Saliendo...");
                default-> System.out.println("Opcion no valida, vuelva a intentarlo");
            }
        }while(opcion != 6);
    }
    public void agregarProveedor(){

        String nombre = "";
        String tipo = "";
        int telefono = 0;
        String email = "";

        boolean pasar;

        do{
            pasar = false;
            try{
                System.out.println("Nombre del proveedor:");
                nombre = sc.nextLine();
                System.out.println("Tipo (Peliculas o Productos)");
                tipo = sc.nextLine();
                System.out.println("Telefono");
                telefono = sc.nextInt();
                sc.nextLine();
                System.out.println("Email");
                email = sc.nextLine();
            }catch(Exception e){
                System.out.println("Error " + e.getMessage());
                System.out.println("¿Quieres volver a intentarlo?");

                String respuesta = sc.nextLine();
                if(respuesta.equalsIgnoreCase("si")){
                    pasar = true;
                }
            }
        }while(pasar);
        System.out.println("El proveedor ha sido añadido correctamente");
        Proveedor proveedor = new Proveedor(nombre, tipo, telefono, email);
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        proveedorDAO.agregarProveedor(proveedor);
        System.out.println("Proveedor añadido correctamente");
    }

    public void borrarProveedor(){

        System.out.println("Introduce el ID del proveedor");
        int id = sc.nextInt();
        sc.nextLine();

        ProveedorDAO proveedorDAO = new ProveedorDAO();
        if(proveedorDAO.eliminarProveedor(id)){
            System.out.println("Proveedor eliminado corretamente");
        }else{
            System.out.println("Proveedor no encontrado");
        }
    }

    public void modificarProveedor(){

        int id;
        boolean pasar;

        do{
            try{
                System.out.println("Indica el ID del proveedor");
                id = sc.nextInt();
                sc.nextLine();

                ProveedorDAO proveedorDAO = new ProveedorDAO();
                Proveedor proveedor = proveedorDAO.proveedorPorID(id);

                if(proveedor != null){
                    menuModificacion(proveedor);
                    pasar = false;
                }else{
                    System.out.println("No se ha encontrado el ID del proveedor");
                    System.out.println("Vuelve a intentarlo");
                    pasar = true;
                }
            }catch(Exception e){
                System.out.println("Error " + e.getMessage());
            }
        }while(pasar);
    }

    public void menuModificacion(Proveedor proveedor){

        int opcion;

        do{
            System.out.println("Menu de modificacion del proveedor");
            System.out.println("Que quieres modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Tipo");
            System.out.println("3. Telefono");
            System.out.println("4. Email");
            System.out.println("5. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1:{
                    System.out.println("Introduce el nuevo nombre");
                    String nombre = sc.nextLine();
                    proveedor.setNombre(nombre);
                }
                case 2:{
                    System.out.println("Introduce el nuevo tipo");
                    String tipo = sc.nextLine();
                    proveedor.setTipo(tipo);
                }
                case 3:{
                    System.out.println("Introduce el nuevo telefono");
                    int telefono = sc.nextInt();
                    sc.nextLine();
                    proveedor.setTelefono(telefono);
                }                
                case 4:{
                    System.out.println("Introduce el nuevo email");
                    String email = sc.nextLine();
                    proveedor.setEmail(email);
                }
                case 5: {System.out.println("Saliendo...");}
                default: {System.out.println("Valor no valido, vuelve a intentarlo");}
            }
        }while(opcion != 5);
    }

    public void listarProveedor(){
        System.out.println("Listado de proveedores");
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        for(Proveedor proveedor : proveedorDAO.obtenerProveedor()){
            System.out.println(proveedor);
        }
    }

    public void proveedorPorID(){
        System.out.println("Introduce el ID del proveedor");
        int id = sc.nextInt();
        sc.nextLine();
        ProveedorDAO proveedorDAO = new ProveedorDAO();
        Proveedor proveedor = proveedorDAO.proveedorPorID(id);

        if(proveedor != null){
            System.out.println(proveedor);
        }else{
            System.out.println("No se ha encontrado al proveedor");
        }
    }
}