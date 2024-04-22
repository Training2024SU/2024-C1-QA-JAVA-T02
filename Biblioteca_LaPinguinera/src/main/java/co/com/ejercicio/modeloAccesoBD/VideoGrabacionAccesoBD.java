package co.com.ejercicio.modeloAccesoBD;

import co.com.ejercicio.modelo.Videograbacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.ejercicio.conexionBd.MapeoBD.MapearTablasBD.mapearResultSetAVideoGrabacion;
import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.*;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_EXITOSA;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;

public class VideoGrabacionAccesoBD {
    private static Connection conexion;

    public VideoGrabacionAccesoBD(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarVideoGrabacion(Videograbacion videoGrabacion) throws SQLException {
        try {
            PreparedStatement statement = conexion.prepareStatement(INSERT_VIDEOGRABACION);
            statement.setString(1, videoGrabacion.getTitulo());
            statement.setString(2, videoGrabacion.getDirector());
            statement.setString(3, videoGrabacion.getDuracion());
            statement.setInt(4, videoGrabacion.getCantidadEjemplares());
            statement.setInt(5, videoGrabacion.getCantidadPrestado());
            statement.setInt(6, videoGrabacion.getCantidadDisponible());
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public List<Videograbacion> obtenerTodasLasVideoGrabaciones() throws SQLException {
        List<Videograbacion> videoGrabaciones = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_FROM_VIDEOGRABACION);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                videoGrabaciones.add(mapearResultSetAVideoGrabacion(resultSet));
            }
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
        return videoGrabaciones;
    }

    public static List<Videograbacion> obtenerVideoGrabacionesDeUnDirector(String director) throws SQLException {
        List<Videograbacion> videoGrabaciones = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_DIRECTOR_FROM_VIDEOGRABACION)) {
            statement.setString(1, director);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Videograbacion videoGrabacion = new Videograbacion(
                            resultSet.getString("titulo"),
                            resultSet.getString("director"),
                            resultSet.getString("duracion"),
                            resultSet.getInt("cantidad_ejemplares"),
                            resultSet.getInt("cantidad_prestado"),
                            resultSet.getInt("cantidad_disponible")
                    );
                    videoGrabaciones.add(videoGrabacion);
                }
            }
        }

        return videoGrabaciones;
    }

    public void actualizarVideoGrabacion(Videograbacion videoGrabacion, String tituloPrevio) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(UPDATE_VIDEOGRABACION)) {
            statement.setString(1, videoGrabacion.getTitulo());
            statement.setString(2, videoGrabacion.getDirector());
            statement.setString(3, videoGrabacion.getDuracion());
            statement.setInt(4, videoGrabacion.getCantidadEjemplares());
            statement.setInt(5, videoGrabacion.getCantidadPrestado());
            statement.setInt(6, videoGrabacion.getCantidadDisponible());
            statement.setString(7, tituloPrevio);


            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }

    public void eliminarVideoGrabacion(String titulo) throws SQLException, RuntimeException {
        try (PreparedStatement statement = conexion.prepareStatement(DELETE_FROM_VIDEOGRABACION)) {
            statement.setString(1, titulo);
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }
}
