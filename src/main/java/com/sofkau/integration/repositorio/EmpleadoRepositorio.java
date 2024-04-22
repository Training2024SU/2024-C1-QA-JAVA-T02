package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.Empleado;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EmpleadoRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    public static void crearEmpleado(Empleado empleado) {
        String query = String.format("INSERT INTO Empleado (idEmpleado, nombre, contrasena, correo, rol) VALUES ('%s', '%s', '%s', '%s', '%s')",
                empleado.getId(), empleado.getNombre(), empleado.getContrasena(), empleado.getCorreo(), empleado.getRol());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static HashMap<String, Empleado> consultarEmpleados() {
        String query = "SELECT * FROM Empleado";
        IngresoQuery.ejecutarConsultaQuery(query);
        ResultSet resultSet = mySqlOperation.getResulset();

        HashMap<String, Empleado> empleados = new HashMap<>();

        try {
            while (resultSet.next()) {
                String id = resultSet.getString("idEmpleado");
                String nombre = resultSet.getString("nombre");
                String correo = resultSet.getString("correo");
                String contrasena = resultSet.getString("contrasena");
                String rol = resultSet.getString("rol");

                Empleado empleado = new Empleado(id,nombre,correo,contrasena,rol);

                empleados.put(id, empleado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return empleados;
    }

    public static Empleado consultarEmpleadoPorId(String id){
        String query = String.format("Select * from Empleado where idEmpleado = '%s'",id);
        IngresoQuery.ejecutarConsultaQuery(query);

        ResultSet resultSet = mySqlOperation.getResulset();

        try {
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String correo = resultSet.getString("correo");
                String contrasena = resultSet.getString("contrasena");
                String rol = resultSet.getString("rol");

                return new Empleado(id,nombre,correo,contrasena,rol);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void registroCreacion(String empleadoCreador,String empleadoCreado) {
        String query = String.format("INSERT INTO registros_creados (id_empleado_creador, id_empleado_creado) VALUES ('%s', '%s')",
                empleadoCreador,empleadoCreado);
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static void actualizarEmpleado(Empleado empleado) {
        String query = String.format("UPDATE Empleado SET nombre = '%s', correo = '%s', contrasena = '%s', rol = '%s' WHERE idEmpleado = '%s'",
                empleado.getNombre(), empleado.getCorreo(), empleado.getContrasena(), empleado.getRol(), empleado.getId());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

}
