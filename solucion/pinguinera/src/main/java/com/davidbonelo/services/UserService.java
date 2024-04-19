package com.davidbonelo.services;

import com.davidbonelo.models.User;
import com.davidbonelo.persistance.UserDAO;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;
    private User loggedUser;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String email, String password) {
        try {
            loggedUser = userDAO.validateUserCredentials(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loggedUser;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void logOut() {
        loggedUser = null;
    }

    public void register(User user, String password) throws SQLException {
        userDAO.createUser(user, password);
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<User> getAllUsersExtraInfo() {
        try {
            return userDAO.getAllUsersExtraInfo();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Collections.emptyList();
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }
    public void updateUserInfo(User user){
        userDAO.updateUserInfo(user);
    }
    public void updateUserPassword(User user, String password){
        userDAO.updatePassword(user, password);
    }
    public void insertUserInfo(User user){
        userDAO.insertExtraData(user);
    }

    public void deleteUser(int userId) throws SQLException {
        userDAO.softDeleteUser(userId);
    }

}
