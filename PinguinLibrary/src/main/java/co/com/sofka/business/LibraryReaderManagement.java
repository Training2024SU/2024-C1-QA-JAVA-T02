package co.com.sofka.business;

import co.com.sofka.DAO.Impl.BookDAO;
import co.com.sofka.business.generalActions.IGetAllBooks;
import co.com.sofka.business.generalActions.IGetAllNovels;
import co.com.sofka.business.readerActions.IGenerateBookLoan;
import co.com.sofka.business.readerActions.IGenerateNovelLoan;
import co.com.sofka.business.readerActions.ILoanBookRepayment;
import co.com.sofka.business.readerActions.ILoanNovelRepayment;
import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.Book;
import co.com.sofka.entities.LoanStatus;
import co.com.sofka.entities.Novel;
import co.com.sofka.entities.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class LibraryReaderManagement implements IGenerateNovelLoan,
        IGenerateBookLoan,
        ILoanBookRepayment,
        ILoanNovelRepayment,
        IGetAllNovels,
        IGetAllBooks {

    private final MySqlOperation mySqlOperation;
    private final BookDAO bookDAO;

    public LibraryReaderManagement(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
        bookDAO = new BookDAO(mySqlOperation);
    }


    @Override
    public void generateNovelLoan(Novel novel) {

    }

    @Override
    public void loanBookRepayment(Book book) {

    }

    @Override
    public void loanNovelRepayment(Novel novel) {

    }

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public List<Novel> getAllNovel() {

        return null;
    }

    @Override
    public void generateBookLoan(Book book, User user, int quantity) {
        String insertInto = "INSERT INTO book_loan (book_loan_id, user_id, book_id, loan_date, return_date, status)" +
                " VALUES ('%s', '%s', '%s', '%s', '%s', '%s')";


        String loanId = UUID.randomUUID().toString();


        LocalDate loanDate = LocalDate.now();
        LocalDate returnDate = loanDate.plusDays(15);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String loanDateString = loanDate.format(formatter);
        String returnDateString = returnDate.format(formatter);


        String query = String.format(insertInto, loanId, user.getId(), book.getId(), loanDateString, returnDateString, LoanStatus.REQUESTED);

        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
}
