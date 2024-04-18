package Garcia.Juan.logica;

import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Usuario;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor ingrese un correo electrónico para el superusuario:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese la contraseña para el superusuario:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese el nombre del superusuario:");
        credenciales.add(scanner.nextLine());

        // Solicitar número de teléfono
        System.out.println("Por favor ingrese su número de teléfono:");
        credenciales.add(scanner.nextLine());

        // Solicitar dirección
        System.out.println("Por favor ingrese su dirección:");
        credenciales.add(scanner.nextLine());

        // Añadir el rol de SUPERUSUARIO a las credenciales
        credenciales.add("SUPERUSUARIO");

        // Verificar si el usuario ya está registrado
        if (checkUser(mySqlOperation, credenciales)) {
            System.out.println("El superusuario ya está registrado.");
        } else {
            // Insertar el superusuario en la base de datos
            insertUser(mySqlOperation, credenciales);
            // Iniciar sesión del superusuario (si es necesario)
            usuarioIniciado(mySqlOperation, credenciales);
            System.out.println("Superusuario creado exitosamente.");
        }

        return credenciales;
    }

    public static List<String> registrarAdministrador(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor ingrese un correo electrónico para el administrador:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese la contraseña para el administrador:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese el nombre del administrador:");
        credenciales.add(scanner.nextLine());

        // Solicitar número de teléfono
        System.out.println("Por favor ingrese su número de teléfono:");
        credenciales.add(scanner.nextLine());

        // Solicitar dirección
        System.out.println("Por favor ingrese su dirección:");
        credenciales.add(scanner.nextLine());

        // Asignar rol de ADMINISTRADOR a las credenciales
        credenciales.add("ADMINISTRADOR");

        // Verificar si el administrador ya está registrado
        if (checkUser(mySqlOperation, credenciales)) {
            System.out.println("El administrador ya está registrado.");
        } else {
            // Insertar el administrador en la base de datos
            insertUser(mySqlOperation, credenciales);
            // Iniciar sesión del administrador (si es necesario)
            usuarioIniciado(mySqlOperation, credenciales);
            System.out.println("Administrador creado exitosamente.");
        }

        return credenciales;
    }


    public static List<String> crearLector(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor ingrese un correo electrónico para el lector:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese la contraseña:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese su nombre:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese su número de teléfono:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese su dirección:");
        credenciales.add(scanner.nextLine());

        // Asignar el rol de LECTOR
        credenciales.add("LECTOR");

        // Verificar si el usuario ya está registrado
        if (checkUser(mySqlOperation, credenciales)) {
            System.out.println("El lector ya está registrado.");
        } else {
            // Insertar el lector en la base de datos
            insertUser(mySqlOperation, credenciales);
            System.out.println("Lector creado exitosamente.");
            // Iniciar sesión automáticamente
            usuarioIniciado(mySqlOperation, credenciales);
        }

        return credenciales;
    }

    public static List<String> crearAsistente(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor ingrese un correo electrónico para el asistente:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese la contraseña:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese su nombre:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese su número de teléfono:");
        credenciales.add(scanner.nextLine());

        System.out.println("Por favor ingrese su dirección:");
        credenciales.add(scanner.nextLine());

        // Asignar el rol de ASISTENTE
        credenciales.add("ASISTENTE");

        // Verificar si el usuario ya está registrado
        if (checkUser(mySqlOperation, credenciales)) {
            System.out.println("El asistente ya está registrado.");
        } else {
            // Insertar el asistente en la base de datos
            insertUser(mySqlOperation, credenciales);
            System.out.println("Asistente creado exitosamente.");
            // Iniciar sesión automáticamente
            usuarioIniciado(mySqlOperation, credenciales);
        }

        return credenciales;
    }


}
