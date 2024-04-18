package co.com.sofka.businessLogic.administrator;

import co.com.sofka.businessLogic.generalAdmin.GeneralAdministrativeManagement;
import co.com.sofka.enums.UserType;
import co.com.sofka.model.User;

import java.time.LocalDate;
import java.util.UUID;

import static co.com.sofka.menu.MenuConstant.enterYourOptionMessage;
import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.administratorUserMenuMessage;
import static co.com.sofka.utils.Utils.askDate;
import static co.com.sofka.utils.Utils.getIntOption;
import static co.com.sofka.utils.Utils.getStringOption;

public class AdministratorUserFunctions {
    private static final AdministratorManagement administratorManagement =
            new AdministratorManagement();
    private static final GeneralAdministrativeManagement generalAdministrativeManagement =
            new GeneralAdministrativeManagement();

    public static void administratorUserMenuOptions(User user) {
        boolean keepMenu = true;
        while (keepMenu) {
            administratorUserMenuMessage(user);
            System.out.print(enterYourOptionMessage);
            int option = getIntOption();
            switch (option) {
                case 1 -> insertUser();
                case 2 -> seeAllUsers();
                case 3 -> seeUserByEmail();
                case 4 -> updateUser();
                case 5 -> deleteUser();
                case 6 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void deleteUser() {
        System.out.println("Enter user's email: ");
        String email = getStringOption();
        administratorManagement.deleteUser(generalAdministrativeManagement.getUserByEmail(email));
    }

    private static void updateUser() {
        System.out.println("Enter user's email: ");
        User oldUser = generalAdministrativeManagement.getUserByEmail(getStringOption());
        System.out.println("ENTER NEW DATA");
        User newUser = getUserData();
        newUser.setId(oldUser.getId());
        administratorManagement.updateUser(newUser);
    }

    private static User getUserData() {
        System.out.println("Enter name: ");
        String name = getStringOption();
        System.out.println("Enter email: ");
        String email = getStringOption();
        System.out.println("Enter password: ");
        String password = getStringOption();
        System.out.println("Enter user role: (ADMINISTRATOR/ASSISTANT/READER)");
        UserType role = UserType.valueOf(getStringOption());
        LocalDate birthDate = askDate("Enter Birth Date: ");
        System.out.println("Enter phone number: ");
        String phone = getStringOption();
        return new User(UUID.randomUUID().toString(), name, email, password, role, birthDate,
                phone);
    }

    private static void seeUserByEmail() {
        System.out.println("Enter user's email: ");
        System.out.println(generalAdministrativeManagement.getUserByEmail(getStringOption()));
    }

    private static void seeAllUsers() {
        administratorManagement.getAllUsers().forEach(user -> {
            System.out.println("User's name: " + user.getName());
            System.out.println("User's email: " + user.getEmail());
            System.out.println("User's password: " + user.getPassword());
            System.out.println("User's role: " + user.getRole());
            System.out.println();
        });
    }

    public static void insertUser() {
        administratorManagement.insertUser(getUserData());
    }
}
