package com.example.sportevent.data.model.entities;

import java.util.Date;

public class Event {
    private String imageURL;
    private String eventName;
    private String eventDescription;
    private String location;
    private Date joinStartDate;
    private Date joinEndDate;
    private Date raceStartDate;
    private Date raceEndDate;
    // TODO: 1/4/2021 make a boolean attribute to hide the event according to joinEndDate

    public Event(String imageURL, String eventName, String eventDescription, Date joinStartDate, Date joinEndDate, Date raceStartDate, Date raceEndDate) {
        this.imageURL = imageURL;
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

    public String getImageURL() {
        return imageURL;
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
