package com.sofkau.logica.cancion;

import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.integration.repositorio.CancionRepositorio;
import com.sofkau.model.Cancion;
import com.sofkau.util.enums.Roles;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CancionOperaciones {
    ArrayList<Cancion> listaCanciones = new ArrayList<>();

    static SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public CancionOperaciones() {
        getCanciones();
    }

    public void crearCancion(Cancion cancion) {
        CancionRepositorio.crearCancion(cancion);
        MensajeOperacionBd.crearCancion();
        System.out.println(cancion);
        listaCanciones.add(cancion);
    }

    public void getCanciones() {
        listaCanciones = CancionRepositorio.consultarCanciones();
    }

    // Retorna todos los registros que posean el titulo de la canción
    public ArrayList<Cancion> getCancionesPorTitulo(String titulo) {
        return listaCanciones.stream()
                .filter(cancion -> cancion.getTitulo().equals(titulo))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void mostrarCanciones() {
            for (Cancion cancion : listaCanciones) {
                if((cancion.getCantidadCopia() - cancion.getCantidadPrestado())> 0){
                    System.out.println(cancion);
                }
            }
    }

    public void mostrarCanciones(Roles rol) {
        for (Cancion cancion : listaCanciones) {
            System.out.println(cancion);
        }
    }


    public void actualizarCancion(Cancion cancion) {
        // Actualizar en el repositorio
        CancionRepositorio.actualizarCancion(cancion);
    }
}
