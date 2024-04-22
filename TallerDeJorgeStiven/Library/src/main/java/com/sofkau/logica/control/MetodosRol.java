package com.sofkau.logica.control;

import com.sofkau.dialogo.PublicacionDialog;

import java.io.IOException;
import java.sql.SQLException;

import static com.sofkau.dialogo.ConstantesMenu.*;
import static com.sofkau.dialogo.EmpleadoDialog.*;
import static com.sofkau.dialogo.GrabacionDialog.logicaCrudGrabacion;
import static com.sofkau.dialogo.Menus.*;
import static com.sofkau.dialogo.PrestamoGrabaDialog.*;
import static com.sofkau.dialogo.PrestamoPubliDialog.*;
import static com.sofkau.dialogo.PublicacionDialog.logicaCrudPublicacion;
import static com.sofkau.dialogo.UsuarioDialog.crearUsuario;
import static com.sofkau.dialogo.UsuarioDialog.modificarUsuario;
import static com.sofkau.logica.Empleado.CrudEmpleado.logearseEmpleado;
import static com.sofkau.logica.usuario.CrudUsuario.crearUsuarioSuper;
import static com.sofkau.util.PedirPorConsola.pedirOpcion;

public class MetodosRol {
    public static void implementarLogica() throws SQLException {
        int option;
        do {
            menuPrincipal();
            option = pedirOpcion();
            seleccionarOpcion(option);
        } while (option != 0);
    }

    public static void implementarLogicaUsuario() throws SQLException {
        int optionUsuario;
        do {
            menuUsuario();
            optionUsuario = pedirOpcion();
            seleccionarOpcionesUsuario(optionUsuario);
        } while (optionUsuario != 0);
    }

    public static void implementarLogicaEmpleado() throws SQLException {
        logearseEmpleado();
    }

    private static void seleccionarOpcion(int option) throws SQLException {
        switch (option) {
            case 1:
                implementarLogicaEmpleado();
                break;
            case 2:
                implementarLogicaUsuario();
                break;
            case 0:
                System.out.println(MSJ_SALIENDO);
                break;
            default:
                System.out.println(MSJ_OPCION_NO_VALIDA);
        }
    }

    public static void seleccionarOpcionesUsuario(int optionUsuario) throws SQLException {
        switch (optionUsuario) {
            case 1:
                PublicacionDialog.logicaObtenerPublicacionPorTitulo();
                break;
            case 2:
                PublicacionDialog.logicaObtenerPublicacionesPorAutor();
                break;
            case 3:
                logicaParaHacerPrestamosUsuario();
                break;
            case 4:
                logicaParaHacerPrestamosGrabaUsuario();
                break;
            case 5:
                modificarUsuario();
                break;
            case 6:
                System.out.println("Ha seleccionado Crear usuario");
                crearUsuario();
                break;
            case 0:
                System.out.println(MSJ_SALIENDO);
                break;
            default:
                System.out.println(MSJ_OPCION_NO_VALIDA);
        }
    }


    public static void seleccionarOpcionesAsistente(int optionEmpleado) throws SQLException, IOException, ClassNotFoundException {
        switch (optionEmpleado) {
            case 1:
                logicaParaActualizarPrestamosEmpleados();
                break;
            case 2:
                logicaCrudPublicacion();
                break;
            case 3:
                modificarEmpleado();
                break;
            case 0:
                System.out.println(MSJ_SALIENDO);
                break;
            default:
                System.out.println(MSJ_OPCION_NO_VALIDA);
        }
    }

    public static void seleccionarOpcionesAdmin(int opcionAdmin) throws SQLException {
        switch (opcionAdmin) {
            case 1:
                System.out.println("Ha seleccionado Crear  Asistente");
                System.out.println(" Crear empleado nuevo");
                crearAsistente();
                break;
            case 2:
                System.out.println("Ha seleccionado Crear  usuario");
                System.out.println(" Crear usuario nuevo");
                crearUsuario();
                break;
            case 3:
                modificarEmpleado();
                break;
            case 0:
                System.out.println(MSJ_SALIENDO);
                break;
            default:
                System.out.println(MSJ_OPCION_NO_VALIDA);
        }
    }

    public static void seleccionarOpcionesSuper(int optionEmpleado) throws SQLException, IOException, ClassNotFoundException {
        switch (optionEmpleado) {
            case 0:
                System.out.println(MSN_SALIR);
                break;
            case 1:
                logicaParaActualizarPrestamosEmpleados();
                break;
            case 2:
                logicaCrudPublicacion();
                break;
            case 3:
                logicaParaHacerPrestamosUsuario();
                break;
            case 4:
                System.out.println("Ha seleccionado Crear  Administrador");
                System.out.println(" Crear empleado nuevo");
                crearAdministrador();
                break;
            case 5:
                System.out.println("Ha seleccionado Crear  Asistente");
                System.out.println(" Crear empleado nuevo");
                crearAsistente();
                break;
            case 6:
                System.out.println("Ha seleccionado Crear  usuario");
                System.out.println(" Crear usuario nuevo");
                crearUsuarioSuper();
                break;
            case 7:
                logicaCrudGrabacion();
                break;
            case 8:
                logicaParaActualizarPrestamosGrabaEmpleados();
                break;
            case 9:
                logicaParaHacerPrestamosGrabaUsuario();
                break;
            case 10:
                eliminarPrestamo();
                break;
            case 11:
                eliminarPrestamoGraba();
                break;
            case 12:
                modificarEmpleado();
                break;
            case 13:
                modificarUsuario();
                break;
            default:
                System.out.println(MSJ_OPCION_NO_VALIDA);
        }
    }
}
