package co.com.sofka.business.readerActions;

import co.com.sofka.entities.Book;
import co.com.sofka.entities.User;

public interface IGenerateBookLoan {
    public void generateBookLoan(Book book, User user, int quantity);
}
