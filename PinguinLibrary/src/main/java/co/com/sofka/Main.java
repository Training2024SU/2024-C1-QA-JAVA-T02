package co.com.sofka;

import co.com.sofka.DAO.Impl.AuthorDAO;
import co.com.sofka.database.DatabaseConfigConstants;
import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.Author;

import static co.com.sofka.database.DatabaseConfigConstants.*;

public class Main {


    private static final MySqlOperation mySqlOperation = new MySqlOperation();
    public static void main(String[] args) {
        openConnection();
        Author author = new Author("bb", "bb");
        AuthorDAO authorDAO = new AuthorDAO(mySqlOperation);
        authorDAO.insertAuthor(author);
        closeConnection();

    }
    public static void openConnection() {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
    }

    public static void closeConnection() {
        mySqlOperation.close();
    }
}