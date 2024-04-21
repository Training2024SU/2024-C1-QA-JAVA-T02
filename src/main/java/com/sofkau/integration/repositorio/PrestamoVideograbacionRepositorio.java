package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PrestamoVideograbacionRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearPrestamoVideoGrabacion(String idPrestamo, String idVideoGrabacion) {
        String query = String.format("INSERT INTO prestamovideograbacion (id_prestamo, id_videograbacion) VALUES ('%s', '%s')",
                idPrestamo,
                idVideoGrabacion);
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static HashMap<String, String> consultarPrestamosVideoGrabacion() {
        String query = "SELECT * FROM prestamovideograbacion";
        IngresoQuery.ejecutarConsultaQuery(query);
        ResultSet resultSet = mySqlOperation.getResulset();

        HashMap<String, String> prestamosVideoGrabacion = new HashMap<>();

        try {
            while (resultSet.next()) {
                String idPrestamo = resultSet.getString("id_prestamo");
                String idVideoGrabacion = resultSet.getString("id_videograbacion");

                prestamosVideoGrabacion.put(idPrestamo, idVideoGrabacion);
            }
            return prestamosVideoGrabacion;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
