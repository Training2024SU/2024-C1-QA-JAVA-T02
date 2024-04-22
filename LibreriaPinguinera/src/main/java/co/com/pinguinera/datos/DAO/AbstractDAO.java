package co.com.pinguinera.datos.DAO;

import co.com.pinguinera.datos.interfaces.DAO;
import co.com.pinguinera.datos.interfaces.GestorBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {

    protected GestorBD gestorBD;
    private static final String OBTENER_TODAS = "SELECT * FROM Publicacion";

    public AbstractDAO(GestorBD gestorBD) {
        this.gestorBD = gestorBD;
    }

    @Override
    public List<T> obtenerTodos() throws SQLException {
        String consulta = obtenerConsultaTodos();
        try (PreparedStatement statement = prepararConsulta(consulta)) {
            ResultSet resultSet = statement.executeQuery();
            List<T> resultados = new ArrayList<>();

            // Llamada a un método abstracto para convertir cada fila a un objeto T
            while (resultSet.next()) {
                T objeto = convertirFilaAObjeto(resultSet);
                resultados.add(objeto);
            }

            return resultados;
        }
    }

    public List<T> obtenerTodosExport() throws SQLException {
        String consulta = OBTENER_TODAS;
        try (PreparedStatement statement = prepararConsulta(consulta)) {
            ResultSet resultSet = statement.executeQuery();
            List<T> resultados = new ArrayList<>();

            // Llamada a un método abstracto para convertir cada fila a un objeto T
            while (resultSet.next()) {
                T objeto = convertirFilaAObjeto(resultSet);
                resultados.add(objeto);
            }

            return resultados;
        }
    }


    // Método abstracto para obtener la consulta específica de la tabla
    protected abstract String obtenerConsultaTodos();

    // Método abstracto para convertir una fila de ResultSet a un objeto T
    protected abstract T convertirFilaAObjeto(ResultSet resultSet) throws SQLException;

    // Método auxiliar para preparar la consulta
    protected PreparedStatement prepararConsulta(String consulta) throws SQLException {
        return gestorBD.prepararConsulta(consulta);
    }
}
