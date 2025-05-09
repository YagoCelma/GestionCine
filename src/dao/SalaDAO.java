package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Sala;

public class SalaDAO {
    
    public void crearSala(Sala sala){

        try(Connection conn = ConexionDB.getConnection()){
            String sql = "INSERT INTO sala(numeroFilas, numeroColumnas) VALUES (?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, sala.getNumeroFilas());
            statement.setInt(2, sala.getNumeroColumnas());
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
            String sql = "DELETE FROM sala WHERE id_sala = ?";
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
            String sql = "SELECT * FROM sala WHERE id_sala = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                sala = new Sala();
                sala.setNumeroFilas(resultSet.getInt("numeroFilas"));
                sala.setNumeroColumnas(resultSet.getInt("numeroColumnas"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return sala;
    }

    public ArrayList<Sala> obtenerSala(){

        ArrayList<Sala> salas = new ArrayList<>();
        try(Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM sala";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Sala sala = new Sala();
                sala.setId(resultSet.getInt("id_sala"));
                sala.setNumeroFilas(resultSet.getInt("numeroFilas"));
                sala.setNumeroColumnas(resultSet.getInt("numeroColumnas"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return salas;
    }
}
