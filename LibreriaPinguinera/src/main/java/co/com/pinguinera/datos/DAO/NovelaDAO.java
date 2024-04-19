package co.com.pinguinera.datos.DAO;

import co.com.pinguinera.datos.interfaces.GestorBD;
import co.com.pinguinera.datos.model.Publicacion;
import co.com.pinguinera.datos.model.publicaciones.Novela;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NovelaDAO extends AbstractDAO<Novela> {

    private static final String CONSULTA_NOVELAS = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'NOVELA'";
    private static final String INSERTAR_NOVELA = "INSERT INTO Publicacion (idPublicacion, Titulo, tipo_publicacion, Autor, Num_paginas, Cant_ejemplares, Cant_prestados) VALUES (?, ?, 'NOVELA', ?, ?, ?, ?)";
    private static final String ACTUALIZAR_NOVELA = "UPDATE Publicacion SET Titulo = ?, Autor = ?, Num_paginas = ?, Cant_ejemplares = ?, Cant_prestados = ? WHERE idPublicacion = ?";
    private static final String ELIMINAR_NOVELA = "DELETE FROM Publicacion WHERE idPublicacion = ?";

    public NovelaDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        return CONSULTA_NOVELAS;
    }

    @Override
    protected Novela convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        Novela novela = new Novela();
        novela.setIdPublicacion(resultSet.getInt("idPublicacion"));
        novela.setTitulo(resultSet.getString("Titulo"));
        novela.setAutor(resultSet.getString("Autor"));
        novela.setFormato(resultSet.getString("Formato"));
        novela.setCantEjemplares(resultSet.getInt("Cant_ejemplares"));
        novela.setCantPrestados(resultSet.getInt("Cant_prestados"));

        // `cant_disponible` es un campo calculado, se ajusta automáticamente en la base de datos
        // pero lo tomamos del resultado para tener la información actualizada
        novela.setCantDisponible(resultSet.getInt("Cant_disponible"));

        return novela;
    }

    // Implementación de los métodos LocalCRUD

    // Método para insertar una nueva novela en la base de datos
    @Override
    public void insertar(Novela novela) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_NOVELA)) {
            statement.setInt(1, novela.getIdPublicacion());
            statement.setString(2, novela.getTitulo());
            statement.setString(3, novela.getAutor());
            statement.setString(4, novela.getFormato());
            statement.setInt(5, novela.getCantEjemplares());
            statement.setInt(6, novela.getCantPrestados());
            statement.executeUpdate();
        }
    }

    // Método para actualizar una novela existente en la base de datos
    @Override
    public void actualizar(Novela novela) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_NOVELA)) {
            statement.setString(1, novela.getTitulo());
            statement.setString(2, novela.getAutor());
            statement.setString(3, novela.getFormato());
            statement.setInt(4, novela.getCantEjemplares());
            statement.setInt(5, novela.getCantPrestados());
            statement.setInt(6, novela.getIdPublicacion());
            statement.executeUpdate();
        }
    }

    // Método para eliminar una novela de la base de datos
    @Override
    public void eliminar(Novela novela) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_NOVELA)) {
            statement.setInt(1, novela.getIdPublicacion()); // Usa el ID de la novela para la eliminación
            statement.executeUpdate();
        }
    }


}
