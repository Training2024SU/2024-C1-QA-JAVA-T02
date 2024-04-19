package co.com.pinguinera.vistas.vistas_libro;

import co.com.pinguinera.vistas.VistaUtil;

public class InformacionLibroVista {

    public String pedirTituloLibro() {
        return VistaUtil.pedirTitulo();
    }

    public String pedirAutorLibro() {
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
