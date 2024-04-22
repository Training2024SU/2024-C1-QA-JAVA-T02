package co.com.sofka.businessLogic.generalUser;

import co.com.sofka.businessLogic.administrator.AdministratorManagement;
import co.com.sofka.model.User;

import java.time.LocalDate;
import java.util.Objects;

import static co.com.sofka.businessLogic.Authentication.login;
import static co.com.sofka.menu.MenuConstant.enterYourOptionMessage;
import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.userProfileMenuMessage;
import static co.com.sofka.utils.Utils.askDate;
import static co.com.sofka.utils.Utils.askInt;
import static co.com.sofka.utils.Utils.askString;

public class UserProfileFunctions {

    private static final AdministratorManagement administratorManagement =
            new AdministratorManagement();

    public static void userProfileMenuOptions(User user) {
        boolean keepMenu = true;
        while (keepMenu) {
            userProfileMenuMessage(user);
            int option = askInt(enterYourOptionMessage);
            switch (option) {
                case 1 -> updateProfile(user);
                case 2 -> changePassword(user);
                case 3 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void updateProfile(User user) {
        User validUser = validateUser(user);
        if (validUser != null) {
            System.out.println("Current data: ");
            printUserProfile(validUser);
            System.out.println("ENTER NEW DATA");
            String name = askString("Enter name: ");
            String email = askString("Enter email: ");
            LocalDate birthDate = askDate("Enter Birth Date: ");
            String phone = askString("Enter phone number: ");
            validUser.setName(name);
            validUser.setEmail(email);
            validUser.setBirthDate(birthDate);
            validUser.setPhone(phone);
            administratorManagement.updateUser(validUser);
        }
    }

    private static void printUserProfile(User user) {
        System.out.println("User's name: " + user.getName());
        System.out.println("User's email: " + user.getEmail());
        System.out.println("User's password: " + user.getPassword());
        System.out.println("User's role: " + user.getRole());
        System.out.println();
    }

    private static void changePassword(User user) {
        User validUser = validateUser(user);
        if (validUser != null) {
            String password = askString("Enter new password: ");
            validUser.setPassword(password);
            administratorManagement.updateUser(validUser);
        }
    }

    private static User validateUser(User user) {
        System.out.println("Validate your credentials before proceeding: ");
        var revalidated = login();
        if (revalidated != null && Objects.equals(revalidated.getId(), user.getId())) {
            return revalidated;
        }
        return null;
    }
}
