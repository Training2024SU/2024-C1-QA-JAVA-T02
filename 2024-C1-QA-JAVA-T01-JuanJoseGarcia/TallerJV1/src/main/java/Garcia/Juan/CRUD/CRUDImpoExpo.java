package Garcia.Juan.CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class CRUDImpoExpo {
    private static final String GET_PRODUCTS = "SELECT titulo, tipo, autor, magnitud, cant_ejemplares, cant_prestados, cant_disponibles FROM bibliotecapingu.producto";

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
}
