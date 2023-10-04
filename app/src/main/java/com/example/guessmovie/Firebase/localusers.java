package com.example.guessmovie.Firebase;

public class localusers {
    private String nick;
    private String pass;

    public localusers(String nick, String pass) {
        this.nick = nick;
        this.pass = pass;
    }

    public String getnick() {
        return nick;
    }

    public void setnick(String nick) {
        this.nick = nick;
    }

    public String getpass() {
        return pass;
    }

    public void setpass(String pass) {
        this.pass = pass;
    }

    public localusers() {
    }

}
