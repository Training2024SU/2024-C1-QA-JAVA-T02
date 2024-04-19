package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.logica.Autor.AutorOperaciones;
import com.sofkau.logica.cancion.CancionOperaciones;
import com.sofkau.logica.empleado.EmpleadoOperaciones;
import com.sofkau.logica.prestamo.PrestamoOperaciones;
import com.sofkau.logica.publicacion.PublicacionOperaciones;
import com.sofkau.logica.tesis.TesisOperaciones;
import com.sofkau.logica.usuario.UsuarioOperaciones;
import com.sofkau.logica.videograbacion.VideoGrabacionOperaciones;
import com.sofkau.util.generar.GenerarAutores;
import com.sofkau.util.generar.GenerarEmpleadoSuperAdmin;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import java.util.Scanner;


public class ControlIngreso {

    protected static boolean bandera = true;

    protected static Scanner scannerGlobal = new Scanner(System.in);

    protected static UsuarioOperaciones usuarioOp = new UsuarioOperaciones();

    protected static EmpleadoOperaciones empleadoOp = new EmpleadoOperaciones();

    protected static AutorOperaciones autorOp = new AutorOperaciones();

    protected static PublicacionOperaciones publicacionOp = new PublicacionOperaciones();

    protected static PrestamoOperaciones prestamoOp = new PrestamoOperaciones();

    protected static CancionOperaciones cancionOperaciones = new CancionOperaciones();

    protected static VideoGrabacionOperaciones videoGrabacionOperaciones = new VideoGrabacionOperaciones();

    protected static TesisOperaciones tesisOperaciones = new TesisOperaciones();

    protected static final Logger logger = Logger.getLogger(ControlIngreso.class.getName());

    protected static SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    protected static int option = 0;

    public ControlIngreso() {
    }


    // Se hace publico para ser el unico metodo que se pueda llamar desde el main
    public static void implementarLogica() {

        // Se generan autores autes de iniciar el programa
     /*   GenerarAutores.generateAutores(5);*/

        // Se genera el empleado administrador si no se encuentra registrado en la base de datos en base a los
        // Datos proporicionado en el .env
        GenerarEmpleadoSuperAdmin.generarEmpleadoAdministrador();

            while (bandera) {
                try {
                    switch (option) {
                        case 0 ->{
                            Menu.menuPrincipal();
                            String op = scannerGlobal.nextLine();
                            option = Integer.parseInt(op);
                            InicioSesion.inicioSesion(option);
                        }case 1 ->{
                            MenuUsuario.menuUsuario();
                        }case 2 ->{
                            MenuEmpleado.menuEmpleado();
                        }
                    }
            } catch (Exception e) {
                    option = 0;
                    logger.severe("Error por la razón: "+e.getMessage());
            }
        }
    }

}
