package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.services.DataService;
import com.davidbonelo.services.UserService;

import java.util.ResourceBundle;

import static com.davidbonelo.utils.Permissions.validMenuAccess;

public class PersonalUserMenu {
    private final UserService userService;
    private final User user;
    private final ResourceBundle messages = ResourceBundle.getBundle("messages");
    private final DataService dataService;


    public PersonalUserMenu(UserService userService, DataService dataService, User user) {
        this.userService = userService;
        this.dataService = dataService;
        this.user = user;
    }
    public void menu() {
        while (true) {
            int menuChoice = showMenu();
            switch (menuChoice) {
                case 1 -> seePersonalInfo();
                case 2 -> editPersonalInfo();
                case 3 -> addPersonalInfo();
                case 0 -> {
                    return;
                }
                default -> System.out.println(messages.getString("unknownOption"));
            }
        }
    }

    private void addPersonalInfo() {
    }

    private void editPersonalInfo() {
    }

    private int showMenu() {
        String visitorChoices = messages.getString("personalInfo.choices.user");

        MenuChoices menu = new MenuChoices("Personal Info", visitorChoices, "",
                "", "");
        return menu.showMenu(user);
    }
    public void seePersonalInfo(){
        
    }
}
