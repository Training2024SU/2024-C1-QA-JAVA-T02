package co.com.sofka.businessLogic.reader;


import co.com.sofka.model.User;

import static co.com.sofka.businessLogic.generalUser.UserProfileFunctions.userProfileMenuOptions;
import static co.com.sofka.businessLogic.reader.ReaderAuthorFunctions.readerAuthorMenuOptions;
import static co.com.sofka.businessLogic.reader.ReaderBookFunctions.readerBookMenuOptions;
import static co.com.sofka.businessLogic.reader.ReaderNovelFunctions.readerNovelMenuOptions;
import static co.com.sofka.menu.MenuConstant.enterYourOptionMessage;
import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.readerMenuMessage;
import static co.com.sofka.utils.Utils.getIntOption;

public class ReaderFunctions {
    public static void readerMenu(User user) {
        boolean keepMenu = true;
        while (keepMenu) {
            readerMenuMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option) {
                case 1 -> readerBookMenuOptions(user);
                case 2 -> readerNovelMenuOptions(user);
                case 3 -> readerAuthorMenuOptions(user);
                case 4 -> userProfileMenuOptions(user);
                case 5 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }
}
