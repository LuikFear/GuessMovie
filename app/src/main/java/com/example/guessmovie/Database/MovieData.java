package com.example.guessmovie.Database;

public class MovieData {

    private String title;
    private String des;
    private int LvS;
    private String hint1;
    private String hint2;
    private String hint3;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getLvS() {
        return LvS;
    }

    public void setLvS(int lvS) {
        LvS = lvS;
    }

    public String getHint1() {
        return hint1;
    }

    public void setHint1(String hint1) {
        this.hint1 = hint1;
    }

    public String getHint2() {
        return hint2;
    }

    public void setHint2(String hint2) {
        this.hint2 = hint2;
    }

    public String getHint3() {
        return hint3;
    }

    public void setHint3(String hint3) {
        this.hint3 = hint3;
    }

    public MovieData() {
    }

    public MovieData(String title, String des, int lvS, String hint1, String hint2, String hint3) {
        this.title = title;
        this.des = des;
        LvS = lvS;
        this.hint1 = hint1;
        this.hint2 = hint2;
        this.hint3 = hint3;
    }
}