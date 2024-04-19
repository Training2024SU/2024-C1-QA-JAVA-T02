package co.com.pinguinera.datos.DAO;

import co.com.pinguinera.datos.interfaces.GestorBD;
import co.com.pinguinera.datos.model.publicaciones.Cancion;
import co.com.pinguinera.datos.model.publicaciones.Videograbaciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CancionDAO extends AbstractDAO<Cancion>{
    private static final String CONSULTA_CANCIONES = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'CANCION'";
    private static final String INSERTAR_VIDEO = "INSERT INTO Publicacion (idPublicacion, Titulo, tipo_publicacion, Autor, Num_paginas, Cant_ejemplares, Cant_prestados) VALUES (?, ?, 'VIDEO', ?, ?, ?, ?)";
    private static final String ACTUALIZAR_CANCION = "UPDATE Publicacion SET Titulo = ?, Autor = ?, Formato = ?, Cant_ejemplares = ?, Cant_prestados = ? WHERE idPublicacion = ?";
    private static final String ELIMINAR_CANCION = "DELETE FROM Publicacion WHERE idPublicacion = ?";

    public CancionDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        return CONSULTA_CANCIONES;
    }

    @Override
    protected Cancion convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        Cancion cancion = new Cancion();
        cancion.setIdPublicacion(resultSet.getInt("idPublicacion"));
        cancion.setTitulo(resultSet.getString("Titulo"));
        cancion.setAutor(resultSet.getString("Autor"));
        cancion.setFormato(resultSet.getString("Formato"));
        cancion.setCantEjemplares(resultSet.getInt("Cant_ejemplares"));
        cancion.setCantPrestados(resultSet.getInt("Cant_prestados"));
        cancion.setCantDisponible(resultSet.getInt("Cant_disponible"));
        return cancion;
    }

    @Override
    public void insertar(Cancion cancion) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_VIDEO)) {
            statement.setInt(1, cancion.getIdPublicacion());
            statement.setString(2, cancion.getTitulo());
            statement.setString(3, cancion.getAutor());
            statement.setString(4, cancion.getFormato());
            statement.setInt(5, cancion.getCantEjemplares());
            statement.setInt(6, cancion.getCantPrestados());
            statement.executeUpdate();
        }
    }

    @Override
    public void actualizar(Cancion cancion) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_CANCION)) {
            statement.setInt(1, cancion.getIdPublicacion());
            statement.setString(2, cancion.getTitulo());
            statement.setString(3, cancion.getAutor());
            statement.setString(4, cancion.getFormato());
            statement.setInt(5, cancion.getCantEjemplares());
            statement.setInt(6, cancion.getCantPrestados());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Cancion cancion) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_CANCION)) {
            statement.setInt(1, cancion.getIdPublicacion());
            statement.executeUpdate();
        }
    }
}
