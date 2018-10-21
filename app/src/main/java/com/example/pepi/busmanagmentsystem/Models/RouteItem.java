package com.example.pepi.busmanagmentsystem.Models;

/**
 * Created by pepi_ on 10/17/2018.
 */

public class RouteItem {

    private int id;
    private String from;
    private String to;

    public RouteItem(int id, String from, String to) {
        this.id = id;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
       return from+" - "+to;
    }
}
