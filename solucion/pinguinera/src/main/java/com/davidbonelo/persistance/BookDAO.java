package com.davidbonelo.persistance;

import com.davidbonelo.models.Book;
import com.davidbonelo.models.LibraryItem;
import com.mysql.cj.MysqlType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private final Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    protected static Book buildBookFromResult(ResultSet rs) throws SQLException {
        return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                rs.getInt("copies"), rs.getInt("copies_borrowed"), rs.getString("field"),
                rs.getInt("pages"));
    }

    public Book getBookById(int itemId) throws SQLException {
        String sql = "SELECT * FROM Books WHERE is_deleted = 0 AND id= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return buildBookFromResult(rs);
            }
            rs.close();
        }
        return null;
    }

    public List<Book> getAllBooks()  {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books WHERE is_deleted = 0";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs =
                statement.executeQuery()) {
            while (rs.next()) {
                books.add(buildBookFromResult(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return books;
    }

    public void createBook(Book book) throws SQLException {
        String sql = "INSERT INTO Books ( id, title, author, copies, copies_borrowed, field, " +
                "pages ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareCreate(statement, book);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Creating book failed, no rows affected.");
            }
        }
    }

    public void prepareCreate(PreparedStatement statement, Book book) throws SQLException {
        if (containsId(book)) {
            statement.setInt(1, book.getId());
        } else {
            statement.setNull(1, MysqlType.NULL.getJdbcType());
        }
        statement.setString(2, book.getTitle());
        statement.setString(3, book.getAuthor());
        statement.setInt(4, book.getCopies());
        statement.setInt(5, book.getCopiesBorrowed());
        statement.setString(6, book.getField());
        statement.setInt(7, book.getPages());
    }

    public void updateBook(Book book) throws SQLException {
        if (!containsId(book))
            throw new IllegalArgumentException("Cant update a Book without an id");
        String sql = "UPDATE Books b SET title= ?, author= ?, copies= ?, copies_borrowed= ?, " +
                "field= ?, pages= ? WHERE is_deleted = 0 AND b.id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getCopies());
            statement.setInt(4, book.getCopiesBorrowed());
            statement.setString(5, book.getField());
            statement.setInt(6, book.getPages());
            statement.setInt(7, book.getId()); // WHERE
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update of book with id " + book.getId() + " failed.");
            }
        }
    }

    public void deleteBook(int bookId) throws SQLException {
        String sql = "UPDATE Books b SET is_deleted = 1 WHERE b.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Book deletion failed, no rows affected");
            }
        }
    }

    private boolean containsId(LibraryItem item) {
        return item.getId() != 0; // just learned that int cant be null and defaults to 0
    }
}
