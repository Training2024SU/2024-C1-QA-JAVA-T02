package com.sofkau.dialogo;

import static com.sofkau.dialogo.ConstantesMenu.*;

public class Menus {

    public static void menuPrincipal() {
        System.out.println(MSN_SELECT_ROL);
        System.out.println(MSN_EMPLEADO);
        System.out.println(MSN_USUARIO);
        System.out.println(MSN_SALIR);
    }

    public static void menuSuper() {
        System.out.println(MSJ_SUPER);
        System.out.println(MSNE_PRESTAMO);
        System.out.println(MSNE_PUBLICACION);
        System.out.println(MSN_CREATE_PRESTAMO);
        System.out.println("4. Registrar Administrador");
        System.out.println("5. Registrar Asistente");
        System.out.println("6. Registrar Usuarios");
        System.out.println("7."+MSNE_GRABACION);
        System.out.println("8."+MSNE_PRESTAMO_G);
        System.out.println("9."+MSN_CREATE_PRESTAMO_G);
        System.out.println("10."+MSN_RESTAURAR_PRESTAMO);
        System.out.println("11."+MSN_RESTAURAR_PRESTAMO_G);
        System.out.println("12. Modificar Empleado");
        System.out.println("13. Modificar Usuario");
        System.out.println(MSN_SALIR);
    }

    public static void menuAdmin() {
        System.out.println(" eres admin");
        System.out.println("Que quieres hacer hoy?");
        System.out.println("1. Registrar Asistente");
        System.out.println("2. Registrar Usuarios ");
        System.out.println("3. Modificar Empleado");
        System.out.println(MSN_SALIR);
    }

    public static void menuAsistente() {
        System.out.println(MSNE_EMPLEADO);
        System.out.println(MSNE_PRESTAMO);
        System.out.println(MSNE_PUBLICACION);
        System.out.println("3. Modificar Empleado");
        System.out.println(MSN_SALIR);
    }

    public static void menuUsuario() {
        System.out.println(MSN_BIENVENIDA);
        System.out.println(MSN_USUARIO_BUSCAR_LIBRO_POR_TITULO);
        System.out.println(MSN_USUARIO_BUSCAR_LIBROS_POR_AUTOR);
        System.out.println(MSN_CREATE_PRESTAMO);
        System.out.println("4."+MSN_CREATE_PRESTAMO_G);
        System.out.println("5. Modificar Usuario");
        System.out.println("6. Crear Usuario");
        System.out.println(MSN_SALIR);
    }

    public static void menuLoguearseEmpleado() {
        System.out.println(MSNE_EMPLEADO);
        System.out.println(MSNE_INGRESE_CONTINUAR);
    }
}
