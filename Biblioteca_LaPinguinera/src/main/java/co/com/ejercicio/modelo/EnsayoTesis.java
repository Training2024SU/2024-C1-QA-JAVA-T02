package co.com.ejercicio.modelo;

public class EnsayoTesis {
    private String titulo;
    private String autor;
    private int numeroPaginas;
    private int cantidadEjemplares;
    private int cantidadPrestado;
    private int cantidadDisponible;

    public EnsayoTesis(String titulo, String autor, int numeroPaginas, int cantidadEjemplares, int cantidadPrestado, int cantidadDisponible) {
        this.titulo = titulo;
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
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
        return "EnsayoTesis{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", numeroPaginas=" + numeroPaginas +
                ", cantidadEjemplares=" + cantidadEjemplares +
                ", cantidadPrestado=" + cantidadPrestado +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }
}
