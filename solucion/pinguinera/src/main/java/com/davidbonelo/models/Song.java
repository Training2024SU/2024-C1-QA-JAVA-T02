package com.davidbonelo.models;

import java.sql.Time;

import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class Song extends LibraryItem{
    private String recordLabel;
    private Time duration;

    public Song(String title, String author, int copies, int copiesBorrowed, String recordLabel, Time duration) {
        super(title, author, copies, copiesBorrowed);
        this.recordLabel = recordLabel;
        this.duration = duration;
    }

    public Song(int id, String title, String author, int copies, int copiesBorrowed, String recordLabel, Time duration) {
        super(id, title, author, copies, copiesBorrowed);
        this.recordLabel = recordLabel;
        this.duration = duration;
    }

    public static Song createSongFromInput() {
        String title = askText("Title: ");
        String author = askText("Author: ");
        int copies = askNumber("Copies: ");
        int copiesBorrowed = askNumber("Copies borrowed: ");
        String recordLabel = askText("Record label: ");
        Time duration = Time.valueOf(askText("Durations: "));
        return new Song(title, author, copies, copiesBorrowed, recordLabel, duration);
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" + super.toString() +
                ", recordLabel='" + recordLabel + '\'' +
                ", duration=" + duration +
                '}';
    }
}
