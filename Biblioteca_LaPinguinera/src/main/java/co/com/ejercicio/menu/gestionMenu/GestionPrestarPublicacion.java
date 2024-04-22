package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Usuario;
import co.com.ejercicio.util.enums.EstadoPrestamo;
import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionPrestarPublicacion {

    public static void datosPrestamo() {

        Faker faker = new Faker();
        int id = faker.number().numberBetween(10,999);
        String estado = String.valueOf(EstadoPrestamo.ESTADO_UNO.getvalue());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el titulo de la publicacion que deseas prestar");
        String titulo = scanner.nextLine();
        System.out.println("Ingresa Nuevamente tu correo para confirmar la transaccion");
        String correo = scanner.nextLine();


        prestarLibro(id,estado,correo, titulo);

        try {
            descontarPublicacion( titulo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void prestarLibro(int id,String estado,String correo, String tituloPublicacion) {
        try (Connection conn = Conexion.obtenerConexion()) {
            // Verificar si hay ejemplares disponibles
            if (ejemplaresDisponibles(tituloPublicacion) > 0) {
                // Registrar el préstamo en la base de datos

                registrarPrestamo(conn,id,estado, correo, tituloPublicacion);
                System.out.println("El préstamo se realizó exitosamente.");
            } else {
                System.out.println("No hay ejemplares disponibles para este libro.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int ejemplaresDisponibles(String tituloPublicacion) throws SQLException {
        // Lógica para obtener la cantidad de ejemplares disponibles de la base de datos
        int cantidadDisponible = 0;
        try (Connection conn = Conexion.obtenerConexion()) {
            String sql = "SELECT cantidad_disponible FROM publicacion WHERE titulo = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, tituloPublicacion);
                try (var rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        cantidadDisponible = rs.getInt("cantidad_disponible");
                    }
                }
            }
        }
        return cantidadDisponible;

    }


    private static void registrarPrestamo(Connection conn, int id, String estado,String correoUsuario, String tituloPublicacion) throws SQLException {

        String sql = "INSERT INTO prestamo (idPrestamo,fecha_prestamo,estado, usuario_correo, publicacion_titulo) VALUES (?,NOW(),?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, estado);
            stmt.setString(3, correoUsuario);
            stmt.setString(4, tituloPublicacion);
            stmt.executeUpdate();
        }

    }

    public static void descontarPublicacion(String titulo) throws SQLException {
        String consultaActualizarCantidad = "UPDATE publicacion SET cantidad_disponible = cantidad_disponible - 1, cantidad_prestado = cantidad_prestado + 1 WHERE titulo = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(consultaActualizarCantidad)) {
            stmt.setString(1, titulo);
            stmt.executeUpdate();
        }

    }
}