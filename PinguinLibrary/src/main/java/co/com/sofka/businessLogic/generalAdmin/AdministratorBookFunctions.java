package co.com.sofka.businessLogic.generalAdmin;


import co.com.sofka.model.Book;
import co.com.sofka.model.User;

import java.util.UUID;


import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.administratorBookMenuMessage;
import static co.com.sofka.utils.CsvBookManagement.exportBooksData;
import static co.com.sofka.utils.Utils.askInt;
import static co.com.sofka.utils.Utils.askString;

public class AdministratorBookFunctions {
    private static final GeneralAdministrativeManagement generalAdministrativeManagement = new GeneralAdministrativeManagement();

    public static void administratorBookMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            administratorBookMenuMessage(user);
            int option = askInt(enterYourOptionMessage);
            switch (option) {
                case 1 -> insertBook();
                case 2 -> seeAllBooks();
                case 3 -> seeBookByTitle();
                case 4 -> updateBook();
                case 5 -> deleteBook();
                case 6 -> exportBooksData();
                case 7 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void insertBook(){
        Book book = getBookData();
        generalAdministrativeManagement.insertBook(book);
    }

    private static Book getBookData(){
        String title = askString("Enter book title: ");
        String author = askString("Enter author's name: ");
        String category = askString("Enter category: ");
        int quantity = askInt("Enter quantity: ");
        int quantityLoaned = askInt("Enter quantity loaned: ");

        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setTitle(title);
        book.setAuthor(generalAdministrativeManagement.getAuthorByName(author));
        book.setCategory(category);
        book.setQuantity(quantity);
        book.setQuantityLoaned(quantityLoaned);
        return book;
    }

    private static void seeAllBooks(){
        generalAdministrativeManagement.getAllBooks().forEach(System.out::println);
    }

    private static void seeBookByTitle(){
        String title = askString("Enter book title: ");
        System.out.println(generalAdministrativeManagement.getBookByTitle(title));
    }

    private static void updateBook(){
        String id = askString("Enter book id: ");
        Book newBook = getBookData();
        Book oldBook = generalAdministrativeManagement.getBookById(id);
        newBook.setId(oldBook.getId());
        generalAdministrativeManagement.updateBook(newBook);
    }

    private static void deleteBook(){
        String title = askString("Enter book title: ");
        generalAdministrativeManagement.deleteBook(generalAdministrativeManagement.getBookByTitle(title));
    }
}
