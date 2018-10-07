package com.example.pepi.busmanagmentsystem.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pepi_ on 10/07/2018.
 */

public class Route {

    private int id;

    private int startId;

    private int endId;

    private String startLocation;

    private String endLocation;

    private String startTime;

    private String duration;


    public Route(int id,
                 int startId,
                 int endId,
                 String startLocation,
                 String endLocation,
                 String startTime,
                 String duration
    ) {
        this.id = id;
        this.startId = startId;
        this.endId = endId;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startTime = startTime;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartId() {
        return startId;
    }

    public void setStartId(int startId) {
        this.startId = startId;
    }

    public int getEndlocationId() {
        return endId;
    }

    public void setEndlocationId(int endlocationId) {
        this.endId = endlocationId;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
