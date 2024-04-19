package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.VideoGrabacion;
import co.com.ejercicio.modeloAccesoBD.VideoGrabacionAccesoBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;
import static co.com.ejercicio.modeloAccesoBD.VideoGrabacionAccesoBD.obtenerVideoGrabacionesDeUnDirector;

public class GestionVideoGrabacion {
    public static void gestionInsertarVideoGrabacion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insertando video grabacion...");
        System.out.println("Ingresa Titulo");
        String titulo = scanner.nextLine();
        System.out.println("Ingresa director");
        String director = scanner.nextLine();
        System.out.println("Ingresa duracion");
        String duracion = scanner.nextLine();
        System.out.println("Ingresa cantidad de ejemplares");
        int cantidadEjemplares = scanner.nextInt();
        System.out.println("Ingresa cantidad prestado");
        int cantidadPrestado = scanner.nextInt();
        int cantidadDisponible = cantidadEjemplares - cantidadPrestado;

        Connection conexion = Conexion.obtenerConexion();
        VideoGrabacionAccesoBD videoGrabacionAccesoBD = new VideoGrabacionAccesoBD(conexion);
        videoGrabacionAccesoBD.insertarVideoGrabacion(new VideoGrabacion(titulo, director, duracion, cantidadEjemplares, cantidadPrestado, cantidadDisponible));
    }

    public static void gestionObtenerDirectorVideoGrabacion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el nombre del director:");
        String director = scanner.nextLine();
        try {
            Connection conexion = Conexion.obtenerConexion();
            VideoGrabacionAccesoBD videoGrabacionAccesoBD = new VideoGrabacionAccesoBD(conexion);
            List<VideoGrabacion> videoGrabaciones = obtenerVideoGrabacionesDeUnDirector(director);
            for (VideoGrabacion videoGrabacion : videoGrabaciones) {
                System.out.println(videoGrabacion);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las video grabaciones del director: " + e.getMessage());
        }
    }

    public static void gestionObtenerVideoGrabacion() {
        System.out.println("Obteniendo videograbaciones...");
        try {
            Connection conexion = Conexion.obtenerConexion();
            VideoGrabacionAccesoBD videoGrabacionAccesoBD = new VideoGrabacionAccesoBD(conexion);
            List<VideoGrabacion> videoGrabaciones = videoGrabacionAccesoBD.obtenerTodasLasVideoGrabaciones();
            for (VideoGrabacion videoGrabacion : videoGrabaciones) {
                System.out.println(videoGrabacion);
            }
        } catch (SQLException e) {
            System.out.println("Operación fallida: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }

    public static void gestionEliminarVideoGrabacion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el titulo de la videograbacion que quieres eliminar");
        String opcion = scanner.nextLine();
        System.out.println("Eliminando videograbacion...");
        try {
            Connection conexion = Conexion.obtenerConexion();
            VideoGrabacionAccesoBD videoGrabacionAccesoBD = new VideoGrabacionAccesoBD(conexion);
            videoGrabacionAccesoBD.eliminarVideoGrabacion(opcion);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public static void gestionActualizarVideograbacion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa los datos para actualizar");
        System.out.println("Ingresa el titulo");
        String titulo = scanner.nextLine();
        System.out.println("Ingresa el director");
        String director = scanner.nextLine();
        System.out.println("Ingresa la duracion");
        String duracion = scanner.nextLine();
        System.out.println("Ingresa la cantidad Ejemplares");
        int cantidadEjemplares = scanner.nextInt();
        System.out.println("Ingresa cantidad prestado");
        int cantidadPrestado = scanner.nextInt();
        int cantidadDisponible = cantidadEjemplares - cantidadPrestado;
        scanner.nextLine();
        System.out.println("Actualizando cancion...");

        Connection conexion = Conexion.obtenerConexion();
        VideoGrabacionAccesoBD videoGrabacionAccesoBD = new VideoGrabacionAccesoBD(conexion);
        videoGrabacionAccesoBD.actualizarVideoGrabacion(new VideoGrabacion (titulo, director, duracion,
                cantidadEjemplares, cantidadPrestado, cantidadDisponible));
    }
}
