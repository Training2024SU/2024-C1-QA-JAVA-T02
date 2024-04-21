package co.com.ejercicio.menu.gestionMenu;

import co.com.ejercicio.conexionBd.Conexion;
import co.com.ejercicio.modelo.Empleado;
import co.com.ejercicio.modeloAccesoBD.EmpleadoAccesoBD;
import co.com.ejercicio.modeloAccesoBD.UsuarioAccesoBD;
import co.com.ejercicio.util.enums.Roles;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static co.com.ejercicio.menu.dialogos.MenuGestionEmpleado.menuEmpleado;

public class GestionEmpleado {

    public static void mostrarMenuGestionEmpleado() {
        menuEmpleado();
    }

    public static void gestionInsertarEmpleado() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insertando empleado...");

        System.out.println("Ingrese el id");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el nombreEmpleado");
        String nombreEmpleado = scanner.nextLine();
        System.out.println("Ingrese el correo");
        String correo = scanner.nextLine();
        System.out.println("Ingrese contraseña");
        String contrasenia = scanner.nextLine();
        String rol = Roles.TIPO_DOS.getvalue();
        System.out.println("Ingrese la edad");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el telefono");
        String telefono = scanner.nextLine();



        Connection conexion = Conexion.obtenerConexion();
        EmpleadoAccesoBD empleadoAccesoBD =new EmpleadoAccesoBD(conexion);
        Empleado empleado = new Empleado(id, nombreEmpleado,correo,contrasenia, rol, edad, telefono);
        empleadoAccesoBD.insertarEmpleado(empleado);
    }

    public static void gestionObtenerEmpleado() throws SQLException{

        System.out.println("Obteniendo empleados...");

        Connection conexion = Conexion.obtenerConexion();
        EmpleadoAccesoBD empleadoAccesoBD = new EmpleadoAccesoBD(conexion);
        List<Empleado> empleados = empleadoAccesoBD.obtenerTodosLosEmpleados();
        for (Empleado empleado: empleados){
            System.out.println(empleado);
        }
    }

    public static void gestionEliminarEmpleado() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Eliminando usuario...");
        System.out.println("Ingresa el correo del usuario a eliminar");
        String correoUsuario = scanner.nextLine();
        Connection conexion = Conexion.obtenerConexion();
        UsuarioAccesoBD usuarioAccesoBD = new UsuarioAccesoBD(conexion);
        usuarioAccesoBD.eliminarUsuario(correoUsuario);
    }

    public static void gestionActualizarEmpleado() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Modificando empleado...");
        System.out.println("Ingresa el correo del empleado a modificar");
        String correoEmpleado = scanner.nextLine();


        Connection conexion = Conexion.obtenerConexion();
        EmpleadoAccesoBD empleadoAccesoBD = new EmpleadoAccesoBD(conexion);
        List<Empleado> listadoEmpleados = empleadoAccesoBD.obtenerTodosLosEmpleados();

        try {
            Empleado empleado = obtenerUsuarioPorSuCorreo(listadoEmpleados, correoEmpleado);

            System.out.println("Ingrese el nombre nuevo");
            String nombre = scanner.nextLine();
            System.out.println("Ingrese el correo nuevo");
            String correo = scanner.nextLine();

            System.out.println("Ingrese la edad");
            int edad = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ingrese el telefono");
            String telefono = scanner.nextLine();

            empleado.setNombre(nombre);
            empleado.setCorreo(correo);
            empleado.setEdad(edad);
            empleado.setTelefono(telefono);

            empleadoAccesoBD.modificarEmpleado(empleado);

        } catch (NoSuchElementException e){
            System.out.println("Empleado con correo: " + correoEmpleado + " no existe");
        }

    }

    public static void gestionarModificarContrasenaEmpleado() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Modificando Contraseña...");
        System.out.println("Ingresa tu correo");
        String correoEmpleado = scanner.nextLine();

        System.out.println("Ingresa tu contraseña");
        String contrasenaEmpleado = scanner.nextLine();

        Connection conexion = Conexion.obtenerConexion();
        EmpleadoAccesoBD empleadoAccesoBD = new EmpleadoAccesoBD(conexion);
        List<Empleado> todosLosEmpleados = empleadoAccesoBD.obtenerTodosLosEmpleados();

        try {
            Empleado empleado = obtenerUsuarioPorSuCorreoYContrasena(todosLosEmpleados, correoEmpleado, contrasenaEmpleado);

            System.out.println("Ingresa la clave nueva");
            String contrasenaNueva = scanner.nextLine();

            empleadoAccesoBD.actualizarContrasena(empleado, contrasenaNueva);

        } catch(NoSuchElementException e){
            System.out.println("Empleado con correo y contraseña ingresados no existe");
        }

    }

    private static Empleado obtenerUsuarioPorSuCorreo(List<Empleado> empleados, String correo){
        return empleados.stream()
                .filter(empleado -> correo.equals(empleado.getCorreo()))
                .findFirst().orElseThrow();
    }

    private static Empleado obtenerUsuarioPorSuCorreoYContrasena(List<Empleado> empleados, String correo, String contrasena){
        return empleados.stream()
                .filter(empleado -> correo.equals(empleado.getCorreo()))
                .filter(empleado -> contrasena.equals(empleado.getContrasenia()))
                .findFirst().orElseThrow();
    }
}

