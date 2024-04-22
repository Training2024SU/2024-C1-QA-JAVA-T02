package co.com.sofka.businessLogic;

import co.com.sofka.DAO.Impl.UserDAOImpl;
import co.com.sofka.enums.UserType;
import co.com.sofka.model.User;

import java.time.LocalDate;
import java.util.UUID;

import static co.com.sofka.utils.Utils.askDate;
import static co.com.sofka.utils.Utils.askString;


public class Authentication {
    public static User login() {
        User user;
        int attempts = 0;
        boolean loggedIn = false;
        while (attempts < 3 && !loggedIn) {
            String email = askString("Enter your email: ");
            String password = askString("Enter your password: ");
            user = verifyEmail(email);
            if (user == null) {
                System.out.println("Incorrect email. Please try again.");
            } else if (!user.getPassword().equals(password)) {
                System.out.println("Incorrect password. Please try again.");
                System.out.println("You have: " + (2 - attempts) + " attempts left");
                attempts++;
            } else {
                System.out.println("Login successful. Welcome, " + user.getName() + "!");
                return user;
            }
        }
        if (!loggedIn) {
            System.out.println("Too many attempts. Please try again later.");
        }
        return null;
    }

    public static void register() {
        String name = askString("Enter your name: ");
        String email = askString("Enter your email: ");
        String password = askString("Enter your password: ");
        LocalDate birthDate = askDate("Enter your birth date: ");
        String phone = askString("Enter your phone number: ");
        User user = verifyEmail(email);
        if (user != null) {
            System.out.println("There is an user with that email");
        } else {
            UserDAOImpl userDAOImpl = new UserDAOImpl();
            user = new User(UUID.randomUUID().toString(), name, email, password, UserType.READER,
                    birthDate, phone);
            userDAOImpl.insertUser(user);
        }
    }

    public static User verifyEmail(String email) {
        UserDAOImpl userDAOImpl = new UserDAOImpl();
        return userDAOImpl.findUserByEmail(email);
    }
}
