package co.com.sofka.DAO;

import co.com.sofka.model.Loan;

import java.sql.SQLException;
import java.util.List;

public interface LoanDAO {
    void insertLoan(Loan loan) throws SQLException;

    List<Loan> getAllLoans();

    Loan getLoanById(int id);

    void updateLoan(Loan loan);

    void deleteLoan(Loan loan);
}
