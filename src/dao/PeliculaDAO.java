package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Hay que hacer los metodos de conexion

public class PeliculaDAO {
    public void insertarPelicula(model.Pelicula pelicula) {
        // Connection conexion = dao.ConexionDB.conectar();

        String titulo = pelicula.getTitulo();
        String director = pelicula.getDirector();
        String genero = pelicula.getGenero();
        int duracion = pelicula.getDuracion();
        String clasificacion = pelicula.getClasificacion();
        double precio = pelicula.getPrecioEntrada();
        boolean enCartelera = pelicula.isEnCartelera();

        String query = "INSERT INTO peliculas (titulo, director, genero, duracion, clasificacion, precio, en_cartelera) VALUES (?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {

            stmt.setString(1, titulo);
            stmt.setString(2, director);
            stmt.setString(3, genero);
            stmt.setInt(4, duracion);
            stmt.setString(5, clasificacion);
            stmt.setDouble(6, precio);
            stmt.setBoolean(7, enCartelera);

            stmt.executeUpdate();

            System.out.println("Los datos se han introducido con exito");

        } catch (SQLException e) {

            System.out.println("Error al introducir datos");

        }

    }

    public void modificarTituloPelicula(String id, String nuevoTitulo) {

        Connection conexion = dao.ConexionDB.conectar();

        String query = "UPDATE peliculas SET titulo = ? WHERE id=" + id;

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {

            stmt.setString(1, nuevoTitulo);
            stmt.executeUpdate();

            System.out.println("Los datos se han actualizado con exito");

        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }

    }

    public void modificarDirectorPelicula(String id, String nuevoDirector) {
        Connection conexion = dao.ConexionDB.conectar();
        String query = "UPDATE peliculas SET director = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nuevoDirector);
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void modificarGeneroPelicula(String id, String nuevoGenero) {
        Connection conexion = dao.ConexionDB.conectar();
        String query = "UPDATE peliculas SET genero = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nuevoGenero);
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void modificarDuracionPelicula(String id, int nuevaDuracion) {
        Connection conexion = dao.ConexionDB.conectar();
        String query = "UPDATE peliculas SET duracion = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, nuevaDuracion);
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void modificarClasificacionPelicula(String id, String nuevaClasificacion) {
        Connection conexion = dao.ConexionDB.conectar();
        String query = "UPDATE peliculas SET clasificacion = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nuevaClasificacion);
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void modificarPrecioPelicula(String id, double nuevoPrecio) {
        Connection conexion = dao.ConexionDB.conectar();
        String query = "UPDATE peliculas SET precio = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setDouble(1, nuevoPrecio);
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void modificarEnCartelera(String id, boolean nuevoEnCartelera) {
        Connection conexion = dao.ConexionDB.conectar();
        String query = "UPDATE peliculas SET en_cartelera = ? WHERE id=" + id;
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setBoolean(1, nuevoEnCartelera);
            stmt.executeUpdate();
            System.out.println("Los datos se han actualizado con exito");
        } catch (SQLException e) {
            System.out.println("Error al actualizar datos");
        }
    }

    public void mostrarPeliculas(String id) {

        Connection conexion = dao.ConexionDB.conectar();

        String query = "SELECT * FROM peliculas WHERE id=" + id;
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
                System.out.println("En cartelera: " + rs.getBoolean("en_cartelera"));
                System.out.println("-------------------------");

            }

        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta" + e.getMessage());

        }
    }

    public void mostrarPeliculaByID(String id) {

        Connection conexion = dao.ConexionDB.conectar();

        String query = "SELECT * FROM peliculas WHERE id=" + id;
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
                System.out.println("En cartelera: " + rs.getBoolean("en_cartelera"));
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al realizar la consulta: " + e.getMessage());

        }
    }

    public void borrarPelicula(String id) {

        Connection conexion = dao.ConexionDB.conectar();

        String query = "DELETE FROM peliculas WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("La película se ha eliminado con éxito");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la película: " + e.getMessage());

        }
    }
}
