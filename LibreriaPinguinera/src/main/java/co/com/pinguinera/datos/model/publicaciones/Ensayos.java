package co.com.pinguinera.datos.model.publicaciones;

import co.com.pinguinera.datos.model.Publicacion;
import co.com.pinguinera.datos.model.enums.TipoPublicacion;

public class Ensayos extends Publicacion {
    public Ensayos() {
        //
        super();
    this.setTipoPublicacion(TipoPublicacion.ENSAYO);
    }

    public Ensayos(int idPublicacion, String titulo, String autor, String formato, int cantEjemplares, int cantPrestados, int cantDisponible) {
        super(idPublicacion, titulo, TipoPublicacion.ENSAYO, autor, formato, cantEjemplares, cantPrestados, cantDisponible);
    }
}
