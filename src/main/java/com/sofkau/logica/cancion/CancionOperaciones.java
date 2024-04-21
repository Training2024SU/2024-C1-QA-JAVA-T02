package com.sofkau.logica.cancion;

import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.integration.repositorio.CancionRepositorio;
import com.sofkau.model.Cancion;
import com.sofkau.util.enums.Roles;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CancionOperaciones {
    static ArrayList<Cancion> listaCanciones = new ArrayList<>();

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
    public static void actualizarCancion(Cancion cancion) {
        // Actualizar en el repositorio
        CancionRepositorio.actualizarCancion(cancion);
    }

    public static  void actualizarStock (boolean addStock, String titulo ){
        Cancion cancion = getCancionPorTitulo(titulo);
        if(addStock){
            cancion.setCantidadPrestado(cancion.getCantidadPrestado()-1);
        }else{
            cancion.setCantidadPrestado(cancion.getCantidadPrestado()+1);
        }
        if(cancion != null){
            actualizarCancion(cancion);
            // Utiliza un stream para buscar la canción por título y luego actualizarla
            listaCanciones.stream()
                    .filter(cancionn -> cancion.getTitulo().equals(titulo))
                    .findFirst()
                    .ifPresent(cancionn -> {
                        cancionn.setCantidadPrestado(cancion.getCantidadPrestado());
                    });
        }
    }

    public static Cancion getCancionPorTitulo(String titulo) {
        return listaCanciones.stream()
                .filter(cancion -> cancion.getTitulo().equals(titulo))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Cancion> getListacanciones() {
        return listaCanciones;
    }
}
