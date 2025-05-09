package dao;

import java.sql.*;
import java.util.ArrayList;
import model.InventarioProductos;


public class InventarioProductosDAO {

    public void crearProducto(InventarioProductos inventarioProductos){
        try(Connection conn = ConexionDB.getConnection()){
            String sql = " INSERT INTO inventario_productos (nombre_producto, cantidad, cantidad_minima, id_proveedor) VALUES(?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, inventarioProductos.getNombre());
            statement.setInt(2, inventarioProductos.getCantidad());
            statement.setInt(3, inventarioProductos.getCantidadMinima());
            statement.setInt(4, inventarioProductos.getIdProveedor());
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
            String sql = "DELETE FROM inventario_productos WHERE id_inventario_producto = ?";
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
        ArrayList<InventarioProductos> inventario_productos = new ArrayList<>();
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM inventario_productos";
            Statement statement = (Statement) conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                InventarioProductos inventarioProductos = new InventarioProductos();
                inventarioProductos.setCantidad(resultSet.getInt("id_inventario_producto"));
                inventarioProductos.setNombre(resultSet.getString("nombre_producto"));
                inventarioProductos.setCantidad(resultSet.getInt("cantidad"));
                inventarioProductos.setCantidadMinima(resultSet.getInt("cantidad_minima"));
                inventarioProductos.setIdProveedor(resultSet.getInt("id_proveedor"));
                inventario_productos.add(inventarioProductos);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return inventario_productos;
    }

    public InventarioProductos ProductosPorID(int id_inventario_productos){
        InventarioProductos inventarioProductos = null;
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM inventario_productos WHERE id_inventario_producto = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id_inventario_productos);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                inventarioProductos = new InventarioProductos();
                inventarioProductos.setId(resultSet.getInt("id_inventario_producto"));
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
