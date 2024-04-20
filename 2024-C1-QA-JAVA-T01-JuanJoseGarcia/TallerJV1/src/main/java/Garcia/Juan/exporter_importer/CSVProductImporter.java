package Garcia.Juan.exporter_importer;

import Garcia.Juan.CRUD.CRUDImpoExpo;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que proporciona una función para importar productos desde un archivo CSV.
 */
public class CSVProductImporter {

    /**
     * Importa productos desde un archivo CSV y los inserta en la base de datos.
     *
     * @param mySqlOperation Objeto MySqlOperation para realizar operaciones de base de datos.
     * @param csvFilePath Ruta del archivo CSV a importar.
     * @return Lista de productos importados desde el archivo CSV.
     */
    public static List<Producto> importProductsFromCSV(MySqlOperation mySqlOperation, String csvFilePath) {
        List<Producto> productos = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            // Salta la primera línea (encabezados)
            reader.readNext();

            String[] line;
            while ((line = reader.readNext()) != null) {
                Producto producto = new Producto();
                producto.setTitulo(line[0]);
                producto.setTipo(line[1]);
                producto.setAutor(line[2]);
                producto.setMagnitud(line[3]);
                producto.setCantidadEjemplares(Integer.parseInt(line[4]));
                producto.setCantidadPrestados(Integer.parseInt(line[5]));
                producto.setCantidadDisponibles(Integer.parseInt(line[6]));

                productos.add(producto);
            }

            // Inserta productos en la base de datos
            CRUDImpoExpo.insertProducts(mySqlOperation, productos);
            System.out.println("Productos importados exitosamente desde el archivo CSV.");
        } catch (IOException | NumberFormatException | CsvValidationException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return productos;
    }
}
