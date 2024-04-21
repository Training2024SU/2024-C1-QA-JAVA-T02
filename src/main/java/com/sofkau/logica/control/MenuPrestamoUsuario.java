package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.logica.prestamo.PrestamoCancionOperaciones;
import com.sofkau.logica.prestamo.PrestamoTesisOperaciones;
import com.sofkau.logica.prestamo.PrestamoVideoGrabacionOperaciones;
import com.sofkau.util.enums.TipoPublicacion;

import static com.sofkau.logica.control.ControlIngreso.*;

public class MenuPrestamoUsuario {
    private static PrestamoCancionOperaciones prestCancionOp = new PrestamoCancionOperaciones();
    private static PrestamoVideoGrabacionOperaciones prestVideoGrabacionOp = new PrestamoVideoGrabacionOperaciones();
    private static PrestamoTesisOperaciones prestamoTesisOp = new PrestamoTesisOperaciones();

    public static void mostrarMenuPrestamo() {
        Menu.ingresoPrestamoOtroMaterial();
        int op = scannerGlobal.nextInt();
        scannerGlobal.nextLine();

        switch (op) {
            case 0:
                option = 0;
                break;
            case 1:
                cancionOperaciones.mostrarCanciones();
                Menu.ingresoTitulo();
                String titulo = scannerGlobal.nextLine();
                Menu.ingresoFechaDevolucion();
                String fechaDev = scannerGlobal.nextLine();

                if(MenuSuperAdmin.isModoSuperAdmin()){
                    prestCancionOp.registrarPrestamoCancion(titulo,fechaDev,empleadoOp.getEmpleadoActual().getCorreo());
                }else{
                    prestCancionOp.registrarPrestamoCancion(titulo,fechaDev,usuarioOp.getUsuarioActual().getCorreo());
                }

                break;
            case 2:
                videoGrabacionOperaciones.mostrarVideoGrabaciones();
                Menu.ingresoTitulo();
                String tituloVid = scannerGlobal.nextLine();
                Menu.ingresoFechaDevolucion();
                String fechaDevVid = scannerGlobal.nextLine();

                if(MenuSuperAdmin.isModoSuperAdmin()){
                    prestVideoGrabacionOp.registrarPrestamoVideoGrabacion(tituloVid,fechaDevVid,empleadoOp.getEmpleadoActual().getCorreo());
                }else{
                    prestVideoGrabacionOp.registrarPrestamoVideoGrabacion(tituloVid,fechaDevVid,usuarioOp.getUsuarioActual().getCorreo());
                }

                break;
            case 3:
                tesisOperaciones.mostrarTesis();
                Menu.ingresoTitulo();
                String tituloTes = scannerGlobal.nextLine();
                Menu.ingresoFechaDevolucion();
                String fechaDevTes= scannerGlobal.nextLine();

                if(MenuSuperAdmin.isModoSuperAdmin()){
                    prestamoTesisOp.registrarPrestamoTesis(tituloTes,fechaDevTes,empleadoOp.getEmpleadoActual().getCorreo());
                }else{
                    prestamoTesisOp.registrarPrestamoTesis(tituloTes,fechaDevTes,usuarioOp.getUsuarioActual().getCorreo());
                }

                break;
            default:
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                option = 0;
                break;
        }
    }
}
