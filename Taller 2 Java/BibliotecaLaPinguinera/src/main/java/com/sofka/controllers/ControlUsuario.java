package com.sofka.controllers;

import com.sofka.modelo.Publicacion;
import com.sofka.modelo.Usuario;
import com.sofka.util.JsonUtil;
import net.datafaker.Faker;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.sofka.BibliotecaLaPinguinera.mySqlOperation;
import static com.sofka.constants.InsertConstants.INSERT_USUARIO;
import static com.sofka.constants.SelectConstants.*;
import static com.sofka.constants.UpdateConstants.*;
import static com.sofka.controllers.ControlMenu.mostrarMensaje;
import static com.sofka.controllers.ControlMenu.pedirEntrada;
import static com.sofka.controllers.ControlStament.ejecutarMostrarSQL;
import static com.sofka.controllers.ControlStament.insertIntoBd;

public class ControlUsuario {
    private ControlUsuario() {
    }

    private static final List<Usuario> usuarios = new ArrayList<>();

    public static void logicaCrearUsuario(Usuario usuario) {
        registrarUsuario(usuario);
        usuarios.add(usuario);
        mostrarMensaje("¡Usuario registrado exitosamente: " + usuario.getNombre() + "!");
    }

    public static void registrarNuevoUsuario() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre de usuario: ");
        String correo = JOptionPane.showInputDialog(null, "Ingrese un nuevo correo: ");
        String contrasena = JOptionPane.showInputDialog(null, "Ingrese una nueva contraseña: ");
        Usuario user = new Usuario(correo, nombre, contrasena);
        logicaCrearUsuario(user);
    }

    public static void registrarUsuario(Usuario usuario) {
        String cadena = String.format(INSERT_USUARIO, usuario.getCorreo(), usuario.getNombre(), usuario.getContrasena());
        insertIntoBd(cadena);
    }

    public static void crearUsuario() {
        Faker faker = new Faker(new Locale("es"));
        String nombre = faker.name().name().replace("'", "");
        String correo = faker.internet().emailAddress();
        String contrasena = faker.internet().password();
        Usuario user = new Usuario(nombre, correo, contrasena);
        logicaCrearUsuario(user);
    }

    public static void insertarUsuarioFaker(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            crearUsuario();
        }
    }

    public static void selectAllFromUsuario() throws SQLException {
        System.out.println("Lista de Usuarios Registrados");
        ejecutarMostrarSQL(SELECT_ALL_FROM_USUARIO);

    }

    public static List<Usuario> selectAllFromUsuarioWithReturn() throws SQLException {

        System.out.println("Lista de Usuarios Registrados");
        List<Usuario> usuarios = new ArrayList<>();
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_USUARIO);
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResulset();
        while(resultSet.next()){
            Usuario usuario = new Usuario();
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setCorreo(resultSet.getString("correo"));
            usuario.setContrasena(resultSet.getString("contrasenha"));
            usuarios.add(usuario);
        }
        return usuarios;

    }
    public static List<Publicacion> selectAllFromPublicacionWithReturn() throws SQLException {
        System.out.println("Lista de Usuarios Registrados");
        List<Publicacion> publicaciones = new ArrayList<>();
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_PUBLICACION);
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResulset();
        while(resultSet.next()){
            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo(resultSet.getString("titulo"));
            publicacion.setAutor(resultSet.getString("autor"));
            publicacion.setTipo(resultSet.getString("tipo"));
            publicacion.setNumPaginas(Integer.parseInt(resultSet.getString("numPaginas")));
            publicacion.setCantEjemplares(Integer.parseInt(resultSet.getString("cantEjemplares")));
            publicacion.setCantPrestados(Integer.parseInt(resultSet.getString("cantPrestados")));
            publicacion.setCantiDisponibles(Integer.parseInt(resultSet.getString("cantDisponibles")));
            publicaciones.add(publicacion);
        }
        return publicaciones;
    }

    public static void modificarUsuario() throws SQLException {
        String correoUsuario = JOptionPane.showInputDialog(null, "Ingrese el correo: ");
        String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre: ");
        String nuevaContrasenha = JOptionPane.showInputDialog(null, "Ingrese la nueva contraseña: ");
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_USUARIO);
        mySqlOperation.executeSqlStatement();
        ResultSet usuario = mySqlOperation.getResulset();
        boolean encontrado = false;
        while (usuario.next()) {
            String correo = usuario.getString("correo");
            if (correo.equals(correoUsuario)) {
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            String updateStatement = String.format(UPDATE_USUARIO, nuevoNombre, nuevaContrasenha, correoUsuario);
            mySqlOperation.setSqlStatement(updateStatement);
            mySqlOperation.executeSqlStatementVoid();
            System.out.println("Usuario actualizado exitosamente.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }




}
