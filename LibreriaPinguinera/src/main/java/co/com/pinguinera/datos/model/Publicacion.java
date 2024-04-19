package co.com.pinguinera.datos.model;
import co.com.pinguinera.datos.model.enums.TipoPublicacion;


public class Publicacion {
    private int idPublicacion;
    private String titulo;
    private TipoPublicacion tipoPublicacion;
    private String autor;
    private String formato;
    private int cantEjemplares;
    private int cantPrestados;
    private int cantDisponible;

    public Publicacion() {
        // Constructor vacío
    }

    // Constructor con todos los campos, incluyendo `idPublicacion`
    public Publicacion(int idPublicacion, String titulo, TipoPublicacion tipoPublicacion, String autor, String formato, int cantEjemplares, int cantPrestados, int cantDisponible) {
        this.idPublicacion = idPublicacion;
        this.titulo = titulo;
        this.tipoPublicacion = tipoPublicacion;
        this.autor = autor;
        this.formato = formato;
        this.cantEjemplares = cantEjemplares;
        this.cantPrestados = cantPrestados;
        this.cantDisponible = cantDisponible;

    }

    // Getters y Setters
    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFormato() {        return formato;    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getCantEjemplares() {
        return cantEjemplares;
    }

    public void setCantEjemplares(int cantEjemplares) {
        this.cantEjemplares = cantEjemplares;
    }

    public int getCantPrestados() {
        return cantPrestados;
    }

    public void setCantPrestados(int cantPrestados) {
        this.cantPrestados = cantPrestados;
    }

    public int getCantDisponible() {
        return cantDisponible;
    }

    public void setCantDisponible(int cantDisponible) {
        this.cantDisponible = cantDisponible;
    }


    @Override
    public String toString() {
        final String RESET = "\033[0m"; // Restablecer color
        final String GREEN = "\033[32m"; // Verde

        String columnas = String.format(
                "%-10s %-40s %-25s %-10s %-12s %-12s %-12s",
                "ID", "Título", "Autor", "Formato", "Ejemplares", "Prestados", "Disponible"
        );
        String datos = String.format(
                "%s%-10d%s %s%-40s%s %s%-25s%s %s%-10d%s %s%-12d%s %s%-12d%s %s%-12d%s",
                GREEN, idPublicacion, RESET,
                GREEN, titulo, RESET,
                GREEN, autor, RESET,
                GREEN, formato, RESET,
                GREEN, cantEjemplares, RESET,
                GREEN, cantPrestados, RESET,
                GREEN, cantDisponible, RESET
        );

        return "\n" + columnas + "\n" + datos;
    }

}
