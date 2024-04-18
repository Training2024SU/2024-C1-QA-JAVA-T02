package com.davidbonelo.models;

import java.sql.Time;

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
        return "Song{" +
                "recordLabel='" + recordLabel + '\'' +
                ", duration=" + duration +
                '}';
    }
}
