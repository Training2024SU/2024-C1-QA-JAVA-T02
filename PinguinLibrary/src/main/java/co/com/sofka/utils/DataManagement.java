package co.com.sofka.utils;

import co.com.sofka.enums.ResourceType;
import co.com.sofka.model.Essay;
import co.com.sofka.model.Resource;
import co.com.sofka.model.Song;
import co.com.sofka.model.VideoRecording;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManagement {
    private DataManagement() {
        throw new IllegalStateException("Utility Class");
    }

    public static void exportResourcesToJson(String fileName, List<Resource> resources) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(fileName), resources);
    }

    public static void exportResourcesToXML(String fileName, List<Resource> resources) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(fileName), resources);
    }

    public static List<Resource> importFromFile(String fileName, String extension,
                                                ResourceType resourceType) throws IOException {
        File file = new File(fileName + extension);
        ObjectMapper objectMapper;
        switch (extension) {
            case ".json" -> objectMapper = new ObjectMapper();
            case ".xml" -> objectMapper = new XmlMapper();
            default -> throw new IllegalArgumentException("cannot read file: " + extension);
        }
        switch (resourceType) {
            case VIDEO_RECORDING -> {
                List<VideoRecording> videos = objectMapper.readValue(file,
                        new TypeReference<List<VideoRecording>>() {
                        });
                return new ArrayList<>(videos);
            }
            case SONG -> {
                List<Song> songs = objectMapper.readValue(file, new TypeReference<List<Song>>() {
                });
                return new ArrayList<>(songs);
            }
            case ESSAY -> {
                List<Essay> essays = objectMapper.readValue(file, new TypeReference<List<Essay>>() {
                });
                return new ArrayList<>(essays);
            }
            default -> throw new IllegalStateException("Unexpected value: " + resourceType);
        }
    }
}
