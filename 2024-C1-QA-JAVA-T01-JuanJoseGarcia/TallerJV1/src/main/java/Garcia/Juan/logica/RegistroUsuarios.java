package Garcia.Juan.logica;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Usuario;
import Garcia.Juan.util.VistaUtil;

import static Garcia.Juan.CRUD.CrudUsuario.*;
import static Garcia.Juan.logica.MetodosUsuario.checkUser;
import static Garcia.Juan.logica.SesionIniciada.usuarioIniciado;

/**
 * Clase que contiene métodos para registrar y administrar usuarios en la base de datos.
 */
public class RegistroUsuarios {

    /**
     * Registra un superusuario en la base de datos.
     *
     * @param mySqlOperation Objeto de MySqlOperation para realizar operaciones en la base de datos.
     * @return Lista con las credenciales del superusuario registrado.
     * @throws SQLException Si ocurre un error de base de datos.
     * @throws ParseException Si ocurre un error de análisis de datos.
     */
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

    /**
     * Registra un administrador en la base de datos.
     *
     * @param mySqlOperation Objeto de MySqlOperation para realizar operaciones en la base de datos.
     * @return Lista con las credenciales del administrador registrado.
     * @throws SQLException Si ocurre un error de base de datos.
     * @throws ParseException Si ocurre un error de análisis de datos.
     */
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

    /**
     * Registra un lector en la base de datos.
     *
     * @param mySqlOperation Objeto de MySqlOperation para realizar operaciones en la base de datos.
     * @return Lista con las credenciales del lector registrado.
     * @throws SQLException Si ocurre un error de base de datos.
     * @throws ParseException Si ocurre un error de análisis de datos.
     */
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
            // Insertar el lector en la base de datos
            insertUser(mySqlOperation, credenciales);
            usuarioIniciado(mySqlOperation, credenciales);
            System.out.println("Lector creado exitosamente.");
        }

        return credenciales;
    }

    /**
     * Registra un asistente en la base de datos.
     *
     * @param mySqlOperation Objeto de MySqlOperation para realizar operaciones en la base de datos.
     * @return Lista con las credenciales del asistente registrado.
     * @throws SQLException Si ocurre un error de base de datos.
     * @throws ParseException Si ocurre un error de análisis de datos.
     */
    public static List<String> crearAsistente(MySqlOperation mySqlOperation) throws SQLException, ParseException {
        List<String> credenciales = new ArrayList<>();

        // Solicitar credenciales al usuario
        credenciales.add(VistaUtil.solicitarCorreoElectronico());
        credenciales.add(VistaUtil.solicitarContraseña());
        credenciales.add(VistaUtil.solicitarNombre());
        credenciales.add("ASISTENTE");
        credenciales.add(VistaUtil.solicitarNumeroTelefono());
        credenciales.add(VistaUtil.solicitarDireccion());

        // Verificar si el asistente ya está registrado
        if (checkUser(mySqlOperation, credenciales)) {
            System.out.println("El asistente ya está registrado.");
        } else {
            // Insertar el asistente en la base de datos
            insertUser(mySqlOperation, credenciales);
            System.out.println("Asistente creado exitosamente.");
            usuarioIniciado(mySqlOperation, credenciales);
        }

        return credenciales;
    }

    /**
     * Actualiza la información de un usuario existente en la base de datos.
     *
     * @param mySqlOperation Objeto de MySqlOperation para realizar operaciones en la base de datos.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    public static void actualizarInformacionUsuario(MySqlOperation mySqlOperation) throws SQLException {
        String correo = VistaUtil.solicitarCorreoElectronico();

        Usuario usuario = obtenerUsuarioPorCorreo(mySqlOperation, correo);

        if (usuario == null) {
            System.out.println("No se encontró un usuario con el correo electrónico especificado.");
            return;
        }

        System.out.println("Ingrese su nueva información usuario:");
        usuario.setContrasena(VistaUtil.solicitarContraseña());
        usuario.setNombre(VistaUtil.solicitarNombre());
        usuario.setTelefono(VistaUtil.solicitarNumeroTelefono());
        usuario.setDireccion(VistaUtil.solicitarDireccion());

        updateUser(mySqlOperation, usuario);
    }

    /**
     * Obtiene un usuario de la base de datos por su correo electrónico.
     *
     * @param mySqlOperation Objeto de MySqlOperation para realizar operaciones en la base de datos.
     * @param correo Correo electrónico del usuario a buscar.
     * @return El usuario encontrado, o null si no se encuentra.
     * @throws SQLException Si ocurre un error de base de datos.
     */
    private static Usuario obtenerUsuarioPorCorreo(MySqlOperation mySqlOperation, String correo) throws SQLException {
        List<Usuario> usuarios = getUsersFromTable(mySqlOperation);

        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo)) {
                return usuario;
            }
        }
        return null;
    }
}
