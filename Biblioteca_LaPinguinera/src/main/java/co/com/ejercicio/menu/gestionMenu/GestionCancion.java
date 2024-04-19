package co.com.ejercicio.menu.gestionMenu;
import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Cancion;
import co.com.ejercicio.modeloAccesoBD.CancionAccesoBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;
import static co.com.ejercicio.modeloAccesoBD.CancionAccesoBD.obtenerCancionDeUnArtista;

public class GestionCancion {
    public static void gestionInsertarCancion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insertando cancion...");
        System.out.println("Ingresa Titulo");
        String titulo = scanner.nextLine();
        System.out.println("Ingresa artista");
        String artista = scanner.nextLine();
        System.out.println("Ingresa album");
        String album = scanner.nextLine();
        System.out.println("Ingresa duracion");
        String duracion = scanner.nextLine();
        System.out.println("Ingresa cantidad de ejemplares");
        int cantidadEjemplares = scanner.nextInt();
        System.out.println("Ingresa cantidad prestado");
        int cantidadPrestado = scanner.nextInt();
        int cantidadDisponible = cantidadEjemplares - cantidadPrestado;

        Connection conexion = Conexion.obtenerConexion();
        CancionAccesoBD cancionAccesoBD = new CancionAccesoBD(conexion);
        cancionAccesoBD.insertarCancion(new Cancion(titulo, artista, album, duracion, cantidadEjemplares, cantidadPrestado, cantidadDisponible));
    }

    public static void gestionObtenerArtistaCancion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el nombre del artista:");
        String artista = scanner.nextLine();
        try {
            Connection conexion = Conexion.obtenerConexion();
            CancionAccesoBD cancionAccesoBD = new CancionAccesoBD(conexion);
            List<Cancion> canciones = obtenerCancionDeUnArtista(artista);
            for (Cancion cancion : canciones) {
                System.out.println(cancion);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener las canciones del artista: " + e.getMessage());
        }
    }

    public static void gestionObtenerCancion() {
    System.out.println("Obteniendo canciones...");
    try {
        Connection conexion = Conexion.obtenerConexion();
        CancionAccesoBD cancionAccesoBD = new CancionAccesoBD(conexion);
        List<Cancion> canciones = cancionAccesoBD.obtenerTodasLasCanciones();
        for (Cancion cancion : canciones) {
            System.out.println(cancion);
        }
    } catch (SQLException e) {
        System.out.println("Operación fallida: " + e.getClass().getSimpleName() + ": " + e.getMessage());
    }
}

    public static void gestionEliminarCancion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el titulo de la cancion que quieres eliminar");
        String opcion = scanner.nextLine();
        System.out.println("Eliminando cancion...");
        try {
            Connection conexion = Conexion.obtenerConexion();
            CancionAccesoBD cancionAccesoBD = new CancionAccesoBD(conexion);
            cancionAccesoBD.eliminarCancion(opcion);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public static void gestionActualizarCancion() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa los datos para actualizar");
        System.out.println("Ingresa el titulo");
        String titulo = scanner.nextLine();
        System.out.println("Ingresa el artista");
        String artista = scanner.nextLine();
        System.out.println("Ingresa el album");
        String album = scanner.nextLine();
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
        CancionAccesoBD cancionAccesoBD = new CancionAccesoBD(conexion);
        cancionAccesoBD.actualizarCancion(new Cancion (titulo, artista, album, duracion,
                cantidadEjemplares, cantidadPrestado, cantidadDisponible));
    }
}
