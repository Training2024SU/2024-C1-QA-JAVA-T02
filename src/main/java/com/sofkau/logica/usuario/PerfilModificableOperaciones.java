package com.sofkau.logica.usuario;

import com.sofkau.integration.repositorio.PerfilUsuarioRepositorio;
import com.sofkau.model.PerfilModificable;

import java.util.HashMap;

public class PerfilModificableOperaciones {

    HashMap<String, PerfilModificable> perfilesModificables = new HashMap<>();


    public PerfilModificableOperaciones() {
        getPerfiles();
    }

    public void getPerfiles(){
        HashMap<String, String> direcciones = PerfilUsuarioRepositorio.consultarDireccionesUsuarios();
        HashMap<String, String> contactos = PerfilUsuarioRepositorio.consultarDireccionesUsuarios();

        for (String idUsuario : direcciones.keySet()) {
            // Obtener la dirección y el contacto correspondientes a esta clave
            String direccion = direcciones.get(idUsuario);
            String contacto = contactos.get(idUsuario);

            // Crear un perfil modificable con la dirección y el contacto obtenidos
            PerfilModificable perfilModificable = new PerfilModificable(idUsuario, contacto, direccion);

            // Agregar el perfil modificable al HashMap
            perfilesModificables.put(idUsuario, perfilModificable);
        }
    }

    public void getPerfil(String correo){
        System.out.println(perfilesModificables.get(correo));
    }

    public void ingresarPerfil(PerfilModificable perfil){
        PerfilUsuarioRepositorio.ingresarDireccionUsuario(perfil.getId(), perfil.getDireccion());
        PerfilUsuarioRepositorio.ingresarContacto(perfil.getId(), perfil.getContacto());
        perfilesModificables.put(perfil.getId(),perfil);
    }

    public void actualizarPerfil(PerfilModificable perfil){
        PerfilUsuarioRepositorio.actualizarDireccionUsuario(perfil.getId(), perfil.getDireccion());
        PerfilUsuarioRepositorio.actualizarContactoUsuario(perfil.getId(), perfil.getContacto());
        perfilesModificables.put(perfil.getId(),perfil);
    }

}
