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


        System.out.println("MENÚ DE GESTIÓN DE PUBLICACIONES");
        System.out.println("¿Qué desea hacer?");
        System.out.println(INSERTAR);
        System.out.println(OBTENER);
        System.out.println(ACTUALIZAR);
        System.out.println(ELIMINAR);
        System.out.println(OBTENER_AUTORES);
        System.out.println("6. Insertar ensayo");
        System.out.println("7. Obtener ensayo");
        System.out.println("8. Actualizar ensayo");
        System.out.println("9. Eliminar ensayo");
        System.out.println("10. Insertar grabacion");
        System.out.println("11. Obtener grabacion");
        System.out.println("12. Actualizar grabacion");
        System.out.println("13. Eliminar grabacion");
        System.out.println("14. Insertar cancion");
        System.out.println("15. Obtener cancion");
        System.out.println("16. Actualizar cancion");
        System.out.println("17. Eliminar cancion");

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
                    gestionInsertarEnsayo();
                    mostrarMenuAdministrador();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 7:
                try {
                    gestionObtenerEnsayoTesis();
                    mostrarMenuAdministrador();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 8:
                try {
                    gestionActualizarEnsayoTesis();
                    mostrarMenuAdministrador();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 9:
                try {
                    gestionEliminarEnsayoTesis();
                    mostrarMenuAdministrador();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 10:
                try {
                    gestionObtenerAutoresEnsayo();
                    mostrarMenuAdministrador();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                mostrarMenuAdministrador();
                break;

            default:
                System.out.println("Opción no válida. Por favor ingrese un número entre 1 y 4.");
        }
        scanner.close();
    }
}
