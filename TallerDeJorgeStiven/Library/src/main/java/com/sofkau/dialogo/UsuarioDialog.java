package com.sofkau.dialogo;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sofkau.Main.mySqlOperation;
import static com.sofkau.integration.database.mysql.Constantes.DATA_BASE_NAME;
import static com.sofkau.logica.control.MetodosPrincipales.insertIntoDb;
import static com.sofkau.util.PedirPorConsola.pedirString;

public class UsuarioDialog {
    public static final String INSERT_USUARIO = "insert into usuario values ('%s', '%s', '%s');";
    public static final String INSERT_USER = "insert into user values ('%s', '%s');";
    public static final String SELECT_ALL_FROM_USUARIO = String.format("select * from %s.usuario;", DATA_BASE_NAME);
    public static final String UPDATE_USUARIO = "UPDATE usuario SET nombre = '%s', contrasena = '%s' WHERE correo = '%s';";

    public static void crearUsuario() {
        System.out.print("Ingrese el correo del usuario:");
        String correo = pedirString();
        System.out.print("Ingrese el nombre del usuario:");
        String nombre = pedirString();
        System.out.print("Ingrese la contraseña del usuario:");
        String contrasena = pedirString();
        System.out.println("Usuario creado exitosamente: " + nombre);
        insertIntoDb(String.format(INSERT_USUARIO, correo, nombre, contrasena));
    }

    public static void modificarUsuario() throws SQLException {
        System.out.print("Ingrese el correo: ");
        String correoUsuario = pedirString();
        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = pedirString();
        System.out.print("Ingrese la nueva contraseña: ");
        String nuevaContrasenha = pedirString();
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_USUARIO);
        mySqlOperation.executeSqlStatement();
        ResultSet usuario = mySqlOperation.getResulset();
        boolean encontrado = false;
        while (usuario.next()) {
            String correo = usuario.getString("Correo");
            if (correo.equals(correoUsuario)) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            String updateStatement = String.format(UPDATE_USUARIO, nuevoNombre, nuevaContrasenha, correoUsuario);
            mySqlOperation.setSqlStatement(updateStatement);
            mySqlOperation.executeSqlStatementVoid();
            System.out.println("Usuario actualizado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
}
