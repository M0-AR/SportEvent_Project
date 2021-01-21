package com.example.sportevent.utilities;

import com.example.sportevent.data.model.entities.Participant;

import java.util.ArrayList;

public class LogicValidation {
    public static boolean isEmailAlreadyExists(ArrayList<Participant> participants, String email){
        for (Participant participant : participants) {
            if (participant.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
}
