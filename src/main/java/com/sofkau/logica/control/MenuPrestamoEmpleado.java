package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.model.*;
import com.sofkau.util.CommonOperacion.GenerateUniqueId;
import com.sofkau.util.enums.EstadoPrestamo;
import com.sofkau.util.enums.Material;
import com.sofkau.util.enums.TipoPublicacion;

import java.text.ParseException;
import java.util.Date;

import static com.sofkau.logica.control.ControlIngreso.*;
import static com.sofkau.logica.control.ControlIngreso.option;

public class MenuPrestamoEmpleado {
    protected static void menuEntregaMaterial(int op) {
        Menu.IngresoIdPrestamo();
        String idPrestamo = scannerGlobal.nextLine();

        switch (op) {
            case 0-> {
                option = 0;
            }
            case 1 -> {
                prestamoOp.actualizarEstadoPrestamo(EstadoPrestamo.REALIZADO,idPrestamo, Material.PUBLICACION);
            }case 2 ->{
                prestamoOp.actualizarEstadoPrestamo(EstadoPrestamo.REALIZADO,idPrestamo, Material.PUBLICACION);
            }
            case 3 -> {
                prestamoOp.actualizarEstadoPrestamo(EstadoPrestamo.REALIZADO,idPrestamo, Material.CANCION);
            } case 4 -> {
                prestamoOp.actualizarEstadoPrestamo(EstadoPrestamo.REALIZADO,idPrestamo, Material.VIDEOGRABACION);
            } case 5 -> {
                prestamoOp.actualizarEstadoPrestamo(EstadoPrestamo.REALIZADO,idPrestamo, Material.TESIS);
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                option = 0;
            }

        }
    }

    protected static void menuDevolucionMaterial(int op) {
        Menu.IngresoIdPrestamo();
        String idPrestamo = scannerGlobal.nextLine();
        switch (op) {
            case 0-> {
                option = 0;
            }
            case 1, 2 -> {
                prestamoOp.actualizarEstadoPrestamo(EstadoPrestamo.FINALIZADO,idPrestamo, Material.PUBLICACION);
            }
            case 3 -> {
                prestamoOp.actualizarEstadoPrestamo(EstadoPrestamo.FINALIZADO,idPrestamo, Material.CANCION);
            } case 4 -> {
                prestamoOp.actualizarEstadoPrestamo(EstadoPrestamo.FINALIZADO,idPrestamo, Material.VIDEOGRABACION);
            } case 5 -> {
                prestamoOp.actualizarEstadoPrestamo(EstadoPrestamo.FINALIZADO,idPrestamo, Material.TESIS);
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                option = 0;
            }

        }
    }

}
