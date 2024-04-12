package co.com.sofka.utils.menu.administratorMenu;

import co.com.sofka.business.LibraryAdministratorManagement;
import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.Author;
import co.com.sofka.entities.Book;
import co.com.sofka.entities.Novel;
import co.com.sofka.entities.User;
import co.com.sofka.utils.menu.Menu;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class AdministratorMenu extends Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private final LibraryAdministratorManagement libraryAdministratorManagement;
    private List<User> users;
    private List<Book> books;
    private List<Novel> novels;
    private List<Author> authors;
    private final User user;

    public AdministratorMenu(User user, MySqlOperation mySqlOperation) {
        this.user = user;
        this.libraryAdministratorManagement = new LibraryAdministratorManagement(mySqlOperation);
        this.books = libraryAdministratorManagement.getAllBooks();
        this.users = libraryAdministratorManagement.getAllUsers();
        this.novels = libraryAdministratorManagement.getAllNovels();
        this.authors = libraryAdministratorManagement.getAllAuthor();
    }

    public  void displayMenu(){
        int option;
        do {
            menuOptions();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    userOptionsImplementation();
                    break;
                case 2:
                    bookOptionsImplementation();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option != 7);
    }

    public void userOptionsImplementation(){
        int userOption;
    }

    private void bookOptionsImplementation() {
        int bookOption;
        do {
            bookOptions();
            bookOption = scanner.nextInt();
            switch (bookOption){
                case 1:
                    String title = askForTitle();
                    String category = askForCategory();
                    Author author = askForAuthorId();
                    int quantity = askForQuantity();
                    int quantityLoaned = askForQuantityLoaned();
                    int quantityAvailable = askForQuantityAvailable();
                    Book book = new Book(UUID.randomUUID().toString(),quantity, quantityLoaned, quantityAvailable, title, author, category);
                    libraryAdministratorManagement.insertBook(book);
                    books.add(book);
                    break;
                case 2:
                    System.out.println(books);
                    break;
                case 3:

                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (bookOption != 6);
    }

    public static void menuOptions(){
        System.out.println("-------MENU-------");
        System.out.println("1. User options");
        System.out.println("2. Book Options");
        System.out.println("3. Novel Options");
        System.out.println("4. Author Options");
        System.out.println("5. Book loaned options");
        System.out.println("6. Novel loaned options");
        System.out.println("7. Exit.");
        System.out.print("Choose your option: ");
    }

    public static void bookOptions(){
        System.out.println("-------Book Options-------");
        System.out.println("1. Insert book");
        System.out.println("2. See all books");
        System.out.println("3. See book by id");
        System.out.println("4. Update book");
        System.out.println("5. Delete book");
        System.out.println("6. Exit.");
        System.out.print("Choose your option: ");
    }

    public int askForQuantity(){
        System.out.print("Enter the quantity: ");
        return scanner.nextInt();
    }

    public int askForQuantityLoaned(){
        System.out.print("Enter the quantity loaned: ");
        return scanner.nextInt();
    }

    public int askForQuantityAvailable(){
        System.out.print("Enter the quantity available: ");
        return scanner.nextInt();
    }

    public Author askForAuthorId(){
        System.out.println(authors);
        System.out.print("Enter author id: ");

        String id = scanner.next();
        Optional<Author> authorOptional = authors
                .stream()
                .filter(author -> author.getId().equals(id))
                .findFirst();
        return authorOptional.orElse(null);
    }

    public String askForCategory(){
        System.out.print("Enter category: ");
        return scanner.next();
    }

    public String askForTitle(){
        System.out.print("Enter title: ");
        return scanner.next();
    }
}
