package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.util.enums.TipoPublicacion;

import static com.sofkau.logica.control.ControlIngreso.*;


/*        Menu Usuario
        1. Realizar Prestamo
        2. LISTAR LIBROS
        3. LISTAR NOVELAS
        4. LISTAR AUTORES
        5. LISTAR PUBLICACIONES POR AUTOR*/

public class MenuUsuario {
    protected static void menuUsuario() {

        Menu.menuUsuario();
        int op = scannerGlobal.nextInt();
        scannerGlobal.nextLine();

        switch (op) {
            case 0-> {
                option = 0;
            }
            case 1-> {
                Menu.ingresoTitulo();
                String titulo = scannerGlobal.nextLine();
                Menu.ingresoFechaDevolucion();
                String fechaDev = scannerGlobal.nextLine();

                if(MenuSuperAdmin.isModoSuperAdmin()){
                    prestamoOp.RegistrarPrestamo(titulo,fechaDev,empleadoOp.getEmpleadoActual().getCorreo());
                }else{
                    prestamoOp.RegistrarPrestamo(titulo,fechaDev,usuarioOp.getUsuarioActual().getCorreo());
                }

            }
            case 2-> {
                publicacionOp.imprimirPublicaciones(TipoPublicacion.Libro);
            }
            case 3-> {
                publicacionOp.imprimirPublicaciones(TipoPublicacion.Novela);
            }
            case 4-> {
                autorOp.listarAutores();
            }
            case 5-> {
                Menu.nombre();
                String nombre = scannerGlobal.nextLine();
            }
            case 6-> {
                    MenuPerfilUsuario.menuPerfilUsuario();
            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                option = 0;
            }

        }
    }
}
