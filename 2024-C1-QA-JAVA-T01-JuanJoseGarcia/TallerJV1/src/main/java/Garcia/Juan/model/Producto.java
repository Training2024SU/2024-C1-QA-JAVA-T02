package Garcia.Juan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Producto {
    private String titulo;
    private String tipo;
    private String autor;
    private String magnitud;
    private int cantidadEjemplares;
    private int cantidadPrestados;
    private int cantidadDisponibles;
    private String genero;

    private int edadMinima;

    public Producto(String titulo, String tipo, String autor, String magnitud, int cantidadPrestados, int cantidadEjemplares, int cantidadDisponibles, String genero, int edadMinima) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.autor = autor;
        this.magnitud = magnitud; // Usamos 'magnitud' en lugar de 'numeroPaginas'
        this.cantidadPrestados = cantidadPrestados;
        this.cantidadEjemplares = cantidadEjemplares;
        this.cantidadDisponibles = cantidadDisponibles;
        this.genero = genero;
        this.edadMinima = edadMinima;
    }

    public Producto() {
        // Constructor por defecto
    }

    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMagnitud() {  // Cambiamos el nombre del método de 'getNumeroPaginas' a 'getMagnitud'
        return magnitud;
    }

    public void setMagnitud(String magnitud) {  // Cambiamos el nombre del método de 'setNumeroPaginas' a 'setMagnitud'
        this.magnitud = magnitud;
    }

    public int getCantidadEjemplares() {
        return cantidadEjemplares;
    }

    public void setCantidadEjemplares(int cantidadEjemplares) {
        this.cantidadEjemplares = cantidadEjemplares;
    }

    public int getCantidadPrestados() {
        return cantidadPrestados;
    }

    public void setCantidadPrestados(int cantidadPrestados) {
        this.cantidadPrestados = cantidadPrestados;
    }

    public int getCantidadDisponibles() {
        return cantidadDisponibles;
    }

    public void setCantidadDisponibles(int cantidadDisponibles) {
        this.cantidadDisponibles = cantidadDisponibles;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdadMinima() {
        return edadMinima;
    }

    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }

    // Métodos `toString` para diferentes representaciones de `Producto`
    @Override
    public String toString() {
        return "Producto{" +
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", autor='" + autor + '\'' +
                ", magnitud='" + magnitud + '\'' +
                ", cantidadEjemplares=" + cantidadEjemplares +
                ", cantidadPrestados=" + cantidadPrestados +
                ", cantidadDisponibles=" + cantidadDisponibles +
                '}';
    }

    public String toStringLibros() {
        return "Producto{ " +
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", autor='" + autor + '\'' +
                ", magnitud='" + magnitud + '\'' +
                ", cantidadDisponibles=" + cantidadDisponibles +
                ", Area=" + genero +
                '}';
    }

    public String toStringNovelas() {
        return "Producto{ " +
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", autor='" + autor + '\'' +
                ", magnitud='" + magnitud + '\'' +
                ", cantidadDisponibles=" + cantidadDisponibles +
                ", genero=" + genero +
                ", Edad mínima=" + edadMinima +
                '}';
    }

    public String toStringAutor() {
        return "Producto{ " +
                "Autor='" + autor + '\'' +
                ", titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", magnitud='" + magnitud + '\'' +
                ", cantidadDisponibles=" + cantidadDisponibles +
                '}';
    }

    public String toStringPrestamo() {
        return "Producto{ " +
                "titulo='" + titulo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}
