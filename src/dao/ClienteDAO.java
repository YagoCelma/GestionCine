package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Cliente;

public class ClienteDAO{
    
    public void añadirCliente(Cliente cliente){
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "INSERT INTO clientes (nombre, apellido, telefono, email) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setInt(3, cliente.getTelefono());
            statement.setString(4, cliente.getEmail());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                int idGenerado = generatedKeys.getInt(1);
                cliente.setId(idGenerado);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean eliminarCliente(int id){
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "DELETE FROM clientes WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Cliente> obtenerClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM clientes";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("id"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setTelefono(resultSet.getInt("telefono"));
                cliente.setEmail(resultSet.getString("email"));
                clientes.add(cliente);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente clientePorID(int id){

        Cliente cliente = null;
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM clientes WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                cliente = new Cliente();
                cliente.setId(resultSet.getInt("id"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setTelefono(resultSet.getInt("telefono"));
                cliente.setEmail(resultSet.getString("email"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cliente;
    }
    
}