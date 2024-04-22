package co.com.sofka.businessLogic.generalAdmin;

import co.com.sofka.enums.ResourceType;
import co.com.sofka.model.Resource;
import co.com.sofka.model.ResourceFactory;
import co.com.sofka.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.administratorResourceMenuMessage;
import static co.com.sofka.utils.Utils.askInt;
import static co.com.sofka.utils.Utils.askResourceType;
import static co.com.sofka.utils.Utils.askString;

public class AdministratorResourceFunctions {
    private static final GeneralAdministrativeManagement administrativeManagement =
            new GeneralAdministrativeManagement();

    public static void administratorResourceMenuOptions(User user) {
        boolean keepMenu = true;
        while (keepMenu) {
            administratorResourceMenuMessage(user);
            int option = askInt("Enter your option: ");
            switch (option) {
                case 1 -> listResources(ResourceType.SONG);
                case 2 -> listResources(ResourceType.VIDEO_RECORDING);
                case 3 -> listResources(ResourceType.ESSAY);
                case 4 -> createResource();
                case 5 -> updateResource();
                case 6 -> deleteResource();
                case 7 -> exportResourcesInfo();
                case 8 -> importResourcesInfo();
                case 9 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void listResources(ResourceType type) {
        List<Resource> resources = administrativeManagement.getAllResources(type);
        System.out.println("List of " + type + "S available: ");
        resources.forEach(System.out::println);
    }

    private static void createResource() {
        System.out.println("Adding a new resource: ");
        ResourceType type = askResourceType("Enter the new resource type: (Song, Video_recording,"
                + " Essay)");
        Resource resource = ResourceFactory.getResourceFromInput(type);
        try {
            administrativeManagement.insertResource(resource);
        } catch (SQLException e) {
            System.out.println("Couldn't create resource, " + e.getLocalizedMessage());
        }
    }

    private static void updateResource() {
        System.out.println("Updating a resource: ");
        int id = askInt("Enter the id of the resource you want to update: ");
        Resource resource = administrativeManagement.getResourceDetails(id);
        if (resource == null) {
            System.out.println("Resource with id: " + id + " not found");
            return;
        }
        Resource newResource = ResourceFactory.getResourceFromInput(resource.getType());
        newResource.setId(resource.getId());
        administrativeManagement.updateResource(resource);
    }

    private static void deleteResource() {
        System.out.println("Deleting a resource: ");
        int id = askInt("Enter the id of the resource you want to delete: ");
        administrativeManagement.deleteResource(id);
    }

    private static void exportResourcesInfo() {
        System.out.println("Select the format you want for the exported file: ");
        int formatOption = askInt("1. CSV | 2. JSON | 3. XML |");
        String fileName = askString("Enter the name for the exported file: ");
        try {
            switch (formatOption) {
//                case 1 -> administrativeManagement.exportResourcesToCSV(fileName);
                case 2 -> administrativeManagement.exportResourcesToFile(fileName, ".json");
                case 3 -> administrativeManagement.exportResourcesToFile(fileName, ".xml");
                default -> System.out.println("Unknown option");
            }
            System.out.println("Successful data export\n");
        } catch (Exception e) {
            System.out.println("Couldn't export resources info, " + e.getLocalizedMessage());
        }
    }

    private static void importResourcesInfo() {
        System.out.println("Select the type of resource you want to import: ");
        ResourceType resourceType = askResourceType("Song | Video_recording | Essay");
        System.out.println("Select the format of the file you want to import: ");
        int formatOption = askInt("1. CSV | 2. JSON | 3. XML |");
        String fileName = askString("Enter the path or name of the file to import: ");
        try {
            switch (formatOption) {
//                case 1 -> administrativeManagement.exportResourcesToCSV(fileName);
                case 2 -> administrativeManagement.importFromFile(fileName, ".json", resourceType);
                case 3 -> administrativeManagement.importFromFile(fileName, ".xml", resourceType);
                default -> System.out.println("Unknown option");
            }
            System.out.println("Successful data import\n");
        } catch (SQLException | IOException e) {
            System.out.println("Couldn't import resources info, " + e.getLocalizedMessage());
        }
    }
}
