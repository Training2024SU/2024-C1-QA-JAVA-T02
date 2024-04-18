package com.davidbonelo.ui;

import com.davidbonelo.models.Book;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.BorrowingsService;
import com.davidbonelo.services.LibraryManager;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.davidbonelo.utils.Permissions.validMenuAccess;
import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class SongsMenu {
    private final LibraryManager libraryManager;
    private final BorrowingsService borrowingsService;
    private final User user;
    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public SongsMenu(LibraryManager libraryManager, BorrowingsService borrowingsService,
                     User user) {
        this.libraryManager = libraryManager;
        this.borrowingsService = borrowingsService;
        this.user = user;
    }
    public void menu() {
        while (true) {
            int menuChoice = showMenu();
            switch (menuChoice) {
                case 1 -> listSongs();
                case 2 -> listAuthors();
                case 3 -> searchByAuthor();
                case 4 -> addToBorrowing();
                case 5 -> registerSong();
                case 6 -> updateSong();
                case 7 -> deleteSong();
                case 0 -> {
                    return;
                }
                default -> System.out.println(messages.getString("unknownOption"));
            }
        }
    }



    private int showMenu() {
        String visitorChoices = messages.getString("songs.choices.visitor");
        String readerChoices = messages.getString("songs.choices.reader");
        String employeeChoices = messages.getString("songs.choices.employee");
        MenuChoices menu = new MenuChoices("Songs", visitorChoices, readerChoices,
                employeeChoices, "");
        return menu.showMenu(user);
    }

    private void searchByAuthor() {
        String author = askText(messages.getString("item.req.author"));
        libraryManager.filterItemsByAuthor(libraryManager.getAllBooks(user), author).forEach(System.out::println);
    }

    private void listSongs() {
        libraryManager.getAllBooks(user).forEach(System.out::println);
    }

    private void listAuthors() {
        libraryManager.getAuthorsList(libraryManager.getAllBooks(user)).forEach(System.out::println);
    }

    private void registerSong() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        libraryManager.registerItem(Book.createBookFromInput());
    }

    private void updateSong() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int bookId = askNumber(messages.getString("books.req.updateId"));
        System.out.println(messages.getString("books.req.bookData"));
        Book book = Book.createBookFromInput();
        book.setId(bookId);
        libraryManager.updateItem(book);
    }

    private void deleteSong() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int bookId = askNumber(messages.getString("books.req.deleteId"));
        libraryManager.deleteBook(bookId);
    }

    private void addToBorrowing() {
        if (!validMenuAccess(user, UserRole.READER)) {
            return;
        }
        int bookId = askNumber(messages.getString("books.req.toBorrowId"));
        try {
            borrowingsService.addBorrowingBook(bookId);
            System.out.println(messages.getString("books.res.borrowingOk"));
        } catch (SQLException e) {
            System.out.println(messages.getString("books.res.borrowingBad") + e.getLocalizedMessage());
        }
    }

}
