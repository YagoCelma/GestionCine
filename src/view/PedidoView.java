package view;

import dao.PedidoDAO;
import java.sql.Time;
import java.util.Scanner;

import model.Pedido;
import model.Producto;
import dao.ProductoDAO;

public class PedidoView {

    Scanner sc = new Scanner(System.in);

    public void menuPedido(){

        int opcion;
        do{
            System.out.println("Menu Pedido");
            System.out.println("1. Añadir pedido");
            System.out.println("2. Borrar pedido");
            System.out.println("3. Modificar pedido");
            System.out.println("4. Pedido por ID");
            System.out.println("5. Listar pedidos");
            System.out.println("6. Salir");
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> añadirPedido();
                case 2-> borrarPedido();
                case 3-> modificarPedido();
                case 4-> pedidoPorID();
                case 5-> listarPedido();
                case 6-> System.out.println("Saliendo...");
                default-> System.out.println("Opcion no valida, vuelva a intentarlo");
            }
        }while(opcion != 6);
    }

    public void añadirPedido(){

        System.out.println("Indique el ID del proveedor");
        int idProveedor = sc.nextInt();
        sc.nextLine();

        System.out.println("Indique la fecha del pedido (HH:MM:SS)");
        String fechaPedidoStr = sc.nextLine();
        Time fechaPedido = Time.valueOf(fechaPedidoStr);

        System.out.println("Indique el ID del articulo");
        int idArticulo = sc.nextInt();
        sc.nextLine();

        System.out.println("Indique la cantidad del articulo");
        int cantidad = sc.nextInt();
        sc.nextLine();

        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = productoDAO.productoPorID(idArticulo);
        
        double precioTotal = producto.getPrecio() * cantidad;


        Pedido pedido = new Pedido(idProveedor, fechaPedido, idArticulo, cantidad, precioTotal);
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedidoDAO.añadirPedido(pedido);

        System.out.println("Pedido añadido correctamente");
    }

    public void borrarPedido(){
        System.out.println("Introduce el ID del pedido");
        int id = sc.nextInt();
        sc.nextLine();

        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = pedidoDAO.pedidoPorID(id);

        if(pedidoDAO.eliminarPedido(id)){
            System.out.println("Pedido eliminado correctamente");
        }else{
            System.out.println("Pedido no encontrado");
        }
    }

    public void modificarPedido(){
        System.out.println("Indique el ID del pedido");
        int id = sc.nextInt();
        sc.nextLine();

        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = pedidoDAO.pedidoPorID(id);
        if(pedido != null){
            menuModificacion(pedido);
        }else{
            System.out.println("Pedido no encontrado");
        }
    }

    public void pedidoPorID(){
        System.out.println("Introduzca le ID del pedido");
        int id = sc.nextInt();
        sc.nextLine();

        PedidoDAO pedidoDAO = new PedidoDAO();
        Pedido pedido = pedidoDAO.pedidoPorID(id);

        if(pedido != null){
            System.out.println(pedido);
        }else{
            System.out.println("No se ha encontrado el pedido");
        }
    }

    public void listarPedido(){
        System.out.println("Lista de Pedidos:");
        PedidoDAO pedidoDAO = new PedidoDAO();
        for (Pedido pedido : pedidoDAO.obtenerPedidos()) {
            System.out.println(pedido);
        }
    }

    public void menuModificacion(Pedido pedido){

        int opcion;
        do{
            System.out.println("Menu de modificacion");
            System.out.println("1. ID Proveedor");
            System.out.println("2. Fecha pedido");
            System.out.println("3. ID Articulo");
            System.out.println("4. Cantidad");
            System.out.println("5. Salir");
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> {
                    System.out.println("Introduzca el nuevo valor");
                    pedido.setIdProveedor(sc.nextInt());
                    sc.nextLine();
                }
                case 2 -> {
                    System.out.println("Introduzca la nueva fecha del pedido (HH:MM:SS)");
                    String nuevaFechaPedidoStr = sc.nextLine();

                    Time nuevaFechaPedido = Time.valueOf(nuevaFechaPedidoStr);
                    pedido.setFechaPedido(nuevaFechaPedido);
                    System.out.println("Fecha del pedido actualizada.");
                }
                case 3-> {
                    System.out.println("Introduzca el nuevo valor");
                    pedido.setIdArticulo(sc.nextInt());
                    sc.nextLine();
                }
                case 4-> {
                    System.out.println("Introduzca el nuevo valor");
                    pedido.setCantidad(sc.nextInt());
                    sc.nextLine();
                }
            }
        }while(opcion != 5);

    }


}