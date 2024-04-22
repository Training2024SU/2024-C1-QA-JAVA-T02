package co.com.pinguinera.exporting;

import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import com.google.gson.Gson;
import java.io.FileWriter;


public class Json {
    private static final Gson gson = new Gson();

    public static <T> void export(List<T> objects, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> importJson(String fileName, Class<T> clazz) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            Type type = TypeToken.getParameterized(List.class, clazz).getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}