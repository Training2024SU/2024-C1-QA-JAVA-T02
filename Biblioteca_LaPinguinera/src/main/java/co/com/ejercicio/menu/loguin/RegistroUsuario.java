package co.com.ejercicio.menu.loguin;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Usuario;
import co.com.ejercicio.modeloAccesoBD.UsuarioAccesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.INSERT_USUARIO;
import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.SELECT_REGISTRO_USUARIO;
import static co.com.ejercicio.menu.dialogos.MenuPrincipal.mostrarMenu;
import static co.com.ejercicio.principal.Main.logger;

public class RegistroUsuario {


    public static void registrarUsuario() {
        try (Connection conn = Conexion.obtenerConexion()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese su correo: ");
            String correo = scanner.nextLine();

            if (verificarUsuarioExistente(conn, correo)) {
                System.out.println("El usuario ya está registrado. Por favor, inicie sesión.");
                mostrarMenu();
            } else {
                System.out.println("Ingrese su nombre: ");
                String nombre = scanner.nextLine();
                System.out.println("Ingrese su contraseña: ");
                String contraseniaUsuario = scanner.nextLine();
                System.out.println("Ingrese su edad: ");
                int edad = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Ingrese su telefono: ");
                String telefono = scanner.nextLine();

                UsuarioAccesoBD usuarioAccesoBD = new UsuarioAccesoBD(conn);
                Usuario usuario = new Usuario(correo, nombre, contraseniaUsuario, edad, telefono);
                usuarioAccesoBD.agregarUsuario(usuario);
                logger.info("Usuario registrado correctamente");
                mostrarMenu();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean verificarUsuarioExistente(Connection conn, String correo) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(SELECT_REGISTRO_USUARIO)) {
            statement.setString(1, correo);
            try (ResultSet rs = statement.executeQuery()) {
                return rs.next(); // Retorna true si el usuario existe, false si no existe
            }
        }
    }

    public static void registrarUsuario(Connection conn, String correo, String nombre, String contrasenia, int edad, String telefono) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(INSERT_USUARIO)) {
            statement.setString(1, correo);
            statement.setString(2, nombre);
            statement.setString(3, contrasenia);
            statement.setInt(4, edad);
            statement.setString(5, telefono);
            statement.executeUpdate();
        }
    }
}




