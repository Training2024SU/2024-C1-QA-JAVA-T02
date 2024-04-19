package Garcia.Juan.Exporter;

import Garcia.Juan.model.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONProductExporter {

    public static void exportProductsToJSON(List<Producto> productos) {
        // Ruta del archivo JSON donde se exportarán los productos
        String filePath = "/home/dan/Desktop/productos.json";

        // Crear una instancia de ObjectMapper para manejar la serialización de JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Escribir la lista de productos como JSON en el archivo especificado
            objectMapper.writeValue(new File(filePath), productos);
            System.out.println("JSON exportado exitosamente a: " + filePath);
        } catch (IOException e) {
            System.err.println("Error al exportar a JSON: " + e.getMessage());
        }
    }
}
