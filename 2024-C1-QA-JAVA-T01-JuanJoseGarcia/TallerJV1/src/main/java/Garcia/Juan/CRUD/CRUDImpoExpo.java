package Garcia.Juan.CRUD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class CRUDImpoExpo {
    private static final String GET_PRODUCTS = "SELECT titulo, tipo, autor, magnitud, cant_ejemplares, cant_prestados, cant_disponibles FROM bibliotecapingu.producto";
    private static final String INSERT_PRODUCT = "INSERT INTO producto (titulo, tipo, autor, magnitud, cant_ejemplares, cant_prestados) VALUES (?, ?, ?, ?, ?, ?)";

    public static List<Producto> getProductsFromTable(MySqlOperation mySqlOperation) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try {
            // Consulta SQL para seleccionar todos los campos de la tabla 'producto'
            mySqlOperation.setSqlStatement(GET_PRODUCTS);
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();

            // Iterar sobre los resultados y crear objetos Producto
            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setTitulo(resultSet.getString("titulo"));
                producto.setTipo(resultSet.getString("tipo"));
                producto.setAutor(resultSet.getString("autor"));
                producto.setMagnitud(resultSet.getString("magnitud"));
                producto.setCantidadEjemplares(resultSet.getInt("cant_ejemplares"));
                producto.setCantidadPrestados(resultSet.getInt("cant_prestados"));
                producto.setCantidadDisponibles(resultSet.getInt("cant_disponibles"));

                // Agregar el objeto Producto a la lista de productos
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }

    // Método para insertar una lista de productos en la base de datos
    public static void insertProducts(MySqlOperation mySqlOperation, List<Producto> productos) {
        // Declaración preparada
        PreparedStatement preparedStatement = null;
        try {
            // Preparar la declaración SQL
            preparedStatement = mySqlOperation.prepareStatement(INSERT_PRODUCT);

            // Iterar sobre la lista de productos
            for (Producto producto : productos) {
                // Establecer los parámetros de la declaración preparada
                preparedStatement.setString(1, producto.getTitulo());
                preparedStatement.setString(2, producto.getTipo());
                preparedStatement.setString(3, producto.getAutor());
                preparedStatement.setString(4, producto.getMagnitud());
                preparedStatement.setInt(5, producto.getCantidadEjemplares());
                preparedStatement.setInt(6, producto.getCantidadPrestados());

                // Ejecutar la instrucción preparada
                preparedStatement.addBatch();
            }

            // Ejecutar el lote de declaraciones
            preparedStatement.executeBatch();

            System.out.println("Productos insertados exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar productos: " + e.getMessage());
        } finally {
            // Cerrar la declaración preparada y otros recursos
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar PreparedStatement: " + e.getMessage());
                }
            }
            // Cerrar la conexión a la base de datos
            mySqlOperation.close();
        }
    }
}



