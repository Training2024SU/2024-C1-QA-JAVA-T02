package co.com.sofka.utils;

import java.time.LocalDate;
import java.util.Scanner;

public class Utils {

    public static int getIntOption() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return option;
    }

    public static String getStringOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static LocalDate askDate(String prompt) {
        Scanner scanner = new Scanner(System.in);
        prompt += " (YYYY-MM-DD)\n> ";
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        LocalDate date = null;
        // Invalid input handling
        while (input.isEmpty() || date == null) {
            try {
                date = LocalDate.parse(input);
            } catch (Exception ignored) {
                System.out.println("invalid input. Enter a date in the format YYYY-MM-DD");
                System.out.print(prompt);
                input = scanner.nextLine().trim();
            }
        }
        return date;
    }
}
