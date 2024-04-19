package com.davidbonelo.utils;

import com.davidbonelo.models.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {
    private static final Gson gson = new Gson();

    public static <T> void writeJson(List<T> objects, String fileName) {
        String filePath = "./src/main/resources/JsonOutFiles/";
        try (Writer writer = new FileWriter(filePath + fileName)) {
            gson.toJson(objects, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Book> readBookJsonFile(String fileName) {
        String filePath = "./src/main/resources/JsonInputFiles/";
        try (Reader reader = new FileReader(filePath + fileName)) {
            Type type = new TypeToken<List<Book>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
