package Garcia.Juan.Exporter;

import Garcia.Juan.CRUD.CRUDImpoExpo;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVProductImporter {

    // Ruta del archivo CSV a importar
    private static final String CSV_FILE_PATH = "/home/dan/Desktop/productos.csv";

    // Método para importar datos desde un archivo CSV e insertarlos en la base de datos
    public static List<Producto> importProductsFromCSV(MySqlOperation mySqlOperation) {
        // Lista para almacenar los productos leídos del archivo CSV
        List<Producto> productos = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] line;

            // Omitir la primera línea (encabezados) si es necesario
            reader.readNext();

            // Leer cada línea del archivo CSV
            while ((line = reader.readNext()) != null) {
                // Crear un objeto Producto a partir de los datos de la línea
                Producto producto = new Producto();
                producto.setTitulo(line[0]);
                producto.setTipo(line[1]);
                producto.setAutor(line[2]);
                producto.setMagnitud(line[3]);
                producto.setCantidadEjemplares(Integer.parseInt(line[4]));
                producto.setCantidadPrestados(Integer.parseInt(line[5]));
                producto.setCantidadDisponibles(Integer.parseInt(line[6]));

                // Agregar el producto a la lista
                productos.add(producto);
            }

            // Insertar la lista de productos en la base de datos
            CRUDImpoExpo.insertProducts(mySqlOperation, productos);
            System.out.println("Productos importados exitosamente desde el archivo CSV.");
        } catch (IOException | NumberFormatException | CsvValidationException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return productos;
    }
}
