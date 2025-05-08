package dao;

import model.CompraButaca;
import util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraButacaDAO {
    private Connection conexion;

    public CompraButacaDAO() {
        this.conexion = Conexion.getConnection();
    }

    public boolean comprarButaca(CompraButaca compra) {
        String sqlButaca = "INSERT INTO compras_butacas (id_sesion, id_cliente, num_butaca, precio, fecha_compra, estado) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlActualizarSesion = "UPDATE sesiones SET butacas_disponibles = butacas_disponibles - 1 WHERE id = ? AND butacas_disponibles > 0";
        String sqlRegistrarIngreso = "INSERT INTO flujo_caja (tipo, descripcion, cantidad, categoria, fecha) VALUES ('INGRESO', ?, ?, 'ENTRADAS', ?)";

        try {
            conexion.setAutoCommit(false); // Iniciar transacción

            // 1. Verificar disponibilidad de la butaca
            if (!verificarDisponibilidadButaca(compra.getIdSesion(), compra.getNumButaca())) {
                conexion.rollback();
                return false;
            }

            // 2. Insertar la compra
            PreparedStatement psButaca = conexion.prepareStatement(sqlButaca, Statement.RETURN_GENERATED_KEYS);
            psButaca.setInt(1, compra.getIdSesion());
            psButaca.setInt(2, compra.getIdCliente());
            psButaca.setInt(3, compra.getNumButaca());
            psButaca.setDouble(4, compra.getPrecio());
            psButaca.setDate(5, compra.getFechaCompra());
            psButaca.setString(6, compra.getEstado());
            psButaca.executeUpdate();

            // 3. Actualizar butacas disponibles en la sesión
            PreparedStatement psSesion = conexion.prepareStatement(sqlActualizarSesion);
            psSesion.setInt(1, compra.getIdSesion());
            int filasActualizadas = psSesion.executeUpdate();
            
            if (filasActualizadas == 0) {
                conexion.rollback();
                return false;
            }

            // 4. Registrar el ingreso en flujo de caja
            PreparedStatement psFlujo = conexion.prepareStatement(sqlRegistrarIngreso);
            psFlujo.setString(1, "Venta de entrada - Sesión " + compra.getIdSesion());
            psFlujo.setDouble(2, compra.getPrecio());
            psFlujo.setDate(3, compra.getFechaCompra());
            psFlujo.executeUpdate();

            conexion.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            try {
                conexion.rollback(); // Revertir cambios en caso de error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                conexion.setAutoCommit(true); // Restaurar autocommit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean verificarDisponibilidadButaca(int idSesion, int numButaca) {
        String sql = "SELECT COUNT(*) FROM compras_butacas WHERE id_sesion = ? AND num_butaca = ? AND estado != 'CANCELADA'";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idSesion);
            ps.setInt(2, numButaca);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<CompraButaca> obtenerComprasPorSesion(int idSesion) {
        List<CompraButaca> compras = new ArrayList<>();
        String sql = "SELECT * FROM compras_butacas WHERE id_sesion = ?";
        
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idSesion);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                CompraButaca compra = new CompraButaca();
                compra.setId(rs.getInt("id"));
                compra.setIdSesion(rs.getInt("id_sesion"));
                compra.setIdCliente(rs.getInt("id_cliente"));
                compra.setNumButaca(rs.getInt("num_butaca"));
                compra.setPrecio(rs.getDouble("precio"));
                compra.setFechaCompra(rs.getDate("fecha_compra"));
                compra.setEstado(rs.getString("estado"));
                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }

    public boolean cancelarCompra(int idCompra) {
        String sql = "UPDATE compras_butacas SET estado = 'CANCELADA' WHERE id = ?";
        String sqlDevolverButaca = "UPDATE sesiones SET butacas_disponibles = butacas_disponibles + 1 WHERE id = (SELECT id_sesion FROM compras_butacas WHERE id = ?)";
        String sqlRegistrarDevolucion = "INSERT INTO flujo_caja (tipo, descripcion, cantidad, categoria, fecha) VALUES ('GASTO', ?, ?, 'DEVOLUCIONES', ?)";

        try {
            conexion.setAutoCommit(false);

            // 1. Obtener información de la compra
            CompraButaca compra = obtenerCompraPorId(idCompra);
            if (compra == null) {
                conexion.rollback();
                return false;
            }

            // 2. Cancelar la compra
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idCompra);
            ps.executeUpdate();

            // 3. Devolver la butaca a la sesión
            PreparedStatement psDevolver = conexion.prepareStatement(sqlDevolverButaca);
            psDevolver.setInt(1, idCompra);
            psDevolver.executeUpdate();

            // 4. Registrar la devolución en flujo de caja
            PreparedStatement psFlujo = conexion.prepareStatement(sqlRegistrarDevolucion);
            psFlujo.setString(1, "Devolución de entrada - Compra " + idCompra);
            psFlujo.setDouble(2, -compra.getPrecio());
            psFlujo.setDate(3, new Date(System.currentTimeMillis()));
            psFlujo.executeUpdate();

            conexion.commit();
            return true;

        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private CompraButaca obtenerCompraPorId(int id) {
        String sql = "SELECT * FROM compras_butacas WHERE id = ?";
        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                CompraButaca compra = new CompraButaca();
                compra.setId(rs.getInt("id"));
                compra.setIdSesion(rs.getInt("id_sesion"));
                compra.setIdCliente(rs.getInt("id_cliente"));
                compra.setNumButaca(rs.getInt("num_butaca"));
                compra.setPrecio(rs.getDouble("precio"));
                compra.setFechaCompra(rs.getDate("fecha_compra"));
                compra.setEstado(rs.getString("estado"));
                return compra;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
} 