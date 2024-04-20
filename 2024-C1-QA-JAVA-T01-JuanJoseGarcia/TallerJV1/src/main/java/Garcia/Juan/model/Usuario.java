package Garcia.Juan.model;

public class Usuario {
    private String correo;
    private String nombre;
    private String contrasena;
    private String rol;
    private String telefono; // Nuevo campo para el número de teléfono
    private String direccion; // Nuevo campo para la dirección

    public Usuario(String correo, String nombre, String contrasena) {
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public Usuario() {
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", rol='" + rol + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
