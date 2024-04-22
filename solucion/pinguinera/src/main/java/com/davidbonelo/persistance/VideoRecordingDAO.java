package com.davidbonelo.persistance;

import com.davidbonelo.models.LibraryItem;
import com.davidbonelo.models.VideoRecording;
import com.mysql.cj.MysqlType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoRecordingDAO {
    private final Connection connection;

    public VideoRecordingDAO(Connection connection) {
        this.connection = connection;
    }

    protected static VideoRecording buildVideoRecordingFromResult(ResultSet rs) throws SQLException{
        return new VideoRecording(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                rs.getInt("copies"), rs.getInt("copies_borrowed"), rs.getTime("duration"),
                rs.getString("format"));
    }

    public VideoRecording getVideoRecordingById(int itemId) throws SQLException{
        String sql = "SELECT * FROM VideoRecordings WHERE is_deleted = 0 AND id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, itemId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return buildVideoRecordingFromResult(rs);
            }
            rs.close();
        }
        return null;
    }

    public List<VideoRecording> getAllVideoRecordings() throws SQLException {
        List<VideoRecording> videoRecordings = new ArrayList<>();
        String sql = "SELECT * FROM videoRecordings WHERE is_deleted = 0";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()){
            while (rs.next()) {
                videoRecordings.add(buildVideoRecordingFromResult(rs));
            }
        }
        return videoRecordings;
    }

    public void createVideoRecording(VideoRecording videoRecording) throws SQLException{
        String sql = "INSERT INTO VideoRecordings (id, title, author, duration, copies, copies_borrowed, format) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ? )";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            prepareCreate(statement, videoRecording);
            int rowInserted = statement.executeUpdate();
            if (rowInserted == 0){
                throw new SQLException("Creating video recording failed, no rows affected.");
            }
        }
    }

    public void updateVideoRecording(VideoRecording videoRecording) throws SQLException {
        if (!containsId(videoRecording))
            throw new IllegalArgumentException("Cant update a video recording without an id");

        String sql = "UPDATE VideoRecordings SET " +
                "title = ?, " +
                "author = ?, " +
                "duration = ?, " +
                "copies= ?, " +
                "copies_borrowed = ?, " +
                "format = ? " +
                "WHERE is_deleted = 0 AND id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, videoRecording.getTitle());
            statement.setString(2, videoRecording.getAuthor());
            statement.setTime(3, videoRecording.getDuration());
            statement.setInt(4, videoRecording.getCopies());
            statement.setInt(5, videoRecording.getCopiesBorrowed());
            statement.setString(6, videoRecording.getFormat());
            statement.setInt(7, videoRecording.getId()); // WHERE
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update of video recording with id " + videoRecording.getId() + " failed.");
            }
        }
    }

    public void deleteVideoRecording(int videoRecordingId) throws SQLException {
        String sql = "UPDATE VideoRecordings SET is_deleted = 1 WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, videoRecordingId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Video Recording deletion failed, no rows affected");
            }
        }
    }

    public void prepareCreate(PreparedStatement statement, VideoRecording videoRecording) throws SQLException{
        if(containsId(videoRecording)){
          statement.setInt(1, videoRecording.getId());
        } else {
            statement.setNull(1, MysqlType.NULL.getJdbcType());
        }
        statement.setString(2, videoRecording.getTitle());
        statement.setString(3, videoRecording.getAuthor());
        statement.setTime(4, videoRecording.getDuration());
        statement.setInt(5, videoRecording.getCopies());
        statement.setInt(6, videoRecording.getCopiesBorrowed());
        statement.setString(7, videoRecording.getFormat());
    }

    private boolean containsId(LibraryItem item) {
        return item.getId() != 0; // just learned that int cant be null and defaults to 0
    }
}
