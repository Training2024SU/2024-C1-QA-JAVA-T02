package com.davidbonelo.ui;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserRole;
import com.davidbonelo.models.VideoRecording;
import com.davidbonelo.services.BorrowingsService;
import com.davidbonelo.services.LibraryManager;

import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.davidbonelo.utils.Permissions.validMenuAccess;
import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class VideoRecordingsMenu {
    private final LibraryManager libraryManager;
    private final User user;
    private final BorrowingsService borrowingsService;
    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public VideoRecordingsMenu(LibraryManager libraryManager,
                               BorrowingsService borrowingsService,
                               User user) {
        this.libraryManager = libraryManager;
        this.user = user;
        this.borrowingsService = borrowingsService;
    }

    public void menu(){
        while (true){
            int menuChoice = showMenu();
            switch (menuChoice) {
                case 1 -> listVideos();
                case 2 -> listAuthors();
                case 3 -> searchByAuthor();
                case 4 -> addToBorrowing();
                case 5 -> registerVideo();
                case 6 -> updateVideo();
                case 7 -> deleteVideo();
                case 0 -> {
                    return;
                }
                default -> System.out.println(messages.getString("unknownOption"));
            }
        }
    }

    private void deleteVideo() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int videoId = askNumber(messages.getString("videos.req.deleteId"));
        libraryManager.deleteVideoRecording(videoId);
    }

    private void updateVideo() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        int videoId = askNumber(messages.getString("videos.req.updateId"));
        System.out.println(messages.getString("videos.req.videoData"));
        VideoRecording videoRecording = VideoRecording.createVideoFromInput();
        videoRecording.setId(videoId);
        libraryManager.updateItem(videoRecording);
    }

    private void registerVideo() {
        if (!validMenuAccess(user, UserRole.EMPLOYEE)) {
            return;
        }
        libraryManager.registerItem(VideoRecording.createVideoFromInput());
    }

    private void addToBorrowing() {
        if (!validMenuAccess(user, UserRole.READER)) {
            return;
        }
        int videoId = askNumber(messages.getString("videos.req.toBorrowId"));
        try {
            borrowingsService.addBorrowingVideo(videoId);
            System.out.println(messages.getString("videos.res.borrowingOk"));
        } catch (SQLException e) {
            System.out.println(messages.getString("videos.res.borrowingBad") + e.getLocalizedMessage());
        }
    }

    private void searchByAuthor() {
        String author = askText(messages.getString("item.req.author"));
        libraryManager.filterItemsByAuthor(libraryManager.getAllVideoRecordings(user), author).forEach(System.out::println);
    }

    private void listAuthors() {
        libraryManager.getAuthorsList(libraryManager.getAllVideoRecordings(user)).forEach(System.out::println);
    }

    private void listVideos() {
        libraryManager.getAllVideoRecordings(user).forEach(System.out::println);
    }


    private int showMenu() {
        String visitorChoices = messages.getString("videos.choices.visitor");
        String readerChoices = messages.getString("videos.choices.reader");
        String employeeChoices = messages.getString("videos.choices.employee");
        MenuChoices menu = new MenuChoices("Video Recordings", visitorChoices, readerChoices,
                employeeChoices, "", "");
        return menu.showMenu(user);
    }

}
