package com.sofkau.model;

import java.io.Serializable;
import java.util.List;

public class Grabacion implements Serializable {
    private String titulo;
    private String tipo;
    private String autor;
    private String duracion;
    private int cantidadEjemplares;
    private int cantidadPrestado;
    private int cantidadDisponible;
    private List<AreaGenero> areas;
    private List<EdadSugerida> edades;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getCantidadEjemplares() {
        return cantidadEjemplares;
    }

    public void setCantidadEjemplares(int cantidadEjemplares) {
        this.cantidadEjemplares = cantidadEjemplares;
    }

    public int getCantidadPrestado() {
        return cantidadPrestado;
    }

    public void setCantidadPrestado(int cantidadPrestado) {
        this.cantidadPrestado = cantidadPrestado;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public List<AreaGenero> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaGenero> areas) {
        this.areas = areas;
    }

    public List<EdadSugerida> getEdades() {
        return edades;
    }

    public void setEdades(List<EdadSugerida> edades) {
        this.edades = edades;
    }

    @Override
    public String toString() {
        return "Publicacion{" +
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", autor='" + autor + '\'' +
                ", numeroPaginas=" + duracion +
                ", cantidadEjemplares=" + cantidadEjemplares +
                ", cantidadPrestado=" + cantidadPrestado +
                ", cantidadDisponible=" + cantidadDisponible +
                ", areas=" + areas +
                ", edades=" + edades +
                '}';
    }
}


