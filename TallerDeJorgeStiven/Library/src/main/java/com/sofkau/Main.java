package com.sofkau;

import com.sofkau.integration.database.mysql.MySqlOperation;

import java.sql.SQLException;

import static com.sofkau.dialogo.ConstantesMenu.*;
import static com.sofkau.logica.control.MetodosRol.implementarLogica;
import static com.sofkau.logica.control.MetodosPrincipales.*;

public class Main {
    public static final MySqlOperation mySqlOperation = new MySqlOperation();
    static String salir;

    public static void main(String[] args) throws SQLException {
        openConnection();
        System.out.println(MSN_BIENVENIDOS);
        do {
            implementarLogica();
            salir = preguntarSalir();
        } while (!(salir.equals("s") || salir.equals("S")));
        closeConnection();
    }
}