package co.com.pinguinera.datos.DAO;

import co.com.pinguinera.datos.interfaces.GestorBD;
import co.com.pinguinera.datos.model.publicaciones.Libro;
import co.com.pinguinera.datos.model.publicaciones.Videograbaciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoDAO extends AbstractDAO<Videograbaciones>{

    private static final String CONSULTA_VIDEOS = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'LIBRO'";

    private static final String CONSULTA_LIBROS = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'LIBRO'";
    private static final String INSERTAR_LIBRO = "INSERT INTO Publicacion (idPublicacion, Titulo, tipo_publicacion, Autor, Num_paginas, Cant_ejemplares, Cant_prestados) VALUES (?, ?, 'LIBRO', ?, ?, ?, ?)";
    private static final String ACTUALIZAR_LIBRO = "UPDATE Publicacion SET Titulo = ?, Autor = ?, Num_paginas = ?, Cant_ejemplares = ?, Cant_prestados = ? WHERE idPublicacion = ?";
    private static final String ELIMINAR_LIBRO = "DELETE FROM Publicacion WHERE idPublicacion = ?";

    public VideoDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        return CONSULTA_VIDEOS;
    }

    protected Videograbaciones convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        Videograbaciones video = new Videograbaciones();
        video.setIdPublicacion(resultSet.getInt("idPublicacion"));
        video.setTitulo(resultSet.getString("Titulo"));
        video.setAutor(resultSet.getString("Autor"));
        video.setFormato(resultSet.getString("Formato"));
        video.setCantEjemplares(resultSet.getInt("Cant_ejemplares"));
        video.setCantPrestados(resultSet.getInt("Cant_prestados"));
        video.setCantDisponible(resultSet.getInt("Cant_disponible"));

        return video;
    }

    @Override
    public void insertar(Videograbaciones video) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(CONSULTA_VIDEOS)) {
            statement.setInt(1, video.getIdPublicacion());
            statement.setString(2, video.getTitulo());
            statement.setString(3, video.getAutor());
            statement.setString(4, video.getFormato());
            statement.setInt(5, video.getCantEjemplares());
            statement.setInt(6, video.getCantPrestados());
            statement.executeUpdate();
        }
    }

    @Override
    public void actualizar(Videograbaciones video) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_LIBRO)) {
            statement.setString(1, video.getTitulo());
            statement.setString(2, video.getAutor());
            statement.setString(3, video.getFormato());
            statement.setInt(4, video.getCantEjemplares());
            statement.setInt(5, video.getCantPrestados());
            statement.setInt(6, video.getIdPublicacion());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Videograbaciones video) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_LIBRO)) {
            statement.setInt(1, video.getIdPublicacion());
            statement.executeUpdate();
        }
    }
}
