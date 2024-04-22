package com.sofka.constants;

import static com.sofka.constants.ConectorConstants.DATA_BASE_NAME;

public class SelectConstants {

    public static final String SELECT_ALL_FROM_PUBLICACION = String.format("select * from %s.publicacion;", DATA_BASE_NAME);
    public static final String SELECT_ALL_FROM_MULTIMEDIA= String.format("select * from %s.multimedia;", DATA_BASE_NAME);
    public static final String SELECT_ALL_FROM_NOVELA = String.format("select * from %s.publicacion where tipo='NOVELA';", DATA_BASE_NAME);
    public static final String SELECT_ALL_FROM_VIDEO = String.format("select * from %s.publicacion where tipo='VIDEO';", DATA_BASE_NAME);
    public static final String SELECT_ALL_FROM_CANCION = String.format("select * from %s.publicacion where tipo='CANCION';", DATA_BASE_NAME);
    public static final String SELECT_ALL_FROM_TESIS = String.format("select * from %s.publicacion where tipo='TESIS';", DATA_BASE_NAME);
    public static final String SELECT_ALL_FROM_LIBRO = String.format("select * from %s.publicacion where tipo='LIBRO';", DATA_BASE_NAME);
    public static final String SELECT_ALL_FROM_PRESTAMO = String.format("select * from %s.prestamo;", DATA_BASE_NAME);
    public static final String SELECT_ALL_FROM_EMPLEADO = String.format("select * from %s.empleado;", DATA_BASE_NAME);
    public static final String SELECT_ALL_FROM_USUARIO = String.format("select * from %s.usuario;", DATA_BASE_NAME);
    public static final String SELECT_ALL_FROM_AREAGENERO = String.format("select * from %s.areagenero;", DATA_BASE_NAME);
    public static final String SELECT_ALL_FROM_EDADSUGERIDA = String.format("select * from %s.edadsugerida;", DATA_BASE_NAME);
}