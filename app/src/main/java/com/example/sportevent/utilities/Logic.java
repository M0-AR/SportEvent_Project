package com.example.sportevent.utilities;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.Participant;

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
    public static boolean isUserAlreadySignUpToEvent(Event event, String userEmail) {
       if (event.getJoinedEventParticipantsEmails().contains(userEmail)) {
           return true;
       } else {
           return false;
       }
    }


    // O(n^2)
    public static ArrayList<Event> sortEventsByClosestDateToToday(ArrayList<Event> events){
        ArrayList<Event> sortedEvents = events;
        for (int i = 0; i < events.size(); i++) {
            Event min = events.get(i);
            int index = i;
            for (int j = i + 1; j < events.size(); j++) {
                Event event = events.get(j);
                if (min.getJoinStartDate() == null || event.getJoinStartDate() == null) continue;
                if (min.getJoinStartDate().after(event.getJoinStartDate())) { // "Date1 is after Date2"
                    min = event;
                    index = j;
                }
            }
            if (i != index) {
                events.set(index, events.get(i));
                events.set(i, min);
            }
        }
        return sortedEvents;
    }


    // O(n)
    public static boolean isEmailAlreadyExist(ArrayList<Participant> participantArrayList, String userEmail) {
        for (Participant participant : participantArrayList) {
           if (participant.getEmail().equalsIgnoreCase(userEmail)) {
               return true;
           }
        }
        return false;
    }


    // O(n^2)
    public static ArrayList<Participant> getParticipantsWhoJoinedEvent(ArrayList<Participant> participants, Event mEvent) {
        ArrayList<Participant> joinedEvent = new ArrayList<>();
        for (Participant participant : participants) {
            if (mEvent.getJoinedEventParticipantsEmails().contains(participant.getEmail())) {
                joinedEvent.add(participant);
            }
        }
        return joinedEvent;
    }

    // O(n^2)
    public static ArrayList<Participant> getParticipantsWhoFinishedEvent(ArrayList<Participant> participants, Event mEvent) {
        ArrayList<Participant> joinedEvent = new ArrayList<>();
        for (Participant participant : participants) {
            if (mEvent.getFinishedRaceParticipantsEmails().contains(participant.getEmail())) {
                joinedEvent.add(participant);
            }
        }
        return joinedEvent;
    }
}
