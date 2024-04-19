package com.davidbonelo.persistance;

import com.davidbonelo.models.User;
import com.davidbonelo.models.UserExtraInfo;
import com.davidbonelo.models.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public static User buildUserFromResult(ResultSet rs) throws SQLException {
        UserRole role = UserRole.valueOf(rs.getString("role"));
        return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), role);
    }

    public User validateUserCredentials(String userEmail, String password) throws SQLException {
        String sql = "SELECT * FROM Users WHERE is_deleted = 0 AND email= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userEmail);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next() && rs.getString("password").equals(password)) {
                    return buildUserFromResult(rs);
                }
            }
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        // Avoid selecting passwords
        String sql = "SELECT id, name, email, role FROM Users WHERE is_deleted = 0";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                users.add(buildUserFromResult(rs));
            }
            return users;
        }
    }

    public List<User> getAllUsersExtraInfo() throws SQLException {
        String sql = "SELECT u.id, u.name, u.email, u.role, ui.biography, ui.birthday FROM Users u " +
                "LEFT JOIN user_info ui ON (u.id = ui.user_id) " +
                "WHERE is_deleted = 0;";
        ArrayList<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                User user = buildEntireUserFromResult(rs);
                users.add(user);
            }
            return users;
        }
    }

    public static User buildEntireUserFromResult(ResultSet rs) throws SQLException {
        UserRole role = UserRole.valueOf(rs.getString("role"));
        return new User(rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                role,
                rs.getString("biography"),
                rs.getDate("birthday"));
    }

    public void createUser(User user, String password) throws SQLException {
        String sql = "INSERT INTO Users (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, password);
            statement.setString(4, user.getRole().getValue());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                   throw new SQLException("Creating user failed, no rows affected");
            }
        }
    }

    public void insertExtraData(User user){
        String sql = "INSERT INTO user_info (user_id, biography, birthday) VALUES (?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, user.getId());
            statement.setString(2, user.getBiography());
            statement.setDate(3, user.getBirthday());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Creating user_info failed, no rows affected");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(User user) {
        if (missingId(user)) {
            throw new IllegalArgumentException("Cant update a User without an id");
        }
        String sql = "UPDATE Users SET name= ?, email= ?, role= ? WHERE is_deleted = 0 AND id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getRole().getValue());
            statement.setInt(4, user.getId()); // WHERE

            int updatedRows = statement.executeUpdate();
            if (updatedRows == 0) {
                throw new SQLException("Couldn't update user with id: " + user.getId());
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void updateUserInfo(User user){
        if (missingId(user)) {
            throw new IllegalArgumentException("Cant update a User without an id");
        }
        String sql = "UPDATE user_info SET biography = ?, birthday = ? WHERE  user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getBiography());
            statement.setDate(2, user.getBirthday());
            statement.setInt(3, user.getId()); // WHERE
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 0) {
                throw new SQLException("Couldn't update user with id: " + user.getId());
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updatePassword(User user, String password){
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, password);
            statement.setInt(2, user.getId()); // WHERE
            int updatedRows = statement.executeUpdate();
            if (updatedRows == 0) {
                throw new SQLException("Couldn't update user with id: " + user.getId());
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void softDeleteUser(int userId) throws SQLException {
        String sql = "UPDATE Users SET is_deleted=1 WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            int deletedRows = statement.executeUpdate();
            if (deletedRows == 0) {
                throw new SQLException("User with id " + userId + " not found, can't delete");
            }
        }
    }

    private boolean missingId(User user) {
        return user.getId() == 0;
    }
}
