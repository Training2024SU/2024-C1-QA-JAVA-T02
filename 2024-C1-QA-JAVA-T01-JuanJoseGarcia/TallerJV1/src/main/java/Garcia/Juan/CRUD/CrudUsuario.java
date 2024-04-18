package Garcia.Juan.CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;

import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CrudUsuario {
    private static MySqlOperation mySqlOperation;

    private static final String GET_USERS = "SELECT correo, contrasenha, nombre, rol, telefono, direccion FROM bibliotecapingu.usuario";
    protected static final String INSERT_USER = "INSERT INTO bibliotecapingu.usuario (correo, contrasenha, nombre, rol, telefono, direccion) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')";
    protected static final String INSERT_ADMIN = "INSERT IGNORE INTO bibliotecapingu.usuario (correo, contrasenha, nombre, rol, telefono, direccion) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')";
    protected static final String UPDATE_USER = "UPDATE bibliotecapingu.usuario SET contrasenha = '%s', nombre = '%s', rol = '%s', telefono = '%s', direccion = '%s' WHERE correo = '%s'";

    public CrudUsuario(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public static List<Usuario> getUsersFromTable(MySqlOperation mySqlOperation) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            mySqlOperation.setSqlStatement(GET_USERS);
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setCorreo(resultSet.getString("correo"));
                usuario.setContrasena(resultSet.getString("contrasenha"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setRol(resultSet.getString("rol"));
                usuario.setTelefono(resultSet.getString("telefono"));
                usuario.setDireccion(resultSet.getString("direccion"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    public static void insertUser(MySqlOperation mySqlOperation, List<String> credenciales) {
        String correo = credenciales.get(0);
        String contrasena = credenciales.get(1);
        String nombre = credenciales.get(2);
        String rol = credenciales.get(3);
        String telefono = credenciales.get(4);
        String direccion = credenciales.get(5);

        String statement = String.format(INSERT_USER, correo, contrasena, nombre, rol, telefono, direccion);
        mySqlOperation.setSqlStatement(statement);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void crearAdmin(MySqlOperation mySqlOperation) {
        String correo = "administrador@pingu.com.co";
        String contrasena = "contrasenasegura123456";
        String nombre = "John Doe";
        String rol = "ADMINISTRADOR";
        String telefono = "1234567890";
        String direccion = "123 Calle Principal";

        String statement = String.format(INSERT_ADMIN, correo, contrasena, nombre, rol, telefono, direccion);
        mySqlOperation.setSqlStatement(statement);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void updateUser(MySqlOperation mySqlOperation, Usuario usuario) {
        String statement = String.format(UPDATE_USER, usuario.getContrasena(), usuario.getNombre(), usuario.getRol(),
                usuario.getTelefono(), usuario.getDireccion(), usuario.getCorreo());
        mySqlOperation.setSqlStatement(statement);
        mySqlOperation.executeSqlStatementVoid();
        System.out.println("Información del usuario actualizada.");
    }
}
