package co.com.sofka.businessLogic.generalAdmin;

import co.com.sofka.enums.LoanStatus;
import co.com.sofka.model.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.administratorNovelLoanMessage;
import static co.com.sofka.utils.Utils.askDate;
import static co.com.sofka.utils.Utils.askInt;
import static co.com.sofka.utils.Utils.askString;

public class AdministratorNovelLoanFunctions {
    private static final GeneralAdministrativeManagement generalAdministrativeManagement = new GeneralAdministrativeManagement();
    public static void administratorNovelLoanMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            administratorNovelLoanMessage(user);
            int option = askInt(enterYourOptionMessage);
            switch (option){
                case 1:
                    createNovelLoan();
                    break;
                case 2:
                    seeNovelLoans();
                    break;
                case 3:
                    editAcceptNovelLoan();
                    break;
                case 4:
                    deleteNovelLoan();
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

    public static void createNovelLoan(){
        NovelLoan novelLoan = getNovelLoanData();
        generalAdministrativeManagement.insertNovelLoan(novelLoan);
    }
    private static NovelLoan getNovelLoanData(){
        String email = askString("Enter user's email: ");
        String novelTitle = askString("Enter novel's title: ");
        LocalDate loanDate = askDate("Enter loan date format(yyyy-mm-dd): ");
        Date loanDateD = Date.valueOf(loanDate);
        LocalDate returnDate = askDate("Enter return date format(yyyy-mm-dd): ");
        Date returnDateD = Date.valueOf(returnDate);

        String statusS = askString("Enter loan status (REQUESTED/COMPLETED/FINISHED): ");
        LoanStatus status = LoanStatus.valueOf(statusS);

        return new NovelLoan(UUID.randomUUID().toString(),
                generalAdministrativeManagement.getUserByEmail(email),
                generalAdministrativeManagement.getNovelByTitle(novelTitle),
                loanDateD,
                returnDateD,
                status);
    }

    private static void seeNovelLoans(){
        generalAdministrativeManagement.getAllNovelLoans().forEach(novelLoan -> {
            System.out.println("Novel loan id: " + novelLoan.getId());
            System.out.println("Novel title: " + novelLoan.getNovel().getTitle());
            System.out.println("Novel loan date: " + novelLoan.getLoanDate());
            System.out.println("Novel loan return date: " + novelLoan.getReturnDate());
            System.out.println("Novel loan status: " + novelLoan.getLoanStatus());
            System.out.println();
        });
    }

    private static void editAcceptNovelLoan(){
        String id = askString("Enter novel loan id: ");
        NovelLoan oldNovelLoan = generalAdministrativeManagement.getNovelLoanById(id);
        String option = askString("Edit/Accept");
        switch (option.toLowerCase()){
            case "edit":
                editNovelLoan(oldNovelLoan);
                break;
            case "accept":
                acceptNovelLoan(oldNovelLoan);
                break;
            default:
                System.out.println(incorrectOptionMessage);
        }
    }

    private static void editNovelLoan(NovelLoan oldNovelLoan){
        NovelLoan newNovelLoan = getNovelLoanData();
        newNovelLoan.setId(oldNovelLoan.getId());
        generalAdministrativeManagement.updateNovelLoan(newNovelLoan);
    }
    private static void acceptNovelLoan(NovelLoan novelLoan){
        generalAdministrativeManagement.approveNovelLoan(novelLoan);
    }
    private static void deleteNovelLoan(){
        String title = askString("Enter novel title: ");
        Novel novel = generalAdministrativeManagement.getNovelByTitle(title);
        generalAdministrativeManagement.deleteNovel(novel);
    }

}
