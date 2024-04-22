package com.sofkau.model;

public class VideoGrabacion {
    private String titulo;
    private String sinopsis;
    private String genero;
    private String autor;
    private int calificacion;
    private String tipo;
    private int cantidadCopia;
    private int cantidadPrestado;

    public VideoGrabacion(String titulo, String sinopsis, String genero, String autor, int calificacion, String tipo, int cantidadCopia, int cantidadPrestado) {
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.genero = genero;
        this.autor = autor;
        this.calificacion = calificacion;
        this.tipo = tipo;
        this.cantidadCopia = cantidadCopia;
        this.cantidadPrestado = cantidadPrestado;
    }

    public VideoGrabacion() {
    }

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
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

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        return "VideoGrabacion{" +
                "titulo='" + titulo + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", genero='" + genero + '\'' +
                ", autor='" + autor + '\'' +
                ", calificacion=" + calificacion +
                ", tipo='" + tipo + '\'' +
                ", cantidadCopia=" + cantidadCopia +
                ", cantidadPrestado=" + cantidadPrestado +
                '}';
    }
}
