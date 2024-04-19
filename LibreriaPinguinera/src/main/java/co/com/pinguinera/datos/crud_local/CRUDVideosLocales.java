package co.com.pinguinera.datos.crud_local;

import co.com.pinguinera.datos.model.publicaciones.Videograbaciones;

import java.util.ArrayList;
import java.util.List;

public class CRUDVideosLocales extends AbstractLocalCRUD<Videograbaciones>{
    // El constructor llama al constructor de la clase base
    public CRUDVideosLocales() {
        super(); // Llama al constructor de la clase base `AbstractLocalCRUD`
    }

    // Método para obtener todos los libros de la lista local
    public List<Videograbaciones> obtenerTodos() {
        // Devuelve la lista completa de libros locales
        return new ArrayList<>(datosLocales);
    }
}
