package view;

import java.util.Scanner;
import dao.ProductoDAO;
import model.Producto;


public class ProductoView {
    
    Scanner sc = new Scanner(System.in);

    public void menuProducto(){

        int opcion;
        do{
            System.out.println("Menu de Producto");
            System.out.println("1. Añadir producto");
            System.out.println("2. Borrar producto");
            System.out.println("3. Modificar producto");
            System.out.println("4. Producto por ID");
            System.out.println("5. Listar productos");
            System.out.println("6. Salir");
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> añadirProducto();
                case 2-> borrarProducto();
                case 3-> modificarProducto();
                case 4-> productoPorID();
                case 5-> listarProductos();
                case 6-> System.out.println("Saliendo...");
                default-> System.out.println("Valor no valido, vuelve a intentarlo");
            }
        }while(opcion != 6);
    }

    public void añadirProducto(){

        System.out.println("Indique el nombre del producto");
        String nombre = sc.nextLine();

        System.out.println("Indique el precio del producto");
        double precio = sc.nextDouble();
        sc.nextLine();

        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = new Producto(nombre, precio);
        productoDAO.añadirProducto(producto);

        System.out.println("Producto añadido correctamente");
    }

    public void borrarProducto(){

        System.out.println("Indique el ID del producto");
        int id = sc.nextInt();
        sc.nextLine();

        ProductoDAO productoDAO = new ProductoDAO();
        if(productoDAO.eliminarProducto(id)){
            System.out.println("Producto eliminado correctamente");
        }else{
            System.out.println("Producto no encontrado");
        }
    }

    public void modificarProducto(){

        System.out.println("Indique el ID del producto");
        int id = sc.nextInt();
        sc.nextLine();

        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = productoDAO.productoPorID(id);

        if(producto != null){
            menuModificacion(producto);
        }else{
            System.out.println("No se ha encontrado el producto");
        }
    }

    public void menuModificacion(Producto producto){

        int opcion;
        do{
            System.out.println("Menu Modificacion");
            System.out.println("Que quieres modificar?");
            System.out.println("1. Nombre");
            System.out.println("2. Precio");
            System.out.println("3. Salir");
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> actualizarNombre(producto);
                case 2-> actualizarPrecio(producto);
                case 3-> System.out.println("Saliendo...");
                default-> System.out.println("Opcion no valida vuelva a intentarlo");
            }
        }while(opcion != 3);
    }

    public void actualizarNombre(Producto producto){

        System.out.println("Introduce el nuevo nombre");
        String nombre = sc.nextLine();
        producto.setNombre(nombre);
        System.out.println("Actualizado con exito");
    }

    public void actualizarPrecio(Producto producto){

        System.out.println("Introduce el nuevo precio");
        double precio = sc.nextDouble();
        sc.nextLine();
        producto.setPrecio(precio);
        System.out.println("Actualizado con exito");
    }

    public void productoPorID(){
        System.out.println("Indique el ID del producto");
        int id = sc.nextInt();
        sc.nextLine();

        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = productoDAO.productoPorID(id);

        if(producto != null){
            System.out.println(producto);
        }else{
            System.out.println("No se ha encontrado el producto");
        }
    }

    public void listarProductos(){
        System.out.println("Listado de productos");
        ProductoDAO productoDAO = new ProductoDAO();
        for(Producto producto : productoDAO.obtenerProductos()){
            System.out.println(producto);
        }
    }
}
