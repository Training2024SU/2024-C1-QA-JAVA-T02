package co.com.sofka.business.management;

import co.com.sofka.entities.Author;

import java.util.List;

public interface IAuthorManagement {
    public void insertAuthor(Author author);
    public List<Author> getAllAuthor();
    public Author getAuthorById(String id);
    public void updateAuthor(Author author);
    public void deleteAuthor(Author author);
}
