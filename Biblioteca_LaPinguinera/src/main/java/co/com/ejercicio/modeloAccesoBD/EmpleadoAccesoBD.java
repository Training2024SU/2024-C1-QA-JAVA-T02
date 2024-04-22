package co.com.ejercicio.modeloAccesoBD;

import co.com.ejercicio.modelo.Empleado;
import co.com.ejercicio.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.com.ejercicio.conexionBd.MapeoBD.MapearTablasBD.mapearResultSetAEmpleado;
import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.*;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_EXITOSA;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;

public class EmpleadoAccesoBD {

    private Connection conexion;

    public EmpleadoAccesoBD(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarEmpleado(Empleado empleado) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement(INSERT_EMPLEADO)) {
            stmt.setInt(1, empleado.getIdEmpleado());
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getCorreo());
            stmt.setString(4, empleado.getContrasenia());
            stmt.setString(5, String.valueOf(empleado.getRol()));
            stmt.setInt(6, empleado.getEdad());
            stmt.setString(7, empleado.getTelefono());
            stmt.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public void eliminarEmpleado(int idEmpleado) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(DELETE_FROM_EMPLEADO)) {
            statement.setInt(1, idEmpleado);
            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public void modificarEmpleado(Empleado empleado) throws SQLException{
        try (PreparedStatement stmt = conexion.prepareStatement(UPDATE_EMPLEADO)) {
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getCorreo());
            stmt.setInt(3, empleado.getEdad());
            stmt.setString(4, empleado.getTelefono());
            stmt.setInt(5, empleado.getIdEmpleado());



            stmt.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }


    public List<Empleado> obtenerTodosLosEmpleados() throws SQLException {
        List<Empleado> empleados = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_FROM_EMPLEADO);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                empleados.add(mapearResultSetAEmpleado(resultSet));
            }
            System.out.println(OPERACION_EXITOSA);

        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
        return empleados;
    }

    public void actualizarContrasena(Empleado empleado, String nuevaContrasena ) throws SQLException {
        try (PreparedStatement statement = conexion.prepareStatement(UPDATE_PASSWORD_EMPLEADO)) {
            statement.setString(1, nuevaContrasena);
            statement.setInt(2, empleado.getIdEmpleado());

            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }
}
