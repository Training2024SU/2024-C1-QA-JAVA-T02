package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.DAO.CancionDAO;
import co.com.pinguinera.datos.crud_local.CRUDCancionesLocales;
import co.com.pinguinera.datos.model.publicaciones.Cancion;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorCanciones {
    private final CancionDAO cancionDAO;
    private final CRUDCancionesLocales crudCancionesLocales;

    public SincronizadorCanciones(CancionDAO cancionDAO, CRUDCancionesLocales crudCancionesLocales) {
        this.cancionDAO = cancionDAO;
        this.crudCancionesLocales = crudCancionesLocales;
    }

    public void sincronizarCanciones() throws SQLException {
        List<Cancion> cancionesBD = cancionDAO.obtenerTodos();
        List<Cancion> cancionesLocales = crudCancionesLocales.obtenerTodos();

        Map<Integer, Cancion> mapaCancionesLocales = new HashMap<>();
        for (Cancion cancionLocal : cancionesLocales) {
            mapaCancionesLocales.put(cancionLocal.getIdPublicacion(), cancionLocal);
        }

        for (Cancion cancionBD : cancionesBD) {
            Cancion cancionLocal = mapaCancionesLocales.get(cancionBD.getIdPublicacion());
            if (cancionLocal != null) {
                if (!cancionLocal.equals(cancionBD)) {
                    cancionDAO.actualizar(cancionLocal);
                }
                mapaCancionesLocales.remove(cancionLocal.getIdPublicacion());
            }
        }

        // Inserta libros locales que no están en la base de datos
        for (Cancion cancionLocal : mapaCancionesLocales.values()) {
            cancionDAO.insertar(cancionLocal);
        }
    }
}
