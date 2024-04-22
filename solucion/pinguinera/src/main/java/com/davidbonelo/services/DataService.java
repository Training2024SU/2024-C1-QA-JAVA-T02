package com.davidbonelo.services;

import com.davidbonelo.models.Book;
import com.davidbonelo.models.LibraryItem;
import com.davidbonelo.models.Novel;
import com.davidbonelo.persistance.*;
import com.davidbonelo.utils.JsonUtil;
import com.davidbonelo.utils.XmlUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataService {
    private final BookDAO bookDAO;
    private final NovelDAO novelDAO;
    private final Connection connection;
    private final VideoRecordingDAO videoRecordingDAO;
    private final SongDAO songDAO;
    private final EssayDAO essayDAO;

    public DataService(BookDAO bookDAO, NovelDAO novelDAO, VideoRecordingDAO videoRecordingDAO, SongDAO songDAO, EssayDAO essayDAO, Connection connection) {
        this.bookDAO = bookDAO;
        this.novelDAO = novelDAO;
        this.videoRecordingDAO = videoRecordingDAO;
        this.songDAO = songDAO;
        this.essayDAO = essayDAO;
        this.connection = connection;
    }

    public void exportInventory(String fileName) throws SQLException, IOException {
        List<LibraryItem> items = new ArrayList<>();
        items.addAll(bookDAO.getAllBooks());
        items.addAll(novelDAO.getAllNovels());
        DataHandler.saveToCsv(fileName, items);
    }
    public void exportJsonInventory(){
        String books = "books.json";
        String novels = "novels.json";
        String videos = "videos.json";
        String song = "songs.json";
        String essays = "essays.json";
        try{
            JsonUtil.writeJson(bookDAO.getAllBooks(), books);
            JsonUtil.writeJson(novelDAO.getAllNovels(), novels);
            JsonUtil.writeJson(videoRecordingDAO.getAllVideoRecordings(), videos);
            JsonUtil.writeJson(songDAO.getAllSongs(), song);
            JsonUtil.writeJson(essayDAO.getAllEssays(), essays);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Json data successfully exported");

    }
    public void importJsonInventory(){
        String books = "newbooks.json";
        List<Book> importedBooks = JsonUtil.readBookJsonFile(books);
        if (importedBooks != null){
            importedBooks.forEach(book -> {
                try{
                    bookDAO.createBook(book);
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            });
        } else {
            System.out.println("No books imported");
        }

    }
    public void exportXmlInventory(){
        String books = "books.xml";
        String novels = "novels.xml";
        String videos = "videos.xml";
        String song = "songs.xml";
        String essays = "essays.xml";
        try{
            XmlUtil.writeXml(bookDAO.getAllBooks(), books);
            XmlUtil.writeXml(novelDAO.getAllNovels(), novels);
            XmlUtil.writeXml(videoRecordingDAO.getAllVideoRecordings(), videos);
            XmlUtil.writeXml(songDAO.getAllSongs(), song);
            XmlUtil.writeXml(essayDAO.getAllEssays(), essays);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Xml data successfully exported");

    }
    public void importXmlInventory(){
        String books = "newbooks.xml";
        List<Book> importedBooks = XmlUtil.readXmlFile(books);
        if (importedBooks != null){
            importedBooks.forEach(book -> {
                try{
                    bookDAO.createBook(book);
                }catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            });
            System.out.println("Books imported");
        } else {
            System.out.println("No books imported");
        }

    }


    public void importInventory(String filename) throws SQLException, IOException {
        List<LibraryItem> items = DataHandler.readFromCsv(filename);
        try {
            connection.setAutoCommit(false);
            batchCreateItems(items);
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void batchCreateItems(List<LibraryItem> items) throws SQLException {
        String sqlB =
                "INSERT INTO Books ( id, title, author, copies, copies_borrowed, field, " +
                        "pages ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
        String sqlN =
                "INSERT INTO Novels ( id, title, author, copies, copies_borrowed, genre, " +
                        "recommended_age ) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
        try (PreparedStatement statementB = connection.prepareStatement(sqlB); PreparedStatement statementN = connection.prepareStatement(sqlN)) {
            for (LibraryItem item : items) {
                if (item instanceof Book book) {
                    bookDAO.prepareCreate(statementB, book);
                    statementB.addBatch();
                } else if (item instanceof Novel novel) {
                    novelDAO.prepareCreate(statementN, novel);
                    statementN.addBatch();
                }
            }
            statementB.executeBatch();
            statementN.executeBatch();
        }
    }
}
