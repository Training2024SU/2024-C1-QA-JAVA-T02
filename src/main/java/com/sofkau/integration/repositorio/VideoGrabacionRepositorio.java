package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.VideoGrabacion;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VideoGrabacionRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearVideoGrabacion(VideoGrabacion videoGrabacion) {
        String query = String.format("INSERT INTO videograbacion (titulo, sinopsis, genero, autor, calificacion, tipo, cantidad_copia, cantidad_prestado) VALUES ('%s', '%s', '%s', '%s', %f, '%s', %d, %d)",
                videoGrabacion.getTitulo(), videoGrabacion.getSinopsis(), videoGrabacion.getGenero(), videoGrabacion.getAutor(), videoGrabacion.getCalificacion(), videoGrabacion.getTipo(), videoGrabacion.getCantidadCopia(), videoGrabacion.getCantidadPrestado());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static ArrayList<VideoGrabacion> consultarVideoGrabaciones() {
        String query = "SELECT * FROM videograbacion";
        IngresoQuery.ejecutarConsultaQuery(query);
        ArrayList<VideoGrabacion> videoGrabaciones = new ArrayList<>();
        ResultSet resultSet = mySqlOperation.getResulset();

        try {
            while (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                String sinopsis = resultSet.getString("sinopsis");
                String genero = resultSet.getString("genero");
                String autor = resultSet.getString("autor");
                float calificacion = resultSet.getFloat("calificacion");
                String tipo = resultSet.getString("tipo");
                int cantidadCopia = resultSet.getInt("cantidad_copia");
                int cantidadPrestado = resultSet.getInt("cantidad_prestado");

                VideoGrabacion videoGrabacion = new VideoGrabacion();
                videoGrabacion.setTitulo(titulo);
                videoGrabacion.setSinopsis(sinopsis);
                videoGrabacion.setGenero(genero);
                videoGrabacion.setAutor(autor);
                videoGrabacion.setCalificacion(calificacion);
                videoGrabacion.setTipo(tipo);
                videoGrabacion.setCantidadCopia(cantidadCopia);
                videoGrabacion.setCantidadPrestado(cantidadPrestado);

                videoGrabaciones.add(videoGrabacion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return videoGrabaciones;
    }

    public static void actualizarVideoGrabacion(VideoGrabacion videoGrabacion) {
        String query = String.format("UPDATE videograbacion SET sinopsis = '%s', genero = '%s', autor = '%s', calificacion = %f, tipo = '%s', cantidad_copia = %d, cantidad_prestado = %d WHERE titulo = '%s'",
                videoGrabacion.getSinopsis(), videoGrabacion.getGenero(), videoGrabacion.getAutor(), videoGrabacion.getCalificacion(), videoGrabacion.getTipo(), videoGrabacion.getCantidadCopia(), videoGrabacion.getCantidadPrestado(), videoGrabacion.getTitulo());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

}
