package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.util.enums.EstadoPrestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

    public class GestionConfirmarPrestamo {

        public static void confirmarPrestamo() throws SQLException {
            Connection conexion = Conexion.obtenerConexion();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el ID del préstamo que desea confirmar");
            int idPrestamo = scanner.nextInt();
            scanner.nextLine();
            String nuevoEstado = EstadoPrestamo.ESTADO_DOS.getvalue();

            // Consulta SQL para actualizar el estado del préstamo
            String consulta = "UPDATE prestamo SET estado = ? WHERE idPrestamo = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);{

                statement.setString(1, nuevoEstado); // Establecer el nuevo estado
                statement.setInt(2, idPrestamo); // Establecer el ID del préstamo

                int filasAfectadas = statement.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Se cambió el estado del préstamo correctamente.");
                } else {
                    System.out.println("No se encontró ningún préstamo con el ID especificado.");
                }
            }
        }
    }

