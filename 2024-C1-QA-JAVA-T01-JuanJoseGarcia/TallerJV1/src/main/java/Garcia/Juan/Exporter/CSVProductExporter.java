package Garcia.Juan.Exporter;

import Garcia.Juan.model.Producto;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVProductExporter {

    public static void exportProductsToCSV(List<Producto> productos) {
        // Ruta del archivo CSV donde se exportarán los productos
        String filePath = "/home/dan/Desktop/productos.csv";

        // Usar un bloque try-with-resources para manejar la creación y cierre de CSVWriter
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Definir encabezados de columnas
            String[] headers = {"Título", "Tipo", "Autor", "Magnitud", "Cantidad de Ejemplares", "Cantidad de Prestados", "Cantidad Disponibles"};
            writer.writeNext(headers);

            // Iterar sobre la lista de productos para exportarlos al archivo CSV
            for (Producto producto : productos) {
                // Crear un array de datos con los atributos del producto
                String[] data = {
                        producto.getTitulo(),
                        producto.getTipo(),
                        producto.getAutor(),
                        producto.getMagnitud(), // Cambiado a 'getMagnitud'
                        String.valueOf(producto.getCantidadEjemplares()),
                        String.valueOf(producto.getCantidadPrestados()),
                        String.valueOf(producto.getCantidadDisponibles())
                };

                // Escribir los datos del producto al archivo CSV
                writer.writeNext(data);
            }

            // Mensaje de éxito al terminar la exportación
            System.out.println("CSV exportado exitosamente a: " + filePath);
        } catch (IOException e) {
            // Manejar excepciones durante la exportación
            System.err.println("Error al exportar a CSV: " + e.getMessage());
        }
    }
}
