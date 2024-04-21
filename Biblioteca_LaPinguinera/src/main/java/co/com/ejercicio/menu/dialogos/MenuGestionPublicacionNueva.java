package co.com.ejercicio.menu.dialogos;

import java.sql.SQLException;
import java.util.Scanner;

import static co.com.ejercicio.menu.constantesMenu.ConstanteParaMenu.ELIGE_OPCION;
import static co.com.ejercicio.menu.gestionMenu.GestionAdministrador.mostrarMenuAdministrador;
import static co.com.ejercicio.menu.gestionMenu.GestionCancion.*;
import static co.com.ejercicio.menu.gestionMenu.GestionEnsayoTesis.*;
import static co.com.ejercicio.menu.gestionMenu.GestionVideoGrabacion.*;

public class MenuGestionPublicacionNueva {
    public static void menuPublicacionNueva() {

        Scanner scanner = new Scanner(System.in);

        boolean continuarMenu = true;

        while(continuarMenu){
        System.out.println("MENÚ DE GESTIÓN DE PUBLICACIONES NUEVAS");
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Insertar ensayo");
        System.out.println("2. Obtener ensayo");
        System.out.println("3. Obtener autores de ensayo");
        System.out.println("4. Actualizar ensayo");
        System.out.println("5. Eliminar ensayo");
        System.out.println("6. Insertar videograbacion");
        System.out.println("7. Obtener videograbacion");
        System.out.println("8. Obtener directores de videograbaciones");
        System.out.println("9. Actualizar videograbacion");
        System.out.println("10. Eliminar videograbacion");
        System.out.println("11. Insertar cancion");
        System.out.println("12. Obtener cancion");
        System.out.println("13. Obtener artistas de canciones");
        System.out.println("14. Actualizar cancion");
        System.out.println("15. Eliminar cancion");
        System.out.println("0. Salir");

        System.out.print(ELIGE_OPCION);
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcion) {
            case 1:
                try {
                    gestionInsertarEnsayo();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    gestionObtenerEnsayoTesis();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                try {
                    gestionObtenerAutoresEnsayo();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                mostrarMenuAdministrador();
                break;
            case 4:
                try {
                    gestionActualizarEnsayoTesis();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 5:
                try {
                    gestionEliminarEnsayoTesis();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case 6:
                try {
                    gestionInsertarVideoGrabacion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case 7:
                gestionObtenerVideoGrabacion();
                break;

            case 8:
                try {
                    gestionObtenerDirectorVideoGrabacion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 9:
                try {
                    gestionActualizarVideograbacion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 10:
                try {
                    gestionEliminarVideoGrabacion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 11:
                try {
                    gestionInsertarCancion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                mostrarMenuAdministrador();
                break;

            case 12:
                gestionObtenerCancion();
                break;

            case 13:
                gestionObtenerArtistaCancion();
                break;

            case 14:
                try {
                    gestionActualizarCancion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case 15:
                try {
                    gestionEliminarCancion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 0:
                continuarMenu = false;
                break;

            default:
                System.out.println("Opción no válida. Por favor ingrese un número entre 1 y 15.");
            }
        }
    }
}

