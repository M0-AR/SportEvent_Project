package com.example.sportevent.data.model.entities;

import java.io.Serializable;

public class Result implements Serializable {
    private double distance;
    private int hours;
    private int minutes;
    private int seconds;
    private int placeNumber;
    private String medal;


    public Result(double distance, int hours, int minutes, int seconds, int placeNumber, String medal) {
        this.distance = distance;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.placeNumber = placeNumber;
        this.medal = medal;
    }

    public Result() {
        // Cloud FireStore
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public double getDistance() {
        return distance;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public String getMedal() {
        return medal;
    }
}
