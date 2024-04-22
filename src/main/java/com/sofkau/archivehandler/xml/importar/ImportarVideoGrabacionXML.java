package com.sofkau.archivehandler.xml.importar;

import com.sofkau.logica.CommonHandlerOperation.xml.XmlReader;
import com.sofkau.logica.videograbacion.VideoGrabacionOperaciones;
import com.sofkau.model.VideoGrabacion;

import java.util.ArrayList;

public class ImportarVideoGrabacionXML {
    private static VideoGrabacionOperaciones videoGrabacionOp = new VideoGrabacionOperaciones();
    private static ArrayList<VideoGrabacion> videoGrabaciones = new ArrayList<>();

    public static void guardarVideoGrabacionXml() {
        XmlReader<VideoGrabacion> xmlReader = new XmlReader<>();
        videoGrabaciones = xmlReader.leerXmlArrayArchivo("src/main/java/com/sofkau/archivehandler/archives/videograbaciones.xml",
                VideoGrabacion.class);
        for (VideoGrabacion videoGrabacion : videoGrabaciones) {
            videoGrabacionOp.crearVideoGrabacion(videoGrabacion);
        }
    }
}
