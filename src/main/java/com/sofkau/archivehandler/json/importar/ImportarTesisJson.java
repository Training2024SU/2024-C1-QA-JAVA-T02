package com.sofkau.archivehandler.json.importar;

import com.sofkau.logica.CommonHandlerOperation.json.JsonReader;
import com.sofkau.logica.tesis.TesisOperaciones;
import com.sofkau.model.Tesis;

import java.util.ArrayList;

public class ImportarTesisJson {

    private static TesisOperaciones tesisOp = new TesisOperaciones();
    private static ArrayList<Tesis> tesisList = new ArrayList<>();

    public static void guardarTesisJson() {
        JsonReader<Tesis> jsonReader = new JsonReader<>();
        tesisList = jsonReader.leerJsonArrayArchivo("src/main/java/com/sofkau/archivehandler/archives/tesis.json",
                Tesis.class);
        for (Tesis tesis : tesisList) {
            tesisOp.crearTesis(tesis);
        }
    }

}
