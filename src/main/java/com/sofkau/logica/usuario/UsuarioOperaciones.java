package com.sofkau.logica.usuario;

import com.sofkau.dialogo.MensajeOperacionBd;
import com.sofkau.integration.repositorio.UsuarioRepositorio;
import com.sofkau.model.Usuario;

import java.util.HashMap;
import java.util.Optional;

public class UsuarioOperaciones {

    public UsuarioOperaciones() {
        getUsuarios();
    }

    private Usuario usuarioActual = new Usuario();
    private HashMap<String, Usuario> usuarios = new HashMap<>();


    public void registrarUsuario(Usuario usuario) {
        UsuarioRepositorio.crearUsuario(usuario);
        usuarios.put(usuario.getCorreo(),usuario);
        MensajeOperacionBd.crearUsuario();
        System.out.println(usuario);
    }

    public void getUsuarios() {
        usuarios = UsuarioRepositorio.consultarUsuarios();
    }

    public boolean inicioSesion(String correo, String contrasena) {
        Optional<Usuario> usuarioVal = usuarios.values().stream()
                .filter(usuario -> usuario.getCorreo().equals(correo) && usuario.getContrasena().equals(contrasena))
                .findFirst();

        if(usuarioVal.isPresent()){
            usuarioActual = usuarioVal.get();
            return true;
        }
        return false;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void actualizarUsuario(Usuario usuario) {
        // Verificamos si el usuario a actualizar existe en la base de datos
        if (usuarios.containsKey(usuario.getCorreo())) {
            // Actualizamos el usuario en la base de datos
            UsuarioRepositorio.actualizarUsuario(usuario);
            // Actualizamos el usuario en la lista de usuarios en memoria
            usuarios.put(usuario.getCorreo(), usuario);
            // Asignamos el usuario actualizado como el usuario actual
            usuarioActual = usuario;
            System.out.println("Usuario actualizado correctamente: " + usuario);
        } else {
            System.out.println("El usuario con correo " + usuario.getCorreo() + " no existe en la base de datos.");
        }
    }
}
