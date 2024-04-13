package co.com.sofka.business.reader;

import co.com.sofka.DAO.BookDAO;
import co.com.sofka.DAO.Impl.*;
import co.com.sofka.enums.LoanStatus;
import co.com.sofka.model.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReaderManagement {
    private static final BookDAOImpl bookDAO = new BookDAOImpl();
    private static final NovelDaoImpl novelDao = new NovelDaoImpl();
    private static final AuthorDAOImpl authorDAO = new AuthorDAOImpl();
    private static final BookLoanDAOImpl bookLoanDAO = new BookLoanDAOImpl();
    private static final NovelLoanDAOImpl novelLoanDAO = new NovelLoanDAOImpl();

    public static List<Book> getAllAvailableBooks(){
        return bookDAO.getAllBooks()
                .stream()
                .filter(book -> book.getQuantityAvailable() >= 1)
                .collect(Collectors.toList());
    }
    public static Book getAvailableBookByTitle(String title){
        return bookDAO.getAllBooks()
                .stream()
                .filter(book -> (book.getQuantityAvailable() >= 1 && book.getTitle().equals(title)))
                .findFirst()
                .orElse(null);
    }

    private static List<Novel> getAllAvailableNovels(){
        return novelDao.getAllNovels()
                .stream()
                .filter(novel -> novel.getQuantityAvailable() >= 1)
                .collect(Collectors.toList());
    }

    public static Novel getAvailableNovelByTitle(String title){
        return novelDao.getAllNovels()
                .stream()
                .filter(novel -> (novel.getQuantityAvailable() >= 1 && novel.getTitle().equals(title)))
                .findFirst()
                .orElse(null);
    }

    public static List<Author> getAllAuthors(){
        return authorDAO.getAllAuthors();
    }
    public static List<NovelLoan> getAllUserNovelLoan(User user){
        return novelLoanDAO.getAllNovelLoans()
                .stream()
                .filter(novelLoan -> novelLoan
                        .getUser()
                        .getId()
                        .equals(user.getId()))
                .collect(Collectors.toList());
    }

    public static List<BookLoan> getAllUserBookLoan(User user){
        return bookLoanDAO.getAllBookLoans()
                .stream()
                .filter(bookLoan -> bookLoan
                        .getUser()
                        .getId()
                        .equals(user.getId()))
                .collect(Collectors.toList());
    }

    //public static loan book and loan novel
    public static void loanBook(User user, Book book){
        book.setQuantityLoaned(book.getQuantityLoaned() + 1);
        book.setQuantityAvailable(book.getQuantityAvailable() - 1);
        bookDAO.updateBook(book);
        BookLoan bookLoan = new BookLoan(UUID.randomUUID().toString(),
                user,
                book,
                null,
                null,
                LoanStatus.REQUESTED);
        bookLoanDAO.insertBookLoan(bookLoan);
    }
}
