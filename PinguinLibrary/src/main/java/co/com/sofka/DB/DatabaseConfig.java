package co.com.sofka.DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseConfig {
    public void configureDatabaseConnection();
    public void executeSqlStatement();
    public void executeSqlStatementVoid();
    public ResultSet getResultSet();
    public void close();
    public void printResultSet() throws SQLException;
}
