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
                case 5-> listarProdctos();
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
        productoDAO.productoPorID(id);
    }
}
