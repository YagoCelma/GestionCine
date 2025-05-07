package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Pedido;

public class PedidoDAO {
    
    public void añadirPedido(Pedido pedido) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO pedidos (id_proveedor, fecha_pedido, id_articulo, cantidad, precio_total) VALUES (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, pedido.getIdProveedor());
            statement.setTime(2, pedido.getFechaPedido());
            statement.setInt(3, pedido.getIdArticulo());
            statement.setInt(4, pedido.getCantidad());
            statement.setDouble(5, pedido.getPrecioTotal());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGenerado = generatedKeys.getInt(1);
                pedido.setId(idGenerado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarPedido(int id) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "DELETE FROM pedidos WHERE id_pedido = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas > 0; // Devuelve true si se eliminó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pedido pedidoPorID(int id) {
        Pedido pedido = null;
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT id_pedido, id_proveedor, fecha_pedido, id_articulo, cantidad, precio_total FROM pedidos WHERE id_pedido = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pedido = new Pedido(
                        resultSet.getInt("id_pedido"),
                        resultSet.getInt("id_proveedor"),
                        resultSet.getTime("fecha_pedido"),
                        resultSet.getInt("id_articulo"),
                        resultSet.getInt("cantidad"),
                        resultSet.getDouble("precio_total")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedido;
    }

    public ArrayList<Pedido> obtenerPedidos() {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT id_pedido, id_proveedor, fecha_pedido, id_articulo, cantidad, precio_total FROM pedidos";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Pedido pedido = new Pedido(
                        resultSet.getInt("id_pedido"),
                        resultSet.getInt("id_proveedor"),
                        resultSet.getTime("fecha_pedido"),
                        resultSet.getInt("id_articulo"),
                        resultSet.getInt("cantidad"),
                        resultSet.getDouble("precio_total")
                );
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    

}
