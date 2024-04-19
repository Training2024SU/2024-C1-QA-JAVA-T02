package Garcia.Juan.exporter_importer;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import Garcia.Juan.model.Producto;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Clase que proporciona una función para exportar productos a un archivo XML.
 */
public class XMLProductExporter {

    /**
     * Exporta una lista de productos a un archivo XML.
     *
     * @param productos La lista de productos a exportar.
     * @param filePath La ruta del archivo de salida XML.
     */
    public static void exportProductsToXML(List<Producto> productos, String filePath) {
        // Crear un objeto XmlMapper
        XmlMapper xmlMapper = new XmlMapper();

        // Usar try-catch para manejar posibles excepciones de E/S
        try {
            // Escribir la lista de productos en el archivo XML especificado por filePath
            xmlMapper.writeValue(new File(filePath), productos);
            System.out.println("XML exportado exitosamente a: " + filePath);
        } catch (IOException e) {
            // Mostrar el mensaje de error si ocurre una excepción de E/S
            System.err.println("Error al exportar a XML: " + e.getMessage());
        }
    }
}
