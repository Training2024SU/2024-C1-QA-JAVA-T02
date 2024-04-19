package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.DAO.EnsayoDAO;
import co.com.pinguinera.datos.crud_local.CRUDEnsayosLocales;
import co.com.pinguinera.datos.model.publicaciones.Ensayos;
import co.com.pinguinera.datos.model.publicaciones.Videograbaciones;
import co.com.pinguinera.servicios.integracion.SincronizadorEnsayos;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vista_ensayos.InformacionEnsayosVista;

import java.sql.SQLException;
import java.util.List;

public class ControladorCRUDEnsayos {
    private final InformacionEnsayosVista vista;
    private final CRUDEnsayosLocales crudEnsayosLocales;
    private final EnsayoDAO ensayoDAO;
    private final SincronizadorEnsayos sincronizadorEnsayos;


    public ControladorCRUDEnsayos(InformacionEnsayosVista vista, CRUDEnsayosLocales crudEnsayosLocales,
                                  EnsayoDAO ensayoDAO, SincronizadorEnsayos sincronizadorEnsayos) {
        this.vista = vista;
        this.crudEnsayosLocales = crudEnsayosLocales;
        this.ensayoDAO = ensayoDAO;
        this.sincronizadorEnsayos = sincronizadorEnsayos;
    }

    public void registrarEnsayo() {
        Ensayos nuevoEnsayo = new Ensayos();
        nuevoEnsayo.setTitulo(vista.pedirTituloEnsayo());
        nuevoEnsayo.setAutor(vista.pedirAutorEnsayo());
        nuevoEnsayo.setFormato(vista.pedirFormato());
        nuevoEnsayo.setCantEjemplares(vista.pedirCantEjemplares());
        nuevoEnsayo.setCantPrestados(vista.pedirCantPrestados());
        crudEnsayosLocales.agregar(nuevoEnsayo);
        try {
            ensayoDAO.insertar(nuevoEnsayo);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void actualizarEnsayo() {
        Ensayos ensayoActualizado = new Ensayos();
        ensayoActualizado.setTitulo(vista.pedirTituloEnsayo());
        ensayoActualizado.setAutor(vista.pedirAutorEnsayo());
        ensayoActualizado.setFormato(vista.pedirFormato());
        ensayoActualizado.setCantEjemplares(vista.pedirCantEjemplares());
        ensayoActualizado.setCantPrestados(vista.pedirCantPrestados());
        crudEnsayosLocales.actualizar(ensayoActualizado);
        try {
            ensayoDAO.actualizar(ensayoActualizado);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void eliminarVideo() {
        String titulo = vista.pedirTituloEnsayo();
        Ensayos ensayoAEliminar = new Ensayos();
        ensayoAEliminar.setTitulo(titulo);
        crudEnsayosLocales.eliminar(ensayoAEliminar);
        try {
            ensayoDAO.eliminar(ensayoAEliminar);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void obtenerTodosEnsayos() {

        try {

            List<Ensayos> ensayosBD = ensayoDAO.obtenerTodos();
            ensayosBD.forEach(System.out::println);

        } catch (SQLException e) {
            VistaUtil.mostrarMensajeSolicitudFallida();
        }
    }

    private void sincronizarDatos() {
        try {
            sincronizadorEnsayos.sincronizarEnsayos();
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
    }

}
