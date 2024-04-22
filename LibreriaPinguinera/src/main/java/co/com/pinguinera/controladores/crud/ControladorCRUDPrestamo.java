package co.com.pinguinera.controladores.crud;

import co.com.pinguinera.datos.DAO.PrestamoDAO;
import co.com.pinguinera.datos.crud_local.CRUDPrestamosLocales;
import co.com.pinguinera.datos.model.Prestamo;
import co.com.pinguinera.servicios.integracion.SincronizadorPrestamos;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vistas_prestamo.InformacionPrestamoVista;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ControladorCRUDPrestamo {

    private final InformacionPrestamoVista vista;
    private final CRUDPrestamosLocales crudPrestamosLocales;
    private final PrestamoDAO prestamoDAO;
    private final SincronizadorPrestamos sincronizadorPrestamos;

    public ControladorCRUDPrestamo(InformacionPrestamoVista vista, CRUDPrestamosLocales crudPrestamosLocales,
                                   PrestamoDAO prestamoDAO, SincronizadorPrestamos sincronizadorPrestamos) {
        this.vista = vista;
        this.crudPrestamosLocales = crudPrestamosLocales;
        this.prestamoDAO = prestamoDAO;
        this.sincronizadorPrestamos = sincronizadorPrestamos;
    }

    public void registrarPrestamo() {
        Scanner scanner = new Scanner(System.in);
        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.setFechaPrestamo(vista.pedirFechaPrestamo());
        nuevoPrestamo.setFechaDevolucion(vista.pedirFechaDevolucion());
        nuevoPrestamo.setEstado(vista.crearEstadoPrestamo());
        nuevoPrestamo.setIdUsuario(vista.pedirIdUsuario());
        nuevoPrestamo.setIdPublicacion(vista.pedirIdPublicacion());
        System.out.println("Desea confirmar el prestamo (1 para si, 0 para no)");
        int option = Integer.parseInt(scanner.nextLine());
        if (option==1){
            crudPrestamosLocales.agregar(nuevoPrestamo);
        } else {
            System.out.println("Solicitud cancelada");
            return;
        }
        try {
            prestamoDAO.insertar(nuevoPrestamo);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void actualizarPrestamo() {
        Prestamo prestamoActualizado = new Prestamo();
        prestamoActualizado.setFechaPrestamo(vista.pedirFechaPrestamo());
        prestamoActualizado.setFechaDevolucion(vista.pedirFechaDevolucion());
        prestamoActualizado.setEstado(vista.pedirEstadoPrestamo());
        prestamoActualizado.setIdUsuario(vista.pedirIdUsuario());
        prestamoActualizado.setIdPublicacion(vista.pedirIdPublicacion());
        crudPrestamosLocales.actualizar(prestamoActualizado);
        try {
            prestamoDAO.actualizar(prestamoActualizado);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void eliminarPrestamo() {
        Prestamo prestamoAEliminar = new Prestamo();
        prestamoAEliminar.setIdPrestamo(vista.pedirIdPrestamo());
        crudPrestamosLocales.eliminar(prestamoAEliminar);
        try {
            prestamoDAO.eliminar(prestamoAEliminar);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void eliminarPrestamoSA() {
        Prestamo prestamoAEliminar = new Prestamo();
        //prestamoAEliminar.setIdPrestamo(vista.pedirIdPrestamo());
        //crudPrestamosLocales.eliminar(prestamoAEliminar);
        try {
            prestamoDAO.eliminarSA(prestamoAEliminar);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void eliminarPrestamosSuperad() {
        Prestamo prestamoAEliminar = new Prestamo();
        prestamoAEliminar.setIdPrestamo(1);
        crudPrestamosLocales.eliminar(prestamoAEliminar);
        try {
            prestamoDAO.eliminar(prestamoAEliminar);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
            return;
        }
        sincronizarDatos();
        VistaUtil.mostrarMensajeExito();
    }

    public void obtenerTodosPrestamos() {
        try {
            List<Prestamo> prestamos = prestamoDAO.obtenerTodos();
            prestamos.forEach(System.out::println);
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeSolicitudFallida();
        }
    }

    private void sincronizarDatos() {
        try {
            sincronizadorPrestamos.sincronizarPrestamos();
        } catch (SQLException e) {
            VistaUtil.mostrarMensajeError();
        }
    }
}
