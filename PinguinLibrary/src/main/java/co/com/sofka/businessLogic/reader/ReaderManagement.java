package co.com.sofka.businessLogic.reader;

import co.com.sofka.DAO.Impl.AuthorDAOImpl;
import co.com.sofka.DAO.Impl.BookDAOImpl;
import co.com.sofka.DAO.Impl.BookLoanDAOImpl;
import co.com.sofka.DAO.Impl.LoanDAOImpl;
import co.com.sofka.DAO.Impl.NovelDaoImpl;
import co.com.sofka.DAO.Impl.NovelLoanDAOImpl;
import co.com.sofka.DAO.Impl.ResourceDAOImpl;
import co.com.sofka.DAO.LoanDAO;
import co.com.sofka.DAO.ResourceDAO;
import co.com.sofka.businessLogic.reader.interf.GetAllAuthors;
import co.com.sofka.businessLogic.reader.interf.GetAllAvailableBooks;
import co.com.sofka.businessLogic.reader.interf.GetAllAvailableNovels;
import co.com.sofka.businessLogic.reader.interf.GetAvailableBookByTitle;
import co.com.sofka.businessLogic.reader.interf.GetAvailableNovelByTitle;
import co.com.sofka.businessLogic.reader.interf.LoanABook;
import co.com.sofka.businessLogic.reader.interf.LoanANovel;
import co.com.sofka.enums.LoanStatus;
import co.com.sofka.enums.ResourceType;
import co.com.sofka.model.Author;
import co.com.sofka.model.Book;
import co.com.sofka.model.BookLoan;
import co.com.sofka.model.Loan;
import co.com.sofka.model.Novel;
import co.com.sofka.model.NovelLoan;
import co.com.sofka.model.Resource;
import co.com.sofka.model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static co.com.sofka.enums.LoanStatus.FINISHED;

public class ReaderManagement implements
        GetAllAvailableBooks,
        GetAvailableBookByTitle,
        GetAllAvailableNovels,
        GetAvailableNovelByTitle,
        GetAllAuthors,
        LoanABook,
        LoanANovel {
    private final BookDAOImpl bookDAO = new BookDAOImpl();
    private final NovelDaoImpl novelDao = new NovelDaoImpl();
    private final AuthorDAOImpl authorDAO = new AuthorDAOImpl();
    private final BookLoanDAOImpl bookLoanDAO = new BookLoanDAOImpl();
    private final NovelLoanDAOImpl novelLoanDAO = new NovelLoanDAOImpl();
    private final ResourceDAO resourceDAO = new ResourceDAOImpl();
    private final LoanDAO loanDAO = new LoanDAOImpl();

    private final Set<Resource> resourcesToLoan = new HashSet<>();

    public List<Book> getAllAvailableBooks() {
        return bookDAO.getAllBooks()
                .stream()
                .filter(book -> book.getQuantityAvailable() >= 1)
                .collect(Collectors.toList());
    }

    public Book getAvailableBookByTitle(String title) {
        return bookDAO.getAllBooks()
                .stream()
                .filter(book -> (book.getQuantityAvailable() >= 1 && book.getTitle().equals(title)))
                .findFirst()
                .orElse(null);
    }

    public List<Novel> getAllAvailableNovels() {
        return novelDao.getAllNovels()
                .stream()
                .filter(novel -> novel.getQuantityAvailable() >= 1)
                .collect(Collectors.toList());
    }

    public Novel getAvailableNovelByTitle(String title) {
        return novelDao.getAllNovels()
                .stream()
                .filter(novel -> (novel.getQuantityAvailable() >= 1 && novel.getTitle().equals(title)))
                .findFirst()
                .orElse(null);
    }

    public List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    public List<NovelLoan> getAllUserNovelLoan(User user) {
        return novelLoanDAO.getAllNovelLoans()
                .stream()
                .filter(novelLoan -> novelLoan
                        .getUser()
                        .getId()
                        .equals(user.getId()))
                .collect(Collectors.toList());
    }

    public List<BookLoan> getAllUserBookLoan(User user) {
        return bookLoanDAO.getAllBookLoans()
                .stream()
                .filter(bookLoan -> bookLoan
                        .getUser()
                        .getId()
                        .equals(user.getId()))
                .collect(Collectors.toList());
    }


    public void loanBook(User user, Book book) {
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

    public void returnBookLoan(String id) {
        BookLoan bookLoan = bookLoanDAO.getBookLoanById(id);
        Book book = bookLoan.getBook();
        bookLoan.setLoanStatus(FINISHED);
        book.setQuantityLoaned(book.getQuantityLoaned() - 1);
        bookDAO.updateBook(book);
        bookLoanDAO.updateBookLoan(bookLoan);
    }

    public void loanNovel(User user, Novel novel) {
        novel.setQuantityLoaned(novel.getQuantityLoaned() + 1);
        novel.setQuantityAvailable(novel.getQuantityAvailable() - 1);
        novelDao.updateNovel(novel);
        NovelLoan novelLoan = new NovelLoan(UUID.randomUUID().toString(),
                user,
                novel,
                null,
                null,
                LoanStatus.REQUESTED);
        novelLoanDAO.insertNovelLoan(novelLoan);
    }

    public void returnNovelLoan(String id) {
        NovelLoan novelLoan = novelLoanDAO.getNovelLoanById(id);
        Novel novel = novelLoan.getNovel();
        novelLoan.setLoanStatus(FINISHED);
        novel.setQuantityLoaned(novel.getQuantityLoaned() - 1);
        novelDao.updateNovel(novel);
        novelLoanDAO.updateNovelLoan(novelLoan);
    }

    public List<Resource> getAvailableResources(ResourceType type) {
        return resourceDAO.getAllResources().stream().filter(r -> r.getType() == type).toList();
    }

    public void selectResourceToLoan(int resourceId) {
        Resource resource = resourceDAO.getResourceById(resourceId);
        resourcesToLoan.add(resource);
    }

    public void requestLoan(User user, LocalDate returnDate) throws SQLException {
        Loan loan = new Loan(returnDate, user);
        loan.setLentResources(resourcesToLoan.stream().toList());
        loanDAO.insertLoan(loan);
        updateResourcesInventory(loan.getLentResources());
        resourcesToLoan.clear();
    }

    private void updateResourcesInventory(List<Resource> resources) {
        for (Resource r : resources) {
            r.setQuantityLoaned(r.getQuantityLoaned() + 1);
            resourceDAO.updateResource(r);
        }
    }

    public List<Loan> getAllResourceLoans(User user) {
        return loanDAO.getAllLoans().stream().filter(l -> l.getUser().getId().equals(user.getId())).toList();
    }

    public Loan getResourceLoanDetails(int loanId){
        return loanDAO.getLoanById(loanId);
    }
}
