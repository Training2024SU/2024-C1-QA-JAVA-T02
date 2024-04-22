package co.com.pinguinera.controladores.autenticacion;

import co.com.pinguinera.datos.model.enums.RolEmpleado;
import co.com.pinguinera.servicios.GestorAccesoEmpleados;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vistas_asistente.MenuPrincipalAsistente;
import co.com.pinguinera.vistas.vistas_administrativo.MenuPrincipalAdministrativo;
import co.com.pinguinera.vistas.vistas_superadmin.MenuPrincipalSuperadmin;

public class EmpleadoSesionControlador {

    private GestorAccesoEmpleados gestorAccesoEmpleados;
    private MenuPrincipalAsistente menuPrincipalAsistente;
    private MenuPrincipalAdministrativo menuPrincipalAdministrativo;
    private MenuPrincipalSuperadmin menuPrincipalSuperadmin;

    public EmpleadoSesionControlador(GestorAccesoEmpleados gestorAccesoEmpleados,
                                     MenuPrincipalAsistente menuPrincipalAsistente,
                                     MenuPrincipalAdministrativo menuPrincipalAdministrativo,
                                     MenuPrincipalSuperadmin menuPrincipalSuperadmin) {
        this.gestorAccesoEmpleados = gestorAccesoEmpleados;
        this.menuPrincipalAsistente = menuPrincipalAsistente;
        this.menuPrincipalAdministrativo = menuPrincipalAdministrativo;
        this.menuPrincipalSuperadmin = menuPrincipalSuperadmin;
    }

    /**
     * Gestiona el proceso de inicio de sesión para empleados.
     */
    public void iniciarSesion() {
        String correo = VistaUtil.pedirCorreoElectronico();
        String contrasena = VistaUtil.pedirContrasena();

        RolEmpleado rolEmpleado = RolEmpleado.valueOf(gestorAccesoEmpleados.verificarEmpleado(correo, contrasena));

        if (rolEmpleado != null) {
            VistaUtil.mostrarMensajeExito();

            if (rolEmpleado == RolEmpleado.ASISTENTE) {
                menuPrincipalAsistente.mostrarMenu();
            } else if (rolEmpleado == RolEmpleado.ADMINISTRATIVO) {
                menuPrincipalAdministrativo.mostrarMenu();
            } else if (rolEmpleado == RolEmpleado.SUPERADMIN) {
                menuPrincipalSuperadmin.mostrarMenu();
            } else {
                System.out.println("Rol del empleado desconocido. No se puede mostrar un menú.");
            }
        }
    }
}
