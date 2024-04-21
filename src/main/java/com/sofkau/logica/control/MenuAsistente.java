package com.sofkau.logica.control;

import com.sofkau.dialogo.Menu;
import com.sofkau.model.*;
import com.sofkau.util.CommonOperacion.GenerateUniqueId;
import com.sofkau.util.enums.EstadoPrestamo;
import com.sofkau.util.enums.TipoPublicacion;

import java.text.ParseException;
import java.util.Date;

import static com.sofkau.logica.control.ControlIngreso.*;

public class MenuAsistente {

   /* Menu Asistente
    1. Crear Libro
    2. Crear Novela
    3. Actualizar Libro
    4. Actualizar Novela
    5. Entregar Libro usuario
    6. Ingresar libro devuelto
    7. Consultar Prestamos lector
    7. Consultar Prestamos lector
    8. Ingresar autor*/

    protected static void menuAsistente(int op) {
        switch (op) {
            case 0-> {
                option = 0;
            }
            case 1 -> {
                Menu.ingresoLibro();
                Menu.ingresoTitulo();
                String titulo = scannerGlobal.nextLine();
                autorOp.listarAutores();
                Menu.ingresoAutor();
                String nombreAutor = scannerGlobal.nextLine();
                Menu.ingresoCantEjemplar();
                int cantEjemplar = scannerGlobal.nextInt();
                Menu.ingresoCantPrestado();
                int cantPres = scannerGlobal.nextInt();
                Menu.ingresoNumPaginas();
                int numPag = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                Menu.ingresoAreaConocimiento();
                String areaConocimiento = scannerGlobal.nextLine();

                publicacionOp.registrarPublicacion(new Publicacion(titulo,autorOp.buscarAutorNombre(nombreAutor),TipoPublicacion.Libro.toString(),
                                numPag,cantEjemplar,cantPres),
                        new AreaGenero(titulo,areaConocimiento));

            }
            case 2 -> {
                Menu.ingresoNovela();
                Menu.ingresoTitulo();
                String titulo = scannerGlobal.nextLine();
                autorOp.listarAutores();
                Menu.ingresoAutor();
                String nombreAutor = scannerGlobal.nextLine();
                Menu.ingresoCantEjemplar();
                int cantEjemplar = scannerGlobal.nextInt();
                Menu.ingresoCantPrestado();
                int cantPres = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                Menu.ingresoGenero();
                String genero = scannerGlobal.nextLine();
                Menu.ingresoEdadSugerida();
                String edadSugeridad = scannerGlobal.nextLine();

                publicacionOp.registrarPublicacion(new Publicacion(titulo,autorOp.buscarAutorNombre(nombreAutor),TipoPublicacion.Novela.toString(),
                                cantEjemplar,cantPres),
                        new AreaGenero(titulo,genero),new EdadSugerida(titulo,edadSugeridad));

            }case 3 ->{
                Menu.actualizacionLibro();
                Menu.tituloActualizar();
                String tituloAntiguo = scannerGlobal.nextLine();
                Menu.ingresoTitulo();
                String titulo = scannerGlobal.nextLine();
                autorOp.listarAutores();
                Menu.ingresoAutor();
                String nombreAutor = scannerGlobal.nextLine();
                Menu.ingresoCantEjemplar();
                int cantEjemplar = scannerGlobal.nextInt();
                Menu.ingresoCantPrestado();
                int cantPres = scannerGlobal.nextInt();
                Menu.ingresoNumPaginas();
                int numPag = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                Menu.ingresoAreaConocimiento();
                String areaConocimiento = scannerGlobal.nextLine();
                publicacionOp.actualizarPublicacion(new Publicacion(titulo,autorOp.buscarAutorNombre(nombreAutor),TipoPublicacion.Libro.toString(),
                                numPag,cantEjemplar,cantPres),
                        new AreaGenero(titulo,areaConocimiento),
                        tituloAntiguo);
            }case 4 ->{
                Menu.actualizacionNovela();
                Menu.tituloActualizar();
                String tituloAntiguo = scannerGlobal.nextLine();
                Menu.ingresoTitulo();
                String titulo = scannerGlobal.nextLine();
                autorOp.listarAutores();
                Menu.ingresoAutor();
                String nombreAutor = scannerGlobal.nextLine();
                Menu.ingresoCantEjemplar();
                int cantEjemplar = scannerGlobal.nextInt();
                Menu.ingresoCantPrestado();
                int cantPres = scannerGlobal.nextInt();
                scannerGlobal.nextLine();
                Menu.ingresoGenero();
                String genero = scannerGlobal.nextLine();
                Menu.ingresoEdadSugerida();
                String edadSugeridad = scannerGlobal.nextLine();

                publicacionOp.actualizarPublicacion(new Publicacion(titulo,autorOp.buscarAutorNombre(nombreAutor),TipoPublicacion.Novela.toString(),
                                cantEjemplar,cantPres),
                        new AreaGenero(titulo,genero),new EdadSugerida(titulo,edadSugeridad),
                        tituloAntiguo);
            }case 5 ->{
                Menu.IngresoIdPrestamo();
                String idPrestamo = scannerGlobal.nextLine();
                prestamoOp.actualizarEstadoPrestamo(EstadoPrestamo.REALIZADO,idPrestamo);

            }case 6 ->{
                Menu.IngresoIdPrestamo();
                String idPrestamo = scannerGlobal.nextLine();
                prestamoOp.actualizarEstadoPrestamo(EstadoPrestamo.FINALIZADO,idPrestamo);

            }case 7 ->{
                Menu.listarPrestamos();
                Menu.correo();
                String correo = scannerGlobal.nextLine();

                prestamoOp.listarPrestamosPorCorreo(correo);
            }case 8->{
                Menu.ingresoAutor();
                String nombre = scannerGlobal.nextLine();
                autorOp.registrarAutor(new Autor(GenerateUniqueId.generateID(),nombre));
            }case 9 ->{
                Menu.ingresoTitulo();
                String titulo = scannerGlobal.nextLine();
                Menu.ingresoGenero();
                String genero = scannerGlobal.nextLine();
                Menu.ingresoAutor();
                String nombreAutor = scannerGlobal.nextLine();
                Menu.fechaLanzamiento();
                String fecha = scannerGlobal.nextLine();
                Date fechaLanzamiento = null;
                try {
                    fechaLanzamiento = formato.parse(fecha);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Menu.ingresoLetra();
                String letra = scannerGlobal.nextLine();
                Menu.ingresoCantEjemplar();
                int cantEjemplar = scannerGlobal.nextInt();
                Menu.ingresoCantPrestado();
                int cantPres = scannerGlobal.nextInt();

                cancionOperaciones.crearCancion(new Cancion(titulo,genero,nombreAutor,letra,fechaLanzamiento,cantEjemplar,cantPres));

            }case 10 ->{
                Menu.ingresoTitulo();
                String titulo = scannerGlobal.nextLine();
                Menu.ingresoGenero();
                String genero = scannerGlobal.nextLine();
                Menu.ingresoAutor();
                String nombreAutor = scannerGlobal.nextLine();
                Menu.ingresoSinopsis();
                String sinopsis = scannerGlobal.nextLine();
                Menu.ingresoTipo();
                String tipo = scannerGlobal.nextLine();
                Menu.ingresoCalificacion();
                int calificacion = scannerGlobal.nextInt();
                Menu.ingresoCantEjemplar();
                int cantEjemplar = scannerGlobal.nextInt();
                Menu.ingresoCantPrestado();
                int cantPres = scannerGlobal.nextInt();

                videoGrabacionOperaciones.crearVideoGrabacion(new VideoGrabacion(titulo, sinopsis, genero, nombreAutor,calificacion,tipo, cantEjemplar, cantPres));
            }case 11 ->{
                Menu.ingresoTitulo();
                String tituloTesis = scannerGlobal.nextLine();
                Menu.ingresoAutor();
                String autorTesis = scannerGlobal.nextLine();
                Menu.ingresoFecha();
                String fechaTesis = scannerGlobal.nextLine();
                Date fechaLanzamientoTesis = null;
                try {
                    fechaLanzamientoTesis = formato.parse(fechaTesis);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                Menu.ingresoCampoEstudio();
                String campoEstudioTesis = scannerGlobal.nextLine();
                Menu.ingresoPais();
                String paisTesis = scannerGlobal.nextLine();
                Menu.ingresoCantEjemplar();
                int cantEjemplar = scannerGlobal.nextInt();
                Menu.ingresoCantPrestado();
                int cantPres = scannerGlobal.nextInt();

                tesisOperaciones.crearTesis(new Tesis(tituloTesis,fechaLanzamientoTesis,
                        autorTesis,campoEstudioTesis,paisTesis,cantEjemplar,cantPres
                ));

            }case 12 ->{

            }

            default -> {
                System.out.println("Ha ocurrido un error por favor verifique sus credenciales");
                option = 0;
            }

        }
    }

}
