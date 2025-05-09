package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Entrada;

public class EntradaDAO {

    public void añadirEntrada(Entrada entrada) {
        String sql = "INSERT INTO entradas (id_sala_pelicula, precio, asiento) VALUES (?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,entrada.getidSalaPelicula());//Cambiado
            statement.setDouble(2, entrada.getPrecio());
            statement.setInt(3, entrada.getAsiento());
            
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGenerado = generatedKeys.getInt(1);
                entrada.setId(idGenerado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean borrarEntrada(int id) {
        String sql = "DELETE FROM entradas WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection()){
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

    public void modificarEntrada(Entrada entrada) {
        String sql = "UPDATE entradas SET precio = ?, asiento = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, entrada.getPrecio());
            statement.setInt(2, entrada.getAsiento());
            statement.setInt(3, entrada.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Entrada> listarEntradas() {
        ArrayList<Entrada> entradas = new ArrayList<>();
        String sql = "SELECT * FROM entradas";
        try (Connection conn = ConexionDB.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Entrada entrada = new Entrada(
                    resultSet.getInt("idSalaPeliculas"),
                    resultSet.getInt("precio"),
                    resultSet.getInt("asiento")
                );
                entradas.add(entrada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entradas;
    }
    
    public Entrada buscarEntradaPorID(int id) {
        Entrada entrada = null;
        String sql = "SELECT * FROM entradas WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entrada = new Entrada(
                    resultSet.getInt("idSalaPeliculas"),
                    resultSet.getInt("precio"),
                    resultSet.getInt("asiento")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrada;
    }

    
}
