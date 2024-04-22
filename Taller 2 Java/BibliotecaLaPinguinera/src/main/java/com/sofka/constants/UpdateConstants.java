package com.sofka.constants;

public class UpdateConstants {
    public static final String UPDATE_PUBLICACION_REALIZADA = "UPDATE `publicacion` SET `cantPrestados` = cantPrestados+1, `cantDisponibles` = cantDisponibles-1 WHERE (`titulo` = '%s');";
    public static final String UPDATE_PUBLICACION_FINALIZADA = "UPDATE `publicacion` SET `cantPrestados` = cantPrestados-1, `cantDisponibles` = cantDisponibles+1 WHERE (`titulo` = '%s');";
    public static final String UPDATE_PRESTAMO = "UPDATE `prestamo` SET `estado` = '%s' WHERE `idPrestamo` = '%s';";
    public static final String UPDATE_USUARIO = "UPDATE `usuario` SET `nombre` = '%s', `contrasenha` = '%s' WHERE `correo` = '%s';";
    public static final String UPDATE_EMPLEADO= "UPDATE `empleado` SET `nombre` = '%s' , contrasenha = '%s' WHERE `idEmpleado` = '%s';";

}
