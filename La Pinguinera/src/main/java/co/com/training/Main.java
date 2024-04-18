package co.com.training;

import co.com.training.integration.database.mysql.MySqlOperation;
import co.com.training.logica.CrudLibro;
import co.com.training.logica.CrudNovela;
import co.com.training.logica.util.InputOutputUtil;
import co.com.training.modelo.Libro;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static co.com.training.integration.database.mysql.Constantes.*;

public class Main {

    private static final String DATA_BASE_NAME = "la_pinguinera_biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";

    private static final String SELECT_ALL_FROM_LIBRO = String.format("select * from %s.libro", DATA_BASE_NAME);
    private static final MySqlOperation mySqlOperation = new MySqlOperation();
    private static CrudLibro crudLibro;
    private static CrudNovela crudNovela;

    public static void main(String[] args) throws SQLException {

        openConnection();
        insertarLibroEnBd(preguntarAlUsuario());
        selectAllFromLibro();
        closeConnection();

    }

    private static int preguntarAlUsuario() {
        Scanner scanner = new Scanner(System.in);
        int cantidad;
        System.out.println(MENSAJE_LIBRO);
        cantidad = Integer.parseInt(scanner.nextLine());
        return cantidad;
    }

    private static void insertarLibroEnBd(int cantidadLibros) {
        for (int i = 0; i < cantidadLibros; i++) {
            System.out.println("Libro: " + i+1 + "\n");
            insertarLibro();
        }
    }

    public static void openConnection() {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
        crudLibro = new CrudLibro(mySqlOperation);
        crudNovela = new CrudNovela(mySqlOperation);
    }

    public static void selectAllFromLibro() throws SQLException {
        mySqlOperation.setSqlStatement(SELECT_ALL_FROM_LIBRO);
        mySqlOperation.executeSqlStatement();
        mySqlOperation.printColumnValue();
    }

    public static void insertarLibro() {
        Libro libro = InputOutputUtil.ingresarLibro();
        String sqlStatement = String.format(INSERT_LIBRO,
                libro.getTituloLibro(),
                libro.getAutor(),
                libro.getAreaConocimiento(),
                libro.getNumeroPaginas(),
                libro.getCantidadEjemplares(),
                libro.getCantidadPrestados()
                );
        mySqlOperation.setSqlStatement(sqlStatement);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void closeConnection() {
        mySqlOperation.close();
    }
}
