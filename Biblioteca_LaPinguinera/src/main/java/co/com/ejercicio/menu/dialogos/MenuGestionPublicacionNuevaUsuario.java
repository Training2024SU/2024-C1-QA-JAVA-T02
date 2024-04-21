package co.com.ejercicio.menu.dialogos;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Publicacion;
import co.com.ejercicio.modeloAccesoBD.PublicacionAccesoBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static co.com.ejercicio.menu.constantesMenu.ConstanteParaMenu.ELIGE_OPCION;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;
import static co.com.ejercicio.menu.gestionMenu.GestionCancion.*;
import static co.com.ejercicio.menu.gestionMenu.GestionEnsayoTesis.*;
import static co.com.ejercicio.menu.gestionMenu.GestionVideoGrabacion.*;

public class MenuGestionPublicacionNuevaUsuario {
    public static void menuPublicacionNuevaParaUsuario() {
        Scanner scanner = new Scanner(System.in);
        boolean continuarMenu = true;

        while(continuarMenu){
        System.out.println("MENÚ DE GESTIÓN DE PUBLICACIONES NUEVAS");
        System.out.println("¿Qué desea hacer?");
        System.out.println("1. Obtener ensayo");
        System.out.println("2. Obtener autores de ensayo");
        System.out.println("3. Obtener videograbacion");
        System.out.println("4. Obtener directores de videograbaciones");
        System.out.println("5. Obtener cancion");
        System.out.println("6. Obtener artistas de canciones");
        System.out.println("7. Salir");


        System.out.print(ELIGE_OPCION);
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcion) {
            case 1:
                try {
                    gestionObtenerEnsayoTesis();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    gestionObtenerAutoresEnsayo();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                gestionObtenerVideoGrabacion();
                break;
            case 4:
                try {
                    gestionObtenerDirectorVideoGrabacion();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 5:
                gestionObtenerCancion();
                break;

            case 6:
                gestionObtenerArtistaCancion();
                break;
            case 7:
                continuarMenu = false;
                break;
            default:
                System.out.println("Opción no válida. Por favor ingrese un número entre 1 y 6.");
            }
         }
    }
}
