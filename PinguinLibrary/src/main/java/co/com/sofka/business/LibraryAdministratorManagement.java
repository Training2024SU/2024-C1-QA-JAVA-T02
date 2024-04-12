package co.com.sofka.business;

import co.com.sofka.DAO.Impl.AuthorDAO;
import co.com.sofka.DAO.Impl.BookDAO;
import co.com.sofka.DAO.Impl.NovelDao;
import co.com.sofka.DAO.Impl.UserDAO;
import co.com.sofka.business.management.*;
import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.Author;
import co.com.sofka.entities.Book;
import co.com.sofka.entities.Novel;
import co.com.sofka.entities.User;

import java.util.List;

public class LibraryAdministratorManagement implements
        IUserManagement,
        IBookManagement,
        INovelManagement,
        INovelLoanManagement,
        IBookLoanManagement,
        IAuthorManagement{

    private final AuthorDAO authorDAO;
    private final NovelDao novelDao;
    private final BookDAO bookDAO;
    private final UserDAO userDAO;

    public LibraryAdministratorManagement(MySqlOperation mySqlOperation) {
        this.authorDAO = new AuthorDAO(mySqlOperation);
        this.novelDao = new NovelDao(mySqlOperation);
        this.bookDAO = new BookDAO(mySqlOperation);
        this.userDAO = new UserDAO(mySqlOperation);
    }

    @Override
    public void insertAuthor(Author author) {
        authorDAO.insertAuthor(author);
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorDAO.getAllAuthors();
    }

    @Override
    public Author getAuthorById(String id) {
        return authorDAO.getAuthorById(id);
    }

    @Override
    public void updateAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }

    @Override
    public void deleteAuthor(Author author) {
        authorDAO.deleteAuthor(author);
    }

    @Override
    public void cancelBookLoan(String id) {

    }

    @Override
    public void acceptBookLoan(String id) {

    }

    @Override
    public void insertBook(Book book) {
        bookDAO.insertBook(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public Book getBookById(String id) {
        return bookDAO.getBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(Book book) {
        bookDAO.deleteBook(book);
    }

    @Override
    public void cancelNovelLoan(String id) {

    }

    @Override
    public void acceptNovelLoan(String id) {

    }

    @Override
    public void insertNovel(Novel novel) {
        novelDao.insertNovel(novel);
    }

    @Override
    public List<Novel> getAllNovels() {
        return novelDao.getAllNovels();
    }

    @Override
    public Novel getNovelById(String id) {
    return novelDao.getNovelById(id);
    }

    @Override
    public void updateNovel(Novel novel) {
        novelDao.updateNovel(novel);
    }

    @Override
    public void deleteNovel(Novel novel) {
        novelDao.deleteNovel(novel);
    }

    @Override
    public void insertUser(User user) {
        userDAO.insertUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(String id) {
        return userDAO.getUserById(id);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);

    }
    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
