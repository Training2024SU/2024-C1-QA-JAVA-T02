package co.com.ejercicio.modelo;

public class Videograbacion {
    private String titulo;
    private String director;
    private String duracion;
    private int cantidadEjemplares;
    private int cantidadPrestado;
    private int cantidadDisponible;

    public Videograbacion(String titulo, String director, String duracion, int cantidadEjemplares, int cantidadPrestado, int cantidadDisponible) {
        this.titulo = titulo;
        this.director = director;
        this.duracion = duracion;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadPrestado = cantidadPrestado;
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getCantidadEjemplares() {
        return cantidadEjemplares;
    }

    public void setCantidadEjemplares(int cantidadEjemplares) {
        this.cantidadEjemplares = cantidadEjemplares;
    }

    public int getCantidadPrestado() {
        return cantidadPrestado;
    }

    public void setCantidadPrestado(int cantidadPrestado) {
        this.cantidadPrestado = cantidadPrestado;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    @Override
    public String toString() {
        return "VideoGrabacion{" +
                "titulo='" + titulo + '\'' +
                ", director='" + director + '\'' +
                ", duracion=" + duracion +
                ", cantidadEjemplares=" + cantidadEjemplares +
                ", cantidadPrestado=" + cantidadPrestado +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}
