package co.com.sofka.businessLogic.reader;

import co.com.sofka.enums.ResourceType;
import co.com.sofka.model.Loan;
import co.com.sofka.model.Resource;
import co.com.sofka.model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.readerResourceMenuMessage;
import static co.com.sofka.utils.Utils.askDate;
import static co.com.sofka.utils.Utils.getIntOption;

public class ReaderResourceFunctions {
    private static final ReaderManagement readerManagement = new ReaderManagement();

    public static void readerResourceMenuOptions(User user) {
        boolean keepMenu = true;
        while (keepMenu) {
            readerResourceMenuMessage(user);
            System.out.print("Enter your option: ");
            int option = getIntOption();
            switch (option) {
                case 1 -> listResources(ResourceType.SONG);
                case 2 -> listResources(ResourceType.VIDEO_RECORDING);
                case 3 -> listResources(ResourceType.ESSAY);
                case 4 -> selectResource();
                case 5 -> listSelected();
                case 6 -> requestLoan(user);
                case 7 -> listMyLoans(user);
                case 8 -> myLoanDetails(user);
                case 9 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void listResources(ResourceType type) {
        List<Resource> resources = readerManagement.getAvailableResources(type);
        System.out.println("List of " + type + "S available: ");
        resources.forEach(System.out::println);
    }

    private static void selectResource() {
        System.out.println("Enter the id of the resource you want to select: ");
        int id = getIntOption();
        readerManagement.selectResourceToLoan(id);
        System.out.println("Resource selected, you can select more or complete the request\n");
    }

    private static void listSelected() {
        Set<Resource> resources = readerManagement.getSelectedResources();
        System.out.println("Resources selected for loan: " + resources.size());
        resources.forEach(r -> System.out.println("* " + r));
        System.out.println("you can select more or complete the request\n");
    }

    private static void requestLoan(User user) {
        System.out.println("Loan request");
        System.out.println();
        LocalDate returnDate = askDate("Enter the date you plan to return the resources: ");
        try {
            readerManagement.requestLoan(user, returnDate);
            System.out.println("Request successful, don't forget to return the resources before: "
                    + returnDate);
        } catch (SQLException e) {
            System.out.println("Couldn't make the request, " + e.getMessage());
        }
    }

    private static void listMyLoans(User user) {
        System.out.println("List of your loans: ");
        List<Loan> loans = readerManagement.getAllResourceLoans(user);
        loans.forEach(System.out::println);
    }

    private static void myLoanDetails(User user) {
        System.out.println("Enter the id of the loan you want to see its details: ");
        int id = getIntOption();
        Loan loan = readerManagement.getResourceLoanDetails(user, id);
        if (loan != null) {
            System.out.println("Loan details: ");
            System.out.println(loan.toStringWithResources());
        } else {
            System.out.println("Loan with id " + id + " not found");
        }
    }
}
