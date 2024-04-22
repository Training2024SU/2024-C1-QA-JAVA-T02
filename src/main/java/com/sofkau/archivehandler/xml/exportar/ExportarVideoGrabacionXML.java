package com.sofkau.archivehandler.xml.exportar;

import com.sofkau.logica.CommonHandlerOperation.xml.XmlWriter;
import com.sofkau.logica.videograbacion.VideoGrabacionOperaciones;
import com.sofkau.model.VideoGrabacion;

public class ExportarVideoGrabacionXML {
    private static VideoGrabacionOperaciones videoOp = new VideoGrabacionOperaciones();

    public static void exportarVideoGrabacionXml() {
        XmlWriter<VideoGrabacion> xmlWriter = new XmlWriter<>();
        xmlWriter.exportArrayListToXml(videoOp.getListaVideoGrabaciones(),
                "src/main/java/com/sofkau/archivehandler/archives/videoGrabacionesExportadas.xml"
        );
    }
}
