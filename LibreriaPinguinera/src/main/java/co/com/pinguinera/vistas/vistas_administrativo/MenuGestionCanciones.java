package co.com.pinguinera.vistas.vistas_administrativo;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.crud.ControladorCRUDCanciones;
import co.com.pinguinera.controladores.crud.ControladorCRUDVideograbaciones;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.logging.Logger;

public class MenuGestionCanciones {
    private static final Logger LOGGER = LoggerUtil.getLogger();
    private final ControladorCRUDCanciones controladorCRUDCanciones;

    public MenuGestionCanciones(ControladorCRUDCanciones controladorCRUDCanciones) {
        this.controladorCRUDCanciones = controladorCRUDCanciones;
    }

    public void mostrarMenu() {
        LOGGER.info("\nGestión de canciones");
        LOGGER.info("1. Agregar cancion");
        LOGGER.info("2. Actualizar cancion");
        LOGGER.info("3. Eliminar cancion");
        LOGGER.info("4. Ver todos los cancion");
        LOGGER.info("5. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDCanciones.registrarCancion();
                break;
            case 2:
                controladorCRUDCanciones.actualizarCancion();
                break;
            case 3:
                controladorCRUDCanciones.eliminarCancion();
                break;
            case 4:
                controladorCRUDCanciones.obtenerTodosCanciones();
                break;
            case 5:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }
}
