package Laboral.Modelo;

/**
 * Representa la nómina de un empleado.
 */
public class NominaModel {

    private String dni;
    private int sueldo;


    /**
     * Constructor de la nómina.
     *
     * @param dni DNI del empleado
     * @param sueldo sueldo calculado
     */

    public NominaModel(String dni, int sueldo) {
        this.dni = dni;
        this.sueldo = sueldo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Nominas{" +
                "dni='" + dni + '\'' +
                ", sueldo=" + sueldo +
                '}';
    }
}