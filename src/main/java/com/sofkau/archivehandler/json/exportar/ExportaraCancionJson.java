package com.sofkau.archivehandler.json.exportar;

import com.sofkau.logica.CommonHandlerOperation.json.JsonReader;
import com.sofkau.logica.CommonHandlerOperation.json.JsonWriter;
import com.sofkau.logica.cancion.CancionOperaciones;
import com.sofkau.model.Cancion;

import java.util.ArrayList;

public class ExportaraCancionJson {

    private static CancionOperaciones cancionOp = new CancionOperaciones();

    public static void exportarCancionJson(){
        JsonWriter jsonWriter = new JsonWriter();
        jsonWriter.exportarArrayListJson(cancionOp.getListacanciones(),
                "src/main/java/com/sofkau/archivehandler/archives/cancionesExportadas.json"
        );
    }
}
