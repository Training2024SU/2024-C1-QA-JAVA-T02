package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.DAO.VideoDAO;
import co.com.pinguinera.datos.crud_local.CRUDVideosLocales;
import co.com.pinguinera.datos.model.publicaciones.Videograbaciones;
import co.com.pinguinera.servicios.integracion.SincronizadorVideos;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vista_videograbacion.InformacionVideograbacionVista;

import java.sql.SQLException;
import java.util.List;

public class ControladorCRUDVideograbaciones {
    private final InformacionVideograbacionVista vista;
    private final CRUDVideosLocales crudVideosLocales;
    private final VideoDAO videoDAO;
    private final SincronizadorVideos sincronizadorVideos;

    public ControladorCRUDVideograbaciones(InformacionVideograbacionVista vista, CRUDVideosLocales crudVideosLocales,
                                           VideoDAO videoDAO, SincronizadorVideos sincronizadorVideos) {
        this.vista = vista;
        this.crudVideosLocales = crudVideosLocales;
        this.videoDAO = videoDAO;
        this.sincronizadorVideos = sincronizadorVideos;
    }

    public void registrarVideo() {
        Videograbaciones nuevoVideo = new Videograbaciones();
        nuevoVideo.setTitulo(vista.pedirTituloVideo());
        nuevoVideo.setAutor(vista.pedirAutorVideo());
        nuevoVideo.setFormato(vista.pedirFormato());
        nuevoVideo.setCantEjemplares(vista.pedirCantEjemplares());
        nuevoVideo.setCantPrestados(vista.pedirCantPrestados());
        crudVideosLocales.agregar(nuevoVideo);
        try {
            videoDAO.insertar(nuevoVideo);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void actualizarVideo() {
        Videograbaciones videoActualizado = new Videograbaciones();
        videoActualizado.setTitulo(vista.pedirTituloVideo());
        videoActualizado.setAutor(vista.pedirAutorVideo());
        videoActualizado.setFormato(vista.pedirFormato());
        videoActualizado.setCantEjemplares(vista.pedirCantEjemplares());
        videoActualizado.setCantPrestados(vista.pedirCantPrestados());
        crudVideosLocales.actualizar(videoActualizado);
        try {
            videoDAO.actualizar(videoActualizado);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void eliminarVideo() {
        String titulo = vista.pedirTituloVideo();
        Videograbaciones videoAEliminar = new Videograbaciones();
        videoAEliminar.setTitulo(titulo);
        crudVideosLocales.eliminar(videoAEliminar);
        try {
            videoDAO.eliminar(videoAEliminar);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void obtenerTodosVideos() {

        try {

            List<Videograbaciones> videosBD = videoDAO.obtenerTodos();
            videosBD.forEach(System.out::println);

        } catch (SQLException e) {
            VistaUtil.mostrarMensajeSolicitudFallida();
        }
    }

    private void sincronizarDatos() {
        try {
            sincronizadorVideos.sincronizarVideos();
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
    }


}
