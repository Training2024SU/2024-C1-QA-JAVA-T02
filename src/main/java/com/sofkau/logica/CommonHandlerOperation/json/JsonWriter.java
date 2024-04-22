package com.sofkau.logica.CommonHandlerOperation.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JsonWriter<T> {

    // Método para escribir un ArrayList en un archivo JSON
    public void exportarArrayListJson(ArrayList<T> arrayList, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        // Configurar ObjectMapper para que genere el JSON con formato legible
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            // Convertir el ArrayList a formato JSON y escribirlo en el archivo
            objectMapper.writeValue(new File(filePath), arrayList);
            System.out.println("ArrayList exportado exitosamente a JSON en " + filePath);
        } catch (IOException e) {
            System.out.println("Error al exportar el ArrayList a JSON: " + e.getMessage());
        }
    }
}
