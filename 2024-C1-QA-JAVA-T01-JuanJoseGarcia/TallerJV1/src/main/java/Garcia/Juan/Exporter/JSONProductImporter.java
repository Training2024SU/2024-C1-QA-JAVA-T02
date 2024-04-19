package Garcia.Juan.Exporter;

import Garcia.Juan.CRUD.CRUDImpoExpo;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONProductImporter {

    // Ruta del archivo JSON a importar
    private static final String JSON_FILE_PATH = "/home/dan/Desktop/productos.json";

    private Integer edadMinima;

    // Método para importar datos desde un archivo JSON e insertarlos en la base de datos
    public static List<Producto> importProductsFromJSON(MySqlOperation mySqlOperation) {
        // Crear un objeto ObjectMapper para manejar la lectura de JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Leer el archivo JSON y convertirlo a una lista de productos
            List<Producto> productos = objectMapper.readValue(new File(JSON_FILE_PATH), objectMapper.getTypeFactory().constructCollectionType(List.class, Producto.class));

            // Insertar la lista de productos en la base de datos
            CRUDImpoExpo.insertProducts(mySqlOperation, productos);
            System.out.println("Productos importados exitosamente desde el archivo JSON.");

            // Retornar la lista de productos importados
            return productos;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            return null;
        }
    }
}
