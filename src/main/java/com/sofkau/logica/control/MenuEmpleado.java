package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;

import com.sofkau.dialogo.Menu;
import com.sofkau.util.enums.TipoPublicacion;

import static com.sofkau.logica.control.ControlIngreso.*;


public class MenuEmpleado {
    protected static void menuEmpleado()  {
        switch (empleadoOp.getEmpleadoActual().getRol()) {
            case "ADMINISTRADOR" -> {
                Menu.menuAdministrador();
                int op = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                MenuAdministrador.menuAdministrador(op);
            }
            case "ASISTENTE" -> {
                Menu.ingresoAsistente();
                int op = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                MenuAsistente.menuAsistente(op);
            }
            case "PROPIETARIO" -> {
                Menu.ingresoPropietario();
                int op = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                MenuPropietario.menuPropietario(op);
            }
            case "SUPERADMIN" -> {
                Menu.menuSuperAdmin();
                int op = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                MenuSuperAdmin.menuSuperAdmin(op);
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                option = 0;
            }

        }
    }

}
