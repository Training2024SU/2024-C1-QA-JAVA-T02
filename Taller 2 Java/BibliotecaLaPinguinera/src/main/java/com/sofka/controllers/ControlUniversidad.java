package com.sofka.controllers;

import static com.sofka.constants.InsertConstants.INSERT_MINUTOS;
import static com.sofka.constants.InsertConstants.INSERT_UNIVERSIDAD;

public class ControlUniversidad {
    public static String crearUniversidad(String titulo, String Universidad) {
        return (String.format(INSERT_UNIVERSIDAD, titulo, Universidad));
    }
}
