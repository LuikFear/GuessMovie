package com.example.guessmovie;

import android.widget.ImageButton;

import java.io.Serializable;

public class ListItemM  implements Serializable {
    public String color;
    public String name;
    public String des;
    public String status;

    public ListItemM(String color, String name, String des, String status) {
        this.color = color;
        this.name = name;
        this.des = des;
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
