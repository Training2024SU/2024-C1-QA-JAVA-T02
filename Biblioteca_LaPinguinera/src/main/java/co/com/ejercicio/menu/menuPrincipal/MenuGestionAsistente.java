package co.com.ejercicio.menu.menuPrincipal;

import java.sql.SQLException;
import java.util.Scanner;

import static co.com.ejercicio.menu.gestionMenu.GestionAdministrador.*;
import static co.com.ejercicio.menu.gestionMenu.GestionEmpleado.gestionarModificarContrasenaEmpleado;


public class MenuGestionAsistente {

    public static void menuAsistente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("MENÚ DE GESTIÓN - ASISTENTE");
        System.out.println("¿Qué desea gestionar?");
        System.out.println("1. Gestionar Publicacion");
        System.out.println("2. Gestionar préstamos");
        System.out.println("3. Modificar contraseña");
        System.out.println("4. Gestionar Publicacion Nueva");
        System.out.println("0. Cerrar sesion");
        System.out.print("Ingrese su opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {

            case 1:
                gestionarPublicacion();
                break;
            case 2:
                gestionarPrestamos();
                break;
            case 3:
                try{
                    gestionarModificarContrasenaEmpleado();
                } catch (SQLException e){
                    throw new RuntimeException(e);
                }

                break;
            case 4:
                gestionarPublicacionNueva();
                break;
            case 0:
                System.out.println("Cerraste Sesion");
                menuAsistente();
                break;
            default:
                System.out.println("Opción no válida. Por favor ingrese un número valido.");
                System.out.println(" ");
                menuAsistente();

        }
    }
}
