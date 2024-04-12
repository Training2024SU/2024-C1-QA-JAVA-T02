package co.com.sofka.DAO.Impl;

import co.com.sofka.DAO.INovelLoanDAO;
import co.com.sofka.database.mysql.MySqlOperation;
import co.com.sofka.entities.LoanStatus;
import co.com.sofka.entities.Novel;
import co.com.sofka.entities.NovelLoan;
import co.com.sofka.entities.User;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NovelLoanDAO implements INovelLoanDAO {
    private static final String insertIntoQuery = "INSERT INTO novel_loan (novel_loan_id, user_id, novel_id, loan_date, return_date, status)" +
            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')";

    private static final String selectAllQuery = "SELECT novel_loan_id, user_id, novel_id, loan_date, return_date, status FROM novel_loan ";
    private static final String updateQuery = "UPDATE novel_loan SET " +
            "user_id = '%s', " +
            "novel_id = '%s', " +
            "loan_date = '%s', " +
            "return_date = '%s', " +
            "status = '%s' " +
            "WHERE novel_loan_id = '%s'";

    private static final String deleteQuery = "DELETE FROM novel_loan WHERE novel_loan_id = '%s'";
    private final MySqlOperation mySqlOperation;

    public NovelLoanDAO(MySqlOperation mySqlOperation) {
        this.mySqlOperation = mySqlOperation;
    }

    @Override
    public void insertNovelLoan(NovelLoan novelLoan) {
        String query = String.format(insertIntoQuery,
                novelLoan.getId(),
                novelLoan.getUser().getId(),
                novelLoan.getNovel().getId(),
                novelLoan.getLoanDate(),
                novelLoan.getReturnDate(),
                novelLoan.getLoanStatus());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }

    @Override
    public List<NovelLoan> getAllNovelLoans() {
        List<NovelLoan> novelLoans = new ArrayList<>();
        mySqlOperation.setSqlStatement(selectAllQuery);
        mySqlOperation.executeSqlStatement();
        try (ResultSet resultSet = mySqlOperation.getResultSet()){
            while (resultSet.next()){
                NovelLoan novelLoan = getNovelLoan(resultSet);
                novelLoans.add(novelLoan);
            }
        }catch (SQLException e){
            throw new RuntimeException("Error fetching novel loans: " + e.getMessage(), e);
        }
        return novelLoans;
    }

    private NovelLoan getNovelLoan(ResultSet resultSet) throws SQLException {
        NovelDao novelDao = new NovelDao(mySqlOperation);
        UserDAO userDAO = new UserDAO(mySqlOperation);
        Novel novel;
        User user;
        String id = resultSet.getString("novel_loan_id");
        user = userDAO.getUserById(resultSet.getString("user_id"));
        novel = novelDao.getNovelById(resultSet.getString("novel_id"));
        Date loanDate = resultSet.getDate("loan_date");
        Date returnDate = resultSet.getDate("return_date");
        LoanStatus loanStatus = LoanStatus.valueOf(resultSet.getString("status"));
        return new NovelLoan(id, user, novel, loanDate, returnDate, loanStatus);
    }

    @Override
    public NovelLoan getNovelLoanById(String id) {
        String query = selectAllQuery + "WHERE novel_loan_id = '" + id + "';";
        NovelLoan novelLoan = new NovelLoan();
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatement();
        try(ResultSet resultSet = mySqlOperation.getResultSet()){
            while (resultSet.next()){
                novelLoan = getNovelLoan(resultSet);
            }
        }catch (SQLException e){
            throw new RuntimeException("Error fetching novel loans: " + e.getMessage(), e);
        }
        return novelLoan;
    }

    @Override
    public void updateNovelLoan(NovelLoan novelLoan) {
        String query = String.format(updateQuery,
                novelLoan.getUser().getId(),
                novelLoan.getNovel().getId(),
                novelLoan.getLoanDate(),
                novelLoan.getReturnDate(),
                novelLoan.getLoanStatus(),
                novelLoan.getId());
        mySqlOperation.setSqlStatement(query);
        mySqlOperation.executeSqlStatementVoid();
    }
    @Override
    public void deleteNovelLoan(NovelLoan novelLoan) {

    }
}

