package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Empleado;

public class EmpleadoDao {

    public void añadirEmpleado(Empleado empleado){
        try (Connection conn = ConexionDB.getConnection()){
            String sql = "INSERT INTO empleados (nombre, apellido, telefono, email, puesto, salario, fechaContratacion) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setInt(3, empleado.getTelefono());
            stmt.setString(4, empleado.getEmail());
            stmt.setString(5, empleado.getPuesto());
            stmt.setDouble(6, empleado.getSalario());
            stmt.setDate(7, empleado.getFechaContratacion());

            stmt.executeUpdate();

            //Esto lo hacemos para obtener el ID generado
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                empleado.setId(keys.getInt(1)); //Actualizamos el objeto empeleado
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean eliminarEmpleado(int id){
        try (Connection conn = ConexionDB.getConnection()){
            String sql = "DELETE FROM empleados WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; //esto devuelve true si se elimino al menos un empleado
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Empleado> mostrarEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        try (Connection conn = ConexionDB.getConnection()){
            String sql = "SELECT * FROM empleados ";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Empleado empleado = new Empleado();
                empleado.setId(resultSet.getInt("id"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setApellido(resultSet.getString("apellido"));
                empleado.setTelefono(resultSet.getInt("telefono"));
                empleado.setEmail(resultSet.getString("email"));
                empleado.setPuesto(resultSet.getString("puesto"));
                empleado.setSalario(resultSet.getDouble("salario"));
                empleado.setFechaContratacion(resultSet.getDate("FechaContratacion"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public Empleado obtenerPorId(int id){
        Empleado emp = null;
        String sql = "SELECT FROM empleados WHERE Id = ?";
        try (Connection conn = ConexionDB.getConnection()){
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                emp.setId(resultSet.getInt("id"));
                emp.setNombre(resultSet.getString("nombre"));
                emp.setApellido(resultSet.getString("apellido"));
                emp.setTelefono(resultSet.getInt("telefono"));
                emp.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emp;
    }

    public void actualizarEmpleado(Empleado empleado){
        try (Connection conn = ConexionDB.getConnection()){
            String sql = "UPDATE empleados SET nombre = ?, apellido = ?, telefono = ?, email = ?, puesto = ?, salario = ?, fechaContratacion = ? WHERE id = ? ";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, empleado.getNombre());
            statement.setString(1, empleado.getApellido());
            statement.setInt(1, empleado.getTelefono());
            statement.setString(1, empleado.getEmail());
            statement.setString(1, empleado.getPuesto());
            statement.setDouble(1, empleado.getSalario());
            statement.setDate(1, empleado.getFechaContratacion());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
}
