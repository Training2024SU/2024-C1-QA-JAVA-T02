package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.services.UserService;

import java.sql.Date;
import java.util.ResourceBundle;
import java.util.Scanner;


public class PersonalUserMenu {
    private final UserService userService;
    private  User user;
    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public PersonalUserMenu(UserService userService,  User user) {
        this.userService = userService;
        this.user = user;
        this.user = getUserData();
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
        Scanner scanner = new Scanner(System.in);
        if ((user.getBiography() == null)){
            ExtraData result = getExtraData(scanner);
            user.setBiography(result.biography());
            user.setBirthday(result.birthday());
            userService.insertUserInfo(user);
        }else {
            System.out.println("You already have added your personal data");
        }
    }

    private void editPersonalInfo()  {
        Scanner scanner = new Scanner(System.in);
        BasicData basicResult = getBasicData(scanner);
        user.setName(basicResult.name());
        user.setEmail(basicResult.email());
        userService.updateUser(user);
        userService.updateUserPassword(user, basicResult.password());
        if(!(user.getBiography() == null)){
            ExtraData result = getExtraData(scanner);
            user.setBiography(result.biography());
            user.setBirthday(result.birthday());
            userService.updateUserInfo(user);
        }
    }

    private static BasicData getBasicData(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        return new BasicData(name, email, password);
    }

    private record BasicData(String name, String email, String password) {
    }

    private static ExtraData getExtraData(Scanner scanner) {
        System.out.print("Enter biography: ");
        String biography =  scanner.nextLine();
        System.out.print("Enter birthday (yyyy-mm-dd): ");
        Date birthday = Date.valueOf(scanner.nextLine());
        return new ExtraData(biography, birthday);
    }

    private record ExtraData(String biography, Date birthday) {
    }

    private int showMenu() {
        String visitorChoices = messages.getString("personalInfo.choices.user");

        MenuChoices menu = new MenuChoices("PersonalInfo", visitorChoices, "",
                "", "", "");
        return menu.showMenu(user);
    }
    public void seePersonalInfo(){
        System.out.println(user);
    }

    private User getUserData() {
        return userService.getAllUsersExtraInfo()
                .stream()
                .filter(personalUser -> personalUser.getEmail().equals(user.getEmail()))
                .findFirst().orElse(null);
    }
}
