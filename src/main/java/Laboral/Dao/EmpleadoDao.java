package Laboral.Dao;

import Laboral.Modelo.EmpleadoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Acceso a datos de los empleados.
 */
public class EmpleadoDao {


    public List<EmpleadoModel> obtenerTodos() {

        List<EmpleadoModel> listaEmpleados = new ArrayList<>();

        String sql = "SELECT nombre, dni, sexo, categoria, anyos\n" +
                     "FROM Empleados\n";

        try (Connection con = ConexionDb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                EmpleadoModel empleado = new EmpleadoModel(
                        rs.getString("nombre"),
                        rs.getString("dni"),
                        rs.getString("sexo").charAt(0),
                        rs.getInt("categoria"),
                        rs.getInt("anyos")
                );

                listaEmpleados.add(empleado);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar empleados: "
                    + e.getMessage());
        }

        return listaEmpleados;
    }

    /**
     * Busca un empleado por su DNI.
     *
     * @param dni DNI del empleado
     * @return empleado encontrado o null
     */
    public EmpleadoModel buscarPorDni(String dni) {

        String sql = "SELECT nombre, dni, sexo, categoria, anyos\n" +
                     "FROM Empleados\n" +
                     "WHERE dni = ?\n";

        try (Connection con = ConexionDb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new EmpleadoModel(
                            rs.getString("nombre"),
                            rs.getString("dni"),
                            rs.getString("sexo").charAt(0),
                            rs.getInt("categoria"),
                            rs.getInt("anyos")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar empleado: "
                    + e.getMessage());
        }

        return null;
    }

    /**
     * Modifica los datos de un empleado en la base de datos.
     *
     * @param empleado empleado con los datos actualizados
     * @return true si se actualizó correctamente
     */
    public boolean actualizar(EmpleadoModel empleado) {
        String sql = "UPDATE Empleados\n" +
                     "SET nombre = ?, sexo = ?, categoria = ?, anyos = ?\n" +
                     "WHERE dni = ?\n";

        try (Connection con = ConexionDb.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, String.valueOf(empleado.getSexo()));
            ps.setInt(3, empleado.getCategoria());
            ps.setInt(4, empleado.getAnyos());
            ps.setString(5, empleado.getDni());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            return false;
        }
    }

}