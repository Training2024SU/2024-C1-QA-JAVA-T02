package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.IAuthorDAO;
import co.com.sofka.database.mysql.MySqlConstants;
import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO implements IAuthorDAO {

    private static final String insertIntoQuery = "INSERT INTO author (id, name) VALUES ('%s', '%s');";
    private static final String selectAllQuery = "SELECT id, name FROM author";
    private static final String updateQuery = "UPDATE author SET " +
            "name = '%s' " +
            "WHERE id = '%s';";
    private static final String deleteQuery = "DELETE FROM author WHERE id = '%s';";

    private final MySqlOperation mySqlOperation;
    public AuthorDAO(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    @Override
    public void insertAuthor(Author author) {
        String query = String.format(insertIntoQuery, author.getId(), author.getName());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();

        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResultSet();

        try {
            while(resultSet.next()){
                String id   = resultSet.getString("id");
                String name = resultSet.getString("name");
                authors.add(new Author(id, name));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return authors;
    }

    @Override
    public Author getAuthorById(String id) {
        String query  = selectAllQuery + " WHERE id = " + id;
        Author author = new Author();
        ResultSet resultSet;
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatement();
        resultSet = mySqlOperation.getResultSet();
        try{
            while (resultSet.next()){
                String foundId   = resultSet.getString("id");
                String foundName = resultSet.getString("name");
                author.setId(foundId);
                author.setName(foundName);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return author;
    }

    @Override
    public void updateAuthor(Author author) {
        String query = String.format(updateQuery, author.getName(), author.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public void deleteAuthor(Author author) {
        String query = String.format(deleteQuery, author.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
}
