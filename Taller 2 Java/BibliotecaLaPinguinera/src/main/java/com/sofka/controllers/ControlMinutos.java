package com.sofka.controllers;

import static com.sofka.constants.InsertConstants.*;

public class ControlMinutos {

    public static String crearMinutos(String titulo, String crearMinutos) {
        return (String.format(INSERT_MINUTOS, titulo, crearMinutos));
    }


}
