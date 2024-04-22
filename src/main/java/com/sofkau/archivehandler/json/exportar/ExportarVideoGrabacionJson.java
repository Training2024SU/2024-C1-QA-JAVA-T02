package com.sofkau.archivehandler.json.exportar;

import com.sofkau.logica.CommonHandlerOperation.json.JsonWriter;
import com.sofkau.logica.videograbacion.VideoGrabacionOperaciones;


public class ExportarVideoGrabacionJson {
    private static VideoGrabacionOperaciones videoOp = new VideoGrabacionOperaciones();

    public static void exportarVideoGrabacionJson() {
        JsonWriter jsonWriter = new JsonWriter();
        jsonWriter.exportarArrayListJson(videoOp.getListaVideoGrabaciones(),
                "src/main/java/com/sofkau/archivehandler/archives/videoGrabacionesExportadas.json"
        );
    }
}
