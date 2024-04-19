package co.com.pinguinera.servicios.integracion;

import co.com.pinguinera.datos.DAO.VideoDAO;
import co.com.pinguinera.datos.crud_local.CRUDVideosLocales;
import co.com.pinguinera.datos.model.publicaciones.Libro;
import co.com.pinguinera.datos.model.publicaciones.Videograbaciones;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SincronizadorVideos {
    private final VideoDAO videoDAO;
    private final CRUDVideosLocales crudVideosLocales;

    public SincronizadorVideos(VideoDAO videoDAO, CRUDVideosLocales crudLibrosLocales) {
        this.videoDAO = videoDAO;
        this.crudVideosLocales = crudLibrosLocales;
    }

    public void sincronizarVideos() throws SQLException {
        List<Videograbaciones> videosBD = videoDAO.obtenerTodos();
        List<Videograbaciones> videosLocales = crudVideosLocales.obtenerTodos();

        Map<Integer, Videograbaciones> mapaVideosLocales = new HashMap<>();
        for (Videograbaciones videoLocal : videosLocales) {
            mapaVideosLocales.put(videoLocal.getIdPublicacion(), videoLocal);
        }

        for (Videograbaciones videoBD : videosBD) {
            Videograbaciones videoLocal = mapaVideosLocales.get(videoBD.getIdPublicacion());
            if (videoLocal != null) {
                if (!videoLocal.equals(videoBD)) {
                    videoDAO.actualizar(videoLocal);
                }
                mapaVideosLocales.remove(videoLocal.getIdPublicacion());
            }
        }

        // Inserta libros locales que no están en la base de datos
        for (Videograbaciones videoLocal : mapaVideosLocales.values()) {
            videoDAO.insertar(videoLocal);
        }
    }
}
