package com.sofkau.logica.prestamo;

import com.sofkau.integration.repositorio.PrestamoVideograbacionRepositorio;
import com.sofkau.logica.videograbacion.VideoGrabacionOperaciones;

import java.util.Date;
import java.util.HashMap;

public class PrestamoVideoGrabacionOperaciones {

    private static HashMap<String, String> prestamosVideoGrabacion = new HashMap<>();

    public PrestamoVideoGrabacionOperaciones() {
        getPrestamosVideoGrabacion();
    }

    public void registrarPrestamoVideoGrabacion(String idVideoGrabacion, String fechaDevolucion, String correo) {
        String idPrestamo = PrestamoOperaciones.registrarPrestamoOtroMaterial(fechaDevolucion, correo);
        PrestamoVideograbacionRepositorio.crearPrestamoVideoGrabacion(idPrestamo, idVideoGrabacion);
        VideoGrabacionOperaciones.actualizarStockVideograbacion(false, idVideoGrabacion);
        prestamosVideoGrabacion.put(idPrestamo, idVideoGrabacion);
    }

    public void getPrestamosVideoGrabacion() {
        prestamosVideoGrabacion = PrestamoVideograbacionRepositorio.consultarPrestamosVideoGrabacion();
    }

    public static String getPrestamoVideograbacion(String idPrestamo) {
        return prestamosVideoGrabacion.get(idPrestamo);
    }
}
