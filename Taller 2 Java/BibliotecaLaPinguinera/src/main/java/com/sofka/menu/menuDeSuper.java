package com.sofka.menu;

import java.sql.SQLException;

import static com.sofka.constants.MenuConstants.MENU_SUPER;
import static com.sofka.controllers.ControlEmpleado.*;
import static com.sofka.controllers.ControlMenu.*;
import static com.sofka.controllers.ControlPrestamo.*;
import static com.sofka.controllers.ControlPublicacion.*;
import static com.sofka.controllers.ControlStament.insertarLista;
import static com.sofka.controllers.ControlUsuario.*;
import static com.sofka.util.JsonUtil.escribirPublicaciones;
import static com.sofka.util.JsonUtil.escribirUsuarios;

public class menuDeSuper {

        public static void menuSuper() throws NumberFormatException, SQLException {

           String opcion = pedirEntrada(MENU_SUPER);

            ejecutarMenuSuper(opcion);
        }

        private static void ejecutarMenuSuper (String opcion) throws SQLException {
        switch (opcion) {
            case "1":
                registrarNuevoUsuario();
                break;
            case "2":
                registrarNuevoEmpleado("ADMINISTRADOR");
                break;
            case "3":
                registrarNuevoEmpleado("ASISTENTE");
                break;
            case "4":
                insertarLista(registrarLibro());
                break;
            case "5":
                insertarLista(registrarNovela());
                break;
            case "6":
                do {
                    solicitarPrestamo();
                }while (!(preguntarRestaurar().equals("s")));
                    restaurar();
                break;
            case "7":
                selectAllFromUsuario();
                break;
            case "8":
                selectAllFromLibro();
                break;
            case "9":
                selectAllFromNovela();
                break;
            case "10":
                selectAllFromPrestamo();
                break;
            case "11":
                selectAllFromEmpleado();
                break;
            case "12":
                insertarUsuarioFaker(preguntarCantidadAlUsuario());
                break;
            case "13":
                insertarLibrosFaker(preguntarCantidadAlUsuario());
                break;
            case "14":
                insertarNovelaFaker(preguntarCantidadAlUsuario());
                break;
            case "15":
                insertarPrestamoFaker(preguntarCantidadAlUsuario());
                break;
            case "16":
                insertarEmpleadoFaker(preguntarCantidadAlUsuario());
                break;
            case "17":
                modificarUsuario();
            case "18":
                modificarEmpleado();
                break;
            case "19":
                escribirUsuarios();
                break;
            case "20":
                registrarVideo();
                break;
            case "21":
                registrarCacion();
                break;
            case "22":
                registrarTesis();
                break;
            case "23":
                selectAllFromVideo();
                break;
            case "24":
                selectAllFromCancion();
                break;
            case "25":
                selectAllFromTesis();
                break;
            case "26":
                escribirPublicaciones();
                break;
            case "0":
                mostrarMensaje("¡Cerrando Sesion!");
                break;
            default:
                mostrarMensaje("Opción incorrecta, por favor seleccione correctamente");
                break;
        }
            if (!(opcion.equals("0"))) menuSuper();
    }
}
