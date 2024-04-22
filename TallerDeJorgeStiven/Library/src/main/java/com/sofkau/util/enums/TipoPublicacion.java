package com.sofkau.util.enums;

public enum TipoPublicacion {
    TIPO_UNO("Libro"),TIPO_DOS("Novela"), TIPO_TRES("Tesis");
    private String value;

    private TipoPublicacion (String tipo){
        this.value = tipo;
    }

    public String getvalue() {
        return value;
    }
}
