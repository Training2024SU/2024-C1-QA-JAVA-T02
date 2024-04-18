package Garcia.Juan.dialogo;

import Garcia.Juan.Exporter.CSVProductExporter;
import Garcia.Juan.model.Producto;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static Garcia.Juan.CRUD.CRUDImpoExpo.getProductsFromTable;
import static Garcia.Juan.Main.mySqlOperation;
import static Garcia.Juan.dialogo.ConstantesDialogo.SALIR;
import static Garcia.Juan.dialogo.ConstantesDialogo.SELECCIONE;

public class MenuExporImport {

    // Menú que presenta opciones de importación y exportación al usuario
    public static void menuImportExport() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // Ciclo para mantener el menú abierto hasta que el usuario elija salir
        while (!salir) {
            System.out.println(SELECCIONE);
            System.out.println("1. Importar desde archivo CSV");
            System.out.println("2. Exportar a archivo CSV");
            System.out.println("3. Importar desde archivo XML");
            System.out.println("4. Exportar a archivo XML");
            System.out.println("5. Importar desde archivo JSON");
            System.out.println("6. Exportar a archivo JSON");
            System.out.println("7. " + SALIR);

            // Lee la opción del usuario
            int opcion = scanner.nextInt();

            // Utiliza un switch para manejar la opción seleccionada
            switch (opcion) {
                case 1:
                    // Llamar a la función para importar desde archivo CSV
                    System.out.println("Importar desde archivo CSV");
                    // Aquí debes agregar el código de importación desde CSV
                    break;

                case 2:
                    // Llamar a la función para exportar a archivo CSV
                    System.out.println("Exportar a archivo CSV");
                    List<Producto> productos = getProductsFromTable(mySqlOperation);
                    System.out.println(productos);
                    CSVProductExporter.exportProductsToCSV(productos);
                    // Aquí debes agregar el código de exportación a CSV
                    break;

                case 3:
                    // Llamar a la función para importar desde archivo XML
                    System.out.println("Importar desde archivo XML");
                    // Aquí debes agregar el código de importación desde XML
                    break;

                case 4:
                    // Llamar a la función para exportar a archivo XML
                    System.out.println("Exportar a archivo XML");
                    // Aquí debes agregar el código de exportación a XML
                    break;

                case 5:
                    // Llamar a la función para importar desde archivo JSON
                    System.out.println("Importar desde archivo JSON");
                    // Aquí debes agregar el código de importación desde JSON
                    break;

                case 6:
                    // Llamar a la función para exportar a archivo JSON
                    System.out.println("Exportar a archivo JSON");
                    // Aquí debes agregar el código de exportación a JSON
                    break;

                case 7:
                    // Opción para salir del menú
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
