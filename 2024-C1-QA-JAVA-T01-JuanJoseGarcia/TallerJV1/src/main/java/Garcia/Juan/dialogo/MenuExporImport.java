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

            int opcion = scanner.nextInt();

            List<Producto> productos;

            switch (opcion) {
                case 1:
                    CSVProductImporter csvImporter = new CSVProductImporter();
                    List<Producto> productosImportados = csvImporter.importProductsFromCSV(mySqlOperation);
                    CRUDImpoExpo.insertProducts(mySqlOperation, productosImportados);
                    break;

                case 2:
                    productos = getProductsFromTable(mySqlOperation);
                    CSVProductExporter.exportProductsToCSV(productos);
                    break;

                case 3:
                    List<Producto> productosImportadorXML = XMLProductImporter.importProductsFromXML(mySqlOperation);
                    CRUDImpoExpo.insertProducts(mySqlOperation, productosImportadorXML);
                    break;

                case 4:
                    productos = getProductsFromTable(mySqlOperation);
                    XMLProductExporter.exportProductsToXML(productos);
                    break;

                case 5:
                    JSONProductImporter jsonImporter = new JSONProductImporter();
                    List<Producto> productosImportadosDesdeJSON = jsonImporter.importProductsFromJSON(mySqlOperation);
                    CRUDImpoExpo.insertProducts(mySqlOperation, productosImportadosDesdeJSON);
                    break;

                case 6:
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
        scanner.close();
    }
}
