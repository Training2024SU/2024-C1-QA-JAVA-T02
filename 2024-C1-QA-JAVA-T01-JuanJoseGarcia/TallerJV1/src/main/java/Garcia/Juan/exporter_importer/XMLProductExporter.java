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
     */
    public static void exportProductsToXML(List<Producto> productos) {
        String filePath = "/home/dan/Desktop/productos.xml";

        XmlMapper xmlMapper = new XmlMapper();

        try {
            xmlMapper.writeValue(new File(filePath), productos);
            System.out.println("XML exportado exitosamente a: " + filePath);
        } catch (IOException e) {
            System.err.println("Error al exportar a XML: " + e.getMessage());
        }
    }
}
