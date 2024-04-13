package co.com.sofka.menu;

public class MenuMessage {
    public static void menuMessage(){
        System.out.println(MenuConstant.welcomeMessage);
        System.out.println(MenuConstant.loginMessage);
        System.out.println(MenuConstant.registerMessage);
        System.out.println(MenuConstant.exitMessage);
    }

    public static void readerMenuMessage(){
        System.out.printf((MenuConstant.bookOptions) + "%n", 1);
        System.out.printf((MenuConstant.novelOptions) + "%n", 2);
        System.out.printf((MenuConstant.authorOptions) + "%n", 3);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 4);
    }

    public static void readerBookMenuMessage(){
        System.out.printf((MenuConstant.seeAllBooksMessage) + "%n", 1);
        System.out.printf((MenuConstant.seeBookByNameMessage) + "%n", 2);
        System.out.printf((MenuConstant.loanBookMessage) + "%n", 3);
        System.out.printf((MenuConstant.seeMyBookLoansMessage) + "%n", 4);
        System.out.printf((MenuConstant.backMessageMessage) + "%n", 5);

    }
}
