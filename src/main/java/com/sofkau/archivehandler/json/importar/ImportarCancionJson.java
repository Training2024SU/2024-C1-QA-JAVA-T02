package com.sofkau.archivehandler.json.importar;

import com.sofkau.logica.CommonHandlerOperation.json.JsonReader;
import com.sofkau.logica.cancion.CancionOperaciones;
import com.sofkau.model.Cancion;

import java.util.ArrayList;

public class ImportarCancionJson {

    private static CancionOperaciones cancionOp = new CancionOperaciones();
   private static ArrayList<Cancion> canciones = new ArrayList<>();
    public static void guardarCancionJson(){
        JsonReader<Cancion> jsonReader = new JsonReader<>();
        canciones = jsonReader.leerJsonArrayArchivo("src/main/java/com/sofkau/archivehandler/archives/canciones.json",
                Cancion.class);
        for (Cancion cancion : canciones) {
            cancionOp.crearCancion(cancion);
        }
    }

}
