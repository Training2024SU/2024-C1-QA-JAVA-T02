package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Administrador;
import co.com.ejercicio.modelo.Usuario;
import co.com.ejercicio.modeloAccesoBD.AdministradorAccesoBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;

import static co.com.ejercicio.menu.dialogos.MenuGestionPublicacion.menuPublicacion;
import static co.com.ejercicio.menu.dialogos.MenuGestionPublicacionNueva.menuPublicacionNueva;
import static co.com.ejercicio.menu.gestionMenu.GestionEmpleado.mostrarMenuGestionEmpleado;
import static co.com.ejercicio.menu.gestionMenu.GestionUsuario.mostrarMenuGestionUsuarios;
import static co.com.ejercicio.menu.gestionMenu.GestionPrestamo.mostrarMenuGestionPrestamo;

public class GestionAdministrador {
    public static void mostrarMenuAdministrador() {

    }

    public  static  void gestionarEmpleados(){
        System.out.println(" ");
        System.out.println("Gestionando Empleado...");
        mostrarMenuGestionEmpleado();
    }
    public static void gestionarUsuarios() {
        //gestionar usuarios
        System.out.println(" ");
        System.out.println("Gestionando usuarios...");
        mostrarMenuGestionUsuarios();
    }

    public static void gestionarPublicacion(){
         //gestionar libros
        System.out.println("Gestionando Publicaciones...");
        menuPublicacion();
    }

    public static void gestionarPublicacionNueva(){
        //gestionar libros
        System.out.println("Gestionando Publicaciones Nuevas...");
        menuPublicacionNueva();
    }



    public static void gestionarPrestamos() {
        //gestionar préstamos
        System.out.println("Gestionando préstamos...");
        mostrarMenuGestionPrestamo();
    }

    public static void gestionarModificarContrasenaAdministrador() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Modificando Contraseña...");
        System.out.println("Ingresa tu correo");
        String correo = scanner.nextLine();

        System.out.println("Ingresa tu contraseña");
        String contrasena = scanner.nextLine();

        Connection conexion = Conexion.obtenerConexion();
        AdministradorAccesoBD administradorAccesoBD = new AdministradorAccesoBD(conexion);
        List<Administrador> todosLosAdministradores = administradorAccesoBD.obetenerAdministradores();

        try {
            Administrador administrador = obtenerAdministradorPorSuCorreoYContrasena(todosLosAdministradores, correo, contrasena);

            System.out.println("Ingresa la clave nueva");
            String contrasenaNueva = scanner.nextLine();

            administradorAccesoBD.actualizarContrasena(administrador, contrasenaNueva);

        } catch (NoSuchElementException e){
            System.out.println("Administrador con correo y contraseña ingresados no existe");
        }
    }

    private static Administrador obtenerAdministradorPorSuCorreoYContrasena(List<Administrador> administradores, String correo, String contrasena){
        return administradores.stream()
                .filter(administrador -> correo.equals(administrador.getCorreo()))
                .filter(administrador -> contrasena.equals(administrador.getContrasenia()))
                .findFirst().orElseThrow();
    }
}
