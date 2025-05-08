package dao;

import java.sql.*;
import model.Producto;
import java.util.ArrayList;

public class ProductoDAO {
    
    public void añadirProducto(Producto producto){
        try(Connection conn = ConexionDB.getConnection()){
            String sql ="INSERT INTO producto(nombre, precio) VALUES (?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecio());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                int idGenerado = generatedKeys.getInt(1);
                producto.setId(idGenerado);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean eliminarProducto(int id){
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "DELETE FROM producto WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public Producto productoPorID(int id){

        Producto producto = null;
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM producto WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()){
                producto = new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setPrecio(resultSet.getDouble("precio"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return producto;
    }

    public ArrayList<Producto> obtenerProductos(){
        
        ArrayList<Producto> productos = new ArrayList<>();
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM producto";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Producto producto = new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setPrecio(resultSet.getDouble("precio"));
                productos.add(producto);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return productos;
    }


}
