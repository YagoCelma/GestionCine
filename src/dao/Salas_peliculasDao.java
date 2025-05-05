package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Salas_peliculas;

public class Salas_peliculasDao {

    public void agregarSalaPelicula(Salas_peliculas sala) {
        String sql = "INSERT INTO salas_peliculas (nombre_pelicula, hora_inicio, hora_fin, id_sala, precio_base, id_pelicula) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sala.getNombrePelicula());
            stmt.setTime(2, sala.getHora_inicio());
            stmt.setTime(3, sala.getHora_fin());
            stmt.setInt(4, sala.getId_sala());
            stmt.setDouble(5, sala.getPrecioBase());
            stmt.setInt(6, sala.getIdPelicula());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar sala_pelicula: " + e.getMessage());
        }
    }

    public boolean eliminar(int id){
        return false;
    }

    public ArrayList<Salas_peliculas> mostrar(){
        ArrayList<Salas_peliculas> salas_peliculas = new ArrayList<>();
        return salas_peliculas;
    }

    public Salas_peliculas buscarSalaPeliPorID(int id){
        Salas_peliculas sp = null;
        return sp;
    }

    public void actualizar(Salas_peliculas sp) {
        String sql = "UPDATE salas_peliculas SET nombre_pelicula = ?, hora_inicio = ?, hora_fin = ?, id_sala = ?, precio_base = ?, id_pelicula = ? WHERE id = ?";
    
        try (Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, sp.getNombrePelicula());
            stmt.setTime(2, sp.getHora_inicio());
            stmt.setTime(3, sp.getHora_fin());
            stmt.setInt(4, sp.getId_sala());
            stmt.setDouble(5, sp.getPrecioBase());
            stmt.setInt(6, sp.getIdPelicula());
            stmt.setInt(7, sp.getId()); // importante para el WHERE
    
            int filasActualizadas = stmt.executeUpdate();
    
            if (filasActualizadas > 0) {
                System.out.println("Actualización exitosa.");
            } else {
                System.out.println("No se actualizó ninguna fila.");
            }
    
        } catch (SQLException e) {
            System.out.println("Error al actualizar salas_peliculas: " + e.getMessage());
        }
    }

    
}
    /* 
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

    public double obtenerPrecioBase(int idSalaPelicula) {
    String sql = "SELECT precio_base FROM salas_peliculas WHERE id = ?";
    try(Connection conn = ConexionDB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, idSalaPelicula);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()) {
            return rs.getDouble("precio_base");
        }
    }catch (SQLException e){
        e.printStackTrace();
    }
        return 0.0; 
    }
    */

