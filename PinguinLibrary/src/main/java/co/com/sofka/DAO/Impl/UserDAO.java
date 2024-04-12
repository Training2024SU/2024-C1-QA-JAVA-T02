package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.IUserDAO;
import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.Author;
import co.com.sofka.entities.User;
import co.com.sofka.entities.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAO implements IUserDAO {
    private static final String insertIntoQuery = "INSERT INTO user " +
            "(id, name, email, password, role) " +
            "VALUES ('%s', '%s', '%s', '%s', '%s');";
    private static final String selectAllQuery = "SELECT id, " +
            "name, " +
            "email, " +
            "password, " +
            "role " +
            "FROM user ";
    private static final String updateQuery = "UPDATE user SET " +
            "name = '%s', " +
            "email = %s, " +
            "password = %s, " +
            "role = %s, " +
            "WHERE id = '%s';";
    private static final String deleteQuery = "DELETE FROM user WHERE id = '%s';";

    private final MySqlOperation mySqlOperation;
    public UserDAO(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public User getUserByEmail(String email) {
        String query = selectAllQuery + "WHERE email = '" + email + "';";
        User user = null;
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatement();
        ResultSet resultSet = mySqlOperation.getResultSet();

        try {
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(email);
                user.setPassword(resultSet.getString("password"));
                user.setRole(UserType.valueOf(resultSet.getString("role")));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving user by email: " + e.getMessage());
        }
        return user;
    }

}
