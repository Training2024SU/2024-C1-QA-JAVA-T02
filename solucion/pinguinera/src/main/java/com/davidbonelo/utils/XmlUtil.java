package com.davidbonelo.utils;

import com.davidbonelo.models.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.List;

public class XmlUtil {
    private static final ObjectMapper xmlMapper = new XmlMapper();

    public static <T> void writeXml(List<T> objects, String fileName) {
        String filePath = "./src/main/resources/XMLOutFiles/";
        try (Writer writer = new FileWriter(filePath + fileName)) {
            xmlMapper.writeValue(writer, objects);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Book> readXmlFile(String fileName) {
        String filePath = "./src/main/resources/XMLInputFiles/";
        try (Reader reader = new FileReader(filePath + fileName)) {
            TypeReference<List<Book>> typeReference = new TypeReference<>() {};
            return xmlMapper.readValue(reader, typeReference);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
