package co.com.sofka;

import co.com.sofka.DB.SqlOperation;

import static co.com.sofka.DB.constants.DatabaseConnectionConstants.*;

public class Main {
    private static final SqlOperation sqlOperation = new SqlOperation();

    public static void main(String[] args) {
        openConnection();
        sqlOperation.setSqlStatement("insert into author values ('AU01', 'Johan Smith')");
        sqlOperation.executeSqlStatementVoid();
        closeConnection();


    }

    public static void openConnection(){
        sqlOperation.setServer(SERVER);
        sqlOperation.setDataBaseName(DATA_BASE_NAME);
        sqlOperation.setUser(USER);
        sqlOperation.setPassword(PASSWORD);
    }

    public static void closeConnection(){
        sqlOperation.close();
    }

}