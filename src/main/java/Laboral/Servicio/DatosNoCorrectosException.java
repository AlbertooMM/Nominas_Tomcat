package Laboral.Servicio;

public class DatosNoCorrectosException extends RuntimeException {
    private String Message;

    public DatosNoCorrectosException(String mensaje) {
        Message = mensaje;
    }

    /**
     * @return String
     */
    public String getMessage() {
        return Message;

    }
}