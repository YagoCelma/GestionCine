package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Entrada;

public class EntradaDAO {

    public void añadirEntrada(Entrada entrada) {
        String sql = "INSERT INTO entradas (precio, tipo, fecha, hora, tipoEntrada, nombrePelicula, sala) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, entrada.getPrecio());
            statement.setString(2, entrada.getTipo());
            statement.setString(3, entrada.getFecha());
            statement.setString(4, entrada.getHora());
            statement.setString(5, entrada.getTipoEntrada());
            statement.setString(6, entrada.getNombrePelicula());
            statement.setString(7, entrada.getSala());
            statement.executeUpdate();
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
        String sql = "UPDATE entradas SET precio = ?, tipo = ?, fecha = ?, hora = ?, tipoEntrada = ?, nombrePelicula = ?, sala = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, entrada.getPrecio());
            statement.setString(2, entrada.getTipo());
            statement.setString(3, entrada.getFecha());
            statement.setString(4, entrada.getHora());
            statement.setString(5, entrada.getTipoEntrada());
            statement.setString(6, entrada.getNombrePelicula());
            statement.setString(7, entrada.getSala());
            statement.setInt(8, entrada.getId());
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
                        resultSet.getInt("precio"),
                        resultSet.getString("tipo"),
                        resultSet.getString("fecha"),
                        resultSet.getString("hora"),
                        resultSet.getString("tipoEntrada"),
                        resultSet.getString("nombrePelicula"),
                        resultSet.getString("sala")
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
                        resultSet.getInt("precio"),
                        resultSet.getString("tipo"),
                        resultSet.getString("fecha"),
                        resultSet.getString("hora"),
                        resultSet.getString("tipoEntrada"),
                        resultSet.getString("nombrePelicula"),
                        resultSet.getString("sala")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entrada;
    }

    
}
