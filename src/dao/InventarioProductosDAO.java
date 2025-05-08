package dao;


import java.beans.Statement;
import java.util.ArrayList;
import model.InventarioProductos;
import java.net.ConnectException;
import java.nio.channels.ClosedByInterruptException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;


public class InventarioProductosDAO {

    public void crearProducto(InventarioProductos inventarioProductos){
        try(Connection conn = ConexionDB.getConnection()){
            String sql = " INSERT INTO inventario_Productos (nombre_producto, cantidad, cantidad_minima) VALUES(?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, inventarioProductos.getNombre());
            statement.setInt(2, inventarioProductos.getCantidad());
            statement.setInt(3, inventarioProductos.getCantidadMinima());
            statement.executeUpdate();

             //Para el ID auto-increment
             ResultSet generatedKeys = statement.getGeneratedKeys();
             if(generatedKeys.next()){
                 int idGenerado = generatedKeys.getInt(1);
                 inventarioProductos.setId(idGenerado);
             }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean eliminarProducto(int id){
        try (Connection conn = ConexionDB.getConnection()){
            String sql = "DELETE FORM inventario_producto WHERE id = ?";
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

    public ArrayList<InventarioProductos> obtenerInventarioProductos(){
        ArrayList<InventarioProductos> inventario_Productos = new ArrayList<>();
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM inventario_productos";
            Statement statement = (Statement) conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                InventarioProductos inventarioProductos = new InventarioProductos();
                inventarioProductos.setCantidad(resultSet.getInt("id_inventario_productos"));
                inventarioProductos.setNombre(resultSet.getString("nombre_productos"));
                inventarioProductos.setCantidad(resultSet.getInt("cantidad"));
                inventarioProductos.setCantidadMinima(resultSet.getInt("cantidad_minima"));
                inventarioProductos.setIdProveedor(resultSet.getInt("id_preoveedor"));
                inventario_Productos.add(inventarioProductos);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return inventario_Productos;
    }

    public InventarioProductos ProductosPorID(int id_inventario_productos){
        InventarioProductos inventarioProductos = null;
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM productos WHERE id_inventario_productos = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id_inventario_productos);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                inventarioProductos = new InventarioProductos();
                inventarioProductos.setId(resultSet.getInt("id_inventario_productos"));
                inventarioProductos.setNombre(resultSet.getString("nombre_producto"));
                inventarioProductos.setCantidad(resultSet.getInt("cantidad"));
                inventarioProductos.setCantidadMinima(resultSet.getInt("cantidad_minima"));
                inventarioProductos.setIdProveedor(resultSet.getInt("id_proveedor"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return inventarioProductos;
        
    }
}
