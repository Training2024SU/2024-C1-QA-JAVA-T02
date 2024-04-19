package Garcia.Juan.Exporter;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import Garcia.Juan.model.Producto;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLProductExporter {

    public static void exportProductsToXML(List<Producto> productos) {
        // Ruta del archivo XML donde se exportarán los productos
        String filePath = "/home/dan/Desktop/productos.xml";

        // Crear una instancia de XmlMapper
        XmlMapper xmlMapper = new XmlMapper();

        try {
            // Escribir la lista de productos como XML en el archivo especificado
            xmlMapper.writeValue(new File(filePath), productos);
            System.out.println("XML exportado exitosamente a: " + filePath);
        } catch (IOException e) {
            // Manejar excepciones durante la exportación
            System.err.println("Error al exportar a XML: " + e.getMessage());
        }
    }
}

