package Garcia.Juan.exporter_importer;

import Garcia.Juan.model.Producto;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Clase que proporciona una función para exportar productos a un archivo CSV.
 */
public class CSVProductExporter {

    /**
     * Exporta una lista de productos a un archivo CSV.
     *
     * @param productos La lista de productos a exportar.
     * @param filePath  La ruta de archivo CSV de salida.
     */
    public static void exportProductsToCSV(List<Producto> productos, String filePath) {
        // Uso de un bloque try-with-resources para cerrar automáticamente el CSVWriter
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Escribir encabezados en el archivo CSV
            String[] headers = {"Título", "Tipo", "Autor", "Magnitud", "Cantidad de Ejemplares", "Cantidad de Prestados", "Cantidad Disponibles"};
            writer.writeNext(headers);

            // Escribir datos de productos en el archivo CSV
            for (Producto producto : productos) {
                String[] data = {
                        producto.getTitulo(),
                        producto.getTipo(),
                        producto.getAutor(),
                        producto.getMagnitud(),
                        String.valueOf(producto.getCantidadEjemplares()),
                        String.valueOf(producto.getCantidadPrestados()),
                        String.valueOf(producto.getCantidadDisponibles())
                };
                writer.writeNext(data);
            }

            // Mensaje de éxito para confirmar exportación
            System.out.println("CSV exportado exitosamente a: " + filePath);
        } catch (IOException e) {
            // Manejo de errores al exportar a CSV
            System.err.println("Error al exportar a CSV: " + e.getMessage());
        }
    }
}
