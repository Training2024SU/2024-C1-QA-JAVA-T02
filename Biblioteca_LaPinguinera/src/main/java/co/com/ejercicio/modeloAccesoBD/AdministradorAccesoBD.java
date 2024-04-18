package co.com.ejercicio.modeloAccesoBD;

import co.com.ejercicio.modelo.Administrador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static co.com.ejercicio.conexionBd.constantesCRUD.QueryConstante.INSERT_ADMINISTRADOR;
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

}
