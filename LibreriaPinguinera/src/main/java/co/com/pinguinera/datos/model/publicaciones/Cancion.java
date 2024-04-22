package co.com.pinguinera.datos.model.publicaciones;

import co.com.pinguinera.datos.model.Publicacion;
import co.com.pinguinera.datos.model.enums.TipoPublicacion;

public class Cancion extends Publicacion {
    public Cancion() {
        //
        super();
        this.setTipoPublicacion(TipoPublicacion.CANCION);
    }

    public Cancion(int idPublicacion, String titulo, String autor, String formato, int cantEjemplares, int cantPrestados, int cantDisponible) {
        super(idPublicacion, titulo, TipoPublicacion.CANCION, autor, formato, cantEjemplares, cantPrestados, cantDisponible);
    }
}
