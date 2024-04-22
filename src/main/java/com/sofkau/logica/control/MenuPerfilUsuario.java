package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.model.PerfilModificable;
import com.sofkau.model.Usuario;
import com.sofkau.util.enums.TipoPublicacion;

import static com.sofkau.logica.control.ControlIngreso.*;
import static com.sofkau.logica.control.ControlIngreso.option;

public class MenuPerfilUsuario {
    protected static void menuPerfilUsuario() {

        Menu.menuPerfilUsuario();
        int op = scannerGlobal.nextInt();
        scannerGlobal.nextLine();

        switch (op) {
            case 0-> {
                option = 0;
            }
            case 1-> {
                perfilOp.getPerfil(usuarioOp.getUsuarioActual().getCorreo());
            }
            case 2-> {
                Menu.ingresoContacto();
                String contacto = scannerGlobal.nextLine();
                Menu.ingresoDireccio();
                String direccion = scannerGlobal.nextLine();

                perfilOp.ingresarPerfil(new PerfilModificable(usuarioOp.getUsuarioActual().getCorreo(),
                        contacto,direccion
                        ));
            }
            case 3-> {
                Menu.ingresoContacto();
                String contacto = scannerGlobal.nextLine();
                Menu.ingresoDireccio();
                String direccion = scannerGlobal.nextLine();

                perfilOp.actualizarPerfil(new PerfilModificable(usuarioOp.getUsuarioActual().getCorreo(),
                        contacto,direccion
                ));
            }
            case 4-> {
                Menu.contrasena();
                String contrasena = scannerGlobal.nextLine();
                usuarioOp.actualizarUsuario(new Usuario(
                        usuarioOp.getUsuarioActual().getNombre(),
                        usuarioOp.getUsuarioActual().getCorreo(),
                        contrasena
                ));

            }
            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                option = 0;
            }

        }
    }
}
