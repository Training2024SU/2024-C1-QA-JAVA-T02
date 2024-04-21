package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Usuario;
import co.com.ejercicio.modeloAccesoBD.UsuarioAccesoBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static co.com.ejercicio.menu.dialogos.MenuGestionPublicacionNuevaUsuario.menuPublicacionNuevaParaUsuario;
import static co.com.ejercicio.menu.dialogos.MenuGestionUsuario.interactuarConUsuario;
import static co.com.ejercicio.menu.gestionMenu.GestionPublicacion.mostrarMenuGestionPublicacion;

public class GestionUsuario {

    public static void mostrarMenuGestionUsuarios() {
        interactuarConUsuario();
    }

    public static void gestionInsertarUsuario() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insertando usuario...");

        System.out.println("Ingrese el correo");
        String correo = scanner.nextLine();
        System.out.println("Ingrese el nombre");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese contraseña");
        String contrasenia = scanner.nextLine();
        System.out.println("Ingrese edad");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese telefono");
        String telefono = scanner.nextLine();


        Connection conexion = Conexion.obtenerConexion();
        UsuarioAccesoBD usuarioAccesoBD =new UsuarioAccesoBD(conexion);
        Usuario usuario = new Usuario(correo, nombre, contrasenia, edad, telefono);
        usuarioAccesoBD.agregarUsuario(usuario);
    }

    public static void gestionObtenerUsuario() throws SQLException{
        System.out.println("Obteniendo usuario...");

        Connection conexion = Conexion.obtenerConexion();
        UsuarioAccesoBD usuarioAccesoBD = new UsuarioAccesoBD(conexion);
        List<Usuario> usuarios = usuarioAccesoBD.obtenerTodosLosUsuarios();
        for (Usuario usuario: usuarios){
            System.out.println(usuario);
        }
    }

    public static void gestionEliminarUsuario() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eliminando usuario...");
        System.out.println("Ingresa el correo del usuario a eliminar");
        String correoUsuario = scanner.nextLine();
        Connection conexion = Conexion.obtenerConexion();
        UsuarioAccesoBD usuarioAccesoBD = new UsuarioAccesoBD(conexion);
        usuarioAccesoBD.eliminarUsuario(correoUsuario);
    }

    public static void gestionActualizarUsuario() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Modificando usuario...");
        System.out.println("Ingresa el correo del usuario a modificar");
        String correoUsuario = scanner.nextLine();

        Connection conexion = Conexion.obtenerConexion();
        UsuarioAccesoBD usuarioAccesoBD = new UsuarioAccesoBD(conexion);
        List<Usuario> todosLosUsuarios = usuarioAccesoBD.obtenerTodosLosUsuarios();
        try {
            Usuario usuario = obtenerUsuarioPorSuCorreo(todosLosUsuarios, correoUsuario);

            System.out.println("Ingrese el nombre nuevo");
            String nombre = scanner.nextLine();

            System.out.println("Ingrese la edad");
            int edad = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese el telefono");
            String telefono = scanner.nextLine();

            usuario.setNombre(nombre);
            usuario.setEdad(edad);
            usuario.setTelefono(telefono);

            usuarioAccesoBD.actualizarUsuario(usuario);

        } catch (NoSuchElementException e){
            System.out.println("Usuario con correo: " + correoUsuario + " no existe");
        }
    }

    public static void gestionModificarContrasenaUsuario() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Modificando Contraseña...");
        System.out.println("Ingresa tu correo");
        String correoUsuario = scanner.nextLine();

        System.out.println("Ingresa tu contraseña");
        String contrasenaUsuario = scanner.nextLine();

        Connection conexion = Conexion.obtenerConexion();
        UsuarioAccesoBD usuarioAccesoBD = new UsuarioAccesoBD(conexion);
        List<Usuario> todosUsuarios = usuarioAccesoBD.obtenerTodosLosUsuarios();

        try {
            Usuario usuario = obtenerUsuarioPorSuCorreoYContrasena(todosUsuarios, correoUsuario, contrasenaUsuario);

            System.out.println("Ingresa la clave nueva");
            String contrasenaNueva = scanner.nextLine();

            usuarioAccesoBD.actualizarContrasena(usuario, contrasenaNueva);

        } catch (NoSuchElementException e){
            System.out.println("Usuario con correo y contraseña ingresados no existe");
        }

    }

    private static Usuario obtenerUsuarioPorSuCorreo(List<Usuario> usuarios, String correo){
        return usuarios.stream()
                .filter(usuario -> correo.equals(usuario.getCorreo()))
                .findFirst().orElseThrow();
    }

    private static Usuario obtenerUsuarioPorSuCorreoYContrasena(List<Usuario> usuarios, String correo, String contrasena){
        return usuarios.stream()
                .filter(usuario -> correo.equals(usuario.getCorreo()))
                .filter(usuario -> contrasena.equals(usuario.getContrasenia()))
                .findFirst().orElseThrow();
    }

    public static void gestionarPublicacionNuevaParaUsuario(){
        System.out.println("Visualizando publicaciones nuevas de la libreria...");
        menuPublicacionNuevaParaUsuario();
    }

}
