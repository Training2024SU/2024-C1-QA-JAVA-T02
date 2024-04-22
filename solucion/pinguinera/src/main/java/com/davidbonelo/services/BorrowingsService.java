package com.davidbonelo.services;

import com.davidbonelo.models.*;
import com.davidbonelo.persistance.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import static com.davidbonelo.utils.Permissions.validPermission;

public class BorrowingsService {
    private final BookDAO bookDAO;
    private final NovelDAO novelDAO;
    private final VideoRecordingDAO videoRecordingDAO;
    private final SongDAO songDAO;
    private final EssayDAO essayDAO;
    private final BorrowingDAO borrowingDAO;
    private final Set<LibraryItem> itemsToBorrow;
    private final Connection connection;
    private final ResourceBundle messages = ResourceBundle.getBundle("messages");

    public BorrowingsService(BookDAO bookDAO,
                             NovelDAO novelDAO,
                             VideoRecordingDAO videoRecordingDAO,
                             SongDAO songDAO,
                             EssayDAO essayDAO,
                             BorrowingDAO borrowingDAO,
                             Connection connection) {
        this.bookDAO = bookDAO;
        this.novelDAO = novelDAO;
        this.videoRecordingDAO = videoRecordingDAO;
        this.songDAO = songDAO;
        this.essayDAO = essayDAO;
        this.borrowingDAO = borrowingDAO;
        this.connection = connection;
        this.itemsToBorrow = new HashSet<>();
    }

    public Set<LibraryItem> getItemsToBorrow() {
        return this.itemsToBorrow;
    }

    public List<Borrowing> getBorrowingsByEmail(User employee, String email) {
        return getAllBorrowings(employee).stream().filter(b -> b.getBorrower().getEmail().equals(email)).toList();
    }

    public Borrowing getBorrowingDetails(User user, int borrowingId) throws SQLException {
        Borrowing borrowing = borrowingDAO.getBorrowingWithItems(borrowingId);
        if (validPermission(user, UserRole.EMPLOYEE)) {
            // Allow if is employee
            return borrowing;
        }
        if (user.getId() == borrowing.getBorrower().getId()) {
            // Allow if is the owner
            return borrowing;
        }
        return null;
    }

    public void addBorrowingBook(int itemId) throws SQLException {
        Book book = bookDAO.getBookById(itemId);
        if (book == null) {
            String errMsg =
                    MessageFormat.format(messages.getString("borrowings.res.bookNotFound"), itemId);
            throw new SQLException(errMsg);
        }
        this.itemsToBorrow.add(book);
    }

    public void addBorrowingNovel(int itemId) throws SQLException {
        Novel novel = novelDAO.getNovelById(itemId);
        if (novel == null) {
            String errMsg = MessageFormat.format(messages.getString("borrowings.res" +
                    ".novelNotFound"), itemId);
            throw new SQLException(errMsg);
        }
        this.itemsToBorrow.add(novel);
    }

    public void addBorrowingVideo(int itemId) throws SQLException {
        VideoRecording videoRecording = videoRecordingDAO.getVideoRecordingById(itemId);
        if (videoRecording == null) {
            String errMsg = MessageFormat.format(messages.getString("borrowings.res" +
                    ".videoRecordingNotFound"), itemId);
            throw new SQLException(errMsg);
        }
        this.itemsToBorrow.add(videoRecording);
    }
    public void addBorrowingSong(int itemId) throws SQLException {
        Song song = songDAO.getSongById(itemId);
        if (song == null) {
            String errMsg = MessageFormat.format(messages.getString("borrowings.res" +
                    ".songNotFound"), itemId);
            throw new SQLException(errMsg);
        }
        this.itemsToBorrow.add(song);
    }
    public void addBorrowingEssay(int itemId) throws SQLException {
        Essay essay = essayDAO.getEssayById(itemId);
        if (essay == null) {
            String errMsg = MessageFormat.format(messages.getString("borrowings.res" +
                    ".essayNotFound"), itemId);
            throw new SQLException(errMsg);
        }
        this.itemsToBorrow.add(essay);
    }

    public List<Borrowing> getAllBorrowings(User user) {
        try {
            List<Borrowing> borrowings = borrowingDAO.getAllBorrowings();
            // Show all borrowings only if authorized
            if (validPermission(user, UserRole.EMPLOYEE)) {
                return borrowings;
            } else {
                // Filter only the ones where user is owner
                return borrowings.stream().filter(b -> b.getBorrower().getId() == user.getId()).toList();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    public void createBorrowing(Borrowing borrowing) throws SQLException {
        try {
            connection.setAutoCommit(false); // start transaction
            verifyItemsAvailability(itemsToBorrow);
            borrowing.setBorrowedItems(itemsToBorrow.stream().toList());

            borrowingDAO.createBorrowing(borrowing);
            updateItemsBorrowedCopies(borrowing.getBorrowedItems(), 1);

            connection.commit(); // commit transaction
            itemsToBorrow.clear(); // Clean this class set

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void verifyItemsAvailability(Set<LibraryItem> borrowingItems) {
        for (LibraryItem item : borrowingItems) {
            if (item.getAvailableCopies() < 1) {
                throw new IllegalArgumentException("Item: " + item.getTitle() + messages.getString("borrowings.res.noCopies"));
            }
        }
    }

    /**
     * @param factor the amount of copies to sum (or rest if negative)
     */
    private void updateItemsBorrowedCopies(List<LibraryItem> items, int factor) throws SQLException {
        for (LibraryItem li : items) {
            li.setCopiesBorrowed(li.getCopiesBorrowed() + factor);
            if (li instanceof Book book) {
                bookDAO.updateBook(book);
            } else if (li instanceof Novel novel) {
                novelDAO.updateNovel(novel);
            } else if (li instanceof VideoRecording videoRecording) {
                videoRecordingDAO.updateVideoRecording(videoRecording);
            } else if (li instanceof Song song) {
                songDAO.updateSong(song);
            } else if (li instanceof Essay essay) {
                essayDAO.updateEssay(essay);
            }
        }
    }

    public void confirmBorrowing(User user, int borrowingId) throws SQLException {
        Borrowing borrowing = getBorrowingDetails(user, borrowingId);
        borrowing.setStatusBorrowed();
        borrowingDAO.updateBorrowingStatus(borrowing);
    }

    public void deleteBorrowing(int borrowingId) {
        try {
            borrowingDAO.deleteBorrowing(borrowingId);
            System.out.println(messages.getString("borrowings.res.deleteOk"));
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void finalizeBorrowing(User user, int borrowingId) throws SQLException {
        try {
            connection.setAutoCommit(false);

            Borrowing borrowing = getBorrowingDetails(user, borrowingId);
            borrowing.setStatusFinalized();
            borrowingDAO.updateBorrowingStatus(borrowing);
            updateItemsBorrowedCopies(borrowing.getBorrowedItems(), -1);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
