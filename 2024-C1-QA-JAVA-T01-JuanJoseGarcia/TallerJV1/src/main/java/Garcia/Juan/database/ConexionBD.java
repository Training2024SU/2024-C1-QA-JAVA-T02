package Garcia.Juan.database;

import Garcia.Juan.database.mysql.MySqlOperation;

public class ConexionBD {

    private static final String SERVER = "localhost";
    private static final String DATA_BASE_NAME = "bibliotecapingu";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

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
