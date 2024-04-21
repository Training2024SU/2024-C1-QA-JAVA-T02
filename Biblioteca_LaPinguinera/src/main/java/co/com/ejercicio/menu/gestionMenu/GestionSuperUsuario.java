package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Administrador;
import co.com.ejercicio.modeloAccesoBD.AdministradorAccesoBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.List;

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
        System.out.println("Ingrese el telefono");
        String telefono = scanner.nextLine();




        Connection conexion = Conexion.obtenerConexion();
        AdministradorAccesoBD administradorAccesoBD = new AdministradorAccesoBD(conexion);
        Administrador administrador = new Administrador(id, nombre,correo,contrasenia, departamentoAdministrado, telefono);
        administradorAccesoBD.insertarAdministrador(administrador);
    }

    public static void gestionModificarAdministrador() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Modificar administrador...");

        System.out.println("Ingrese el id a modificar");
        int idAdministrador = scanner.nextInt();
        scanner.nextLine();

        Connection conexion = Conexion.obtenerConexion();
        AdministradorAccesoBD administradorAccesoBD = new AdministradorAccesoBD(conexion);
        List<Administrador> administradores = administradorAccesoBD.obetenerAdministradores();

        try {
            Administrador administrador = obtenerAdminitradorPorSuId(administradores, idAdministrador);

            System.out.println("Ingresa los datos para actualizar la informacion");
            System.out.println("Ingrese el nombre nuevo");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el correo nuevo");
            String correo = scanner.nextLine();
            System.out.println("Ingrese el departamento administrado nuevo");
            String departamento = scanner.nextLine();
            System.out.println("Ingrese el telefono nuevo");
            String telefono = scanner.nextLine();
            System.out.println("Actualizando informacion de administrador...");

            administrador.setNombre(nombre);
            administrador.setCorreo(correo);
            administrador.setDepartamentoAdministrado(departamento);
            administrador.setTelefono(telefono);

            administradorAccesoBD.modificarAdministrador(administrador);

        } catch (NoSuchElementException e){
            System.out.println("Administrador con id: " +  idAdministrador + " no existe");
        }
    }


    public static void gestionarPrestamosSuperUsuario(){
        System.out.println("Gestionando préstamos...");
        mostrarMenuGestionPrestamoSuperUsuario();
    }

    private static Administrador obtenerAdminitradorPorSuId(List<Administrador> adminitradores, int id){
        return adminitradores.stream()
                .filter(administrador -> administrador.getId() == id)
                .findFirst().orElseThrow();
    }
}
