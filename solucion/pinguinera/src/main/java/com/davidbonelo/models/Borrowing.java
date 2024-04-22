package com.davidbonelo.models;

import java.time.LocalDate;
import java.util.List;

public class Borrowing {
    private final User borrower;
    private int id;
    private List<LibraryItem> borrowedItems;
    private final LocalDate requestedDate;
    private LocalDate returnDate;
    private BorrowingStatus status;

    public Borrowing(LocalDate returnDate, User borrower) {
        this.requestedDate = LocalDate.now();
        setReturnDate(returnDate);
        this.status = BorrowingStatus.REQUESTED;
        this.borrower = borrower;
    }

    public Borrowing(int id, LocalDate returnDate, LocalDate requestedDate, User borrower,
                     BorrowingStatus status) {
        this.id = id;
        this.requestedDate = requestedDate;
        setReturnDate(returnDate);
        this.borrower = borrower;
        this.status = status;
    }

    public List<LibraryItem> getBorrowedItems() {
        return borrowedItems;
    }

    public void setBorrowedItems(List<LibraryItem> borrowedItems) {
        this.borrowedItems = borrowedItems;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        int daysUntilReturn = requestedDate.until(returnDate).getDays();
        if (daysUntilReturn > 15) {
            throw new IllegalArgumentException("returnDate must be less than 15 days since " +
                    "requestedDate");
        }
        this.returnDate = returnDate;
    }

    public BorrowingStatus getStatus() {
        return status;
    }

    public void setStatusBorrowed() {
        this.status = BorrowingStatus.BORROWED;
    }

    public void setStatusFinalized() {
        this.status = BorrowingStatus.FINALIZED;
        if (LocalDate.now().isAfter(returnDate)) {
            System.err.println("The user returned the items after the established date!!");
        }
    }

    public User getBorrower() {
        return borrower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Borrowing{ id=" + id + ", borrowerId=" + borrower.getId() + ", borrower=" + borrower.getName() + ", requestedDate=" + requestedDate + ", returnDate=" + returnDate + ", status=" + status + '}';
    }

    public String toStringWithItems() {
        StringBuilder stringBuilder = new StringBuilder(this + "\nItems list: \n");
        if (borrowedItems != null) {
            borrowedItems.forEach(i -> stringBuilder.append(i).append("\n"));
        }
        return stringBuilder.toString();
    }
}
