package com.example.sportevent.data;

import java.util.Date;
// joinStartDate
// joinEndDate
// raceStartDate
// raceEndDate




public class Event {
    private int imageResource;
    private String eventName;
    private String eventDescription;
    private Date joinStartDate;
    private Date joinEndDate;
    private Date raceStartDate;
    private Date raceEndDate;

    public Event(int imageResource, String eventName, String eventDescription, Date joinStartDate, Date joinEndDate, Date raceStartDate, Date raceEndDate) {
        this.imageResource = imageResource;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.joinStartDate = joinStartDate;
        this.joinEndDate = joinEndDate;
        this.raceStartDate = raceStartDate;
        this.raceEndDate = raceEndDate;
    }

    public Date getRaceStartDate() {
        return raceStartDate;
    }

    public Date getRaceEndDate() {
        return raceEndDate;
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


    public Date getJoinStartDate() {
        return joinStartDate;
    }

    public Date getJoinEndDate() {
        return joinEndDate;
    }

}
