package com.example.sportevent.data.model.process;

import com.example.sportevent.data.model.entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class RequestCall {
    public int status;
    public String message;
    public ArrayList<Event> eventList;
    public ArrayList<Participant> participantList;
}
