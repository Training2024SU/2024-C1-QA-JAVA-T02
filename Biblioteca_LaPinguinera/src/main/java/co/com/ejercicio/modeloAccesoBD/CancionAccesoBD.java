package co.com.ejercicio.modeloAccesoBD;

import co.com.ejercicio.modelo.Cancion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.ejercicio.conexionBd.MapeoBD.MapearTablasBD.mapearResultSetACancion;

import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.*;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_EXITOSA;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;

public class CancionAccesoBD {
    private static Connection conexion;

    public CancionAccesoBD(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarCancion(Cancion cancion) throws SQLException {
        try {
            PreparedStatement statement = conexion.prepareStatement(INSERT_CANCION);
            statement.setString(1, cancion.getTitulo());
            statement.setString(2, cancion.getArtista());
            statement.setString(3, cancion.getAlbum());
            statement.setString(4, cancion.getDuracion());
            statement.setInt(5, cancion.getCantidadEjemplares());
            statement.setInt(6, cancion.getCantidadPrestado());
            statement.setInt(7, cancion.getCantidadDisponible());
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public List<Cancion> obtenerTodasLasCanciones() throws SQLException {
        List<Cancion> canciones = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_FROM_CANCION);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                canciones.add(mapearResultSetACancion(resultSet));
            }
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
        return canciones;
    }

    public static List<Cancion> obtenerCancionDeUnArtista(String artista) throws SQLException {
        List<Cancion> canciones = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_ARTISTA_FROM_CANCION)) {
            statement.setString(1, artista);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Cancion cancion = new Cancion(
                            resultSet.getString("titulo"),
                            resultSet.getString("artista"),
                            resultSet.getString("album"),
                            resultSet.getString("duracion"),
                            resultSet.getInt("cantidad_ejemplares"),
                            resultSet.getInt("cantidad_prestado"),
                            resultSet.getInt("cantidad_disponible")
                    );
                    canciones.add(cancion);
                }
            }
        }

        return canciones;
    }

    public void actualizarCancion(Cancion cancion, String tituloPrevio) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(UPDATE_CANCION)) {
            statement.setString(1, cancion.getTitulo());
            statement.setString(2, cancion.getArtista());
            statement.setString(3, cancion.getAlbum());
            statement.setString(4, cancion.getDuracion());
            statement.setInt(5, cancion.getCantidadEjemplares());
            statement.setInt(6, cancion.getCantidadPrestado());
            statement.setInt(7, cancion.getCantidadDisponible());
            statement.setString(8, tituloPrevio);


            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }

    public void eliminarCancion(String titulo) throws SQLException, RuntimeException {
        try (PreparedStatement statement = conexion.prepareStatement(DELETE_FROM_CANCION)) {
            statement.setString(1, titulo);
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }
}
