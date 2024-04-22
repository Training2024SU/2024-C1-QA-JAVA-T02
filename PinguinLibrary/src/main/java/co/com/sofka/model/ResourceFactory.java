package co.com.sofka.model;

import co.com.sofka.DAO.AuthorDAO;
import co.com.sofka.DAO.Impl.AuthorDAOImpl;
import co.com.sofka.enums.ResourceType;

import static co.com.sofka.utils.Utils.askInt;
import static co.com.sofka.utils.Utils.askString;

public class ResourceFactory {
    private static final AuthorDAO authorDao = new AuthorDAOImpl();

    private ResourceFactory() {
        throw new IllegalStateException("Utility Class");
    }

    public static Resource getResourceFromInput(ResourceType type) {
        String typeString = type.toString().toLowerCase();
        String title = askString("Enter the title of the " + typeString);
        int quantity = askInt("Enter the quantity of the " + typeString + " " + title);
        int quantityLoaned = askInt("Enter the quantity loaned of the " + typeString + " " + title);
        String authorName = askString("Enter the author of the " + typeString + " " + title);
        Author author = authorDao.getAuthorByName(authorName);

        switch (type) {
            case SONG -> {
                int duration = askInt("Enter the duration of the " + typeString + " " + title);
                return new Song(type, title, quantity, quantityLoaned, author, duration);
            }
            case VIDEO_RECORDING -> {
                String res = askString("Enter the resolution of the " + typeString + " " + title);
                return new VideoRecording(type, title, quantity, quantityLoaned, author, res);
            }
            case ESSAY -> {
                String level = askString("Enter the academic level of the " + typeString + " " + title);
                return new Essay(type, title, quantity, quantityLoaned, author,level);
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
