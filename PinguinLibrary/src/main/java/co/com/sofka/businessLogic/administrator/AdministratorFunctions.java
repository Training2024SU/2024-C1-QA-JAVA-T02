package co.com.sofka.businessLogic.administrator;

import co.com.sofka.model.User;

import static co.com.sofka.businessLogic.administrator.AdministratorUserFunctions.administratorUserMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorAuthorFunctions.administratorAuthorMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorBookFunctions.administratorBookMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorBookLoanFunctions.administratorBookLoanMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorNovelFunctions.administratorNovelMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorNovelLoanFunctions.administratorNovelLoanMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorResourceFunctions.administratorResourceMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorResourceLoanFunctions.administratorResourceLoanMenuOptions;
import static co.com.sofka.businessLogic.generalUser.UserProfileFunctions.userProfileMenuOptions;
import static co.com.sofka.menu.MenuConstant.enterYourOptionMessage;
import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.administratorMenuMessage;
import static co.com.sofka.utils.Utils.askInt;

public class AdministratorFunctions {
    public static void administratorMenu(User user) {
        boolean keepMenu = true;
        while (keepMenu) {
            administratorMenuMessage(user);
            int option = askInt(enterYourOptionMessage);
            switch (option) {
                case 1 -> administratorBookMenuOptions(user);
                case 2 -> administratorBookLoanMenuOptions(user);
                case 3 -> administratorNovelMenuOptions(user);
                case 4 -> administratorNovelLoanMenuOptions(user);
                case 5 -> administratorResourceMenuOptions(user);
                case 6 -> administratorResourceLoanMenuOptions(user);
                case 7 -> administratorAuthorMenuOptions(user);
                case 8 -> administratorUserMenuOptions(user);
                case 9 -> userProfileMenuOptions(user);
                case 0 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }
}
