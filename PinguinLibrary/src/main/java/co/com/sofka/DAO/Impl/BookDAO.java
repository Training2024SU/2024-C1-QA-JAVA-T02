package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.IBookDAO;
import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.Author;
import co.com.sofka.entities.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements IBookDAO {
    private static final String insertIntoQuery = "INSERT INTO BOOK " +
            "(book_id, title, quantity, quantity_loaned, quantity_available, category, author_id) " +
            "VALUES ('%s', '%s', %d, %d, %d, '%s', '%s');";
    private static final String selectAllQuery = "SELECT book_id, " +
            "title, " +
            "quantity, " +
            "quantity_loaned, " +
            "quantity_available, " +
            "category, " +
            "author_id " +
            "FROM book;";
    private static final String updateQuery = "UPDATE BOOK SET " +
            "title = '%s', " +
            "quantity = %d, " +
            "quantity_loaned = %d, " +
            "quantity_available = %d, " +
            "category = '%s', " +
            "author_id = '%s' " +
            "WHERE book_id = '%s';";
    private static final String deleteQuery = "DELETE FROM BOOK WHERE book_id = '%s';";

    private final MySqlOperation mySqlOperation;
    public BookDAO(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    @Override
    public void insertBook(Book book) {
        String query = String.format(insertIntoQuery,
                book.getId(),
                book.getTitle(),
                book.getQuantity(),
                book.getQuantityLoaned(),
                book.getQuantityAvailable(),
                book.getCategory(),
                book.getAuthor().getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSet = mySqlOperation.getResultSet()) {
            while (resultSet.next()) {
                String id = resultSet.getString("book_id");
                String title = resultSet.getString("title");
                String category = resultSet.getString("category");
                String authorId = resultSet.getString("author_id");
                int quantity = resultSet.getInt("quantity");
                int quantityLoaned = resultSet.getInt("quantity_loaned");
                int quantityAvailable = resultSet.getInt("quantity_available");


                Author author = getAuthorById(authorId);


                Book book = new Book(id, quantity, quantityLoaned, quantityAvailable, title, author, category);
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching books: " + e.getMessage(), e);
        }

        return books;
    }

    @Override
    public Book getBookById(String id) {
        Book book = null;
        try {
            String query = selectAllQuery + " WHERE book_id = '" + id + "'";
            mySqlOperation.setSqlStatement(query);
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResultSet();
            if (resultSet.next()) {
                String title    = resultSet.getString("title");
                String category = resultSet.getString("category");
                String authorId = resultSet.getString("author_id");
                int quantity    = resultSet.getInt("quantity");
                int quantityLoaned    = resultSet.getInt("quantity_loaned");
                int quantityAvailable = resultSet.getInt("quantity_available");

                Author author = getAuthorById(authorId);

                book = new Book(id, quantity, quantityLoaned, quantityAvailable, title, author, category);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching book by ID: " + e.getMessage(), e);
        }
        return book;
    }

    private Author getAuthorById(String authorId) throws SQLException {
        AuthorDAO authorDAO = new AuthorDAO(mySqlOperation);
        return authorDAO.getAuthorById(authorId);
    }
    @Override
    public void updateBook(Book book) {
        String query = String.format(updateQuery,
                book.getTitle(),
                book.getQuantity(),
                book.getQuantityLoaned(),
                book.getQuantityAvailable(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
    @Override
    public void deleteBook(Book book) {
        String query = String.format(deleteQuery, book.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
}
