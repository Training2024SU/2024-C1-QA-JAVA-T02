package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.mongodb.MongoDBConnector;
import com.davidbonelo.persistance.UserDAOMongo;
import com.davidbonelo.services.DataService;
import com.davidbonelo.services.UserService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import static com.davidbonelo.utils.Permissions.validMenuAccess;
import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class AdminMenu {
    private final UserService userService;
    private final User user;
    private final ResourceBundle messages = ResourceBundle.getBundle("messages");
    private final DataService dataService;


    public AdminMenu(UserService userService, DataService dataService, User user) {
        this.userService = userService;
        this.dataService = dataService;
        this.user = user;
    }

    public void menu() {
        while (true) {

            if (!validMenuAccess(user, UserRole.ADMINISTRATOR)) {
                return;
            }

            int menuChoice = showMenu();
            switch (menuChoice) {
                case 1 -> listUsers();
                case 2 -> createEmployeeUser();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> exportInventoryData();
                case 6 -> importInventoryData();
                case 7 -> exportInventoryJson();
                case 8 -> importInventoryJson();
                case 9 -> exportInventoryXml();
                case 10 -> importInventoryXml();
                case 11 -> selectUserFromMongo();

                case 0 -> {
                    return;
                }
                default -> System.out.println(messages.getString("unknownOption"));
            }
        }
    }

    private void selectUserFromMongo() {
        MongoDBConnector mongoDBConnector = new MongoDBConnector();
        UserDAOMongo userDAOMongo = new UserDAOMongo(mongoDBConnector.getMongoDatabase());
        MongoCollection<Document> usersCollection = userDAOMongo.getAllUsersFromMongoDB();

        // Obtenemos un cursor para recorrer los documentos
        MongoCursor<Document> cursor = usersCollection.find().iterator();

        // Iteramos sobre los documentos
        while (cursor.hasNext()) {
            Document document = cursor.next();
            System.out.println(document.toJson());
        }

        // Cerramos el cursor
        cursor.close();
    }

    private User buildUserFromDocument(Document document) {
        int id = document.getInteger("id");
        String name = document.getString("name");
        String email = document.getString("email");
        String roleString = document.getString("role");
        UserRole role = UserRole.valueOf(roleString);
        String biography = document.getString("biography");
        Date birthday = Date.valueOf(String.valueOf(document.getDate("birthday")));
        return new User(id, name, email, role, biography, birthday);
    }

    private void exportInventoryXml() {
        dataService.exportXmlInventory();
    }
    private void importInventoryXml() {
        dataService.importXmlInventory();
    }

    private void importInventoryJson() {
        dataService.importJsonInventory();
    }

    private void exportInventoryJson() {
        dataService.exportJsonInventory();
    }

    private int showMenu() {
        String adminChoices = messages.getString("admin.choices.admin");
        MenuChoices menu = new MenuChoices("Admin", "", "", "", adminChoices, "");
        return menu.showMenu(user);
    }

    private void listUsers() {
        System.out.println(messages.getString("admin.info.usersList"));
        userService.getAllUsers().forEach(System.out::println);
    }

    private void createEmployeeUser() {
        System.out.println(messages.getString("admin.req.createId"));
        User newUser = User.createUserFromInput();
        newUser.setRole(UserRole.EMPLOYEE);
        String password = askText(messages.getString("login.req.password"));
        try {
            userService.register(newUser, password);
            System.out.println(messages.getString("admin.res.registerOk"));
        } catch (SQLException e) {
            System.out.println(MessageFormat.format(messages.getString("admin.res.registerBad"),
                    e.getLocalizedMessage()));
        }
    }

    private void updateUser() {
        int userId = askNumber(messages.getString("admin.req.updateId"));
        User newUser = User.createUserFromInput();
        newUser.setRole(askRole());
        newUser.setId(userId);
        userService.updateUser(newUser);
        System.out.println(messages.getString("admin.res.updateOk"));

    }

    private UserRole askRole() {
        UserRole role = null;
        while (role == null) {
            int roleChoice = askNumber(messages.getString("admin.req.role"));
            switch (roleChoice) {
                case 1 -> role = UserRole.READER;
                case 2 -> role = UserRole.EMPLOYEE;
                case 3 -> role = UserRole.ADMINISTRATOR;
                default -> System.out.println(messages.getString("admin.res.badRole"));
            }
        }
        return role;
    }

    private void deleteUser() {
        int userId = askNumber(messages.getString("admin.req.deleteId"));
        try {
            userService.deleteUser(userId);
            System.out.println(messages.getString("admin.res.deleteOk"));
        } catch (SQLException e) {
            System.out.println(MessageFormat.format(messages.getString("admin.res.deleteBad"),
                    e.getLocalizedMessage()));
        }
    }

    private void exportInventoryData() {
        System.out.println(messages.getString("admin.info.exporting"));
        String fileName = askText(messages.getString("admin.req.exportName")) + ".csv";
        try {
            dataService.exportInventory(fileName);
            System.out.println(messages.getString("admin.res.exportOk") + fileName + ".csv");
        } catch (SQLException | IOException e) {
            System.out.println(messages.getString("main.res.exportBad") + e.getMessage());
        }
    }

    private void importInventoryData() {
        System.out.println(messages.getString("admin.info.importing"));
        String fileName = askText(messages.getString("admin.req.importName")) + ".csv";
        try {
            dataService.importInventory(fileName);
            System.out.println(messages.getString("admin.res.importOk") + fileName + ".csv");
        } catch (Exception e) {
            System.out.println(messages.getString("admin.res.importBad") + e.getMessage());
        }
    }
}
