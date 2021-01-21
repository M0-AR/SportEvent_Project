package com.example.sportevent.utilities;

import com.example.sportevent.data.model.entities.Event;
import com.example.sportevent.data.model.entities.Participant;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogicTest {
    private ArrayList<Event> signUpList;
    private Event firstEvent;
    private Event secondEvent;
    private Event thirdEvent;

    private ArrayList<Participant> participants;

    @Before
    public void initDataTest() {
        //Get a sign up list
        signUpList = new ArrayList<>();
        // Three have sign up to this event, and three finish the race of this event
          //  public Event(int id, int maxNumberOfParticipant, double distance, String imageURL, String eventName, String eventDescription, String location, List<String> joinedEventParticipantsEmails, List<String> finishedRaceParticipantsEmails, Date joinStartDate, Date joinEndDate, Date raceStartDate, Date raceEndDate) {
            firstEvent = new Event(0,0,0.0, " : ","", "description","https://www.google.com/maps/dir/Copenhagen/Spain/@47.7297451,-4.5837011,5z/data=!3m1!4b1!4m14!4m13!1m5!1m1!1s0x4652533c5c803d23:0x4dd7edde69467b8!2m2!1d12.5683372!2d55.6760968!1m5!1m1!1s0xc42e3783261bc8b:0xa6ec2c940768a3ec!2m2!1d-3.74922!2d40.463667!3e1"
                , Arrays.asList("md@gmail.com", "ar@gmail.com", "cr@gmail.com"),Arrays.asList("md@gmail.com", "ar@gmail.com"), new Date(), new Date(), new Date(), new Date() );
        // Three have sign up to this event, and two finish the race of this event
        secondEvent = new Event(1,1,1.0, 1 +" : ","", "description","https://www.google.com/maps/dir/Copenhagen/Spain/@47.7297451,-4.5837011,5z/data=!3m1!4b1!4m14!4m13!1m5!1m1!1s0x4652533c5c803d23:0x4dd7edde69467b8!2m2!1d12.5683372!2d55.6760968!1m5!1m1!1s0xc42e3783261bc8b:0xa6ec2c940768a3ec!2m2!1d-3.74922!2d40.463667!3e1"
                , Arrays.asList("ar@gmail.com", "cr@gmail.com", "md@gmail.com"),Arrays.asList("ar@gmail.com", "cr@gmail.com"), new Date(), new Date(), new Date(), new Date() );
        // Two have sign up to this event, and no one finish the race of this event
        thirdEvent = new Event(2,2,2.0,"", 2 +" : ", "description","https://www.google.com/maps/dir/Copenhagen/Spain/@47.7297451,-4.5837011,5z/data=!3m1!4b1!4m14!4m13!1m5!1m1!1s0x4652533c5c803d23:0x4dd7edde69467b8!2m2!1d12.5683372!2d55.6760968!1m5!1m1!1s0xc42e3783261bc8b:0xa6ec2c940768a3ec!2m2!1d-3.74922!2d40.463667!3e1"
                , Arrays.asList("md@gmail.com", "ar@gmail.com"), null, new Date(), new Date(), new Date(), new Date() );
        signUpList.add(firstEvent);
        signUpList.add(secondEvent);
        signUpList.add(thirdEvent);

        participants = new ArrayList<>();
        participants.add(new Participant(0, "MD" ,"md@gmail.com", "Albertslund", "33333333"));
    }

    @Test
    public void checkJoinedEventListForUserByEmail() {
        // Get the events that cr@gmail.com has been joined
        ArrayList<Event> joinedEventListForUser =  Logic.getJoinedEventListForUserByEmail(signUpList, "cr@gmail.com");
        // cr@gmail.com has just joined tow events (firstEvent and secondEvent)
        assertEquals(2, joinedEventListForUser.size());
        assertEquals(firstEvent, joinedEventListForUser.get(0));
        assertEquals(secondEvent, joinedEventListForUser.get(1));
    }

    @Test
    public void checkFinishedEventListForUserByEmail() {
        // Get the events that cr@gmail.com has been joined
        ArrayList<Event> joinedEventListForUser =  Logic.getFinishedEventListForUserByEmail(signUpList, "cr@gmail.com");
        // cr@gmail.com has just finished one event (secondEvent)
        assertEquals(1, joinedEventListForUser.size());
        assertEquals(secondEvent, joinedEventListForUser.get(0));
    }

    @Test
    public void checkIfEmailAlreadyExist() {
       boolean exist = Logic.isEmailAlreadyExist(participants, "md@gmail.com");
        assertTrue(exist);
    }

}