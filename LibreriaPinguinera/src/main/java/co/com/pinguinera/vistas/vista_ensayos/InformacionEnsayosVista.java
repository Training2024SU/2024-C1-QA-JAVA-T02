package co.com.pinguinera.vistas.vista_ensayos;

import co.com.pinguinera.vistas.VistaUtil;

public class InformacionEnsayosVista {
    public String pedirTituloEnsayo() {
        return VistaUtil.pedirTitulo();
    }

    public String pedirAutorEnsayo() {
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
