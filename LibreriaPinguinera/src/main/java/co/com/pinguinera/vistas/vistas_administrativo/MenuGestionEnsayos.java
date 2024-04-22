package co.com.pinguinera.vistas.vistas_administrativo;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.crud.ControladorCRUDCanciones;
import co.com.pinguinera.controladores.crud.ControladorCRUDEnsayos;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;

import java.util.logging.Logger;

public class MenuGestionEnsayos {
    private static final Logger LOGGER = LoggerUtil.getLogger();
    private final ControladorCRUDEnsayos controladorCRUDEnsayos;

    public MenuGestionEnsayos(ControladorCRUDEnsayos controladorCRUDEnsayos) {
        this.controladorCRUDEnsayos = controladorCRUDEnsayos;
    }

    public void mostrarMenu() {
        LOGGER.info("\nGestión de ensayos");
        LOGGER.info("1. Agregar ensayos");
        LOGGER.info("2. Actualizar ensayos");
        LOGGER.info("3. Eliminar ensayos");
        LOGGER.info("4. Ver todos los ensayos");
        LOGGER.info("5. " + MenuConstantes.OPCION_VOLVER);

        int opcion = VistaUtil.obtenerOpcion();
        switch (opcion) {
            case 1:
                controladorCRUDEnsayos.registrarEnsayo();
                break;
            case 2:
                controladorCRUDEnsayos.actualizarEnsayo();
                break;
            case 3:
                controladorCRUDEnsayos.eliminarVideo();
                break;
            case 4:
                controladorCRUDEnsayos.obtenerTodosEnsayos();
                break;
            case 5:
                return;
            default:
                LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
        }
    }
}
