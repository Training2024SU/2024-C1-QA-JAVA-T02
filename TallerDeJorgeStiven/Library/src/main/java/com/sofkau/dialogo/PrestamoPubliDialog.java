package com.sofkau.dialogo;

import com.github.javafaker.Faker;
import com.sofkau.logica.prestamo.CrudPrestamoPubli;
import com.sofkau.model.Prestamo;
import com.sofkau.model.Publicacion;
import com.sofkau.util.OperadorFechas;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.sofkau.Main.mySqlOperation;
import static com.sofkau.logica.prestamo.CrudPrestamoPubli.createPrestamo;
import static com.sofkau.logica.prestamo.CrudPrestamoPubli.findPrestamoById;
import static com.sofkau.logica.publicacion.CrudLibro.findPublicacionByTitulo;
import static com.sofkau.logica.publicacion.CrudLibro.updatePublicacionByTitulo;
import static com.sofkau.util.PedirPorConsola.*;

public class PrestamoPubliDialog {

    public static void logicaParaActualizarPrestamosEmpleados() throws SQLException {
        System.out.println("Ha ingresado a la seccion de pretamos, donde podra modificar el estado del prestamo");
        System.out.print("Ingrese el id del prestamo a modificar: ");
        String idPrestamo = pedirString();
        Prestamo prestamo = findPrestamoById(idPrestamo);
        System.out.println("Con que estado desea que quede el prestamo");
        System.out.println("1. Finalizado");
        System.out.println("2. Realizado");
        int opcionSeleccionada = pedirOpcion();
        switch (opcionSeleccionada) {
            case 1:
                CrudPrestamoPubli.changeStatusPrestamo("FINALIZADA", prestamo.getIdPrestamo());
                List<Publicacion> publicacionEstado = findPublicacionByTitulo(prestamo.getTituloPublicacion());
                publicacionEstado.forEach(publicacion -> updatePublicacionByTitulo(publicacion.getCantidadEjemplares(),publicacion.getCantidadPrestado()-1,publicacion.getTitulo()));
                break;
            case 2:
                CrudPrestamoPubli.changeStatusPrestamo("REALIZADA", prestamo.getIdPrestamo());
                break;
            default:
                System.out.println("valor no valido");
        }
    }

    public static void logicaParaHacerPrestamosUsuario () throws SQLException {
        System.out.println("Eligio solicitar prestamo");
        System.out.print("Escriba el titulo de la publicacion que desea obtener: ");
        String tituloToGive = pedirString();
        System.out.print("Escriba su correo: ");
        Faker faker = new Faker(new Locale("es"));
        String id = faker.bothify("##########");
        String emailToGive = pedirString();
        Prestamo prestamoGive = new Prestamo();
        prestamoGive.setIdPrestamo(id);
        prestamoGive.setFechaPrestamo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        prestamoGive.setFechaDevolucion(new SimpleDateFormat("yyy-MM-dd").format(OperadorFechas.sumarRestarDiasFecha(new Date(), 15)));
        prestamoGive.setEstadoPrestamo("SOLICITADO");
        prestamoGive.setCorreoUsuario(emailToGive);
        prestamoGive.setTituloPublicacion(tituloToGive);

        createPrestamo(prestamoGive);
        List<Publicacion> publicacionToSet = findPublicacionByTitulo(prestamoGive.getTituloPublicacion());
        publicacionToSet.forEach(publicacion -> updatePublicacionByTitulo(publicacion.getCantidadEjemplares(),publicacion.getCantidadPrestado()+1,publicacion.getTitulo()));
    }

    private static final String DELETE_PRESTAMOS_BY_ID= "delete from prestamo where idPrestamo = '%s';";

    public static void eliminarPrestamo() {
        System.out.print("Escriba el id del prestamo que desea restaurar: ");
        String idPrestamo = pedirString();
        mySqlOperation.setSqlStatement(String.format(DELETE_PRESTAMOS_BY_ID, idPrestamo));
        mySqlOperation.executeSqlStatementVoid();
    }
}
