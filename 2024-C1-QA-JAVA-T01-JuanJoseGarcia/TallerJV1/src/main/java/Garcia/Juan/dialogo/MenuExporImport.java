package Garcia.Juan.dialogo;

import Garcia.Juan.CRUD.CRUDImpoExpo;
import Garcia.Juan.Exporter.*;
import Garcia.Juan.model.Producto;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static Garcia.Juan.CRUD.CRUDImpoExpo.getProductsFromTable;
import static Garcia.Juan.Main.mySqlOperation;
import static Garcia.Juan.dialogo.ConstantesDialogo.SALIR;
import static Garcia.Juan.dialogo.ConstantesDialogo.SELECCIONE;

public class MenuExporImport {

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

            int opcion = scanner.nextInt();

            List<Producto> productos;

            switch (opcion) {
                case 1:
                    System.out.println("Importar desde archivo CSV");

                    // Crear un objeto CSVProductImporter
                    CSVProductImporter csvImporter = new CSVProductImporter();

                    // Importar los productos desde el archivo CSV
                    List<Producto> productosImportados = csvImporter.importProductsFromCSV(mySqlOperation);

                    // Insertar los productos importados en la base de datos
                    CRUDImpoExpo.insertProducts(mySqlOperation, productosImportados);
                    break;

                case 2:
                    System.out.println("Exportar a archivo CSV");
                    productos = getProductsFromTable(mySqlOperation);
                    CSVProductExporter.exportProductsToCSV(productos);
                    break;

                case 3:
                    // Llamar a la función para importar desde archivo XML
                    System.out.println("Importar desde archivo XML");
                    // Aquí debes agregar el código de importación desde XML
                    break;

                case 4:
                    // Llamar a la función para exportar a archivo XML
                    System.out.println("Exportar a archivo XML");
                    productos = getProductsFromTable(mySqlOperation);
                    XMLProductExporter.exportProductsToXML(productos);
                    // Aquí debes agregar el código de exportación a XML
                    break;

                case 5:
                    System.out.println("Importar desde archivo JSON");

                    // Crear un objeto JSONProductImporter
                    JSONProductImporter jsonImporter = new JSONProductImporter();

                    // Importar los productos desde el archivo JSON
                    List<Producto> productosImportadosDesdeJSON = jsonImporter.importProductsFromJSON(mySqlOperation);

                    // Insertar los productos importados en la base de datos
                    CRUDImpoExpo.insertProducts(mySqlOperation, productosImportadosDesdeJSON);

                case 6:
                    System.out.println("Exportar a archivo JSON");
                    productos = getProductsFromTable(mySqlOperation);
                    JSONProductExporter.exportProductsToJSON(productos);
                    break;

                case 7:
                    salir = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, inténtelo de nuevo.");
                    break;
            }

        }

        // Cerrar el escáner
        scanner.close();
    }
}
