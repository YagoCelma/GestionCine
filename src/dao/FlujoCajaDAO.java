package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import model.FlujoCaja;

public class FlujoCajaDAO {
    public void generarGastosNominas() {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT id, nombre, salario FROM empleados";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            String insertSql = "INSERT INTO flujo_caja (tipo, descripcion, cantidad, fecha, categoria) VALUES ('GASTO', ?, ?, ?, 'NOMINA')";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);

            Date fechaActual = Date.valueOf(LocalDate.now());
            int contador = 0;

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double salario = rs.getDouble("salario");
                String descripcion = "Pago de nómina a " + nombre;

                insertStmt.setString(1, descripcion);
                insertStmt.setDouble(2, salario);
                insertStmt.setDate(3, fechaActual);
                insertStmt.addBatch();
                contador++;
            }

            insertStmt.executeBatch();
            System.out.println(contador + " gastos por nómina generados automáticamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registrarMovimiento(FlujoCaja movimiento) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO flujo_caja (tipo, descripcion, cantidad, fecha, categoria) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, movimiento.getTipo());
            stmt.setString(2, movimiento.getDescripcion());
            stmt.setDouble(3, movimiento.getCantidad());
            stmt.setDate(4, movimiento.getFecha());
            stmt.setString(5, movimiento.getCategoria());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                movimiento.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void calcularSaldoActual() {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT (SELECT COALESCE(SUM(cantidad), 0) FROM flujo_caja WHERE tipo = 'INGRESO' AND fecha <= ?) - (SELECT COALESCE(SUM(cantidad), 0) FROM flujo_caja WHERE tipo = 'GASTO' AND fecha <= ?) as saldo";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double saldo = rs.getDouble(1);
                System.out.printf("Saldo actual: %.2f€\n", saldo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ingresosDelMes() {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT SUM(cantidad) FROM flujo_caja WHERE tipo = 'INGRESO' AND fecha BETWEEN ? AND ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(LocalDate.now().withDayOfMonth(1)));
            stmt.setDate(2, Date.valueOf(LocalDate.now().withDayOfMonth(LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()))));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double ingresos = rs.getDouble(1);
                System.out.printf("Ingresos del mes: %.2f€\n", ingresos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gastosDelMes() {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT SUM(cantidad) FROM flujo_caja WHERE tipo = 'GASTO' AND fecha BETWEEN ? AND ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(LocalDate.now().withDayOfMonth(1)));
            stmt.setDate(2, Date.valueOf(LocalDate.now().withDayOfMonth(LocalDate.now().getMonth().length(LocalDate.now().isLeapYear()))));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double gastos = rs.getDouble(1);
                System.out.printf("Gastos del mes: %.2f€\n", gastos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarResumenPorCategoria() {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT categoria, SUM(cantidad) FROM flujo_caja GROUP BY categoria";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String categoria = rs.getString("categoria");
                double cantidad = rs.getDouble("SUM(cantidad)");
                System.out.printf("%s: %.2f€\n", categoria, cantidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
