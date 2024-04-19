package Garcia.Juan.exporter_importer;

import Garcia.Juan.model.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Clase que proporciona una función para exportar productos a un archivo JSON.
 */
public class JSONProductExporter {

    /**
     * Exporta una lista de productos a un archivo JSON.
     *
     * @param productos La lista de productos a exportar.
     */
    public static void exportProductsToJSON(List<Producto> productos) {
        String filePath = "/home/dan/Desktop/productos.json";

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), productos);
            System.out.println("JSON exportado exitosamente a: " + filePath);
        } catch (IOException e) {
            System.err.println("Error al exportar a JSON: " + e.getMessage());
        }
    }
}
