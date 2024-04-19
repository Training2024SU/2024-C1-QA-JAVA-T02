package Garcia.Juan.exporter_importer;

import Garcia.Juan.CRUD.CRUDImpoExpo;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Clase que proporciona una función para importar productos desde un archivo XML.
 */
public class XMLProductImporter {

    private static final String XML_FILE_PATH = "/home/dan/Desktop/productos.xml";

    /**
     * Importa productos desde un archivo XML y los inserta en la base de datos.
     *
     * @param mySqlOperation Objeto MySqlOperation para realizar operaciones de base de datos.
     * @return Lista de productos importados desde el archivo XML.
     */
    public static List<Producto> importProductsFromXML(MySqlOperation mySqlOperation) {
        XmlMapper xmlMapper = new XmlMapper();
        List<Producto> productos = null;

        try {
            productos = xmlMapper.readValue(new File(XML_FILE_PATH), xmlMapper.getTypeFactory().constructCollectionType(List.class, Producto.class));

            CRUDImpoExpo.insertProducts(mySqlOperation, productos);
            System.out.println("Productos importados exitosamente desde el archivo XML.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo XML: " + e.getMessage());
        }
        return productos;
    }
}
