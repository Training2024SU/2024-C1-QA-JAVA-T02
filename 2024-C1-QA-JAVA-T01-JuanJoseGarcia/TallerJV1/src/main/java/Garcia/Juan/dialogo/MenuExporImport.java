package Garcia.Juan.dialogo;

import static Garcia.Juan.dialogo.ConstantesDialogo.SALIR;
import static Garcia.Juan.dialogo.ConstantesDialogo.SELECCIONE;

public class MenuExporImport {

    public static void menuImportExport() {
        System.out.println(SELECCIONE);
        System.out.println("1. Importar desde archivo CSV");
        System.out.println("2. Exportar a archivo CSV");
        System.out.println("3. Importar desde archivo XML");
        System.out.println("4. Exportar a archivo XML");
        System.out.println("5. Importar desde archivo JSON");
        System.out.println("6. Exportar a archivo JSON");
        System.out.println("7. " + SALIR);
    }

}
