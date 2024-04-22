package co.com.pinguinera.vistas.vistas_administrativo;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.crud.ControladorCRUDLibro;
import co.com.pinguinera.controladores.crud.ControladorCRUDVideograbaciones;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.logging.Logger;

public class MenuGestionVideos {
    private static final Logger LOGGER = LoggerUtil.getLogger();
    private final ControladorCRUDVideograbaciones controladorCRUDVideograbaciones;

    public MenuGestionVideos(ControladorCRUDVideograbaciones controladorCRUDVideograbaciones) {
        this.controladorCRUDVideograbaciones = controladorCRUDVideograbaciones;
    }

    public void mostrarMenu() {
        LOGGER.info("\nGestión de videograbaciones");
        LOGGER.info("1. Agregar video");
        LOGGER.info("2. Actualizar video");
        LOGGER.info("3. Eliminar video");
        LOGGER.info("4. Ver todos los videos");
        LOGGER.info("5. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDVideograbaciones.registrarVideo();
                break;
            case 2:
                controladorCRUDVideograbaciones.actualizarVideo();
                break;
            case 3:
                controladorCRUDVideograbaciones.eliminarVideo();
                break;
            case 4:
                controladorCRUDVideograbaciones.obtenerTodosVideos();
                break;
            case 5:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }
}
