package com.davidbonelo.models;

import java.sql.Date;

public class UserExtraInfo {
    private User user;
    private String biography;
    private Date birthday;

    public UserExtraInfo() {
    }

    public UserExtraInfo(User user, String biography, Date birthday) {
        this.user = user;
        this.biography = biography;
        this.birthday = birthday;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "UserExtraInfo{" +
                "user=" + user +
                ", biography='" + biography + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
