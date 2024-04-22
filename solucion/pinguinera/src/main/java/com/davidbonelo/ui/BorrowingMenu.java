package com.davidbonelo.ui;

import com.davidbonelo.models.*;
import com.davidbonelo.services.BorrowingsService;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.davidbonelo.utils.Permissions.validMenuAccess;
import static com.davidbonelo.utils.UserInteractions.askDate;
import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class BorrowingMenu {
    private final User user;
    private final BorrowingsService borrowingsService;
    private final ResourceBundle messages = ResourceBundle.getBundle("messages");
    public static Set<LibraryItem> items = new HashSet<>();
    public static List<Borrowing> borrowings = new ArrayList<>();
    private static final AtomicInteger nextId = new AtomicInteger(1);


    public BorrowingMenu(BorrowingsService borrowingsService, User user) {
        this.borrowingsService = borrowingsService;
        this.user = user;
    }

    public void menu() {
        while (true) {
            if (!validMenuAccess(user, UserRole.READER)) {
                return;
            }
            int menuChoice = showMenu();
            switch (menuChoice) {
                case 1 -> listBorrowingItems();
                case 2 -> listBorrowings();
                case 3 -> showDetails();
                case 4 -> createRequest();
                case 5 -> searchByEmail();
                case 6 -> confirmBorrowing();
                case 7 -> finalizeBorrowing();
                case 8 -> deleteBorrowing();
                case 0 -> {
                    return;
                }
                default -> System.out.println(messages.getString("unknownOption"));
            }
        }
    }

    private int showMenu() {
        String readerChoices = messages.getString("borrowings.choices.reader");
        String employeeChoices = messages.getString("borrowings.choices.employee");
        MenuChoices menu = new MenuChoices("Borrowings", "", readerChoices, employeeChoices, "", "");
        return menu.showMenu(user);
    }

    private void listBorrowings() {
        if (user.getRole().equals(UserRole.SUPER)){
            System.out.println(messages.getString("borrowings.info.list"));
            borrowings.forEach(System.out::println);
            return;
        }
        System.out.println(messages.getString("borrowings.info.list"));
        borrowingsService.getAllBorrowings(user).forEach(System.out::println);
    }

    private void listBorrowingItems() {
        if (user.getRole().equals(UserRole.SUPER)){
            System.out.println(messages.getString("borrowings.info.list"));
            items.forEach(System.out::println);
            return;
        }
        Set<LibraryItem> items = borrowingsService.getItemsToBorrow();
        System.out.println(messages.getString("borrowings.info.items") + items.size());
        items.forEach(System.out::println);
    }

    private void showDetails() {
        int borrowingId = askNumber(messages.getString("borrowings.req.id"));
        try {
            Borrowing borrowing = borrowingsService.getBorrowingDetails(user, borrowingId);
            if (borrowing == null){
                System.out.println(messages.getString("borrowings.out.detailsBad"));
                return;
            }
            System.out.println(messages.getString("borrowings.info.details"));
            System.out.println(borrowing.toStringWithItems());
        } catch (SQLException e) {
            System.out.println(messages.getString("borrowings.out.detailsBad") + e.getLocalizedMessage());
        }
    }

    private void createRequest() {
        if (user.getRole().equals(UserRole.SUPER)) {
            System.out.println(messages.getString("borrowings.info.list"));
            LocalDate returnDate = askDate(messages.getString("borrowings.req.dueDate"));
            int id = generateUniqueId();
            Borrowing borrowing = new Borrowing(id + 1000, returnDate, LocalDate.now(), user, BorrowingStatus.REQUESTED);
            borrowings.add(borrowing);
            return;
        }
        System.out.println(messages.getString("borrowings.info.requesting"));
        LocalDate returnDate = askDate(messages.getString("borrowings.req.dueDate"));
        try {
            borrowingsService.createBorrowing(new Borrowing(returnDate, user));
            System.out.println(messages.getString("borrowings.res.createOk"));
        } catch (Exception e) {
            System.out.println(messages.getString("borrowings.res.createBad") + e.getLocalizedMessage());
        }
    }

    private void searchByEmail() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        String email = askText(messages.getString("borrowings.req.userId"));
        List<Borrowing> borrowings = borrowingsService.getBorrowingsByEmail(user, email);
        System.out.println(MessageFormat.format(messages.getString("borrowings.res.userList"),
                email));
        borrowings.forEach(System.out::println);
    }

    private void confirmBorrowing() {
        if (user.getRole().equals(UserRole.SUPER)) {
            int borrowingId = askNumber(messages.getString("borrowings.req.confirmId"));
            borrowings.stream().forEach(borrowing -> {
                if(borrowing.getId() == borrowingId){
                    borrowing.setStatusBorrowed();
                }
            }
            );
            return;
        }
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int borrowingId = askNumber(messages.getString("borrowings.req.confirmId"));
        try {
            borrowingsService.confirmBorrowing(user, borrowingId);
            System.out.println(messages.getString("borrowings.res.confirmOk"));
        } catch (SQLException e) {
            System.out.println(messages.getString("borrowings.res.confirmBad") + e.getLocalizedMessage());
        }
    }

    private void finalizeBorrowing() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int borrowingId = askNumber(messages.getString("borrowings.req.finalizeId"));
        try {
            borrowingsService.finalizeBorrowing(user, borrowingId);
            System.out.println(messages.getString("borrowings.res.finalizeOk"));
        } catch (SQLException e) {
            System.out.println(messages.getString("borrowings.res.finalizeBad") + e.getLocalizedMessage());
        }
    }

    private void deleteBorrowing() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int borrowingId = askNumber(messages.getString("borrowings.req.deleteId"));
        borrowingsService.deleteBorrowing(borrowingId);
    }

    private static int generateUniqueId() {
        return nextId.getAndIncrement();
    }
}
