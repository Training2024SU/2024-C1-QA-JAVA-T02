package Garcia.Juan.util;

import java.util.Scanner;

public class VistaUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static String solicitarCorreoElectronico() {
        System.out.println("Por favor ingrese un correo electrónico:");
        String correo = scanner.nextLine();
        return correo;
    }

    public static String solicitarContraseña() {
        System.out.println("Por favor ingrese una contraseña:");
        String contraseña = scanner.nextLine();
        return contraseña;
    }

    public static String solicitarNombre() {
        System.out.println("Por favor ingrese su nombre:");
        String nombre = scanner.nextLine();
        return nombre;
    }

    public static String solicitarNumeroTelefono() {
        System.out.println("Por favor ingrese su número de teléfono:");
        String numeroTelefono = scanner.nextLine();
        return numeroTelefono;
    }

    public static String solicitarDireccion() {
        System.out.println("Por favor ingrese su dirección:");
        String direccion = scanner.nextLine();
        return direccion;
    }
}
