package com.davidbonelo.services;

import com.davidbonelo.models.*;
import com.davidbonelo.persistance.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.davidbonelo.utils.Permissions.validPermission;

public class LibraryManager {

    private final BookDAO bookDAO;
    private final NovelDAO novelDAO;
    private final VideoRecordingDAO videoRecordingDAO;
    private final SongDAO songDAO;
    private final EssayDAO essayDAO;

    public LibraryManager(BookDAO bookDAO, NovelDAO novelDAO, VideoRecordingDAO videoRecordingDAO, SongDAO songDAO, EssayDAO essayDAO) {
        this.bookDAO = bookDAO;
        this.novelDAO = novelDAO;
        this.videoRecordingDAO = videoRecordingDAO;
        this.songDAO = songDAO;
        this.essayDAO = essayDAO;
    }

    public List<Book> getAllBooks(User user) {
        List<Book> books = bookDAO.getAllBooks();
        if (validPermission(user, UserRole.EMPLOYEE)) {
            return books;
        } else {
            // Filter unavailable items for users
            return books.stream().filter(b -> b.getAvailableCopies() >= 1).toList();
        }
    }

    public List<Novel> getAllNovels(User user) {
        try {
            List<Novel> novels = novelDAO.getAllNovels();
            if (validPermission(user, UserRole.EMPLOYEE)) {
                return novels;
            } else {
                return novels.stream().filter(n -> n.getAvailableCopies() >= 1).toList();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    public List<VideoRecording> getAllVideoRecordings(User user){
        try{
            List<VideoRecording> videoRecordings = videoRecordingDAO.getAllVideoRecordings();
            if (validPermission(user, UserRole.EMPLOYEE)){
                return videoRecordings;
            }else{
                return videoRecordings
                        .stream()
                        .filter( videoRecording -> videoRecording.getAvailableCopies() >= 1).toList();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }
    public List<Song> getAllSongs(User user){
        try{
            List<Song> songs = songDAO.getAllSongs();
            if (validPermission(user, UserRole.EMPLOYEE)){
                return songs;
            }else{
                return songs
                        .stream()
                        .filter( song -> song.getAvailableCopies() >= 1).toList();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    public List<Essay> getAllEssays(User user){
        try{
            List<Essay> essays = essayDAO.getAllEssays();
            if (validPermission(user, UserRole.EMPLOYEE)){
                return essays;
            }else{
                return essays
                        .stream()
                        .filter( song -> song.getAvailableCopies() >= 1).toList();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    public Set<String> getAuthorsList(List<? extends LibraryItem> items) {
        return items.stream().map(LibraryItem::getAuthor).collect(Collectors.toSet());
    }

    public List<? extends LibraryItem> filterItemsByAuthor(List<? extends LibraryItem> items,
                                                           String author) {
        return items.stream().filter(item -> item.getAuthor().equalsIgnoreCase(author)).toList();
    }

    public void registerItem(LibraryItem item) {
        try {
            if (item instanceof Book book) {
                bookDAO.createBook(book);
            } else if (item instanceof Novel novel) {
                novelDAO.createNovel(novel);
            } else if (item instanceof VideoRecording videoRecording) {
                videoRecordingDAO.createVideoRecording(videoRecording);
            } else if (item instanceof Song song) {
                songDAO.createSong(song);
            } else if (item instanceof Essay essay) {
                essayDAO.createEssay(essay);
            }
            System.out.println("Successful item registration " + item);
        } catch (SQLException e) {
            System.out.println("Couldn't register item, " + e.getLocalizedMessage());
        }
    }

    public void updateItem(LibraryItem item) {
        try {
            if (item.getClass().equals(Book.class)) {
                bookDAO.updateBook((Book) item);
            } else if (item.getClass().equals(Novel.class)) {
                novelDAO.updateNovel((Novel) item);
            } else if (item.getClass().equals(VideoRecording.class)) {
                videoRecordingDAO.updateVideoRecording((VideoRecording) item);
            } else if (item.getClass().equals(Song.class)) {
                songDAO.updateSong((Song) item);
            } else if (item.getClass().equals(Essay.class)) {
                essayDAO.updateEssay((Essay) item);
            }
            System.out.println("Successful item update " + item);
        } catch (SQLException e) {
            System.out.println("Couldn't update item, " + e.getLocalizedMessage());
        }
    }

    public void deleteBook(int bookId) {
        try {
            bookDAO.deleteBook(bookId);
        } catch (SQLException e) {
            System.out.println("Couldn't delete book, " + e.getLocalizedMessage());
        }
    }

    public void deleteNovel(int novelId) {
        try {
            novelDAO.deleteNovel(novelId);
        } catch (SQLException e) {
            System.out.println("Couldn't delete novel, " + e.getLocalizedMessage());
        }
    }
    public void deleteVideoRecording(int videoId) {
        try {
            videoRecordingDAO.deleteVideoRecording(videoId);
        } catch (SQLException e) {
            System.out.println("Couldn't delete video recording, " + e.getLocalizedMessage());
        }
    }

    public void deleteSong(int songId) {
        try {
            songDAO.deleteSong(songId);
        } catch (SQLException e) {
            System.out.println("Couldn't delete song, " + e.getLocalizedMessage());
        }
    }

    public void deleteEssay(int essayId) {
        try {
            essayDAO.deleteEssay(essayId);
        } catch (SQLException e) {
            System.out.println("Couldn't delete essay, " + e.getLocalizedMessage());
        }
    }
}
