package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Emision;

public class EmisionDAO {

    public void agregarEmision(Emision emision){
        try (Connection conn = conexionDB.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            String sql = "INSERT INTO emison (id, nombre, duracion, fecha_inicio_emision, fecha_fin_emision)";
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public ArrayList<Emision> mostrarEmision(){
        ArrayList<Emision> emision = new ArrayList<>();
        return emision;
    }
    
}
