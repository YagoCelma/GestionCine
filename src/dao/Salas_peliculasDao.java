package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Salas_peliculas;

public class Salas_peliculasDao {

    public void agregarSalaPelicula(Salas_peliculas salas_peliculas){
        try(Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO salas_peliculas (nombre_pelicula, fecha_incio_emision, fecha_fin_emision, hora_inicio, hora_fin)  ";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, salas_peliculas.getNombre());
            stmt.setDate(2, salas_peliculas.getfecha_inicio_emision());
            stmt.setDate(3, salas_peliculas.getfecha_fin_emision());
            stmt.setTime(4, salas_peliculas.getHora_inicio());
            stmt.setTime(5, salas_peliculas.getHora_fin());

            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()){
                salas_peliculas.setId(keys.getInt(1));
            }

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean  eliminarSalasPeliculas(int id){
        try(Connection conn = ConexionDB.getConnection()) {
            String sql = "DELETE FROM salas_peliculas WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Salas_peliculas> mostrarSalasPeliculas(){
        ArrayList<Salas_peliculas> salas_peliculas = new ArrayList<>();
        return salas_peliculas;
    }

    public Salas_peliculas obtenerPorId(int id){
        Salas_peliculas sp = null;
        return sp;
    }

    public void actualizarSalaPelicula(Salas_peliculas salas_peliculas){

    }
    
}
