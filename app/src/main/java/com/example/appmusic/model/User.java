package com.example.appmusic.model;

public class User {
    private String fullname;
    private String username;
    private String password;
    private String imageuser;

    // Constructor
    public User(String fullname, String username, String password, String imageuser) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.imageuser = imageuser;
    }

    // Getters and Setters
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageuser() {
        return imageuser;
    }

    public void setImageuser(String imageuser) {
        this.imageuser = imageuser;
    }
}
