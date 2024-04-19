package co.com.pinguinera.vistas.vista_usuario;

import co.com.pinguinera.LoggerUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class InformacionUsuarioVista {
    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global desde LoggerUtil

    private final Scanner scanner;

    public InformacionUsuarioVista() {
        this.scanner = new Scanner(System.in);
    }

    public String pedirNombreUsuario() {
        LOGGER.info("Ingrese el nombre del usuario:");
        return scanner.nextLine();
    }

    public String pedirCorreoUsuario() {
        LOGGER.info("Ingrese el correo electrónico del usuario:");
        return scanner.nextLine();
    }

    public String pedirContrasenaUsuario() {
        LOGGER.info("Ingrese la contraseña del usuario:");
        return scanner.nextLine();
    }

    public String pedirEdadUsuario() {
        LOGGER.info("Ingrese la edad del usuario:");
        return scanner.nextLine();
    }

    public String pedirTelefonoUsuario() {
        LOGGER.info("Ingrese el telefono del usuario:");
        return scanner.nextLine();
    }

    public List<String> preguntaInfoAdicional(){
        LOGGER.info("Desea agrear la información adicional? (Edad y telefono)");
        LOGGER.info("1 para si, 0 para no");
        int opcion = Integer.parseInt(scanner.nextLine());
        List<String> infoAdicional =new ArrayList<>();
        if(opcion == 1) {
            infoAdicional.add(pedirEdadUsuario());
            infoAdicional.add(pedirTelefonoUsuario());
            return infoAdicional;
        }
        else{
            infoAdicional.add("NO");
            return infoAdicional;
        }
    }

    public List<String> preguntaInfoAdicionalUpdate(){
        LOGGER.info("Desea actualizar la información adicional? (Edad y telefono)");
        LOGGER.info("1 para si, 0 para no");
        int opcion = Integer.parseInt(scanner.nextLine());
        List<String> infoAdicional =new ArrayList<>();
        if(opcion == 1) {
            infoAdicional.add(pedirEdadUsuario());
            infoAdicional.add(pedirTelefonoUsuario());
            return infoAdicional;
        }
        else{
            infoAdicional.add("NO");
            return infoAdicional;
        }
    }
}
