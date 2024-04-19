package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.EnsayoTesis;
import co.com.ejercicio.modelo.Publicacion;
import co.com.ejercicio.modeloAccesoBD.EnsayoTesisAccesoBD;
import co.com.ejercicio.modeloAccesoBD.PublicacionAccesoBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;
import static co.com.ejercicio.modeloAccesoBD.EnsayoTesisAccesoBD.obtenerEnsayoTesisDeUnAutor;

public class GestionEnsayoTesis {

    public static void gestionInsertarEnsayo() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insertando ensayo...");
        System.out.println("Ingresa Titulo");
        String titulo = scanner.nextLine();
        System.out.println("Ingresa autor");
        String autor = scanner.nextLine();
        System.out.println("Ingresa numero de paginas");
        int numeroPaginas = scanner.nextInt();
        System.out.println("Ingresa cantidad de ejemplares");
        int cantidadEjemplares = scanner.nextInt();
        System.out.println("Ingresa cantidad prestado");
        int cantidadPrestado = scanner.nextInt();
        int cantidadDisponible = cantidadEjemplares - cantidadPrestado;
        scanner.nextLine();

        Connection conexion = Conexion.obtenerConexion();
        EnsayoTesisAccesoBD ensayoTesisAccesoBD = new EnsayoTesisAccesoBD(conexion);
        ensayoTesisAccesoBD.insertarEnsayoTesis(new EnsayoTesis(titulo, autor, numeroPaginas,
                cantidadEjemplares, cantidadPrestado, cantidadDisponible));
    }

    public static void gestionObtenerAutoresEnsayo() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el nombre del autor");
        String autor = scanner.nextLine();
        Connection conexion = Conexion.obtenerConexion();
        EnsayoTesisAccesoBD ensayoTesisAccesoBD = new EnsayoTesisAccesoBD(conexion);
        List<EnsayoTesis> ensayosTesis = obtenerEnsayoTesisDeUnAutor(autor);
        for (EnsayoTesis ensayoTesis : ensayosTesis) {
            System.out.println(ensayoTesis);
        }
    }
    public static void gestionObtenerEnsayoTesis() throws SQLException {

        System.out.println("Obteniendo ensayo tesis...");
        try {
            Connection conexion = Conexion.obtenerConexion();
            EnsayoTesisAccesoBD ensayoTesisAccesoBD = new EnsayoTesisAccesoBD(conexion);
            List<EnsayoTesis> ensayosTesis = ensayoTesisAccesoBD.obtenerTodosLosEnsayosTesis();
            for (EnsayoTesis ensayoTesis : ensayosTesis) {
                System.out.println(ensayoTesis);
            }
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

        public static void gestionEliminarEnsayoTesis() throws SQLException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingresa el titulo del ensayo que quiere eliminar");
            String opcion = scanner.nextLine();
            System.out.println("Eliminando ensayo...");
            try {
                Connection conexion = Conexion.obtenerConexion();
                EnsayoTesisAccesoBD ensayoTesisAccesoBD = new EnsayoTesisAccesoBD(conexion);
                ensayoTesisAccesoBD.eliminarEnsayoTesis(opcion);
            } catch (SQLException e) {
                System.out.println(OPERACION_FALLIDA + e);
            }
        }

    public static void gestionActualizarEnsayoTesis() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa los datos para actualizar");
        System.out.println("Ingresa el titulo");
        String titulo = scanner.nextLine();
        System.out.println("ingresa el autor");
        String autor = scanner.nextLine();
        System.out.println("ingresa numero de paginas");
        int numeroPaginas = scanner.nextInt();
        System.out.println("ingresa cantidad Ejemplares");
        int cantidadEjemplares = scanner.nextInt();
        System.out.println("ingresa cantidad prestado");
        int cantidadPrestado = scanner.nextInt();
        int cantidadDisponible = cantidadEjemplares - cantidadPrestado;
        scanner.nextLine();
        System.out.println("Actualizando ensayoTesis...");

        Connection conexion = Conexion.obtenerConexion();
        EnsayoTesisAccesoBD ensayoTesisAccesoBD = new EnsayoTesisAccesoBD(conexion);
        ensayoTesisAccesoBD.actualizarEnsayoTesis(new EnsayoTesis (titulo, autor, numeroPaginas,
                cantidadEjemplares, cantidadPrestado, cantidadDisponible));
    }
}


