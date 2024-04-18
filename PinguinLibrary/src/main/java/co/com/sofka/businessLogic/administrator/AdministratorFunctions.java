package co.com.sofka.businessLogic.administrator;

import co.com.sofka.model.User;

import static co.com.sofka.businessLogic.administrator.AdministratorUserFunctions.administratorUserMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorAuthorFunctions.administratorAuthorMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorBookFunctions.administratorBookMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorBookLoanFunctions.administratorBookLoanMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorNovelFunctions.administratorNovelMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorNovelLoanFunctions.administratorNovelLoanMenuOptions;
import static co.com.sofka.businessLogic.generalUser.UserProfileFunctions.userProfileMenuOptions;
import static co.com.sofka.menu.MenuConstant.enterYourOptionMessage;
import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.administratorMenuMessage;
import static co.com.sofka.utils.Utils.getIntOption;

public class AdministratorFunctions {
    public static void administratorMenu(User user) {
        boolean keepMenu = true;
        while (keepMenu) {
            administratorMenuMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option) {
                case 1 -> administratorBookMenuOptions(user);
                case 2 -> administratorBookLoanMenuOptions(user);
                case 3 -> administratorNovelMenuOptions(user);
                case 4 -> administratorNovelLoanMenuOptions(user);
                case 5 -> administratorAuthorMenuOptions(user);
                case 6 -> administratorUserMenuOptions(user);
                case 7 -> userProfileMenuOptions(user);
                case 8 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }
}
