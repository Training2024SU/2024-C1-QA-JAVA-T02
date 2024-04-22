package com.sofkau.integration.repositorio;

import com.sofkau.integration.database.ConexionDatabase;
import com.sofkau.integration.database.mysql.MySqlOperation;
import com.sofkau.model.Tesis;
import com.sofkau.util.CommonOperacion.IngresoQuery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TesisRepositorio {

    private static MySqlOperation mySqlOperation = ConexionDatabase.getMySqlOperation();

    private static SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

    public static void crearTesis(Tesis tesis) {
        String query = String.format("INSERT INTO tesis (titulo, fecha, autor, campo_estudio, pais, cantidad_copia, cantidad_prestado) VALUES ('%s', '%s', '%s', '%s', '%s', %d, %d)",
                tesis.getTitulo(), formato.format(tesis.getFecha()), tesis.getAutor(), tesis.getCampoEstudio(), tesis.getPais(), tesis.getCantidadCopia(), tesis.getCantidadPrestado());
        IngresoQuery.ejecutarIngresoQuery(query);
    }

    public static ArrayList<Tesis> consultarTesis() {
        String query = "SELECT * FROM tesis";
        IngresoQuery.ejecutarConsultaQuery(query);
        ArrayList<Tesis> tesisList = new ArrayList<>();
        ResultSet resultSet = mySqlOperation.getResulset();

        try {
            while (resultSet.next()) {
                String titulo = resultSet.getString("titulo");
                Date fecha = resultSet.getDate("fecha");
                String autor = resultSet.getString("autor");
                String campoEstudio = resultSet.getString("campo_estudio");
                String pais = resultSet.getString("pais");
                int cantidadCopia = resultSet.getInt("cantidad_copia");
                int cantidadPrestado = resultSet.getInt("cantidad_prestado");

                Tesis tesis = new Tesis();
                tesis.setTitulo(titulo);
                tesis.setFecha(fecha);
                tesis.setAutor(autor);
                tesis.setCampoEstudio(campoEstudio);
                tesis.setPais(pais);
                tesis.setCantidadCopia(cantidadCopia);
                tesis.setCantidadPrestado(cantidadPrestado);

                tesisList.add(tesis);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tesisList;
    }

    public static void actualizarTesis(Tesis tesis) {
        String query = String.format("UPDATE tesis SET fecha = '%s', autor = '%s', campo_estudio = '%s', pais = '%s', cantidad_copia = %d, cantidad_prestado = %d WHERE titulo = '%s'",
                formato.format(tesis.getFecha()), tesis.getAutor(), tesis.getCampoEstudio(), tesis.getPais(), tesis.getCantidadCopia(), tesis.getCantidadPrestado(), tesis.getTitulo());
        IngresoQuery.ejecutarIngresoQuery(query);
    }
}
