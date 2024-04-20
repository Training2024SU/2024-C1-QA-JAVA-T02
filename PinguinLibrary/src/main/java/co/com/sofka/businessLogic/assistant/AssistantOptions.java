package co.com.sofka.businessLogic.assistant;

import co.com.sofka.model.User;

import static co.com.sofka.businessLogic.generalAdmin.AdministratorAuthorFunctions.administratorAuthorMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorBookFunctions.administratorBookMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorBookLoanFunctions.administratorBookLoanMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorNovelFunctions.administratorNovelMenuOptions;
import static co.com.sofka.businessLogic.generalAdmin.AdministratorNovelLoanFunctions.administratorNovelLoanMenuOptions;
import static co.com.sofka.businessLogic.generalUser.UserProfileFunctions.userProfileMenuOptions;
import static co.com.sofka.menu.MenuConstant.enterYourOptionMessage;
import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.assistantMenuMessage;
import static co.com.sofka.utils.Utils.askInt;

public class AssistantOptions {
    public static void assistantMenu(User user) {
        boolean keepMenu = true;
        while (keepMenu) {
            assistantMenuMessage(user);
            int option = askInt(enterYourOptionMessage);
            switch (option) {
                case 1 -> administratorBookMenuOptions(user);
                case 2 -> administratorBookLoanMenuOptions(user);
                case 3 -> administratorNovelMenuOptions(user);
                case 4 -> administratorNovelLoanMenuOptions(user);
                case 5 -> administratorAuthorMenuOptions(user);
                case 6 -> userProfileMenuOptions(user);
                case 7 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }
}
