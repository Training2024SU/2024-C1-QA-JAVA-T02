package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.ResourceDAO;
import co.com.sofka.enums.ResourceType;
import co.com.sofka.model.Author;
import co.com.sofka.model.Essay;
import co.com.sofka.model.Resource;
import co.com.sofka.model.Song;
import co.com.sofka.model.VideoRecording;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.businessLogic.Library.mySqlOperation;

public class ResourceDAOImpl implements ResourceDAO {
    private static final String insertIntoQuery = "INSERT INTO Resources (resource_id, type, " +
            "title, quantity, quantity_loaned, " + "author_id) VALUES (%s, '%s', '%s', %s, %s, " + "'%s');";
    private static final String selectAllQuery =
            "SELECT * FROM Resources r LEFT JOIN author a " + "ON" + " r.author_id = a.id WHERE " + "r" + ".is_deleted = 0;";
    private static final String updateQuery =
            "UPDATE Resources SET type = '%s', title = '%s', " + "quantity = %s, quantity_loaned "
                    + "= %s, author_id = '%s' WHERE is_deleted = 0 AND resource_id = " + "%s;";
    private static final String deleteQuery = "UPDATE Resources SET is_deleted = 1 WHERE " +
            "resource_id = %s;";

    @Override
    public void insertResource(Resource resource) throws SQLException {
        Connection connection = mySqlOperation.getConnection();
        String id = (resource.getId() == null) ? "NULL" : resource.getId().toString();
        String query = String.format(insertIntoQuery, id, resource.getType().toString(),
                resource.getTitle(), resource.getQuantity(), resource.getQuantityLoaned(),
                resource.getAuthor().getId());
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);
            int insertedRows = preparedStatement.executeUpdate();
            if (insertedRows == 0) {
                throw new SQLException("Borrowing creation failed, no rows affected");
            }
            if (resource.getId() == null) {
                // Get and set auto generated id
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        resource.setId(generatedKeys.getInt(1));
                    } else throw new SQLException("Couldn't obtain generated id");
                }
            }
            // insert extra data for every type
            insertResourceDetails(resource);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void insertResourceDetails(Resource resource) throws SQLException {
        String sql = null;
        if (resource instanceof Song song) {
            sql = "INSERT INTO Songs VALUES (" + song.getId() + ", " + song.getDuration() + ");";
        } else if (resource instanceof VideoRecording video) {
            sql = "INSERT INTO Video_recordings VALUES (" + video.getId() + ", " + video.getResolution() + ");";
        } else if (resource instanceof Essay essay) {
            sql = "INSERT INTO Essays VALUES (" + essay.getId() + ", " + essay.getAcademicLevel() + ");";
        }
        mySqlOperation.setSqlStatement(sql);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public List<Resource> getAllResources() {
        List<Resource> users = new ArrayList<>();
        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSet = mySqlOperation.getResultSet()) {
            while (resultSet.next()) {
                Resource resource = buildResourceFromResultSet(resultSet);
                users.add(resource);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching resources: " + e.getMessage(), e);
        }
        return users;
    }

    @Override
    public Resource getResourceById(int id) {
        return getAllResources().stream().filter(resource -> resource.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void updateResource(Resource resource) {
        String query = String.format(updateQuery, resource.getType().toString(),
                resource.getTitle(), resource.getQuantity(), resource.getQuantityLoaned(),
                resource.getAuthor().getId(), resource.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();

        // TODO: Check affected rows before updating details
        updateResourceDetails(resource);
    }

    private void updateResourceDetails(Resource resource) {
        String sql = null;
        if (resource instanceof Song song) {
            sql = "UPDATE Songs SET duration = " + song.getDuration() + "WHERE resource_id = " + song.getId() + ";";
        } else if (resource instanceof VideoRecording video) {
            sql = "UPDATE Video_recordings SET resolution = " + video.getResolution() + "WHERE " + "resource_id = " + video.getId() + ";";
        } else if (resource instanceof Essay essay) {
            sql = "UPDATE Essays SET academic_level = " + essay.getAcademicLevel() + "WHERE " +
                    "resource_id = " + essay.getId() + ";";
        }
        mySqlOperation.setSqlStatement(sql);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public void deleteResource(Resource resource) {
        String query = String.format(deleteQuery, resource.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    public Resource buildResourceFromResultSet(ResultSet resultSet) throws SQLException {
        ResourceType type = ResourceType.valueOf(resultSet.getString("type"));
        int id = resultSet.getInt("resource_id");
        String title = resultSet.getString("title");
        int quantity = resultSet.getInt("quantity");
        int quantityLoaned = resultSet.getInt("quantity_loaned");
        String authorId = resultSet.getString("author_id");
        String authorName = resultSet.getString("name");
        Author author = new Author(authorId, authorName);
        return switch (type) {
            case SONG -> getSong(type, id, title, quantity, quantityLoaned, author);
            case VIDEO_RECORDING -> getVideo(type, id, title, quantity, quantityLoaned, author);
            case ESSAY -> getEssay(type, id, title, quantity, quantityLoaned, author);
        };
    }

    private Song getSong(ResourceType type, int id, String title, int quantity,
                         int quantityLoaned, Author author) throws SQLException {
        String sql = "SELECT * FROM Songs WHERE resource_id = " + id + ";";
        mySqlOperation.setSqlStatement(sql);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSetS = mySqlOperation.getResultSet()) {
            int duration = resultSetS.getInt("duration");
            return new Song(id, type, title, quantity, quantityLoaned, author, duration);
        }
    }

    private VideoRecording getVideo(ResourceType type, int id, String title, int quantity,
                                    int quantityLoaned, Author author) throws SQLException {
        String sql = "SELECT * FROM Video_recordings WHERE resource_id = " + id + ";";
        mySqlOperation.setSqlStatement(sql);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSetV = mySqlOperation.getResultSet()) {
            String resolution = resultSetV.getString("resolution");
            return new VideoRecording(id, type, title, quantity, quantityLoaned, author,
                    resolution);
        }
    }

    private Essay getEssay(ResourceType type, int id, String title, int quantity,
                           int quantityLoaned, Author author) throws SQLException {
        String sql = "SELECT * FROM Essays WHERE resource_id = " + id + ";";
        mySqlOperation.setSqlStatement(sql);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSetE = mySqlOperation.getResultSet()) {
            String academicLevel = resultSetE.getString("academic_level");
            return new Essay(id, type, title, quantity, quantityLoaned, author, academicLevel);
        }
    }
}
