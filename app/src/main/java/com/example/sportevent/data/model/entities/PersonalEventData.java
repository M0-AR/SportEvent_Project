package com.example.sportevent.data.model.entities;

// TODO: 05/01/2021 Why have we this class ???
public class PersonalEventData {

    String racerMail;
    String eventName;
    int result;

    public PersonalEventData(String racerMail, String eventName, int result) {
        this.racerMail = racerMail;
        this.eventName = eventName;
        this.result = result;
    }

    public String getRacerMail() {
        return racerMail;
    }

    public String getEventName() {
        return eventName;
    }

    public int getResult() {
        return result;
    }

}
