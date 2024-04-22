package com.sofkau.archivehandler.json.importar;

import com.sofkau.logica.CommonHandlerOperation.json.JsonReader;
import com.sofkau.logica.videograbacion.VideoGrabacionOperaciones;
import com.sofkau.model.VideoGrabacion;


import java.util.ArrayList;

public class ImportarVideograbacionJson {

    private static VideoGrabacionOperaciones videograbacionOp = new VideoGrabacionOperaciones();
    private static ArrayList<VideoGrabacion> videograbaciones = new ArrayList<>();

    public static void guardarVideograbacionJson() {
        JsonReader<VideoGrabacion> jsonReader = new JsonReader<>();
        videograbaciones = jsonReader.leerJsonArrayArchivo("src/main/java/com/sofkau/archivehandler/archives/videograbaciones.json",
                VideoGrabacion.class);
        for (VideoGrabacion videograbacion : videograbaciones) {
            videograbacionOp.crearVideoGrabacion(videograbacion);
        }
    }

}
