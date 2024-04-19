package com.sofkau.logica.tesis;

import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.integration.repositorio.TesisRepositorio;
import com.sofkau.model.Tesis;
import com.sofkau.util.enums.Roles;

import java.util.ArrayList;

public class TesisOperaciones {
    ArrayList<Tesis> listaTesis = new ArrayList<>();

    public TesisOperaciones() {
        getTesis();
    }

    public void crearTesis(Tesis tesis) {
        TesisRepositorio.crearTesis(tesis);
        MensajeOperacionBd.crearTesis();
        System.out.println(tesis);
        listaTesis.add(tesis);
    }

    public void getTesis() {
        listaTesis = TesisRepositorio.consultarTesis();
    }

    public void mostrarTesis() {
        for (Tesis tesis : listaTesis) {
            System.out.println(tesis);
        }
    }

    public void mostrarTesis(Roles rol) {
        for (Tesis tesis : listaTesis) {
            System.out.println(tesis);
        }
    }

    public void actualizarTesis(Tesis tesis) {
        // Actualizar en el repositorio
        TesisRepositorio.actualizarTesis(tesis);
    }

    public ArrayList<Tesis> getListaTesis() {
        return listaTesis;
    }
}