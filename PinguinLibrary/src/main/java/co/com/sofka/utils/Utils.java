package co.com.sofka.utils;

import co.com.sofka.enums.ResourceType;

import java.time.LocalDate;
import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    private Utils() {
        throw new IllegalStateException("Utility Class");
    }

    public static int askInt(String prompt) {
        prompt += "\n> ";
        System.out.print(prompt);
        // Invalid integer handling
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input, enter an integer");
            System.out.print(prompt);
            scanner.next(); // clear invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // clear buffer
        return input;
    }

    public static String askString(String prompt) {
        prompt += "\n> ";
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        // Invalid input handling
        while (input.isEmpty()) {
            System.out.println("Invalid input, enter a non empty string");
            System.out.print(prompt);
            input = scanner.nextLine().trim();
        }
        return input;
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
