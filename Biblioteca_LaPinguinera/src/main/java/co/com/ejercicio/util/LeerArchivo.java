package co.com.ejercicio.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class LeerArchivo {

    public static void leerArchivoJson(String path){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
            String line;
            // Read and print each line of the JSON file
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
    }

    public static void leerArchivoXml(String path){

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            // Read and print each line of the XML file
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading XML file: " + e.getMessage());
        }
    }
}
