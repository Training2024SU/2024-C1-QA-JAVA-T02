package co.com.pinguinera.datos.crud_local;

import co.com.pinguinera.datos.model.publicaciones.Cancion;

import java.util.ArrayList;
import java.util.List;

public class CRUDCancionesLocales extends AbstractLocalCRUD<Cancion>{
    public CRUDCancionesLocales() {
        super(); // Llama al constructor de la clase base `AbstractLocalCRUD`
    }

    // Método para obtener todos los libros de la lista local
    public List<Cancion> obtenerTodos() {
        // Devuelve la lista completa de libros locales
        return new ArrayList<>(datosLocales);
    }
}
