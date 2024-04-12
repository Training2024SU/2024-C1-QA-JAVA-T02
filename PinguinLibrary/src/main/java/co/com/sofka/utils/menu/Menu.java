package co.com.sofka.utils.menu;

import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.User;
import co.com.sofka.utils.menu.administratorMenu.AdministratorMenu;
import co.com.sofka.utils.menu.readerMenu.ReaderMenu;

import static co.com.sofka.database.DatabaseConfigConstants.*;
import static co.com.sofka.database.DatabaseConfigConstants.PASSWORD;
import static co.com.sofka.utils.Login.login;

public class Menu {
    private static final MySqlOperation mySqlOperation = new MySqlOperation();
    public void displayMenu(){

        openConnection();
        User user = login(mySqlOperation);
        if (user == null) {
            System.out.println("Login failed. Exiting...");
            closeConnection();
            System.exit(0);
        }
        switch (user.getRole()){
            case ADMINISTRATOR:
                AdministratorMenu administratorMenu = new AdministratorMenu(user, mySqlOperation);
                administratorMenu.displayMenu();
                break;
            case ASSISTANT:
                break;
            case READER:
                ReaderMenu readerMenu = new ReaderMenu(user, mySqlOperation);
                readerMenu.displayMenu();
                break;
        }
        closeConnection();
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
