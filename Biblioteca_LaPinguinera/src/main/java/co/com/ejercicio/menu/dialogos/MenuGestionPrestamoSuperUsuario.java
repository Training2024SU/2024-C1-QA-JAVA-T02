package co.com.ejercicio.menu.dialogos;

import java.util.Scanner;

import static co.com.ejercicio.menu.constantesMenu.ConstanteParaMenu.*;
import static co.com.ejercicio.menu.gestionMenu.GestionPrestamoSuperUsuario.*;

public class MenuGestionPrestamoSuperUsuario {
    public static void menuPrestamosSuperUsuario() {
        boolean continuarMenu = true;

        while(continuarMenu){
            Scanner scanner = new Scanner(System.in);
            System.out.println("MENÚ DE GESTIÓN DE PRÉSTAMOS PARA SUPER USUARIO");
            System.out.println("¿Qué desea hacer?");
            System.out.println(INSERTAR);
            System.out.println(OBTENER);
            System.out.println(ACTUALIZAR);
            System.out.println(ELIMINAR);
            System.out.println("5. Salir");
            System.out.print(ELIGE_OPCION);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    insertarPrestamoSuperUsuario();
                    break;
                case 2:
                    obtenerPrestamoSuperUsuario();
                    break;
                case 3:
                    actualizarPrestamoSuperUsuario();
                    break;
                case 4:
                    eliminarPrestamoSuperUsuario();
                    break;
                case 5:
                    continuarMenu = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor ingrese un número entre 1 y 4.");
            }
        }

    }
}
