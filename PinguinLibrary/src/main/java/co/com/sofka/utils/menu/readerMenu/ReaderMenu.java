package co.com.sofka.utils.menu.readerMenu;

import co.com.sofka.business.LibraryReaderManagement;
import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.Book;
import co.com.sofka.entities.Novel;
import co.com.sofka.entities.User;
import co.com.sofka.utils.menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ReaderMenu extends Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private final LibraryReaderManagement libraryReaderManagement;
    private List<Book> books = new ArrayList<>();
    private final User user;
    public ReaderMenu(User user, MySqlOperation mySqlOperation) {
        this.libraryReaderManagement = new LibraryReaderManagement(mySqlOperation);
        this.user = user;
    }

    public void displayMenu() {
        int option;
        do {
            menuOptions();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    books = libraryReaderManagement.getAllBooks();
                    System.out.println(books);
                    break;
                case 2:
                    libraryReaderManagement.generateBookLoan(askForBook(), user, askForQuantity());
                    break;
                case 3:
                    Novel novel = new Novel();
                    libraryReaderManagement.generateNovelLoan(novel);
                    break;
                case 4:
                    System.out.println("Exiting reader menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option != 4);
    }

    public Book askForBook(){
        System.out.print("Enter book id: ");
        String id = scanner.next();
        Optional<Book> bookOptional = books
                .stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
        return bookOptional.orElse(null);
    }
    public int askForQuantity(){
        System.out.print("How many books?: ");
        return scanner.nextInt();
    }
    public static void menuOptions(){
        System.out.println("-------MENU-------");
        System.out.println("1. See all books");
        System.out.println("2. Ask for book");
        System.out.println("3. Ask for novel");
        System.out.println("4. Exit.");
        System.out.print("Choose your option: ");
    }
}
