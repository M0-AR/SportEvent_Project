package com.example.sportevent.data.repository;

import androidx.lifecycle.LiveData;

import com.example.sportevent.data.model.entities.Event;

import java.util.List;

public class EventRepository {
    private static EventRepository instance;
    public LiveData<List<Event>> mEvents;

    public static EventRepository getInstance() {
        if(instance == null) {
            instance = new EventRepository();
        }
        return instance;
    }

    public EventRepository() {
        this.mEvents = getAllEvents();
    }

    private LiveData<List<Event>> getAllEvents() {
        return null;
    }




}
