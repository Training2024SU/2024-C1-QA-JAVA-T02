package co.com.ejercicio.menu.menuPrincipal;

import co.com.ejercicio.util.enums.TipoGestion;

import java.sql.SQLException;
import java.util.Scanner;

import static co.com.ejercicio.menu.constantesMenu.ConstanteParaMenu.ELIGE_OPCION;
import static co.com.ejercicio.menu.dialogos.MenuPrincipal.mostrarMenu;
import static co.com.ejercicio.menu.gestionMenu.GestionAdministrador.*;
import static co.com.ejercicio.menu.gestionMenu.GestionSuperUsuario.gestionInsertarAdministrador;
import static co.com.ejercicio.menu.gestionMenu.GestionSuperUsuario.gestionarPrestamosSuperUsuario;

public class MenuGestionSuperUsuario {

    public static void menuSuperUsuario(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("MENÚ DE GESTIÓN - SUPER USUARIO");
        System.out.println("¿Qué desea gestionar?");
        System.out.println("1. Crear administrador");
        System.out.println("2. Simular prestamos");
        System.out.println("3. Restableser prestamos");
        System.out.println("0. Cerrar sesion");
        System.out.print(ELIGE_OPCION);
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcion) {
            case 1:
                try{
                    gestionInsertarAdministrador();
                } catch (SQLException e){
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                gestionarPrestamosSuperUsuario();
                break;
            case 0:
                System.out.println("Cerraste Sesion");
                mostrarMenu();
                break;
            default:
                System.out.println("Opción no válida. Por favor ingrese un número entre 1 y 4.");
                System.out.println(" ");
                mostrarMenuAdministrador();
                System.out.println(" ");
        }
    }
}
