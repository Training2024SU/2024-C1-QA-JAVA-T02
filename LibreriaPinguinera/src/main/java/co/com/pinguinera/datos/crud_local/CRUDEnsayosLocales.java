package co.com.pinguinera.datos.crud_local;

import co.com.pinguinera.datos.model.publicaciones.Ensayos;

import java.util.ArrayList;
import java.util.List;

public class CRUDEnsayosLocales extends AbstractLocalCRUD<Ensayos>{
    public CRUDEnsayosLocales() {
        super(); // Llama al constructor de la clase base `AbstractLocalCRUD`
    }

    // Método para obtener todos los libros de la lista local
    public List<Ensayos> obtenerTodos() {
        // Devuelve la lista completa de libros locales
        return new ArrayList<>(datosLocales);
    }
}
