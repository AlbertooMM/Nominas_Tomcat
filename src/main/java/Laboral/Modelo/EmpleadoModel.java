package Laboral.Modelo;


/**
 * Representa a un empleado de la empresa.
 */
public class EmpleadoModel {

    private String nombre;
    private String dni;
    private char sexo;
    private int categoria;
    private int anyos;

    /**
     * Constructor completo.
     *
     * @param nombre nombre del empleado
     * @param dni DNI del empleado
     * @param sexo sexo del empleado
     * @param categoria categoría del empleado
     * @param anyos años trabajados
     */
    public EmpleadoModel(String nombre, String dni, char sexo, int categoria, int anyos) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyos = anyos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getAnyos() {
        return anyos;
    }

    public void setAnyos(int anyos) {
        this.anyos = anyos;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo=" + sexo +
                ", categoria=" + categoria +
                ", anyos=" + anyos +
                '}';
    }
}