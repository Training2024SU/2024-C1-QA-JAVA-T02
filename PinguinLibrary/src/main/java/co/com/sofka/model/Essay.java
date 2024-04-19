package co.com.sofka.model;

import co.com.sofka.enums.ResourceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Essay extends Resource {
    private String academicLevel;

    public Essay(int id, ResourceType type, String title, int quantity, int quantityLoaned,
                 Author author, String academicLevel) {
        super(id, type, title, quantity, quantityLoaned, author);
        this.academicLevel = academicLevel;
    }

    @Override
    public String toString() {
        return "Essay{" + super.toString() + "academicLevel='" + academicLevel + "'} ";
    }
}
