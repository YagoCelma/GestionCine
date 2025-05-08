package view;

import dao.ClienteDAO;
import java.util.*;
import model.Cliente;

public class ClienteView {

    Scanner sc = new Scanner(System.in);
    
    public void menuCliente(){
        int opcion;
        do{
            System.out.println("                ");
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
                case 4-> listarClientes();
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
        System.out.println("Introduzca el DNI");
        String dni = sc.nextLine();
        System.out.println("Introduzca el telefono");
        String telefono = sc.nextLine();
        System.out.println("Introduzca el email");
        String email = sc.nextLine();

        Cliente cliente = new Cliente(nombre, apellidos,dni, telefono, email);
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.añadirCliente(cliente);
        System.out.println("Cliente añadido correctamente con ID: " + cliente.getId());
    }
    private void listarClientes() {
        System.out.println("Lista de Clientes:");
        ClienteDAO clienteDao = new ClienteDAO();
        for (Cliente cliente : clienteDao.obtenerClientes()) {
            System.out.println(cliente);
        }
    }

    public void borrarCliente(){
        System.out.println("Introduce el ID del cliente a eliminar:");
        int id = sc.nextInt();
        sc.nextLine();
        ClienteDAO clienteDAO = new ClienteDAO();
        if(clienteDAO.eliminarCliente(id)){
            System.out.println("El cliente ha sido eliminado correctamente");
        }else{
            System.out.println("Cliente no encontrado");
        }
    }

    public void buscarClienteID(){
        System.out.println("Introduce el id del cliente:");
        int id = sc.nextInt();
        sc.nextLine();
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.clientePorID(id);
        if(cliente != null){
            System.out.println(cliente);
        }else{
            System.out.println("No se ha encontrado el cliente");
        }
    }

    public void modificarCliente(){
        System.out.println("Introduce el ID del cliente a modificar");
        int id = sc.nextInt();
        sc.nextLine();
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.clientePorID(id);
        if(cliente != null){
            menuModificacion(cliente);
        }else{
            System.out.println("Cliente no encontrado");
        }
    }

    public void menuModificacion(Cliente cliente){
        int opcion;

        do{
            System.out.println("Que quieres modificar del cliente?");
            System.out.println("1. Nombre");
            System.out.println("2. Apellidos");
            System.out.println("3. Telefono");
            System.out.println("4. Email");
            System.out.println("5. Salir");
            System.out.println("Elige una opcion:");
            opcion = sc.nextInt();
            sc.nextLine();

            switch(opcion){
                case 1-> actualizarNombre(cliente);
                case 2-> actualizarApellido(cliente);
                case 3-> actualizarTelefono(cliente);
                case 4-> actualizarEmail(cliente);
                case 5-> System.out.println("Saliendo...");
            }
        }while(opcion != 5);
    }

    public void actualizarNombre(Cliente cliente){
        System.out.println("Introduce el nuevo nombre");
        String nombre = sc.nextLine();
        cliente.setNombre(nombre);
        System.out.println("Actualizado con exito");
    }

    public void actualizarApellido(Cliente cliente){
        System.out.println("Introduce el nuevo apellido");
        String apellido = sc.nextLine();
        cliente.setApellido(apellido);
        System.out.println("Actualizado con exito");
    }

    public void actualizarTelefono(Cliente cliente){
        System.out.println("Introduce el nuevo apellido");
        String telefono = sc.nextLine();
        cliente.setTelefono(telefono);
        System.out.println("Actualizado con exito");
    }

    public void actualizarEmail(Cliente cliente){
        System.out.println("Introduce el nuevo email");
        String email = sc.nextLine();
        cliente.setEmail(email);
        System.out.println("Actualizado con exito");
    }
}
