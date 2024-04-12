package co.com.sofka.business.management;

import co.com.sofka.entities.User;

import java.util.List;

public interface IUserManagement {
    public void insertUser(User user);
    public List<User> getAllUsers();
    public User getUserById(String id);
    public void updateUser(User user);
    public void deleteUser(User user);
    public User getUserByEmail(String email);
}
