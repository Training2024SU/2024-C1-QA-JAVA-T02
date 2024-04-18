package com.sofkau.model;

import java.util.Date;

public class Cancion {
    private String titulo;
    private String genero;
    private String autor;
    private String letra;
    private Date fechaLanzamiento;
    private int cantidadCopia;
    private int cantidadPrestado;

    public Cancion(String titulo, String genero, String autor, String letra, Date fechaLanzamiento, int cantidadCopia, int cantidadPrestado) {
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.letra = letra;
        this.fechaLanzamiento = fechaLanzamiento;
        this.cantidadCopia = cantidadCopia;
        this.cantidadPrestado = cantidadPrestado;
    }

    public Cancion() {
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getCantidadCopia() {
        return cantidadCopia;
    }

    public void setCantidadCopia(int cantidadCopia) {
        this.cantidadCopia = cantidadCopia;
    }

    public int getCantidadPrestado() {
        return cantidadPrestado;
    }

    public void setCantidadPrestado(int cantidadPrestado) {
        this.cantidadPrestado = cantidadPrestado;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", autor='" + autor + '\'' +
                ", letra='" + letra + '\'' +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", cantidadCopia=" + cantidadCopia +
                ", cantidadPrestado=" + cantidadPrestado +
                '}';
    }
}

