package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.InventarioPelicula;
import model.Pelicula;

public class InventarioPeliculaDAO {
    
    private Connection conexion;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public boolean insertarInventario(InventarioPelicula inventarioPelicula) {
        String sql = "INSERT INTO inventario_pelicula (pelicula_id, copias, copias_disponibles) VALUES (?, ?, ?)";
        try {
            Connection conexion = dao.ConexionDB.getConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, inventarioPelicula.getPelicula().getId());
            ps.setInt(2, inventarioPelicula.getCopias());
            ps.setInt(3, inventarioPelicula.getCopiasDisponibles());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar inventario: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.getMessage());
            }
        }
    }
    
    public boolean actualizarInventario(InventarioPelicula inventarioPelicula) {
        String sql = "UPDATE inventario_pelicula SET pelicula_id=?, copias=?, copias_disponibles=? WHERE id=?";
        try {
            Connection conexion = dao.ConexionDB.getConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, inventarioPelicula.getPelicula().getId());
            ps.setInt(2, inventarioPelicula.getCopias());
            ps.setInt(3, inventarioPelicula.getCopiasDisponibles());
            ps.setInt(4, inventarioPelicula.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar inventario: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.getMessage());
            }
        }
    }
    
    public boolean eliminarInventario(int id) {
        String sql = "DELETE FROM inventario_pelicula WHERE id=?";
        try {
            Connection conexion = dao.ConexionDB.getConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar inventario: " + e.getMessage());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.getMessage());
            }
        }
    }
    
    public InventarioPelicula buscar(int id) {
        String sql = "SELECT * FROM inventario_pelicula WHERE id=?";
        try {
            Connection conexion = dao.ConexionDB.getConnection();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()) {
                InventarioPelicula inventario = new InventarioPelicula();
                inventario.setId(rs.getInt("id"));
                
                PeliculaDAO peliculaDAO = new PeliculaDAO();
                Pelicula pelicula = peliculaDAO.mostrarPeliculaByID(rs.getInt("pelicula_id"));
                inventario.setPelicula(pelicula);
                
                inventario.setCopias(rs.getInt("copias"));
                inventario.setCopiasDisponibles(rs.getInt("copias_disponibles"));
                return inventario;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error al buscar inventario: " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.getMessage());
            }
        }
    }
    
    public List<InventarioPelicula> listar() {
        List<InventarioPelicula> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario_pelicula";
        try {
            Connection conexion = dao.ConexionDB.getConnection();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                InventarioPelicula inventario = new InventarioPelicula();
                inventario.setId(rs.getInt("id"));
                
                PeliculaDAO peliculaDAO = new PeliculaDAO();
                Pelicula pelicula = peliculaDAO.mostrarPeliculaByID(rs.getInt("pelicula_id"));
                inventario.setPelicula(pelicula);
                
                inventario.setCopias(rs.getInt("copias"));
                inventario.setCopiasDisponibles(rs.getInt("copias_disponibles"));
                lista.add(inventario);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("Error al listar inventarios: " + e.getMessage());
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar conexiones: " + e.getMessage());
            }
        }
    }
} 