package co.com.sofka.business.reader.interf;

import co.com.sofka.model.Book;
import co.com.sofka.model.Novel;
import co.com.sofka.model.User;

public interface LoanABook {
    public void loanBook(User user, Book book);
}
