package co.com.pinguinera.vistas.vista_usuario;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.crud.*;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.Scanner;
import java.util.logging.Logger;

public class MenuPrincipalUsuario {

    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global desde LoggerUtil

    private final ControladorCRUDUsuario controladorCRUDUsuario;
    private final ControladorCRUDPrestamo controladorPrestamo;
    private final ControladorCRUDLibro controladorCRUDLibro;
    private final ControladorCRUDNovela controladorCRUDNovela;
    private final ControladorCRUDVideograbaciones controladorCRUDVideograbaciones;
    private final ControladorCRUDEnsayos controladorCRUDEnsayos;
    private final ControladorCRUDCanciones controladorCRUDCanciones;

    private final Scanner scanner;

    public MenuPrincipalUsuario(ControladorCRUDUsuario controladorCRUDUsuario,
                                ControladorCRUDPrestamo controladorPrestamo,
                                ControladorCRUDLibro controladorCRUDLibro,
                                ControladorCRUDNovela controladorCRUDNovela,
                                ControladorCRUDVideograbaciones controladorCRUDVideograbaciones,
                                ControladorCRUDEnsayos controladorCRUDEnsayos,
                                ControladorCRUDCanciones controladorCRUDCanciones) {
        this.controladorCRUDUsuario = controladorCRUDUsuario;
        this.controladorPrestamo = controladorPrestamo;
        this.controladorCRUDLibro = controladorCRUDLibro;
        this.controladorCRUDNovela = controladorCRUDNovela;
        this.controladorCRUDVideograbaciones = controladorCRUDVideograbaciones;
        this.controladorCRUDEnsayos = controladorCRUDEnsayos;
        this.controladorCRUDCanciones = controladorCRUDCanciones;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            LOGGER.info("\nMenú principal de usuario");
            LOGGER.info("1. Actualizar información de usuario");
            LOGGER.info("2. Realizar préstamo");
            LOGGER.info("3. Ver todos los libros");
            LOGGER.info("4. Ver todas las novelas");
            LOGGER.info("5. Ver todos los videos");
            LOGGER.info("6. Ver todas las canciones");
            LOGGER.info("7. Ver todos los ensayos (tesis)");
            LOGGER.info("8. Salir");

            int opcion = VistaUtil.obtenerOpcion();

            switch (opcion) {
                case 1:
                    controladorCRUDUsuario.actualizarUsuario();
                    break;
                case 2:
                    controladorPrestamo.registrarPrestamo();
                    break;
                case 3:
                    controladorCRUDLibro.obtenerTodosLibros();
                    break;
                case 4:
                    controladorCRUDNovela.obtenerTodasNovelas();
                    break;
                case 5:
                    controladorCRUDVideograbaciones.obtenerTodosVideos();
                    break;
                case 6:
                    controladorCRUDCanciones.obtenerTodosCanciones();
                    break;
                case 7:
                    controladorCRUDEnsayos.obtenerTodosEnsayos();
                    break;
                case 8:
                    LOGGER.info("Saliendo del menú...");
                    continuar = false;
                    break;
                default:
                    LOGGER.warning("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}
