package co.com.sofka.database.mysql;

import co.com.sofka.database.DataBase;


import java.sql.*;

public class MySqlOperation implements DataBase {

    private Connection connection = null;
    private Statement statement   = null;
    private ResultSet resultSet   = null;

    private String sqlStatement;
    private String server;
    private String dataBaseName;
    private String user;
    private String password;


    public String getSqlStatement() {
        return sqlStatement;
    }

    public void setSqlStatement(String sqlStatement) {
        this.sqlStatement = sqlStatement;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void configureDataBaseConnection() {
        try {
            Class.forName(MySqlConstants.MY_SQL_JDBC_DRIVER);
            connection= DriverManager.getConnection(
                    String.format(MySqlConstants.CONNECTION_STRING,
                            this.server,
                            this.dataBaseName,
                            this.user,
                            this.password)
            );
            statement=connection.createStatement();

        }catch (Exception e){
            close();
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void executeSqlStatement() {
        try {
            configureDataBaseConnection();
            resultSet = statement.executeQuery(sqlStatement);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void executeSqlStatementVoid() {
        try {
            configureDataBaseConnection();
            statement.execute(sqlStatement);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public ResultSet getResultSet() {
        return resultSet;
    }

    @Override
    public void close() {
        try {
            if(resultSet !=null){
                resultSet.close();
            }
            if(statement !=null){
                statement.close();
            }
            if(connection !=null){
                connection.close();
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void printResultSet() throws SQLException {

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int totalColumnNumber = resultSetMetaData.getColumnCount();
        while (resultSet.next()) {
            for (int columnNumber = 1; columnNumber <= totalColumnNumber; columnNumber++) {
                if (columnNumber > 1) {
                    System.out.print(",\t");
                }
                String columnValue = resultSet.getString(columnNumber);
                System.out.print(resultSetMetaData.getColumnName(columnNumber) + ": " + columnValue);
            }
            System.out.println();
        }

    }
}
