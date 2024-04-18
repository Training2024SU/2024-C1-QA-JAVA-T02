package com.sofkau.model;

import java.util.Date;

public class Tesis {
    private String titulo;
    private Date fecha;
    private String autor;
    private String campoEstudio;
    private String pais;
    private int cantidadCopia;
    private int cantidadPrestado;

    // Getters y Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCampoEstudio() {
        return campoEstudio;
    }

    public void setCampoEstudio(String campoEstudio) {
        this.campoEstudio = campoEstudio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
}
