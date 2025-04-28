package dao;

import java.sql.*;
import view.SalaView;
import model.Cliente;
import model.Sala;
import java.util.ArrayList;

public class SalaDAO {
    
    public void crearSala(Sala sala){

        try(Connection conn = ConexionDB.getConection()){
            String sql = "INSERT INTO sala(capacidad) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sala.getCapacidad());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                int idGenerado = generatedKeys.getInt(1);
                sala.setId(idGenerado);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean borrarSala(int id){

        try(Connection conn = ConexionDB.getConnection()){
            String sql = "DELETE FROM sala WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true; 
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Sala salaPorID(int id){

        Sala sala = null;
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM sala WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                sala = new Sala();
                sala.setCapacidad(resultSet.getInt("capacidad"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Sala> obtenerSala(){

        ArrayList<Sala> salas = new ArrayList<>();
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM sala";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Sala sala = new Sala();
                sala.setId(resultSet.getInt("id"));
                sala.setCapacidad(resultSet.getInt("capacidad"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return salas;
    }
}
