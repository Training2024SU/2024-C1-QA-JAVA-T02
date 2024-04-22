package co.com.ejercicio.modelo;

public class Usuario {

    private String correo;
    private String nombre;
    private String contrasenia;
    private int edad; //Nuevo campo
    private String telefono; //Nuevo campo


    public Usuario(String correo,String nombre,String contrasenia, int edad, String telefono) {

        this.correo = correo;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.edad = edad;
        this.telefono = telefono;

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



    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
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
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
