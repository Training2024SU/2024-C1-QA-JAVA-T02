package Garcia.Juan.logica;

import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Usuario;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Garcia.Juan.util.VistaUtil;


import static Garcia.Juan.CRUD.CrudUsuario.*;
import static Garcia.Juan.logica.SesionIniciada.usuarioIniciado;

public class MetodosUsuario {

    private static MySqlOperation mySqlOperation;

    public MetodosUsuario(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public static void iniciarSesion(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor ingrese un correo electrónio:");//hacer metodo
        credenciales.add(scanner.nextLine());
        //credenciales.add("administrador@pingu.com.co");
        System.out.println("Por favor ingrese la contraseña:");//hacer metodo
        credenciales.add(scanner.nextLine());
        //credenciales.add("contrasenasegura123456 ");
        if (checkUser(mySqlOperation,credenciales)){
            usuarioIniciado(mySqlOperation,credenciales);
        }
        else {
            System.out.println("El correo o contraseña son incorrectos");
        }
    }

    public static boolean checkUser(MySqlOperation mySqlOperation,List<String> credenciales) throws SQLException {
        String correo = credenciales.get(0);
        String contrasena = credenciales.get(1);
        List<Usuario> usuarios = getUsersFromTable(mySqlOperation);
        boolean encontrado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                encontrado = true;
                break; //
            }
        }
        return encontrado;
    }

    public static List<String> registrarUsuario(MySqlOperation mySqlOperation, int admin) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor ingrese un correo electrónio:");
        credenciales.add(scanner.nextLine());
        System.out.println("Por favor ingrese la contraseña:");
        credenciales.add(scanner.nextLine());
        System.out.println("Por favor ingrese su nombre:");
        credenciales.add(scanner.nextLine());

        if (admin==0){
            credenciales.add("LECTOR");
        }
        else{
            credenciales.add("ASISTENTE");
        }
        if (checkUser(mySqlOperation,credenciales)){
            System.out.println("El usuario ya esta registrado");
        }
        else {
            insertUser(mySqlOperation,credenciales);
            usuarioIniciado(mySqlOperation,credenciales);
        }
        return credenciales;
    }

    public static List<String> registrarSuperUsuario(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();

        credenciales.add(VistaUtil.solicitarCorreoElectronico());
        credenciales.add(VistaUtil.solicitarContraseña());
        credenciales.add(VistaUtil.solicitarNombre());
        credenciales.add("SUPERUSUARIO");
        credenciales.add(VistaUtil.solicitarNumeroTelefono());
        credenciales.add(VistaUtil.solicitarDireccion());


        if (checkUser(mySqlOperation, credenciales)) {
            System.out.println("El superusuario ya está registrado.");
        } else {
            insertUser(mySqlOperation, credenciales);
            usuarioIniciado(mySqlOperation, credenciales);
            System.out.println("Superusuario creado exitosamente.");
        }

        return credenciales;
    }


    public static List<String> registrarAdministrador(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();

        credenciales.add(VistaUtil.solicitarCorreoElectronico());
        credenciales.add(VistaUtil.solicitarContraseña());
        credenciales.add(VistaUtil.solicitarNombre());
        credenciales.add("ADMINISTRADOR");
        credenciales.add(VistaUtil.solicitarNumeroTelefono());
        credenciales.add(VistaUtil.solicitarDireccion());

        if (checkUser(mySqlOperation, credenciales)) {
            System.out.println("El administrador ya está registrado.");
        } else {
            insertUser(mySqlOperation, credenciales);
            usuarioIniciado(mySqlOperation, credenciales);
            System.out.println("Administrador creado exitosamente.");
        }

        return credenciales;
    }


    public static List<String> crearLector(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();

        credenciales.add(VistaUtil.solicitarCorreoElectronico());
        credenciales.add(VistaUtil.solicitarContraseña());
        credenciales.add(VistaUtil.solicitarNombre());
        credenciales.add("LECTOR");
        credenciales.add(VistaUtil.solicitarNumeroTelefono());
        credenciales.add(VistaUtil.solicitarDireccion());

        if (checkUser(mySqlOperation, credenciales)) {
            System.out.println("El lector ya está registrado.");
        } else {
            insertUser(mySqlOperation, credenciales);
            System.out.println("Lector creado exitosamente.");
            usuarioIniciado(mySqlOperation, credenciales);
        }

        return credenciales;
    }

    public static List<String> crearAsistente(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();

        credenciales.add(VistaUtil.solicitarCorreoElectronico());
        credenciales.add(VistaUtil.solicitarContraseña());
        credenciales.add(VistaUtil.solicitarNombre());
        credenciales.add("ASISTENTE");
        credenciales.add(VistaUtil.solicitarNumeroTelefono());
        credenciales.add(VistaUtil.solicitarDireccion());

        if (checkUser(mySqlOperation, credenciales)) {
            System.out.println("El asistente ya está registrado.");
        } else {

            insertUser(mySqlOperation, credenciales);
            System.out.println("Asistente creado exitosamente.");
            usuarioIniciado(mySqlOperation, credenciales);
        }

        return credenciales;
    }


    public static void actualizarInformacionUsuario(MySqlOperation mySqlOperation) throws SQLException {
        // Pedir al usuario el correo electrónico del usuario que se quiere actualizar
        String correo = VistaUtil.solicitarCorreoElectronico();

        // Recuperar el usuario de la base de datos
        Usuario usuario = obtenerUsuarioPorCorreo(mySqlOperation, correo);

        // Verificar si el usuario existe
        if (usuario == null) {
            System.out.println("No se encontró un usuario con el correo electrónico especificado.");
            return;
        }

        // Solicitar los nuevos datos del usuario utilizando los métodos de VistaUtil
        System.out.println("Ingrese la nueva información del usuario:");
        usuario.setContrasena(VistaUtil.solicitarContraseña());
        usuario.setNombre(VistaUtil.solicitarNombre());
        usuario.setTelefono(VistaUtil.solicitarNumeroTelefono());
        usuario.setDireccion(VistaUtil.solicitarDireccion());

        // Llamar a updateUser para actualizar la información del usuario
        updateUser(mySqlOperation, usuario);
    }


    // Este método es un ejemplo para obtener un usuario por correo electrónico de la base de datos
    private static Usuario obtenerUsuarioPorCorreo(MySqlOperation mySqlOperation, String correo) throws SQLException {
        List<Usuario> usuarios = getUsersFromTable(mySqlOperation);
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return usuario;
            }
        }
        return null; // No se encontró el usuario
    }



}
