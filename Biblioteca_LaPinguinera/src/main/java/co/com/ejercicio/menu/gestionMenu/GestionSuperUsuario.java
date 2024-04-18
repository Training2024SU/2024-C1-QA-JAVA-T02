package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Administrador;
import co.com.ejercicio.modelo.Empleado;
import co.com.ejercicio.modeloAccesoBD.AdministradorAccesoBD;
import co.com.ejercicio.modeloAccesoBD.EmpleadoAccesoBD;
import co.com.ejercicio.util.enums.Roles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static co.com.ejercicio.menu.gestionMenu.GestionPrestamo.mostrarMenuGestionPrestamo;
import static co.com.ejercicio.menu.gestionMenu.GestionPrestamoSuperUsuario.mostrarMenuGestionPrestamoSuperUsuario;
import static co.com.ejercicio.menu.menuPrincipal.MenuGestionSuperUsuario.menuSuperUsuario;

public class GestionSuperUsuario {

    public static void mostrarMenuGestionSuperEUsuario(){
        menuSuperUsuario();
    }

    public static void gestionInsertarAdministrador() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insertando administrador...");

        System.out.println("Ingrese el id");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el nombre del administrador");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el correo");
        String correo = scanner.nextLine();
        System.out.println("Ingrese contraseña");
        String contrasenia = scanner.nextLine();
        System.out.println("Ingrese departamento que administra");
        String departamentoAdministrado = scanner.nextLine();



        Connection conexion = Conexion.obtenerConexion();
        AdministradorAccesoBD administradorAccesoBD = new AdministradorAccesoBD(conexion);
        Administrador administrador = new Administrador(id, nombre,correo,contrasenia, departamentoAdministrado);
        administradorAccesoBD.insertarAdministrador(administrador);

    }

    public static void gestionarPrestamosSuperUsuario(){
        System.out.println("Gestionando préstamos...");
        mostrarMenuGestionPrestamoSuperUsuario();
    }
}
