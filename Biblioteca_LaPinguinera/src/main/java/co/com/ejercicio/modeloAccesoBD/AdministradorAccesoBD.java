package co.com.ejercicio.modeloAccesoBD;

import co.com.ejercicio.modelo.Administrador;
import co.com.ejercicio.modelo.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import static co.com.ejercicio.conexionBd.MapeoBD.MapearTablasBD.mapearResultSetAAdministrador;
import static co.com.ejercicio.conexionBd.MapeoBD.MapearTablasBD.mapearResultSetAEmpleado;
import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.*;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_EXITOSA;
import static co.com.ejercicio.menu.constantesMenu.OperacionExitosaOFallida.OPERACION_FALLIDA;

public class AdministradorAccesoBD {

    private Connection conexion;

    public AdministradorAccesoBD(Connection conexion){ this.conexion = conexion; }

    public void insertarAdministrador(Administrador administrador) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement(INSERT_ADMINISTRADOR)) {
            stmt.setInt(1, administrador.getId());
            stmt.setString(2, administrador.getNombre());
            stmt.setString(3, administrador.getCorreo());
            stmt.setString(4, administrador.getContrasenia());
            stmt.setString(5, String.valueOf(administrador.getDepartamentoAdministrado()));
            stmt.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public List<Administrador> obetenerAdministradores() throws SQLException {
        List<Administrador> administradores = new ArrayList<>();
        try {
            PreparedStatement statement = conexion.prepareStatement(SELECT_ALL_FROM_ADMINISTRADOR);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                administradores.add(mapearResultSetAAdministrador(resultSet));
            }
            System.out.println(OPERACION_EXITOSA);

        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
        return administradores;
    }

    public void modificarAdministrador(Administrador administrador) throws SQLException {
        try (PreparedStatement stmt = conexion.prepareStatement(UPDATE_ADMINISTRADOR)) {

            stmt.setString(1, administrador.getNombre());
            stmt.setString(2, administrador.getCorreo());
            stmt.setString(3, administrador.getDepartamentoAdministrado());
            stmt.setInt(4, administrador.getId());
            stmt.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + e);
        }
    }

    public void actualizarContrasena(Administrador administrador, String nuevaContrasena){
        try (PreparedStatement statement = conexion.prepareStatement(UPDATE_PASSWORD_ADMINISTRADOR)) {
            statement.setString(1, nuevaContrasena);
            statement.setInt(2, administrador.getId());

            statement.executeUpdate();
            System.out.println(OPERACION_EXITOSA);
        } catch (SQLException e) {
            System.out.println(OPERACION_FALLIDA + (e));
        }
    }

}
