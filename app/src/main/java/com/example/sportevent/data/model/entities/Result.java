package com.example.sportevent.data.model.entities;

public class Result {
    private float distance;
    private float hours;
    private float minutes;
    private float seconds;
    private int placeNumber;
    private int medal;


    public Result(float distance, float hours, float minutes, float seconds, int placeNumber, int medal) {
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

    public float getHours() {
        return hours;
    }

    public float getMinutes() {
        return minutes;
    }

    public float getSeconds() {
        return seconds;
    }

    public float getDistance() {
        return distance;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public int getMedal() {
        return medal;
    }
}
