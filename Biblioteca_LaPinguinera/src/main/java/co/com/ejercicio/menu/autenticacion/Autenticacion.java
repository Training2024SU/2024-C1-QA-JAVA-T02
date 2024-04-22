package co.com.ejercicio.menu.autenticacion;

import java.util.HashMap;
import java.util.Map;

public class Autenticacion {
// ADMINSTRADOR
    public static final String NOMBRE_ADMIN = "Jhon Doe";
    private static final String CORREO_ADMIN = "administrador@pingu.com.co";
    private static final String CONTRASENA_ADMIN = "contrasenasegura123456";

    private static final String CORREO_SUPER_USUARIO = "super";
    private static final String CONTRASENA_SUPER_USUARIO = "super";

    private static final Map<String, String> credencialesAdmin = new HashMap<>();

    static {
        // Se agregan las credenciales del usuario principal
        credencialesAdmin.put(CORREO_ADMIN, CONTRASENA_ADMIN);
    }
    public static boolean autenticarAdmin(String correo, String contrasena) {
        if (correo.equals(CORREO_ADMIN) && contrasena.equals(CONTRASENA_ADMIN)) {
            return true; // El administrador es correcto
        }
        return false;
    }

    public static boolean autenticarSuperUsuario(String correo, String contrasena) {
        if (correo.equals(CORREO_SUPER_USUARIO) && contrasena.equals(CONTRASENA_SUPER_USUARIO)) {
            return true; // El superUsuario es correcto
        }
        return false;
    }
}
