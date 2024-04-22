package com.sofkau.logica.CommonHandlerOperation.xml;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XmlWriter<T> {

    // Método para escribir un ArrayList en un archivo XML
    public void exportArrayListToXml(ArrayList<T> arrayList, String filePath) {
        // Instanciar XmlMapper
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            // Convertir el ArrayList a formato XML y escribirlo en el archivo
            xmlMapper.writeValue(new File(filePath), arrayList);
            System.out.println("ArrayList exportado exitosamente a XML en " + filePath);
        } catch (IOException e) {
            System.out.println("Error al exportar el ArrayList a XML: " + e.getMessage());
        }
    }
}