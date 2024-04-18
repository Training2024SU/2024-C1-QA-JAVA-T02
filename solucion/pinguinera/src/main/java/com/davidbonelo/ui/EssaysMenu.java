package com.davidbonelo.ui;

import com.davidbonelo.models.Essay;
import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.BorrowingsService;
import com.davidbonelo.services.LibraryManager;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.davidbonelo.utils.Permissions.validMenuAccess;
import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class EssaysMenu {
    private final LibraryManager libraryManager;
    private final BorrowingsService borrowingsService;
    private final User user;
    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public EssaysMenu(LibraryManager libraryManager, BorrowingsService borrowingsService,
                      User user) {
        this.libraryManager = libraryManager;
        this.borrowingsService = borrowingsService;
        this.user = user;
    }
    public void menu() {
        while (true) {
            int menuChoice = showMenu();
            switch (menuChoice) {
                case 1 -> listEssays();
                case 2 -> listAuthors();
                case 3 -> searchByAuthor();
                case 4 -> addToBorrowing();
                case 5 -> registerEssay();
                case 6 -> updateEssay();
                case 7 -> deleteEssay();
                case 0 -> {
                    return;
                }
                default -> System.out.println(messages.getString("unknownOption"));
            }
        }
    }

    private int showMenu() {
        String visitorChoices = messages.getString("essays.choices.visitor");
        String readerChoices = messages.getString("essays.choices.reader");
        String employeeChoices = messages.getString("essays.choices.employee");
        MenuChoices menu = new MenuChoices("Essays", visitorChoices, readerChoices,
                employeeChoices, "");
        return menu.showMenu(user);
    }

    private void searchByAuthor() {
        String author = askText(messages.getString("item.req.author"));
        libraryManager.filterItemsByAuthor(libraryManager.getAllEssays(user), author).forEach(System.out::println);
    }

    private void listEssays() {
        libraryManager.getAllEssays(user).forEach(System.out::println);
    }

    private void listAuthors() {
        libraryManager.getAuthorsList(libraryManager.getAllEssays(user)).forEach(System.out::println);
    }

    private void registerEssay() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        libraryManager.registerItem(Essay.createEssayFromInput());
    }

    private void updateEssay() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int essayId = askNumber(messages.getString("essays.req.updateId"));
        System.out.println(messages.getString("essays.req.essayData"));
        Essay essay = Essay.createEssayFromInput();
        essay.setId(essayId);
        libraryManager.updateItem(essay);
    }

    private void deleteEssay() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int essayId = askNumber(messages.getString("essays.req.deleteId"));
        libraryManager.deleteEssay(essayId);
    }

    private void addToBorrowing() {
        if (!validMenuAccess(user, UserRole.READER)) {
            return;
        }
        int essayId = askNumber(messages.getString("essays.req.toBorrowId"));
        try {
            borrowingsService.addBorrowingEssay(essayId);
            System.out.println(messages.getString("essays.res.borrowingOk"));
        } catch (SQLException e) {
            System.out.println(messages.getString("essays.res.borrowingBad") + e.getLocalizedMessage());
        }
    }
}
