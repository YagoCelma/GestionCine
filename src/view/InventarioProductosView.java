package view;

import dao.InventarioProductosDAO;
import java.util.*;
import model.InventarioProductos;


public class InventarioProductosView {

    Scanner sc = new Scanner(System.in);
    
    public void menuInventarioproductos(){
        int opcion;
        do { 
            System.out.println("Bienvenido al menu de Productos");
            System.out.println("1. Añadir producto");
            System.out.println("2. Borrar producto");
            System.out.println("3. Modificar producto");
            System.out.println("4. Listar productos");
            System.out.println("5. Buscar producto por ID");
            System.out.println("6.Salir");
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> añadirProducto();
                case 2-> borrarProductos();
                case 3-> modificarProducto();
                case 4-> listarProductos();
                case 5-> buscarProductoID();
                case 6-> System.out.println("Saliendo...");
                default -> System.out.println("Seleccione una opcion que sea valida");
            }
         }while (opcion != 6);
     }
    public void añadirProducto(){
            System.out.println("Introduzca el nombre");
            String nombre = sc.nextLine();
            System.out.println("Introduzca la cantidad");
            int cantidad = sc.nextInt();
            sc.nextLine();
            System.out.println("Introduzca la cantidad minima");
            int cantidad_minima = sc.nextInt();
            sc.nextLine();

            System.out.println("Introduzca la ID del proveedor");
            int id_proveedor = sc.nextInt();
            sc.nextLine();

            InventarioProductos inventarioProductos = new InventarioProductos(nombre, cantidad, cantidad_minima, id_proveedor);
            InventarioProductosDAO inventarioProductosDAO = new InventarioProductosDAO();
            inventarioProductosDAO.crearProducto(inventarioProductos);
            System.out.println("Producto añadido correctamente con  ID" + inventarioProductos.getId());
     }
    
    private void listarProductos(){
         System.out.println("Lista de Productos:");
        InventarioProductosDAO inventarioProductosDAO = new InventarioProductosDAO();
        for (InventarioProductos inventarioProductos : inventarioProductosDAO.obtenerInventarioProductos()) {
            System.out.println(inventarioProductos);
        }
    }
    
    public void borrarProductos(){
        System.out.println("Introduce el ID del producto a eliminar:");
        int id = sc.nextInt();
        sc.nextLine();
        InventarioProductosDAO inventarioProductosDAO = new InventarioProductosDAO();
        if(inventarioProductosDAO.eliminarProducto(id)){
            System.out.println("El producto ha sido eliminado correctamente");
        }else{
            System.out.println("Producto no encontrado");

        }
    }
    public void buscarProductoID(){
        System.out.println("Introduce el id del producto:");
        int id_inventario_productos = sc.nextInt();
        sc.nextLine();
        InventarioProductosDAO inventarioProductosDAO = new InventarioProductosDAO();
        InventarioProductos inventarioProductos = inventarioProductosDAO.ProductosPorID(id_inventario_productos);

        
        if(inventarioProductos != null){
            System.out.println(inventarioProductos);
        }else{
            System.out.println("No se ha encontrado el producto");
        }
    }

    
    public void modificarProducto(){
        System.out.println("Introduce el ID del producto a modificar ");
        int id =sc.nextInt();
        sc.nextLine();
        InventarioProductosDAO inventarioProductosDAO = new InventarioProductosDAO();
        InventarioProductos inventarioProductos= inventarioProductosDAO.ProductosPorID(id);
        if(inventarioProductos != null){
            menuModificacionProducto(inventarioProductos);
        }else{
            System.out.println("Producto no encontrado");
        }
    }
    
    public void menuModificacionProducto(InventarioProductos inventarioProductos){
        int opcion;

        do{
            System.out.println("Que quieres modificar del producto?");
            System.out.println("1. Nombre");
            System.out.println("2. Cantidad");
            System.out.println("3. Cantidad minima");
            System.out.println("4. Salir");
            System.out.println("Elige una opcion:");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> actualizarNombre(inventarioProductos);
                case 2-> actualizarCantidad(inventarioProductos);
                case 3-> actualizarCantidadMinima(inventarioProductos); 
                case 4-> System.out.println("Saliendo...");
            }
        }while(opcion != 5);
    }
    
    public void actualizarNombre(InventarioProductos inventarioProductos){
        System.out.println("Introduce el nuevo nombre");
        String nombre = sc.nextLine();
        inventarioProductos.setNombre(nombre);
    }
    
    public void actualizarCantidad(InventarioProductos inventarioProductos){
        System.out.println("Introduce la nueva cantidad");
        int cantidad = sc.nextInt();
        inventarioProductos.setCantidad(cantidad);
    }
   
    public void actualizarCantidadMinima(InventarioProductos inventarioProductos){
        System.out.println("Introduce la nueva cantidad minima");
        int cantidadMinima = sc.nextInt();
        sc.nextLine();
        inventarioProductos.setCantidadMinima(cantidadMinima);
    }
}


    
    

