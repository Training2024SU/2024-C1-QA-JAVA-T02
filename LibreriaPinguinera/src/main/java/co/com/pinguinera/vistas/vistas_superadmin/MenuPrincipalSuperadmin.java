package co.com.pinguinera.vistas.vistas_superadmin;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.crud.*;
import co.com.pinguinera.datos.model.publicaciones.Ensayos;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vista_usuario.MenuPrincipalUsuario;
import co.com.pinguinera.vistas.vistas_administrativo.MenuPrincipalAdministrativo;
import co.com.pinguinera.vistas.vistas_asistente.MenuAdministrarPrestamos;
import co.com.pinguinera.vistas.vistas_asistente.MenuPrincipalAsistente;


import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static co.com.pinguinera.datos.conexionBD.mongo.MongoDBExample.agregarPublis;
import static co.com.pinguinera.datos.conexionBD.mongo.MongoDBExample.obtenerPublicacionPorId;

public class MenuPrincipalSuperadmin {
    private static final Logger LOGGER = LoggerUtil.getLogger();

    private final ControladorCRUDLibro controladorCRUDLibro;
    private final ControladorCRUDNovela controladorCRUDNovela;
    private final ControladorCRUDPrestamo controladorCRUDPrestamo;
    private final MenuAdministrarPrestamos menuAdministrarPrestamos;
    private final ControladorCRUDEmpleado controladorCRUDEmpleado;
    private final ControladorCRUDVideograbaciones controladorCRUDVideograbaciones;
    private final ControladorCRUDEnsayos controladorCRUDEnsayos;
    private final ControladorCRUDCanciones controladorCRUDCanciones;

    private MenuPrincipalUsuario menuPrincipalUsuario;
    private MenuPrincipalAsistente menuPrincipalAsistente;
    private MenuPrincipalAdministrativo menuPrincipalAdministrativo;

    public MenuPrincipalSuperadmin(ControladorCRUDUsuario controladorCRUDUsuario,
                                   ControladorCRUDPrestamo controladorCRUDPrestamo,
                                   ControladorCRUDEmpleado controladorCRUDEmpleado,
                                   ControladorCRUDNovela controladorCRUDNovela,
                                   ControladorCRUDLibro controladorCRUDLibro,
                                   ControladorCRUDVideograbaciones controladorCRUDVideograbaciones,
                                   ControladorCRUDEnsayos controladorCRUDEnsayos,
                                   ControladorCRUDCanciones controladorCRUDCanciones,
                                   MenuAdministrarPrestamos menuAdministrarPrestamos) {
        this.controladorCRUDLibro = controladorCRUDLibro;
        this.controladorCRUDNovela = controladorCRUDNovela;
        this.controladorCRUDPrestamo = controladorCRUDPrestamo;
        this.controladorCRUDVideograbaciones = controladorCRUDVideograbaciones;
        this.controladorCRUDCanciones = controladorCRUDCanciones;
        this.controladorCRUDEnsayos = controladorCRUDEnsayos;
        this.menuAdministrarPrestamos = menuAdministrarPrestamos;


        this.menuPrincipalUsuario = new MenuPrincipalUsuario(controladorCRUDUsuario,controladorCRUDPrestamo,controladorCRUDLibro,controladorCRUDNovela,controladorCRUDVideograbaciones,controladorCRUDEnsayos,controladorCRUDCanciones);
        this.menuPrincipalAsistente = new MenuPrincipalAsistente(controladorCRUDLibro,controladorCRUDNovela,controladorCRUDPrestamo,controladorCRUDVideograbaciones,controladorCRUDEnsayos,controladorCRUDCanciones,menuAdministrarPrestamos);
        this.menuPrincipalAdministrativo = new MenuPrincipalAdministrativo(controladorCRUDUsuario,controladorCRUDPrestamo, controladorCRUDEmpleado,controladorCRUDNovela,controladorCRUDLibro,controladorCRUDVideograbaciones,controladorCRUDEnsayos,controladorCRUDCanciones);
        this.controladorCRUDEmpleado = controladorCRUDEmpleado;
    }


    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            LOGGER.info("\nMenú Super Administrativo");
            LOGGER.info("1. Funciones como usuario");
            LOGGER.info("2. Funciones como asistente");
            LOGGER.info("3. Funciones como administrador");
            LOGGER.info("4. Crear administrador");
            LOGGER.info("5. Restaurar");
            LOGGER.info("6. Agregar publicaciones a mongo");
            LOGGER.info("7. Buscar publicación en mongo");
            LOGGER.info("8. " + MenuConstantes.SALIR);

            int opcion = VistaUtil.obtenerOpcion();

            switch (opcion) {
                case 1:
                    menuPrincipalUsuario.mostrarMenu("super@pingu.com","contrasena");
                    break;
                case 2:
                    menuPrincipalAsistente.mostrarMenu();
                    break;
                case 3:
                    menuPrincipalAdministrativo.mostrarMenu();
                    break;
                case 4:
                    controladorCRUDEmpleado.registrarAdministrativo();
                    //Metodo para crear admins
                    break;
                case 5:
                    controladorCRUDPrestamo.eliminarPrestamoSA();

                    break;
                case 6:
                    List<Ensayos> publicaciones = controladorCRUDEnsayos.obtenerTodosGson();
                    agregarPublis(publicaciones);
                    break;
                case 7:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Inserte el ID que desea consultar");
                    int id = scanner.nextInt();
                    obtenerPublicacionPorId(id);
                    break;
                case 8:

                    LOGGER.info(MenuConstantes.OPCION_VOLVER);
                    continuar = false;
                    break;

                default:
                    LOGGER.warning(MenuConstantes.OPCION_INVALIDA);
            }
        }
    }
    public void setMenuPrincipalUsuario(MenuPrincipalUsuario menuPrincipalUsuario) {
        this.menuPrincipalUsuario = menuPrincipalUsuario;
    }
}
