package co.com.sofka.DAO;

import co.com.sofka.entities.Novel;
import co.com.sofka.entities.NovelLoan;

import java.util.List;

public interface INovelLoanDAO {
    public void insertNovelLoan(NovelLoan novelLoan);
    public List<NovelLoan> getAllNovelLoans();
    public NovelLoan getNovelLoanById(String id);
    public void updateNovelLoan(NovelLoan novelLoan);
    public void deleteNovelLoan(NovelLoan novelLoan);
}
