package co.com.training.logica;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.modelo.Usuario;
import co.com.training.util.enums.RolUsuario;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CrudUsuario {
    Connection conn;

    public CrudUsuario () {
        try {
            conn = MySqlOperation.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Optional<Usuario> validarCredenciales(String correo, String contrasenia) {

        String sqlConsulta = "SELECT * FROM usuario WHERE correoUsuario = ? AND contrasenaUsuario = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);

            pstmt.setString(1, new String(correo.getBytes(), "UTF-8"));
            pstmt.setString(2, new String(contrasenia.getBytes(), "UTF-8"));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Bienvenido " + rs.getString("nombreUsuario"));

                return Optional.of(new Usuario(

                        rs.getString("correoUsuario"),
                        rs.getString("nombreUsuario"),
                        rs.getString("contrasenaUsuario"),
                        RolUsuario.valueOf(rs.getString("rolUsuario"))
                ));
            }
        } catch (SQLException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public Optional<List<Usuario>> listarUsuarios() {

        String sqlConsulta = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("correoUsuario"),
                        rs.getString("nombreUsuario"),
                        rs.getString("contrasenaUsuario"),
                        RolUsuario.valueOf(rs.getString("rolUsuario"))
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!usuarios.isEmpty()) {
            return Optional.of(usuarios);
        } else {
            return Optional.empty();
        }
    }


    public Optional<Usuario> guardarUsuario(Usuario nuevoUser) {

        String sqlConsulta = "INSERT INTO usuario (correoUsuario, nombreUsuario, contrasenaUsuario, rolUsuario) " +
                "VALUES (?, ?, ?, ?)";
        int rowsAffected = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlConsulta);

            pstmt.setString(1, nuevoUser.getCorreoUsuario());
            pstmt.setString(2, nuevoUser.getNombreUsuario());
            pstmt.setString(3, nuevoUser.getContrasenaUsuario());
            pstmt.setString(4, nuevoUser.getRol().toString());

            rowsAffected = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rowsAffected > 0) {
            return Optional.of(nuevoUser);
        }
        return Optional.empty();
    }


//    public Optional<Usuario> obtenerUsuarioPorId(String userId) {
//
//        Optional<List<Usuario>> listaUsuariosOptional = listarUsuarios();
//        if (listaUsuariosOptional.isPresent()) {
//            Optional<Usuario> usuarioOptional = listaUsuariosOptional.get().stream()
//                    .filter(usuario -> usuario.getId().equals(userId))
//                    .findFirst();
//
//            return usuarioOptional;
//        }
//        return Optional.empty();
//    }
}
