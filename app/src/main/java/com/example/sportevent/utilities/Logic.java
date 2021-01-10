package com.example.sportevent.utilities;

import com.example.sportevent.data.model.entities.Event;

import java.util.ArrayList;
import java.util.List;

public class Logic {

    // O(n^2)
    public static ArrayList<Event> getJoinedEventForUserByEmail(ArrayList<Event> events, String email) {
        ArrayList<Event> joinedEvent = new ArrayList<>();
        for (Event event : events) {
            List<String> emails = event.getJoinedEventParticipantsEmails();
            if (emails == null) continue;
            for (String s : emails) {
                if (email.equalsIgnoreCase(s)) {
                    joinedEvent.add(event);
                }
            }
        }
        return joinedEvent;
    }

    // O(n^2)
    public static ArrayList<Event> getFinishedEventForUserByEmail(ArrayList<Event> events, String email) {
        ArrayList<Event> joinedEvent = new ArrayList<>();
        for (Event event : events) {
            List<String> emails = event.getFinishedRaceParticipantsEmails();
            if (emails == null) continue;
            for (String s : emails) {
                if (email.equalsIgnoreCase(s)) {
                    joinedEvent.add(event);
                }
            }
        }
        return joinedEvent;
    }

    // O(n)
    public static boolean alreadySignUpToThisEvent(Event event, String userEmail) {
       if (event.getJoinedEventParticipantsEmails().contains(userEmail)) {
           return true;
       } else {
           return false;
       }
    }
}
