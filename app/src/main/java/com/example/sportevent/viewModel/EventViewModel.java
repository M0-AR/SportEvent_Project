package com.example.sportevent.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.process.RequestCall;
import com.example.sportevent.data.repository.EventRepository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private  EventRepository mEventRepository;
    // TODO: 12/01/2021 Come back and see for what to use this
    public MutableLiveData<Event> mEvents;

    public EventViewModel(@NonNull Application application) {
        super(application);

        mEventRepository = new EventRepository(application);
        mEvents = mEventRepository.mEvents;
    }


    public void createEvent(Event event, int id) {
        mEventRepository.createEvent(event, id);
    }

    public MutableLiveData<RequestCall> getAllEvents() {
        return mEventRepository.getAllEvents();
    }


}
