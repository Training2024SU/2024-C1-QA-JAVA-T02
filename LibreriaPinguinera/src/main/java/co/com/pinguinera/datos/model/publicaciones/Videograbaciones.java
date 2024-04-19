package co.com.pinguinera.datos.model.publicaciones;

import co.com.pinguinera.datos.model.Publicacion;
import co.com.pinguinera.datos.model.enums.TipoPublicacion;

public class Videograbaciones extends Publicacion {

    public Videograbaciones() {
        //
        super();
        this.setTipoPublicacion(TipoPublicacion.VIDEO);
    }

    public Videograbaciones(int idPublicacion, String titulo, String autor, String formato, int cantEjemplares, int cantPrestados, int cantDisponible) {
        super(idPublicacion, titulo, TipoPublicacion.VIDEO, autor, formato, cantEjemplares, cantPrestados, cantDisponible);
    }
}
