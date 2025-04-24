package view;

import java.util.*;

public class ClienteView {

    Scanner sc = new Scanner(System.in);
    
    public void menuCliente(){
        int opcion;
        do{
            System.out.println("Menu de Clientes");
            System.out.println("1. Añadir cliente");
            System.out.println("2. Borrar cliente");
            System.out.println("3. Modificar cliente");
            System.out.println("4. Listar clientes");
            System.out.println("5. Buscar cliente por ID");
            System.out.println("6. Salir");
            System.out.println("Elige una opcion");
            opcion = sc.nextInt();
            sc.nextLine();
    
            switch(opcion){
                case 1-> añadirCliente();
                case 2-> borrarCliente();
                case 3-> modificarCliente();
                case 4-> listarCliente();
                case 5 -> buscarClienteID();
                default -> System.out.println("Seleccione una opcion que sea valida");
            }
        }while(opcion != 6);
    }
    public void añadirCliente(){
        System.out.println("Introduzca el nombre");
        String nombre = sc.nextLine();
        System.out.println("Introduzca el apellidos");
        String apellidos = sc.nextLine();
        System.out.println("Introduzca el telefono");
        int telefono = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduzca el email");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(nombre, apellidos, telefono, email);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.añadirCliente(cliente);
        System.out.println("Cliente añadido correctamente con ID: " + cliente.getId());
    }
    private void listarClientes() {
        System.out.println("Lista de Clientes:");
        ClienteDao clienteDao = new ClienteDao();
        for (Cliente cliente : clienteDao.obtenerClientes()) {
            System.out.println(cliente);
        }
    }

}
