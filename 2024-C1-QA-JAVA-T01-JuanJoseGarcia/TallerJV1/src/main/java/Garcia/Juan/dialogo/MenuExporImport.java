package Garcia.Juan.dialogo;

import Garcia.Juan.CRUD.CRUDImpoExpo;
import Garcia.Juan.exporter_importer.*;
import Garcia.Juan.model.Producto;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static Garcia.Juan.CRUD.CRUDImpoExpo.getProductsFromTable;
import static Garcia.Juan.Main.mySqlOperation;
import static Garcia.Juan.dialogo.ConstantesDialogo.SALIR;
import static Garcia.Juan.dialogo.ConstantesDialogo.SELECCIONE;

/**
 * Clase que representa el menú de importación y exportación de productos desde y hacia archivos CSV, XML, y JSON.
 * Permite al usuario seleccionar entre varias opciones para importar o exportar productos a diferentes formatos de archivo.
 */
public class MenuExporImport {

    /**
     * Método principal que muestra el menú de importación y exportación de productos.
     * El usuario puede elegir importar o exportar productos desde y hacia archivos CSV, XML, o JSON.
     *
     * @throws SQLException Si ocurre un error de base de datos durante la importación o exportación.
     */
    public static void menuImportExport() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println(SELECCIONE);
            System.out.println("1. Importar desde archivo CSV");
            System.out.println("2. Exportar a archivo CSV");
            System.out.println("3. Importar desde archivo XML");
            System.out.println("4. Exportar a archivo XML");
            System.out.println("5. Importar desde archivo JSON");
            System.out.println("6. Exportar a archivo JSON");
            System.out.println("7. " + SALIR);

            // Leer la opción del usuario
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea restante

            // Declarar la lista de productos
            List<Producto> productos;

            // Manejar las diferentes opciones del menú
            switch (opcion) {
                case 1:
                    // Importar desde CSV
                    System.out.print("Por favor, ingresa la ruta del archivo CSV a importar: ");
                    String csvFilePath = scanner.nextLine();
                    CSVProductImporter csvImporter = new CSVProductImporter();
                    List<Producto> productosImportados = csvImporter.importProductsFromCSV(mySqlOperation, csvFilePath);
                    CRUDImpoExpo.insertProducts(mySqlOperation, productosImportados);
                    break;
                case 2:
                    // Exportar a CSV
                    productos = getProductsFromTable(mySqlOperation);
                    System.out.print("Por favor, ingresa la ruta de salida del archivo CSV: ");
                    String filePath = scanner.nextLine();
                    CSVProductExporter.exportProductsToCSV(productos, filePath);
                    break;
                case 3:
                    // Importar desde XML
                    System.out.print("Por favor, ingresa la ruta del archivo XML a importar: ");
                    String xmlFilePath = scanner.nextLine(); // Leer la ruta especificada por el usuario
                    List<Producto> productosImportadosXML = XMLProductImporter.importProductsFromXML(mySqlOperation, xmlFilePath);
                    CRUDImpoExpo.insertProducts(mySqlOperation, productosImportadosXML);
                    break;
                case 4:
                    // Exportar a XML
                    productos = getProductsFromTable(mySqlOperation);
                    System.out.print("Por favor, ingresa la ruta de salida del archivo XML: ");
                    String filePath3 = scanner.nextLine();
                    XMLProductExporter.exportProductsToXML(productos,filePath3);
                    break;
                case 5:
                    // Importar desde JSON
                    System.out.print("Por favor, ingresa la ruta del archivo JSON a importar: ");
                    String jsonFilePath = scanner.nextLine(); // Leer la ruta especificada por el usuario
                    List<Producto> productosImportadosJSON = JSONProductImporter.importProductsFromJSON(mySqlOperation, jsonFilePath);
                    CRUDImpoExpo.insertProducts(mySqlOperation, productosImportadosJSON);
                    break;
                case 6:
                    // Exportar a JSON
                    productos = getProductsFromTable(mySqlOperation);
                    System.out.print("Por favor, ingresa la ruta de salida del archivo JSON: ");
                    String filePath1 = scanner.nextLine();
                    JSONProductExporter.exportProductsToJSON(productos,filePath1);
                    break;
                case 7:
                    // Salir
                    salir = true;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, inténtelo de nuevo.");
                    break;
            }
        }
        scanner.close();
    }
}
