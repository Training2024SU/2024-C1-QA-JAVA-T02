package com.davidbonelo.persistance;

import com.davidbonelo.models.Essay;
import com.davidbonelo.models.LibraryItem;
import com.mysql.cj.MysqlType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EssayDAO {
    private final Connection connection;

    public EssayDAO(Connection connection) {
        this.connection = connection;
    }

    protected static Essay buildEssayFromResult(ResultSet rs) throws SQLException {
        return new Essay(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                 rs.getInt("copies"), rs.getInt("copies_borrowed"), rs.getString("supervisor"),
                rs.getString("topic"));
    }

    public Essay getEssayById(int itemId) throws SQLException {
        String sql = "SELECT * FROM essays WHERE is_deleted = 0 AND id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return buildEssayFromResult(rs);
            }
            rs.close();
        }
        return null;
    }

    public List<Essay> getAllEssays() throws SQLException {
        List<Essay> essays = new ArrayList<>();
        String sql = "SELECT * FROM essays WHERE is_deleted = 0";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                essays.add(buildEssayFromResult(rs));
            }
        }
        return essays;
    }

    public void createEssay(Essay essay) throws SQLException {
        String sql = "INSERT INTO essays (id, title, author, supervisor, copies, copies_borrowed, topic) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareCreate(statement, essay);
            int rowInserted = statement.executeUpdate();
            if (rowInserted == 0) {
                throw new SQLException("Creating essay failed, no rows affected.");
            }
        }
    }

    public void updateEssay(Essay essay) throws SQLException {
        if (!containsId(essay))
            throw new IllegalArgumentException("Can't update an essay without an id");

        String sql = "UPDATE essays SET title = ?, author = ?, supervisor = ?, copies = ?, " +
                "copies_borrowed = ?, topic = ? WHERE is_deleted = 0 AND id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, essay.getTitle());
            statement.setString(2, essay.getAuthor());
            statement.setString(3, essay.getSupervisor());
            statement.setInt(4, essay.getCopies());
            statement.setInt(5, essay.getCopiesBorrowed());
            statement.setString(6, essay.getTopic());
            statement.setInt(7, essay.getId()); // WHERE
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update of essay with id " + essay.getId() + " failed.");
            }
        }
    }

    public void deleteEssay(int essayId) throws SQLException {
        String sql = "UPDATE essays SET is_deleted = 1 WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, essayId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Essay deletion failed, no rows affected");
            }
        }
    }

    public void prepareCreate(PreparedStatement statement, Essay essay) throws SQLException {
        if (containsId(essay)) {
            statement.setInt(1, essay.getId());
        } else {
            statement.setNull(1, MysqlType.NULL.getJdbcType());
        }
        statement.setString(2, essay.getTitle());
        statement.setString(3, essay.getAuthor());
        statement.setString(4, essay.getSupervisor());
        statement.setInt(5, essay.getCopies());
        statement.setInt(6, essay.getCopiesBorrowed());
        statement.setString(7, essay.getTopic());
    }

    private boolean containsId(LibraryItem item) {
        return item.getId() != 0;
    }
}
