package com.example.pepi.busmanagmentsystem.Models;

/**
 * Created by pepi_ on 10/17/2018.
 */

public class RouteItem {

    private int id;
    private String from;
    private String to;
    private String time;
    private String company;

    public RouteItem(int id, String from, String to,String time,String company) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.time=time;
        this.company=company;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
       return from+" - "+to+" - "+time;
    }
}
