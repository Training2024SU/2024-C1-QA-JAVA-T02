package co.com.sofka.businessLogic.generalAdmin;

import co.com.sofka.enums.LoanStatus;
import co.com.sofka.model.Book;
import co.com.sofka.model.BookLoan;
import co.com.sofka.model.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;
import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.administratorBookLoanMessage;
import static co.com.sofka.utils.Utils.askDate;
import static co.com.sofka.utils.Utils.askInt;
import static co.com.sofka.utils.Utils.askString;

public class AdministratorBookLoanFunctions {
    private static final GeneralAdministrativeManagement generalAdministrativeManagement = new GeneralAdministrativeManagement();

    public static void administratorBookLoanMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            administratorBookLoanMessage(user);
            int option = askInt(enterYourOptionMessage);
            switch (option){
                case 1:
                    createBookLoan();
                    break;
                case 2:
                    seeBookLoans();
                    break;
                case 3:
                    editAcceptBookLoan();
                    break;
                case 4:
                    deleteBookLoan();
                    break;
                case 5:
                    System.out.println(exitingMessage);
                    keepMenu = false;
                    break;
                default:
                    System.out.println(incorrectOptionMessage);
            }
        }
    }
    public static void createBookLoan(){
        BookLoan bookLoan = getBookLoanData();
        generalAdministrativeManagement.insertBookLoan(bookLoan);
    }

    private static BookLoan getBookLoanData(){
        String email = askString("Enter user's email: ");
        String bookTitle = askString("Enter book's title: ");
        LocalDate loanDate = askDate("Enter loan date format(yyyy-mm-dd): ");
        Date loanDateD = Date.valueOf(loanDate);
        LocalDate returnDate = askDate("Enter return date format(yyyy-mm-dd): ");
        Date returnDateD = Date.valueOf(returnDate);
        String statusS = askString("Enter loan status (REQUESTED/COMPLETED/FINISHED): ");
        LoanStatus status = LoanStatus.valueOf(statusS);

        return new BookLoan(UUID.randomUUID().toString(),
                generalAdministrativeManagement.getUserByEmail(email),
                generalAdministrativeManagement.getBookByTitle(bookTitle),
                loanDateD,
                returnDateD,
                status);
    }
    private static void seeBookLoans(){
        generalAdministrativeManagement.getAllBookLoans().forEach(bookLoan -> {
            System.out.println("Book loan id: " + bookLoan.getId());
            System.out.println("Book title: " + bookLoan.getBook().getTitle());
            System.out.println("Book loan date: " + bookLoan.getLoanDate());
            System.out.println("Book loan return date: " + bookLoan.getReturnDate());
            System.out.println("Book loan status: " + bookLoan.getLoanStatus());
            System.out.println();
        });
    }

    private static void editAcceptBookLoan(){
        String id = askString("Enter book loan id: ");
        BookLoan oldBookLoan = generalAdministrativeManagement.getBookLoanById(id);
        String option = askString("Edit/Accept");
        switch (option.toLowerCase()){
            case "edit":
                editBookLoan(oldBookLoan);
                break;
            case "accept":
                acceptBookLoan(oldBookLoan);
                break;
            default:
                System.out.println(incorrectOptionMessage);
        }
    }

    private static void editBookLoan(BookLoan oldBookLoan){
        BookLoan newBookLoan = getBookLoanData();
        newBookLoan.setId(oldBookLoan.getId());
        generalAdministrativeManagement.updateBookLoan(newBookLoan);
    }

    private static void acceptBookLoan(BookLoan bookLoan){
        generalAdministrativeManagement.approveBookLoan(bookLoan);
    }

    private static void deleteBookLoan(){
        String title = askString("Enter book title: ");
        Book book = generalAdministrativeManagement.getBookByTitle(title);
        generalAdministrativeManagement.deleteBook(book);
    }

}
