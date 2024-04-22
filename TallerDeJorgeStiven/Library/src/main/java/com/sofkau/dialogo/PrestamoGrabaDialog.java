package com.sofkau.dialogo;

import com.github.javafaker.Faker;
import com.sofkau.logica.prestamo.CrudPrestamoPubli;
import com.sofkau.model.Prestamo;
import com.sofkau.model.Grabacion;
import com.sofkau.util.OperadorFechas;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.sofkau.Main.mySqlOperation;
import static com.sofkau.logica.grabacion.CrudGraba.findGrabacionByTitulo;
import static com.sofkau.logica.grabacion.CrudGraba.updateGrabacionByTitulo;
import static com.sofkau.logica.prestamo.CrudPrestamoGraba.*;
import static com.sofkau.logica.prestamo.CrudPrestamoPubli.createPrestamo;
import static com.sofkau.logica.prestamo.CrudPrestamoPubli.findPrestamoById;
import static com.sofkau.util.PedirPorConsola.*;

public class PrestamoGrabaDialog {

    public static void logicaParaActualizarPrestamosGrabaEmpleados() throws SQLException {
        System.out.println("Ha ingresado a la seccion de pretamos, donde podra modificar el estado del prestamo");
        System.out.print("Ingrese el id del prestamo a modificar: ");
        String idPrestamo = pedirString();
        Prestamo prestamo = findPrestamoByIdGraba(idPrestamo);
        System.out.println("Con que estado desea que quede el prestamo");
        System.out.println("1. Finalizado");
        System.out.println("2. Realizado");
        int opcionSeleccionada = pedirOpcion();
        switch (opcionSeleccionada) {
            case 1:
                changeStatusPrestamoGraba("FINALIZADA", prestamo.getIdPrestamo());
                List<Grabacion> grabacionEstado = findGrabacionByTitulo(prestamo.getTituloPublicacion());
                grabacionEstado.forEach(grabacion -> updateGrabacionByTitulo(grabacion.getCantidadEjemplares(),grabacion.getCantidadPrestado()-1,grabacion.getTitulo()));
                break;
            case 2:
                changeStatusPrestamoGraba("REALIZADA", prestamo.getIdPrestamo());
                break;
            default:
                System.out.println("valor no valido");
        }
    }

    public static void logicaParaHacerPrestamosGrabaUsuario () {
        System.out.println("Eligio solicitar prestamo");
        System.out.print("Escriba el titulo de la grabacion que desea obtener: ");
        String tituloToGive = pedirString();
        System.out.print("Escriba su correo: ");
        String emailToGive = pedirString();
        Faker faker = new Faker(new Locale("es"));
        String id = faker.bothify("##########");
        Prestamo prestamoGive = new Prestamo();
        prestamoGive.setIdPrestamo(id);
        prestamoGive.setFechaPrestamo(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        prestamoGive.setFechaDevolucion(new SimpleDateFormat("yyy-MM-dd").format(OperadorFechas.sumarRestarDiasFecha(new Date(), 15)));
        prestamoGive.setEstadoPrestamo("SOLICITADO");
        prestamoGive.setCorreoUsuario(emailToGive);
        prestamoGive.setTituloPublicacion(tituloToGive);

        createPrestamoGraba(prestamoGive);
        List<Grabacion> grabacionToSet = findGrabacionByTitulo(prestamoGive.getTituloPublicacion());
        grabacionToSet.forEach(grabacion -> updateGrabacionByTitulo(grabacion.getCantidadEjemplares(),grabacion.getCantidadPrestado()+1,grabacion.getTitulo()));
    }

    private static final String DELETE_PRESTAMOS_BY_ID= "delete from prestamo_g where idPrestamo = '%s';";

    public static void eliminarPrestamoGraba() {
        System.out.print("Escriba el id del prestamo que desea restaurar: ");
        String idPrestamo = pedirString();
        mySqlOperation.setSqlStatement(String.format(DELETE_PRESTAMOS_BY_ID, idPrestamo));
        mySqlOperation.executeSqlStatementVoid();
    }
}
