package com.example.sportevent.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.process.RequestCall;
import com.example.sportevent.data.repository.EventRepository;

public class EventViewModel extends AndroidViewModel {

    private  EventRepository mEventRepository;

    public EventViewModel(@NonNull Application application) {
        super(application);
        mEventRepository = new EventRepository(application);
    }


    public void createEvent(Event event, int id) {
        mEventRepository.createEvent(event, id);
    }

    public MutableLiveData<RequestCall> getAllEvents() {
        return mEventRepository.getAllEvents();
    }


}
