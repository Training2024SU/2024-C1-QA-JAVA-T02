package com.sofkau.logica.publicacion;

import com.sofkau.model.Publicacion;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.sofkau.Main.mySqlOperation;
import static com.sofkau.logica.control.MetodosPrincipales.insertIntoDb;

public class CrudLibro {

    public static List<Publicacion> publicaciones = new ArrayList<>();
    private static final String SELECT_LIBROS_BY_AUTOR = "select * from mydb.publicacion where autor = '%s';";

    private static final String SELECT_LIBROS_BY_TITULO = "select * from mydb.publicacion where titulo = '%s';";
    private static final String UPTDATE_LIBRO_ESTADO = "update mydb.publicacion SET cant_ejemplares = %s, cant_prestados = %s where titulo = '%s';";

    private static final String DELETE_LIBRO_BY_TITULO = "delete from mydb.publicacion where titulo = '%s'";

    private static final String INSERT_PUBLICACION = "insert into mydb.publicacion (Titulo, tipo, autor, num_paginas, cant_ejemplares, cant_prestados) values('%s', '%s', '%s', '%s', '%s', '%s');";


    public static List<Publicacion> getAllPublicacionesByAutor(String autor) throws SQLException {
        try {
            mySqlOperation.setSqlStatement(String.format(SELECT_LIBROS_BY_AUTOR, autor));
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();
            while (resultSet.next()) {
                Publicacion publicacion = new Publicacion();
                publicacion.setTitulo(resultSet.getString(1));
                publicacion.setTipo(resultSet.getString(2));
                publicacion.setAutor(resultSet.getString(3));
                publicacion.setNumeroPaginas(Integer.parseInt(resultSet.getString(4)));
                publicacion.setCantidadEjemplares(Integer.parseInt(resultSet.getString(5)));
                publicacion.setCantidadPrestado(Integer.parseInt(resultSet.getString(6)));
                publicacion.setCantidadDisponible(Integer.parseInt(resultSet.getString(7)));
                publicaciones.add(publicacion);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener autor: " + e.getMessage());
        }
        return publicaciones;
    }

    public static List<Publicacion> findPublicacionByTitulo(String titulo) {
        try {
            mySqlOperation.setSqlStatement(String.format(SELECT_LIBROS_BY_TITULO, titulo));
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResulset();
            while (resultSet.next()) {
                Publicacion publicacion = new Publicacion();
                publicacion.setTitulo(resultSet.getString(1));
                publicacion.setTipo(resultSet.getString(2));
                publicacion.setAutor(resultSet.getString(3));
                publicacion.setNumeroPaginas(Integer.parseInt(resultSet.getString(4)));
                publicacion.setCantidadEjemplares(Integer.parseInt(resultSet.getString(5)));
                publicacion.setCantidadPrestado(Integer.parseInt(resultSet.getString(6)));
                publicacion.setCantidadDisponible(Integer.parseInt(resultSet.getString(7)));
                publicaciones.add(publicacion);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener titulo: " + e.getMessage());
        }
        return publicaciones;
    }

    public static void updatePublicacionByTitulo(int cantidadEjemplares, int cantidadPrestados, String titulo) {
        insertIntoDb(String.format(UPTDATE_LIBRO_ESTADO, cantidadEjemplares, cantidadPrestados, titulo));
    }

    public static void deletePublicacion(String titulo) {
        insertIntoDb(String.format(DELETE_LIBRO_BY_TITULO, titulo));
    }

    public static void createPublicacion(Publicacion publicacion) {
        insertIntoDb(String.format(INSERT_PUBLICACION, publicacion.getTitulo(), publicacion.getTipo(), publicacion.getAutor(), publicacion.getNumeroPaginas(), publicacion.getCantidadEjemplares(), publicacion.getCantidadPrestado()));
    }

    public static void serializarPublicacion(Publicacion publicacion) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("publicacion.csv");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(publicacion);
        objectOutputStream.close();
    }

    public static void deserializarPublicacion() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("publicacion.csv");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Publicacion publicacion1 = (Publicacion) objectInputStream.readObject();
        objectInputStream.close();
        System.out.println(publicacion1);
    }
}
