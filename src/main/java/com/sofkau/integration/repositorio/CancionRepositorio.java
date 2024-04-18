package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.Cancion;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CancionRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    private static SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public static void crearCancion(Cancion cancion) {
        String query = String.format("INSERT INTO cancion (titulo, genero, autor, letra, fecha_lanzamiento, cantidad_copia, cantidad_prestado) VALUES ('%s', '%s', '%s', '%s', '%s', %d, %d)",
                cancion.getTitulo(), cancion.getGenero(), cancion.getAutor(), cancion.getLetra(), formato.format(cancion.getFechaLanzamiento()), cancion.getCantidadCopia(), cancion.getCantidadPrestado());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static ArrayList<Cancion> consultarCanciones() {
        String query = "SELECT * FROM cancion";
        IngresoQuery.ejecutarConsultaQuery(query);
        ArrayList<Cancion> canciones = new ArrayList<>();
        ResultSet resultSet = mySqlOperation.getResulset();

        try {
            while (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String genero = resultSet.getString("genero");
                String autor = resultSet.getString("autor");
                String letra = resultSet.getString("letra");
                Date fechaLanzamiento = resultSet.getDate("fecha_lanzamiento");
                int cantidadCopia = resultSet.getInt("cantidad_copia");
                int cantidadPrestado = resultSet.getInt("cantidad_prestado");

                Cancion cancion = new Cancion();
                cancion.setTitulo(titulo);
                cancion.setGenero(genero);
                cancion.setAutor(autor);
                cancion.setLetra(letra);
                cancion.setFechaLanzamiento(fechaLanzamiento);
                cancion.setCantidadCopia(cantidadCopia);
                cancion.setCantidadPrestado(cantidadPrestado);

                canciones.add(cancion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return canciones;
    }

    public static void actualizarCancion(Cancion cancion) {
        String query = String.format("UPDATE cancion SET genero = '%s', autor = '%s', letra = '%s', fecha_lanzamiento = '%s', cantidad_copia = %d, cantidad_prestado = %d WHERE titulo = '%s'",
                cancion.getGenero(), cancion.getAutor(), cancion.getLetra(), formato.format(cancion.getFechaLanzamiento()),
                cancion.getCantidadCopia(), cancion.getCantidadPrestado(), cancion.getTitulo());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

}
