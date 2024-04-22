package co.com.pinguinera.datos.model;

public class Usuario {
    private int idUsuario; // Nuevo campo `idUsuario`
    private String correo;
    private String nombre;
    private String contrasena;
    private String edad;
    private String telefono;

    public Usuario() {
        // Constructor vacío
    }

    // Constructor con todos los campos
    public Usuario(int idUsuario, String correo, String nombre, String contrasena,String edad,String telefono) {
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.edad= edad;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        final String RESET = "\033[0m";
        final String GREEN = "\033[32m";

        String columnas = String.format(
                "%-10s %-30s %-20s %-20s",
                "ID", "Correo", "Nombre", "Contraseña"
        );

        String datos = String.format(
                "%s%-10d%s %s%-30s%s %s%-20s%s %s%-20s%s",
                GREEN, idUsuario, RESET,
                GREEN, correo, RESET,
                GREEN, nombre, RESET,
                GREEN, contrasena, RESET
        );
        return "\n" + columnas + "\n" + datos;
    }

}

