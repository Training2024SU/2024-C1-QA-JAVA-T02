package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PerfilEmpleadoRepositorio {
    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static HashMap<String, String> consultarContactosEmpleados() {
        String queryContacto = "SELECT * FROM contacto_empleado";
        IngresoQuery.ejecutarConsultaQuery(queryContacto);
        ResultSet resultSetContacto = mySqlOperation.getResulset();

        HashMap<String, String> contactosEmpleados = new HashMap<>();
        try {
            while (resultSetContacto.next()) {
                String idEmpleado = resultSetContacto.getString("id_empleado");
                String contacto = resultSetContacto.getString("numero");
                contactosEmpleados.put(idEmpleado, contacto);
            }
            return contactosEmpleados;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static HashMap<String, String> consultarDireccionesEmpleados() {
        String queryDireccion = "SELECT * FROM direccion_empleado";
        IngresoQuery.ejecutarConsultaQuery(queryDireccion);
        ResultSet resultSetDireccion = mySqlOperation.getResulset();

        HashMap<String, String> direccionesEmpleados = new HashMap<>();
        try {
            while (resultSetDireccion.next()) {
                String idEmpleado = resultSetDireccion.getString("id_empleado");
                String direccion = resultSetDireccion.getString("direccion");
                direccionesEmpleados.put(idEmpleado, direccion);
            }
            return direccionesEmpleados;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ingresarContactoEmpleado(String idEmpleado, String nuevoContacto) {
        String queryInsercion = "INSERT INTO contacto_empleado (id_empleado, numero) VALUES ('" +
                idEmpleado + "', '" + nuevoContacto + "')";
        IngresoQuery.ejecutarIngresoQuery(queryInsercion);
    }

    public static void ingresarDireccionEmpleado(String idEmpleado, String nuevaDireccion) {
        String queryInsercion = "INSERT INTO direccion_empleado (id_empleado, direccion) VALUES ('" +
                idEmpleado + "', '" + nuevaDireccion + "')";
        IngresoQuery.ejecutarIngresoQuery(queryInsercion);
    }

    public static void actualizarContactoEmpleado(String idEmpleado, String nuevoContacto) {
        String queryActualizacion = "UPDATE contacto_empleado SET numero = '" + nuevoContacto +
                "' WHERE id_empleado = '" + idEmpleado + "'";
        IngresoQuery.ejecutarIngresoQuery(queryActualizacion);
    }

    public static void actualizarDireccionEmpleado(String idEmpleado, String nuevaDireccion) {
        String queryActualizacion = "UPDATE direccion_empleado SET direccion = '" + nuevaDireccion +
                "' WHERE id_empleado = '" + idEmpleado + "'";
        IngresoQuery.ejecutarIngresoQuery(queryActualizacion);
    }
}