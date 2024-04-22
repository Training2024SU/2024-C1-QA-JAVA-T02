package co.com.sofka.businessLogic.reader;

import co.com.sofka.model.Book;
import co.com.sofka.model.User;

import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.readerBookMenuMessage;
import static co.com.sofka.utils.Utils.askInt;
import static co.com.sofka.utils.Utils.askString;

public class ReaderBookFunctions {
    private static final ReaderManagement readerManagement = new ReaderManagement();
    public static void readerBookMenuOptions(User user){

        boolean keepMenu = true;
        while (keepMenu){
            readerBookMenuMessage(user);
            int option = askInt(enterYourOptionMessage);
            switch (option) {
                case 1 -> seeAllBooks();
                case 2 -> seeBookByName();
                case 3 -> loanBook(user);
                case 4 -> seeAllUserLoan(user);
                case 5 -> returnBookLoan();
                case 6 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }

    }

    private static void returnBookLoan() {
        String id = askString("Enter book loan id: ");
        readerManagement.returnBookLoan(id);
    }

    private static void seeAllUserLoan(User user) {
        readerManagement.getAllUserBookLoan(user).forEach(bookLoan -> {
            System.out.println("Book loan id: " + bookLoan.getId());
            System.out.println("Book id: " + bookLoan.getBook().getTitle());
            System.out.println("Book loan user: " + bookLoan.getUser().getEmail());
            System.out.println("Book loan date: " + bookLoan.getLoanDate());
            System.out.println("Book return date: " + bookLoan.getReturnDate());
            System.out.println("Book loan status: " + bookLoan.getLoanStatus());
            System.out.println();
        });
    }

    private static void seeAllBooks() {
        readerManagement.getAllAvailableBooks().
                forEach(availableBook -> {
                    System.out.println("Book title: " + availableBook.getTitle());
                    System.out.println("Book author: " + availableBook.getAuthor().getName());
                    System.out.println("available quantity: " + availableBook.getQuantityAvailable());
                    System.out.println();
                });
    }

    private static void loanBook(User user) {
        String book = askString("Enter book name: ");
        Book bookToLoan =  readerManagement.getAvailableBookByTitle(book);
        if(bookToLoan == null){
            System.out.println("There are not available options");
        }else{
            readerManagement.loanBook(user, bookToLoan);
        }
    }
    private static void seeBookByName() {
        String name = askString("Enter the title: ");
        System.out.println(readerManagement.getAvailableBookByTitle(name));
    }
}
