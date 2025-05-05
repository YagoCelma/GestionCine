package dao;
import java.sql.*;
import java.util.ArrayList;
import model.Cartelera;

public class CarteleraDAO {

    public void añadirCartelera(Cartelera cartelera){
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO cartelera (titulo) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cartelera.getTitulo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    public boolean eliminarPeliCartelera(String titulo){
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "DELETE FROM cartelera WHERE titulo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, titulo);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Cartelera> mostrarCartelera(){
        ArrayList<Cartelera> carteleras = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM cartelera";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cartelera c = new Cartelera();
                c.setTitulo(rs.getString("titulo")); 
                carteleras.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carteleras;
    }

    public boolean esta(String titulo) {
        String sql = "SELECT COUNT(*) FROM peliculas WHERE titulo = ?";
        try (Connection conn = ConexionDB.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, titulo);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
}
