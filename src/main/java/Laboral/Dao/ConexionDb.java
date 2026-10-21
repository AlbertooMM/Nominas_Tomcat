package Laboral.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDb {
    /** URL de conexión a la base de datos MariaDB */
    private static final String URL = "jdbc:mariadb://localhost:3306/GestionNomina";
    /** Usuario de la base de datos */
    private static final String USER = "root";
    /** Contraseña de la base de datos */
    private static final String PASS = "123456"; // Cambiar por tu contraseña

    public static Connection getConexion() {

        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos");
            return null;
        }

    }
}
