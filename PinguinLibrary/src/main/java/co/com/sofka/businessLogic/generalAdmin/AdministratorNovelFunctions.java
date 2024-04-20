package co.com.sofka.businessLogic.generalAdmin;

import co.com.sofka.model.Novel;
import co.com.sofka.model.User;

import java.util.UUID;

import static co.com.sofka.menu.MenuConstant.*;
import static co.com.sofka.menu.MenuMessage.administratorNovelMenuMessage;
import static co.com.sofka.utils.CsvNovelManagement.exportNovelsData;
import static co.com.sofka.utils.Utils.askInt;
import static co.com.sofka.utils.Utils.askString;

public class AdministratorNovelFunctions {
    private static final GeneralAdministrativeManagement generalAdministrativeManagement = new GeneralAdministrativeManagement();
    public static void administratorNovelMenuOptions(User user){
        while (true){
            administratorNovelMenuMessage(user);
            int option = askInt(enterYourOptionMessage);
            switch (option) {
                case 1 -> insertNovel();
                case 2 -> seeAllNovels();
                case 3 -> seeNovelByTitle();
                case 4 -> updateNovel();
                case 5 -> deleteNovel();
                case 6 -> exportNovelsData();
                case 7 -> {
                    System.out.println(exitingMessage);
                    return;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void insertNovel(){
        Novel novel = getNovelData();
        generalAdministrativeManagement.insertNovel(novel);
    }

    private static Novel getNovelData(){
        String title = askString("Enter novel title: ");
        String author = askString("Enter author's name: ");
        String gender = askString("Enter category: ");
        int recommendedAge = askInt("Enter recommended age: ");
        int quantity = askInt("Enter quantity: ");
        int quantityLoaned = askInt("Enter quantity loaned: ");

        Novel novel = new Novel();
        novel.setId(UUID.randomUUID().toString());
        novel.setTitle(title);
        novel.setAuthor(generalAdministrativeManagement.getAuthorByName(author));
        novel.setRecommendedAge(recommendedAge);
        novel.setGender(gender);
        novel.setQuantity(quantity);
        novel.setQuantityLoaned(quantityLoaned);
        return novel;
    }

    private static void seeAllNovels(){
        generalAdministrativeManagement.getAllNovels().forEach(System.out::println);
    }

    private static void seeNovelByTitle(){
        String title = askString("Enter novel title: ");
        System.out.println(generalAdministrativeManagement.getNovelByTitle(title));
    }

    private static void updateNovel(){
        String id = askString("Enter novel id: ");
        Novel newNovel = getNovelData();
        Novel oldNovel = generalAdministrativeManagement.getNovelById(id);
        newNovel.setId(oldNovel.getId());
        generalAdministrativeManagement.updateNovel(newNovel);
    }

    private static void deleteNovel(){
        String title = askString("Enter novel title: ");
        generalAdministrativeManagement.deleteNovel(generalAdministrativeManagement.getNovelByTitle(title));
    }
}
