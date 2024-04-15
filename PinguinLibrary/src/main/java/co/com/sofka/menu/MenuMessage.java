package co.com.sofka.menu;

import co.com.sofka.model.User;

public class MenuMessage {
    public static void menuMessage(){
        System.out.println(MenuConstant.welcomeMessage);
        System.out.println(MenuConstant.loginMessage);
        System.out.println(MenuConstant.registerMessage);
        System.out.println(MenuConstant.exitMessage);
    }

    public static void administratorMenuMessage(User user){
        System.out.printf((MenuConstant.personalizedWelcomeMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.bookOptionsMessage) + "%n", 1);
        System.out.printf((MenuConstant.bookLoanOptionsMessage) + "%n", 2);
        System.out.printf((MenuConstant.novelOptionsMessage) + "%n", 3);
        System.out.printf((MenuConstant.novelLoanOptions) + "%n", 4);
        System.out.printf((MenuConstant.authorOptionsMessage) + "%n", 5);
        System.out.printf((MenuConstant.userOptionsMessage) + "%n", 6);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 7);
    }
    public static void administratorBookMenuMessage(User user){
        System.out.printf((MenuConstant.personalizedWelcomeBookMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.createBookMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeAllBooksMessage) + "%n", 2);
        System.out.printf((MenuConstant.seeBookByNameMessage) + "%n", 3);
        System.out.printf((MenuConstant.updateBookMessage) + "%n", 4);
        System.out.printf((MenuConstant.deleteBookMessage) + "%n", 5);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 6);
    }

    public static void administratorBookLoanMessage(User user){
        System.out.printf((MenuConstant.personalizedWelcomeBookLoanMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.createBookLoanMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeBookLoansMessage) + "%n", 2);
        System.out.printf((MenuConstant.editAcceptDeniedBookLoansMessage) + "%n", 3);
        System.out.printf((MenuConstant.deleteBookLoanMessage) + "%n", 4);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 5);
    }

    public static void administratorNovelMenuMessage(User user){
        System.out.printf((MenuConstant.personalizedWelcomeNovelMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.createNovelMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeAllNovelsMessage) + "%n", 2);
        System.out.printf((MenuConstant.seeNovelByNameMessage) + "%n", 3);
        System.out.printf((MenuConstant.updateNovelMessage) + "%n", 4);
        System.out.printf((MenuConstant.deleteNovelMessage) + "%n", 5);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 6);
    }
    public static void readerMenuMessage(User user){
        System.out.printf((MenuConstant.personalizedWelcomeMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.bookOptionsMessage) + "%n", 1);
        System.out.printf((MenuConstant.novelOptionsMessage) + "%n", 2);
        System.out.printf((MenuConstant.authorOptionsMessage) + "%n", 3);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 4);
    }
    public static void readerBookMenuMessage(User user){
        System.out.printf((MenuConstant.personalizedWelcomeBookMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.seeAllBooksMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeBookByNameMessage) + "%n", 2);
        System.out.printf((MenuConstant.loanBookMessage) + "%n", 3);
        System.out.printf((MenuConstant.seeMyBookLoansMessage) + "%n", 4);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 5);
    }
    public static void readerNovelMenuMessage(User user){
        System.out.printf((MenuConstant.personalizedWelcomeNovelMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.seeAllNovelsMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeNovelByNameMessage) + "%n", 2);
        System.out.printf((MenuConstant.loanNovelMessage) + "%n", 3);
        System.out.printf((MenuConstant.seeMyNovelLoansMessage) + "%n", 4);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 5);
    }

    public static void readerAuthorMenuMessage(User user){
        System.out.printf((MenuConstant.personalizedWelcomeAuthorMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.seeAllAuthorsMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeAuthorByNameMessage) + "%n", 2);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 3);
    }
}
