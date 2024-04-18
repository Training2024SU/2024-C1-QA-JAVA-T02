package Garcia.Juan.io;

import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class CSVImporter {

    public static void importFromCSV(MySqlOperation mySqlOperation, String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            // Lee el encabezado del CSV
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                // Crea un objeto Producto con los datos de la línea
                Producto producto = new Producto();
                producto.setTitulo(line[0]);
                producto.setTipo(line[1]);
                producto.setAutor(line[2]);
                producto.setNumeroPaginas(line[3]);
                producto.setCantidadEjemplares(Integer.parseInt(line[4]));
                producto.setCantidadPrestados(Integer.parseInt(line[5]));

                // Inserta el producto en la base de datos usando un método separado
                insertProductFromCSV(mySqlOperation, producto);
            }

            System.out.println("Datos importados desde CSV exitosamente.");

        } catch (IOException | NumberFormatException | CsvValidationException e) {
            System.err.println("Error al importar datos desde CSV: " + e.getMessage());
        }
    }

    /**
     * Método separado para insertar productos en la base de datos desde el archivo CSV.
     *
     * @param mySqlOperation La instancia de MySqlOperation para ejecutar consultas en la base de datos.
     * @param producto El objeto Producto con los datos a insertar.
     */
    private static void insertProductFromCSV(MySqlOperation mySqlOperation, Producto producto) {
        // Formatea la consulta SQL para insertar el producto en la base de datos
        String insertProductSQL = String.format(
                "INSERT INTO bibliotecapingu.producto (titulo, tipo, autor, magnitud, cant_ejemplares, cant_prestados) " +
                        "VALUES ('%s', '%s', '%s', '%s', %d, %d)",
                producto.getTitulo(),
                producto.getTipo(),
                producto.getAutor(),
                producto.getNumeroPaginas(),
                producto.getCantidadEjemplares(),
                producto.getCantidadPrestados()
        );

        // Establece la consulta SQL en la instancia de MySqlOperation
        mySqlOperation.setSqlStatement(insertProductSQL);
        // Ejecuta la consulta SQL
        mySqlOperation.executeSqlStatementVoid();
    }
}
