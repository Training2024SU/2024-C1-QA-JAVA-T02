package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.logica.empleado.EmpleadoOperaciones;
import com.sofkau.logica.usuario.UsuarioOperaciones;
import com.sofkau.model.Usuario;

import java.util.Scanner;

import static com.sofkau.dialogo.Menu.menuPrincipal;
import static com.sofkau.logica.control.ControlIngreso.scannerGlobal;
import static com.sofkau.logica.control.ControlIngreso.usuarioOp;
import static com.sofkau.logica.control.ControlIngreso.option;
import static com.sofkau.logica.control.ControlIngreso.empleadoOp;
import static com.sofkau.logica.control.ControlIngreso.bandera;


public class InicioSesion {

  /*  Menu inicio
    Bienvenidos a la libreria pinguinera
    1. iniciar sesión como usuario
    2. iniciar sesión como empleado
    3. Registrarse
    4. Salir*/

    protected static void inicioSesion(int op) {
        switch (op) {
            case 1 -> {
                Menu.correo();
                String correo = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();


                if (usuarioOp.inicioSesion(correo, contrasena)) {
                    MenuUsuario.menuUsuario();
                } else {
                    option = 0;
                    System.out.println("Credenciales incorrectas por favor verifique");
                }
            }
            case 2 -> {
                Menu.correo();
                String correoEmp = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasenaEmp = scannerGlobal.nextLine();


                if (empleadoOp.inicioSesion(correoEmp, contrasenaEmp)) {
                    MenuEmpleado.menuEmpleado();
                } else {
                    option = 0;
                    System.out.println("Credenciales incorrectas por favor verifique");
                }

            } case 3 -> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
                Menu.correo();
                String correoU = scannerGlobal.nextLine();
                Menu.contrasena();
                String contrasenaU = scannerGlobal.nextLine();
                usuarioOp.registrarUsuario(new Usuario(nombre, correoU, contrasenaU));
                option = 0;

            }case 4 ->{
                bandera = false;
            }case 0 -> {
                option = 0;

            }default -> {
                System.out.println("ingrese una opcion válida");

            }

        }
    }
}
