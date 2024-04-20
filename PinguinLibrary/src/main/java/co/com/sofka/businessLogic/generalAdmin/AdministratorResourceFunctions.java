package co.com.sofka.businessLogic.generalAdmin;

import co.com.sofka.businessLogic.reader.ReaderManagement;
import co.com.sofka.enums.ResourceType;
import co.com.sofka.model.Resource;
import co.com.sofka.model.ResourceFactory;
import co.com.sofka.model.User;

import java.sql.SQLException;
import java.util.List;

import static co.com.sofka.menu.MenuConstant.exitingMessage;
import static co.com.sofka.menu.MenuConstant.incorrectOptionMessage;
import static co.com.sofka.menu.MenuMessage.administratorResourceMenuMessage;
import static co.com.sofka.utils.Utils.askInt;
import static co.com.sofka.utils.Utils.askResourceType;

public class AdministratorResourceFunctions {
    private static final ReaderManagement readerManagement = new ReaderManagement();
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
                case 7 -> exportResourceInfo();
                case 8 -> {
                    System.out.println(exitingMessage);
                    keepMenu = false;
                }
                default -> System.out.println(incorrectOptionMessage);
            }
        }
    }

    private static void listResources(ResourceType type) {
        List<Resource> resources = readerManagement.getAvailableResources(type);
        System.out.println("List of " + type + "S available: ");
        resources.forEach(System.out::println);
    }

    private static void createResource() {
        System.out.println("Adding a new resource: ");
        ResourceType type = askResourceType("Enter the new resource type: (Song, Video_recording, Essay)");
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

    private static void exportResourceInfo() {
    }
}
