package com.sofkau.dialogo;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sofkau.Main.mySqlOperation;
import static com.sofkau.integration.database.mysql.Constantes.DATA_BASE_NAME;
import static com.sofkau.logica.control.MetodosPrincipales.insertIntoDb;
import static com.sofkau.util.PedirPorConsola.pedirString;

public class EmpleadoDialog {
    private static final String INSERT_ADMIN = "insert into mydb.Empleado values ('%s', '%s', '%s', '%s', 'admin');";
    private static final String INSERT_ASISTENTE = "insert into mydb.Empleado values ('%s', '%s', '%s', '%s', 'asistente');";
    public static final String SELECT_ALL_FROM_EMPLEADO = String.format("select * from %s.empleado;", DATA_BASE_NAME);
    public static final String UPDATE_EMPLEADO = "UPDATE empleado SET nombre = '%s', contrasena = '%s' WHERE correo = '%s';";

    public static void crearAsistente() {
        System.out.print("Ingrese el id del asistente:");
        String id_usuario = pedirString();

        System.out.print("Ingrese el nombre del asistente:");
        String nombre = pedirString();

        System.out.print("Ingrese el correo del asistente:");
        String correo = pedirString();

        System.out.print("Ingrese la contraseña del asistente:");
        String contrasena = pedirString();

        System.out.println("Empleado creado exitosamente");
        insertIntoDb(String.format(INSERT_ASISTENTE, id_usuario, nombre, correo, contrasena));
    }

    public static void crearAdministrador() {
        System.out.print("Ingrese el id del Admin:");
        String id_usuario = pedirString();

        System.out.print("Ingrese el nombre del Admin:");
        String nombre = pedirString();

        System.out.print("Ingrese el correo del Admin:");
        String correo = pedirString();

        System.out.print("Ingrese la contraseña del Admin:");
        String contrasena = pedirString();

        System.out.println("Empleado creado exitosamente");
        insertIntoDb(String.format(INSERT_ADMIN, id_usuario, nombre, correo, contrasena));
    }

    public static void modificarEmpleado() throws SQLException {
        System.out.print("Ingrese el correo: ");
        String correoEmpleado = pedirString();
        System.out.print("Ingrese el nuevo nombre: ");
        String nuevoNombre = pedirString();
        System.out.print("Ingrese la nueva contraseña: ");
        String nuevaContrasenha = pedirString();
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_EMPLEADO);
        mySqlOperation.executeSqlStatement();
        ResultSet empleado = mySqlOperation.getResulset();
        boolean encontrado = false;
        while (empleado.next()) {
            String correo = empleado.getString("Correo");
            if (correo.equals(correoEmpleado)) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            String updateStatement = String.format(UPDATE_EMPLEADO, nuevoNombre, nuevaContrasenha, correoEmpleado);
            mySqlOperation.setSqlStatement(updateStatement);
            mySqlOperation.executeSqlStatementVoid();
            System.out.println("Empleado actualizado exitosamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }
}
