package com.example.sportevent.data.model.entities;

public class Result {
    private Timer timer;
    private int distance;
    private int placeNumber;
    private int medal;

    public Result(Timer timer, int distance, int placeNumber, int medal) {
        this.timer = timer;
        this.distance = distance;
        this.placeNumber = placeNumber;
        this.medal = medal;
    }


    public Timer getTimer() {
        return timer;
    }

    public int getDistance() {
        return distance;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public int getMedal() {
        return medal;
    }
}
