package co.com.sofka.business;

import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.model.*;


import static co.com.sofka.business.administrator.AdministratorFunctions.administratorMenu;
import static co.com.sofka.business.assistant.AssistantOptions.assistantMenu;
import static co.com.sofka.business.Authentication.login;
import static co.com.sofka.business.Authentication.register;
import static co.com.sofka.business.reader.ReaderFunctions.readerMenu;
import static co.com.sofka.database.DatabaseConfigConstants.*;
import static co.com.sofka.database.DatabaseConfigConstants.PASSWORD;
import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.menuMessage;
import static co.com.sofka.utils.Utils.getIntOption;

public class Library {

    public static final MySqlOperation mySqlOperation = new MySqlOperation();

    public static void startApp(){
        openConnection();
        mainMenuFunctions();
        closeConnection();
    }

    private static void mainMenuFunctions() {
        boolean runApp = true;
        while (runApp){
            menuMessage();
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option){
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
        switch (user.getRole()){
            case READER:
                readerMenu(user);
                break;
            case ADMINISTRATOR:
                administratorMenu(user);
                break;
            case ASSISTANT:
                assistantMenu(user);
                break;
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
