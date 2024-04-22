package com.sofkau.dialogo;

import com.sofkau.logica.publicacion.CrudLibro;
import com.sofkau.model.Publicacion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.sofkau.dialogo.ConstantesMenu.MSJ_OPCION_NO_VALIDA;
import static com.sofkau.logica.publicacion.CrudLibro.deserializarPublicacion;
import static com.sofkau.logica.publicacion.CrudLibro.findPublicacionByTitulo;
import static com.sofkau.util.PedirPorConsola.pedirOpcion;
import static com.sofkau.util.PedirPorConsola.pedirString;

public class PublicacionDialog {

    public static void logicaObtenerPublicacionPorTitulo() throws SQLException {
        System.out.println("Eligio obtener publicacion por titulo");
        System.out.print("Escriba el Titulo de la publicacion que desea obtener: ");
        String tituloToGet = pedirString();
        List<Publicacion> publicacionObtenida = findPublicacionByTitulo(tituloToGet);
        publicacionObtenida.forEach(publicacion -> System.out.println(publicacion));
    }

    public static void logicaObtenerPublicacionesPorAutor() throws SQLException {
        System.out.println("Eligio obtener publicaciones por autor");
        System.out.print("Escriba el autor de las publicaciones que desea obtener: ");
        String autorToGet = pedirString();
        List<Publicacion> publicacionesPorAutor = CrudLibro.getAllPublicacionesByAutor(autorToGet);
        publicacionesPorAutor.forEach(publicacion -> System.out.println(publicacion));
    }

    public static void logicaCrudPublicacion() throws SQLException, IOException, ClassNotFoundException {
        System.out.println("Publicacion");
        System.out.println("Eligio la seccion de publicaciones, selecione la operacion que desea realizar");
        System.out.println("1. Crear publicacion");
        System.out.println("2. Editar publicacion");
        System.out.println("3. Eliminar publicacion");
        System.out.println("4. Obtener publicacion por Titulo");
        System.out.println("5. Obtener publicacion por Autor");
        int operacionSeleccionada = pedirOpcion();
        switch (operacionSeleccionada) {
            case 1:
                System.out.println("Eligio crea publicacion");
                System.out.print("Escriba el Titulo: ");
                String titulo = pedirString();
                System.out.print("Escriba el Tipo (Libro,Novela,Tesis): ");
                String tipo = pedirString();
                System.out.print("Escriba el Autor: ");
                String autor = pedirString();
                System.out.print("Escriba el Numero de paginas: ");
                int numPaginas = pedirOpcion();
                System.out.print("Escriba el cantidad de ejemplares: ");
                int cantEjemplares = pedirOpcion();
                Publicacion publicacion = new Publicacion();
                publicacion.setTitulo(titulo);
                publicacion.setAutor(autor);
                publicacion.setTipo(tipo);
                publicacion.setNumeroPaginas(numPaginas);
                publicacion.setCantidadEjemplares(cantEjemplares);
                publicacion.setCantidadPrestado(0);
                CrudLibro.createPublicacion(publicacion);
                CrudLibro.serializarPublicacion(publicacion);
                deserializarPublicacion();
                break;
            case 2:
                System.out.println("Eligio editar publicacion");
                System.out.print("Escriba el Titulo de la publicacion que desea editar: ");
                String tituloEditar = pedirString();
                System.out.print("Escriba la cantidad nueva de prestados: ");
                int cantPrestadosEditar = pedirOpcion();
                System.out.print("Escriba la cantidad nueva de ejemplares: ");
                int cantEjemplaresEditar = pedirOpcion();
                CrudLibro.updatePublicacionByTitulo(cantEjemplaresEditar, cantPrestadosEditar, tituloEditar);
                break;
            case 3:
                System.out.println("Eligio eliminar publicacion");
                System.out.print("Escriba el Titulo de la publicacion que desea eliminar: ");
                String tituloEliminar = pedirString();
                CrudLibro.deletePublicacion(tituloEliminar);
                break;
            case 4:
                logicaObtenerPublicacionPorTitulo();
                break;
            case 5:
                logicaObtenerPublicacionesPorAutor();
                break;
            default:
                System.out.println(MSJ_OPCION_NO_VALIDA);
        }
    }
}
