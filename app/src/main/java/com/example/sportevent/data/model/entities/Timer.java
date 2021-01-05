package com.example.sportevent.data.model.entities;

public class Timer {
    private int minutes;
    private int seconds;

    public Timer(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }
}
