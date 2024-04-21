package com.sofkau.logica.prestamo;

import com.sofkau.integration.repositorio.PrestamoRepositorio;
import com.sofkau.integration.repositorio.PrestamoTesisRepositorio;
import com.sofkau.logica.tesis.TesisOperaciones;
import com.sofkau.model.Prestamo;
import com.sofkau.util.CommonOperacion.GenerateUniqueId;
import com.sofkau.util.enums.EstadoPrestamo;

import java.util.Date;
import java.util.HashMap;

public class PrestamoTesisOperaciones {

    private static HashMap<String, String> prestamosTesis = new HashMap<>();

    public PrestamoTesisOperaciones() {
        getPrestamosTesis();
    }

    public void registrarPrestamoTesis(String idTesis, String fechaDevolucion, String correo) {
        String idPrestamo = PrestamoOperaciones.registrarPrestamoOtroMaterial(fechaDevolucion, correo);
        PrestamoTesisRepositorio.crearPrestamoTesis(idPrestamo, idTesis);
        TesisOperaciones.actualizarStockTesis(false, idTesis);
        prestamosTesis.put(idPrestamo, idTesis);
    }

    public void getPrestamosTesis() {
        prestamosTesis = PrestamoTesisRepositorio.consultarPrestamosTesis();
    }
}
