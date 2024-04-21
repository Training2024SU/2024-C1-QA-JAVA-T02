package com.sofkau.logica.prestamo;

import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.integration.repositorio.CancionRepositorio;
import com.sofkau.integration.repositorio.PrestamoCancionRepositorio;
import com.sofkau.integration.repositorio.PrestamoRepositorio;
import com.sofkau.logica.cancion.CancionOperaciones;
import com.sofkau.model.Prestamo;
import com.sofkau.util.CommonOperacion.GenerateUniqueId;
import com.sofkau.util.enums.EstadoPrestamo;

import java.util.Date;
import java.util.HashMap;

public class PrestamoCancionOperaciones {

    private static HashMap<String, String> prestamosCancion = new HashMap<>();

    public PrestamoCancionOperaciones() {
        getPrestamosCancion();
    }

    public void registrarPrestamoCancion (String idCancion, String fechaDevolucion, String correo){
        String idPrestamo = PrestamoOperaciones.registrarPrestamoOtroMaterial(fechaDevolucion,correo);
        PrestamoCancionRepositorio.crearPrestamoCancion(idPrestamo,idCancion);
        CancionOperaciones.actualizarStock(false,idCancion);
        prestamosCancion.put(idPrestamo,idCancion);
    }

    public void getPrestamosCancion(){
        prestamosCancion = PrestamoCancionRepositorio.consultarPrestamosCancion();
    }

}
