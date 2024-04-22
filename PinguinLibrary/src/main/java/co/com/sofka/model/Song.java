package co.com.sofka.model;

import co.com.sofka.enums.ResourceType;

public class Song extends Resource {
    private int duration;

    public Song() {
    }

    public Song(ResourceType type, String title, int quantity, int quantityLoaned,
                Author author, int duration) {
        super(type, title, quantity, quantityLoaned, author);
        this.duration = duration;
    }

    public Song(int id, ResourceType type, String title, int quantity, int quantityLoaned,
                Author author, int duration) {
        super(id, type, title, quantity, quantityLoaned, author);
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" + super.toString() + "duration=" + duration + "} ";
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
