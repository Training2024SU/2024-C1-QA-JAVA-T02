package co.com.pinguinera.vistas.vista_videograbacion;

import co.com.pinguinera.vistas.VistaUtil;

public class InformacionVideograbacionVista {

    public String pedirTituloVideo() {
        return VistaUtil.pedirTitulo();
    }

    public String pedirAutorVideo() {
        return VistaUtil.pedirAutor();
    }

    public String pedirFormato() {
        return VistaUtil.pedirFormato();
    }

    public int pedirCantEjemplares() {
        return VistaUtil.pedirCantEjemplares();
    }

    public int pedirCantPrestados() {
        return VistaUtil.pedirCantPrestados();
    }

}



