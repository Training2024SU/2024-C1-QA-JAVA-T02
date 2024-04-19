package co.com.ejercicio.modeloAccesoBD;

import co.com.ejercicio.modelo.EnsayoTesis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.ejercicio.conexionBd.MapeoBD.MapearTablasBD.mapearResultSetAEnsayoTesis;
import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.*;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_EXITOSA;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;

public class EnsayoTesisAccesoBD {
    private static Connection conexion;

    public EnsayoTesisAccesoBD(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarEnsayoTesis(EnsayoTesis ensayoTesis) throws SQLException {
        try {
            PreparedStatement statement = conexion.prepareStatement(INSERT_ENSAYO_TESIS);
            statement.setString(1, ensayoTesis.getTitulo());
            statement.setString(2, ensayoTesis.getAutor());
            statement.setInt(3, ensayoTesis.getNumeroPaginas());
            statement.setInt(4, ensayoTesis.getCantidadEjemplares());
            statement.setInt(5, ensayoTesis.getCantidadPrestado());
            statement.setInt(6, ensayoTesis.getCantidadDisponible());
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public List<EnsayoTesis> obtenerTodosLosEnsayosTesis() throws SQLException {
        List<EnsayoTesis> ensayosTesis = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_FROM_ENSAYO_TESIS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ensayosTesis.add(mapearResultSetAEnsayoTesis(resultSet));
            }
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
        return ensayosTesis;
    }

    public static List<EnsayoTesis> obtenerEnsayoTesisDeUnAutor(String autor) throws SQLException {
        List<EnsayoTesis> ensayosTesis = new ArrayList<>();

        try (PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_AUTOR_FROM_ENSAYO_TESIS)) {
            statement.setString(1, autor);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    EnsayoTesis ensayoTesis = new EnsayoTesis(
                            resultSet.getString("titulo"),
                            resultSet.getString("autor"),
                            resultSet.getInt("numero_paginas"),
                            resultSet.getInt("cantidad_ejemplares"),
                            resultSet.getInt("cantidad_prestado"),
                            resultSet.getInt("cantidad_disponible")
                    );
                    ensayosTesis.add(ensayoTesis);
                }
            }
        }

        return ensayosTesis;
    }

    public void actualizarEnsayoTesis(EnsayoTesis ensayoTesis, String tituloPrevio) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(UPDATE_ENSAYO_TESIS)) {
            statement.setString(1, ensayoTesis.getTitulo());
            statement.setString(2, ensayoTesis.getAutor());
            statement.setInt(3, ensayoTesis.getNumeroPaginas());
            statement.setInt(4, ensayoTesis.getCantidadEjemplares());
            statement.setInt(5, ensayoTesis.getCantidadPrestado());
            statement.setInt(6, ensayoTesis.getCantidadDisponible());
            statement.setString(7, tituloPrevio);

            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }

    public void eliminarEnsayoTesis(String titulo) throws SQLException, RuntimeException {
        try (PreparedStatement statement = conexion.prepareStatement(DELETE_FROM_ENSAYO_TESIS)) {
            statement.setString(1, titulo);
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }
}
