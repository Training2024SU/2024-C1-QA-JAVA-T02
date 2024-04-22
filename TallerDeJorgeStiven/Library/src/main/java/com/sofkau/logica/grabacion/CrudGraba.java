package com.sofkau.logica.grabacion;

import com.sofkau.model.Grabacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sofkau.Main.mySqlOperation;
import static com.sofkau.logica.control.MetodosPrincipales.insertIntoDb;

public class CrudGraba {

    private static final String SELECT_GRABAS_BY_AUTOR = "select * from mydb.Grabacion where autor = '%s';";

    private static final String SELECT_GRABAS_BY_TITULO = "select * from mydb.Grabacion where titulo = '%s';";
    private static final String UPTDATE_GRABA_ESTADO = "update mydb.Grabacion SET cant_ejemplares = %s, cant_prestados = %s where titulo = '%s';";

    private static final String DELETE_GRABA_BY_TITULO = "delete from mydb.Grabacion where titulo = '%s'";

    private static final String INSERT_GRABA = "insert into mydb.Grabacion (Titulo, tipo, autor, duracion, cant_ejemplares, cant_prestados) values('%s', '%s', '%s', '%s', '%s', '%s');";


    public static List<Grabacion> getAllGrabacionesByAutor(String autor) throws SQLException {
        List<Grabacion> grabaciones = new ArrayList<>();
        try {
            mySqlOperation.setSqlStatement(String.format(SELECT_GRABAS_BY_AUTOR, autor));
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();
            while (resultSet.next()) {
                Grabacion grabacion = new Grabacion();
                grabacion.setTitulo(resultSet.getString(1));
                grabacion.setTipo(resultSet.getString(2));
                grabacion.setAutor(resultSet.getString(3));
                grabacion.setDuracion(resultSet.getString(4));
                grabacion.setCantidadEjemplares(Integer.parseInt(resultSet.getString(5)));
                grabacion.setCantidadPrestado(Integer.parseInt(resultSet.getString(6)));
                grabacion.setCantidadDisponible(Integer.parseInt(resultSet.getString(7)));
                grabaciones.add(grabacion);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener autor: " + e.getMessage());
        }
        return grabaciones;
    }

    public static List<Grabacion> findGrabacionByTitulo(String titulo) {
        List<Grabacion> grabaciones = new ArrayList<>();
        try {
            mySqlOperation.setSqlStatement(String.format(SELECT_GRABAS_BY_TITULO, titulo));
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();
            while (resultSet.next()) {
                Grabacion grabacion = new Grabacion();
                grabacion.setTitulo(resultSet.getString(1));
                grabacion.setTipo(resultSet.getString(2));
                grabacion.setAutor(resultSet.getString(3));
                grabacion.setDuracion(resultSet.getString(4));
                grabacion.setCantidadEjemplares(Integer.parseInt(resultSet.getString(5)));
                grabacion.setCantidadPrestado(Integer.parseInt(resultSet.getString(6)));
                grabacion.setCantidadDisponible(Integer.parseInt(resultSet.getString(7)));
                grabaciones.add(grabacion);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener titulo: " + e.getMessage());
        }
        return grabaciones;
    }

    public static void updateGrabacionByTitulo(int cantidadEjemplares, int cantidadPrestados, String titulo) {
        insertIntoDb(String.format(UPTDATE_GRABA_ESTADO, cantidadEjemplares, cantidadPrestados, titulo));
    }

    public static void deleteGrabacion(String titulo) {
        insertIntoDb(String.format(DELETE_GRABA_BY_TITULO, titulo));
    }

    public static void createGrabacion(Grabacion grabacion) {
        insertIntoDb(String.format(INSERT_GRABA, grabacion.getTitulo(), grabacion.getTipo(), grabacion.getAutor(), grabacion.getDuracion(), grabacion.getCantidadEjemplares(), grabacion.getCantidadPrestado()));
    }
}
