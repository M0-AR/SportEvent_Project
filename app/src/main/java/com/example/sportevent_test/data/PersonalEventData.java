package com.example.sportevent_test.data;


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
