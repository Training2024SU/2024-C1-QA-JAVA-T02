package com.sofkau.archivehandler.json.exportar;

import com.sofkau.logica.CommonHandlerOperation.json.JsonWriter;
import com.sofkau.logica.tesis.TesisOperaciones;
import com.sofkau.model.Tesis;

public class ExportarTesisJson {
    private static TesisOperaciones tesisOp = new TesisOperaciones();

    public static void exportarTesisJson() {
        JsonWriter jsonWriter = new JsonWriter();
        jsonWriter.exportarArrayListJson(tesisOp.getListaTesis(),
                "src/main/java/com/sofkau/archivehandler/archives/tesisExportadas.json"
        );
    }
}
