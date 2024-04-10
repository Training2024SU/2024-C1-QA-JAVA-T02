package co.com.sofka.model;

public class Novel extends Manuscript{
    private String gender;

    public Novel() {
    }

    public Novel(String gender) {
        this.gender = gender;
    }

    public Novel(String title, Author author, String gender) {
        super(title, author);
        this.gender = gender;
    }

    public Novel(String id, int quantity, int quantityLoaned, int quantityAvailable, String title, Author author, String gender) {
        super(id, quantity, quantityLoaned, quantityAvailable, title, author);
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "gender='" + gender + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", id='" + id + '\'' +
                ", quantity=" + quantity +
                ", quantityLoaned=" + quantityLoaned +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
