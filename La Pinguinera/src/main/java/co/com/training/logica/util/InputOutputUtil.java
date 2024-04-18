package co.com.training.logica.util;

import co.com.training.modelo.Libro;

import java.util.Scanner;

public class InputOutputUtil {

    public static Libro ingresarLibro() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa el titulo : \n");
        String titulo = scanner.nextLine();

        System.out.print("Ingresa el autor : \n");
        String autor = scanner.nextLine();

        System.out.print("Ingresa el área conocimiento : \n");
        String areaConocimiento = scanner.nextLine();

        System.out.print("Ingresa el número de páginas : \n");
        int numeroPaginas = ScannerUtil.pedirEntero();

        System.out.print("Ingresa el número de cantidad ejemplares : \n");
        int cantidadEjemplares = ScannerUtil.pedirEntero();

        System.out.print("Ingresa el número de cantidad prestados : \n");
        int cantidadPrestados = ScannerUtil.pedirEntero();

        return new Libro(titulo,autor,areaConocimiento,numeroPaginas,cantidadEjemplares,cantidadPrestados);
    }
}
