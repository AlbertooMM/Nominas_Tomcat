package Laboral.Servicio;

import Laboral.Dao.ConexionDb;
import Laboral.Modelo.EmpleadoModel;
import java.sql.*;

/**
 * Representa a un empleado.
 */
public class Empleado extends Persona {

    private int categoria;
    public int anyos;

    /**
     * Constructor para un empleado nuevo con categoría 1 y 0 años.
     *
     * @param nombre nombre
     * @param dni DNI
     * @param sexo sexo
     * @throws DatosNoCorrectosException si los datos no son válidos
     */
    public Empleado(String nombre, String dni, char sexo)
            throws DatosNoCorrectosException {

        super(nombre, dni, sexo);

        this.categoria = 1;
        this.anyos = 0;
    }

    /**
     * Constructor completo.
     *
     * @param nombre nombre
     * @param dni DNI
     * @param sexo sexo
     * @param categoria categoría
     * @param anyos años trabajados
     * @throws DatosNoCorrectosException si los datos no son válidos
     */
    public Empleado(String nombre, String dni, char sexo,
                    int categoria, int anyos)
            throws DatosNoCorrectosException {

        super(nombre, dni, sexo);

        if (categoria < 1 || categoria > 10 || anyos < 0) {
            throw new DatosNoCorrectosException("Datos no correctos");
        }

        this.categoria = categoria;
        this.anyos = anyos;
    }

    /**
     * Cambia la categoría.
     *
     * @param nuevaCategoria nueva categoría
     */
    public void setCategoria(int nuevaCategoria) {

        if (nuevaCategoria >= 1 && nuevaCategoria <= 10) {
            categoria = nuevaCategoria;
        }
    }

    /**
     * Obtiene la categoría.
     *
     * @return categoría
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * Incrementa un año trabajado.
     */
    public void incrAnyo() {
        anyos++;
    }

    /**
     * Imprime todos los datos del empleado.
     */
    @Override
    public void imprime() {

        super.imprime();

        System.out.println("Categoría: " + categoria);
        System.out.println("Años trabajados: " + anyos);
    }
    public boolean actualizar(EmpleadoModel empleado) {
        String sql = """
                UPDATE Empleados
                SET nombre = ?, sexo = ?, categoria = ?, anyos = ?
                WHERE dni = ?
                """;

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