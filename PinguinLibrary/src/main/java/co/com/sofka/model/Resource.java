package co.com.sofka.model;

import co.com.sofka.enums.ResourceType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class Resource {
    private Integer id;
    private ResourceType type;
    private String title;
    private int quantity;
    private int quantityLoaned;
    private Author author;

    protected Resource(int id, ResourceType type, String title, int quantity, int quantityLoaned,
                       Author author) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.quantity = quantity;
        this.quantityLoaned = quantityLoaned;
        this.author = author;
    }

    protected int getAlailableQuantity() {
        return quantity - quantityLoaned;
    }

    @Override
    public String toString() {
        return "id=" + id + ", type=" + type + ", title='" + title + "', quantity=" + quantity +
                ", quantityLoaned=" + quantityLoaned + ", author=" + author + '}';
    }
}
