package co.com.sofka.model;

import co.com.sofka.enums.ResourceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VideoRecording extends Resource {
    private String resolution;

    public VideoRecording(int id, ResourceType type, String title, int quantity, int quantityLoaned,
                          Author author, String resolution) {
        super(id, type, title, quantity, quantityLoaned, author);
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "VideoRecording{" + super.toString() + "resolution='" + resolution + "'} ";
    }
}
