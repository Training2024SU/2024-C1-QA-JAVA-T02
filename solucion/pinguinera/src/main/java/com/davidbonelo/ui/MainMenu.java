package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.persistance.*;
import com.davidbonelo.services.BorrowingsService;
import com.davidbonelo.services.DataService;
import com.davidbonelo.services.LibraryManager;
import com.davidbonelo.services.UserService;

import java.sql.Connection;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.davidbonelo.utils.Permissions.validMenuAccess;
import static com.davidbonelo.utils.UserInteractions.closeScanner;

public class MainMenu {
    private final UserService userService;
    private final LibraryManager libraryManager;
    private final BorrowingsService borrowingsService;
    private final DataService dataService;
    private User user;
    private ResourceBundle messages = ResourceBundle.getBundle("messages");

    public MainMenu(Connection connection) {
        UserDAO userDAO = new UserDAO(connection);
        BookDAO bookDAO = new BookDAO(connection);
        NovelDAO novelDAO = new NovelDAO(connection);
        SongDAO songDAO = new SongDAO(connection);
        EssayDAO essayDAO = new EssayDAO(connection);
        VideoRecordingDAO videoRecordingDAO = new VideoRecordingDAO(connection);

        BorrowingDAO borrowingDAO = new BorrowingDAO(connection);
        this.userService = new UserService(userDAO);
        this.libraryManager = new LibraryManager(bookDAO, novelDAO, videoRecordingDAO, songDAO, essayDAO);
        this.borrowingsService = new BorrowingsService(bookDAO, novelDAO, videoRecordingDAO, songDAO, essayDAO, borrowingDAO, connection);
        this.dataService = new DataService(bookDAO, novelDAO,videoRecordingDAO, songDAO, essayDAO, connection);
    }

    public void menu() {
        userService.login("ad","min");

        System.out.println(messages.getString("welcome"));
        while (true) {
            messages = ResourceBundle.getBundle("messages"); // Refresh if language changed
            user = userService.getLoggedUser();

            int menuChoice = showMenu();
            switch (menuChoice) {
                case 1 -> new LoginMenu(userService).menu();
                case 2 -> new BooksMenu(libraryManager, borrowingsService, user).menu();
                case 3 -> new NovelsMenu(libraryManager, borrowingsService, user).menu();
                case 4 -> new VideoRecordingsMenu(libraryManager, borrowingsService, user).menu();
                case 5 -> new SongsMenu(libraryManager, borrowingsService, user).menu();
                case 6 -> new EssaysMenu(libraryManager, borrowingsService, user).menu();
                case 7 -> new BorrowingMenu(borrowingsService, user).menu();
                // case 8 -> new PersonalUserMenu(borrowingsService, user).menu();
                case 8 -> new AdminMenu(userService, dataService, user).menu();
                case 9 -> Locale.setDefault(Locale.forLanguageTag("es"));
                case 10 -> logout(user);
                case 0 -> {
                    closeScanner();
                    return;
                }
                default -> System.out.println(messages.getString("unknownOption"));
            }
        }
    }

    private int showMenu() {
        String visitorChoices = messages.getString("main.choices.visitor");
        String readerChoices = messages.getString("main.choices.reader");
        String adminChoices = messages.getString("main.choices.admin");
        return new MenuChoices(messages.getString("main.keyword"), visitorChoices, readerChoices,
                "", adminChoices).showMenu(user);
    }

    private void logout(User user) {
        if (validMenuAccess(user, UserRole.READER)) {
            userService.logOut();
        }
    }
}
