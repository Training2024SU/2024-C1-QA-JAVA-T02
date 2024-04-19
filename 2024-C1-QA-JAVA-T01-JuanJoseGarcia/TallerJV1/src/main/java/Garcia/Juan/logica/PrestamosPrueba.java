package Garcia.Juan.logica;

import Garcia.Juan.model.Prestamo;
import Garcia.Juan.util.EstadoPrestamo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que proporciona funcionalidades para gestionar préstamos de prueba.
 * Incluye operaciones como realizar un préstamo de prueba, consultar los préstamos existentes
 * y eliminar todos los préstamos de prueba.
 */
public class PrestamosPrueba {

    /**
     * Lista que almacena todos los préstamos de prueba realizados.
     */
    private static List<Prestamo> prestamos = new ArrayList<>();

    /**
     * Realiza un préstamo de prueba solicitando al usuario los datos necesarios,
     * y luego guarda el préstamo en la lista de préstamos.
     */
    public static void realizarPrestamoPrueba() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID del préstamo:");
        String id = scanner.nextLine();

        System.out.println("Ingrese el correo del usuario:");
        String correoUsuario = scanner.nextLine();

        System.out.println("Seleccione el estado del préstamo (SOLICITADO, REALIZADO, FINALIZADO):");
        EstadoPrestamo estado = EstadoPrestamo.valueOf(scanner.nextLine().toUpperCase());
        String estadoString = estado.toString();

        System.out.println("Ingrese la fecha de salida (YYYY-MM-DD):");
        Date fechaSalida = java.sql.Date.valueOf(scanner.nextLine());

        System.out.println("Ingrese la fecha de devolución (YYYY-MM-DD):");
        Date fechaDevolucion = java.sql.Date.valueOf(scanner.nextLine());

        // Crear el objeto Prestamo con los datos proporcionados por el usuario
        Prestamo prestamo = new Prestamo(id, estadoString, fechaSalida, fechaDevolucion, correoUsuario);

        // Agregar el préstamo a la lista de préstamos
        prestamos.add(prestamo);
        System.out.println("Préstamo de prueba realizado.");
    }

    /**
     * Consulta todos los préstamos de prueba existentes y los imprime en consola.
     */
    public static void consultarPrestamosPrueba() {
        System.out.println("Préstamos de prueba:");
        // Imprime cada préstamo de la lista
        prestamos.forEach(System.out::println);
    }

    /**
     * Elimina todos los préstamos de prueba de la lista.
     */
    public static void eliminarPrestamosPrueba() {
        // Limpia la lista de préstamos
        prestamos.clear();
        System.out.println("Préstamos de prueba eliminados.");
    }

    /**
     * Muestra un menú de opciones para gestionar los préstamos de prueba.
     * Permite al usuario realizar un préstamo de prueba, consultar los préstamos existentes,
     * eliminar todos los préstamos de prueba, o salir.
     */
    public static void menuPrestamosPrueba() {
        Scanner scanner = new Scanner(System.in);
        boolean ciclo = true;

        // Ciclo para mantener el menú activo hasta que el usuario decida salir
        while (ciclo) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Realizar préstamo de prueba");
            System.out.println("2. Consultar préstamos de prueba");
            System.out.println("3. Eliminar todos los préstamos de prueba");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea restante

            // Ejecutar la opción seleccionada por el usuario
            switch (opcion) {
                case 1:
                    realizarPrestamoPrueba();
                    break;
                case 2:
                    consultarPrestamosPrueba();
                    break;
                case 3:
                    eliminarPrestamosPrueba();
                    break;
                case 4:
                    ciclo = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }

    /**
     * Punto de entrada principal de la aplicación para ejecutar el menú de préstamos de prueba.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        menuPrestamosPrueba();
    }
}
