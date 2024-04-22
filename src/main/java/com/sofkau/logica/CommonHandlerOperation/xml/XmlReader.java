package com.sofkau.logica.CommonHandlerOperation.xml;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XmlReader<T> {

    // Leer archivo XML y retornar un ArrayList
    public ArrayList<T> leerXmlArrayArchivo(String rutaArchivo, Class<T> valueType) {
        // Instanciar objeto XmlMapper que permite parsear de XML al tipo del objeto
        XmlMapper xmlMapper = new XmlMapper();
        // Crear ArrayList genérico
        ArrayList<T> lista = new ArrayList<>();
        try {
            // Obtener el objeto File del archivo utilizando la ruta
            File archivoXml = new File(rutaArchivo);
            // Leer el archivo XML y convertirlo en un ArrayList del tipo especificado
            lista = xmlMapper.readValue(archivoXml, xmlMapper.getTypeFactory().constructCollectionType(ArrayList.class, valueType));
        } catch (IOException e) {
            System.out.println("Error al leer el archivo XML: " + e.getMessage());
        }
        return lista;
    }
}