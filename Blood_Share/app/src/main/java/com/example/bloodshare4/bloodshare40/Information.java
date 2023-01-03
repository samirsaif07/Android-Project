package com.example.bloodshare4.bloodshare40;

import android.app.Application;

public class Information {
    String s;
    double latitude;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    String group;
    double longitude;
    private static final Information ourInstance = new Information();
    public static Information getInstance() {
        return ourInstance;
    }
    private Information() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}