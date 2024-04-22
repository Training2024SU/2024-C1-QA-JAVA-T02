package com.davidbonelo.persistance;

import com.davidbonelo.models.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.davidbonelo.persistance.BookDAO.buildBookFromResult;
import static com.davidbonelo.persistance.NovelDAO.buildNovelFromResult;

public class BorrowingDAO {
    Connection connection;

    public BorrowingDAO(Connection connection) {
        this.connection = connection;
    }

    private static Borrowing buildBorrowingFromResult(ResultSet rs) throws SQLException {
        UserRole role = UserRole.valueOf(rs.getString("role"));
        User user = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("email"),
                role);
        BorrowingStatus status = BorrowingStatus.valueOf(rs.getString("status"));
        return new Borrowing(rs.getInt("id"), rs.getDate("returned_date").toLocalDate(),
                rs.getDate("requested_date").toLocalDate(), user, status);
    }

    public Borrowing getBorrowingWithItems(int borrowingId) throws SQLException {
        String sql = "SELECT b.*, u.name, u.email, u.role FROM (SELECT * FROM Borrowings b WHERE " +
                "b.is_deleted = 0 AND b.id = ? ) AS b LEFT JOIN Users u ON b.user_id = u.id;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, borrowingId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Borrowing borrowing = buildBorrowingFromResult(rs);
                borrowing.setBorrowedItems(getAllItemsForABorrowing(borrowing.getId()));
                rs.close();
                return borrowing;
            } else {
                rs.close();
                throw new SQLException("Borrowing with id " + borrowingId + " Not found");
            }
        }
    }

    public List<Borrowing> getAllBorrowings() throws SQLException {
        List<Borrowing> borrowings = new ArrayList<>();
        String sql = "SELECT b.*, u.name, u.email, u.role FROM Borrowings b LEFT JOIN Users u ON " +
                "b.user_id = u.id WHERE b.is_deleted = 0";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs =
                statement.executeQuery()) {
            while (rs.next()) {
                borrowings.add(buildBorrowingFromResult(rs));
            }
        }
        return borrowings;
    }

    public List<LibraryItem> getAllItemsForABorrowing(int borrowingId) throws SQLException {
        List<LibraryItem> items = new ArrayList<>();
        // Note to self: Don't try to optimize by joining this 2 queries because then it will not
        // be possible to differentiate Books and Novels

        // SubQuery a borrowing and join its books
        String sqlB = "SELECT b.* FROM (SELECT * FROM borrowings_books bb WHERE bb.borrowing_id = ?) AS bb LEFT JOIN Books b ON bb.book_id = b.id";
        // SubQuery a borrowing and join its novels
        String sqlN = "SELECT n.* FROM (SELECT * FROM borrowings_novels bn WHERE bn.borrowing_id "
                + "= ?) AS bn LEFT JOIN Novels n ON bn.novel_id = n.id";

        // EXTENSION USING THE NEW ENTITIES
        // borrowing and video recordings
        String sqlV = "SELECT v.* FROM borrowings_videos bv " +
                "INNER JOIN VideoRecordings v ON (bv.video_recording_id = v.id) " +
                "WHERE bv.borrowing_id = ?;";

        // Borrowing and songs
        String sqlS = "SELECT s.* FROM borrowings_songs bs " +
                "INNER JOIN songs s ON (bs.song_id = s.id) " +
                "WHERE bs.borrowing_id = ?;";

        // Borrowings and essays
        String sqlE = "SELECT e.* FROM borrowings_essays be " +
                "INNER JOIN essays e ON (be.essay_id = e.id) " +
                "WHERE be.borrowing_id = ?;";

        try (PreparedStatement statementB = connection.prepareStatement(sqlB);
             PreparedStatement statementN = connection.prepareStatement(sqlN);
             PreparedStatement statementV = connection.prepareStatement(sqlV);
             PreparedStatement statementS = connection.prepareStatement(sqlS);
             PreparedStatement statementE = connection.prepareStatement(sqlE)) {

            statementB.setInt(1, borrowingId);
            statementN.setInt(1, borrowingId);
            statementV.setInt(1, borrowingId);
            statementS.setInt(1, borrowingId);
            statementE.setInt(1, borrowingId);

            try (ResultSet rsB = statementB.executeQuery();
                 ResultSet rsN = statementN.executeQuery()) {

                while (rsB.next()) {
                    items.add(buildBookFromResult(rsB));
                }
                while (rsN.next()) {
                    items.add(buildNovelFromResult(rsN));
                }
            }
        }
        return items;
    }

    public void createBorrowing(Borrowing borrowing) throws SQLException {
        String sql = "INSERT INTO Borrowings ( user_id , requested_date , returned_date , status "
                + ") VALUES ( ?, ?, ?, ? )";
        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, borrowing.getBorrower().getId());
            statement.setDate(2, Date.valueOf(borrowing.getRequestedDate()));
            statement.setDate(3, Date.valueOf(borrowing.getReturnDate()));
            statement.setString(4, borrowing.getStatus().getValue());

            int insertedRows = statement.executeUpdate();
            if (insertedRows == 0) {
                throw new SQLException("Borrowing creation failed, no rows affected");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    borrowing.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Borrowing creation failed, no ID obtained");
                }
            }
            saveBorrowedItems(borrowing);
        }
    }

    /**
     * Fills the intermediate tables for the many-to-many relations
     */
    private void saveBorrowedItems(Borrowing borrowing) throws SQLException {
        String sqlB = "INSERT INTO borrowings_books (borrowing_id, book_id) VALUES (?, ?)";
        String sqlN = "INSERT INTO borrowings_novels (borrowing_id, novel_id) VALUES (?, ?)";
        String sqlV = "INSERT INTO borrowings_videos (borrowing_id, video_recording_id) VALUES (?, ?)";
        String sqlS = "INSERT INTO borrowings_songs (borrowing_id, song_id) VALUES (?, ?)";
        String sqlE = "INSERT INTO borrowings_essays (borrowing_id, essay_id) VALUES (?, ?)";
        try (PreparedStatement statementB = connection.prepareStatement(sqlB);
             PreparedStatement statementN = connection.prepareStatement(sqlN);
             PreparedStatement statementV = connection.prepareStatement(sqlV);
             PreparedStatement statementS = connection.prepareStatement(sqlS);
             PreparedStatement statementE = connection.prepareStatement(sqlE)) {
            for (LibraryItem li : borrowing.getBorrowedItems()) {
                if (li instanceof Book) {
                    statementB.setInt(1, borrowing.getId());
                    statementB.setInt(2, li.getId());
                    statementB.addBatch();
                }
                if (li instanceof Novel) {
                    statementN.setInt(1, borrowing.getId());
                    statementN.setInt(2, li.getId());
                    statementN.addBatch();
                }
                if (li instanceof VideoRecording){
                    statementV.setInt(1, borrowing.getId());
                    statementV.setInt(2, li.getId());
                    statementV.addBatch();
                }
                if (li instanceof Song){
                    statementS.setInt(1, borrowing.getId());
                    statementS.setInt(2, li.getId());
                    statementS.addBatch();

                }
                if (li instanceof Essay){
                    statementE.setInt(1, borrowing.getId());
                    statementE.setInt(2, li.getId());
                    statementE.addBatch();
                }

            }
            statementB.executeBatch();
            statementN.executeBatch();
            statementV.executeBatch();
            statementS.executeBatch();
            statementE.executeBatch();
        }
    }

    public void updateBorrowingStatus(Borrowing borrowing) throws SQLException {
        if (missingId(borrowing)) {
            throw new IllegalArgumentException("Can't update a borrowing without a id");
        }
        String sql = "UPDATE Borrowings SET status = ? WHERE is_deleted = 0 AND id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, borrowing.getStatus().getValue());
            statement.setInt(2, borrowing.getId()); // WHERE

            int updatedRows = statement.executeUpdate();
            if (updatedRows == 0) {
                throw new SQLException("Couldn't update borrowing with id " + borrowing.getId());
            }
        }
    }

    public void deleteBorrowing(int borrowingId) throws SQLException {
        String sql = "UPDATE Borrowing SET is_deleted = 1 WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, borrowingId); // WHERE

            int deletedRows = statement.executeUpdate();
            if (deletedRows == 0) {
                throw new SQLException("Couldn't delete borrowing with id " + borrowingId);
            }
        }
    }

    private boolean missingId(Borrowing borrowing) {
        return borrowing.getId() == 0;
    }
}
