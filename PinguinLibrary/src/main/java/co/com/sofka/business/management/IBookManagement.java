package co.com.sofka.business.management;

import co.com.sofka.entities.Book;

import java.util.List;

public interface IBookManagement {
    public void insertBook(Book book);
    public List<Book> getAllBooks();
    public Book getBookById(String id);
    public void updateBook(Book book);
    public void deleteBook(Book book);
}
