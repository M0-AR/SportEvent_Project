package com.example.sportevent.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.Participant;
import com.example.sportevent.data.repository.ParticipantRepository;

public class ParticipantViewModel extends AndroidViewModel {
    private ParticipantRepository participantRepository;

    public ParticipantViewModel(@NonNull Application application) {
        super(application);
        this.participantRepository = new ParticipantRepository(application);
    }
    public void createParticipant(Participant participant) {
        participantRepository.insertParticipant(participant);
    }

}
