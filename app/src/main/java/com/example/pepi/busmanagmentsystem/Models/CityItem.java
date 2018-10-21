package com.example.pepi.busmanagmentsystem.Models;

/**
 * Created by pepi_ on 10/21/2018.
 */

public class CityItem {
    private int id;
    private String name;


    public CityItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
