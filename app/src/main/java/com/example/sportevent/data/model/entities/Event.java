package com.example.sportevent.data.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Event implements Serializable {
    private static int count = 1;
    private int id;
    private String imageURL;
    private String eventName;
    private String eventDescription;
    private String location;
    private List<String> joinedEventParticipantsEmails;
    private List<String> finishedRaceParticipantsEmails;
    private Date joinStartDate;
    private Date joinEndDate;
    private Date raceStartDate;
    private Date raceEndDate;

    public Event(String imageURL, String eventName, String eventDescription, String location, List<String> joinedEventParticipantsEmails, List<String> finishedRaceParticipantsEmails, Date joinStartDate, Date joinEndDate, Date raceStartDate, Date raceEndDate) {
        this.id = count++;
        this.imageURL = imageURL;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.location = location;
        this.joinedEventParticipantsEmails = joinedEventParticipantsEmails;
        this.finishedRaceParticipantsEmails = finishedRaceParticipantsEmails;
        this.joinStartDate = joinStartDate;
        this.joinEndDate = joinEndDate;
        this.raceStartDate = raceStartDate;
        this.raceEndDate = raceEndDate;
    }


    public Event() {
        // Firebase
    }

    public int getId() {
        return id;
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

    public String getLocation() {
        return location;
    }


    public List<String> getJoinedEventParticipantsEmails() {
        return joinedEventParticipantsEmails;
    }

    public List<String> getFinishedRaceParticipantsEmails() {
        return finishedRaceParticipantsEmails;
    }

    public Date getJoinStartDate() {
        return joinStartDate;
    }

    public Date getJoinEndDate() {
        return joinEndDate;
    }

    public Date getRaceStartDate() {
        return raceStartDate;
    }

    public Date getRaceEndDate() {
        return raceEndDate;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", imageURL='" + imageURL + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", location='" + location + '\'' +
                ", joinedEventParticipantsEmails=" + joinedEventParticipantsEmails +
                ", finishedRaceParticipantsEmails=" + finishedRaceParticipantsEmails +
                ", joinStartDate=" + joinStartDate +
                ", joinEndDate=" + joinEndDate +
                ", raceStartDate=" + raceStartDate +
                ", raceEndDate=" + raceEndDate +
                '}';
    }
}
