package com.davidbonelo.models;

import java.sql.Time;

import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

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

    public static VideoRecording createVideoFromInput() {
        String title = askText("Title: ");
        String author = askText("Author: ");
        int copies = askNumber("Copies: ");
        int copiesBorrowed = askNumber("Copies borrowed: ");
        Time duration = Time.valueOf(askText("time (hh:mm:ss): "));
        String format = askText("format: ");
        return new VideoRecording(title, author, copies, copiesBorrowed, duration, format);
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
        return "VideoRecording{" +  super.toString() +
                ", duration=" + duration +
                ", format='" + format + '\'' +
                '}';
    }
}
