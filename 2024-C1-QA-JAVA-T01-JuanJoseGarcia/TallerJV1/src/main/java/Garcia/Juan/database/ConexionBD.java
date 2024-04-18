package Garcia.Juan.database;

import Garcia.Juan.database.mysql.MySqlOperation;
import java.sql.SQLException;

public class ConexionBD {

    private static final String SERVER = "localhost";
    private static final String DATA_BASE_NAME = "bibliotecapingu";
    private static final String USER = "root";

    // Escapa la contraseña usando URLEncoder.encode en la declaración de la constante
    private static final String PASSWORD;

    static {
        try {
            PASSWORD = java.net.URLEncoder.encode("#32zvv48dH", "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            // Maneja la excepción si ocurre un error al codificar la contraseña
            throw new RuntimeException("Error al codificar la contraseña en UTF-8", e);
        }
    }


    private static MySqlOperation mySqlOperation;

    public ConexionBD(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public static void openConnection(MySqlOperation mySqlOperation) {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
    }

    public static void closeConnection(MySqlOperation mySqlOperation) {
        mySqlOperation.close();
    }




}
