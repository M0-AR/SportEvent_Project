package com.example.sportevent.data.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Event implements Serializable {
    private int id;
    private String imageURL;
    private String eventName;
    private String eventDescription;
    private String location;
    private ArrayList<Participant> joinedEventParticipants;
    private ArrayList<Participant> finishedRaceParticipants;
    private Date joinStartDate;
    private Date joinEndDate;
    private Date raceStartDate;
    private Date raceEndDate;




    public Event(int id, String imageURL, String eventName, String eventDescription, String location, ArrayList<Participant> joinedEventParticipants, ArrayList<Participant> finishedRaceParticipants, Date joinStartDate, Date joinEndDate, Date raceStartDate, Date raceEndDate) {
        this.id = id;
        this.imageURL = imageURL;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.location = location;
        this.joinedEventParticipants = joinedEventParticipants;
        this.finishedRaceParticipants = finishedRaceParticipants;
        this.joinStartDate = joinStartDate;
        this.joinEndDate = joinEndDate;
        this.raceStartDate = raceStartDate;
        this.raceEndDate = raceEndDate;
    }

    public int getId() {
        return id;
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

    public String getLocation() {
        return location;
    }

    public ArrayList<Participant> getJoinedEventParticipants() {
        return joinedEventParticipants;
    }

    public ArrayList<Participant> getFinishedRaceParticipants() {
        return finishedRaceParticipants;
    }
}
