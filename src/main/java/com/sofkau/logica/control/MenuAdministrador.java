package com.sofkau.logica.control;

import com.sofkau.archivehandler.json.exportar.ExportaraCancionJson;
import com.sofkau.archivehandler.json.importar.ImportarCancionJson;
import com.sofkau.archivehandler.xml.exportar.ExportarCancionXML;
import com.sofkau.archivehandler.xml.importar.ImportarCancionXML;
import com.sofkau.dialogo.Menu;
import com.sofkau.model.Empleado;
import com.sofkau.util.enums.Roles;

import static com.sofkau.logica.control.ControlIngreso.*;

/*        Menu Administrador
        1. Crear propietario
        2. Crear asistente*/

public class MenuAdministrador {
    protected static void menuAdministrador(int op) {

        switch (op) {
            case 1 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();


                empleadoOp.registrarEmpleado(new Empleado(nombre,correo,contrasena), Roles.PROPIETARIO.toString());

            }
            case 2 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();

                empleadoOp.registrarEmpleado(new Empleado(nombre,correo,contrasena), Roles.ASISTENTE.toString());

            }case 3 -> {
         /*       ImportarCancionJson.guardarCancionJson();*/
                ImportarCancionXML.guardarCancionXml();
            }case 4 -> {
                /*ExportaraCancionJson.exportarCancionJson();*/
                ExportarCancionXML.exportarCancionXml();
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                option = 0;
            }

        }
    }
}
