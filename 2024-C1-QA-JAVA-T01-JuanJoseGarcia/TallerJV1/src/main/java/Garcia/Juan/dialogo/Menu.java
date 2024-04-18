package Garcia.Juan.dialogo;
import java.util.logging.Logger;

import java.util.List;

import static Garcia.Juan.dialogo.ConstantesDialogo.*;

public class Menu {
    private static final Logger LOGGER = Logger.getLogger(Menu.class.getName());
    public static void menuInicial(){

        System.out.println(MSN_UNO);
        System.out.println(MSN_DOS);
        System.out.println(MSN_TRES);
        System.out.println(MSN_CUATRO);
    }

    public static void errorOpcion()
    {
        System.out.println(ERROR);
    }

    public static void loggerInicio(){
        LOGGER.info("Inicio de la aplicacion.");
    }

    public static void loggerFinal(){
        LOGGER.info("Final de la aplicacion.");
    }

    public static void  menuUsuarioIniciado(List<String> usuarioIniciado){
        System.out.println(MSN_UNO+" "+usuarioIniciado.get(3));
    }

    public static void menuLector() {
        System.out.println(SELECCIONE);
        System.out.println(VER_PUBLICACIONES);
        System.out.println(SOLICITAR_PRESTAMO);
        System.out.println("3. Actualizar información personal"); // Nueva opción
        System.out.println("4. " + SALIR);
    }

    public static void  menuAsistente(){
        System.out.println(SELECCIONE);
        System.out.println(GESTIONAR_MATERIAL);
        System.out.println(CONSULTAR_PRESTAMOS);
        System.out.println("3. " +SALIR);
    }

    public static void  menuAdmin(){
        System.out.println(SELECCIONE);
        System.out.println(GESTIONAR_MATERIAL);
        System.out.println(CONSULTAR_PRESTAMOS);
        System.out.println(REGISTRAR_ASISTENTE);
        System.out.println(INFO_USUARIOS);
        System.out.println("5. " +SALIR);
    }

    public static void menuProductos(){
        System.out.println(SELECCIONE);
        System.out.println(VER_LIBROS);
        System.out.println(VER_LIBROS_AUTOR);
        System.out.println(VER_NOVELAS);
        System.out.println(LISTAR_AUTORES);

    }

    public static void menuConfirmacion(){
        System.out.println(PREGUNTA_CONFIRMACION);
        System.out.println(SI);
        System.out.println(NO);
    }

    public static void menuSuperUsuario() {
        System.out.println(SELECCIONE);
        System.out.println("1. Crear superusuario");
        System.out.println("2. Registrar lector");
        System.out.println("3. Gestionar materiales");
        System.out.println("4. Gestionar préstamos");
        System.out.println("5. Registrar administrador");
        System.out.println("6. Consultar usuarios");
        System.out.println("7. Simulación de préstamos");
        System.out.println("8. Crear asistente"); // Nueva opción para crear un asistente
        System.out.println("9. Salir");
    }


    public static void menuImportExport() {
        System.out.println(SELECCIONE);
        System.out.println("1. Importar desde archivo CSV");
        System.out.println("2. Exportar a archivo CSV");
        System.out.println("3. Importar desde archivo XML");
        System.out.println("4. Exportar a archivo XML");
        System.out.println("5. Importar desde archivo JSON");
        System.out.println("6. Exportar a archivo JSON");
        System.out.println("7. " + SALIR);
    }



}
