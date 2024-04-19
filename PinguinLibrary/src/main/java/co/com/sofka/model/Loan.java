package co.com.sofka.model;

import co.com.sofka.enums.LoanStatus;

import java.time.LocalDate;
import java.util.List;

public class Loan {
    private Integer id;
    private LocalDate requestedDate;
    private LocalDate returnDate;
    private LoanStatus status;
    private User user;
    private List<Resource> lentResources;

    public Loan(LocalDate returnDate, User user) {
        this.requestedDate = LocalDate.now();
        this.returnDate = returnDate;
        this.status = LoanStatus.REQUESTED;
        this.user = user;
    }

    public Loan(Integer id, LocalDate requestedDate, LocalDate returnDate, LoanStatus status,
                User user) {
        this.id = id;
        this.requestedDate = requestedDate;
        this.returnDate = returnDate;
        this.status = status;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Loan{" + "id=" + id + ", requestedDate=" + requestedDate + ", returnDate=" + returnDate + ", status=" + status + ", user=" + user.getName() + '}';
    }

    public String toStringWithResources() {
        StringBuilder stringBuilder = new StringBuilder(this + "\nResources list: \n");
        if (lentResources != null) {
            lentResources.forEach(r -> stringBuilder.append(r).append("\n"));
        }
        return stringBuilder.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Resource> getLentResources() {
        return lentResources;
    }

    public void setLentResources(List<Resource> lentResources) {
        this.lentResources = lentResources;
    }
}
