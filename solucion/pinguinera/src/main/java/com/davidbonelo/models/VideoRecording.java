package com.davidbonelo.models;

import java.sql.Time;

public class VideoRecording extends LibraryItem{
    private Time duration;
    private String format;

    public VideoRecording(String title, String author, int copies, int copiesBorrowed, Time duration, String format) {
        super(title, author, copies, copiesBorrowed);
        this.duration = duration;
        this.format = format;
    }

    public VideoRecording(int id, String title, String author, int copies, int copiesBorrowed, Time duration, String format) {
        super(id, title, author, copies, copiesBorrowed);
        this.duration = duration;
        this.format = format;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "VideoRecording{" +
                "duration=" + duration +
                ", format='" + format + '\'' +
                '}';
    }
}
