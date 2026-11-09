
package Laboral.BBDD.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDb {

    private static final String URL =
            "jdbc:mariadb://localhost:3306/gestionnomina";

    private static final String USER = "root";
    private static final String PASS = "123456";


    public static Connection getConexion() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "No se encuentra el controlador JDBC de MariaDB. "
                            + "Comprueba la dependencia Maven y el despliegue.",
                    e
            );
        }

        return DriverManager.getConnection(URL, USER, PASS);
    }

}

