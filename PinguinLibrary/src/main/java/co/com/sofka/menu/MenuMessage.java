package co.com.sofka.menu;

import co.com.sofka.model.User;


public class MenuMessage {
    public static void menuMessage() {
        System.out.println(MenuConstant.welcomeMessage);
        System.out.println(MenuConstant.loginMessage);
        System.out.println(MenuConstant.registerMessage);
        System.out.println(MenuConstant.exitMessage);
    }

    public static void administratorMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.bookOptionsMessage) + "%n", 1);
        System.out.printf((MenuConstant.bookLoanOptionsMessage) + "%n", 2);
        System.out.printf((MenuConstant.novelOptionsMessage) + "%n", 3);
        System.out.printf((MenuConstant.novelLoanOptionsMessage) + "%n", 4);
        System.out.printf((MenuConstant.resourceOptionsMessage) + "%n", 5);
        System.out.printf((MenuConstant.resourceLoanOptionsMessage) + "%n", 6);
        System.out.printf((MenuConstant.authorOptionsMessage) + "%n", 7);
        System.out.printf((MenuConstant.userOptionsMessage) + "%n", 8);
        System.out.printf((MenuConstant.profileOptionsMessage) + "%n", 9);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 0);
    }

    public static void administratorBookMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeBookMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.createBookMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeAllBooksMessage) + "%n", 2);
        System.out.printf((MenuConstant.seeBookByNameMessage) + "%n", 3);
        System.out.printf((MenuConstant.updateBookMessage) + "%n", 4);
        System.out.printf((MenuConstant.deleteBookMessage) + "%n", 5);
        System.out.printf((MenuConstant.exportBookInfoMessage) + "%n", 6);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 7);
    }

    public static void administratorBookLoanMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeBookLoanMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.createBookLoanMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeBookLoansMessage) + "%n", 2);
        System.out.printf((MenuConstant.editAcceptBookLoansMessage) + "%n", 3);
        System.out.printf((MenuConstant.deleteBookLoanMessage) + "%n", 4);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 5);
    }

    public static void administratorNovelMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeNovelMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.createNovelMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeAllNovelsMessage) + "%n", 2);
        System.out.printf((MenuConstant.seeNovelByNameMessage) + "%n", 3);
        System.out.printf((MenuConstant.updateNovelMessage) + "%n", 4);
        System.out.printf((MenuConstant.deleteNovelMessage) + "%n", 5);
        System.out.printf((MenuConstant.exportNovelInfoMessage) + "%n", 6);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 7);
    }

    public static void administratorAuthorLoanMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeAuthorMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.createAuthorMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeAllAuthorsMessage) + "%n", 2);
        System.out.printf((MenuConstant.seeAuthorByNameMessage) + "%n", 3);
        System.out.printf((MenuConstant.updateAuthorMessage) + "%n", 4);
        System.out.printf((MenuConstant.deleteAuthorMessage) + "%n", 5);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 6);
    }

    public static void administratorNovelLoanMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeNovelLoanMessage) + "%n",
                user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.createNovelMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeNovelLoansMessage) + "%n", 2);
        System.out.printf((MenuConstant.editAcceptNovelLoansMessage) + "%n", 3);
        System.out.printf((MenuConstant.deleteNovelLoanMessage) + "%n", 4);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 5);
    }

    public static void administratorResourceMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeResourceMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.seeAllSongsMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeAllVideoMessage) + "%n", 2);
        System.out.printf((MenuConstant.seeAllEssayMessage) + "%n", 3);
        System.out.printf((MenuConstant.createResourceMessage) + "%n", 4);
        System.out.printf((MenuConstant.updateResourceMessage) + "%n", 5);
        System.out.printf((MenuConstant.deleteResourceMessage) + "%n", 6);
        System.out.printf((MenuConstant.exportResourceInfoMessage) + "%n", 7);
        System.out.printf((MenuConstant.importResourceInfoMessage) + "%n", 8);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 9);
    }

    public static void administratorResourceLoanMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeResourceLoanMessage) + "%n",
                user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.seeResourceLoansMessage) + "%n", 1);
        System.out.printf((MenuConstant.searchLoansByEmailMessage) + "%n", 2);
        System.out.printf((MenuConstant.seeLoanDetailsMessage) + "%n", 3);
        System.out.printf((MenuConstant.approveLoanMessage) + "%n", 4);
        System.out.printf((MenuConstant.finishLoanMessage) + "%n", 5);
        System.out.printf((MenuConstant.deleteResourceLoanMessage) + "%n", 6);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 7);
    }

    public static void administratorUserMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeUserMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.createUserMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeAllUsersMessage) + "%n", 2);
        System.out.printf((MenuConstant.seeUserByEmailMessage) + "%n", 3);
        System.out.printf((MenuConstant.updateUserMessage) + "%n", 4);
        System.out.printf((MenuConstant.deleteUserMessage) + "%n", 5);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 6);

    }

    public static void assistantMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.bookOptionsMessage) + "%n", 1);
        System.out.printf((MenuConstant.bookLoanOptionsMessage) + "%n", 2);
        System.out.printf((MenuConstant.novelOptionsMessage) + "%n", 3);
        System.out.printf((MenuConstant.novelLoanOptionsMessage) + "%n", 4);
        System.out.printf((MenuConstant.resourceOptionsMessage) + "%n", 5);
        System.out.printf((MenuConstant.resourceLoanOptionsMessage) + "%n", 6);
        System.out.printf((MenuConstant.authorOptionsMessage) + "%n", 7);
        System.out.printf((MenuConstant.profileOptionsMessage) + "%n", 8);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 9);

    }

    public static void readerMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.bookOptionsMessage) + "%n", 1);
        System.out.printf((MenuConstant.novelOptionsMessage) + "%n", 2);
        System.out.printf((MenuConstant.resourceOptionsMessage) + "%n", 3);
        System.out.printf((MenuConstant.authorOptionsMessage) + "%n", 4);
        System.out.printf((MenuConstant.profileOptionsMessage) + "%n", 5);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 6);
    }

    public static void readerBookMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeBookMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.seeAllBooksMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeBookByNameMessage) + "%n", 2);
        System.out.printf((MenuConstant.loanBookMessage) + "%n", 3);
        System.out.printf((MenuConstant.seeMyBookLoansMessage) + "%n", 4);
        System.out.printf((MenuConstant.returnBookLMessage) + "%n", 5);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 6);
    }

    public static void readerNovelMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeNovelMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.seeAllNovelsMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeNovelByNameMessage) + "%n", 2);
        System.out.printf((MenuConstant.loanNovelMessage) + "%n", 3);
        System.out.printf((MenuConstant.seeMyNovelLoansMessage) + "%n", 4);
        System.out.printf((MenuConstant.returnNovelMessage) + "%n", 5);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 6);
    }

    public static void readerResourceMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeResourceMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.seeAllSongsMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeAllVideoMessage) + "%n", 2);
        System.out.printf((MenuConstant.seeAllEssayMessage) + "%n", 3);
        System.out.printf((MenuConstant.selectResourceMessage) + "%n", 4);
        System.out.printf((MenuConstant.listSelectedResourcesMessage) + "%n", 5);
        System.out.printf((MenuConstant.requestLoanMessage) + "%n", 6);
        System.out.printf((MenuConstant.seeMyResourceLoansMessage) + "%n", 7);
        System.out.printf((MenuConstant.seeMyLoanDetailsMessage) + "%n", 8);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 9);
    }

    public static void readerAuthorMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeAuthorMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.seeAllAuthorsMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeAuthorByNameMessage) + "%n", 2);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 3);
    }

    public static void superuserMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeSuperuserMessage) + "%n",
                user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.readerMenuMessage) + "%n", 1);
        System.out.printf((MenuConstant.assistantMenuMessage) + "%n", 2);
        System.out.printf((MenuConstant.administratorMenuMessage) + "%n", 3);
        System.out.printf((MenuConstant.createUserMessage) + "%n", 4);
        System.out.printf((MenuConstant.restoreSULoansMessage) + "%n", 5);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 6);
    }

    public static void userProfileMenuMessage(User user) {
        System.out.printf((MenuConstant.personalizedWelcomeProfileMessage) + "%n", user.getName());
        System.out.println(MenuConstant.separator);
        System.out.printf((MenuConstant.updateProfileMessage) + "%n", 1);
        System.out.printf((MenuConstant.changePasswordMessage) + "%n", 2);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 3);

    }
}
