package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class PrestamoCancionRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearPrestamoCancion(String idPrestamo, String idCancion) {
        String query = String.format("INSERT INTO prestamocancion (id_prestamo, id_cancion) VALUES ('%s', '%s')",
                idPrestamo,
                idCancion);
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static HashMap<String, String> consultarPrestamosCancion() {
        String query = "SELECT * FROM prestamocancion";
        IngresoQuery.ejecutarConsultaQuery(query);
        ResultSet resultSet = mySqlOperation.getResulset();

        HashMap<String, String> prestamosCancion = new HashMap<>();

        try {
            while (resultSet.next()) {
                String idPrestamo = resultSet.getString("id_prestamo");
                String idCancion = resultSet.getString("id_cancion");

                prestamosCancion.put(idPrestamo, idCancion);
            }
            return prestamosCancion;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
