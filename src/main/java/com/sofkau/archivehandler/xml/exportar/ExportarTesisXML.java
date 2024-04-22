package com.sofkau.archivehandler.xml.exportar;

import com.sofkau.logica.CommonHandlerOperation.xml.XmlWriter;
import com.sofkau.logica.tesis.TesisOperaciones;
import com.sofkau.model.Tesis;

public class ExportarTesisXML {
    private static TesisOperaciones tesisOp = new TesisOperaciones();

    public static void exportarTesisXml() {
        XmlWriter<Tesis> xmlWriter = new XmlWriter<>();
        xmlWriter.exportArrayListToXml(tesisOp.getListaTesis(),
                "src/main/java/com/sofkau/archivehandler/archives/tesisExportadas.xml"
        );
    }
}

