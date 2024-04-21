package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PrestamoTesisRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearPrestamoTesis(String idPrestamo, String idTesis) {
        String query = String.format("INSERT INTO prestamotesis (id_prestamo, id_tesis) VALUES ('%s', '%s')",
                idPrestamo,
                idTesis);
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static HashMap<String, String> consultarPrestamosTesis() {
        String query = "SELECT * FROM prestamotesis";
        IngresoQuery.ejecutarConsultaQuery(query);
        ResultSet resultSet = mySqlOperation.getResulset();

        HashMap<String, String> prestamosTesis = new HashMap<>();

        try {
            while (resultSet.next()) {
                String idPrestamo = resultSet.getString("id_prestamo");
                String idTesis = resultSet.getString("id_tesis");

                prestamosTesis.put(idPrestamo, idTesis);
            }
            return prestamosTesis;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
