package com.sofkau.util.enums;

public enum Material {
    PUBLICACION,

    TESIS,

    CANCION,

    VIDEOGRABACION;

    public static String parseString(Material mat){
        return mat.toString();
    }
}


