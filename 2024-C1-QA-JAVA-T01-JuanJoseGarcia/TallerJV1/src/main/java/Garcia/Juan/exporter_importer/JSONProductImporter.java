package Garcia.Juan.exporter_importer;

import Garcia.Juan.CRUD.CRUDImpoExpo;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Clase que proporciona una función para importar productos desde un archivo JSON.
 */
public class JSONProductImporter {

    /**
     * Importa productos desde un archivo JSON y los inserta en la base de datos.
     *
     * @param mySqlOperation Objeto MySqlOperation para realizar operaciones de base de datos.
     * @param jsonFilePath La ruta del archivo JSON a importar.
     * @return Lista de productos importados desde el archivo JSON.
     */
    public static List<Producto> importProductsFromJSON(MySqlOperation mySqlOperation, String jsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Producto> productos = objectMapper.readValue(new File(jsonFilePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Producto.class));

            CRUDImpoExpo.insertProducts(mySqlOperation, productos);
            System.out.println("Productos importados exitosamente desde el archivo JSON.");

            return productos;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            return null;
        }
    }
}
