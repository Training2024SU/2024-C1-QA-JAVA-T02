package com.sofkau.logica.empleado;

import com.sofkau.integration.repositorio.PerfilEmpleadoRepositorio;
import com.sofkau.model.PerfilModificable;

import java.util.HashMap;

public class PerfilEmpleadoOperaciones {

    private HashMap<String, PerfilModificable> perfilesModificables = new HashMap<>();

    public PerfilEmpleadoOperaciones() {
        getPerfiles();
    }

    public void getPerfiles() {
        HashMap<String, String> direcciones = PerfilEmpleadoRepositorio.consultarDireccionesEmpleados();
        HashMap<String, String> contactos = PerfilEmpleadoRepositorio.consultarContactosEmpleados();

        for (String idEmpleado : direcciones.keySet()) {
            // Obtener la dirección y el contacto correspondientes a esta clave
            String direccion = direcciones.get(idEmpleado);
            String contacto = contactos.get(idEmpleado);

            // Crear un perfil modificable con la dirección y el contacto obtenidos
            PerfilModificable perfilModificable = new PerfilModificable(idEmpleado, contacto, direccion);

            // Agregar el perfil modificable al HashMap
            perfilesModificables.put(idEmpleado, perfilModificable);
        }
    }

    public void getPerfil(String idEmpleado, String correo) {
        System.out.println("Contacto: "+perfilesModificables.get(idEmpleado).getContacto());
        System.out.println("Direccion: "+perfilesModificables.get(idEmpleado).getDireccion());
        System.out.println("Correo: "+correo);

    }

    public void ingresarPerfil(PerfilModificable perfil) {
        PerfilEmpleadoRepositorio.ingresarDireccionEmpleado(perfil.getId(), perfil.getDireccion());
        PerfilEmpleadoRepositorio.ingresarContactoEmpleado(perfil.getId(), perfil.getContacto());
        perfilesModificables.put(perfil.getId(), perfil);
    }

    public void actualizarPerfil(PerfilModificable perfil) {
        PerfilEmpleadoRepositorio.actualizarDireccionEmpleado(perfil.getId(), perfil.getDireccion());
        PerfilEmpleadoRepositorio.actualizarContactoEmpleado(perfil.getId(), perfil.getContacto());
        perfilesModificables.put(perfil.getId(), perfil);
    }
}

