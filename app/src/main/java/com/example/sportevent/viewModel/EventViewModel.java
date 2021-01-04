package com.example.sportevent.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.repository.EventRepository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private  EventRepository mEventRepository;
    public LiveData<List<Event>> mEvents;

    public EventViewModel(@NonNull Application application) {
        super(application);

        mEventRepository = EventRepository.getInstance();
        mEvents = mEventRepository.mEvents;
    }


}
