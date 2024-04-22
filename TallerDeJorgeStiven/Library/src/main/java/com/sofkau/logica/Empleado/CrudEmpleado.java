package com.sofkau.logica.Empleado;

import java.sql.SQLException;

import static com.sofkau.Main.mySqlOperation;
import static com.sofkau.dialogo.ConstantesMenu.MSJ_OPCION_NO_VALIDA;
import static com.sofkau.dialogo.Menus.*;
import static com.sofkau.logica.control.MetodosRol.*;
import static com.sofkau.util.PedirPorConsola.*;

public class CrudEmpleado {
    private static final String SELECT_CORREO_PASSWORD_FROM_EMPLEADO = "select * from mydb.empleado where Contrasena = '%s' and Correo = '%s';";

    public static void logearseEmpleado() throws SQLException {
        menuLoguearseEmpleado();
        System.out.print("Ingrese correo: ");
        String correo = pedirString();
        System.out.print("Ingrese contrasena: ");
        String contrasena = pedirString();
        int optionEmpleado;
        mySqlOperation.setSqlStatement(String.format(SELECT_CORREO_PASSWORD_FROM_EMPLEADO, contrasena, correo));
        mySqlOperation.executeSqlStatement();

        try {
            String valor = mySqlOperation.printColumnValue(5);

            if ("admin".equals(valor)) {
                System.out.println("eres Administrador");
                do{
                menuAdmin();
                optionEmpleado = pedirOpcion();
                seleccionarOpcionesAdmin(optionEmpleado);
                } while (optionEmpleado != 0);
            } else if ("asistente".equals(valor)) {
                System.out.println("eres asistente");
                do{
                menuAsistente();
                optionEmpleado = pedirOpcion();
                seleccionarOpcionesAsistente(optionEmpleado);
                } while (optionEmpleado != 0);
            } else if ("super".equals(valor)) {
                System.out.println("eres SuperAdministrador");
                do{
                menuSuper();
                optionEmpleado = pedirOpcion();
                seleccionarOpcionesSuper(optionEmpleado);
                } while (optionEmpleado != 0);
            } else {
                System.out.println(MSJ_OPCION_NO_VALIDA);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
