package co.com.sofka.businessLogic.superuser;

import co.com.sofka.model.User;

import static co.com.sofka.businessLogic.administrator.AdministratorFunctions.administratorMenu;
import static co.com.sofka.businessLogic.assistant.AssistantOptions.assistantMenu;
import static co.com.sofka.businessLogic.reader.ReaderFunctions.readerMenu;
import static co.com.sofka.menu.MenuConstant.enterYourOptionMessage;
import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.superuserMenuMessage;
import static co.com.sofka.utils.Utils.getIntOption;

public class SuperuserFunctions {

    public static void superuserMenu(User user) {
        while (true) {
            superuserMenuMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option) {
                case 1 -> readerMenu(user);
                case 2 -> administratorMenu(user);
                case 3 -> assistantMenu(user);
                case 4 -> superuserMenu(user);
                case 5 -> {
                    System.out.println(exitingMessage);
                    return;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }
}
