package com.sofkau.model;

public class PerfilModificable {
    private String id;
    private String contacto;
    private String direccion;

    public PerfilModificable() {
    }

    public PerfilModificable(String id, String contacto, String direccion) {
        this.id = id;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "PerfilModificable{" +
                "correo='" + id + '\'' +
                ", contacto='" + contacto + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
