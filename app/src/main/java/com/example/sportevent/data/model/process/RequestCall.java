package com.example.sportevent.data.model.process;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.Participant;
import com.example.sportevent.data.model.entities.Result;

import java.util.ArrayList;
import java.util.HashMap;


public class RequestCall {
    public int status;
    public String message;
    public ArrayList<Event> eventList;
    public ArrayList<Participant> participantList;
    // String -> eventId
    public HashMap<String,Result> participantResultList;
}
