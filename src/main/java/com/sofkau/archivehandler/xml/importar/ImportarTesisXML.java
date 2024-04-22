package com.sofkau.archivehandler.xml.importar;

import com.sofkau.logica.CommonHandlerOperation.xml.XmlReader;
import com.sofkau.logica.tesis.TesisOperaciones;
import com.sofkau.model.Tesis;

import java.util.ArrayList;

public class ImportarTesisXML {
    private static TesisOperaciones tesisOp = new TesisOperaciones();
    private static ArrayList<Tesis> tesisList = new ArrayList<>();

    public static void guardarTesisXml() {
        XmlReader<Tesis> xmlReader = new XmlReader<>();
        tesisList = xmlReader.leerXmlArrayArchivo("src/main/java/com/sofkau/archivehandler/archives/tesis.xml",
                Tesis.class);
        for (Tesis tesis : tesisList) {
            tesisOp.crearTesis(tesis);
        }
    }
}
