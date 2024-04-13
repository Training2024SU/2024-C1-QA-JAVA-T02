package co.com.sofka.business.reader;


import co.com.sofka.model.Book;
import co.com.sofka.model.User;

import static co.com.sofka.business.reader.ReaderManagement.*;
import static co.com.sofka.menu.Menu.displayReaderPrincipalMenu;
import static co.com.sofka.menu.MenuMessage.readerBookMenuMessage;
import static co.com.sofka.utils.Utils.getIntOption;
import static co.com.sofka.utils.Utils.getStringOption;

public class ReaderFunctions {
    public static void readerMenu(User user){
        boolean keepMenu = true;
        while (keepMenu){
            displayReaderPrincipalMenu();
            System.out.print("Enter your option: ");
            int option = getIntOption();
            switch (option){
                case 1:
                    readerBookMenuOptions(user);
                    break;
                case 2:
                    // novel options
                    //System.out.println(libraryReaderManagement.getAllNovel());
                    break;
                case 3:
                    // Author options
                    //System.out.println(libraryReaderManagement.getAllAuthors());
                    break;
                case 4:
                    // back
                    System.out.println("Exit..");
                    keepMenu = false;
                    break;
                default:
                    System.out.println("Enter a valid option");
            }
        }
    }

    public static void readerBookMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            readerBookMenuMessage();
            System.out.print("Enter your option: ");
            int option = getIntOption();
            switch (option){
                case 1:
                    System.out.println(getAllAvailableBooks());
                    break;
                case 2:
                    seeBookByNameOption();
                    break;
                case 3:
                    loanBookOption(user);
                    break;
                case 4:
                    System.out.println(getAllUserBookLoan(user));
                    break;
                case 5:
                    System.out.println("Exit..");
                    keepMenu = false;
                    break;
                default:
                    System.out.println("Enter a valid option");
            }
        }

    }

    private static void loanBookOption(User user) {
        System.out.println("Enter book name: ");
        String book = getStringOption();
        Book bookToLoan =  getAvailableBookByTitle(book);
        if(bookToLoan == null){
            System.out.println("There are not available options");
        }else{
            loanBook(user, bookToLoan);
        }
    }
    private static void seeBookByNameOption() {
        System.out.print("Enter the title: ");
        String name = getStringOption();
        System.out.println(name);
        System.out.println(getAvailableBookByTitle(name));
    }
}
