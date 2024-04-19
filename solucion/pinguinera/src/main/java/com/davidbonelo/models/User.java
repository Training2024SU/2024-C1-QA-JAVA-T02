package com.davidbonelo.models;

import java.sql.Date;
import java.util.ResourceBundle;

import static com.davidbonelo.utils.UserInteractions.askText;

public class User {
    private int id;
    private String name;
    private String email;
    private UserRole role;

    private String biography;
    private Date birthday;

    public User(String name, String email, UserRole role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public User(int id, String name, String email, UserRole role, String biography, Date birthday) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.biography = biography;
        this.birthday = birthday;
    }

    public User(int id, String name, String email, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public static User createUserFromInput() {
        ResourceBundle messages = ResourceBundle.getBundle("messages");
        String name = askText(messages.getString("user.req.name"));
        String email = askText(messages.getString("user.req.email"));
        return new User(name, email, UserRole.READER);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", biography='" + biography + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
