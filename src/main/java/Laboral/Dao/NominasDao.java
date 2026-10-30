package Laboral.Dao;

import Laboral.Modelo.NominaModel;
import Laboral.Modelo.EmpleadoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class NominasDao {


    public NominaModel buscarPorDni(String dni) {

        String sql = "SELECT dni, sueldo\n" +
                "FROM Nominas\n" +
                "WHERE dni = ?\n";

        try (Connection con = ConexionDb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new NominaModel(
                            rs.getString("dni"),
                            rs.getInt("sueldo")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar nómina: "
                    + e.getMessage());
        }

        return null;
    }

    public boolean recalcularYActualizar(String dni) {
        // Fórmula estándar de sueldo basada en categoría y años trabajados (ejemplo típico)
        // Sueldo base según categoría + complemento por años (5000 * categoria + 1000 * anyos, por ejemplo)
        // Ajusta la lógica matemática de la fórmula según los requerimientos de tu práctica.
        String sqlSelect = "SELECT categoria, anyos FROM Empleados WHERE dni = ?";
        String sqlUpsert = "INSERT INTO Nominas (dni, sueldo) VALUES (?, ?) ON DUPLICATE KEY UPDATE sueldo = ?";

        try (Connection con = ConexionDb.getConexion();
             PreparedStatement psSelect = con.prepareStatement(sqlSelect)) {

            psSelect.setString(1, dni);
            try (ResultSet rs = psSelect.executeQuery()) {
                if (rs.next()) {
                    int categoria = rs.getInt("categoria");
                    int anyos = rs.getInt("anyos");

                    // Cálculo del sueldo (ajusta esta fórmula según tu enunciado específico)
                    int sueldoCalculado = 50000 + (categoria * 5000) + (anyos * 1000);

                    try (PreparedStatement psUpsert = con.prepareStatement(sqlUpsert)) {
                        psUpsert.setString(1, dni);
                        psUpsert.setInt(2, sueldoCalculado);
                        psUpsert.setInt(3, sueldoCalculado);
                        psUpsert.executeUpdate();
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al recalcular sueldo: " + e.getMessage());
        }
        return false;
    }

    /**
     * Recalcula los sueldos de todos los empleados existentes.
     */
    public boolean recalcularTodos(EmpleadoDao empleadoDao) {
        List<EmpleadoModel> empleados = empleadoDao.obtenerTodos();
        for (EmpleadoModel emp : empleados) {
            if (!recalcularYActualizar(emp.getDni())) {
                return false;
            }
        }
        return true;
    }

}