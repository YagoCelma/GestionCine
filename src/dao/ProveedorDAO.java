package dao;

import view.ProveedorView;
import model.Proveedor;
import java.sql.*;

public class ProveedorDAO {
    
    public void agregarProveedor(Proveedor proveedor){

        try(Connection conn = ConexionDB.getConnection()){
            String sql = "INSERT INTO proveedor(nombre, tipo, telefono, email) VALUES (?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
        }
    }
}
