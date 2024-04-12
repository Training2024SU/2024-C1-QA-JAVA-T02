package co.com.sofka.utils;

import co.com.sofka.DAO.Impl.UserDAO;
import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.User;

import java.util.Scanner;

public class Login {
    private static final Scanner scanner = new Scanner(System.in);
    public static User login(MySqlOperation mySqlOperation) {
        UserDAO userDAO = new UserDAO(mySqlOperation);
        User user;
        int attempts = 0;
        boolean loggedIn = false;
        while (attempts < 3 && !loggedIn) {
            System.out.print("Enter your email: ");
            String email = scanner.next();
            System.out.print("Enter your password: ");
            String password = scanner.next();
            user = userDAO.getUserByEmail(email);
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
}
