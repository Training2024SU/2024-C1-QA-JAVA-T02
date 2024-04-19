package Garcia.Juan.Exporter;

import Garcia.Juan.model.Producto;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVProductExporter {

    public static void exportProductsToCSV(List<Producto> productos) {
        String filePath = "/home/dan/Desktop/productos.csv";

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            String[] headers = {"Título", "Tipo", "Autor", "Magnitud", "Cantidad de Ejemplares", "Cantidad de Prestados", "Cantidad Disponibles"};
            writer.writeNext(headers);

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

            System.out.println("CSV exportado exitosamente a: " + filePath);
        } catch (IOException e) {
            System.err.println("Error al exportar a CSV: " + e.getMessage());
        }
    }
}
