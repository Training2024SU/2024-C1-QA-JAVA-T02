package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.model.Empleado;
import com.sofkau.model.PerfilModificable;

import static com.sofkau.logica.control.ControlIngreso.*;

public class MenuPerfilEmpleado {

    private static Empleado empleadoPerfil;
    protected static void menuPerfilEmpleado() {

        Menu.correo();
        String correo = scannerGlobal.nextLine();

        empleadoPerfil = empleadoOp.buscarEmpleadoPorCorreo(correo);

        Menu.menuPerfilUsuario();
        int op = scannerGlobal.nextInt();
        scannerGlobal.nextLine();

        switch (op) {
            case 0-> {
                option = 0;
            }
            case 1-> {
                perfilEmpleadoOp.getPerfil(empleadoPerfil.getId(),empleadoPerfil.getCorreo());
            }
            case 2-> {
                Menu.ingresoContacto();
                String contacto = scannerGlobal.nextLine();
                Menu.ingresoDireccio();
                String direccion = scannerGlobal.nextLine();

                perfilEmpleadoOp.ingresarPerfil(new PerfilModificable(empleadoPerfil.getId(),
                        contacto,direccion
                ));
            }
            case 3-> {
                Menu.ingresoContacto();
                String contacto = scannerGlobal.nextLine();
                Menu.ingresoDireccio();
                String direccion = scannerGlobal.nextLine();

                perfilEmpleadoOp.actualizarPerfil(new PerfilModificable(empleadoPerfil.getId(),
                        contacto,direccion
                ));
            }
            case 4-> {
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();
                empleadoPerfil.setContrasena(contrasena);
                empleadoOp.actualizarEmpleado(empleadoPerfil);
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                option = 0;
            }

        }
    }
}
