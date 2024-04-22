package com.davidbonelo.persistance;

import com.davidbonelo.models.LibraryItem;
import com.davidbonelo.models.Song;
import com.mysql.cj.MysqlType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {
    private final Connection connection;

    public SongDAO(Connection connection) {
        this.connection = connection;
    }

    protected static Song buildSongFromResult(ResultSet rs) throws SQLException {
        return new Song(rs.getInt("id"), rs.getString("title"), rs.getString("author"),
                 rs.getInt("copies"), rs.getInt("copies_borrowed"), rs.getString("recordLabel"),
                rs.getTime("duration"));
    }

    public Song getSongById(int itemId) throws SQLException{
        String sql = "SELECT * FROM songs WHERE is_deleted = 0 AND id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, itemId);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return buildSongFromResult(rs);
            }
            rs.close();
        }
        return null;
    }

    public List<Song> getAllSongs() throws SQLException {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM songs WHERE is_deleted = 0";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()){
            while (rs.next()) {
                songs.add(buildSongFromResult(rs));
            }
        }
        return songs;
    }

    public void createSong(Song song) throws SQLException{
        String sql = "INSERT INTO songs (id, title, author, recordLabel, duration, copies, copies_borrowed) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            prepareCreate(statement, song);
            int rowInserted = statement.executeUpdate();
            if (rowInserted == 0){
                throw new SQLException("Creating song failed, no rows affected.");
            }
        }
    }

    public void updateSong(Song song) throws SQLException {
        if (!containsId(song))
            throw new IllegalArgumentException("Can't update a song without an id");

        String sql = "UPDATE songs SET title = ?, author = ?, recordLabel = ?, duration = ?, copies = ?, " +
                "copies_borrowed = ? WHERE is_deleted = 0 AND id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, song.getTitle());
            statement.setString(2, song.getAuthor());
            statement.setString(3, song.getRecordLabel());
            statement.setTime(4, song.getDuration());
            statement.setInt(5, song.getCopies());
            statement.setInt(6, song.getCopiesBorrowed());
            statement.setInt(7, song.getId()); // WHERE
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("Update of song with id " + song.getId() + " failed.");
            }
        }
    }

    public void deleteSong(int songId) throws SQLException {
        String sql = "UPDATE songs SET is_deleted = 1 WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, songId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new SQLException("Song deletion failed, no rows affected");
            }
        }
    }

    public void prepareCreate(PreparedStatement statement, Song song) throws SQLException{
        if(containsId(song)){
            statement.setInt(1, song.getId());
        } else {
            statement.setNull(1, MysqlType.NULL.getJdbcType());
        }
        statement.setString(2, song.getTitle());
        statement.setString(3, song.getAuthor());
        statement.setString(4, song.getRecordLabel());
        statement.setTime(5, song.getDuration());
        statement.setInt(6, song.getCopies());
        statement.setInt(7, song.getCopiesBorrowed());
    }

    private boolean containsId(LibraryItem item) {
        return item.getId() != 0;
    }
}
