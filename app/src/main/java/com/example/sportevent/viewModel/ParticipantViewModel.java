package com.example.sportevent.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.Participant;
import com.example.sportevent.data.model.process.RequestCall;
import com.example.sportevent.data.repository.ParticipantRepository;

public class ParticipantViewModel extends AndroidViewModel {
    private ParticipantRepository participantRepository;

    public ParticipantViewModel(@NonNull Application application) {
        super(application);
        this.participantRepository = new ParticipantRepository(application);
    }
    public void createParticipant(Participant participant, String participantEmail) {
        participantRepository.insertParticipant(participant, participantEmail);
    }

    public MutableLiveData<RequestCall> getAllParticipants() {
        return participantRepository.getAllParticipants();
    }
}
