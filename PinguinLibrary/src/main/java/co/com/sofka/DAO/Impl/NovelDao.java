package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.INovelDAO;
import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.Novel;
import co.com.sofka.entities.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NovelDao implements INovelDAO {
    private static final String insertIntoQuery = "INSERT INTO novel " +
            "(novel_id, title, recommended_age, quantity, quantity_loaned, quantity_available, gender, author_id) " +
            "VALUES ('%s', '%s', %d, %d, %d, %d, '%s', '%s');";
    private static final String selectAllQuery = "SELECT novel_id, " +
            "title, " +
            "recommended_age, " +
            "quantity, " +
            "quantity_loaned, " +
            "quantity_available, " +
            "gender, " +
            "author_id " +
            "FROM novel;";
    private static final String updateQuery = "UPDATE novel SET " +
            "title = '%s', " +
            "recommended_age = %d, " +
            "quantity = %d, " +
            "quantity_loaned = %d, " +
            "quantity_available = %d, " +
            "gender = '%s', " +
            "author_id = '%s' " +
            "WHERE novel_id = '%s';";
    private static final String deleteQuery = "DELETE FROM novel WHERE novel_id = '%s';";

    private final MySqlOperation mySqlOperation;

    public NovelDao(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    @Override
    public void insertNovel(Novel novel) {
        String query = String.format(insertIntoQuery,
                novel.getId(),
                novel.getTitle(),
                novel.getRecommendedAge(),
                novel.getQuantity(),
                novel.getQuantityLoaned(),
                novel.getQuantityAvailable(),
                novel.getGender(),
                novel.getAuthor().getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public List<Novel> getAllNovels() {
        List<Novel> novels = new ArrayList<>();
        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSet = mySqlOperation.getResultSet()) {
            while (resultSet.next()) {
                String id = resultSet.getString("novel_id");
                String title = resultSet.getString("title");
                int recommendedAge = resultSet.getInt("recommended_age");
                int quantity = resultSet.getInt("quantity");
                int quantityLoaned = resultSet.getInt("quantity_loaned");
                int quantityAvailable = resultSet.getInt("quantity_available");
                String gender = resultSet.getString("gender");
                String authorId = resultSet.getString("author_id");

                Author author = getAuthorById(authorId);

                Novel novel = new Novel(id,quantity,quantityLoaned,quantityAvailable,title,author,gender,recommendedAge);
                novels.add(novel);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching novels: " + e.getMessage(), e);
        }
        return novels;
    }

    @Override
    public Novel getNovelById(String id) {
        Novel novel = null;
        try {
            String query = selectAllQuery + " WHERE novel_id = '" + id + "'";
            mySqlOperation.setSqlStatement(query);
            mySqlOperation.executeSqlStatement();
            ResultSet resultSet = mySqlOperation.getResultSet();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                int recommendedAge = resultSet.getInt("recommended_age");
                int quantity = resultSet.getInt("quantity");
                int quantityLoaned = resultSet.getInt("quantity_loaned");
                int quantityAvailable = resultSet.getInt("quantity_available");
                String gender = resultSet.getString("gender");
                String authorId = resultSet.getString("author_id");

                Author author = getAuthorById(authorId);

                novel = new Novel(id,quantity,quantityLoaned,quantityAvailable,title,author,gender,recommendedAge);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching novel by ID: " + e.getMessage(), e);
        }
        return novel;
    }

    @Override
    public void updateNovel(Novel novel) {
        String query = String.format(updateQuery,
                novel.getTitle(),
                novel.getRecommendedAge(),
                novel.getQuantity(),
                novel.getQuantityLoaned(),
                novel.getQuantityAvailable(),
                novel.getGender(),
                novel.getAuthor().getId(),
                novel.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public void deleteNovel(Novel novel) {
        String query = String.format(deleteQuery, novel.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    private Author getAuthorById(String authorId) throws SQLException {
        AuthorDAO authorDAO = new AuthorDAO(mySqlOperation);
        return authorDAO.getAuthorById(authorId);
    }
}
