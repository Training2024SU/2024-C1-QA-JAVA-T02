package com.sofkau.archivehandler.xml.importar;

import com.sofkau.logica.CommonHandlerOperation.xml.XmlReader;
import com.sofkau.logica.cancion.CancionOperaciones;
import com.sofkau.model.Cancion;

import java.util.ArrayList;

public class ImportarCancionXML {
    private static CancionOperaciones cancionOp = new CancionOperaciones();
    private static ArrayList<Cancion> canciones = new ArrayList<>();

    public static void guardarCancionXml() {
        XmlReader<Cancion> xmlReader = new XmlReader<>();
        canciones = xmlReader.leerXmlArrayArchivo("src/main/java/com/sofkau/archivehandler/archives/canciones.xml",
                Cancion.class);
        for (Cancion cancion : canciones) {
            cancionOp.crearCancion(cancion);
        }
    }
}
