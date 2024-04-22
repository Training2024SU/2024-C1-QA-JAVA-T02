package co.com.ejercicio.menu.dialogos;

import java.sql.SQLException;
import java.util.Scanner;

import static co.com.ejercicio.menu.gestionMenu.GestionEnsayoTesis.*;
import static co.com.ejercicio.menu.gestionMenu.GestionPublicacion.*;
import static co.com.ejercicio.menu.gestionMenu.GestionAdministrador.mostrarMenuAdministrador;
import static co.com.ejercicio.menu.constantesMenu.ConstanteParaMenu.*;

public class MenuGestionPublicacion {

    public static void menuPublicacion() {
        Scanner scanner = new Scanner(System.in);

        boolean continuarMenu = true;

        while(continuarMenu){
        System.out.println("MENÚ DE GESTIÓN DE PUBLICACIONES");
        System.out.println("¿Qué desea hacer?");
        System.out.println(INSERTAR);
        System.out.println(OBTENER);
        System.out.println(ACTUALIZAR);
        System.out.println(ELIMINAR);
        System.out.println(OBTENER_AUTORES);
        System.out.println("6. Exportar publicaciones a Json");
        System.out.println("7. Exportar publicaciones a XML");
        System.out.println("8. Imprimir publicaciones de archivo json");
        System.out.println("9. Imprimir publicaciones de archivo xml");
            System.out.println("0. Salir");

        System.out.print(ELIGE_OPCION);
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcion) {
            case 1:
                try {
                    gestionInsertarPublicacion();
                    mostrarMenuAdministrador();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    gestionObtenerPublicacion();
                    mostrarMenuAdministrador();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:

                try {
                    gestionActualizarPublicacion();
                    mostrarMenuAdministrador();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                try {
                    gestionEliminarPublicacion();
                    mostrarMenuAdministrador();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case 5:
                try {
                    gestionObtenerAutoresPublicacion();
                    mostrarMenuAdministrador();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                mostrarMenuAdministrador();
                break;
            case 6:
                try {
                    gestionarExportarAJson();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 7:
                try {
                    gestionarExportarAXML();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 8:
                gestionarImportarJson();
                break;
            case 9:
                gestionarImportarXml();
                break;
            case 0:
                continuarMenu = false;
                break;
            default:
                System.out.println("Opción no válida. Por favor ingrese un número entre 1 y 5.");
            }
        }
    }
}
