package co.com.sofka.businessLogic.generalAdmin;

import co.com.sofka.model.Loan;
import co.com.sofka.model.User;

import java.util.List;

import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.administratorResourceLoanMenuMessage;
import static co.com.sofka.utils.Utils.askInt;
import static co.com.sofka.utils.Utils.askString;

public class AdministratorResourceLoanFunctions {
    private static final GeneralAdministrativeManagement administrativeManagement =
            new GeneralAdministrativeManagement();

    public static void administratorResourceLoanMenuOptions(User user) {
        boolean keepMenu = true;
        while (keepMenu) {
            administratorResourceLoanMenuMessage(user);
            int option = askInt("Enter your option: ");
            switch (option) {
                case 1 -> listResourceLoans();
                case 2 -> searchLoanByEmail();
                case 3 -> seeLoanDetails();
                case 4 -> approveLoan();
                case 5 -> finishLoan();
                case 6 -> deleteLoan();
                case 7 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void listResourceLoans() {
        List<Loan> loans = administrativeManagement.getAllLoans();
        System.out.println("Resource loans list:" + loans.size());
        loans.forEach(System.out::println);
        System.out.println();
    }

    private static void searchLoanByEmail() {
        String email = askString("Enter the user email to search it's loans");
        List<Loan> loans = administrativeManagement.getLoansByEmail(email);
        System.out.println(email + " Loans list: " + loans.size());
        loans.forEach(System.out::println);
        System.out.println();
    }

    private static void seeLoanDetails() {
        int id = askInt("Enter the id of the loan you want to see its details: ");
        Loan loan = administrativeManagement.getResourceLoanDetails(id);
        if (loan != null) {
            System.out.println("Loan details: ");
            System.out.println(loan.toStringWithResources());
        } else {
            System.out.println("Loan with id " + id + " not found");
        }
    }

    private static void approveLoan() {
        int loanId = askInt("Enter the id of the loan you want to approve: ");
        administrativeManagement.approveResourceLoan(loanId);
        System.out.println("Loan approved successfully.\n");
    }

    private static void finishLoan() {
        int loanId = askInt("Enter the id of the loan you want to set as finish: ");
        administrativeManagement.finishResourceLoan(loanId);
        System.out.println("Loan finalized successfully.\n");
    }

    private static void deleteLoan() {
        int loanId = askInt("Enter the id of the loan you want to delete: ");
        administrativeManagement.deleteResourceLoan(loanId);
        System.out.println("Loan deleted successfully.\n");

    }
}
