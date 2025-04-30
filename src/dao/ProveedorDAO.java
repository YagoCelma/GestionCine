package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Proveedor;

public class ProveedorDAO {
    
    public void agregarProveedor(Proveedor proveedor){

        try(Connection conn = ConexionDB.getConnection()){
            String sql = "INSERT INTO proveedor(nombre, tipo, telefono, email) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, proveedor.getNombre());
            statement.setString(2,proveedor.getTipo());
            statement.setInt(3, proveedor.getTelefono());
            statement.setString(4, proveedor.getEmail());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                int idGenerado = generatedKeys.getInt(1);
                proveedor.setId(idGenerado);
            }
        }catch(SQLException e){
            e.printStackTrace();}
    }

    public boolean borrarProveedor(int id){
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "DELETE FROM proveedor WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Proveedor> listarProveedor(){
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM proveedor";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Proveedor proveedor = new Proveedor();
                proveedor.setId(resultSet.getInt("id"));
                proveedor.setNombre(resultSet.getString("nombre"));
                proveedor.setTipo(resultSet.getString("tipo"));
                proveedor.setTelefono(resultSet.getInt("telefono"));
                proveedor.setEmail(resultSet.getString("email"));
                proveedores.add(proveedor);
            }
    }catch(SQLException e){
            e.printStackTrace();
        }
        return proveedores;
    }

    public Proveedor provedorPorId(int id){
        Proveedor proveedor = null;
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM proveedor WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                proveedor = new Proveedor();
                proveedor.setId(resultSet.getInt("id"));
                proveedor.setNombre(resultSet.getString("nombre"));
                proveedor.setTipo(resultSet.getString("tipo"));
                proveedor.setTelefono(resultSet.getInt("telefono"));
                proveedor.setEmail(resultSet.getString("email"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return proveedor;
    }
    
    

}
