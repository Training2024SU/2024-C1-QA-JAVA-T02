package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.DAO.CancionDAO;
import co.com.pinguinera.datos.crud_local.CRUDCancionesLocales;
import co.com.pinguinera.datos.model.publicaciones.Cancion;
import co.com.pinguinera.servicios.integracion.SincronizadorCanciones;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vista_cancion.InformacionCancionVista;

import java.sql.SQLException;
import java.util.List;

public class ControladorCRUDCanciones {
    private final InformacionCancionVista vista;
    private final CRUDCancionesLocales crudCancionesLocales;
    private final CancionDAO cancionDAO;
    private final SincronizadorCanciones sincronizadorCanciones;


    public ControladorCRUDCanciones(InformacionCancionVista vista, CRUDCancionesLocales crudCancionesLocales,
                                    CancionDAO cancionDAO, SincronizadorCanciones sincronizadorCanciones) {
        this.vista = vista;
        this.crudCancionesLocales = crudCancionesLocales;
        this.cancionDAO = cancionDAO;
        this.sincronizadorCanciones = sincronizadorCanciones;
    }

    public void registrarCancion() {
        Cancion nuevoCancion = new Cancion();
        nuevoCancion.setTitulo(vista.pedirTituloCancion());
        nuevoCancion.setAutor(vista.pedirAutorCancion());
        nuevoCancion.setFormato(vista.pedirFormato());
        nuevoCancion.setCantEjemplares(vista.pedirCantEjemplares());
        nuevoCancion.setCantPrestados(vista.pedirCantPrestados());
        crudCancionesLocales.agregar(nuevoCancion);
        try {
            cancionDAO.insertar(nuevoCancion);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void actualizarCancion() {
        Cancion cancionActualizado = new Cancion();
        cancionActualizado.setTitulo(vista.pedirTituloCancion());
        cancionActualizado.setAutor(vista.pedirAutorCancion());
        cancionActualizado.setFormato(vista.pedirFormato());
        cancionActualizado.setCantEjemplares(vista.pedirCantEjemplares());
        cancionActualizado.setCantPrestados(vista.pedirCantPrestados());
        crudCancionesLocales.actualizar(cancionActualizado);
        try {
            cancionDAO.actualizar(cancionActualizado);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void eliminarCancion() {
        String titulo = vista.pedirTituloCancion();
        Cancion cancionAEliminar = new Cancion();
        cancionAEliminar.setTitulo(titulo);
        crudCancionesLocales.eliminar(cancionAEliminar);
        try {
            cancionDAO.eliminar(cancionAEliminar);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void obtenerTodosCanciones() {

        try {

            List<Cancion> cancionBD = cancionDAO.obtenerTodos();
            cancionBD.forEach(System.out::println);

        } catch (SQLException e) {
            VistaUtil.mostrarMensajeSolicitudFallida();
        }
    }

    private void sincronizarDatos() {
        try {
            sincronizadorCanciones.sincronizarCanciones();
        } catch (SQLException e) {

        }
    }
}
