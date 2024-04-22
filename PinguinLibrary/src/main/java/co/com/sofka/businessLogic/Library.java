package co.com.sofka.businessLogic;

import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.model.User;

import static co.com.sofka.businessLogic.Authentication.login;
import static co.com.sofka.businessLogic.Authentication.register;
import static co.com.sofka.businessLogic.administrator.AdministratorFunctions.administratorMenu;
import static co.com.sofka.businessLogic.assistant.AssistantOptions.assistantMenu;
import static co.com.sofka.businessLogic.reader.ReaderFunctions.readerMenu;
import static co.com.sofka.businessLogic.superuser.SuperuserFunctions.superuserMenu;
import static co.com.sofka.database.DatabaseConfigConstants.DATA_BASE_NAME;
import static co.com.sofka.database.DatabaseConfigConstants.PASSWORD;
import static co.com.sofka.database.DatabaseConfigConstants.SERVER;
import static co.com.sofka.database.DatabaseConfigConstants.USER;
import static co.com.sofka.menu.MenuConstant.enterYourOptionMessage;
import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.menuMessage;
import static co.com.sofka.utils.Utils.askInt;

public class Library {

    public static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void startApp() {
        openConnection();
        mainMenuFunctions();
        closeConnection();
    }

    private static void mainMenuFunctions() {
        boolean runApp = true;
        while (runApp) {
            menuMessage();
            int option = askInt(enterYourOptionMessage);
            switch (option) {
                case 1:
                    User user = login();
                    if (user != null) redirectPersonalizedMenu(user);
                    else System.exit(0);
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println(exitingMessage);
                    runApp = false;
                    break;
                default:
                    System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void redirectPersonalizedMenu(User user) {
        switch (user.getRole()) {
            case READER -> readerMenu(user);
            case ADMINISTRATOR -> administratorMenu(user);
            case ASSISTANT -> assistantMenu(user);
            case SUPERUSER -> superuserMenu(user);
        }
    }

    public static void openConnection() {
        mySqlOperation.setServer(SERVER);
        mySqlOperation.setDataBaseName(DATA_BASE_NAME);
        mySqlOperation.setUser(USER);
        mySqlOperation.setPassword(PASSWORD);
    }

    public static void closeConnection() {
        mySqlOperation.close();
    }

}
