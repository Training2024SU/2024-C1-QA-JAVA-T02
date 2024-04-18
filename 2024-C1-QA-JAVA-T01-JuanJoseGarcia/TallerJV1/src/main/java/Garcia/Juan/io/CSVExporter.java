package Garcia.Juan.io;

import com.opencsv.CSVWriter;
import Garcia.Juan.CRUD.CrudProducto;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Producto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExporter {

    public static void exportToCSV(MySqlOperation mySqlOperation, String filePath) {
        // Obtener todos los productos
        List<Producto> productos = CrudProducto.getAllProducts(mySqlOperation);

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Escribir encabezados del CSV
            String[] header = {"Titulo", "Tipo", "Autor", "Magnitud", "CantidadEjemplares", "CantidadPrestados", "CantidadDisponibles"};
            writer.writeNext(header);

            // Escribir datos de productos
            for (Producto producto : productos) {
                String[] data = {
                        producto.getTitulo(),
                        producto.getTipo(),
                        producto.getAutor(),
                        producto.getNumeroPaginas(),
                        String.valueOf(producto.getCantidadEjemplares()),
                        String.valueOf(producto.getCantidadPrestados()),
                        String.valueOf(producto.getCantidadDisponibles())
                };
                writer.writeNext(data);
            }

            System.out.println("Datos exportados a CSV exitosamente.");

        } catch (IOException e) {
            System.err.println("Error al exportar datos a CSV: " + e.getMessage());
        }
    }
}
