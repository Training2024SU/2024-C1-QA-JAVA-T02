package com.sofkau.logica.CommonHandlerOperation.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonReader<T> {

    // Leer archivo json y retornar un arraylist
    public ArrayList<T> leerJsonArrayArchivo(String rutaArchivo, Class<T> valueType) {
        // Instanciar objeto ObjectMapper que permite parsear de json al tipo del objeto
        ObjectMapper objectMapper = new ObjectMapper();
        // Crear arraylist generico
        ArrayList<T> lista = new ArrayList<>();
        try {
            // Obtener el objeto File del archivo utilizando la ruta
            File archivoJson = Paths.get(rutaArchivo).toFile();
            //Indica el tipo de la lista
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, valueType);
            // Se utiliza el object mapper para leer el archivo json y devolver una iista
            // del tipo de objeto especificado
            lista = objectMapper.readValue(archivoJson, listType);
        } catch (IOException e) {
            System.out.println("Error => "+ e);
        }
        return lista;
    }
}

