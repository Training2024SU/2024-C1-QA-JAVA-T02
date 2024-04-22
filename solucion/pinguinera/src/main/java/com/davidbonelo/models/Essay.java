package com.davidbonelo.models;

import static com.davidbonelo.utils.UserInteractions.askNumber;
import static com.davidbonelo.utils.UserInteractions.askText;

public class Essay extends LibraryItem{
    private String supervisor;
    private String topic;

    public Essay(String title, String author, int copies, int copiesBorrowed, String supervisor, String topic) {
        super(title, author, copies, copiesBorrowed);
        this.supervisor = supervisor;
        this.topic = topic;
    }

    public Essay(int id, String title, String author, int copies, int copiesBorrowed, String supervisor, String topic) {
        super(id, title, author, copies, copiesBorrowed);
        this.supervisor = supervisor;
        this.topic = topic;
    }
    public static Essay createEssayFromInput() {
        String title = askText("Title: ");
        String author = askText("Author: ");
        int copies = askNumber("Copies: ");
        int copiesBorrowed = askNumber("Copies borrowed: ");
        String supervisor = askText("Supervisor: ");
        String topic = askText("Topic: ");
        return new Essay(title, author, copies, copiesBorrowed, supervisor, topic);
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "essay{" +
                "supervisor='" + supervisor + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
