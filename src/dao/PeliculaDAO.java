package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import model.Pelicula;

public class PeliculaDAO {
    public void insertarPelicula(model.Pelicula pelicula) throws SQLException {
        Connection conexion = dao.ConexionDB.getConnection();

        String titulo = pelicula.getTitulo();
        String director = pelicula.getDirector();
        String genero = pelicula.getGenero();
        String clasificacion = pelicula.getClasificacion();
        int duracion = pelicula.getDuracion();
        double precio = pelicula.getPrecioEntrada();
        Date fechaInicio = pelicula.getFechaInicio();
        Date fechaFin = pelicula.getFechaFin();

        String query = "INSERT INTO peliculas (titulo, director, genero, duracion, clasificacion, precio, fecha_inicio, fecha_fin) VALUES (?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, titulo);
            stmt.setString(2, director);
            stmt.setString(3, genero);
            stmt.setInt(4, duracion);
            stmt.setString(5, clasificacion);
            stmt.setDouble(6, precio);
            stmt.setDate(7, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(8, new java.sql.Date(fechaFin.getTime()));

            stmt.executeUpdate();
            System.out.println("Los datos se han introducido con exito");
        } catch (SQLException e) {
            System.out.println("Error al introducir datos");
        }
    }

    public void modificarTituloPelicula(int id, String nuevoTitulo) throws SQLException {
        Connection conexion = dao.ConexionDB.getConnection();

        String query = "UPDATE peliculas SET titulo = ? WHERE id=" + id;

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {

            stmt.setString(1, nuevoTitulo);
            stmt.executeUpdate();

            System.out.println("Los datos se han actualizado con exito");

        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void modificarDirectorPelicula(int id, String nuevoDirector) throws SQLException {
        Connection conexion = dao.ConexionDB.getConnection();
        String query = "UPDATE peliculas SET director = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nuevoDirector);
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void modificarGeneroPelicula(int id, String nuevoGenero) throws SQLException {
        Connection conexion = dao.ConexionDB.getConnection();
        String query = "UPDATE peliculas SET genero = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nuevoGenero);
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void modificarDuracionPelicula(int id, int nuevaDuracion) throws SQLException {
        Connection conexion = dao.ConexionDB.getConnection();
        String query = "UPDATE peliculas SET duracion = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, nuevaDuracion);
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void modificarClasificacionPelicula(int id, String nuevaClasificacion) throws SQLException {
        Connection conexion = dao.ConexionDB.getConnection();
        String query = "UPDATE peliculas SET clasificacion = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nuevaClasificacion);
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void modificarPrecioPelicula(int id, double nuevoPrecio) throws SQLException {
        Connection conexion = dao.ConexionDB.getConnection();
        String query = "UPDATE peliculas SET precio = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setDouble(1, nuevoPrecio);
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void modificarFechasPelicula(int id, Date nuevaFechaInicio, Date nuevaFechaFin) throws SQLException {
        Connection conexion = dao.ConexionDB.getConnection();
        String query = "UPDATE peliculas SET fecha_inicio = ?, fecha_fin = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(nuevaFechaInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(nuevaFechaFin.getTime()));
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void mostrarPeliculas() throws SQLException {

        Connection conexion = dao.ConexionDB.getConnection();

        String query = "SELECT * FROM peliculas";
        try (Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getString("id"));
                System.out.println("Titulo: " + rs.getString("titulo"));
                System.out.println("Director: " + rs.getString("director"));
                System.out.println("Género: " + rs.getString("genero"));
                System.out.println("Duración: " + rs.getInt("duracion") + " minutos");
                System.out.println("Clasificación: " + rs.getString("clasificacion"));
                System.out.println("Precio entrada: " + rs.getDouble("precio") + "€");
                System.out.println("Fecha inicio: " + rs.getDate("fecha_inicio"));
                System.out.println("Fecha fin: " + rs.getDate("fecha_fin"));
                System.out.println("-------------------------");

            }

        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta" + e.getMessage());

        }
    }

    public Pelicula mostrarPeliculaByID(int id) throws SQLException {
        Connection conexion = dao.ConexionDB.getConnection();
        String query = "SELECT * FROM peliculas WHERE id=" + id;
        try (Statement stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setId(rs.getInt("id"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setGenero(rs.getString("genero"));
                pelicula.setDuracion(rs.getInt("duracion"));
                pelicula.setClasificacion(rs.getString("clasificacion"));
                pelicula.setPrecioEntrada(rs.getDouble("precio"));
                pelicula.setFechaInicio(rs.getDate("fecha_inicio"));
                pelicula.setFechaFin(rs.getDate("fecha_fin"));
                return pelicula;
            }
            return null;
        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());
            return null;
        }
    }

    public Pelicula mostrarPeliculaByTitulo(String titulo) throws SQLException { //Lo he creado para la cartelera
        Connection conexion = dao.ConexionDB.getConnection();
        String query = "SELECT * FROM peliculas WHERE titulo = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, titulo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setId(rs.getInt("id"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setDirector(rs.getString("director"));
                    pelicula.setGenero(rs.getString("genero"));
                    pelicula.setDuracion(rs.getInt("duracion"));
                    pelicula.setClasificacion(rs.getString("clasificacion"));
                    pelicula.setPrecioEntrada(rs.getDouble("precio"));
                    pelicula.setFechaInicio(rs.getDate("fecha_inicio"));
                    pelicula.setFechaFin(rs.getDate("fecha_fin"));
                    return pelicula;
                }
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());
            return null;
        }
    }

    public void borrarPelicula(int id) throws SQLException {

        Connection conexion = dao.ConexionDB.getConnection();

        String query = "DELETE FROM peliculas WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("La película se ha eliminado con éxito");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la película: " + e.getMessage());

        }
    }
}
