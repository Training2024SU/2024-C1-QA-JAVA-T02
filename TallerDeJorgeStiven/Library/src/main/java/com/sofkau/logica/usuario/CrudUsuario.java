package com.sofkau.logica.usuario;

import static com.sofkau.dialogo.UsuarioDialog.INSERT_USER;
import static com.sofkau.dialogo.UsuarioDialog.INSERT_USUARIO;
import static com.sofkau.logica.control.MetodosPrincipales.insertIntoDb;
import static com.sofkau.util.PedirPorConsola.pedirString;

public class CrudUsuario {
    public static void crearUsuarioSuper() {
        System.out.print("Ingrese el correo del usuario:");
        String correo = pedirString();
        System.out.print("Ingrese el nombre del usuario:");
        String nombre = pedirString();
        System.out.print("Ingrese la contraseña del usuario:");
        String contrasena = pedirString();
        System.out.print("Usuario creado exitosamente: " + nombre);
        insertIntoDb(String.format(INSERT_USUARIO, correo, nombre, contrasena));
        insertIntoDb(String.format(INSERT_USER, correo,"super"));
    }
}
