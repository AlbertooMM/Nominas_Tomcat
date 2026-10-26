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
        List<EmpleadoModel> empleados = new ArrayList<>();

        String sql = "SELECT nombre, dni, sexo, categoria, anyos FROM empleados";

        try (Connection con = ConexionDb.getConexion()) {

            if (con == null) {
                throw new SQLException("La conexión con la base de datos es null");
            }

            System.out.println("Base de datos conectada: "
                    + con.getCatalog());

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    EmpleadoModel empleado = new EmpleadoModel();

                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setDni(rs.getString("dni"));
                    empleado.setSexo(rs.getString("sexo").charAt(0));
                    empleado.setCategoria(rs.getInt("categoria"));
                    empleado.setAnyos(rs.getInt("anyos"));

                    empleados.add(empleado);
                }
            }

            System.out.println("Empleados encontrados: " + empleados.size());

        } catch (SQLException e) {
            System.err.println("Error al obtener empleados:");
            e.printStackTrace();
            throw new RuntimeException("No se pudieron obtener los empleados", e);
        }

        return empleados;
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