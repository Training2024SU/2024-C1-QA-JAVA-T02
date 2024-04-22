package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.PerfilModificable;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PerfilUsuarioRepositorio {
    private static MySqlOperation mySqlOperation =  ConexionDatabase.getMySqlOperation();

    public static HashMap<String, String> consultarDireccionesUsuarios() {
        String queryDireccion = "SELECT * FROM direccion_usuario";
        IngresoQuery.ejecutarConsultaQuery(queryDireccion);
        ResultSet resultSetDireccion = mySqlOperation.getResulset();

        HashMap<String, String> direccionesUsuarios = new HashMap<>();
        try {
            while (resultSetDireccion.next()) {
                String idUsuario = resultSetDireccion.getString("correo_usuario");
                String direccion = resultSetDireccion.getString("direccion");
                direccionesUsuarios.put(idUsuario, direccion);
            }
            return direccionesUsuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, String> consultarContactosUsuarios() {
        String queryContacto = "SELECT * FROM contacto_usuario";
        IngresoQuery.ejecutarConsultaQuery(queryContacto);
        ResultSet resultSetContacto = mySqlOperation.getResulset();

        HashMap<String, String> contactosUsuarios = new HashMap<>();
        try {
            while (resultSetContacto.next()) {
                String idUsuario = resultSetContacto.getString("correo_usuario");
                String contacto = resultSetContacto.getString("numero");
                contactosUsuarios.put(idUsuario, contacto);
            }
            return contactosUsuarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void ingresarContacto(String correoUsuario, String nuevoContacto) {
            String queryInsercion = "INSERT INTO contacto_usuario (correo_usuario, numero) VALUES ('" +
                    correoUsuario + "', '" + nuevoContacto + "')";
            IngresoQuery.ejecutarIngresoQuery(queryInsercion);
    }

    public static void ingresarDireccionUsuario(String correoUsuario, String nuevaDireccion) {
            String queryInsercion = "INSERT INTO direccion_usuario (correo_usuario, direccion) VALUES ('" +
                    correoUsuario + "', '" + nuevaDireccion + "')";
            IngresoQuery.ejecutarIngresoQuery(queryInsercion);
    }

    public static void actualizarContactoUsuario(String correoUsuario, String nuevoContacto) {
        String queryActualizacion = "UPDATE contacto_usuario SET numero = '" + nuevoContacto +
                "' WHERE correo_usuario = '" + correoUsuario + "'";
        IngresoQuery.ejecutarIngresoQuery(queryActualizacion);
    }

    public static void actualizarDireccionUsuario(String correoUsuario, String nuevaDireccion) {
        String queryActualizacion = "UPDATE direccion_usuario SET direccion = '" + nuevaDireccion +
                "' WHERE correo_usuario = '" + correoUsuario + "'";
        IngresoQuery.ejecutarIngresoQuery(queryActualizacion);
    }



}
