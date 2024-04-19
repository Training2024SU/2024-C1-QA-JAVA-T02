package Garcia.Juan.Exporter;

import Garcia.Juan.CRUD.CRUDImpoExpo;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLProductImporter {

    // Ruta del archivo XML a importar
    private static final String XML_FILE_PATH = "/home/dan/Desktop/productos.xml";

    // Método para importar datos desde un archivo XML e insertarlos en la base de datos
    public static List<Producto> importProductsFromXML(MySqlOperation mySqlOperation) {
        // Crear una instancia de XmlMapper
        XmlMapper xmlMapper = new XmlMapper();
        List<Producto> productos = null;

        try {
            // Leer y deserializar los datos desde el archivo XML
            productos = xmlMapper.readValue(new File(XML_FILE_PATH), xmlMapper.getTypeFactory().constructCollectionType(List.class, Producto.class));

            // Insertar la lista de productos en la base de datos
            CRUDImpoExpo.insertProducts(mySqlOperation, productos);
            System.out.println("Productos importados exitosamente desde el archivo XML.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo XML: " + e.getMessage());
        }
        return productos;
    }
}
