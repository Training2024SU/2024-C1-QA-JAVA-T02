package co.com.pinguinera.vistas.vista_cancion;

import co.com.pinguinera.vistas.VistaUtil;

public class InformacionCancionVista {
    public String pedirTituloCancion() {
        return VistaUtil.pedirTitulo();
    }

    public String pedirAutorCancion() {
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
