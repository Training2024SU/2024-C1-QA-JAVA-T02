package co.com.sofka.businessLogic.reader;

import co.com.sofka.model.Novel;
import co.com.sofka.model.User;

import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.readerNovelMenuMessage;
import static co.com.sofka.utils.Utils.askInt;
import static co.com.sofka.utils.Utils.askString;

public class ReaderNovelFunctions {
    private static final ReaderManagement readerManagement = new ReaderManagement();
    public static void readerNovelMenuOptions(User user){
        boolean keepMenu = true;
        while (keepMenu){
            readerNovelMenuMessage(user);
            int option = askInt("Enter your option: ");
            switch (option){
                case 1:
                    System.out.println(readerManagement.getAllAvailableNovels());
                    break;
                case 2:
                    seeNovelByNameOption();
                    break;
                case 3:
                    loanNovelOption(user);
                    break;
                case 4:
                    System.out.println(readerManagement.getAllUserNovelLoan(user));
                    break;
                case 5:
                    returnNovelLoan();
                    break;
                case 6:
                    System.out.println(exitingMessage);
                    keepMenu = false;
                    break;
                default:
                    System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void returnNovelLoan() {
        String id = askString("Enter novel loan id: ");
        readerManagement.returnNovelLoan(id);
    }

    private static void loanNovelOption(User user) {
        String novel = askString("Enter novel name: ");
        Novel novelToLoan =  readerManagement.getAvailableNovelByTitle(novel);
        if(novelToLoan == null){
            System.out.println("There are not available options");
        }else{
            readerManagement.loanNovel(user, novelToLoan);
        }
    }
    private static void seeNovelByNameOption() {
        String name = askString("Enter the title: ");
        System.out.println(readerManagement.getAvailableNovelByTitle(name));
    }
}
