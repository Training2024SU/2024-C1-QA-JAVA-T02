package co.com.sofka.DAO;

import co.com.sofka.entities.Novel;
import co.com.sofka.entities.User;

import java.util.List;

public interface IUserDAO {
    public void insertUser(User user);
    public List<User> getAllUsers();
    public User getUserById(String id);
    public void updateUser(User user);
    public void deleteUser(User user);
}
