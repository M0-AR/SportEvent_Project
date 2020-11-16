package com.example.sportevent.data;

import java.util.Date;

public class Event {
    private int imageResource;
    private String eventName;
    private String eventDescription;
    private Date startDate;
    private Date endDate;

    public Event(int imageResource, String eventName, String eventDescription, Date startDate, Date endDate) {
        this.imageResource = imageResource;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.startDate = startDate;
        this.endDate = endDate;
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


    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}
