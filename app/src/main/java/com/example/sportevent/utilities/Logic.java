package com.example.sportevent.utilities;

import com.example.sportevent.data.model.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class Logic {

    public static ArrayList<Event> getJoinedEventForUserByEmail(ArrayList<Event> events, String email) {
        ArrayList<Event> joinedEvent = new ArrayList<>();
        for (Event event : events) {
            ArrayList<String> emails = (ArrayList<String>) event.getJoinedEventParticipantsEmails();
            for (String s : emails) {
                if (s.equals(email)) {
                    joinedEvent.add(event);
                }
            }
        }
        return joinedEvent;
    }



}
