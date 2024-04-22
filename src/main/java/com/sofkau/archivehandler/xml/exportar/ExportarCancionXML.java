package com.sofkau.archivehandler.xml.exportar;

import com.sofkau.logica.CommonHandlerOperation.xml.XmlWriter;
import com.sofkau.logica.cancion.CancionOperaciones;
import com.sofkau.model.Cancion;

public class ExportarCancionXML {
    private static CancionOperaciones cancionOp = new CancionOperaciones();

    public static void exportarCancionXml() {
        XmlWriter<Cancion> xmlWriter = new XmlWriter<>();
        xmlWriter.exportArrayListToXml(cancionOp.getListacanciones(),
                "src/main/java/com/sofkau/archivehandler/archives/cancionesExportadas.xml"
        );
    }
}
