package co.com.pinguinera.vistas.vistas_asistente;

import co.com.pinguinera.LoggerUtil;
import co.com.pinguinera.controladores.crud.*;
import co.com.pinguinera.datos.model.Publicacion;
import co.com.pinguinera.datos.model.publicaciones.Ensayos;
import co.com.pinguinera.exporting.Json;
import co.com.pinguinera.exporting.Xml;
import co.com.pinguinera.vistas.MenuConstantes;
import co.com.pinguinera.vistas.VistaUtil;
import co.com.pinguinera.vistas.vistas_asistente.MenuAdministrarPrestamos;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static co.com.pinguinera.exporting.Json.export;
import static co.com.pinguinera.exporting.Xml.exportarPublicaciones;
import static co.com.pinguinera.exporting.Xml.importarPublicaciones;

public class MenuPrincipalAsistente {
    private static final Logger LOGGER = LoggerUtil.getLogger(); // Usar el logger global

    private final ControladorCRUDLibro controladorCRUDLibro;
    private final ControladorCRUDNovela controladorCRUDNovela;
    private final ControladorCRUDPrestamo controladorCRUDPrestamo;
    private final ControladorCRUDVideograbaciones controladorCRUDVideograbaciones;
    private final ControladorCRUDEnsayos controladorCRUDEnsayos;
    private final ControladorCRUDCanciones controladorCRUDCanciones;
    private final MenuAdministrarPrestamos menuAdministrarPrestamos;

    public MenuPrincipalAsistente(ControladorCRUDLibro controladorCRUDLibro,
                                  ControladorCRUDNovela controladorCRUDNovela,
                                  ControladorCRUDPrestamo controladorCRUDPrestamo,
                                  ControladorCRUDVideograbaciones controladorCRUDVideograbaciones,
                                  ControladorCRUDEnsayos controladorCRUDEnsayos,
                                  ControladorCRUDCanciones controladorCRUDCanciones,
                                  MenuAdministrarPrestamos menuAdministrarPrestamos) {
        this.controladorCRUDLibro = controladorCRUDLibro;
        this.controladorCRUDNovela = controladorCRUDNovela;
        this.controladorCRUDPrestamo = controladorCRUDPrestamo;
        this.controladorCRUDEnsayos=controladorCRUDEnsayos;
        this.controladorCRUDCanciones = controladorCRUDCanciones;
        this.controladorCRUDVideograbaciones = controladorCRUDVideograbaciones;
        this.menuAdministrarPrestamos = menuAdministrarPrestamos;
    }

    public void mostrarMenu() {
        boolean continuar = true;

        while (continuar) {
            LOGGER.info("\nMenú principal de asistente");
            LOGGER.info("1. Administrar préstamos");
            LOGGER.info("2. " + MenuConstantes.VER_TODOS_PRESTAMOS);
            LOGGER.info("3. " + MenuConstantes.AGREGAR_LIBRO);
            LOGGER.info("4. " + MenuConstantes.AGREGAR_NOVELA);
            LOGGER.info("5. " + MenuConstantes.AGREGAR_VIDEO);
            LOGGER.info("6. " + MenuConstantes.AGREGAR_CANCION);
            LOGGER.info("7. " + MenuConstantes.AGREGAR_ENSAYO);
            LOGGER.info("8. Agregar publicaciones (Json o XML)");
            LOGGER.info("9. Exportar publicaciones (Json o XML)");
            LOGGER.info("0. Salir");

            int opcion = VistaUtil.obtenerOpcion();

            switch (opcion) {
                case 1:
                    menuAdministrarPrestamos.mostrarMenu(); // Llamada al menú administrar préstamos
                    break;
                case 2:
                    controladorCRUDPrestamo.obtenerTodosPrestamos(); // Llamada al método para ver todos los préstamos
                    break;
                case 3:
                    controladorCRUDLibro.registrarLibro();
                    break;
                case 4:
                    controladorCRUDNovela.registrarNovela();
                    break;
                case 5:
                    controladorCRUDVideograbaciones.registrarVideo();
                    break;
                case 6:
                    controladorCRUDCanciones.registrarCancion();;
                    break;
                case 7:
                    controladorCRUDEnsayos.registrarEnsayo();
                    break;
                case 8:
//                    List<Ensayos> ensayosImportados = importarPublicaciones("ensayo.xml");
//                    System.out.println(ensayosImportados);
//
//                    String filePath = ""; // Ruta del archivo XML
//
//                    // Llamar al método importarPublicaciones para importar los ensayos
                    //List<Ensayos> ensayos = Xml.importarPublicaciones(filePath);

                    // Imprimir los ensayos importados
                    List<Ensayos> ensayos = Json.importJson("importTest.json", Ensayos.class);

                    for (Ensayos ensayo : ensayos) {
                        controladorCRUDEnsayos.registrarJSON(ensayo);
                        System.out.println(ensayo);
                    }
                    break;
                case 9:
                    List<Ensayos> ensayosBD = controladorCRUDEnsayos.obtenerTodosGson();
                    System.out.println("Exportar en JSON o XML (1 JSON, 2XML)");
                    int exportOption = VistaUtil.obtenerOpcion();
                    if (exportOption==1){
                        String userDir = System.getProperty("user.dir");
                        String rutaArchivo = userDir + "/publicaciones.json";
                        export(ensayosBD,rutaArchivo);
                        System.out.println("Archivo exportado a "+rutaArchivo);
                        break;
                    } else if (exportOption==2) {
                        String userDir = System.getProperty("user.dir");
                        String rutaArchivo = userDir + "/publicaciones.xml";
                        exportarPublicaciones(ensayosBD,rutaArchivo);
                        break;
                    }
                case 0:

                    LOGGER.info("Saliendo del menú...");
                    continuar = false;
                    break;
                default:
                    LOGGER.info(MenuConstantes.OPCION_INVALIDA);
            }
        }
    }
}
