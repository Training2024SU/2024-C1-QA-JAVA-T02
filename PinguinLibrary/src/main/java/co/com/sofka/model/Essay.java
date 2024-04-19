package co.com.sofka.model;

import co.com.sofka.enums.ResourceType;

public class Essay extends Resource {
    private String academicLevel;

    public Essay(ResourceType type, String title, int quantity, int quantityLoaned, Author author
            , String academicLevel) {
        super(type, title, quantity, quantityLoaned, author);
        this.academicLevel = academicLevel;
    }

    public Essay(int id, ResourceType type, String title, int quantity, int quantityLoaned,
                 Author author, String academicLevel) {
        super(id, type, title, quantity, quantityLoaned, author);
        this.academicLevel = academicLevel;
    }

    @Override
    public String toString() {
        return "Essay{" + super.toString() + "academicLevel='" + academicLevel + "'} ";
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }
}
