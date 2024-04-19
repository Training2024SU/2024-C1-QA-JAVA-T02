package co.com.sofka.model;

import co.com.sofka.enums.ResourceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Song extends Resource {
    private int duration;

    public Song(int id, ResourceType type, String title, int quantity, int quantityLoaned,
                Author author, int duration) {
        super(id, type, title, quantity, quantityLoaned, author);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" + super.toString() + "duration=" + duration + "} ";
    }
}
