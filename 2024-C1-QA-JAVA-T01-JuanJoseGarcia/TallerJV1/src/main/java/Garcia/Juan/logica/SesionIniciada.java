package Garcia.Juan.logica;

import Garcia.Juan.exporter_importer.CSVExporter;
import Garcia.Juan.database.mysql.MySqlOperation;
import Garcia.Juan.dialogo.MenuExporImport;
import Garcia.Juan.model.Usuario;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static Garcia.Juan.CRUD.CrudUsuario.getUsersFromTable;
import static Garcia.Juan.dialogo.Menu.*;
import static Garcia.Juan.logica.MetodosMain.pedirOpcion;
import static Garcia.Juan.logica.MetodosPrestamo.*;
import static Garcia.Juan.logica.MetodosProducto.*;
import static Garcia.Juan.logica.MetodosUsuario.*;
import static Garcia.Juan.util.Roles.*;

public class SesionIniciada {

    private static MySqlOperation mySqlOperation;

    public SesionIniciada(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    public static void usuarioIniciado(MySqlOperation mySqlOperation, List<String> credenciales) throws SQLException, ParseException {
        String correo = credenciales.get(0);
        String contrasena = credenciales.get(1);
        List<String> usuarioIniciado = new ArrayList<>();
        List<Usuario> usuarios = getUsersFromTable(mySqlOperation);
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena)) {
                usuarioIniciado.add(usuario.getCorreo());
                usuarioIniciado.add(usuario.getContrasena());
                usuarioIniciado.add(usuario.getNombre());
                usuarioIniciado.add(usuario.getRol());
                break;
            }
        }
        menuUsuarioIniciado(usuarioIniciado);
        accionesPorRol(usuarioIniciado.get(3),usuarioIniciado.get(0));
    }

    public static void accionesPorRol(String rol, String correo) throws SQLException, ParseException {
        boolean ciclo = true;
        while (ciclo) {
            int opcion;

            if (TIPO_TRES.getvalue().equals(rol)) { // LECTOR
                menuLector();
                opcion = pedirOpcion();
                switch (opcion) {
                    case 1:
                        verPublicaciones(mySqlOperation);
                        break;
                    case 2:
                        solicitarPrestamo(mySqlOperation, correo);
                        break;
                    case 3:
                        RegistroUsuarios.actualizarInformacionUsuario(mySqlOperation);
                        break;
                    case 4:
                        ciclo = false;
                        break;
                    default:
                        ciclo = false;
                        break;
                }
            } else if (TIPO_DOS.getvalue().equals(rol)) { // ASISTENTE
                menuAsistente();
                opcion = pedirOpcion();
                switch (opcion) {
                    case 1:
                        gestionarMaterial(mySqlOperation);
                        break;
                    case 2:
                        gestionarPrestamo(mySqlOperation);
                        break;
                    case 3:
                        RegistroUsuarios.actualizarInformacionUsuario(mySqlOperation);
                        break;
                    case 4:
                        MenuExporImport.menuImportExport();
                        break;
                    case 5:
                        ciclo = false;
                        break;
                    default:
                        ciclo = false;
                        break;
                }
            } else if (TIPO_UNO.getvalue().equals(rol)) { // ADMINISTRADOR
                menuAdmin();
                opcion = pedirOpcion();
                switch (opcion) {
                    case 1:
                        gestionarMaterial(mySqlOperation);
                        break;
                    case 2:
                        gestionarPrestamo(mySqlOperation);
                        break;
                    case 3:
                        RegistroUsuarios.actualizarInformacionUsuario(mySqlOperation);
                        break;
                    case 4:
                        RegistroUsuarios.crearLector(mySqlOperation);
                        break;
                    case 5:
                        MenuExporImport.menuImportExport();
                        break;
                    case 6:
                        ciclo = false;
                        break;
                    default:
                        ciclo = false;
                        break;
                }
            } else if (TIPO_CUATRO.getvalue().equals(rol)) { // SUPERUSUARIO
                menuSuperUsuario();
                opcion = pedirOpcion();
                switch (opcion) {
                    case 1:
                        RegistroUsuarios.registrarSuperUsuario(mySqlOperation);
                        break;
                    case 3:
                        gestionarMaterial(mySqlOperation);
                        break;
                    case 4:
                        gestionarPrestamo(mySqlOperation);
                        break;
                    case 5:
                        RegistroUsuarios.registrarAdministrador(mySqlOperation);
                        break;
                    case 6:
                        List<Usuario> usuarios = getUsersFromTable(mySqlOperation);
                        System.out.println(usuarios);
                        CSVExporter.exportToCSV(usuarios);
                        break;
                    case 7:
                        PrestamosPrueba.menuPrestamosPrueba();
                        break;
                    case 8:
                        RegistroUsuarios.crearAsistente(mySqlOperation);
                        break;
                    case 9:
                        RegistroUsuarios.actualizarInformacionUsuario(mySqlOperation);
                        break;
                    case 10:
                        MenuExporImport.menuImportExport();
                        break;
                    case 11:
                        ciclo = false;
                        break;
                    default:
                        ciclo = false;
                        break;
                }
            }
        }
    }

}



