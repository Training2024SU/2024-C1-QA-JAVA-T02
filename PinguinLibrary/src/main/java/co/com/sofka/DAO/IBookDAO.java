package co.com.sofka.DAO;

import co.com.sofka.entities.Book;

import java.util.List;

public interface IBookDAO {
    public void insertBook(Book book);
    public List<Book> getAllBooks();
    public Book getBookById(String id);
    public void updateBook(Book book);
    public void deleteBook(Book book);

}
