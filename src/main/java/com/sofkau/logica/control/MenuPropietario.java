package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.model.Empleado;
import com.sofkau.util.enums.Roles;
import com.sofkau.dialogo.Menu;
import com.sofkau.util.enums.TipoPublicacion;

import static com.sofkau.logica.control.ControlIngreso.*;


public class MenuPropietario {

/*    Bienvenidos a la libreria pinguinera
    1. Crear asistente*/

    protected static void menuPropietario(int op) {

        switch (op) {
            case 0-> {
                option = 0;
            }
            case 1 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();


                empleadoOp.registrarEmpleado(new Empleado(nombre,correo,contrasena), Roles.ASISTENTE.toString());

            }case 2 -> {


            }case 3 -> {

            } default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                option = 0;
            }

        }
    }
}
