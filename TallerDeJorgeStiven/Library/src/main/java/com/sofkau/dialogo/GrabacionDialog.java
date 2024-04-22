package com.sofkau.dialogo;

import com.sofkau.model.Grabacion;

import java.sql.SQLException;
import java.util.List;

import static com.sofkau.dialogo.ConstantesMenu.MSJ_OPCION_NO_VALIDA;
import static com.sofkau.logica.grabacion.CrudGraba.*;
import static com.sofkau.util.PedirPorConsola.pedirOpcion;
import static com.sofkau.util.PedirPorConsola.pedirString;

public class GrabacionDialog {

    public static void logicaObtenerGrabacionPorTitulo() throws SQLException {
        System.out.println("Eligio obtener grabación por titulo");
        System.out.print("Escriba el Titulo de la grabacion que desea obtener: ");
        String tituloToGet = pedirString();
        List<Grabacion> grabacionObtenida = findGrabacionByTitulo(tituloToGet);
        grabacionObtenida.forEach(grabacion -> System.out.println(grabacion));
    }

    public static void logicaObtenerGrabacionesPorAutor() throws SQLException {
        System.out.println("Eligio obtener grabaciones por autor");
        System.out.print("Escriba el autor de las grabaciones que desea obtener: ");
        String autorToGet = pedirString();
        List<Grabacion> grabacionesPorAutor = getAllGrabacionesByAutor(autorToGet);
        grabacionesPorAutor.forEach(grabacion -> System.out.println(grabacion));
    }

    public static void logicaCrudGrabacion() throws SQLException {
        System.out.println("Grabación");
        System.out.println("Eligio la seccion de grabaciones, selecione la operacion que desea realizar");
        System.out.println("1. Crear grabacion");
        System.out.println("2. Editar grabacion");
        System.out.println("3. Eliminar grabacion");
        System.out.println("4. Obtener grabacion por Titulo");
        System.out.println("5. Obtener grabacion por Autor");
        int operacionSeleccionada = pedirOpcion();
        switch (operacionSeleccionada) {
            case 1:
                System.out.println("Eligio crea grabacion");
                System.out.print("Escriba el Titulo: ");
                String titulo = pedirString();
                System.out.print("Escriba el Tipo (Videograbación,Canción): ");
                String tipo = pedirString();
                System.out.print("Escriba el Autor: ");
                String autor = pedirString();
                System.out.print("Escriba la duracion: ");
                String numPaginas = pedirString();
                System.out.print("Escriba el cantidad de ejemplares: ");
                int cantEjemplares = pedirOpcion();
                Grabacion grabacion = new Grabacion();
                grabacion.setTitulo(titulo);
                grabacion.setAutor(autor);
                grabacion.setTipo(tipo);
                grabacion.setDuracion(numPaginas);
                grabacion.setCantidadEjemplares(cantEjemplares);
                grabacion.setCantidadPrestado(0);
                createGrabacion(grabacion);
                break;
            case 2:
                System.out.println("Eligio editar grabacion");
                System.out.print("Escriba el Titulo de la grabacion que desea editar: ");
                String tituloEditar = pedirString();
                System.out.print("Escriba la cantidad nueva de prestados: ");
                int cantPrestadosEditar = pedirOpcion();
                System.out.print("Escriba la cantidad nueva de ejemplares: ");
                int cantEjemplaresEditar = pedirOpcion();
                updateGrabacionByTitulo(cantEjemplaresEditar, cantPrestadosEditar, tituloEditar);
                break;
            case 3:
                System.out.println("Eligio eliminar grabacion");
                System.out.print("Escriba el Titulo de la grabacion que desea eliminar: ");
                String tituloEliminar = pedirString();
                deleteGrabacion(tituloEliminar);
                break;
            case 4:
                logicaObtenerGrabacionPorTitulo();
                break;
            case 5:
                logicaObtenerGrabacionesPorAutor();
                break;
            default:
                System.out.println(MSJ_OPCION_NO_VALIDA);
        }
    }
}
