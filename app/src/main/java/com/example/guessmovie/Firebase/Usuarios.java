package com.example.guessmovie.Firebase;

public class Usuarios {

    String userId, name,profile,score;

    public Usuarios() {
    }

    public Usuarios(String userId, String name, String profile, String score) {
        this.userId = userId;
        this.name = name;
        this.profile = profile;
        this.score = score;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
