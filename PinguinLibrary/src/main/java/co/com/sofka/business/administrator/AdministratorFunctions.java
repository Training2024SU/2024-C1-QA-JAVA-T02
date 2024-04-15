package co.com.sofka.business.administrator;

import co.com.sofka.model.User;

import static co.com.sofka.business.generalAdmin.AdministratorBookFunctions.administratorBookMenuOptions;
import static co.com.sofka.business.generalAdmin.AdministratorBookLoanFunctions.administratorBookLoanMenuOptions;
import static co.com.sofka.business.generalAdmin.AdministratorNovelFunctions.administratorNovelMenuOptions;
import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.administratorMenuMessage;
import static co.com.sofka.utils.Utils.getIntOption;

public class AdministratorFunctions {
    public static void administratorMenu(User user){
        boolean keepMenu = true;
        while (keepMenu){
            //administrator
            administratorMenuMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option){
                case 1:
                    administratorBookMenuOptions(user);
                    break;
                case 2:
                    administratorBookLoanMenuOptions(user);
                    break;
                case 3:
                    administratorNovelMenuOptions(user);
                    break;
                case 4:
                    // novel loan
                    //readerBookMenuOptions(user);
                    break;
                case 5:
                    // author
                    //readerAuthorMenuOptions(user);
                    break;
                case 6:
                    // user
                    //readerAuthorMenuOptions(user);
                    break;
                case 7:
                    System.out.println(exitingMessage);
                    keepMenu = false;
                    break;
                default:
                    System.out.println(incorrectOptionMessage);
            }
        }
    }
}
