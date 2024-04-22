package com.sofka.util;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.SQLException;

import static com.sofka.controllers.ControlUsuario.selectAllFromPublicacionWithReturn;
import static com.sofka.controllers.ControlUsuario.selectAllFromUsuarioWithReturn;

public class JsonUtil {

        private static final Gson gson = new Gson();

        public static void escribirJson(Object objeto, String nombreArchivo) {
            try (Writer writer = new FileWriter(nombreArchivo)) {
                gson.toJson(objeto, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static <T> T leerJson(String nombreArchivo, Class<T> clase) {
            try (Reader reader = new FileReader(nombreArchivo)) {
                return gson.fromJson(reader, clase);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        public static void escribirUsuarios() throws SQLException {
            JsonUtil.escribirJson(selectAllFromUsuarioWithReturn(), "Listausuarios");

        }
    public static void escribirPublicaciones() throws SQLException {
        JsonUtil.escribirJson(selectAllFromPublicacionWithReturn(), "ListaPublicaciones");

    }

    }

