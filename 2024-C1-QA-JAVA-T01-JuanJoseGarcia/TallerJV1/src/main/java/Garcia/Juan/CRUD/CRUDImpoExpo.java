package Garcia.Juan.CRUD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clase que realiza operaciones CRUD para suministrar datos a las clases de importaciones
 * y exportaciones especializadas en los productos del inventario de la librería.
 */
public class CRUDImpoExpo {
    // Consulta SQL para obtener todos los productos de la tabla 'producto'
    private static final String GET_PRODUCTS = "SELECT titulo, tipo, autor, magnitud, cant_ejemplares, cant_prestados, cant_disponibles FROM bibliotecapingu.producto";

    // Consulta SQL para insertar un nuevo producto en la tabla 'producto'
    private static final String INSERT_PRODUCT = "INSERT INTO producto (titulo, tipo, autor, magnitud, cant_ejemplares, cant_prestados) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * Obtiene todos los productos de la tabla de la base de datos y los devuelve como una lista.
     *
     * @param mySqlOperation Instancia de MySqlOperation para ejecutar la consulta.
     * @return Lista de objetos Producto obtenidos de la base de datos.
     * @throws SQLException Si ocurre un error durante la ejecución de la consulta.
     */
    public static List<Producto> getProductsFromTable(MySqlOperation mySqlOperation) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try {
            // Configura la consulta SQL para seleccionar todos los campos de la tabla 'producto'
            mySqlOperation.setSqlStatement(GET_PRODUCTS);
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();

            // Itera sobre los resultados de la consulta para crear objetos Producto
            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setTitulo(resultSet.getString("titulo"));
                producto.setTipo(resultSet.getString("tipo"));
                producto.setAutor(resultSet.getString("autor"));
                producto.setMagnitud(resultSet.getString("magnitud"));
                producto.setCantidadEjemplares(resultSet.getInt("cant_ejemplares"));
                producto.setCantidadPrestados(resultSet.getInt("cant_prestados"));
                producto.setCantidadDisponibles(resultSet.getInt("cant_disponibles"));

                // Agrega el objeto Producto a la lista de productos
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }

    /**
     * Inserta una lista de productos en la base de datos.
     *
     * @param mySqlOperation Instancia de MySqlOperation para ejecutar la inserción.
     * @param productos Lista de productos a insertar.
     */
    public static void insertProducts(MySqlOperation mySqlOperation, List<Producto> productos) {
        Set<String> existingProducts = new HashSet<>();

        try (PreparedStatement checkDuplicateStmt = mySqlOperation.prepareStatement("SELECT titulo FROM producto WHERE titulo = ?");
             PreparedStatement insertStmt = mySqlOperation.prepareStatement(INSERT_PRODUCT)) {

            for (Producto producto : productos) {
                checkDuplicateStmt.setString(1, producto.getTitulo());
                try (ResultSet rs = checkDuplicateStmt.executeQuery()) {
                    if (rs.next()) {
                        existingProducts.add(producto.getTitulo());
                    }
                }
            }

            for (Producto producto : productos) {
                if (!existingProducts.contains(producto.getTitulo())) {
                    insertStmt.setString(1, producto.getTitulo());
                    insertStmt.setString(2, producto.getTipo());
                    insertStmt.setString(3, producto.getAutor());
                    insertStmt.setString(4, producto.getMagnitud());
                    insertStmt.setInt(5, producto.getCantidadEjemplares());
                    insertStmt.setInt(6, producto.getCantidadPrestados());
                    insertStmt.addBatch();
                }
            }

            insertStmt.executeBatch();
        } catch (SQLException e) {
            System.err.println("Error al insertar productos: " + e.getMessage());
        } finally {
            mySqlOperation.close();
        }
    }

}
