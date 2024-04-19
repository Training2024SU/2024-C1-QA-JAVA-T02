package co.com.ejercicio.modelo;

public class Cancion {
    private String titulo;
    private String artista;
    private String album;
    private String duracion;
    private int cantidadEjemplares;
    private int cantidadPrestado;
    private int cantidadDisponible;

    public Cancion(String titulo, String artista, String album, String duracion, int cantidadEjemplares, int cantidadPrestado, int cantidadDisponible) {
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
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

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
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
        return "Cancion{" +
                "titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", album='" + album + '\'' +
                ", duracion=" + duracion +
                ", cantidadEjemplares=" + cantidadEjemplares +
                ", cantidadPrestado=" + cantidadPrestado +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}
