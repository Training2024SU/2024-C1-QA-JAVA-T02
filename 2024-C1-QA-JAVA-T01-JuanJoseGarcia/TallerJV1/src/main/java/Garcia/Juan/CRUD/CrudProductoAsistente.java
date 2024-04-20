package Garcia.Juan.CRUD;

import Garcia.Juan.database.mysql.MySqlOperation;

import java.sql.SQLException;
import java.util.Scanner;

public class CrudProductoAsistente {
    private static MySqlOperation mySqlOperation;

    public CrudProductoAsistente(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    private static final String INSERT_PRODUCT = "INSERT INTO bibliotecapingu.producto (titulo, tipo, autor, magnitud, cant_ejemplares, cant_prestados) VALUES ('%s', '%s', '%s', %d, %d, %d)";
    private static final String DELETE_PRODUCT = "DELETE FROM bibliotecapingu.producto where titulo='%s'";
    private static final String OBTAIN_PRODUCT = "DELETE FROM bibliotecapingu.producto where titulo='%s'";
    private static final String UPDATE_PRODUCT = "UPDATE libro SET titulo = '%s', autor = '%s' area_conocimiento = '%s', nu_paginas = '%s', cantidad_ejemplares = '%s', cantidad_prestados = '%s', cantidad_disponibles = '%s', area_genero = '%s'  WHERE titulo = '%s'";

    public static void insertProduct(MySqlOperation mySqlOperation){
        String statement = obtenerInfoProduct();
        mySqlOperation.setSqlStatement(statement);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static String obtenerInfoProduct() {
        Scanner scanner = new Scanner(System.in);

        // Solicita el título del producto al usuario
        System.out.println("Inserte el título del producto: ");
        String titulo = scanner.nextLine();

        // Solicita el tipo de producto al usuario
        System.out.println("Inserte el tipo de producto (LIBRO, NOVELA, CANCION, VIDEOGRABACION, ENSAYO): ");
        String tipo = scanner.nextLine();

        // Solicita el autor del producto al usuario
        System.out.println("Ingrese el autor del producto: ");
        String autor = scanner.nextLine();

        // Solicita el número de páginas del producto al usuario
        System.out.println("Ingrese la magnitud del producto (PAGINAS, DURACION(segundos)): ");
        int numeroPaginas = Integer.parseInt(scanner.nextLine());

        // Solicita el número de ejemplares del producto al usuario
        System.out.println("Ingrese el número de ejemplares del producto: ");
        int cantidadEjemplares = Integer.parseInt(scanner.nextLine());

        // Establece el número de préstamos inicial en cero
        int cantidadPrestados = 0;

        // Devuelve la consulta de inserción formateada utilizando `String.format`
        return String.format(INSERT_PRODUCT, titulo, tipo, autor, numeroPaginas, cantidadEjemplares, cantidadPrestados);
    }

    protected static String PreguntarAlUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Inserte el titulo del libro: ");
        String titulo = scanner.nextLine();
        return titulo;
    }
    public static void deleteProduct(MySqlOperation mySqlOperation){
        String deleteStatement = String.format(DELETE_PRODUCT,PreguntarAlUsuario());
        mySqlOperation.setSqlStatement(deleteStatement);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void obtainProduct(MySqlOperation mySqlOperation){
        String obtainStatement = String.format(OBTAIN_PRODUCT,PreguntarAlUsuario());
        mySqlOperation.setSqlStatement(obtainStatement);
        mySqlOperation.executeSqlStatementVoid();
    }

    public static void updateProduct(MySqlOperation mySqlOperation){
        String updateStatement = PreguntarAlUsuarioUpdate(PreguntarAlUsuario());
        mySqlOperation.setSqlStatement(updateStatement);
        mySqlOperation.executeSqlStatementVoid();
    }

    protected static String PreguntarAlUsuarioUpdate(String titulo) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Inserte el nuevo titulo del libro: ");
        String tituloNuevo = scanner.nextLine();
        System.out.printf("Inserte el nuevo autor del libro: ");
        String autor = scanner.nextLine();
        System.out.printf("Inserte el área de conocimiento nueva: ");
        String areaConocimiento = scanner.nextLine();
        System.out.printf("Inserte el nuevo numero de pagias: ");
        int nuPaginas = Integer.parseInt(scanner.nextLine());
        System.out.printf("Inserte el nuevo numero de ejemplares: ");
        int cantEjemplares = Integer.parseInt(scanner.nextLine());
        System.out.printf("Inserte el nuevo numero de ejemplares disponibles: ");
        int cantDisp = Integer.parseInt(scanner.nextLine());
        int cantPrest =cantEjemplares-cantDisp;
        System.out.print("Ingrese el area/genero del producto: ");
        String area = scanner.nextLine();
        return String.format(UPDATE_PRODUCT, tituloNuevo,areaConocimiento,nuPaginas,cantEjemplares,cantPrest,cantDisp,titulo,area);
    }

}
