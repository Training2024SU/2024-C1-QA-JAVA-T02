package Garcia.Juan.logica;

import Garcia.Juan.model.Prestamo;
import Garcia.Juan.util.EstadoPrestamo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PrestamosPrueba {

    private static List<Prestamo> prestamos = new ArrayList<>();

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

        Prestamo prestamo = new Prestamo(id, estadoString, fechaSalida, fechaDevolucion, correoUsuario);
        prestamos.add(prestamo);
        System.out.println("Préstamo de prueba realizado.");
    }


    public static void consultarPrestamosPrueba() {
        System.out.println("Préstamos de prueba:");
        prestamos.forEach(System.out::println);
    }

    public static void eliminarPrestamosPrueba() {
        prestamos.clear();
        System.out.println("Préstamos de prueba eliminados.");
    }

    public static void menuPrestamosPrueba() {
        Scanner scanner = new Scanner(System.in);
        boolean ciclo = true;

        while (ciclo) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Realizar préstamo de prueba");
            System.out.println("2. Consultar préstamos de prueba");
            System.out.println("3. Eliminar todos los préstamos de prueba");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

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

    public static void main(String[] args) {
        menuPrestamosPrueba();
    }
}
