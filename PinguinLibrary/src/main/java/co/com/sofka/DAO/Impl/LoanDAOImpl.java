package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.LoanDAO;
import co.com.sofka.DAO.ResourceDAO;
import co.com.sofka.DAO.UserDAO;
import co.com.sofka.enums.LoanStatus;
import co.com.sofka.model.Loan;
import co.com.sofka.model.Resource;
import co.com.sofka.model.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.businessLogic.Library.mySqlOperation;

public class LoanDAOImpl implements LoanDAO {
    private static final String insertIntoQuery = "INSERT INTO Loans (loan_id, request_date, " +
            "return_date, status, user_id) VALUES (%s, '%s', '%s', '%s', '%s');";
    private static final String selectAllQuery = "SELECT * FROM Loans l LEFT JOIN user u ON l" +
            ".user_id = u.id WHERE l.is_deleted = 0;";
    private static final String updateQuery = "UPDATE Loans SET loan_id = %s, request_date = " +
            "'%s', return_date = '%s', status = " + "'%s' WHERE is_deleted = 0 AND loan_id = %s;";
    private static final String deleteQuery = "UPDATE Loans SET is_deleted = 1 WHERE " + "loan_id"
            + " = %s;";

    @Override
    public void insertLoan(Loan loan) throws SQLException {
        Connection connection = mySqlOperation.getConnection();
        String id = (loan.getId() == null) ? "NULL" : loan.getId().toString();
        String query = String.format(insertIntoQuery, id, Date.valueOf(loan.getRequestedDate()),
                Date.valueOf(loan.getReturnDate()), loan.getStatus().toString(),
                loan.getUser().getId());
        try (PreparedStatement preparedStatement = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);
            int insertedRows = preparedStatement.executeUpdate();
            if (insertedRows == 0) {
                throw new SQLException("Loan creation failed, no rows affected");
            }
            if (loan.getId() == null) {
                // Get and set auto generated id
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        loan.setId(generatedKeys.getInt(1));
                    } else throw new SQLException("Couldn't obtain generated id");
                }
            }
            // insert extra data for every type
            insertLoanResources(loan, connection);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void insertLoanResources(Loan loan, Connection connection) throws SQLException {
        String sql = "INSERT INTO loan_resources (loan_id, resource_id, type) VALUES ( ?, ?, ? )";
        PreparedStatement statement = connection.prepareStatement(sql);
        for (Resource r : loan.getLentResources()) {
            statement.setInt(1,loan.getId());
            statement.setInt(2,r.getId());
            statement.setString(3, r.getType().toString());
            statement.addBatch();
        }
        statement.executeBatch();
    }

    @Override
    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSet = mySqlOperation.getResultSet()) {
            while (resultSet.next()) {
                Loan loan = buildLoanFromResultSet(resultSet);
                loans.add(loan);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching loans: " + e.getMessage(), e);
        }
        return loans;
    }

    private Loan buildLoanFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("loan_id");
        LocalDate requestDate = resultSet.getDate("request_date").toLocalDate();
        LocalDate returnDate = resultSet.getDate("return_date").toLocalDate();
        LoanStatus status = LoanStatus.valueOf(resultSet.getString("status"));
        String userId = resultSet.getString("user_id");

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUserById(userId);

        return new Loan(id, requestDate, returnDate, status, user);
    }

    @Override
    public Loan getLoanById(int id) {
        Loan loan =
                getAllLoans().stream().filter(l -> l.getId().equals(id)).findFirst().orElse(null);
        if (loan != null) {
            loan.setLentResources(getAllResourcesForALoan(id));
            return loan;
        }
        return null;
    }

    private List<Resource> getAllResourcesForALoan(int id) {
        ResourceDAO resourceDAO = new ResourceDAOImpl();
        List<Resource> resources = new ArrayList<>();
        String sql = "SELECT * FROM loan_resources WHERE loan_id = " + id;
        mySqlOperation.setSqlStatement(sql);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSet = mySqlOperation.getResultSet()) {
            while (resultSet.next()) {
                int resourceId = resultSet.getInt("resource_id");
                Resource resource = resourceDAO.getResourceById(resourceId);
                resources.add(resource);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching resources: " + e.getMessage(), e);
        }
        return resources;
    }

    @Override
    public void updateLoan(Loan loan) {
        String query = String.format(updateQuery, Date.valueOf(loan.getRequestedDate()),
                Date.valueOf(loan.getRequestedDate()), loan.getStatus().toString(),
                loan.getUser().getId(), loan.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
        // Modifying lent resources is not allowed
    }

    @Override
    public void deleteLoan(Loan loan) {
        String query = String.format(deleteQuery, loan.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
}
