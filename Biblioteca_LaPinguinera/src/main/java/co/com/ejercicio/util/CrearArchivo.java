package co.com.ejercicio.util;

import org.json.JSONArray;

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class CrearArchivo {

    public static void crearArchivoJson(String path, String nombreArchivo,  JSONArray jsonArray){

        String filePath = path + "\\" + nombreArchivo + ".json";
        String texto = jsonArray.toString();

        guardarArchivo(filePath, texto);

    }

    public static void crearArchivoXML(String path, String nombreArchivo, Object obj) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(outputStream);
        xmlEncoder.writeObject(obj);
        xmlEncoder.close();

        String filePath = path + "\\" + nombreArchivo + ".xml";
        String texto = outputStream.toString();

        guardarArchivo(filePath, texto);
    }

    private static void guardarArchivo(String path, String texto){
        try (FileWriter fileWriter = new FileWriter(path)) { // Open file in write mode (existing content will be replaced)
            fileWriter.write(texto);
            System.out.println("File content successfully replaced!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file:");
            e.printStackTrace();
        }
    }
}
