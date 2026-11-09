package Laboral.Servicio;

import Laboral.Modelo.EmpleadoModel;

public class Nomina {
    private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000,
            150000, 170000, 190000, 210000, 230000 };

    /**
     * @param em1
     * @return int
     */
    public int sueldo(EmpleadoModel em1) {

        int sueldoBase = SUELDO_BASE[em1.getCategoria() - 1];
        int sueldo = sueldoBase + 5000 * em1.getAnyos();
        return sueldo;
    }
}
