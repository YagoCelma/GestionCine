package dao;

import java.net.ConnectException;
import java.nio.channels.ClosedByInterruptException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Empleado;

public class EmpleadoDao {

    public void añadirEmpleado(EmpleadoDao empleado){
        
    }

    public boolean eliminarEmpleado(int id){
        try (Connection conn = ConexionDB.getConnection()){
            String sql = "DELETE FROM empleados WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; //esto devuelve true si se elimino al menos un empleado
        } catch (Exception e) {
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
            
        } catch (Exception e) {
        }
        return empleados;
    }

    public Empleado obtenerPorId(int id){
        Empleado emp = null;
        return emp;
    }

    public boolean actualizarEmpleado(Empleado empleado){
        return true;
    }
    
}
