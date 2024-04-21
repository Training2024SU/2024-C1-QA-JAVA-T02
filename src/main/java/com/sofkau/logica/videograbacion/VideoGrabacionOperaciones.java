package com.sofkau.logica.videograbacion;

import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.integration.repositorio.VideoGrabacionRepositorio;
import com.sofkau.model.VideoGrabacion;
import com.sofkau.util.enums.Roles;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class VideoGrabacionOperaciones {
    static ArrayList<VideoGrabacion> listaVideoGrabaciones = new ArrayList<>();

    static SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public VideoGrabacionOperaciones() {
        getVideoGrabaciones();
    }

    public void crearVideoGrabacion(VideoGrabacion videoGrabacion) {
        VideoGrabacionRepositorio.crearVideoGrabacion(videoGrabacion);
        MensajeOperacionBd.crerVideoGrabacion();
        System.out.println(videoGrabacion);
        listaVideoGrabaciones.add(videoGrabacion);
    }

    public void getVideoGrabaciones() {
        listaVideoGrabaciones = VideoGrabacionRepositorio.consultarVideoGrabaciones();
    }

    public void mostrarVideoGrabaciones() {
        for (VideoGrabacion videoGrabacion : listaVideoGrabaciones) {
            if ((videoGrabacion.getCantidadCopia() - videoGrabacion.getCantidadPrestado()) > 0) {
                System.out.println(videoGrabacion);
            }
        }
    }

    public void mostrarVideoGrabaciones(Roles rol) {
        for (VideoGrabacion videoGrabacion : listaVideoGrabaciones) {
            System.out.println(videoGrabacion);
        }
    }

    public void actualizarVideoGrabacion(VideoGrabacion videoGrabacion) {
        // Actualizar en el repositorio
        VideoGrabacionRepositorio.actualizarVideoGrabacion(videoGrabacion);
    }

    public static VideoGrabacion getVideograbacionPorTitulo(String titulo) {
        return listaVideoGrabaciones.stream()
                .filter(video -> video.getTitulo().equals(titulo))
                .findFirst()
                .orElse(null);
    }

    public static void actualizarStockVideograbacion(boolean addStock, String titulo){
        VideoGrabacion videograbacion = getVideograbacionPorTitulo(titulo);
        if(videograbacion != null){
            if(addStock){
                videograbacion.setCantidadPrestado(videograbacion.getCantidadPrestado() - 1);
            } else {
                videograbacion.setCantidadPrestado(videograbacion.getCantidadPrestado() + 1);
            }
            VideoGrabacionRepositorio.actualizarVideoGrabacion(videograbacion);
            listaVideoGrabaciones.stream()
                    .filter(video -> video.getTitulo().equals(titulo))
                    .findFirst()
                    .ifPresent(video -> video.setCantidadPrestado(videograbacion.getCantidadPrestado()));
        }
    }


    public ArrayList<VideoGrabacion> getListaVideoGrabaciones() {
        return listaVideoGrabaciones;
    }
}
