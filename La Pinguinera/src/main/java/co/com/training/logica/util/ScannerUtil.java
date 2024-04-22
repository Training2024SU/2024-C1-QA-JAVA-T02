package co.com.training.logica.util;


import java.util.Scanner;

public class ScannerUtil {
    private static Scanner scanner = new Scanner(System.in);


    private ScannerUtil() {
    }

    public static Scanner obtenerScanner() {
        return scanner;
    }

    public static void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }

    public static int pedirEntero() {

        int entero =0;

        while (true) {
            try {
                entero = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Input no válido. Por favor ingresa un entero");
                // Clear
                scanner.next(); // Read and discard the invalid input
            }

        }
        return entero;
    }
}
