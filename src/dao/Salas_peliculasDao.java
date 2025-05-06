package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public boolean  eliminar(int id){
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

public ArrayList<Salas_peliculas> mostrar() {
    ArrayList<Salas_peliculas> salas_peliculas = new ArrayList<>();

    String sql = "SELECT * FROM salas_peliculas";
    try (Connection conn = ConexionDB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Salas_peliculas sp = new Salas_peliculas();
            sp.setId(rs.getInt("id"));
            sp.setNombrePelicula(rs.getString("nombre_pelicula"));
            sp.setHora_inicio(rs.getTime("hora_inicio"));
            sp.setHora_fin(rs.getTime("hora_fin"));
            sp.setId_sala(rs.getInt("id_sala"));
            sp.setPrecioBase(rs.getDouble("precio_base"));
            sp.setIdPelicula(rs.getInt("id_pelicula"));
            salas_peliculas.add(sp);
        }

    } catch (SQLException e) {
        System.out.println("Error al mostrar salas_peliculas: " + e.getMessage());
    }

    return salas_peliculas;
}

public Salas_peliculas buscarSalaPeliPorID(int id) {
    Salas_peliculas sp = null;

    String sql = "SELECT * FROM salas_peliculas WHERE id = ?";

    try (Connection conn = ConexionDB.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            sp = new Salas_peliculas();
            sp.setId(rs.getInt("id"));
            sp.setNombrePelicula(rs.getString("nombre_pelicula"));
            sp.setHora_inicio(rs.getTime("hora_inicio"));
            sp.setHora_fin(rs.getTime("hora_fin"));
            sp.setId_sala(rs.getInt("id_sala"));
            sp.setPrecioBase(rs.getDouble("precio_base"));
            sp.setIdPelicula(rs.getInt("id_pelicula"));
        }

    } catch (SQLException e) {
        System.out.println("Error al buscar sala_pelicula por ID: " + e.getMessage());
    }

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
            stmt.setInt(7, sp.getId()); 
    
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

