package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.logica.Autor.AutorOperaciones;
import com.sofkau.logica.empleado.EmpleadoOperaciones;
import com.sofkau.logica.prestamo.PrestamoOperaciones;
import com.sofkau.logica.publicacion.PublicacionOperaciones;
import com.sofkau.logica.usuario.UsuarioOperaciones;
import com.sofkau.model.Empleado;
import com.sofkau.util.enums.Roles;

import static com.sofkau.logica.control.ControlIngreso.*;

public class MenuSuperAdmin {

    private static boolean modoSuperAdmin = false;

    public static boolean isModoSuperAdmin() {
        return modoSuperAdmin;
    }

    protected static void menuSuperAdmin(int op) {

        switch (op) {
            case 1 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();

                empleadoOp.registrarEmpleado(new Empleado(nombre, correo, contrasena), Roles.ADMINISTRADOR.toString());

            }
            case 2 -> {
                Menu.menuAdministrador();
                int opcion = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                MenuAdministrador.menuAdministrador(opcion);
            }
            case 3 -> {
                Menu.ingresoPropietario();
                int opcion = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                MenuPropietario.menuPropietario(opcion);
            }
            case 4 -> {
                Menu.ingresoAsistente();
                int opcion = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                MenuAsistente.menuAsistente(opcion);
            }
            case 5 -> {
                MenuUsuario.menuUsuario();
            }
            case 6 -> {
                if(modoSuperAdmin){
                    modoSuperAdmin = false;
                    restaurarValores();
                }else{
                    modoSuperAdmin = true;
                }
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                option = 0;
            }

        }

    }

    private static void restaurarValores() {
        usuarioOp = new UsuarioOperaciones();

        empleadoOp.getEmpleados();

        autorOp = new AutorOperaciones();

        publicacionOp = new PublicacionOperaciones();

        prestamoOp = new PrestamoOperaciones();
    }

}
