package Laboral.Servicio;

/**
 * Representa una persona.
 */
public class Persona {

    public String nombre;
    public String dni;
    public char sexo;

    /**
     * Constructor completo.
     *
     * @param nombre nombre
     * @param dni DNI
     * @param sexo sexo
     * @throws DatosNoCorrectosException si los datos no son válidos
     */
    public Persona(String nombre, String dni, char sexo)
            throws DatosNoCorrectosException {

        if (nombre == null || nombre.length() <= 2
                || dni == null || dni.length() != 9
                || (sexo != 'm' && sexo != 'f'
                && sexo != 'M' && sexo != 'F')) {

            throw new DatosNoCorrectosException("Datos no correctos");
        }

        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    /**
     * Constructor sin DNI.
     *
     * @param nombre nombre
     * @param sexo sexo
     * @throws DatosNoCorrectosException si los datos no son válidos
     */
    public Persona(String nombre, char sexo)
            throws DatosNoCorrectosException {

        if (nombre == null || nombre.length() <= 2
                || (sexo != 'm' && sexo != 'f'
                && sexo != 'M' && sexo != 'F')) {

            throw new DatosNoCorrectosException("Datos no correctos");
        }

        this.nombre = nombre;
        this.sexo = sexo;
    }

    /**
     * Modifica el DNI.
     *
     * @param dni nuevo DNI
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Imprime los datos de la persona.
     */
    public void imprime() {
        System.out.println(
                "Nombre: " + nombre
                        + ", DNI: " + dni
                        + ", Sexo: " + sexo
        );
    }
}