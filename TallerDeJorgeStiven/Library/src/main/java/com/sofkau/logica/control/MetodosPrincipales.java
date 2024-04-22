package com.sofkau.logica.control;

import static com.sofkau.Main.mySqlOperation;
import static com.sofkau.dialogo.ConstantesMenu.MSJ_SEGURO_SALIR;
import static com.sofkau.dialogo.CustomLogger.info;
import static com.sofkau.integration.database.mysql.Constantes.*;
import static com.sofkau.integration.database.mysql.Constantes.PASSWORD;
import static com.sofkau.util.PedirPorConsola.pedirString;

public class MetodosPrincipales {
    public static String preguntarSalir() throws NullPointerException {
        info(MSJ_SEGURO_SALIR);
        return pedirString();
    }

    public static void insertIntoDb(String insert) {
        mySqlOperation.setSqlStatement(insert);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void openConnection() {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
    }

    public static void closeConnection() {
        mySqlOperation.close();
    }
}
