package co.com.ejercicio.modelo;

import co.com.ejercicio.util.enums.Roles;

public class Administrador {

    private int id;
    private String nombre;

    private String correo;
    private String contrasenia;

    private String departamentoAdministrado;

    public Administrador(int id, String nombre, String correo, String contrasenia, String departamentoAdministrado) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.departamentoAdministrado = departamentoAdministrado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getDepartamentoAdministrado() {
        return departamentoAdministrado;
    }

    public void setDepartamentoAdministrado(String departamentoAdministrado) {
        this.departamentoAdministrado = departamentoAdministrado;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", departamentoAdministrado='" + departamentoAdministrado + '\'' +
                '}';
    }
}
