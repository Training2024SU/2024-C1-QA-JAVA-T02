package co.com.ejercicio.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class LeerArchivo {

    public static String PATH_RECURSOS = "C:\\Users\\User\\OneDrive\\Documents\\sofka\\EntregasSubidasAGitHub\\ModuloJava\\2024-C1-QA-JAVA-T02\\Biblioteca_LaPinguinera\\src\\main\\resources";

    public static void leerArchivoJson(String nombreArchivo){

        String filePath = PATH_RECURSOS + "\\" + nombreArchivo + ".json";

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            // Read and print each line of the JSON file
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
    }

    public static void leerArchivoXml(String nombreArchivo){

        String filePath = PATH_RECURSOS + "\\" + nombreArchivo + ".xml";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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
