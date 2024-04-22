package co.com.pinguinera.datos.DAO;

import co.com.pinguinera.datos.interfaces.GestorBD;
import co.com.pinguinera.datos.model.publicaciones.Ensayos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnsayoDAO extends AbstractDAO<Ensayos>{

    private static final String CONSULTA_ENSAYOS = "SELECT * FROM Publicacion WHERE tipo_publicacion = 'ENSAYO  '";
    private static final String INSERTAR_ENSAYO = "INSERT INTO Publicacion (idPublicacion, Titulo, tipo_publicacion, Autor, Formato, Cant_ejemplares, Cant_prestados) VALUES (?, ?, 'ENSAYO', ?, ?, ?, ?)";
    private static final String ACTUALIZAR_ENSAYO = "UPDATE Publicacion SET Titulo = ?, Autor = ?, Formato = ?, Cant_ejemplares = ?, Cant_prestados = ? WHERE idPublicacion = ?";
    private static final String ELIMINAR_ENSAYO = "DELETE FROM Publicacion WHERE idPublicacion = ?";
    private static final String OBTENER_TODAS = "SELECT * FROM Publicacion";

    public EnsayoDAO(GestorBD gestorBD) {
        super(gestorBD);
    }

    @Override
    protected String obtenerConsultaTodos() {
        return CONSULTA_ENSAYOS;
    }

    @Override
    protected Ensayos convertirFilaAObjeto(ResultSet resultSet) throws SQLException {
        Ensayos ensayo = new Ensayos();
        ensayo.setIdPublicacion(resultSet.getInt("idPublicacion"));
        ensayo.setTitulo(resultSet.getString("Titulo"));
        ensayo.setAutor(resultSet.getString("Autor"));
        ensayo.setFormato(resultSet.getString("Formato"));
        ensayo.setCantEjemplares(resultSet.getInt("Cant_ejemplares"));
        ensayo.setCantPrestados(resultSet.getInt("Cant_prestados"));
        ensayo.setCantDisponible(resultSet.getInt("Cant_disponible"));

        return ensayo;
    }

    @Override
    public void insertar(Ensayos ensayo) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(INSERTAR_ENSAYO)) {
            statement.setInt(1, ensayo.getIdPublicacion());
            statement.setString(2, ensayo.getTitulo());
            statement.setString(3, ensayo.getAutor());
            statement.setString(4, ensayo.getFormato());
            statement.setInt(5, ensayo.getCantEjemplares());
            statement.setInt(6, ensayo.getCantPrestados());
            statement.executeUpdate();
        }
    }

    @Override
    public void actualizar(Ensayos ensayo) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ACTUALIZAR_ENSAYO)) {
            statement.setString(1, ensayo.getTitulo());
            statement.setString(2, ensayo.getAutor());
            statement.setString(3, ensayo.getFormato());
            statement.setInt(4, ensayo.getCantEjemplares());
            statement.setInt(5, ensayo.getCantPrestados());
            statement.setInt(6, ensayo.getIdPublicacion());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminar(Ensayos ensayo) throws SQLException {
        try (PreparedStatement statement = prepararConsulta(ELIMINAR_ENSAYO)) {
            statement.setInt(1, ensayo.getIdPublicacion());
            statement.executeUpdate();
        }
    }


}
