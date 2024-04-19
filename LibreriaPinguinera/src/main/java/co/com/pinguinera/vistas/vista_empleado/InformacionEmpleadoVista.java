package co.com.pinguinera.vistas.vista_empleado;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class InformacionEmpleadoVista {
    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global desde LoggerUtil

    private Scanner scanner;

    public InformacionEmpleadoVista() {
        this.scanner = new Scanner(System.in);
    }

    public String pedirNombre() {
        return VistaUtil.pedirNombre();
    }

    public String pedirContrasena() {
        return VistaUtil.pedirContrasena();
    }

    public String pedirCorreo() {
        return VistaUtil.pedirCorreoElectronico();
    }

    public String pedirRol() {
        LOGGER.info("Ingrese el rol del empleado (Administrativo/Asistente):");
        return scanner.nextLine();
    }

    public boolean pedirEsAdministrativo() {
        LOGGER.info("¿El empleado es administrativo? (0 para no, 1 para sí):");
        int opcion = Integer.parseInt(scanner.nextLine());
        return opcion == 1;
    }

    public String pedirEdadUsuario() {
        LOGGER.info("Ingrese la edad del empleado:");
        return scanner.nextLine();
    }

    public String pedirTelefonoUsuario() {
        LOGGER.info("Ingrese el telefono del empleado:");
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

}
