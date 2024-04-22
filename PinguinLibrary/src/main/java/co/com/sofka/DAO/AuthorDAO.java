package co.com.sofka.DAO;

import co.com.sofka.model.Author;

import java.util.List;

public interface AuthorDAO {
    void insertAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthorById(String id);

    Author getAuthorByName(String name);

    void updateAuthor(Author author);

    void deleteAuthor(Author author);
}
