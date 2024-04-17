package co.com.pinguinera.datos.conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Nombres de las variables de entorno
    private static final String URL_ENV = "jdbc:mysql://localhost:3306/pinguinera";
    private static final String USUARIO_ENV = "root";
    private static final String CONTRASENA_ENV = "Jjga1910*";

    // Variable para la conexión
    private static Connection conexion;

    // Constructor privado para evitar instanciación externa
    private ConexionBD() {}

    // Método estático para obtener la conexión única a la base de datos
    public static Connection conectar() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            String url = URL_ENV;
            String usuario = USUARIO_ENV;
            String contrasena = CONTRASENA_ENV;

            // Establecemos la conexión con los valores obtenidos de las variables de entorno
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        }
        return conexion;
    }

}
