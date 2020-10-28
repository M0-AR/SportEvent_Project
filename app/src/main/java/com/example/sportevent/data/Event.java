package com.example.sportevent.data;

public class Event {
    private int imageResource;
    private String eventName;
    private String eventDescription;

    public Event(int imageResource, String eventName, String eventDescription) {
        this.imageResource = imageResource;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }
}
