package com.sofkau.logica.prestamo;

import com.sofkau.integration.repositorio.PrestamoCancionRepositorio;

import com.sofkau.logica.cancion.CancionOperaciones;



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

    public static String getPrestamoCancion(String idPrestamo) {
        return prestamosCancion.get(idPrestamo);
    }

}
