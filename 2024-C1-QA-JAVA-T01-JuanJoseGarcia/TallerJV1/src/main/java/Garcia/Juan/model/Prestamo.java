package Garcia.Juan.model;

import java.util.Date;

public class Prestamo {
    private String id;
    private String estado;
    private Date fechaSalida;
    private Date fechaDevolucion;
    private String correoUsuario;

    public Prestamo() {
        // Constructor vacío
    }

    public Prestamo(String id, String estado, Date fechaSalida, Date fechaDevolucion, String correoUsuario) {
        this.id = id;
        this.estado = estado;
        this.fechaSalida = fechaSalida;
        this.fechaDevolucion = fechaDevolucion;
        this.correoUsuario = correoUsuario;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    @Override
    public String toString() {
        return String.format(
                "%-10s %-10s %-15s %-15s %-20s",
                id,
                estado,
                fechaSalida != null ? fechaSalida.toString() : "N/A",
                fechaDevolucion != null ? fechaDevolucion.toString() : "N/A",
                correoUsuario
        );
    }

}
